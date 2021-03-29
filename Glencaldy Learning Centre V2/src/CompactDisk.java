//Completed on 06/01/2021 by Renat Oosthuizen
//Class is used for creating Compact Disk objects.

public class CompactDisk extends Media {

	private int runningTime; //Stores the running time of the CD in minutes.
	private String CDType; //Stores what type of CD it is.
	private int numberOfTracks; //Stores the number of tracks on the CD.
	private String artist; //Stores the artist of the CD.
	private String storageCase; //Stores the storage case that the CD comes in.
		
	//Constructor
	CompactDisk(int stockID, String title, double price, String publisher, String loaner, String loanDate, String reserverID, String reserveDate, int runningTime, String CDType, int numberOfTracks, String artist, String storageCase)
	{
		setMediaType("Compact Disk");
		setStockID(stockID);
		setTitle(title);
		setPrice(price);
		setPublisher(publisher);
		setLoaner(loaner);
		setLoanDate(loanDate);
		setReserverID(reserverID);
		setReserveDate(reserveDate);

		this.runningTime = runningTime;
		this.CDType = CDType;
		this.numberOfTracks = numberOfTracks;
		this.artist = artist;
		this.storageCase = storageCase;
	}
	
	//Setters
	public void setRunningTime(int runningTime) {
		
		this.runningTime = runningTime;
		
	}
	
	public void setCDType(String CDType) {
		
		this.CDType = CDType;
		
	}
	
	public void setNumberOfTracks(int numberOfTracks) {
		
		this.numberOfTracks = numberOfTracks;
		
	}
	
	public void setArtist(String artist) {
		
		this.artist = artist;
		
	}
	
	public void setStorageCase(String storageCase) {
		
		this.storageCase = storageCase;
		
	}
	
	//Getters
	public int getRunningTime() {
		
		return runningTime;
		
	}
	
	public String getCDType() {
		
		return CDType;
		
	}
	
	public int getNumberOfTracks() {
		
		return numberOfTracks;
		
	}
	
	public String getArtist() {
		
		return artist;
		
	}
	
	public String getStorageCase() {
		
		return storageCase;
		
	}
}
