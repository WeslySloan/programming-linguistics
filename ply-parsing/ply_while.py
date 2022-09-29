# -----------------------------------------------------------------------------
# While Language translated into AST
# Defining yacc, the dangling ELSE is not solved yet. 
# "if (cond) stmt" is removed. Instead
# "if (cond) stmt else skip" is used
# -----------------------------------------------------------------------------

# Tokens
reserved = {       # key(string) : value(TokenType)
   'if' : 'IF',
   'else' : 'ELSE',
   'while' : 'WHILE',
   'print' : 'PRINT',
   'skip'  : 'SKIP'
}
tokens = ('NAME', 'NUMBER', 'GE', 'LE', 'EQ') + tuple(reserved.values())
literals = ['=', '+', '-', '*', '/', '(', ')', ';', '<', '>', '{', '}']

t_GE    = r'>='
t_LE    = r'<='
t_EQ    = r'=='

def t_NAME(t):
    r'[a-zA-Z_][a-zA-Z_0-9]*'
    # Check for reserved words:
    # if not listed in the dictionary, the default type is set to NAME
    t.type = reserved.get(t.value,'NAME')    
    return t

def t_NUMBER(t):
    r'\d+'
    t.value = int(t.value)
    return t

t_ignore = " \t\n"


def t_newline(t):
    r'\n+'
    t.lexer.lineno += t.value.count("\n")


def t_error(t):
    print("Illegal character '%s'" % t.value[0])
    t.lexer.skip(1)

# Build the lexer
import ply.lex as lex
lex.lex()

###### Parsing rules ###############

precedence = (
#    ('nonassoc', 'THEN', 'ELSE'),  # Nonassociative operators
    ('left', 'GE', 'LE', 'EQ', '>', '<'),
    ('left', '+', '-'),
    ('left', '*', '/'),
    ('right', 'UMINUS'),
)

# CFG Definition

def p_program(p):
    'program : stmtList'
    p[0] = p[1]

def p_stmtList_single(p):
    'stmtList : stmt'
    p[0] = p[1]

def p_stmtList(p):
    'stmtList : stmtList stmt'
    p[0] = (';', p[1], p[2])

def p_stmt_semi(p):
    'stmt : ";" '
    p[0] = (';', None, None)

def p_stmt_expr(p):
    'stmt : expr ";" '
    p[0] = p[1]

def p_stmt_skip(p):
    'stmt : SKIP ";" '

def p_stmt_print(p):
    'stmt : PRINT expr ";" '
    p[0] = ('PRINT',p[2])

def p_stmt_assgn(p):
    'stmt : NAME "=" expr ";" '
    p[0] = ('=', p[1], p[3])

def p_stmt_while(p): 
    'stmt : WHILE "(" expr ")" stmt'
    p[0] = ('WHILE', p[3], p[5])

def p_stmt_if(p):
    'stmt :  IF "(" expr ")" stmt ELSE stmt '
    p[0] = ('IF_2', p[3], p[5], p[7])

# Dangling ELSE is not solved yet

# def p_stmt_if_1(p):
#    'stmt :  IF "(" expr ")" stmt    %prec THEN '
#    p[0] = ('IF_1', p[3], p[5])
#
# def p_stmt_if_2(p):
#    'stmt :  IF "(" expr ")" stmt ELSE stmt '
#    p[0] = ('IF_2', p[3], p[5], p[7])

def p_stmt_block(p):
    'stmt : "{" stmtList "}" '
    p[0] = p[2]

def p_expr_number(p):
    "expr : NUMBER"
    p[0] = ('NUMBER', p[1])

def p_expr_name(p):
    "expr : NAME"
    p[0] = ('NAME', p[1])

def p_expr_uminus(p):
    "expr : '-' expr %prec UMINUS"
    p[0] = ('UMINUS', p[2])
    
def p_expr_binop(p):
    '''expr : expr '+' expr
            | expr '-' expr
            | expr '*' expr
            | expr '/' expr
            | expr '<' expr
            | expr '>' expr
            | expr GE expr
            | expr LE expr
            | expr EQ expr '''
    if p[2] == '+':
        p[0] = ('+', p[1], p[3])
    elif p[2] == '-':
        p[0] = ('-', p[1], p[3])
    elif p[2] == '*':
        p[0] = ('*', p[1], p[3])
    elif p[2] == '/':
        p[0] = ('/', p[1], p[3])
    elif p[2] == '<':
        p[0] = ('<', p[1], p[3])
    elif p[2] == '>':
        p[0] = ('>', p[1], p[3])
    elif p[2] == 'GE':
        p[0] = ('GE', p[1], p[3])
    elif p[2] == 'LE':
        p[0] = ('LE', p[1], p[3])
    elif p[2] == 'EQ':
        p[0] = ('EQ', p[1], p[3])
    
def p_expr_group(p):
    "expr : '(' expr ')'"
    p[0] = p[2]

def p_error(p):
    if p:
        print("Syntax error at '%s'" % p.value)
    else:
        print("Syntax error at EOF")

import ply.yacc as yacc
yacc.yacc()

###############  Test Code  ##################
prog_exp = '''
3 + 4 * 10
 + -20 *2 ; '''

prog_assign = '''
x = 1 + 12 ; y = 3 * 4; print x + y; '''

prog_fac = '''
x = 4;
y = 1;
while (x > 0) {
    y = y * x;
    x = x - 1;
}  
print y; '''

prog_print = '''
x = 0;
while (x < 3) {
    print x;
    x = x + 1;
} '''

prog_max = '''
x = 20;
y = 30;
z = 10; 
max = 0;
if (x>y)
    if(x>z)
        max = x;
    else
        max = z;
else
    if (y>z)
        max = y;
    else
        max = z;
print max; '''

prog_skip = '''
x = 10; 
if (x == 10) skip; else skip;
print x; '''

#############################################

# lexer.input(data)

ast = yacc.parse(prog_fac)
print(ast)

# dictionary of names
names = {}

def eval(tree):
    if not tree:          return 0      # tree == None
    opr = tree[0]
    if opr == '+':        return eval(tree[1]) +  eval(tree[2])
    elif opr == '-':      return eval(tree[1]) - eval(tree[2])
    elif opr == '*':      return eval(tree[1]) *  eval(tree[2])
    elif opr == '/':      return eval(tree[1]) /  eval(tree[2])
    elif opr == '>':      return eval(tree[1]) >  eval(tree[2])
    elif opr == '<':      return eval(tree[1]) <  eval(tree[2])
    elif opr == 'GE':     return eval(tree[1]) >=  eval(tree[2])
    elif opr == 'LE':     return eval(tree[1]) <=  eval(tree[2])
    elif opr == 'EQ':     return eval(tree[1]) ==  eval(tree[2])
    elif opr == 'UMINUS': return - eval(tree[1])
    elif opr == 'NUMBER': return tree[1]
    elif opr == 'NAME':   return names[tree[1]]
    elif opr == ';':      eval(tree[1]); return eval(tree[2])
    elif opr == 'SKIP':   pass                                 # do nothing
    elif opr == 'PRINT':  print(eval(tree[1]))
    elif opr == '=':      names[tree[1]] = eval(tree[2])
    elif opr == 'WHILE':
        while eval(tree[1]):
            eval(tree[2])
    # elif opr == 'IF_1':
    #    if (eval(tree[1])):
    #        eval(tree[2])
    elif opr == 'IF_2':
        if (eval(tree[1])):
            eval(tree[2])
        else: eval(tree[3])
    else: 
        print("unexpeced case : ", tree)

print(eval(ast))


















        

    
