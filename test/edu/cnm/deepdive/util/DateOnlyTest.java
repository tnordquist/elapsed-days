package edu.cnm.deepdive.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

class DateOnlyTest {

  @ParameterizedTest
  @CsvFileSource(resources = "norm-tests.csv", numLinesToSkip = 1)
  void elapsedDaysNormalized(int year, int month, int day, int expected) {
    assertEquals(expected, DateOnly.elapsedDays(year, month, day));
  }

  @ParameterizedTest
  @CsvFileSource(resources = "non-norm-tests.csv", numLinesToSkip = 1)
  void elapsedDaysNonNormalized(int year, int month, int day, int expected) {
    assertEquals(expected, DateOnly.elapsedDays(year, month, day));
  }

}