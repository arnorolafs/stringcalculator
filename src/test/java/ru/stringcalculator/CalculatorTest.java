package ru.stringcalculator;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import java.lang.IllegalArgumentException;


public class CalculatorTest {
	
	public static void main(String args[]) {
		org.junit.runner.JUnitCore.main("is.ru.stringcalculator.CalculatorTest");
	}
	
	@Test
	public void testEmptyString() {
		assertEquals(0, Calculator.add(""));
	}
	
	@Test
	public void testOneNumber() {
		assertEquals(1, Calculator.add("1"));
	}
	
	@Test
	public void testTwoNumbers() {
		assertEquals(3, Calculator.add("1,2"));
	}
	
	@Test
	public void testMultipleNumbers() {
		assertEquals(6, Calculator.add("1,2\n3"));
	}
	
	@Test
	public void testHandleDifferentDelimiters() {
		assertEquals(3, Calculator.add("//;\n1;2"));
	}
	
	@Rule 
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void throwsExceptionForNegativeNumbers() {
		thrown.expect(IllegalArgumentException.class);
		Calculator.add("-1,-2,2");
	}
	
	@Test
	public void ignoreNumbersOverThousand() {
		assertEquals(2, Calculator.add("1001,2"));
	}
}