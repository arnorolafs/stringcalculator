package ru.stringcalculator;

public class Calculator {

	private static final String DELIMITER = ",|\n";
	
	public static int add(String text) {
		
		if (text.equals(""))
			return 0;
		else  {
			return getSum(text.split(DELIMITER));
		}
	}
	
	private static int getSum(String[] numbers) {
			int sum = 0;
			
			for (String number: numbers)
				sum += toInt(number);
			
			return sum;
	}
	
	private static int toInt(String number) {
		return Integer.parseInt(number);
	}
}