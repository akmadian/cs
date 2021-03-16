#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

#define ARRSIZE 50

// Generates an array of random integers of size 'size'
// The integers can take values from 0 to (size * 2) - 1
int * genSearchArray() {
  static int r[ARRSIZE];

  srand( (unsigned int)time(NULL) );
  for (int i = 0; i < ARRSIZE; ++i) {
    *(r + i) = (rand() % (ARRSIZE * 2));
  }

  return r;
}

// Return location of x in given array arr[l..r] if
// is present, otherwise -1
// Use indexing to do search
int binSearchIdx(int arr[], int l, int r, int x) {
  int m;

  if (l > r)
    return -1;

  m = (l + r) / 2;

  if (arr[m] == x)
    return m;
  else if (x > arr[m])
    return binSearchIdx(arr, m + 1, r, x);
  else
    return binSearchIdx(arr, l, m - 1, x);
}

// Same as method above, but using pointers instead of indexes
int binSearchPtr(int *arr, int l, int r, int x) {
  int m;

  if (l > r)
    return -1;

  m = (l + r) / 2;

  if (*(arr + m) == x)
    return m;
  else if (x > *(arr + m))
    return binSearchPtr(arr, m + 1, r, x);
  else
    return binSearchPtr(arr, l, m - 1, x);
}

// I took this function from a tutorialspoint site
int qsortFunc(const void * a, const void * b) {
  return ( *(int*)a - *(int*)b );
}

int main(int argc, char *argv[]) {
  argc = 0; // Avoid unused var error

  // Generate array of random integers
  int *searchArray = genSearchArray();
  // Sort array so we can do a binary search
  qsort(searchArray, ARRSIZE, sizeof(int), qsortFunc);
  // Generate random int to search for
  int toSearchFor = rand() % (ARRSIZE * 2);

  printf("Searching for: %d in:\n", toSearchFor);
  for (int i = 0; i < ARRSIZE; i++) {
    printf("%d ", searchArray[i]);
  }
  printf("\n");

  int res = -2;
  if (strcmp(argv[1], "--pointers") == 0) {
    res = binSearchPtr(searchArray, 0, ARRSIZE - 1, toSearchFor);
  } else if (strcmp(argv[1], "--indexes") == 0) {
    res = binSearchIdx(searchArray, 0, ARRSIZE - 1, toSearchFor);
  } else {
    printf("Invalid search type, enter either '--indexes' or '--pointers'.");
    return 1;
  }


  if (res != -1)
    printf("Found %d at index %d\n", toSearchFor, res);
  else
    printf("%d not found", toSearchFor);

  return 0;
}

