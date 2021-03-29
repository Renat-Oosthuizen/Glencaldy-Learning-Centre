//Completed on 06/01/2021 by Renat Oosthuizen
//Class is used for creating Book objects.

public class Book extends Media {
	
	private int ISBN; //Stores ISBN of the book.
	private String author; //Stores the name of the author of the book.
	private String subjectArea; //Stores the subject area of the book.
	private int numberOfPages; //Stores the number of pages in the book.
	
	//Constructor
	Book(int stockID, String title, double price, String publisher, String loaner, String loanDate, String reserverID, String reserveDate, int ISBN, String author, String subjectArea, int numberOfPages)
	{
		setMediaType("Book");
		setStockID(stockID);
		setTitle(title);
		setPrice(price);
		setPublisher(publisher);
		setLoaner(loaner);
		setLoanDate(loanDate);
		setReserverID(reserverID);
		setReserveDate(reserveDate);

		this.ISBN = ISBN;
		this.author = author;
		this.subjectArea = subjectArea;
		this.numberOfPages = numberOfPages;
	}
	
	//Setters
	public void setISBN(int ISBN) {
		
		this.ISBN = ISBN;
		
	}
	
	public void setAuthor(String author) {
		
		this.author = author;
		
	}
	
	public void setSubjectArea(String subjectArea) {
		
		this.subjectArea = subjectArea;
		
	}
	
	public void setNumberOfPages(int numberOfPages) {
		
		this.numberOfPages = numberOfPages;
		
	}
	
	//Getters
	public int getISBN() {
		
		return ISBN;
		
	}
	
	public String getAuthor() {
		
		return author;
		
	}
	
	public String getSubjectArea() {
		
		return subjectArea;
		
	}
	
	public int getNumberOfPages() {
		
		return numberOfPages;
		
	}
}
