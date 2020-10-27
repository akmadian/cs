# Exercise 1: creating data frames

# Create a vector of the number of points the Seahawks scored in the first 4 games
# of the season (google "Seahawks" for the scores!)
points <- c(38, 31, 27, 0)


# Create a vector of the number of points the Seahwaks have allowed to be scored
# against them in each of the first 4 games of the season
opp_points <- c(31, 23, 26, 0)


# Combine your two vectors into a dataframe called `games`
games <- data.frame(points, opp_points)


# Create a new column "diff" that is the difference in points between the teams
# Hint: recall the syntax for assigning new elements (which in this case will be
# a vector) to a list!
games$diff <- points - opp_points


# Create a new column "won" which is TRUE if the Seahawks won the game
games$won <- games$diff > 0


# Create a vector of the opponent names corresponding to the games played
opponents = c("Dallas Cowboys", "Miami Dolphins", "Minnesota Vikings", "Arizona Cardinals")

# Assign your dataframe rownames of their opponents
games$opponents <- opponents

# View your data frame to see how it has changed!
View(games)
