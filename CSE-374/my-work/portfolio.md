# Ari Madian's CSE374 Portfolio

## Weekly work

- Week 1: input summary program that prints the number of characaters, words, and vowels contained in an input string.
  - see `work/akmadian/stdinsummary.c`
- Week 2: Wrote a mini grep, really basic, no actual regex support, just basic pattern matching.
  - see `work/akmadian/week2`
- Week 3: I wrote two methods of doing binary search, one with regular indexing notation, and one with pointers.
  - see `work/akmadian/week3.c`
  - I also wrote a program that purposefully had an error, so that I could go through the reasoning of figuring out a seg fault
- Week 4: To learn about dynamic memory allocation, I wrote a program that converts a ppm image to greyscale.
  - see `work/akmadian/ppmGrayscale.c`
  - Learning about the ppm format was a lot of fun, most file formats have very complex encoding, but ppm was really nice and simple
- Week 5: To learn about structs and header files, I wrote a simple program that implements a stack
  - see `work/akmadian/week5-stack.c` and `work/akmadian/week5-stack.h`
- Week 6: Assembly week! This was actually my favorite week. I wrote a C program that computes the hamming distance between two numbers, and an assembly program that does the same.
  - see the `work/akmadian/week6` dir.
  - This week was my favorite because I've always wanted to learn more about assembly, and it forced me to think in new and different ways.
- Week 7: I wrote a triangle classifier program, I actually really like my code for this, I think it's quite nice and clean.
  - see the `work/akmadian/week7` dir.
  - I also wrote a basic shell script that gets my private and public IP address, as well as some information about my wifi signal, and prints it out.
- Week 8: I wrote some testing code for my triangle classifier.
  - see the `work/akmadian/week8` dir.
  - I only had a few tests because there's a good amount of type safety enforced throughout the program.
      - I updated the code that gets user input to enforce integer vs float vs string, etc. input, and the number of integers input
      - Given that the methods only take integers, it's not possible to pass a double or float or any type other than an int in
      - I didn't worry about possible division creating doubles or floats because dividing into an int variable automatically casts
  - I wrote a few manual tests that incorporated negative numbers, lines, all of the points in the same place, etc.

- Week 9: I opted not to do the work this week since I was buried in other classes.
- Week 10: As of writing this, I haven't done the week 10 work yet, but I definitely will since I have been wanting to try out rust for a while.

## Other projects
- My extra credit project 1 was a sort of ls clone with some of my own modifications that I wanted.
  - see `work/akmadian/ec1`
  - I'm quite happy with this program because the dynamic memory allocation got quite tricky at times, but I was able to figure it out.
  - If I would continue development of this script, I would probably work on performance improvements.
- My extra credit project 2 was a status bar program for my window manager of choice, DWM.
  - see `work/akmadian/ec2`
  - I used shell scripting for this, and the program includes asyncrounous update loops, volume, battery, and system sensor information
  - I continued development, and have added more system information, network information, and uptime information, among lots of other tweaks.
- My third extra credit project was a shell daemon that would fetch any new email messages to my computer every 10 minutes.
  - see `work/akmadian/ec3`
  - If there are any new messages, it sends a notification via herbe.
  - The program includes piping and such, conditional statements and arithmetic.

## Reflections and feedback

(This part is optional. Put your favorite things you learned this quarter here. Or let the course staff know what worked well and what didn't.)

- This class was my favorite by far. It's inspired me to get more experience with low level and systems programming (I'm trying to petition into CSE 351 next quarter).
- I absolutely loved assembly. I think I'd want to bash my head in with my computer if I had to use it professionally, but for a little project, it was super fun.
- C is really tricky, but it may be my favorite language. I love how simple it is.

