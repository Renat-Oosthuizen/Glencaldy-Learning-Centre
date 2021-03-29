//Completed on 06/01/2021 by Renat Oosthuizen
//Class is inherited from by Book, CompactDisk, Journal and Video classes.

abstract class Media {

	private String mediaType; //Stores the type of media.
	private int stockID; //Stores the Stock ID of the media.
	private String title; //Stores the title of the media.
	private double price; //Stores the price of the media.
	private String publisher; //Stores the publisher of the media.
	private String loaner; //Stores the User ID of the loaner of the media.
	private String loanDate; //Stores the last date that the media was loaned on.
	private String reserverID; //Stores the User ID of the member that reserved the media.
	private String reserveDate; //Stores the date that the media is reserved for.
	
	//Setters
	public void setMediaType(String mediaType) {
		
		this.mediaType = mediaType;
		
	}
	
	public void setStockID(int stockID) {
		
		this.stockID = stockID;
		
	}
	
	public void setTitle(String title) {
		
		this.title = title;
		
	}
	
	public void setPrice(double price) {
		
		this.price = price;
		
	}
	
	public void setPublisher(String publisher ) {
		
		this.publisher = publisher;
		
	}
	
	
	public void setLoaner(String loaner) {
		
		this.loaner = loaner;
		
	}
	
	public void setLoanDate(String loanDate) {
		
		this.loanDate = loanDate;
		
	}
	
	public void setReserverID(String reserverID) {
		
		this.reserverID = reserverID;
		
	}
	
	public void setReserveDate(String reserveDate) {
		
		this.reserveDate = reserveDate;
		
	}
	
	//Getters
	public String getMediaType() {
		
		return mediaType;
		
	}
	
	public int getStockID() {
		
		return stockID;
		
	}
	
	public String getTitle() {
		
		return title;
		
	}
	
	public double getPrice() {
		
		return price;
		
	}
	
	public String getPublisher() {
		
		return publisher;
		
	}
	
	public String getLoaner() {
		
		return loaner;
		
	}
	
	public String getLoanDate() {
		
		return loanDate;
		
	}
	
	public String getReserverID() {
		
		return reserverID;
		
	}
	
	public String getReserveDate() {
		
		return reserveDate;
		
	}
	
}
