#include <stdio.h>
#include <ctype.h>
#include <string.h>

#define MAX_LINE_LENGTH 1000

const char* normalize_case(int len, char* str) {
  for (int i = 0; i < len; i++) {
    str[i] = str[i] + 32;
  }
  return str;
}

// checks if str contains pattern
int contains(char* str, char* pattern, int ignore_case) {
  if (ignore_case == 1) {
    int str_len = (int) (strlen(str));
    int pattern_len = (int) (strlen(pattern));

    normalize_case(str_len, str);
    normalize_case(pattern_len, pattern);
  }

  // returns null if pattern not in str
  if (strstr(str, pattern) != NULL) {
    return 1;
  }

  return 0;
}

int main(int argc, char* argv[]) {
  int IGNORE_CASE = 0;

  if (argc < 2) {
    printf("Please be sure to include a search pattern.\n");
    return 1;
  }

  char* pattern = argv[1];
  FILE* input = NULL;
  for (int i = 2; i < argc; i++) {
    if (strcmp(argv[i], "-i") == 0) {
      IGNORE_CASE = 1;
    } else {
      input = fopen(argv[2], "r");
      if (input == NULL) {
        printf("There was an error when opening the file.\n");
        return 1;
      }
    }
  }

  char str[MAX_LINE_LENGTH] = {0};
  if (input == NULL)
    input = stdin;

  if (pattern == NULL)
    pattern = argv[1];

  while (fgets(str, MAX_LINE_LENGTH, input)) {
    char temp_str[MAX_LINE_LENGTH] = {0};

    strcpy(temp_str, str);
    if (contains(str, pattern, IGNORE_CASE)) {
      fputs(str, stdout);
    }
  }
  return 0;
}
