//Completed on 06/01/2021 by Renat Oosthuizen
//Class is used for creating Casual Member objects.
//Inherits from Generic Member class.

import java.time.LocalDate;

public class CasualMember extends GenericMember {

	//Constructor
	CasualMember() 
	{
		setMemberType("Casual");
		setAllowedBorrows(0);

	}
	
	//Parameterised constructor
	CasualMember(String memberType, String firstName, String surname, String password, int userID, String address, String town, String postcode, String telNumber, LocalDate DOB, double fine)
	{
		setMemberType(memberType);
		setFirstName(firstName);
		setSurname(surname);
		setPassword(password);
		setUserID(userID);
		setFine(fine);
		setAllowedBorrows(0);
		setAddress(address);
		setTown(town);
		setPostcode(postcode);
		setTelNumber(telNumber);
		setDOB(DOB);
		setFine(fine);
	}
	
}
