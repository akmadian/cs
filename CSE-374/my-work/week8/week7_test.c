#include <stdio.h>
#include <math.h>

int validate_area(int x1, int x2, int x3, int y1, int y2, int y3) {
  int area = x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2);

  if (area == 0)
    return -1;

  return 0;
}

int side_length(int x1, int y1, int x2, int y2) {
  return (int) sqrt(pow(x1 - x2, 2) + pow(y1 - y2, 2));
}

char *edge_classification(int side_length_1, int side_length_2, int side_length_3) {
  if (side_length_1 == side_length_2 && side_length_2 == side_length_3)
    return "Equilateral";
  else if (side_length_1 == side_length_2 || side_length_1 == side_length_3 || side_length_2 == side_length_3)
    return "Isocoles";
  else
    return "Scalene";
}

char *angle_classification(int side_length_1, int side_length_2, int side_length_3) {
  int a = (int) pow(side_length_1, 2);
  int b = (int) pow(side_length_2, 2);
  int c = (int) pow(side_length_3, 2);

  if (a == b + c || b == c + a || c == a + b)
    return "Right";
  else if (a > c + b || b > a + c || c > a + b)
    return "Obtuse";
  else
    return "Acute";
}

int classify(int x1, int x2, int x3, int y1, int y2, int y3) {
  int area_valid = validate_area(x1, x2, x3, y1, y2, y3);
  if (area_valid == -1) {
    printf("Error: area can not be 0.\n");
    return -1;
  }

  int side_length_1 = side_length(x1, y1, x2, y2);
  int side_length_2 = side_length(x2, y2, x3, y3);
  int side_length_3 = side_length(x1, y1, x3, y3);

  char* edge = edge_classification(side_length_1, side_length_2, side_length_3);
  char* angl = angle_classification(side_length_1, side_length_2, side_length_3);

  printf("The entered triangle is an %s %s triangle.\n", angl, edge);
  return 0;
}

void test() {
  int failed = 0;
  int total  = 3;
  printf("Running tests prior to input\n");

  failed += classify(1, 2, 33, 4, 555, 6);
  failed += classify(-1, 2, 3, 4, 5, 666);
  failed += classify(-2, -1, -10, -20, -32483, -5324);

  printf("Passed %i/%i tests.\n", total - failed, total);
}

int main() {
  int x1, x2, x3 = 0;
  int y1, y2, y3 = 0;

  test();

  printf("Enter 6 points of the triangle separated by spaces:\n");
  printf("x1 x2 x3 y1 y2 y3:\n");
  int nargs = scanf("%d %d %d %d %d %d", &x1, &x2, &x3, &y1, &y2, &y3);
  if (nargs == 0) {
    printf("Error: Please enter exactly 6 coordinate values.");
    return -1;
  } else if (nargs != 6) {
    printf("Error: Triangle Classifier input only accepts integers.");
    return -1;
  }

  classify(x1, x2, x3, y1, y2, y3);

  return 0;
}
