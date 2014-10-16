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
		Matcher m = Pattern.compile(DELIMITER_REGEX_PATTERN).matcher(text);
		String delimiters = getDelimiter(m);
		String numberString = getNumberString(text, m.reset());


		return getSum(getNumbers(numberString, delimiters));
	}
	
	private static String getNumberString(String numbers, Matcher m) {
		if (m.find()) {
			return m.group(REGEX_NUMBER_OF_NUMBER_STRING);
		} else {
			return numbers;
		}
	}
	
	private static String getDelimiter(Matcher m) {
		if (m.find()) {
			return getUserDefinedDelimiter(m);
		} else {
			return PREDEFINED_DELIMITER;
		}
	}
	
	private static String getUserDefinedDelimiter(Matcher m) {
		if (m.group(REGEX_NUMBER_OF_SINGLE_CHAR_DELIMITER) != null) {
			return getUserDefinedSingleCharDelimiter(m);
		} else {
			return getUserDefinedMultipleCharDelimiter(m);
		}
	}
	
	private static String getUserDefinedSingleCharDelimiter(Matcher m) {
		return Pattern.quote(m.group(REGEX_NUMBER_OF_SINGLE_CHAR_DELIMITER));
	}
	
	private static String getUserDefinedMultipleCharDelimiter(Matcher m) {
		String delimiters = m.group(1);
		Matcher delimiterMatcher = Pattern.compile("\\[(.+?)\\]").matcher(delimiters);
		StringBuffer allDelimiters = new StringBuffer();
		
		while (delimiterMatcher.find()) {
			addOneUserDefinedDelimiterToAllDelimiters(delimiterMatcher, allDelimiters);
		}
		
		return allDelimiters.toString();
	}
	
	private static void addOneUserDefinedDelimiterToAllDelimiters(Matcher delimiterMatcher, StringBuffer allDelimiters) {
		if (allDelimiters.length() == 0) {
				allDelimiters.append(Pattern.quote(delimiterMatcher.group(1)));
			} else {
				allDelimiters.append("|" + Pattern.quote(delimiterMatcher.group(1)));
			}
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