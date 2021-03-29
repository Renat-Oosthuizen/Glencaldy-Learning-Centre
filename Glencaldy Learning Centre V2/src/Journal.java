//Completed on 06/01/2021 by Renat Oosthuizen
//Class is used for creating Journal objects.

import java.time.LocalDate;

public class Journal extends Media {

	private int ISSN; //Stores the ISSN of the journal.
	private String issueNumber; //Stores the issue number of the journal.
	private LocalDate dateOfIssue; //Stores the issue number of the journal.
	private String subjectArea; //Stores the subject area of the journal.
	private int numberOfPages; //Stores the number of pages in the journal.
	
	//Constructor
	Journal(int stockID, String title, double price, String publisher, String loaner, String loanDate, String reserverID, String reserveDate, int ISSN, String issueNumber, LocalDate dateOfIssue, String subjectArea, int numberOfPages)
	{
		setMediaType("Journal");
		setStockID(stockID);
		setTitle(title);
		setPrice(price);
		setPublisher(publisher);
		setLoaner(loaner);
		setLoanDate(loanDate);
		setReserverID(reserverID);
		setReserveDate(reserveDate);

		this.ISSN = ISSN;
		this.issueNumber = issueNumber;
		this.dateOfIssue = dateOfIssue;
		this.subjectArea = subjectArea;
		this.numberOfPages = numberOfPages;
	}
	
	//Setters
	public void setISSN(int ISSN) {
		
		this.ISSN = ISSN;
		
	}
	
	public void setIssueNumber(String issueNumber) {
		
		this.issueNumber = issueNumber;
		
	}
	
	public void setDateOfIssue(LocalDate dateOfIssue) {
		
		this.dateOfIssue = dateOfIssue;
		
	}
	
	public void setSubjectArea(String subjectArea) {
		
		this.subjectArea = subjectArea;
		
	}
	
	public void setNumberOfPages(int numberOfPages) {
		
		this.numberOfPages = numberOfPages;
		
	}
	
	//Getters
	public int getISSN() {
		
		return ISSN;
		
	}
	
	public String getIssueNumber() {
		
		return issueNumber;
		
	}
	
	public LocalDate getDateOfIssue() {
		
		return dateOfIssue;
		
	}
	
	public String getSubjectArea() {
		
		return subjectArea;
		
	}
	
	public int getNumberOfPages() {
		
		return numberOfPages;
		
	}
}
