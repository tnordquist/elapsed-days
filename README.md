# Date-only Calculations

## Background

For many computational tasks, we want to manipulate dates&mdash;and only dates. For example, in an Android app that displays the NASA Astronomy Picture of the Day (APOD), if we want to store retrieved APOD objects and images in local storage (database and/or file storage), the only temporal information that's relevant about each image is the date (not the time) for which the image was the APOD. (You might also want to record a timestamp for when the image was retrieved, but that's not our concern at the moment.) Then, if you later want to search the local database for the APOD for a given day, using a time component in the search could result in database "misses"&mdash;i.e. queries that should return a non-empty result, but don't.

For some applications, this leads us to the need to have a numeric representation of dates that is not the number of milliseconds elapsed since 1970-01-01 00:00:00 UTC, but the number of calendar days since some agreed-upon date (for the sake of consistency, we might as well use 1970-01-01 in this case as well). That is the basic task here.

## Basic problem: 10 points (out of the 50 points possible for classroom & homework in weeks 4-6)

Complete the implementation of the `edu.cnm.deepdive.util.DateOnly.elapsedDays` method, declared as:

```java
public static int elapsedDays(int year, int month, int day)
```

For any given date value in the range from (roughly) 5,880,000 BCE to 5,880,000 CE, your implementation should return the numbers of days before (negative) or after (positive) January 1, 1970.

### Details

* The `year` value will be a positive or negative `int` in the range given above. Obviously, our current (Gregorian) calendar has only been in use for the last 500 years (or so); much less than that in many countries (including the US). Before that, the Julian calendar was used in most of the European-dominated parts of the world; it had the same months as the Gregorian calendar, but had included too many leap days over the years. Rather than deal with that changeover&mdash;or the lack of a year 0 in either the Julian or Gregorian calendars&mdash;we'll be following the [ISO 8601 standard](https://en.wikipedia.org/wiki/ISO_8601#Years), with the added explicit requirement to support not only years prior to 1583, but years prior to 1 CE, as well, with the year traditionally referred to as 1 BCE (or 1 BC) denoted as year 0.

* The `month` value will be in the range 0&hellip;11. This is in keeping with the convention followed in most C-derived languages and libraries, where months are numbered starting from 0.

* The `day` value will be in the range 1&hellip;(last day of `month` in specified `year`).

### Unit tests

The accompanying `edu.cnm.deepdive.util.DateOnlyTest` class includes the `elapsedDaysNormalized` test method, which is the only test method you need to worry about for this part of the problem. This test is already written, and uses the accompanying `norm-tests.csv` file for its input and expected values.

### Hints

* As discussed in class, this problem can be broken down into 2 parts:

    * Computing the number of days between January 1, 1970 and January 1 in the year specified by the `year` parameter. For a year prior to 1970, this should be a negative number.

    * Computing the number of days between January 1 in the year specified by `year`, and the date specified by `month` and `day` in that same year.
    
    Implemented correctly, the sum of these 2 computations will give the correct overall answer.
    
* In the IS0 8601 calendar, any year which is evenly divisible by 4, and is either _not_ divisible by 100 or divisible by _400_, is a leap year. So, 1976 is a leap year (1976 is evenly divisible by 4, and not by 100), as is 2000 (evenly divisible by 4, and by 400); 2100, however, is not a leap year (2100 is evenly disivible by 4, and also by 100, but not by 400).

* The number of days between January 1, 1970 and January 1 of some other year can be computing by multiplying the difference by 365, and then adding to that product the number of leap days between those 2 dates. 

## Extra credit: 5 points

In the basic problem, the `year`, `month`, and `day` values will all be well-behaved&mdash;that is, each will be in the expected range. However, date computations often require normalization of non-normalized inputs. For example, we would treat month 12 of some year as actually being month 0 of the following year; similarly, January 32 would be considered February 1. We might even have negative values for `month` and `day`: July -2 should be treated as June 28, for example.

For extra credit, normalize the input data before making the elapsed day computations, so that even if `month` or `day` is outside the expected range, the computations work as expected.

### Unit tests

In the `edu.cnm.deepdive.util.DateOnlyTest` class, the `elapsedDaysNonNormalized` test method should be used to test input from the accompanying `non-norm-tests.csv` file; these inputs are all outside the expected ranges, in one way or another.
