package com.example.dartscount;

public class ExpectedResultAndDisplayedNumber {

    public String getDisplayedNumber() {
        return displayedNumber;
    }

    private String displayedNumber;

    public String getExpectedResult() {
        return expectedResult;
    }

    private String expectedResult;


    public ExpectedResultAndDisplayedNumber(String displayedNumber, String expectedResult) {
        this.displayedNumber = displayedNumber;
        this.expectedResult = expectedResult;
    }


}
