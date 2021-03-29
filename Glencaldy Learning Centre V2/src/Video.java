//Completed on 06/01/2021 by Renat Oosthuizen
//Class is used for creating Video objects.

public class Video extends Media {
	
	private int runningTime; //Stores the running time of the video in minutes.
	private String videoFormat; //Stores the format of the video.
	private String genre; //Stores the genre of the video.
	private String storageCase; //Stores the type of storage case that the video comes in.

	//Constructor
	Video(int stockID, String title, double price, String publisher, String loaner, String loanDate, String reserverID, String reserveDate, int runningTime, String videoFormat, String genre, String storageCase) 
	{
		setMediaType("Video");
		setStockID(stockID);
		setTitle(title);
		setPrice(price);
		setPublisher(publisher);
		setLoaner(loaner);
		setLoanDate(loanDate);
		setReserverID(reserverID);
		setReserveDate(reserveDate);

		this.runningTime = runningTime;
		this.videoFormat = videoFormat;
		this.genre = genre;
		this.storageCase = storageCase;
	}
	
	//Setters
	public void setRunningTime(int runningTime) {
		
		this.runningTime = runningTime;
		
	}
	
	public void setVideoFormat(String videoFormat) {
		
		this.videoFormat = videoFormat;
		
	}
	
	public void setGenre(String genre) {
		
		this.genre = genre;
		
	}
	
	public void setStorageCase(String storageCase) {
		
		this.storageCase = storageCase;
		
	}
	
	//Getters
	public int getRunningTime() {
		
		return runningTime;
		
	}
	
	public String getVideoFormat() {
		
		return videoFormat;
		
	}
	
	public String getGenre() {
		
		return genre;
		
	}
	
	public String getStorageCase() {
		
		return storageCase;
		
	}
}
