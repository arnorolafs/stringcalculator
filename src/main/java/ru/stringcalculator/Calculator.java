package ru.stringcalculator;

public class Calculator {

	public static int add(String text) {
		String[] numbers = text.split(",");
		
		if (text.equals(""))
			return 0;
		else if (text.contains(","))  {
			return toInt(numbers[0]) + toInt(numbers[1]);
		}
		else
			return 1;
	}
	
	private static int toInt(String number) {
		return Integer.parseInt(number);
	}
}