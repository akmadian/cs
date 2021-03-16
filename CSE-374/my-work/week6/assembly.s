.globl hammingDistASM
# arg | 1    2    3    4    5   6
# reg | %rdi %rsi %rdx %rcx %r8 %r9
# val | p1   p2   x    bits tmp 0
hammingDistASM:
  movq  %rsi, %rdx # Copy n1 to %rdx
  xorq  %rdi, %rdx # x Var, n1/%rdi ^ n2/%rsi
  xorq  %rcx, %rcx # setBits Var, start at 0
  xorq  %r9,  %r9  # Is just 0, doesn't change
loop:
  cmpq  %rdx, %r9  # %rdx > 0, with %rcx = 0
  je    end

  # setBits += x & 1;
  movq  %rdx, %r8  # Copy x to tmp reg
  andq  $1,   %r8  # Calc x & 1 -> %r8
  addq  %r8,  %rcx
  #movq  %r8,  %rcx # Move %r8 to setBits/%rcx

  # Bitwise x >> 1
  shrq  $1,   %rdx
  jmp loop
end:
  movq  %rcx, %rax
  ret

