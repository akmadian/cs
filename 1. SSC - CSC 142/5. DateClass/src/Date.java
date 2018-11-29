public class Date {

    private int year;
    private int month;
    private int day;

    public Date(int year_, int month_, int day_){
        if (!validateDate(year_, month_, day_)){ throw new IllegalArgumentException("Invalid Month, Day, or Year.");}
        year = year_;
        month = month_;
        day = day_;
    }

    //region constructor
    public String toString(){
        return String.format("");
    }
    //endregion

    public boolean equals(Date other){
        return other.day == this.day && other.month == this.month && other.year == this.year;
    }

    public boolean isLeapYear(){
        return (this.year % 4 == 0) && (this.year % 100 != 0) || (this.year % 400 == 0);
    }

    /* oh boy */
    public void nextDay(){
        if (this.isLeapYear() && this.month == 2 && this.day == 28){
            this.month++;
            this.day = 1;
        }
    }

    public int advanceTo(Date endDate){
        return 1;
    }

    public String getDayOfWeek(){
        return "";
    }

    //region getMethods
    public int getMonth() { return this.month; }
    public int getYear()  { return this.year; }
    public int getDay()   { return this.day; }
    //endregion

    //region inputValidation
    private boolean validateDate(int year, int month, int day){
        return validateDay(day) && validateMonth(month) && validateYear(year);
    }

    private boolean validateDay(int day){
        return day > 0 && day <= 31;
    }

    private boolean validateMonth(int month){
        return month > 0 && month <= 12;
    }

    private boolean validateYear(int year){
        return year >= 1753 && year <= 9999;
    }

    //endregion
}
