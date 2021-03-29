import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class StockRecord {
	
	private Scanner in = new Scanner (System.in); //Global scanner used for all input.
	
	//Function determines the appropriate menu to show to the user based on their membership type and admin status.
	public void srMenu() 
	{ 		
		if (Login.getMembers().get(Login.getUserIndex()) instanceof CasualMember)
		{
			casualSRMenu(); //Display menu for casual members.
		}
		else if (Login.getMembers().get(Login.getUserIndex()).getAdmin() == true)
		{
			adminSRMenu(); //Display menu for admin members
		}
		else 
		{
			normalSRMenu(); //Display menu for non casual members that are not admins.
		}
	}
	
	//Menu for a Casual Member with very limited options.
	private void casualSRMenu() 
	{
		String menuItem; //Using String for the switch so that switch doesn't crash if non numbers are entered.
		boolean quit = false; //Variable used to track when the menu should be exited.
		
		do {
		
			//Prints the menu.
			System.out.println();
			System.out.println("Stock Records");
	    	System.out.println("===========");    
	        System.out.println("Enter '1' to view all stocks"); 
	        System.out.println("Enter '2' to search stocks by title.");
	        System.out.println("Enter '0' to return to main menu.");
	        System.out.print("Please choose a menu item: \n \n");
	        
	        menuItem = in.next();
	        
	        switch (menuItem) {
	        case "1":
	        	System.out.println();
	        	displayMedia(); //Displays all media to user.
	        	break;
	        case "2":
	        	System.out.println();
	        	searchByTitle(); //Displays media item to user if it's title contains target string.
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
	
	//Menu for Non-Casual members that are not admins. Some options are not available.
	private void normalSRMenu()
	{
		String menuItem; //Using String for the switch so that switch doesn't crash if non numbers are entered.
		boolean quit = false; //Variable used to track when the menu should be exited.
		
		do {
		
			//Prints the menu.
			System.out.println();
			System.out.println("Stock Records");
	    	System.out.println("===========");    
	        System.out.println("Enter '1' to view all stocks."); 
	        System.out.println("Enter '2' to search stocks by title.");
	        System.out.println("Enter '3' to reserve a stock item.");
	        System.out.println("Enter '0' to return to main menu.");
	        System.out.print("Please choose a menu item: \n \n");
	        
	        menuItem = in.next();
	        
	        switch (menuItem) {
	        case "1":
	        	System.out.println();
	        	displayMedia(); //Displays all media to user.
	        	break;
	        case "2":
	        	System.out.println();
	        	searchByTitle(); //Displays media item to user if it's title contains target string.
	        	break;
	        case "3":
	        	System.out.println();
	        	 reserveMedia(); //Allows a user to reserve a media item.
	        	break;
	        case "0": 
	            quit = true; 
	            System.out.println();
	            Login.getMenu().mainMenu(); //Display the main menu.
	            break;
	        default: 
	        	System.out.println();
	        	System.out.println("Invalid choice. 0 to 3 only please."); 
	        	System.out.println();
	        }
		} while (!quit);
		 
	in.close();
	}
	
	//Menu for admins with all options available.
	private void adminSRMenu() 
	{
		String menuItem; //Using String for the switch so that switch doesn't crash if non numbers are entered.
		boolean quit = false; //Variable used to track when the menu should be exited.
		
		do {
		
			//Prints the menu.
			System.out.println();
			System.out.println("Stock Records");
	    	System.out.println("===========");    
	        System.out.println("Enter '1' to view all stocks."); 
	        System.out.println("Enter '2' to search stocks by title.");
	        System.out.println("Enter '3' to reserve a stock item.");
	        System.out.println("Enter '4' to create new stock item.");
	        System.out.println("Enter '5' to edit a stock item.");
	        System.out.println("Enter '0' to return to main menu.");
	        System.out.print("Please choose a menu item: \n \n");
	        
	        menuItem = in.next();
	        
	        switch (menuItem) {
	        case "1":
	        	System.out.println();
	        	displayMedia(); //Displays all media to an admin.
	        	break;
	        case "2":
	        	System.out.println();
	        	searchByTitle(); //Displays media item to admin if it's title contains target string.
	        	break;
	        case "3":
	        	System.out.println();
	        	 reserveMedia(); //Allows an admin to reserve a media item.
	        	break;
	        case "4":
	        	System.out.println();
	        	createMedia(); //Allows an admin to create a new media item entry.
	        	break;
	        case "5":
	        	System.out.println();
	        	updateMedia(); //Allows an admin to update a media item entry.
	        	break;
	        case "0": 
	            quit = true; 
	            System.out.println();
	            Login.getMenu().mainMenu(); //Display the main menu.
	            break;
	        default: 
	        	System.out.println();
	        	System.out.println("Invalid choice. 0 to 5 only please.");
	        	System.out.println();
	        }
		} while (!quit);
		 
	in.close();
	}
	
	//Method allows the creation of new media items.
	private void createMedia() {
		
		String mediaType; //Variable stores media type of the item being created.
		String newMedia = ""; //Stores line containing data for the new media item.
		boolean valid = false; //Tracks whether the data entered is of a valid format.
		int checkInt; //Used for checking if input is an integer.
		double checkDouble; //Used to check if input is a float.
		LocalDate checkDate; //Used to check if input is a LocalDate.
		int stockID = 0; //Stores the Stock ID of the last item recorded. Needed for auto incrementing.
		String fullFile = ""; //Stores the contents of the file to be written.
		
		//Read media.csv.
		try 
		{
			
			FileReader file = new FileReader("media.csv");
			BufferedReader br = new BufferedReader(file);
			
			String line = br.readLine(); //Reads the first line of the file (headers).
			fullFile = line + "\n"; //Writes headers to the variable, also clears it of prior data.
			line = br.readLine(); //Reads the second line (with actual data).
			
			while (line != null) 
			{
			   
				fullFile += line + "\n";
				
			    String[] cols = line.split(",");  // Use comma as a separator to split the line.
			    stockID = Integer.parseInt(cols[1].trim()); //stockID becomes the Stock ID of the last item.

			    line = br.readLine(); //read the next line.
			}
			
			file.close();
		
		}
		catch (IOException e) 
		{
			e.printStackTrace();
			
		}
		
		System.out.println("Please enter media type (Book, Journal, Video or Compact Disk)");
		in.nextLine();
		mediaType = in.nextLine();
		
		//Acquiring data for a new Book item.
		if (mediaType.equals("Book"))
		{
			
			newMedia += mediaType + ", ";
			newMedia += (stockID + 1) + ", "; 
			
			System.out.println("Please enter the Title of the Media.");
			newMedia += in.nextLine() + ", ";
			
			while (valid == false)
			{
				System.out.println("Please enter Price of the media e.g. 5.99."); //Must be in Double format.
				try
				{
					checkDouble = Double.parseDouble(in.nextLine());
					newMedia += checkDouble + ", ";
					valid = true; //Setting to true to exit loop.
				}
				catch (Exception e)
				{
					System.out.println("Incorrect format. Correct format is '5.99'.");
				}
				
			}
			valid = false; //Resetting to false for reuse.

			System.out.println("Please enter the Publisher of the item.");
			newMedia += in.nextLine() + ", N/A, N/A, N/A, N/A, ";
			
			while (valid == false)
			{
				System.out.println("Please enter the ISBN."); //Must be in integer format.
				try
				{
					checkInt = Integer.parseInt(in.nextLine());
					newMedia += checkInt + ", ";
					valid = true; //Setting to true to exit loop.
				}
				catch (Exception e)
				{
					System.out.println("Incorrect format. Correct format is '123' etc.");
				}
				
			}
			valid = false; //Resetting to false for reuse.
			
			System.out.println("Please enter the name of the Author.");
			newMedia += in.nextLine() + ", ";
			System.out.println("Please enter the Subject Area.");
			newMedia += in.nextLine() + ", ";
			
			while (valid == false)
			{
				System.out.println("Please enter the Number of Pages."); //Must be in integer format.
				try
				{
					checkInt = Integer.parseInt(in.nextLine());
					newMedia += checkInt + ", N/A, N/A, N/A, N/A, N/A, N/A, N/A, N/A, N/A, N/A";
					valid = true; //Setting to true to exit loop.
				}
				catch (Exception e)
				{
					System.out.println("Incorrect format. Correct format is '123' etc.");
				}
				
			}
			valid = false; //Resetting to false for reuse.
			
			fullFile += newMedia; //Adding new data to data from file.
			
			writeFile("media.csv", fullFile); //Overwriting media.csv.
			
		}
		//Acquiring data for a new Journal item.
		else if (mediaType.equals("Journal"))
		{

			newMedia += mediaType + ", ";
			newMedia += (stockID + 1) + ", ";
			
			System.out.println("Please enter the Title of the Media.");
			newMedia += in.nextLine() + ", ";
			
			while (valid == false)
			{
				System.out.println("Please enter Price of the media e.g. 5.99."); //Must be in Double format.
				try
				{
					checkDouble = Double.parseDouble(in.nextLine());
					newMedia += checkDouble + ", ";
					valid = true; //Setting to true to exit loop.
				}
				catch (Exception e)
				{
					System.out.println("Incorrect format. Correct format is '5.99'.");
				}
				
			}
			valid = false; //Resetting to false for reuse.
			
			System.out.println("Please enter the Publisher of the item.");
			newMedia += in.nextLine() + ", N/A, N/A, N/A, N/A, N/A, N/A, ";
			System.out.println("Please enter the Subject Area.");
			newMedia += in.nextLine() + ", ";
			
			while (valid == false)
			{
				System.out.println("Please enter the Number of Pages."); //Must be in integer format.
				try
				{
					checkInt = Integer.parseInt(in.nextLine());
					newMedia += checkInt + ", ";
					valid = true; //Setting to true to exit loop.
				}
				catch (Exception e)
				{
					System.out.println("Incorrect format. Correct format is '123' etc.");
				}
				
			}
			valid = false; //Resetting to false for reuse.
			
			while (valid == false)
			{
				System.out.println("Please enter the ISSN."); //Must be in integer format.
				try
				{
					checkInt = Integer.parseInt(in.nextLine());
					newMedia += checkInt + ", ";
					valid = true; //Setting to true to exit loop.
				}
				catch (Exception e)
				{
					System.out.println("Incorrect format. Correct format is '123' etc.");
				}
				
			}
			valid = false; //Resetting to false for reuse.
			
			System.out.println("Please enter the Issue Number.");
			newMedia += in.nextLine() + ", ";
			
			while (valid == false)
			{
				System.out.println("Please enter the Date of Issue."); //Must be in LocalDate format.
				try
				{
					checkDate = LocalDate.parse(in.nextLine());
					newMedia += checkDate + ", N/A, N/A, N/A, N/A, N/A, N/A, N/A";
					valid = true; //Setting to true to exit loop.
				}
				catch (Exception e)
				{
					System.out.println("Incorrect format. Correct format is 'YYYY-MM-DD' etc.");
				}
				
			}
			valid = false; //Resetting to false for reuse.
			
			fullFile += newMedia; //Adding new data to data from file.
			
			writeFile("media.csv", fullFile); //Overwriting mediac.csv with new data.
			
		}
		//Acquiring data for a new Video item.
		else if (mediaType.equals("Video"))
		{
			
			newMedia += mediaType + ", ";
			newMedia += (stockID + 1) + ", ";
			
			System.out.println("Please enter the Title of the Media.");
			newMedia += in.nextLine() + ", ";
			
			while (valid == false)
			{
				System.out.println("Please enter Price of the media e.g. 5.99."); //Must be in Double format.
				try
				{
					checkDouble = Double.parseDouble(in.nextLine());
					newMedia += checkDouble + ", ";
					valid = true; //Setting to true to exit loop.
				}
				catch (Exception e)
				{
					System.out.println("Incorrect format. Correct format is '5.99'.");
				}
				
			}
			valid = false; //Resetting to false for reuse.
			
			System.out.println("Please enter the Publisher of the item.");
			newMedia += in.nextLine() + ", N/A, N/A, N/A, N/A, N/A, N/A, N/A, N/A, N/A, N/A, N/A, ";
			
			while (valid == false)
			{
				System.out.println("Please enter the Running Time in minutes e.g. 180."); //Must be in integer format.
				try
				{
					checkInt = Integer.parseInt(in.nextLine());
					newMedia += checkInt + ", ";
					valid = true; //Setting to true to exit loop.
				}
				catch (Exception e)
				{
					System.out.println("Incorrect format. Correct format is '123' etc.");
				}
				
			}
			valid = false; //Resetting to false for reuse.
			
			System.out.println("Please enter the Video Format.");
			newMedia += in.nextLine() + ", ";
			System.out.println("Please enter the Genre.");
			newMedia += in.nextLine() + ", ";
			System.out.println("Please enter the type of Storage Case.");
			newMedia += in.nextLine() + ", N/A, N/A, N/A";
			
			fullFile += newMedia; //Adding new data to data from file.
			
			writeFile("media.csv", fullFile); //Overwriting media.csv with new data.
			
		}
		//Getting data for a new Compact Disk item.
		else if (mediaType.equals("Compact Disk"))
		{

			newMedia += mediaType + ", ";
			newMedia += (stockID + 1) + ", ";
			
			System.out.println("Please enter the Title of the Media.");
			newMedia += in.nextLine() + ", ";
			
			while (valid == false)
			{
				System.out.println("Please enter Price of the media e.g. 5.99."); //Must be in Double format.
				try
				{
					checkDouble = Double.parseDouble(in.nextLine());
					newMedia += checkDouble + ", ";
					valid = true; //Setting to true to exit loop.
				}
				catch (Exception e)
				{
					System.out.println("Incorrect format. Correct format is '5.99'.");
				}
				
			}
			valid = false; //Resetting to false for reuse.
			
			System.out.println("Please enter the Publisher of the item.");
			newMedia += in.nextLine() + ", N/A, N/A, N/A, N/A, N/A, N/A, N/A, N/A, N/A, N/A, N/A, ";
			
			while (valid == false)
			{
				System.out.println("Please enter the Running Time in minutes e.g. 180."); //Must be in integer format.
				try
				{
					checkInt = Integer.parseInt(in.nextLine());
					newMedia += checkInt + ", N/A, N/A, ";
					valid = true; //Setting to true to exit loop.
				}
				catch (Exception e)
				{
					System.out.println("Incorrect format. Correct format is '123' etc.");
				}
				
			}
			valid = false; //Resetting to false for reuse.
			
			System.out.println("Please enter the type of Storage Case.");
			newMedia += in.nextLine() + ", ";
			System.out.println("Please enter the Type of CD it is.");
			newMedia += in.nextLine() + ", ";
			
			while (valid == false)
			{
				System.out.println("Please enter the Number of Tracks on the disk."); //Must be in integer format.
				try
				{
					checkInt = Integer.parseInt(in.nextLine());
					newMedia += checkInt + ", ";
					valid = true; //Setting to true to exit loop.
				}
				catch (Exception e)
				{
					System.out.println("Incorrect format. Correct format is '123' etc.");
				}
				
			}
			valid = false; //Resetting to false for reuse.
			
			System.out.println("Please enter the Name of the Artist.");
			newMedia += in.nextLine();
			
			fullFile += newMedia; //Adding new data to data from file.
			
			writeFile("media.csv", fullFile); //Overwriting media.csv with new data.
			
		}
		else
		{
			System.out.println("Sorry, this is not a valid Media Type."); //If user did not enter a valid media type.
		}
	}
	
	//Function for updating media items.
	private void updateMedia() 
	{
		String stockID; //holds the Stock ID of the item to be edited.
		String headers; //Stores the column headers.
		String headerCols[]; //Stores column headers in an array.
		int columnNumber = 23; //Starting at 23 so that initial data is rejected.
		String columnData = ""; //Holds new data for the target column.
		String completeFile = ""; //Stores the complete edited file contents before it is written back to media.csv
		boolean idExists = false; //Tracks if target Stock ID is found.
		boolean validFormat = false; //Tracks if input into column is of a valid format or not.
		
		displayMedia(); //Display all media items for convenience.
		System.out.println();
		
		System.out.println("Please enter the Stock ID of the item you wish to edit, or enter 0 to return to menu:");
		stockID = in.next(); //Acquiring Stock ID of target item.
		
		if (!stockID.equals("0")) //Allows user to exit method.
		{
			//Read media.csv.
			try 
			{
				
				FileReader file = new FileReader("media.csv");
				BufferedReader br = new BufferedReader(file);
				
				String line = br.readLine(); //Reads the first line of the file.
				headers = line; //Store headers.
				headerCols = line.split(","); //Store the column headers for display purposes.
				line = br.readLine(); //Reads the second line of the file (first line is headers).
				
				completeFile = (headers + "\n");
				
				while (line != null) 
				{
				   
				    String[] cols = line.split(",");  // Use comma as a separator.
				    
					if (stockID.equals(cols[1].trim())) //If target Stock ID matches item's Stock ID. Trim white spaces.
					{
						idExists = true; //Target item found.
						
						
						System.out.println("Begining editing process!");
						System.out.println();
						
						for (int i = 0; i < 23; i++) //For every column in line print header and data.
						{
								System.out.println((i+1) + ") " + headerCols[i].trim() + ": " + cols[i].trim());
						}
						System.out.println();
						
						while ( columnNumber < 0 || columnNumber == 1 || columnNumber > 22) //Getting a valid column number to edit.
						{
							System.out.println("Please enter the number of the row you wish to edit (e.g. 3). Note: Stock ID cannot be edited.");
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
						
						//Data entry and validation.
						while (validFormat == false) 
						{
							System.out.println("Please enter the new data for the column.");
							columnData = in.nextLine(); 
							
							if (columnNumber == 15) //Column must be of LocalDate format.
							{
								try {
									LocalDate.parse(columnData);
									validFormat = true; //Format is valid.
									}
								catch (Exception e) 
								{
									System.out.println("Incorrect format. Correct format is 'YYYY-MM-DD'.");
								}
								
							}
							//Some columns must be of integer format.
							else if ((columnNumber == 9 || columnNumber == 12 || columnNumber == 13 || columnNumber == 16 || columnNumber == 21) && (!columnData.matches("^[0-9]+$"))) 
							{
								System.out.println("Incorrect format. Only numbers allowed.");
							}
							//Price must be a double.
							else if (columnNumber == 3 && !columnData.matches("^[0-9]+.[0-9]+$")) 
							{
								System.out.println("Incorrect format. Correct format is '0.00'.");
							}
							else
							{
								validFormat = true; //Format is valid.
							}
						}
						
						cols[columnNumber] = columnData; //Replacing data in the column with new data.
						line = "";
						
						for (int i = 0; i < 23; i++) //Putting commas back between columns to fit CSV file format.
						{
							line += (cols[i] + ",");
						}
						
					}
					
					completeFile += (line + "\n"); //Reassembling file content, line by line (includes modified line).
				    line = br.readLine(); //Read the next line.
				}
				
				file.close();
				
				if (idExists)
				{
					//Starting here, write back initial data to file and append new data.
					try 	
					{
						FileWriter input  = new FileWriter("media.csv"); 
								
						input.write(completeFile);
								
						input.close();
						
						System.out.println("Item has been edited.");
					}
						
					catch (IOException e) 
					{
						e.printStackTrace();
					}
				}
				else
				{
					System.out.println("StockID not found.");
				}
			
			}
			catch (IOException e) 
			{
				e.printStackTrace();
				
			}
		
		}
	}
	
	//Method for displaying all media items to a user.
	private void displayMedia()
	{
		String[] headerCols; //Stores column headers in an array.
		
		//Read media.csv.
		try 
		{
		
			FileReader file = new FileReader("media.csv"); //Reader for session.log
			BufferedReader reader = new BufferedReader(file); //Buffered reader for session.log
		
			String line = reader.readLine(); //Variable stores a single line of session.log
			
			headerCols = line.split(",");//Store the column headers for later use.
			
			line = reader.readLine();
			
			while (line != null) //While there are more lines...
			{ 	
				
				String[] cols = line.split(",");  // Use comma as a separator.
				
				System.out.println();
				
				//For every column in line print header and data, as long as data doesn't equal "N/A".
				for (int i = 0; i < 23; i++) 
				{
					if (Login.getMembers().get(Login.getUserIndex()).getAdmin() == true) //If Admin then show all relevant data.
					{
							if (! cols[i].trim().equals("N/A")) 
							{
								System.out.println(headerCols[i].trim() + ": " + cols[i].trim());
							}
					}
					else //If not Admin then do not show loan/reservation data.
					{
						if (! cols[i].trim().equals("N/A") && i != 5 && i != 6 && i != 7 && i != 8) 
						{
							System.out.println(headerCols[i].trim() + ": " + cols[i].trim());
						}
						
					}
					
				}
				
				
				line = reader.readLine(); //read the next line.
			}
			
			file.close();			
		} 
		catch (IOException e) 
		{
				
			e.printStackTrace();
		}
	}
	
	//Method displays items that contain target string in their title.
	private void searchByTitle()
	{
		String title; //Stores the title to be searched for.
		Boolean notFound = true; //Tracks if the search target was found.
		String[] headerCols; //Stores column headers in an array.
		
		System.out.println("Please enter the title of the item you are looking for:");
		title = in.next(); //Acquiring target search string.
		
		//Read media.csv.
		try 
		{
			
			FileReader file = new FileReader("media.csv");
			BufferedReader br = new BufferedReader(file);
			
			String line = br.readLine(); //Reads the first line of the file (headers).
			headerCols = line.split(","); //Store the column headers for later use.
			line = br.readLine(); //Reads the second line of the file.
			
			while (line != null) 
			{
			   
			    String[] cols = line.split(",");  // Use comma as a separator.
			    
				if (cols[2].toLowerCase().contains(title.toLowerCase())) //Check if target title is contained in the Title column of the line. Column and target converted to lower case to allow inexact matches.
				{
					
					notFound = false;
					
					System.out.println();
					
					for (int i = 0; i < 23; i++) //For every column in line print header and data, as long as data doesn't equal "N/A".
					{
						if (Login.getMembers().get(Login.getUserIndex()).getAdmin() == true) //If Admin then show all relevant data.
						{
								if (! cols[i].trim().equals("N/A")) 
								{
									System.out.println(headerCols[i].trim() + ": " + cols[i].trim());
								}
						}
						else //If not Admin then do not show loan/reservation data.
						{
							if (! cols[i].trim().equals("N/A") && i != 5 && i != 6 && i != 7 && i != 8) 
							{
								System.out.println(headerCols[i].trim() + ": " + cols[i].trim());
							}
						}
						
					}

				}

			    line = br.readLine(); //read the next line.
			}
			
			file.close();
			
			if (notFound)
			{
				System.out.println("An item with that title could not be found." + "\n");
			}
			
		
		}
		catch (IOException e) 
		{
			e.printStackTrace();
			
		}
	}
	
	//Method used to reserve a media item. 
	private void reserveMedia() 
	{
		displayMedia(); //Show all media items so the user knows what they can reserve.
		System.out.println();
		
		int stockID; //Stores the Stock ID of the item to be reserved.
		String fullFile = ""; //Contains new data that will be used to overwrite media.csv.
		boolean reserved = false; //Tracks if reservation occurred. Otherwise file will not be written.
		Boolean notFound = true; //Tracks if the search target was found.
		String lastReservedID = ""; //Remembers the ID of the last item reserved. Used to inform user of the item that was dereserved.
		String lastReservedName = ""; //Remembers the name of the last item reserved. Used to inform user of the item that was dereserved.
		
		System.out.println("Please enter the Stock ID of the item you wish to reserve:");
		stockID = Integer.parseInt(in.next()); //Acquiring target Stock ID.
		
		//Read media.csv.
		try 
		{
			
			FileReader file1 = new FileReader("media.csv");
			BufferedReader br1 = new BufferedReader(file1);
			
			String line1 = br1.readLine(); //Reads the first line of the file (headers).
			fullFile = line1 + "\n"; //Writes headers to the variable, also clears it of prior data.
			line1 = br1.readLine(); //Reads the second line of the file.
			
			while (line1 != null) 
			{
			   
			    String[] cols = line1.split(",");  // Use comma as a separator.
			    
			  	//If the user has already reserved an item then unreserve it, unless it is target item. Must happen before check for stockID. 
			    if (cols[7].trim().equals(Integer.toString(Login.getMembers().get(Login.getUserIndex()).getUserID())) && ! cols[1].trim().equals(Integer.toString(stockID))) 
			    {
			    	cols[7] = "N/A"; //Reserver ID becomes N/A.
					cols[8]= "N/A"; //Reserve Date becomes N/A.
					
					lastReservedID =  cols[1].trim(); //Storing ID of item.
					lastReservedName = cols[2].trim(); //Storing name of item.
					
					line1 = ""; //Clear line.
					
					for (int i = 0; i < 23; i++) //Putting commas back between columns to fit CSV file format.
					{
						line1 += (cols[i] + ",");
					}
			    }
			    
			    //Check if the media line has the right Stock ID (correct item).
			    if (cols[1].trim().equals(Integer.toString(stockID))) 
				{
			    	
			    	notFound = false; //Target has been found.
			    	
			    	//Check if the item is available to loan.
				    if (cols[6].trim().equals("N/A")) 
				    {
				    	System.out.println("Item cannot be reserved as it is available for loan. Please loan the item instead.");
				    }
				    
				    //Check if item is overdue (it's been more than 10 days since it was loaned).
				    else if ((LocalDate.parse(cols[6].trim()).compareTo(LocalDate.now().minusDays(11))) < 0) 
				    {
				    	System.out.println("Item cannot be reserved as it is currently overdue and we don't know when it will be back.");
				    }
				    
				    //If Reserve Date is N/A or is less than current date then reserve item. 
				    else if (cols[8].trim().equals("N/A") || (LocalDate.parse(cols[8].trim()).compareTo(LocalDate.now())) < 0) 
					{

						//Add new data to media.csv contents.
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); //Date formatter needed for conversion to String. 
							
						reserved = true; //Item is reserved and new data should be written to file.
							
						cols[7] = Integer.toString(Login.getMembers().get(Login.getUserIndex()).getUserID()); //Reserver ID becomes current user.
						cols[8] = (LocalDate.parse(cols[6].trim()).plusDays(11)).format(formatter); //Reserve Date becomes Loan Date + 11.
						line1 = ""; //Clear line.
							
						for (int i = 0; i < 23; i++) //Putting commas back between columns to fit CSV file format.
						{
							line1 += (cols[i] + ",");
						}
							
						System.out.println("Item of Stock ID " + stockID + " successfully reserved for " + cols[8]);
						
						if (! lastReservedName.equals(""))
						{
							System.out.println(lastReservedName + "(ID: " + lastReservedID + ") has been unreserved as you are only allowed to reserve one item at a time.");
						}
							
					}
					
					else
					{
						System.out.println("Sorry, this item is already reserved.");
					}

				}

				fullFile += line1 + "\n"; //Write line to fullFile.
			    line1 = br1.readLine(); //read the next line.
			}
			
			file1.close();
			
			if (notFound)
			{
				System.out.println("An item with that Stock ID could not be found." + "\n");
			}
			
		
		}
		catch (IOException e) 
		{
			e.printStackTrace();
			
		}

		if (reserved == true)
		{
			writeFile("media.csv", fullFile); //Write file back to media.csv.
		}
		
		
	}
	
	//Method writes data to a .csv file. Accepts the file name and data to be written as parameters.
	public void writeFile(String csv, String data) 
	{
		
			try 	
			{
				File file = new File(csv);
				
				FileWriter input  = new FileWriter(file); 
						
				input.write(data); 
				
				input.close();
			}
				
			catch (IOException e) 
			{
				e.printStackTrace();
			}
	}
	
}
