//Completed on 06/01/2021 by Renat Oosthuizen
//Class is used to create files with default data, create instances, validate login attempts and write login data to session.log .

import java.util.Scanner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;  
import java.util.LinkedList;
import java.util.Random; 
import java.time.LocalDate;
import java.time.LocalTime;

public class Login {
	
	private static LinkedList<GenericMember> members = new LinkedList<GenericMember>(); //Downcasted linked list storing multiple member instances.
	private static int userIndex; //Stores the index of the logged user.
	
	private static LinkedList<Media> media = new LinkedList<Media>(); //Downcasted linked list storing instances of all media.
	
	private static Scanner in = new Scanner (System.in); //Global scanner used for all input.
	private static Menu m1 = new Menu(); //Constructs a new instance of Menu, m1.
	
	//Main method where the application starts.
	public static void main(String[] args) 
	{
		startupCheck(); //Calls the startupCheck() method that will create files with default data.
		instantiateData(); //Creates instances of Media and Member objects.
		loginMenu(); //Calls the loginMenu() method that displays the login menu.
	}
	
	//Method that provides the login menu.
	public static void loginMenu() 
	{
		
		String menuItem; //Using String for the switch so that switch doesn't crash if non numbers are entered.
		boolean quit = false;
		
		do 
		{
		
			//Prints the menu.
			System.out.println("Welcome to the Glencaldy Learning Centre.");
	    	System.out.println("===========");    
	        System.out.println("Enter '1' to login"); 
	        System.out.println("Enter '0' to quit application.");
	        
	        menuItem = in.next();
	        
	        switch (menuItem) {
	        case "1":
	        	System.out.println();
	        	signin(); //Attempts to log the user into the system with the provided credentials.
	        	break;
	        case "0": 
	            quit = true; 
	            System.out.println();
	            System.out.println("Quitting application...");
	            break;
	        default: 
	        	System.out.println();
	        	System.out.println("Invalid choice. 0 to 1 only please.");
	        	System.out.println();
	        }
		} while (!quit);
		 
	in.close();
	System.exit(0);
	}
	
	//Method handles the login process.
	private static void signin() 
	{ 
		String pass; //Stores the entered Password.
		String user; //Stores the entered User ID.
		
		//Getting login details from user...
		System.out.println("Begining login processes...");
		System.out.println("Please enter your User ID:");
		user = in.next();
		System.out.println("Please enter your Password:");
		pass = in.next();
		
		//Runs through the linked list of member instances and check if in one of them the entered User ID and password matches.
		for(int i = 0; i < members.size(); i++) 
		{
			if (Integer.toString(members.get(i).getUserID()).equals(user) && members.get(i).getPassword().equals(pass)) //if match is found.
			{
				userIndex = i; //Tracks the position of the logged user object in the linked list.
				
				System.out.println("Login successful!");
				System.out.println();
				
				//Informs user of their membership type and if they are an admin.
				System.out.println("Your membership type is " + members.get(userIndex).getMemberType() + " Member.");
				if (members.get(userIndex).getAdmin() == true)
				{
					System.out.println("You are an Admin.");
				}
					
				recordSession(); //Records new login.
				
				m1.mainMenu(); //Call the mainMenu method from menu instance, m1.
			}
			else if (i == (members.size() - 1)) //Inform the user of a failed login attempt.
			{
				System.out.println("User ID or Password incorrect." + "\n");
			}		
		}
	}
	
