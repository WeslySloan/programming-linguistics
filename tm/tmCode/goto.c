#include <stdio.h>

int fact(int);
int fact2(int);

int main () {
  int n = 4;
  printf ("fact(%d) : %d\n", n, fact(n));
}

// using goto instead of while
int fact (int n) {
  int result = 1;
  ll:
  if (n > 1) {
    result = result * n;
    n = n - 1;
    goto ll;
  }
  return result;
}

int fact2 (int n) {
  int result = 1;
  while (n > 1) {
    result = result * n;
    n = n - 1;
  }
  return result;
}
