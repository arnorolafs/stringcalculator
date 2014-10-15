package ru.stringcalculator;

public class Calculator {

	private static String DELIMITER = ",|\n";
	
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
			String[] negatives;
			
			for (String number: numbers) 
				sum += toInt(number);
			
			return sum;
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