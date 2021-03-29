# Glencaldy-Learning-Centre
This is a Java application with a console interface I made for a college software development project. The application is supposed to simulate a library that allows Admins to loan media items to members.

You can launch the “Glencaldy.jar” file from command line or terminal by navigating to the file location and typing “java -jar Glencaldy.jar” or by opening the project in a Java ready IDE such as Eclipse.
When launched, the application will create member.csv, media.csv and loans.csv files next to Glencaldy.jar or the bin folder. A session.txt file will also be generated on login. 

Login details for different account types:
  Full Member: User ID = 1, Password = 0001
  Casual Member: User ID = 4, Password = 0004
  Staff Member: User ID = 7, Password = 0007
  Admin Staff Member: User ID = 10, Password = 0010

Capabilities of different account types:
  Casual Members are able to view their login history, edit their account, view all stocks and search stocks by title.
  Full and Staff Members can have the same functionality as Casual Members, but can additionally view loan history and reserve items. Staff Members     are able to loan more items than Full Members.
  Admin Staff Members can do everything Staff Members can do as well as viewing and editing profiles of other members, editing media items, creating   new member and media profiles, loaning and returning loaned items and viewing login and loan history for all users.
  
Additional Information - Reservations: An item can only be reserved if it is currently loaned (loanerID != “N/A”) and it is not reserved by another user (reserverID = “N/A” or (reserveID = another User ID and reserveDate < Current Date)). A reservation cannot be made if the item is overdue to be returned. A user can only reserve 1 item at a time, any item previously reserved by that user will become unreserved. Reservation date is automatically assigned to the date the item was loaned + 11.

Additional Information - Loans: Items can only be loaned up to the loan limit for than membership type. Items cannot be loaned if the user has a fine. Items reserved by other members cannot be loaned (reserveDate > current date and reserverID != userID). Items cannot be loaned if the user has items on loan that have passed their return date (10 days).
