n_numbers <- 50
mean_numbers <- 25
stdev_numbers <- 10

sample <- rnorm(n_numbers, mean_numbers, stdev_numbers)
sample_mean <- round(mean(sample), 2)