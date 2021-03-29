import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.time.temporal.ChronoUnit;

public class MemberRecord {

	private Scanner in = new Scanner (System.in); //Global scanner used for all input.

	//Function determines the appropriate menu to show to the user based on their membership type and admin status.
	public void mrMenu() 
	{	
		if (Login.getMembers().get(Login.getUserIndex()) instanceof CasualMember)
		{
			casualMRMenu(); //Display menu for casual members.
		}
		else if (Login.getMembers().get(Login.getUserIndex()).getAdmin() == true)
		{
			adminMRMenu(); //Display menu for admin members.
		}
		else 
		{
			normalMRMenu(); //Display menu for non-casual members that are not admins.
		}
	}
	
	//Menu for a Casual Member with very limited options.
	private void casualMRMenu()
	{
		String menuItem; //Using String for the switch so that switch doesn't crash if non numbers are entered.
		boolean quit = false; //Variable used to track when the menu should be exited.
		
		do {
		
			//Prints the menu.
			System.out.println();
			System.out.println("Member Records");
	    	System.out.println("===========");    
	        System.out.println("Enter '1' to edit your profile."); 
	        System.out.println("Enter '0' to return to main menu.");
	        System.out.print("Please choose a menu item: \n \n");
	        
	        menuItem = in.next();
	        
	        switch (menuItem) {
	        case "1":
	        	System.out.println();
	        	editSelf(); //Lets user edit their own membership profile.
	        	break;
	        case "0": 
	            quit = true; 
	            System.out.println();
	            Login.getMenu().mainMenu(); //Display the main menu.
	            break;
	        default: 
	        	System.out.println();
	        	System.out.println("Invalid choice. 0 to 1 only please."); 
	        	System.out.println();
	        }
		} while (!quit);
		 
	in.close();
	}
	
	//Menu for Non-Casual members that are not admins. Some options are not available.
	private void normalMRMenu()
	{
		String menuItem; //Using String for the switch so that switch doesn't crash if non numbers are entered.
		boolean quit = false; //Variable used to track when the menu should be exited.
		
		do {
		
			//Prints the menu.
			System.out.println();
			System.out.println("Member Records");
	    	System.out.println("===========");    
	        System.out.println("Enter '1' to edit your profile."); 
	        System.out.println("Enter '2' to view your loan history.");
	        System.out.println("Enter '0' to return to main menu.");
	        System.out.print("Please choose a menu item: \n \n");
	        
	        menuItem = in.next();
	        
	        switch (menuItem) {
	        case "1":
	        	System.out.println();
	        	editSelf(); //Lets user edit their own membership profile.
	        	break;
	        case "2":
	        	System.out.println();
	        	viewMyLoans(); //Lets user view their loan history.
	        	break;
	        case "0": 
	            quit = true; 
	            System.out.println();
	            Login.getMenu().mainMenu(); //Display the main menu.
	            break;
	        default: 
	        	System.out.println();
	        	System.out.println("Invalid choice. 0 to 2 only please."); 
	        	System.out.println();
	        }
		} while (!quit);
		 
	in.close();
	}
	
	//Menu for admins with all options available.
	private void adminMRMenu()
	{
		String menuItem; //Using String for the switch so that switch doesn't crash if non numbers are entered.
		boolean quit = false; //Variable used to track when the menu should be exited.
		
		do {
		
			//Prints the menu.
			System.out.println();
			System.out.println("Member Records");
	    	System.out.println("===========");    
	        System.out.println("Enter '1' to view all profiles."); 
	        System.out.println("Enter '2' to create a new profile.");
	        System.out.println("Enter '3' to edit your profile.");
	        System.out.println("Enter '4' to edit any profile."); 
	        System.out.println("Enter '5' to record a loan.");
	        System.out.println("Enter '6' to return a loan.");
	        System.out.println("Enter '7' to view all loan history."); 
	        System.out.println("Enter '8' to view your loan history."); 
	        System.out.println("Enter '0' to return to main menu.");
	        System.out.print("Please choose a menu item: \n \n");
	        
	        menuItem = in.next();
	        
	        switch (menuItem) {
	        case "1":
	        	System.out.println();
	        	viewMembers(); //Lets admin view all member profiles.
	        	break;
	        case "2":
	        	System.out.println();
	        	createMember(); //Lets admin create a new member profile.
	        	break;
	        case "3":
	        	System.out.println();
	        	editSelf(); //Lets admin edit their own membership profile.
	        	break;
	        case "4":
	        	System.out.println();
	        	editMember(); //Lets admin edit a member profile.
	        	break;
	        case "5":
	        	System.out.println();
	        	recordLoan(); //Lets admin record someone's loan.
	        	break;
	        case "6":
	        	System.out.println();
	        	returnLoan(); //Lets admin return someone's loan.
	        	break;
	        case "7":
	        	System.out.println();
	        	viewAllLoans(); //Lets admin view loan history for all users.
	        	break;
	        case "8":
	        	System.out.println();
	        	viewMyLoans(); //Lets admin view their own loan history.
	        	break;
	        case "0": 
	            quit = true; 
	            System.out.println();
	            Login.getMenu().mainMenu(); //Display the main menu.
	            break;
	        default: 
	        	System.out.println();
	        	System.out.println("Invalid choice. 0 to 8 only please."); 
	        	System.out.println();
	        }
		} while (!quit);
		 
	in.close();
	
	}
	
