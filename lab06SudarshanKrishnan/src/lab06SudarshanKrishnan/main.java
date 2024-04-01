package lab06SudarshanKrishnan;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;


public class main {
	public static void main(String[] args) {
		try {
		System.out.println("Welcome to our survey. You may "
		+ "enter \"quit\" at any time to cancel the survey.");
		int age = getAge();
		String[][] states = readStateFile("states.bin");
		String state = getState(states);
		int ZIPCode = getZIPCode();
		System.out.printf("\nAge:\t\t%d\n", age);
		System.out.printf("Address:\t%s %s\n\n", ZIPCode, state);
		System.out.println("Your survey is complete!");
		}
		catch (CancelledSurveyException e) {
		System.out.println(e.getMessage());
		}
		finally {
		System.out.println("Thank you for your time.");
		}
		}

	private static int getZIPCode() throws CancelledSurveyException{
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		while(true) {
			try {
				System.out.println("Please enter your zip code: ");
				String input = getResponseOrQuit(scanner);
				if(input.equalsIgnoreCase("quit")) {
					throw new CancelledSurveyException();
				}
				int zipCode = Integer.parseInt(input);
	            if (zipCode < 0 || input.length() != 5) { // Check for negative value or length not equal to 5
	                System.out.println("Invalid ZIP code");
	            } else {
	                return zipCode;	
//		return Integer.parseInt(input);
	            }
	        
	            }
			catch (NumberFormatException e) {
				System.out.println("Invalid ZIP code");
			}
		}
		
		
	
	}


	private static String getState(String[][] states) throws CancelledSurveyException{
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		while(true) {
			try {
				System.out.println("Please enter the 2-letter state abbreviation: ");
				String input = getResponseOrQuit(scanner);
				if(input.equalsIgnoreCase("quit")) {
					throw new CancelledSurveyException("Your survey was cancelled.");
				}
				for (String[] state: states) {
					if( state[0].equalsIgnoreCase(input)) {
						return state[1];
					}
				}
				System.out.println("The state abbreviation was not found.");
				
			}catch (Exception e) {
				System.out.println("An error occured: " + e.getMessage());
			}
		}
		
		
		
		
		
	}

	private static int getAge() throws CancelledSurveyException{
		
		
		Scanner scanner = new Scanner(System.in);
		while(true) {
			System.out.println("Please enter your age: ");
			String input = scanner.nextLine().trim();
			if(input.equalsIgnoreCase("quit")) {
				throw new CancelledSurveyException();
			}
			
			try {
				int age = Integer.parseInt(input);
				if (age < 0) {
					System.out.println("You've entered an invalid age.");
					throw new CancelledSurveyException();
				}
				else if (age < 18) {
					System.out.println("You are too young to complete the survey.");
					throw new CancelledSurveyException();
				}
				else {
					return age;
				}
				}
				catch (NumberFormatException ex) {
					System.out.println("You've entered an invalid age.");
				}
			
		}
		

	}

	private static String[][] readStateFile(String fileName){
		// TODO Auto-generated method stub
		
		try {
			FileInputStream fileInputStream = new FileInputStream(fileName);
			DataInputStream dataInputStream = new DataInputStream(fileInputStream);
			
			String[][] states = new String[50][2];
		
			for (int i = 0; i < 50; i++) {
			    String abbreviation = dataInputStream.readUTF(); 
			    String name = dataInputStream.readUTF(); 
			    
			    states[i][0] = abbreviation;
			    states[i][1] = name;
			}

			 dataInputStream.close();
			 fileInputStream.close();
			 
			 return states;
		} catch(IOException e) {
			e.printStackTrace();
			return null;
		}
		
		
	}
	

	private static String getResponseOrQuit(Scanner scanner) {
		// TODO Auto-generated method stub
		String string = scanner.nextLine().trim();
		if(string.equalsIgnoreCase("quit")) {
			System.out.println("Your survey was cancelled. \nThank you for your time.");
			System.exit(0);
		}
		return string;
	}
	
}

class CancelledSurveyException extends Exception {
	
	public CancelledSurveyException() {
		super("Your survey has been cancelled");
	}

	public CancelledSurveyException(String message) {
		// TODO Auto-generated constructor stub
		super(message);
	}
}

