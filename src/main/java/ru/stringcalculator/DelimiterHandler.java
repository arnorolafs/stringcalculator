package ru.stringcalculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;	
	
public class DelimiterHandler {

	private static final String PREDEFINED_DELIMITER = ",|\n";
	private static final String DELIMITER_REGEX_PATTERN = "^//((.)|(\\[(.+?)\\])+)\n(.*)";
	private static final int REGEX_NUMBER_OF_SINGLE_CHAR_DELIMITER = 2;
	private static final int REGEX_NUMBER_OF_MULTIPLE_CHAR_DELIMITERS = 4;
	private static final int REGEX_NUMBER_OF_NUMBER_STRING = 5;
	
	
	public static String getNumberString(String numbers) {
		Matcher m = Pattern.compile(DELIMITER_REGEX_PATTERN).matcher(numbers);
		if (m.find()) {
			return m.group(REGEX_NUMBER_OF_NUMBER_STRING);
		} else {
			return numbers;
		}
	}
	
	public static String getDelimiter(String text) {
		Matcher m = Pattern.compile(DELIMITER_REGEX_PATTERN).matcher(text);
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
	
}
	