#include <stdio.h>

int main(int argc, char *argv[]) {
    int c;
    int n_chars = 0;
    int n_words = 0;
    int n_vowels = 0;

    while ((c = getc(stdin)) != '!') {
        putc(c, stdout);
        n_chars++;

        if ('a' <= c && c <= 'z') {
            c -= 32;
        }
        if (c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U') {
            n_vowels++;
        }
        if (c == ' ') {
            n_words++;
        }
    }
    
    printf("\nChars: %d\n", n_chars);
    printf("Words: %d\n", n_words);
    printf("Vowels: %d\n", n_vowels);

    return 0;
}