	//Method writes data to session.log
	private static void recordSession() 
	{ 
		
		Random rand = new Random(); // Create instance of Random class - used for generating computerID.
			
		if (! new File("session.log").isFile()) //If file does not exist...
		{
			try 	
			{
				File file = new File("session.log");	//...then create it. Important as program will first attempt to read from file.
				file.createNewFile();
			}
				
			catch (IOException e) 
			{
				e.printStackTrace();
			}
				   
		}
			
		try 
		{
			   
			String fileContent = ""; //Variable that stores session.log contents
		
			FileReader file = new FileReader("session.log"); //Reader for session.log
			BufferedReader reader = new BufferedReader(file); //Buffered reader for session.log
		
			String line = reader.readLine(); //Variable stores first line of session.log
		
			while (line != null) //While line exists
			{ 
				if (line.equals("Logout Time: User is still logged in")) //and the line says that the user is still logged in...
				{ 
					fileContent += ("Logout Time: Unknown" + "\n"); //change it to Logout Time: Unknown and...
					line = reader.readLine(); //...read the next line.
				}
				else
				{
					fileContent += (line + "\n"); //...add line to fileContent and...
					line = reader.readLine(); //read the next line.
				}
			}
			file.close();
			   
			//Starting here, write back initial data to file and append new data.
			FileWriter input  = new FileWriter("session.log"); 
			
			DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("HH:mm:ss");  //Format used for time.
			String formattedTime = LocalTime.now().format(myFormatObj);  //Variable stores formatted time.
					
			input.write(fileContent + "\n"); //Note: "\n" here create spaces between entries
			input.write("UserID: " + members.get(userIndex).getUserID() + "\n");
			input.write("ComputerID: " + rand.nextInt(10) + "\n");
			input.write("Date: " + LocalDate.now() + "\n");
			input.write("Time: " + formattedTime + "\n"); 
			input.write("Logout Time: User is still logged in"); 
					
			input.close();		
		} 
		catch (IOException e) 
		{
				
			e.printStackTrace();
		}
	}
	
