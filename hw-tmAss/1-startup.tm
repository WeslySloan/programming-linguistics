// ==================== 
// c startup
// ==================== 
0: ld gp, 0(0)       // gp = dMem[0] (1023)
1: st 0, 0(0)        // dMem[0] = 0

2: lda fp, -0(gp)    // fp = gp, no global vars
3: lda sp, -0(gp)    // sp = fp

4: push fp           // fp(startup) saved at addr 1023
5: lda 0, 2(pc)      // r0 <- 8: return address
6: push 0            // save the retrun address
7: ldc pc, 9         // jump to main

8: halt

// ==================== 
// main()
// ====================
9: lda sp, -0(sp)     // 0 means no local vars in main
10: ldc 27, 0         // return value of main
11: lda sp, 0(fp)     // restore the saved sp
12: ld fp, 0(fp)      // restor the old fp (fp-startup)
13: ld pc, -1(sp)     // jump to the return address (8)
// ====================