	//Method allows a logged user to edit their own profile.
	private void editSelf() 
	{
		String userID = Integer.toString(Login.getMembers().get(Login.getUserIndex()).getUserID()); //User ID of logged user.
		String headerCols[]; //Stores column headers in an array for display purposes.
		int columnNumber = 12; //Target column for editing. Starting at 12 so that initial data is rejected.
		String columnData = ""; //Data to be entered into the target column.
		String completeFile = ""; //Stores the complete edited file contents before it is written back to media.csv
		boolean validFormat = false; //Tracks if input into column is of a valid format or not.
		
			try
			{
				FileReader file = new FileReader("member.csv");
				BufferedReader br = new BufferedReader(file);
				
				String line = br.readLine(); //Reads the first line of the file.
				completeFile = line + "\n";
				headerCols = line.split(","); //Store the column headers for display purposes.
				line = br.readLine(); //Reads the second line of the file (first line is headers).
				
				while (line != null) 
				{
				   
				    String[] cols = line.split(",");  // Use comma as a separator.
				    
					if (userID.equals(cols[4].trim())) //Trim white spaces. If target User ID matches member's User ID. 
					{
						
						System.out.println("Begining editing process!");
	
						System.out.println();
						
						for (int i = 0; i < 12; i++) //For every column in line print header and data.
						{
								System.out.println((i+1) + ") " + headerCols[i].trim() + ": " + cols[i].trim());
						}
						System.out.println();
						
						//Getting a valid column number to edit.
						while ( columnNumber < 0 || columnNumber == 4 || columnNumber > 11) 
						{
							System.out.println("Please enter the number of the row you wish to edit (e.g. 2). Note: User ID cannot be Edited.");
							try {
								columnNumber = in.nextInt() - 1;
								}
							catch (Exception e) 
							{
							      System.out.println("You may only enter numbers.");
							      in.nextLine(); //Clearing scanner buffer.
							}
						}
						
						in.nextLine(); //Must do twice, otherwise end up with a blank.
						
						//Checking that the new column data is of a valid format.
						while (validFormat == false)
						{
							System.out.println("Please enter the new data for the column.");
							columnData = (in.nextLine()); 
							
							if (columnNumber == 9) //DOB must be of a valid LocalDate format.
							{
								try {
									LocalDate.parse(columnData);
									validFormat = true;
									}
								catch (Exception e) 
								{
									System.out.println("Incorrect format. Correct format is 'YYYY-MM-DD'.");
								}
							}
							else if (columnNumber == 10 && !columnData.equals("true") && !columnData.equals("false")) //Admin must be in boolean format.
							{
								System.out.println("Enter 'true' or 'false.'");
							}
							else if (columnNumber == 11 && !columnData.matches("^[0-9]+.[0-9]+$")) //Fine must be in double format.
							{
								System.out.println("Incorrect format. Correct format is '0.00'.");
							}
							else
							{
								validFormat = true;
							}
						}
						
						cols[columnNumber] = columnData; //Rewriting target column with new data.
						line = "";
						
						for (int i = 0; i < 12; i++) //Reassembling the line with new data into the correct CSV order.
						{
							line += (cols[i] + ",");
						}
						
					}
					
					completeFile += (line + "\n"); //Reassembling file content, line by line (includes modified line).
				    line = br.readLine(); //read the next line.
				}
				
				file.close();
				
					try 	
					{
						//Starting here, write back initial data to file and append new data.
						FileWriter input  = new FileWriter("member.csv"); 
								
						input.write(completeFile);
								
						input.close();
						
						System.out.println("Profile has been edited.");
					}
						
					catch (IOException e) 
					{
						e.printStackTrace();
					}
			
			}
			catch (IOException e) 
			{
				e.printStackTrace();
				
			}
	}
	
