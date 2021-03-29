//Completed on 06/01/2021 by Renat Oosthuizen
//Class allows users to logout (and writes logout time to session.log), view login history and navigate to Stock Records and Member Records.

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Menu {
	
	private Scanner in = new Scanner (System.in); //Constructs a new instance of Scanner, in.
	private MemberRecord member = new MemberRecord(); //Constructs a new instance of EditMember, member.
	private StockRecord stock = new StockRecord(); //Constructs a new instance of EditMember, stock.
	
	//Function determines the appropriate menu to show to the user based on their membership type and admin status.
	public void mainMenu() 
	{
		if (Login.getMembers().get(Login.getUserIndex()).getAdmin() == true)
		{
			adminMenu(); //Display admin menu.
		}
		else
		{
			normalMenu(); //Display normal menu.
		}
	}
	
	//Method writes logout time to session.log and returns the user to login menu.
	private void logout() 
	{
		try 
		{
			   
			String fileContent = ""; //Variable that stores session.log contents
		
			FileReader file = new FileReader("session.log"); //Reader for session.log
			BufferedReader reader = new BufferedReader(file); //Buffered reader for session.log
		
			String line = reader.readLine(); //Variable stores a single line of session.log
		
			while (line != null && ! line.equals("Logout Time: User is still logged in")) //If line exists and it is the last line...
			{ 
				fileContent += (line + "\n"); //...add line to fileContent and...
				line = reader.readLine(); //read the next line.
			}
			   
			file.close();
			   
			FileWriter input  = new FileWriter("session.log"); //Write back initial data to file and append new data.
			
			DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("HH:mm:ss");  //Format used for time.
			String formattedTime = LocalTime.now().format(myFormatObj);  //Variable stores formatted time.
					
			input.write(fileContent + "Logout Time: " + formattedTime); //The last line is overwritten to the logout time.
					
			input.close();	
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		Login.loginMenu(); //Calls the Login menu.
	}
	
	//Method will show the login history for all users.
	private void loginHistory()
	{
		try 
		{
		
			FileReader file = new FileReader("session.log"); //Reader for session.log
			BufferedReader reader = new BufferedReader(file); //Buffered reader for session.log
		
			String line = reader.readLine(); //Variable stores a single line of session.log
		
			while (line != null) //While there are more lines...
			{ 	
					
				System.out.println(line); //Print out the line.
				line = reader.readLine(); //Read the next line.
			}
			
			file.close();		
		} 
		catch (IOException e) 
		{
				
			e.printStackTrace();
		}
	}
	
	//Method will show the login history for the logged user.
	private void myLoginHistory() 
	{
		try 
		{  
			String fileContent = ""; //Variable that stores session.log contents
		
			FileReader file = new FileReader("session.log"); //Reader for session.log
			BufferedReader reader = new BufferedReader(file); //Buffered reader for session.log
		
			String line = reader.readLine(); //Variable stores a single line of session.log
		
			while (line != null) //While there are more lines...
			{ 	
				if (line.equals("UserID: " + Login.getMembers().get(Login.getUserIndex()).getUserID())) //For the logged user...
				{
					fileContent += (line + "\n"); //...add 1st line to fileContent and...
					line = reader.readLine(); //read the next line.
					fileContent += (line + "\n"); //...add 2nd line to fileContent and...
					line = reader.readLine(); //read the next line.
					fileContent += (line + "\n"); //...add 3rd line to fileContent and...
					line = reader.readLine(); //read the next line.
					fileContent += (line + "\n"); //...add 4th line to fileContent and...
					line = reader.readLine(); //read the next line.
					fileContent += (line + "\n" + "\n"); //...add 5th line to fileContent and 2 line breaks so that there is space between login records.
				}
				
				line = reader.readLine(); //read the next line.
			}
			
			file.close();		
			System.out.println(fileContent);	
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	//Method that shows the menu for non-admin users that only lets the user see their own login history.
	private void normalMenu()
	{
		
		String menuItem; //Using String for the switch so that switch doesn't crash if non numbers are entered.
		boolean quit = false; //Variable tracks when the menu should be closed.
		
		do {
		
			//Prints the menu.
			System.out.println();
			System.out.println("Main Menu");
	    	System.out.println("===========");    
	        System.out.println("Enter '1' to navigate to member records"); 
	        System.out.println("Enter '2' to navigate to stock records");
	        System.out.println("Enter '3' to view your login history");
	        System.out.println("Enter '0' to logout");
	        System.out.print("Please choose a menu item: \n \n");
	        
	        menuItem = in.next();
	        
	        switch (menuItem) {
	        case "1":
	        	System.out.println();
	        	member.mrMenu(); //Display MemberRecord menu.
	        	break;
	        case "2":
	        	System.out.println();
	        	stock.srMenu(); //Display StockRecord menu.
	        	break;
	        case "3":
	        	System.out.println();
	        	myLoginHistory(); //Display user's login history.
	        	break;
	        case "0": 
	            quit = true; 
	            System.out.println();
	            System.out.println("Loging out..");
	            logout(); //Save logout time and return user to login menu
	            break;
	        default: 
	        	System.out.println();
	        	System.out.println("Invalid choice. 0 to 3 only please."); 
	        	System.out.println();
	        }
		} while (!quit);
		 
	in.close();
	
	}
	
	//Method that shows the menu for admin users, all options are available.
	private void adminMenu()
	{
		
		String menuItem; //Using String for the switch so that switch doesn't crash if non numbers are entered.
		boolean quit = false; //Variable tracks when the menu should be closed.
		
		do {
		
			//Prints the menu.
			System.out.println();
			System.out.println("Main Menu");
	    	System.out.println("===========");    
	        System.out.println("Enter '1' to navigate to member records"); 
	        System.out.println("Enter '2' to navigate to stock records");
	        System.out.println("Enter '3' to view your login history");
	        System.out.println("Enter '4' to view all login history");
	        System.out.println("Enter '0' to logout");
	        System.out.print("Please choose a menu item: \n \n");
	        
	        menuItem = in.next();
	        
	        switch (menuItem) {
	        case "1":
	        	System.out.println();
	        	member.mrMenu(); //Display MemberRecord menu.
	        	break;
	        case "2":
	        	System.out.println();
	        	stock.srMenu(); //Display StockRecord menu.
	        	break;
	        case "3":
	        	System.out.println();
	        	myLoginHistory(); //Display user's login history.
	        	break;
	        case "4":
	        	System.out.println();
	        	loginHistory(); //Display login history for all users.
	        	break;
	        case "0": 
	            quit = true; 
	            System.out.println();
	            System.out.println("Loging out..");
	            logout(); //Save logout time and return user to login menu
	            break;
	        default: 
	        	System.out.println();
	        	System.out.println("Invalid choice. 0 to 4 only please."); 
	        	System.out.println();
	        }
		} while (!quit);
		 
	in.close();
		
	}
	
	public MemberRecord getMemberRecord() //Getter for the instance of MemberRecord, member.
	{
		return member;
	}
	
	public StockRecord getStockRecord() //Getter for the instance of StockRecord, stock.
	{
		return stock;
	}
}