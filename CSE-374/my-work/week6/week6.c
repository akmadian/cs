#include <stdio.h>
#include <string.h>

int hammingDistance(long n1, long n2) {
  long x = n1 ^ n2;
  int setBits = 0;

  while (x > 0) {
    setBits += x & 1; /* If */
    x >>= 1;
  }

  return setBits;
}

long hammingDistASM(long n1, long n2);

int main(int argc, char* argv[]) {
  long n1 = 60;
  long n2 = 13;

  if (argc == 1) {
    printf("Please select whether to use --C or --ASM.\n");
  } else {
    printf("Computing the hamming distance of %li and %li\n", n1, n2);
    if (strcmp(argv[1], "--C") == 0) {
      int dist = hammingDistance(n1, n2);
      printf("Distance: %i\n", dist);
    } else if (strcmp(argv[1], "--ASM") == 0) {
        long distASM = hammingDistASM(n1, n2);
        printf("%li\n", distASM);
    }
  }
}