	//Method for editing any member profile.
	private void editMember() 
	{
		String userID; //User ID of the profile that will be edited.
		String headerCols[]; //Stores column headers in an array.
		int columnNumber = 12; //Starting at 12 so that initial data is rejected.
		String columnData = ""; //Data to be entered into the target column.
		String completeFile = ""; //Stores the complete edited file contents before it is written back to media.csv.
		boolean idExists = false; //Tracks if the target User ID is found.
		boolean validFormat = false; //Tracks if input into column is of a valid format or not.
		
		viewMembers(); //Display all member accounts.
		System.out.println();
		
		//Acquiring target User ID. 
		System.out.println("Please enter the User ID of the member you wish to edit, or enter 0 to return to menu:");
		userID = in.next();
		
		if (!userID.equals("0"))
		{
			//Searching member.csv for target User ID.
			try
			{
				
				FileReader file = new FileReader("member.csv");
				BufferedReader br = new BufferedReader(file);
				
				String line = br.readLine(); //Reads the first line of the file.
				completeFile = line + "\n";
				headerCols = line.split(","); //Store the column headers for display purposes.
				line = br.readLine(); //Reads the second line of the file (first line is headers).
				
				while (line != null) 
				{
				   
				    String[] cols = line.split(",");  // Use comma as a separator.
				    
					if (userID.equals(cols[4].trim())) //Trim white spaces. If target User ID matches member's User ID. 
					{
						idExists = true; //Target has been found.
						
						
						System.out.println("Begining editing process!"); 
						System.out.println();
						
						//Display details of member account.
						for (int i = 0; i < 12; i++) //For every column in line print header and data.
						{
								System.out.println((i+1) + ") " + headerCols[i].trim() + ": " + cols[i].trim());
						}
						System.out.println();
						
						//Getting a valid column number to edit.
						while ( columnNumber < 0 || columnNumber == 4 || columnNumber > 11) 
						{
							System.out.println("Please enter the number of the row you wish to edit (e.g. 2). Note: User ID cannot be edited.");
							try {
								columnNumber = in.nextInt() - 1;
								}
							catch (Exception e) 
							{
							      System.out.println("You may only enter numbers.");
							      in.nextLine(); //Clearing scanner buffer.
							}
						}
						
						in.nextLine(); //Must do twice, otherwise end up with a blank.
						
						//Getting new column data in a valid format.
						while (validFormat == false)
						{
							System.out.println("Please enter the new data for the row.");
							columnData = in.nextLine(); 
							
							if (columnNumber == 9) //DOB must be a valid date in a LocalDate format.
							{
								try {
									LocalDate.parse(columnData);
									validFormat = true;
									}
								catch (Exception e) 
								{
									System.out.println("Incorrect format. Correct format is 'YYYY-MM-DD'.");
								}
								
							}
							else if (columnNumber == 10 && !columnData.equals("true") && !columnData.equals("false")) //Admin must be in boolean format.
							{
								System.out.println("Enter 'true' or 'false.'");
							}
							else if (columnNumber == 11 && !columnData.matches("^[0-9]+.[0-9]+$")) //Fine must be in a double format.
							{
								System.out.println("Incorrect format. Correct format is '0.00'.");
							}
							else
							{
								validFormat = true;
							}
						}
						
						cols[columnNumber] = columnData; //Rewriting target column with new data.
						line = "";
						
						for (int i = 0; i < 12; i++) ///Reassembling the line with new data into the correct CSV order.
						{
							line += (cols[i] + ",");
						}
						
					}
					
					completeFile += (line + "\n"); //Reassembling file content, line by line (includes modified line).
				    line = br.readLine(); //read the next line.
				}
				file.close();
				
				//Starting here, write back initial data to file and append new data.
				if (idExists)
				{	
					try 	
					{
						FileWriter input  = new FileWriter("member.csv"); 
								
						input.write(completeFile);
								
						input.close();
						
						System.out.println("Profile has been edited.");
					}
						
					catch (IOException e) 
					{
						e.printStackTrace();
					}
				}
				else
				{
					System.out.println("User ID not found.");
				}
			
			}
			catch (IOException e) 
			{
				e.printStackTrace();
				
			}
		}
	}
	
