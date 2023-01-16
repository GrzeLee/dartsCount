package com.example.dartscount;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CountMethods {


    public static ExpectedResultAndDisplayedNumber getRandomNumberWithOperatorAndResult(){
        List<String> operationList = Arrays.asList("T", "D");
        Random r = new Random();
        int randomItem = r.nextInt(operationList.size());
        String randomOperation = operationList.get(randomItem);
        String number = String.valueOf((int) (Math.random()*(20-2+1)+2));

        return new ExpectedResultAndDisplayedNumber(randomOperation+number,getCorrectAnswer(randomOperation,number));
    }



    public static String getCorrectAnswer(CharSequence displayedOperator, CharSequence displayedNumber) {
        int correctAnswer;
        if (displayedOperator.equals("D")) {
            correctAnswer = Integer.parseInt(String.valueOf(displayedNumber)) * 2;
        }else {
            correctAnswer = Integer.parseInt(String.valueOf(displayedNumber)) * 3;
        }
        return String.valueOf(correctAnswer);
    }


    public static String getFinalScore(String totalOfThrows){
        Random random = new Random();
        int min = Integer.parseInt(totalOfThrows)+1;
        int max = 501;
        int randomScore= random.nextInt(max - min + 1) + min;
        return String.valueOf(randomScore);
    }
}
