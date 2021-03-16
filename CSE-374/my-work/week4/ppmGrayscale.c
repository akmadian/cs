/* Takes in a ppm image, converts it to grayscale, and outputs a pgm image.
 * Usage: ppmGrayscale in.ppm out.pgm
 */

#include <stdio.h>
#include <stdlib.h>

#define BYTES_PER_PIXEL 3

void applyGreyscale(unsigned char* image, unsigned int i) {
  unsigned char avg = (image[i] + image[i + 1] + image[i + 2]) / 3;
  image[i] = avg;
  image[i + 1] = avg;
  image[i + 2] = avg;
}

int main(int argc, char *argv[]) {
  if (!(argc == 3)) {
    printf("Incorrect number of parameters");
    return 1;
  }

  FILE* f = fopen(argv[1], "r");

  unsigned int width, height, max;
  fscanf(f, "P3 %u %u %u", &width, &height, &max);

  if (max != 255) {
    printf("Maximum value must be exactly 255 for ppm.");
    return 1;
  }

  unsigned long num_bytes = BYTES_PER_PIXEL * width * height;
  unsigned char* image = malloc(num_bytes);

  for (unsigned long i = 0; i < num_bytes; i++) {
    unsigned int x;
    fscanf(f, "%u", &x);
    image[i] = (unsigned char) x;
  }

  fclose(f);

  for (unsigned int i = 0; i < num_bytes; i += 3) {
    applyGreyscale(image, i);
  }

  FILE* f_out = fopen(argv[2], "w");
  fprintf(f_out, "P3 %u %u %u", width, height, max);
  for (unsigned int i = 0; i < num_bytes; i++) {
    if (i % BYTES_PER_PIXEL == 0) {
      fprintf(f_out, "\n");
    } else {
      fprintf(f_out, " ");
    }
    fprintf(f_out, "%u", image[i]);
  }

  fprintf(f_out, "\n");
  fclose(f_out);
  free(image);

  return 0;
}

