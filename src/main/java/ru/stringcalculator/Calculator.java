package ru.stringcalculator;

public class Calculator {

	private static final String DELIMITER = ",";
	
	public static int add(String text) {
		
		if (text.equals(""))
			return 0;
		else if (text.contains(DELIMITER))  {
			String[] numbers = text.split(DELIMITER);
			int sum = 0;
			
			for (String number: numbers)
				sum += toInt(number);
			
			return sum;
		}
		else
			return 1;
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