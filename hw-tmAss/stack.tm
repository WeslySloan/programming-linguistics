// ======================================================================
// stack.tm

0000: LD   gp, 0(0)	// gp = dMem[0]
0001: ST   0, 0(0)	// dMem[0] = 0
0002: LDA  fp, -1(gp)	// fp = gp + sizeof(global vars),  no global vars
0003: LDA  sp, -1(gp)	// sp = gp + sizeof(global vars)

// ======================================================================

0004: IN   0		// r0 <= IN
0005: PUSH 0		// push r0
0006: IN   0		// r0 <= IN
0007: PUSH 0		// push r0
0008: IN   0		// r0 <= IN
0009: PUSH 0		// push r0
0010: POP  0		// pop r0
0011: OUT  0		// r0 => OUT
0012: POP  0		// pop r0
0013: OUT  0		// r0 => OUT
0014: POP  0		// pop r0
0015: OUT  0		// r0 => OUT
0016: HALT		// halt

// ======================================================================

// parameter passing

// #include <stdio.h>
// 
// int f() {
//   int a;
//   int b;
//   int c;
//   printf("<= "); scanf("%d", &a); // input from keyboard
//   printf("<= "); scanf("%d", &b); // input from keyboard
//   printf("<= "); scanf("%d", &c); // input from keyboard
//   printf ("=> %d\n", c);
//   printf ("=> %d\n", b);
//   printf ("=> %d\n", a);
//   return 0;
// }
// 
// int main () {
//   f();
// }
