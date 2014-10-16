package ru.stringcalculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {

	private static final String PREDEFINED_DELIMITER = ",|\n";
	private static final String DELIMITER_REGEX_PATTERN = "^//((.)|(\\[(.+?)\\])+)\n(.*)";
	private static final int REGEX_NUMBER_OF_SINGLE_CHAR_DELIMITER = 2;
	private static final int REGEX_NUMBER_OF_MULTIPLE_CHAR_DELIMITERS = 4;
	private static final int REGEX_NUMBER_OF_NUMBER_STRING = 5;
	
	private static final int MAX_NUMBER = 1000;
	
	public static int add(String text) {
		DelimiterHandler  delimiterHandler = new DelimiterHandler();
		String delimiters = delimiterHandler.getDelimiter(text);
		String numberString = delimiterHandler.getNumberString(text);


		return getSum(getNumbers(numberString, delimiters));
	}
	
	private static String[] getNumbers(String numbers, String delimiters) {
		if (numbers.isEmpty())
			return new String[0];
		else
			return numbers.split(delimiters);
	}
	
	private static int getSum(String[] numbers) {
			int sum = 0;
			
			for (String number: numbers) {
				int num = toInt(number);
				sum += ignoreToBig(num);
			}
			
			return sum;
	}
	
	private static int toInt(String number) {
		try {
			throwIllegalArgumentException(number);
			return Integer.parseInt(number);
		} catch (NumberFormatException e) {
			return 0;
		}
	}
	
	public static int ignoreToBig(int number) {
		if (number > MAX_NUMBER)
			return 0;
		else
			return number;
	}
	
	public static void  throwIllegalArgumentException(String number) {
		int num = Integer.parseInt(number);
		if (num < 0) throw new IllegalArgumentException("Negatives not allowed: " + num);
	}
}