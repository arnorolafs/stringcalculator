package ru.stringcalculator;

public class Calculator {

	private static String DELIMITER = ",|\n";
	private static final int MAX_NUMBER = 1000;
	
	public static int add(String text) {
		setDelimiter(text);
		return getSum(getNumbers(text));
	}
	
	private static void setDelimiter(String text) {
		if (text.contains("//")) {
			int starting_index = text.indexOf("/") + 2;
			int ending_index = text.indexOf("\n");
			String added_delimiter = "|/|" + text.substring(starting_index, ending_index);
			DELIMITER += added_delimiter;
		}
	}
	
	private static String[] getNumbers(String numbers) {
		if (numbers.isEmpty())
			return new String[0];
		else
			return numbers.split(DELIMITER);
	}
	
	private static int getSum(String[] numbers) {
			int sum = 0;
			
			for (String number: numbers) {
				int num = toInt(number);
				sum += ignoreToBig(num);
			}
			
			return sum;
	}
	
	public static int ignoreToBig(int number) {
		if (number > MAX_NUMBER)
			return 0;
		else
			return number;
	}
	
	private static int toInt(String number) {
		try {
			int num = Integer.parseInt(number);
			if (num < 0) throw new IllegalArgumentException("Negatives not allowed: " + num);
			return num;
		} catch (NumberFormatException e) {
			return 0;
		}
	}
}