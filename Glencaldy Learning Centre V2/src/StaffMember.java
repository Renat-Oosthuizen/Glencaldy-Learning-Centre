//Completed on 06/01/2021 by Renat Oosthuizen
//Class is used for creating Staff Member objects.
//Inherits from Generic Member class.

import java.time.LocalDate;

public class StaffMember extends GenericMember {
	
	//Constructor
	StaffMember() 
	{
		setMemberType("Staff");
	}
	
	//Parameterised constructor
	StaffMember(String memberType, String firstName, String surname, String password, int userID, String address, String town, String postcode, String telNumber, LocalDate DOB, Boolean admin, double fine)
	{
		setMemberType(memberType);
		setFirstName(firstName);
		setSurname(surname);
		setPassword(password);
		setUserID(userID);
		setFine(fine);
		setAddress(address);
		setTown(town);
		setPostcode(postcode);
		setTelNumber(telNumber);
		setDOB(DOB);
		setAdmin(admin);
	}

}
