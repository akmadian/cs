#include <stdio.h>
#include <stdlib.h>
#include "week5-stack.h"

struct stack* make_stack(struct stack* s, int data) {
  struct stack* root = (struct stack*) malloc(sizeof(struct stack));
  root->data = data;
  root->next = s;
  return root;
}

int pop(struct stack** s) {
  int data = (*s)->data;
  *s = (*s)->next;
  return data;
}

void push(struct stack** s, int x) {
  *s = make_stack(*s, x);
}

int peek(struct stack* s) {
  int x = pop(&s);
  push(&s, x);
  return x;
}

void free_stack(struct stack* s) {
  while (s) {
    struct stack* next = s->next;
    free(s);
    s = next;
  }
}

void print_stack(struct stack* s) {
  while (s) {
    printf("%d\n", s->data);
    s = s->next;
  }
}
