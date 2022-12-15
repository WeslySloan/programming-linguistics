// ======================================================================
// fact.tm

0000: IN   0		      // r0 <= IN,      r0 => n
0001: JLE  0, 6(pc)	  // if r0 <= 0 then goto 8
0002: LDC  1, 1		    // r1 = 1,        r1 => result (1)
0003: LDC  2, 1		    // r2 = 1         r2 => 1
0004: MUL  1, 1, 0	  // r1 = r1 * r0   result = result * n
0005: SUB  0, 0, 2	  // r0 = r0 - r2   n = n - 1
0006: JNE  0, -3(pc)  // if r0 != 0 then goto 4
0007: OUT  1		      // r1 => OUT
0008: HALT		        // 

// ======================================================================


// n! computation

// #include <stdio.h>
// int main () {
//   int n;
//   printf("<= "); scanf("%d", &n); // input from keyboard
//   if (n <= 0) return -1;          // wrong input
//   int result = 1;
//   label:
//     result = result * n;
//     n = n - 1;
//   if (n != 0) goto label;
//   printf ("=> %d\n", result);
//   return 0;
// }
