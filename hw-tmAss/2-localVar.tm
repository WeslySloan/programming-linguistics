// ==================== 
// c startup
// ==================== 

0: ld gp, 0(0)      // gp = dMem[0] (1023) 
1: st 0, 0(0)       // dMem[0] = 0
2: lda fp, -0(gp)   // fp = gp, no global vars
3: lda sp, -0(gp)   // sp = fp
4: push fp          // fp(startup) saved at addr 1023
5: lda 0, 2(pc)     // r0 <- 8: return address
6: push 0           // save the retrun address
7: ldc pc, 9        // jump to main
8: halt             // end of execution

// ==================== 

// int main() {
//   int l1;
//   int l2;
// 
//   l1 = 0;
//   l2 = 1;
//   output l1;
//   output l2;
// }

// ====================

9: lda sp, -2(sp)    // there are 2 local variables in main()

10: ldc 0, 0         // r0 <- 0
11: st 0, -2(fp)     // -2(fp) means first local varirable l1: l1 <- 0

12: ldc 0, 1         // r0 <- 1
13: st 0, -3(fp)     // -3(fp) means second local varirable l2: l2 <- 0

14: ld 0, -2(fp)     // r0 <- l1 
15: out 0            // output l1

16: ld 0, -3(fp)     // r0 <- l2
17: out 0            // output l2

18: ldc 27, 0        // return value of main()

19: lda sp, 0(fp)    // sp <- fp: remove the space for the local variables of main()
20: ld fp, 0(fp)     // fp <- oldfp (startup)
21: ld pc, -1(sp)    // pc <- return address of startup
