//Completed on 06/01/2021 by Renat Oosthuizen
//Class is inherited from by CasualMember, FullMember and StaffMember classes.

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

abstract class GenericMember {

	private String memberType; //Stores membership type of the member.
	private String firstName; //Stores first name of the member.
	private String surname; //Stores surname of the member.
	private String password; //Stores password of the member.
	private int userID; //Stores userID of the member.
	private int allowedBorrows = 6; //Stores the number of items that the member is allowed to loan.
	private String address; //Stores address of the member.
	private String town; //Stores town where the member lives.
	private String postcode; //Stores post code of the member.
	private String telNumber; //Stores telephone number of the member.
	private LocalDate DOB; //Stores date of birth of the member.
	private Boolean admin = false; //Stores information on whether the member is an administrator.
	private double fine = 0.00; //Stores the monetary fines that the member has accrued through late returns. 
	
	//Setters
	public void setMemberType(String memberType) {
		
		this.memberType = memberType;
		
	}
	
	public void setFirstName(String firstName) {
		
		this.firstName = firstName;
		
	}
	
	public void setSurname(String surname) {
		
		this.surname = surname;
		
	}
	
	public void setPassword(String password) {
		
		this.password = password;
		
	}
	
	public void setUserID(int userID) {
		
		this.userID = userID;
		
	}
	
	public void setFine(double fine) {
		
		this.fine = fine;
	}
	
	public void setAllowedBorrows(int allowedBorrows) {
		
		this.allowedBorrows = allowedBorrows;
		
	}
	
	public void setAddress(String address) {
		
		this.address = address;
		
	}
	
	public void setTown(String town) {
		
		this.town = town;
		
	}
	
	public void setPostcode(String postcode) {
		
		this.postcode = postcode;
		
	}
	
	public void setTelNumber(String telNumber) {
		
		this.telNumber = telNumber;
		
	}
	
	public void setDOB(LocalDate DOB) {
		
		this.DOB = DOB;
		
	}
	
	public void setAdmin(Boolean admin)
	{
		this.admin = admin;
	}
	
	//Getters
	public String getMemberType() { 
		
		return memberType;
	}
	
	public String getFirstName() {
		
		return firstName;
		
	}
	
	public String getSurname() {
		
		return surname;
		
	}
	
	public String getPassword() {
		
		return password;
		
	}
	
	public int getUserID() {
		
		return userID;
		
	}
	
	
	public double getFine() {
		
		return fine;
		
	}
	
	public int getAllowedBorrows() {
		
		return allowedBorrows;
		
	}
	
	public String getAddress() {
		
		return address;
		
	}
	
	public String getTown() {
		
		return town;
		
	}
	
	public String getPostcode() {
		
		return postcode;
		
	}
	
	public String getTelNumber() {
		
		return telNumber;
		
	}
	
	public LocalDate getDOB() {
		
		return DOB;
		
	}
	
	public Boolean getAdmin()
	{
		return admin;
	}
	
	
	//Function is used to write a member object to a member.csv file to keep it persistent.
	public void writeMember() {
		
		String newMember = ""; //Stores all the data of the member object in a single comma separated line.
		
		//Reading and storing initial data from member.csv in newMember variable.
		try 
		{
			
			FileReader file = new FileReader("member.csv");
			BufferedReader br = new BufferedReader(file);
			
			String line = br.readLine(); //Reads the first line of the file.
			newMember = "";
			
			while (line != null) 
			{
			  
				newMember += line + "\n";
			    line = br.readLine(); //read the next line.
			}
			
			file.close();
		
		}
		catch (IOException e) 
		{
			e.printStackTrace();
			
		}
		
		//Adding data from the object to newMember.
		newMember += memberType + ",";
		newMember += firstName + ",";
		newMember += surname + ",";
		newMember += password + ",";
		newMember += userID + ",";
		newMember += address + ",";
		newMember += town + ",";
		newMember += postcode + ",";
		newMember += telNumber + ",";
		newMember += DOB + ",";
		newMember += admin + ",";
		newMember += fine;
		
		//Overwriting member.csv with data in newMember.
		try 	
		{

			
			FileWriter input  = new FileWriter("member.csv");
					
			input.write(newMember);
					
			input.close();
		}
			
		catch (IOException e) 
		{
			e.printStackTrace();
		}

	}
}
