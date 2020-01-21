package assign02;

import java.util.GregorianCalendar;

public class LibraryBook extends Book {
	
	private String holder;
	
	private GregorianCalendar dueDate;
	
	public LibraryBook(long isbn, String author, String title) {
		super(isbn, author, title);
		holder = null;
		dueDate = null;
		
	}
	
	/**
     * Accessor method for the holder field
     * 
     * @return the holder
     */
	public String getHolder() {
		return holder;
	}
	
	public GregorianCalendar getDueDate() {
		return dueDate;
	}
	
	/**
	 * Sets the holder and due date to null which represents the LibraryBook as being checked in
	 */
	public void checkIn() {
		holder = null;
		dueDate = null;
	}
	
	/**
	 * Sets the due date and the holder
	 * 
	 * @param holder - the holder checking out the book
	 * @param month - month of due date
	 * @param day - day of due date
	 * @param year - year of due date
	 */
	public void checkOut(String holder, int month, int day, int year) {
		// TODO Auto-generated method stub
		dueDate = new GregorianCalendar(year, month, day, 0, 0, 0);
    	this.holder = holder;
	}


	
	
	

}
