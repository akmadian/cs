# Exercise 1: calling built-in functions

# Create a variable `my_name` that contains your name
name <- "ari"

# Create a variable `name_length` that holds how many letters (including spaces)
# are in your name (use the `nchar()` function)
name_length <- nchar(name)

# Print the number of letters in your name
print(name_length)

# Create a variable `now_doing` that is your name followed by "is programming!" 
# (use the `paste()` function)
now_doing <- paste(name, " is programming!")

# Make the `now_doing` variable upper case
toupper(now_doing)

### Bonus

# Pick two of your favorite numbers (between 1 and 100) and assign them to 
# variables `fav_1` and `fav_2`
fav1 <- 100
fav2 <- 25

# Divide each number by the square root of 201 and save the new value in the
# original variable
fav1 <- fav1 / sqrt(201)
fav2 <- fav2 / sqrt(201)


# Create a variable `raw_sum` that is the sum of the two variables. Use the 
# `sum()` function for practice.
raw_sum <- sum(fav1, fav2)


# Create a variable `round_sum` that is the `raw_sum` rounded to 1 decimal place.
# Use the `round()` functzion.
round_sum <- round(raw_sum, 1)


# Create two new variables `round_1` and `round_2` that are your `fav_1` and 
# `fav_2` variables rounded to 1 decimal places
round_1 <- round(fav1, 1)
round_2 <- round(fav2, 1)

# Create a variable `sum_round` that is the sum of the rounded values
sum_round <- sum(round_1, round_2)

# Which is bigger, `round_sum` or `sum_round`? (You can use the `max()` function!)
max(round_sum, sum_round)