	//Method to view all member profiles.
	private void viewMembers() 
	{
		String[] headerCols; //Stores column headers in an array.
		
		try 
		{
		
			FileReader file = new FileReader("member.csv"); //Reader for member.csv
			BufferedReader reader = new BufferedReader(file); //Buffered reader for member.csv
		
			String line = reader.readLine(); //Variable stores a single line of member.csv
			headerCols = line.split(","); //Store the column headers for later use.
			line = reader.readLine();
		
			while (line != null) //While there are more lines...
			{ 	
				
				String[] cols = line.split(",");  // Use comma as a separator.
				
				System.out.println();
				
				for (int i = 0; i < 12; i++) //For every column in line print header and data.
				{
						System.out.println(headerCols[i].trim() + ": " + cols[i].trim());
				}
				
				
				line = reader.readLine(); //Read the next line.
			}
			file.close();	
			
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	//Method for creating a new member profile.
	private void createMember() 
	{
		String userInput = ""; //Variable stores user input.
		boolean valid = false; //Tracks if the euserInput is of a valid format.
		
		System.out.println("Please enter the Member Type of the new member (Full, Casual or Staff):");
		in.nextLine();
		userInput = in.nextLine();
		
		if (userInput.equals("Full")) //For a Full Member account.
		{
			FullMember newFull = new FullMember(); //Create new instance of FullMember.
			newFull.setMemberType(userInput);
			newFull.setUserID(latestUserID() + 1); //Get the UserID of the latest Member account and then add 1 to it. 
			System.out.println("What is the member's first name?");
			userInput = in.nextLine();
			newFull.setFirstName(userInput);
			System.out.println("What is the member's surname?");
			userInput = in.nextLine();
			newFull.setSurname(userInput);
			System.out.println("What is the member's password?");
			userInput = in.nextLine();
			newFull.setPassword(userInput);
			System.out.println("What is the member's address?");
			userInput = in.nextLine();
			newFull.setAddress(userInput);
			System.out.println("What is the member's town?");
			userInput = in.nextLine();
			newFull.setTown(userInput);
			System.out.println("What is the member's postcode?");
			userInput = in.nextLine();
			newFull.setPostcode(userInput);
			System.out.println("What is the member's telephone number?");
			userInput = in.nextLine();
			newFull.setTelNumber(userInput);
			
			while (valid == false) //Checking that the DOB format is correct and is a valid date.
			{
				System.out.println("What is the member's date of birth (YYYY-MM-DD)?");
				userInput = in.nextLine();
				
				try 
				{
					newFull.setDOB(LocalDate.parse(userInput)); 
					valid = true;
				}
				catch (Exception e)
				{
					System.out.println("Incorrect format. Correct format is 'YYYY-MM-DD'.");
					System.out.println();
				}
			}
			
			newFull.writeMember();
			
		}
		else if (userInput.equals("Casual")) //For a Casual Member account.
		{
			CasualMember newCasual = new CasualMember(); //Create a new instance of CasualMember.
			newCasual.setMemberType(userInput);
			newCasual.setUserID(latestUserID() + 1); //Get the UserID of the latest Member account and then add 1 to it.
			System.out.println("What is the member's first name?");
			userInput = in.nextLine();
			newCasual.setFirstName(userInput);
			System.out.println("What is the member's surname?");
			userInput = in.nextLine();
			newCasual.setSurname(userInput);
			System.out.println("What is the member's password?");
			userInput = in.nextLine();
			newCasual.setPassword(userInput);
			System.out.println("What is the member's address?");
			userInput = in.nextLine();
			newCasual.setAddress(userInput);
			System.out.println("What is the member's town?");
			userInput = in.nextLine();
			newCasual.setTown(userInput);
			System.out.println("What is the member's postcode?");
			userInput = in.nextLine();
			newCasual.setPostcode(userInput);
			System.out.println("What is the member's telephone number?");
			userInput = in.nextLine();
			newCasual.setTelNumber(userInput);
			
			while (valid == false) //Checking that the DOB format is correct and is a valid date.
			{
				System.out.println("What is the member's date of birth (YYYY-MM-DD)?");
				userInput = in.nextLine();
				
				try 
				{
					newCasual.setDOB(LocalDate.parse(userInput)); 
					valid = true;
				}
				catch (Exception e)
				{
					System.out.println("Incorrect format. Correct format is 'YYYY-MM-DD'.");
					System.out.println();
				}
			}
			
			newCasual.writeMember();

		}
		else if (userInput.equals("Staff")) //For a Staff Member account.
		{
			StaffMember newStaff = new StaffMember(); //Create a new instance of StaffMember.
			newStaff.setMemberType(userInput);
			
			while (! userInput.equals("yes") && ! userInput.equals("no")) //While not "yes" or "no".
			{
				System.out.println("Is the member an admin (yes/no)?");
				userInput = in.nextLine();
			
				if (userInput.equals("yes"))
				{
				newStaff.setAdmin(true);
				}
				else if (userInput.equals("no"))
				{
				newStaff.setAdmin(false);
				}
			}
			
			newStaff.setUserID(latestUserID() + 1); //Get UserID of the latest Member account and then add 1 to it.
			System.out.println("What is the member's first name?");
			userInput = in.nextLine();
			newStaff.setFirstName(userInput);
			System.out.println("What is the member's surname?");
			userInput = in.nextLine();
			newStaff.setSurname(userInput);
			System.out.println("What is the member's password?");
			userInput = in.nextLine();
			newStaff.setPassword(userInput);
			System.out.println("What is the member's address?");
			userInput = in.nextLine();
			newStaff.setAddress(userInput);
			System.out.println("What is the member's town?");
			userInput = in.nextLine();
			newStaff.setTown(userInput);
			System.out.println("What is the member's postcode?");
			userInput = in.nextLine();
			newStaff.setPostcode(userInput);
			System.out.println("What is the member's telephone number?");
			userInput = in.nextLine();
			newStaff.setTelNumber(userInput);

			while (valid == false) //Checking that the DOB format is correct and is a valid date.
			{
				System.out.println("What is the member's date of birth (YYYY-MM-DD)?");
				userInput = in.nextLine();
				
				try 
				{
					newStaff.setDOB(LocalDate.parse(userInput)); 
					valid = true;
				}
				catch (Exception e)
				{
					System.out.println("Incorrect format. Correct format is 'YYYY-MM-DD'.");
					System.out.println();
				}
			}
			
			newStaff.writeMember(); //Write the newly created member to member.csv.
		}
		else
		{
			System.out.println("Not a valid member type. Please try again");	
		}
		
	}
	
	//Method allows an Admin to record a loan for a user.
	private void recordLoan() 
	{
		String loanerID = ""; //User ID of the account that wishes to loan an item.
		String stockID = ""; //Stock ID of the item to be loaned.
		String fullFileMedia = ""; //Stores the data that will overwrite media.csv.
		String fullFileMember = ""; //Stores data that will overwrite member.csv.
		String paidFine = ""; //Tracks if the user has paid their fine or not.
		int numBorrowed = 0; //Number of items the user has on loan.
		int allowedBorrows = 0; //Number of items the user is allowed to loan.
		boolean loanerFound = false; //Tracks if the User ID of the account that wishes to make a loan has been found.
		boolean itemFound = false; //Tracks if the Stock Id of the item to be loaned has been found.
		String title = ""; //Title of the item being loaned.
		
		System.out.println("Please enter the Stock ID of the item to be loaned:");
		stockID = in.next(); //Getting Stock ID of the item to be loaned.
		System.out.println("Please enter the User ID of the member that wishes to make a Loan:");
		loanerID = in.next(); //Getting User ID of the account that wishes to make a loan.

		//Read member.csv - if (fine > 0) then message and quit, allow them to pay fine to continue.
		try 
		{
			
			FileReader file = new FileReader("member.csv");
			BufferedReader br = new BufferedReader(file);
			
			String line = br.readLine(); //Reads the first line of the file (headers).
			fullFileMember = line + "\n"; //Writes headers to the variable, also clears it of prior data.
			line = br.readLine(); //Reads the second line (with actual data).
			
			while (line != null) 
			{
				
			    String[] colsMember = line.split(",");  // Use comma as a separator to split the line.
			    
			    if (colsMember[4].trim().equals(loanerID)) //Acquiring user's borrow limit.
			    {
			    	loanerFound = true; //User has been found.
			    	
			    	//Checking user account and setting limited on the number of items that can be loaned.
			    	if (colsMember[0].trim().equals("Full"))
			    	{
			    		allowedBorrows = 4;
			    	}
			    	else if (colsMember[0].trim().equals("Staff"))
			    	{
			    		allowedBorrows = 6;
			    	}
			    	else
			    	{
						System.out.println("User ID belongs to a Casual Member who cannot loan items.");
						mrMenu(); //If User is a Casual Member then return to Member Record Menu.
			    	}
			    }
			    
			  //If user has a fine...
			    if (colsMember[4].trim().equals(loanerID) && Double.parseDouble(colsMember[11].trim()) > 0) 
			    {
			    	System.out.println("Member has a fine of " + colsMember[11].trim());
			    	
			    	//ask if them to pay it.
			    	while(!paidFine.equals("yes") && !paidFine.equals("no")) 
					{
						System.out.println("Has the member payed the fine? (yes/no)");
						paidFine = in.next().toLowerCase();
					}
			    	
					if (paidFine.equals("no")) //If user does not pay then return to Member Record Menu.
					{
						System.out.println("Member cannot borrow items until the fine is paid.");
						mrMenu();
					}
					else //Otherwise their fine is set to 0.
					{
						line = "";
						colsMember[11] = "0.00";
						
						for (int i = 0; i < 12; i++) //Putting commas back between columns to fit CSV file format.
						{
							line += (colsMember[i] + ","); 
						}
						
					}
			    }
			    
			    fullFileMember += line + "\n"; //Line is added to fullFileMember which can later be used to overwrite member.csv. 
			    line = br.readLine(); //Reads the next line.
			}
			
			file.close();
			
			//If User ID was not found in member.csv then return to Member Record Menu.
			if (!loanerFound)
			{
				System.out.println("Could not find a member with this User ID.");
				mrMenu();
			}
			
			if (paidFine.equals("yes")) //If user has paid the fine their write this to their record.
			{
				Login.getMenu().getStockRecord().writeFile("member.csv", fullFileMember); //Write to file;
			}
			
		}
		catch (IOException e) 
		{
			e.printStackTrace();
			
		}
		
		//Read media.csv.
		try 
		{
			
			FileReader file = new FileReader("media.csv");
			BufferedReader br = new BufferedReader(file);
			
			String line = br.readLine(); //Reads the first line of the file (headers).
			fullFileMedia = line + "\n";
			line = br.readLine(); //Reads the second line of the file.
			
			while (line != null) 
			{
			   
			    String[] cols = line.split(",");  // Use comma as a separator.
			    
			    //If item is currently loaned to user, numBorrows increases by 1.
			    if (cols[5].trim().equals(loanerID)) 
			    {
			    	numBorrowed++;
			    }
			    
			    //If item is currently loaned to user and it is overdue then inform user and quit.
			    if ( (cols[5].trim().equals(loanerID)) && (ChronoUnit.DAYS.between(LocalDate.parse(cols[6].trim()), LocalDate.now()) > 10) ) 
			    {
					System.out.println("Member currently has one or more overdue items and must return them before they can loan more items");
					mrMenu();
			    }
			    
			    //If target item is reserved by another user and reservation date is not behind current date, then inform the user and quit.
			    if (cols[1].trim().equals(stockID) && !cols[7].trim().equals(loanerID) && !cols[7].trim().equals("N/A") && ChronoUnit.DAYS.between(LocalDate.parse(cols[8].trim()), LocalDate.now()) >= 0) 
			    {
					System.out.println("Item is reserved by another member and therefore cannot be loaned.");
					mrMenu();
			    }
			    
			    //If target item is currently on loan, then inform the user and quit.
			    if (cols[1].trim().equals(stockID) && !cols[5].trim().equals("N/A")) 
			    {
					System.out.println("Item cannot be loaned as it is currently meant to be on loan.");
					mrMenu();
			    }
			    
			    //If target item then modify line in preparation for overwriting file.
			    if (cols[1].trim().equals(stockID)) 
			    {
			    	
			    	itemFound = true;
			    	
			    	title = cols[2]; //Getting the title of the item.
			    	
			    	line = "";
			    	cols[5] = loanerID;
			    	cols[6] = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			    	cols[7] = "N/A";
			    	cols[8] = "N/A";
			    	
					for (int i = 0; i < 23; i++) //Putting commas back between columns to fit CSV file format.
					{
						line += (cols[i] + ",");
					}
					
			    }
			    
			    fullFileMedia += line + "\n";
			    line = br.readLine(); //read the next line.
			}
			
			file.close();
			
			if (!itemFound) //If no item with this Stock ID exists
			{
				System.out.println("Could not find an item with this Stock ID.");
				mrMenu();
			}
			else if (! (numBorrowed < allowedBorrows)) //If user has reached their allowance limit.
			{
				System.out.println("Member has reached their borrow limit.");
				mrMenu();
			}
			else if (numBorrowed < allowedBorrows && itemFound == true) //If user has not reached their borrow limit and item was found.
			{
			Login.getMenu().getStockRecord().writeFile("media.csv", fullFileMedia); //Write loan to media.csv.
			writeLoan(loanerID, stockID, title); //Write loan to loans.csv.
			System.out.println("Item has been loaned."); //Inform user.
			}
			
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	//Method that allows an Admin to return loans for users.
	private void returnLoan() 
	{
		String loanerID = ""; //User ID of the account that wishes to loan an item.
		String stockID = ""; //Stock ID of the item to be loaned.
		String fullFileMedia = ""; //Stores the data that will overwrite media.csv.
		String fullFileMember = ""; //Stores data that will overwrite member.csv.
		boolean itemFound = false; //Tracks if the Stock Id of the item to be loaned has been found.
		boolean returnedItem = false; //Tracks if the User has returned an item.
		double fine = 0.00; //Stores how much money the user owes due to late submission.
		String paidFine = ""; //Tracks if the user has paid their fine or not.
		
		System.out.println("Please enter the Stock ID of the item to be returned:");
		stockID = in.next(); //Getting Stock ID of the item to be loaned.
		System.out.println("Please enter the User ID of the member that wishes to return an item:");
		loanerID = in.next(); //Getting User ID of the account that wishes to make a loan.
		
		//Read media.csv.
		try 
		{
			
			FileReader file = new FileReader("media.csv");
			BufferedReader br = new BufferedReader(file);
			
			String line = br.readLine(); //Reads the first line of the file (headers).
			fullFileMedia = line + "\n";
			line = br.readLine(); //Reads the second line of the file.
			
			while (line != null) 
			{
			   
			    String[] cols = line.split(",");  // Use comma as a separator.
			    
				if (cols[1].trim().equals(stockID)) //Check if the Stock ID column contains target Stock ID.
				{
					itemFound = true; //Item has been found.
					
					if (cols[5].trim().equals(loanerID)) //Check if the Loaner ID column contains target User ID.
					{
						
						returnedItem = true; //Item can be returned.
						
						//If time difference between current date and loan date is greater than 10 then apply fine.
						if (ChronoUnit.DAYS.between(LocalDate.parse(cols[6].trim()), LocalDate.now()) > 10) 
						{
							//Fine is ((the difference in days between loan date and current date) - 10) * 0.5.
							fine = (ChronoUnit.DAYS.between(LocalDate.parse(cols[6].trim()), LocalDate.now()) - 10) * 0.5; 
							
							System.out.println("Member has a fine of £" + fine);
							
							//Allow user to pay a fine.
							while(!paidFine.equals("yes") && !paidFine.equals("no")) 
							{
								System.out.println("Has the member paid the fine? (yes/no)");
								paidFine = in.next().toLowerCase();
							}
							
							//If user does not pay a fine then the fine is written to their account.
							if (paidFine.equals("no"))
							{
								//Read member.csv.
								try 
								{
									
									FileReader file1 = new FileReader("member.csv");
									BufferedReader br1 = new BufferedReader(file1);
									
									String line1 = br1.readLine(); //Reads the first line of the file (headers).
									fullFileMember = line1 + "\n"; //Writes headers to the variable, also clears it of prior data.
									line1 = br1.readLine(); //Reads the second line (with actual data).
									
									while (line1 != null) 
									{
										
									    String[] colsMember = line1.split(",");  // Use comma as a separator to split the line.
									    
									    if (colsMember[4].trim().equals(loanerID)) 
									    {
									    	//Add fine for the late item to the fine value on their account.
									    	fine += Double.parseDouble(colsMember[11]); 
									    	colsMember[11] = Double.toString(fine);
									    	
											line1 = ""; //Clear line.
											
											for (int i = 0; i < 12; i++) //Putting commas back between columns to fit CSV file format.
											{
												line1 += (colsMember[i] + ",");
											}
									    }
									    
									    fullFileMember += line1 + "\n"; //Add line to fullFileMember.
									    line1 = br1.readLine(); //read the next line.
									}
									
									file1.close();
									Login.getMenu().getStockRecord().writeFile("member.csv", fullFileMember); //Overwrite member.csv with new data.
								}
								catch (IOException e) 
								{
									e.printStackTrace();
									
								}
								
								System.out.println("Fine has been applied to account."); //Let user know that a fine has been applied to their account.
							}
							
						}
						
						
						//Rewriting the line.
						cols[5] = "N/A";
						cols[6] = "N/A";
						line = ""; //Clear line.
						for (int i = 0; i < 23; i++) //Putting commas back between columns to fit CSV file format.
						{
							line += (cols[i] + ",");
						}
						
						System.out.println("Item returned.");
						
					}
					else
					{
						System.out.println("Item is not loaned to member with this User ID.");
					}
	
				}
				fullFileMedia += line + "\n"; //Write line to fullFile.
			    line = br.readLine(); //read the next line.
			}
			
			file.close();
		
		}
		catch (IOException e) 
		{
			e.printStackTrace();
			
		}
		
		if (!itemFound) //If item not found.
		{
			System.out.println("Could not find item with this Stock ID.");
		}
		
		if (returnedItem)
		{
			Login.getMenu().getStockRecord().writeFile("media.csv", fullFileMedia); //Write to file;
			updateLoan(loanerID, stockID); //Update loan record.
		}

	}
	
	//Method returns the User ID of the last created account.
	private int latestUserID()
	{
		int userID = 0; //Stores User ID of an account.
		
		try 
		{
			
			FileReader file = new FileReader("member.csv");
			BufferedReader br = new BufferedReader(file);
			
			String line = br.readLine(); //Reads the first line of the file (headers).
			line = br.readLine(); //Reads the second line (with actual data).
			
			while (line != null) 
			{
				
			    String[] cols = line.split(",");  // Use comma as a separator to split the line.
			    userID = Integer.parseInt(cols[4].trim()); //Set User ID to User ID of the account.

			    line = br.readLine(); //read the next line.
			}
			
			file.close();
		
		}
		catch (IOException e) 
		{
			e.printStackTrace();
			
		}
		
		return userID; //Return User ID.
	}
	
	//Method to display loans for all users.
	private void viewAllLoans()
	{
		//Read loans.csv.
		try 
		{
			
			FileReader file = new FileReader("loans.csv");
			BufferedReader br = new BufferedReader(file);
			
			String line = br.readLine(); //Reads the first line of the file (headers).
			line = br.readLine(); //Reads the second line (with actual data).
			
			//Display first 4 columns of a line.
			while (line != null) 
			{
				
			    String[] cols = line.split(",");  // Use comma as a separator to split the line.
			    
			    System.out.println("User ID: " + cols[0]);
			    System.out.println("Stock ID: " + cols[1]);
			    System.out.println("Title: " + cols[2]);
			    System.out.println("Date Loaned: " + cols[3]);
			    
			    if (cols.length == 5) //If there is a 5th column (return date), display that too.
			    {
			    	System.out.println("Date Returned: " + cols[4]);
			    }
	
			    System.out.println();

			    line = br.readLine(); //read the next line.
			}
			
			file.close();
		
		}
		catch (IOException e) 
		{
			e.printStackTrace();
			
		}

	}
	
	//Method to display all loans for the currently logged user.
	private void viewMyLoans()
	{
		//Read loans.csv.
		try 
		{
			
			FileReader file = new FileReader("loans.csv");
			BufferedReader br = new BufferedReader(file);
			
			String line = br.readLine(); //Reads the first line of the file (headers).
			line = br.readLine(); //Reads the second line (with actual data).
			
			while (line != null) 
			{
				
			    String[] cols = line.split(",");  // Use comma as a separator to split the line.
			    
			    //Display data from line if User ID matches currently logged user.
			    if (cols[0].trim().equals(Integer.toString(Login.getMembers().get(Login.getUserIndex()).getUserID())))
			    {
				    System.out.println("User ID: " + cols[0]);
				    System.out.println("Stock ID: " + cols[1]);
				    System.out.println("Title: " + cols[2]);
				    System.out.println("Date Loaned: " + cols[3]);
				    
				    if (cols.length == 5) //If there is a return date, then display that too.
				    {
				    	System.out.println("Date Returned: " + cols[4]);
				    }
		
				    System.out.println();
			    }
			    
			    line = br.readLine(); //read the next line.
			}
			
			file.close();
		
		}
		catch (IOException e) 
		{
			e.printStackTrace();
			
		}
	}
	
	//Method used to record a loan in loans.csv.
	private void writeLoan(String loanerID, String stockID, String title)
	{
		
		String fullFileLoan = ""; //Stores the data that will overwrite loans.csv.
		
		//Read loans.csv.
		try 
		{
			
			FileReader file = new FileReader("loans.csv");
			BufferedReader br = new BufferedReader(file);
			
			String line = br.readLine(); //Reads the first line of the file (headers).
			fullFileLoan = line + "\n";
			line = br.readLine(); //Reads the second line (with actual data).
			
			while (line != null) 
			{
	
			    fullFileLoan += line + "\n"; 
			    line = br.readLine(); //read the next line.
			}
			
			file.close();
		
			//When there are no more lines in the file, add User ID, Stock ID, title of media and current date to the data to be writen.
			fullFileLoan += loanerID + "," + stockID + "," + title + "," + LocalDate.now(); 
		}
		catch (IOException e) 
		{
			e.printStackTrace();
			
		}
		
		//Write data to loans.csv.
		try 	
		{
			File file = new File("loans.csv");
			
			FileWriter input  = new FileWriter(file); 
					
			input.write(fullFileLoan); 
			
			input.close();
		}
			
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	//Method used to write the return date of a loaned item to loans.csv.
	private void updateLoan(String loanerID, String stockID)
	{
		
		String fullFileLoan = ""; //Stores the data that will overwrite loans.csv.
		
		//Read loans.csv.
		try 
		{
			FileReader file = new FileReader("loans.csv");
			BufferedReader br = new BufferedReader(file);
			
			String line = br.readLine(); //Reads the first line of the file (headers).
			fullFileLoan = line + "\n";
			line = br.readLine(); //Reads the second line (with actual data).
			
			while (line != null) 
			{
	
				String[] cols = line.split(",");  // Use comma as a separator to split the line.
				
				if (cols[0].equals(loanerID) && cols[1].equals(stockID)) //When line with matching UserID and Stock ID is found...
				{
					line = "";
					
					for (int i = 0; i < 4; i++)
					{
						line += cols[i] + ",";
					}
					
					line += LocalDate.now(); //add current date to the last column.
				}
				
			    fullFileLoan += line + "\n"; 
			    line = br.readLine(); //read the next line.
			}
			
			file.close();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
			
		}
		
		//Write the data back to file.
		try 	
		{
			File file = new File("loans.csv");
			
			FileWriter input  = new FileWriter(file); 
					
			input.write(fullFileLoan); 
			
			input.close();
		}
			
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}