	//Method checks if .csv files exists and creates them if they do not while writing default data to them.
	private static void startupCheck() 
	{
		
		if (! new File("member.csv").isFile()) //If member.csv does not exist...
		{
			try 	
			{
				File member = new File("member.csv");	//...then create it with default data.
				
				String member1 = "Member Type, First Name, Surname, Password, User ID, Address, Town, Post Code, Tel Number, DOB, Admin, Fine";
				String member2 = "Full, Alan, Alanson, 0001, 1, Sand Fort, Kirkcaldy, KY1 1YK, 01224 123456, 1990-06-05, false, 0.0";
				String member3 = "Full, Bob, Bobson, 0002, 2, Sand Fort, Kirkcaldy, KY1 1YK, 01224 133456, 1990-06-05, false, 0.00";
				String member4 = "Full, Catherine, Catherinedottir, 0003, 3, Sand Fort, Kirkcaldy, KY1 1YK, 01224 143456, 1990-06-05, false, 0.00";
				String member5 = "Casual, Declan, Declanson, 0004, 4, Sand Fort, Kirkcaldy, KY1 1YK, 01224 153456, 1990-06-05, false, 0.00";
				String member6 = "Casual, Evaline, Evalinedottir, 0005, 5, Sand Fort, Kirkcaldy, KY1 1YK, 01224 163456, 1990-06-05, false, 0.00";
				String member7 = "Casual, Freya, Freyadottir, 0006, 6, Sand Fort, Kirkcaldy, KY1 1YK, 01224 173456, 1990-06-05, false, 0.00";
				String member8 = "Staff, Gunnar, Gunnarson, 0007, 7, Sand Fort, Kirkcaldy, KY1 1YK, 01224 183456, 1990-06-05, false, 0.00";
				String member9 = "Staff, Hugh, Hughson, 0008, 8, Sand Fort, Kirkcaldy, KY1 1YK, 01224 193456, 1990-06-05, false, 0.00";
				String member10 = "Staff, Isla, Isladottir, 0009, 9, Sand Fort, Kirkcaldy, KY1 1YK, 01224 203456, 1990-06-05, false, 0.00";
				String member11 = "Staff, Jeoffery, Jeofferyson, 0010, 10, Sand Fort, Kirkcaldy, KY1 1YK, 01224 213456, 1990-06-05, true, 0.00";
				
				FileWriter input  = new FileWriter(member); //Write default data to file.
						
				input.write(member1 + "\n"); 
				input.write(member2 + "\n"); 
				input.write(member3 + "\n"); 
				input.write(member4 + "\n"); 
				input.write(member5 + "\n"); 
				input.write(member6 + "\n"); 
				input.write(member7 + "\n"); 
				input.write(member8 + "\n");
				input.write(member9 + "\n");
				input.write(member10 + "\n");
				input.write(member11 + "\n");
		
				input.close();
			}
			catch (IOException e) 
			{
				e.printStackTrace();
			}
				   
		}
		
		if (! new File("media.csv").isFile()) //If media.csv does not exist...
		{
			try 	
			{
				File media = new File("media.csv");	//...then create it with default data.
				
				String media1 = "Media Type, Stock ID, Title, Price, Publisher, Loaner ID, Loan Date, Reserver ID, Reserved Date, ISBN, Author, Subject Area, Number of Pages, ISSN, Issue Number, Date of Issue, Running Time, Video Format, Genre, Storage Case, CD Type, Number of Tracks, Artist";
				String media2 = "Book, 1, A Dance with Dragons, 5.99, Harper Collins, 1, 2020-12-24, N/A, N/A, 1111222111, George R. R. Martin, Fantasy, 1000, N/A, N/A, N/A, N/A, N/A, N/A, N/A, N/A, N/A, N/A";
				String media3 = "Book, 2, How to Train your Dragon, 6.99, Penguin, 1, 2020-12-24, N/A, N/A, 1111222112, Cressida Cowell, Fantasy, 1000, N/A, N/A, N/A, N/A, N/A, N/A, N/A, N/A, N/A, N/A";
				String media4 = "Book, 3, A Game of Thrones, 9.99, Harper Collins, 2, 2020-12-23, N/A, N/A, 1111222113, George R. R. Martin, Fantasy, 1000, N/A, N/A, N/A, N/A, N/A, N/A, N/A, N/A, N/A, N/A";
				String media5 = "Journal, 4, Nature Cell Biology, 5.99, Nature, 3, 2020-12-25, N/A, N/A, N/A, N/A, Bilogical Science, 50, 1111222111, Vol 22 Issue 10, 2020-10-01, N/A, N/A, N/A, N/A, N/A, N/A, N/A";
				String media6 = "Journal, 5, Nature Cell Biology, 5.99, Nature, 4, 2020-12-25, N/A, N/A, N/A, N/A, Bilogical Science, 50, 1111222112, Vol 22 Issue 11, 2020-11-01, N/A, N/A, N/A, N/A, N/A, N/A, N/A";
				String media7 = "Journal, 6, Nature Cell Biology, 5.99, Nature, 5, 2020-12-25, N/A, N/A, N/A, N/A, Bilogical Science, 50, 1111222113, Vol 22 Issue 12, 2020-12-01, N/A, N/A, N/A, N/A, N/A, N/A, N/A";
				String media8 = "Video, 7, Fifth Element, 5.99, Gaumont, 6, 2020-12-20, N/A, N/A, N/A, N/A, N/A, N/A, N/A, N/A, N/A, 126, DVD, Sci-Fi Action, Plastic, N/A, N/A, N/A";
				String media9 = "Video, 8, Interstellar, 5.99, Paramount Pictures, 7, 2020-12-20, N/A, N/A, N/A, N/A, N/A, N/A, N/A, N/A, N/A, 169, Blue-Ray, Epic Sci-Fi, Plastic, N/A, N/A, N/A";
				String media10 = "Video, 9, The Imitation Game, 8.99, The Weinstein Company, 9, 2020-12-20, N/A, N/A, N/A, N/A, N/A, N/A, N/A, N/A, N/A, 114, DVD, Historical Drama, Metal, N/A, N/A, N/A";
				String media11 = "Compact Disk, 10, The Rolling Stones No. 2, 6.99, ABKCO, 10, 2020-12-24, N/A, N/A, N/A, N/A, N/A, N/A, N/A, N/A, N/A, 36, N/A, N/A, Plastic, Traditional CD, 12, Rolling Stones";
				String media12 = "Compact Disk, 11, The Slim Shady LP, 5.99, Aftermath Entertainment, 2, 2020-12-25, N/A, N/A, N/A, N/A, N/A, N/A, N/A, N/A, N/A, 59, N/A, N/A, Plastic, Traditional CD, 20, Eminem";
				String media13 = "Compact Disk, 12, A Thousand Suns, 4.99, Warner Bros, N/A, N/A, N/A, N/A, N/A, N/A, N/A, N/A, N/A, N/A, N/A, 47, N/A, N/A, Plastic, Traditional CD, 15, Linkin Park";
				
				FileWriter input  = new FileWriter(media); //Write default data to file.
						
				input.write(media1 + "\n");
				input.write(media2 + "\n");
				input.write(media3 + "\n");
				input.write(media4 + "\n");
				input.write(media5 + "\n");
				input.write(media6 + "\n");
				input.write(media7 + "\n");
				input.write(media8 + "\n");
				input.write(media9 + "\n");
				input.write(media10 + "\n");
				input.write(media11 + "\n");
				input.write(media12 + "\n");
				input.write(media13 + "\n");

				input.close();
			}	
			catch (IOException e) 
			{
				e.printStackTrace();
			}	   
		}
		
		if (! new File("loans.csv").isFile()) //If loans.csv does not exist...
		{
			try 	
			{
				File loans = new File("loans.csv");	//...then create it with default data.
				
				String loans1 = "User ID, Stock ID, Title, Loan Date, Return Date";
				String loans2 = "10,12,A Thousand Suns,2020-01-01,2020-01-02";
				String loans3 = "1,1,A Dance with Dragons,2020-12-24";
				String loans4 = "1,2,How to Train your Dragon,2020-12-24";
				String loans5 = "2,3,A Game of Thrones,2020-12-23";
				String loans6 = "3,4,Nature Cell Biology,2020-12-25";
				String loans7 = "4,5,Nature Cell Biology,2020-12-25";
				String loans8 = "5,6,Nature Cell Biology,2020-12-25";
				String loans9 = "6,7,Fifth Element,2020-12-20";
				String loans10 = "7,8,Interstellar,2020-12-20";
				String loans11 = "9,9,The Imitation Game,2020-12-20";
				String loans12 = "10,10,The Rolling Stones No. 2,2020-12-24";
				String loans13 = "2,11,The Slim Shady LP,2020-12-25";
				
				FileWriter input  = new FileWriter(loans); //Write default data to file.
						
				input.write(loans1 + "\n");
				input.write(loans2 + "\n");
				input.write(loans3 + "\n");
				input.write(loans4 + "\n");
				input.write(loans5 + "\n");
				input.write(loans6 + "\n");
				input.write(loans7 + "\n");
				input.write(loans8 + "\n");
				input.write(loans9 + "\n");
				input.write(loans10 + "\n");
				input.write(loans11 + "\n");
				input.write(loans12 + "\n");
				input.write(loans13 + "\n");

				input.close();
			}
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	//Method reads the .csv files and used them to create object instances
	private static void instantiateData() 
	{
		//Reading the member.csv file and storing them as instances in an upcasted linked list. 
		try 
		{
			FileReader file = new FileReader("member.csv");
			BufferedReader br = new BufferedReader(file);
			
			String line = br.readLine(); //Reads the first line of the file.
			line = br.readLine(); //Reads the second line of the file (first line is headers).
			
			while (line != null) 
			{
			    String[] cols = line.split(",");  // Use comma to separate each column of the line.
			    
			    //Creating member instances...
			    if (cols[0].trim().equals("Full"))
				{
					FullMember fullMember = new FullMember(cols[0].trim(), cols[1].trim(), cols[2].trim(), cols[3].trim(), Integer.parseInt(cols[4].trim()), cols[5].trim(), cols[6].trim(), cols[7].trim(), cols[8].trim(), LocalDate.parse(cols[9].trim()), Double.parseDouble(cols[11].trim())); //Creating a new instance of member for the user.
					members.add(fullMember);
					
				}
				else if (cols[0].trim().equals("Casual"))
				{
					CasualMember casualMember = new CasualMember(cols[0].trim(), cols[1].trim(), cols[2].trim(), cols[3].trim(), Integer.parseInt(cols[4].trim()), cols[5].trim(), cols[6].trim(), cols[7].trim(), cols[8].trim(), LocalDate.parse(cols[9].trim()), Double.parseDouble(cols[11].trim())); //Creating a new instance of member for the user.
					members.add(casualMember);

				}
				else if (cols[0].trim().equals("Staff"))
				{
					StaffMember staffMember = new StaffMember(cols[0].trim(), cols[1].trim(), cols[2].trim(), cols[3].trim(), Integer.parseInt(cols[4].trim()), cols[5].trim(), cols[6].trim(), cols[7].trim(), cols[8].trim(), LocalDate.parse(cols[9].trim()), Boolean.valueOf(cols[10].trim()), Double.parseDouble(cols[11].trim())); //Creating a new instance of member for the user.
					members.add(staffMember);
					
				}

			    line = br.readLine(); //read the next line.
			}
			
			file.close();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
			
		}
		
		//Reading the media.csv file and storing them as instances in linked lists. 
		try 
		{
			FileReader file = new FileReader("media.csv");
			BufferedReader br = new BufferedReader(file);
			
			String line = br.readLine(); //Reads the first line of the file.
			line = br.readLine(); //Reads the second line of the file (first line is headers).
			
			while (line != null) 
			{
			    String[] cols = line.split(",");  // Use comma to separate each column of the line.
			    
			    //Creating member instances...
			    if (cols[0].trim().equals("Book"))
				{
					Book bookMedia = new Book(Integer.parseInt(cols[1].trim()), cols[2].trim(), Double.parseDouble(cols[3].trim()), cols[4].trim(), cols[5].trim(), cols[6].trim(), cols[7].trim(), cols[8].trim(), Integer.parseInt(cols[9].trim()), cols[10].trim(), cols[11].trim(), Integer.parseInt(cols[12].trim())); //Creating a new instance of book..
					media.add(bookMedia);
					
				}
				else if (cols[0].trim().equals("Compact Disk"))
				{
					CompactDisk diskMedia = new CompactDisk(Integer.parseInt(cols[1].trim()), cols[2].trim(), Double.parseDouble(cols[3].trim()), cols[4].trim(), cols[5].trim(), cols[6].trim(), cols[7].trim(), cols[8].trim(), Integer.parseInt(cols[16].trim()), cols[20].trim(), Integer.parseInt(cols[21].trim()), cols[22].trim(), cols[19].trim()); //Creating a new instance of compact disk..
					media.add(diskMedia);
				}
				else if (cols[0].trim().equals("Journal"))
				{
					Journal journalMedia = new Journal(Integer.parseInt(cols[1].trim()), cols[2].trim(), Double.parseDouble(cols[3].trim()), cols[4].trim(), cols[5].trim(), cols[6].trim(), cols[7].trim(), cols[8].trim(), Integer.parseInt(cols[13].trim()), cols[14].trim(), LocalDate.parse(cols[15].trim()), cols[11].trim(), Integer.parseInt(cols[12].trim())); //Creating a new instance of journal..
					media.add(journalMedia);
					
				}
				else if (cols[0].trim().equals("Video"))
				{
					Video videoMedia = new Video(Integer.parseInt(cols[1].trim()), cols[2].trim(), Double.parseDouble(cols[3].trim()), cols[4].trim(), cols[5].trim(), cols[6].trim(), cols[7].trim(), cols[8].trim(), Integer.parseInt(cols[16].trim()), cols[17].trim(), cols[18].trim(), cols[19].trim()); //Creating a new instance of video.
					media.add(videoMedia);
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
	
	public static int getUserIndex() //Getter for the userIndex.
	{
		return userIndex;
	}
	
	public static LinkedList<GenericMember> getMembers() //Getter for linked list of member instances, members.
	{
		return members;
	}
	
	public static Menu getMenu() //Getter for Menu instance, m1.
	{
		return m1;
	}
}