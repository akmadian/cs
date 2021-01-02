public class Date {

    // Public so that tests work
    public int year;
    public int month;
    public int day;

    //region Constructors
    // Default Constructor
    public Date(){
        this.year = 1753;
        this.month = 1;
        this.day = 1;
    }

    /*
    <Date> - Creates a date object with the given year, month, and day.
        <param> int year  - The year of the date, must be between 1753 and 9999 (inclusive).
        <param> int month - The month of the date, must be between 1 and 12 (inclusive).
        <param> int day   - The day of the date, must be between 1 and 31 (inclusive).
     */
    public Date(int year, int month, int day){
        if (!validateDate(year, month, day)){ throw new IllegalArgumentException("Invalid Month, Day, or Year.");}
        this.year = year;
        this.month = month;
        this.day = day;
    }
    //endregion

    /*
    <toString> - Returns the current date instance in yyyy/mm/d format
     */
    public String toString(){
        return this.year + "/" + this.month + "/" + this.day;
    }

    /*
    <equals> - Checks to see whether or not the current date instance is equal to a given date.
        <param> Date other - The date to evaluate.
        <return> boolean - True if dates are equal, False if not.
     */
    public boolean equals(Date other){
        return other.day == this.day && other.month == this.month && other.year == this.year;
    }

    /*
    <isLeapYear> - Determines whether or not the current instance's year is a leap year.
     */
    public boolean isLeapYear(){
        return ((this.year % 400 == 0) || ((this.year % 4 == 0) && (this.year % 100 != 0)));
    }

    /*
    <isLeapYear> - Determines whether or not the given year is a leap year.
        <param> int year - The year to evaluate.
     */
    public boolean isLeapYear(int year) {
        return ((year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0)));
    }

    /*
    <nextDay> - Increments the current instance's day.
     */
    public void nextDay(){
        if (this.isLeapYear() && this.month == 2 && this.day == 29){ // Leap year case
            this.month++;
            this.day = 1;
        } else if (this.month == 12 && this.day == 31) { // If end of year
            this.year++;
            this.month = 1;
            this.day = 1;
        } else if (this.day == getDaysInMonth(this.month, this.year)){ // If end of month
            this.month++;
            this.day = 1;
        } else {
            day++;
        }
    }

    /*
    <advanceTo> - Sets the current date instance equal to the given date.
        <param> Date endDate - The date to advance to.
        <return> int - The number of days between the pre-change
            current instance, and the given instance.
     */
    public int advanceTo(Date endDate){
        int days = daysBetween(endDate);
        this.year = endDate.year;
        this.month = endDate.month;
        this.day = endDate.day;
        return days;
    }

    /*
    <getDayOfWeek> - Gets the day of the week of the current object instance.
        This method uses John Conway's Doomsday Algorithm
        I used this rather than using the Jan 1, 1753 base because this accounts
            for leap years automatically.
     */
    public String getDayOfWeek(){
        int[] months = new int[] {0, 3, 2, 5, 0, 3, 5, 1, 4, 6, 2, 4};
        String[] days = new String[] {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

        // Doomsday Algorithm
        int year = this.year; year -= this.month < 3 ? 1 : 0;
        int dayOfWeek = (year + year / 4 - year / 100 + year / 400 + months[this.month - 1] + this.day) % 7;
        return days[dayOfWeek];
    }

    // region Helpers
    /*
    <getDaysInMonth> - Gets the total number of days in a given month of a given year.
        <param> int month - The number of the month (1 - 12) to find the number of days in
        <param> int year  - The year that the month is in. Used to account for leap years.
     */
    private int getDaysInMonth(int month, int year) {
        if (month == 2) { // If month is February
            if (isLeapYear(year)) { return 29; }
            else { return 28; }
        }

        // Uses knuckle method
        // Days per month calc switches at August
        if (month < 8) { // If before August
            if      (month % 2 == 0) { return 30; }
            else if (month % 2 == 1) { return 31; }
        } else { // If August or after
            if      (month % 2 == 1) { return 30; }
            else if (month % 2 == 0) { return 31; }
        }
        return 0;
    }

    /*
    <daysBetween> - Finds the number of days between the current instance and a given Date object.
                    endDate should be LATER THAN the current object's date, will give incorrect answer otherwise.
        <param> Date endDate - The ending date.
     */
    private int daysBetween(Date endDate){
        int days = 0;

        // Add days left in current month of this date
        days += getDaysInMonth(this.month, this.year) - this.day;

        // Add days of year left for months of this date
        for (int month = this.month + 1; month < 13; month++) {
            days += getDaysInMonth(month, this.year);
        }

        // Add days for years between end of year of this date and beginning of year of endDate
        for (int year = this.year + 1; year < endDate.year; year++) {
            for (int month = 1; month <= 12; month++) {
                days += getDaysInMonth(month, year);
            }
        }

        // Add days of months up to current month of endDate
        for (int month = 1; month < endDate.month; month++) {
            days += getDaysInMonth(month, endDate.year);
        }

        // Add days from start of endDate month to endDate day
        days += endDate.day;

        return days;
    }
    //endregion

    //region Accessors
    public int getMonth() { return this.month; }
    public int getYear()  { return this.year; }
    public int getDay()   { return this.day; }
    //endregion

    //region inputValidation
    /*
    <validateDate> - Validates the given year, month, and day.
                     Ensures that the day number is between 1 and 31
                     Ensures that the month number is between 1 and 12
     */
    private boolean validateDate(int year, int month, int day){
        return validateDay(day) && validateMonth(month) && validateYear(year);
    }

    private boolean validateDay(int day) { return day > 0 && day <= 31; }
    private boolean validateMonth(int month){ return month > 0 && month <= 12; }
    private boolean validateYear(int year){ return year >= 1753 && year <= 9999; }
    //endregion
}
