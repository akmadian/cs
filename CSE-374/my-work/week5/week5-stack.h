struct stack {
  int data;
  struct stack* next;
};

struct stack* make_stack(struct stack* s, int x);
int pop(struct stack** s);
void push(struct stack** s, int x);
int peek(struct stack* s);
void free_stack(struct stack* s);
void print_stack(struct stack* s);

