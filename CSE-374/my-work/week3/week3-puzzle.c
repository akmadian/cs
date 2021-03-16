#include <stdio.h>

int main(int argc, char** argv) {
  if (argc < 1) {
    return 1;
  }

  FILE* file = fopen(argv[1], "r");
  char line[1000] = {0};
  while (fgets(line, 1000, file) != NULL) {
    fputs(line, stdout);
  }

  return 0;
}

/*
 * This program just prints the contents of a file, but when it tries to read a file that doesn't exist,
 * it outputs this error:
 *
AddressSanitizer:DEADLYSIGNAL
=================================================================
==3170==ERROR: AddressSanitizer: SEGV on unknown address 0x000000000000 (pc 0x7f4d823e1f5b bp 0x7fffc9e80390 sp 0x7fffc9e7fb10 T0)
==3170==The signal is caused by a READ memory access.
==3170==Hint: address points to the zero page.
    #0 0x7f4d823e1f5a in fgets (/lib/x86_64-linux-gnu/libc.so.6+0x6ff5a)
    #1 0x43ba8c in __interceptor_fgets (/home/ari/repos/cse374-21wi-monorepo/work/akmadian/week3-puzzle+0x43ba8c)
    #2 0x4f451e in main /home/ari/repos/cse374-21wi-monorepo/work/akmadian/week3-puzzle.c:10:10
    #3 0x7f4d8239609a in __libc_start_main (/lib/x86_64-linux-gnu/libc.so.6+0x2409a)
    #4 0x41d2d9 in _start (/home/ari/repos/cse374-21wi-monorepo/work/akmadian/week3-puzzle+0x41d2d9)

AddressSanitizer can not provide additional info.
SUMMARY: AddressSanitizer: SEGV (/lib/x86_64-linux-gnu/libc.so.6+0x6ff5a) in fgets
==3170==ABORTING
*/

/*
 * This is pretty hard to understand, but based on the fact that it says it's because of a READ access,
 * I would check to see what exactly it's tring to read and identify points of failure from there.
 *
 * You can solve this by checking if the pointer is NULL first, and exiting with a more useful error message if so.
 * */
