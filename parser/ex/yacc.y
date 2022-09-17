%token NUMBER

%%
statement:   expression                {printf("=%d\n", $1);}
;

expression:  NUMBER                    {$$=$1;}
             | expression '+' NUMBER   {$$=$1+$3;}
             | expression '-' NUMBER   {$$=$1-$3;}
;

%%
int main () {
  yyparse ();
}

yyerror (char *s) {   // called by yyparse on error
  printf ("%s\n", s);
}
