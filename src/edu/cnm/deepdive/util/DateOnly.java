package edu.cnm.deepdive.util;

public class DateOnly {


  static int normYear = 0;
  static int normMonthYear = 0;
  static int normDay = 0;
  static int normMonth = 0;

  public static int elapsedDays(int normYear, int month, int day) {

    final int STANDARD_DAYS_YEAR = 365;
    int GROUND_ZERO = 1970;

    normMonthToYears(normYear, month, day);
    int days = normDay - 1;
    int tempYear = normYear;
    int leapDays = 0;

    if (normYear < GROUND_ZERO) {
      GROUND_ZERO = normYear;
      normYear = 1970;
    }
    for (int yr = GROUND_ZERO; yr < normYear; ++yr) {
      if (isLeapYear(yr)) {
        leapDays++;
      }
      if (normMonth < 1) {
        leapDays--;
      }
    }

    GROUND_ZERO = 1970;
    normYear = tempYear;

    int yearDays = (normYear - GROUND_ZERO) * STANDARD_DAYS_YEAR;
    if (normYear >= GROUND_ZERO) {
      days += yearDays + leapDays + monthDays(normMonth);
      return days;
    } else {
      days = yearDays - leapDays + days + monthDays(normMonth);
      return days;
    }
  }

  private static int monthDays(int month) {
    int[] monthDays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    int days = 0;

    if (month == 0) {
      days = 0;
      return days;
    } else {
      for (int i = 0; i < month; ++i) {
        days += monthDays[i];
      }
    }
    return days;
  }

  private static boolean isLeapYear(int year) {
    return ((year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0)));
  }

  private static void normMonthToYears(int year, int month, int day) {

    if ((month > 11 || month < -11)) {
      if (month > 11) {
        normMonthYear = month / 11;
      } else if (month < 0) {
        normMonthYear = month / -11;
      }
      normYear = normMonthYear + year;
      normMonth = month % 11;

    }

    if (day > 364) {
      normMonth = day / 364 + normMonth;
      normDay = day % 364;
    }

    year = normYear;
    month = normMonth;
    day = normDay;
  }


}
