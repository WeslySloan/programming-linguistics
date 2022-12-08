// ======================================================================
// add.tm

// three local variables
// but they are not stored in the stack

0000: IN   0		    // r0 <= IN
0001: LDC  3, 0		  // r3 = 0
0002: ST   0, 0(3)	// dMem[r3] (addr 0) = r0
0003: IN   1		    // r1 <= IN
0004: LDC  3, 1		  // r3 = 1
0005: ST   1, 0(3)	// dMem[r3] (addr 1) = r1
0006: ADD  2, 0, 1	// r2 = r0 + r1
0007: LDC  3, 2		  // r3 = 2
0008: ST   2, 0(3)	// dMem[r3] (addr 2) = r2
0009: OUT  2		    // r2 => OUT
0010: HALT		      // halt

// ======================================================================

// Not exactly correspond to the above

// add two numbers

// #include <stdio.h>
// int main () {
//   int a;
//   int b;
//   int c;
//   printf("<= "); scanf("%d", &a); // input from keyboard
//   printf("<= "); scanf("%d", &b); // input from keyboard
//   c = a + b;
//   printf ("=> %d\n", c);
//   return 0;
// }
