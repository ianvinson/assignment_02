package assign02;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * This class contains tests for LibraryGeneric.
 * 
 * @author Erin Parker and ??
 * @version January 16, 2020
 */
public class LibraryGenericTester {

	private LibraryGeneric<String> nameLib; // library that uses names to identify patrons (holders)
	private LibraryGeneric<PhoneNumber> phoneLib; // library that uses phone numbers to identify patrons
	LibraryBookGeneric<String> book1, book2, book3;

	@BeforeEach
	void setUp() throws Exception {
		book1 = new LibraryBookGeneric<String>(9780374292799L, "Thomas L. Friedman", "The World is Flat");
		book2 = new LibraryBookGeneric<String>(9780330351690L, "Jon Krakauer", "Into the Wild");
		book3 = new LibraryBookGeneric<String>(9780446580342L, "David Baldacci", "Simple Genius");

		nameLib = new LibraryGeneric<String>();

		nameLib.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
		nameLib.add(9780330351690L, "Jon Krakauer", "Into the Wild");
		nameLib.add(9780446580342L, "David Baldacci", "Simple Genius");

		phoneLib = new LibraryGeneric<PhoneNumber>();
		phoneLib.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
		phoneLib.add(9780330351690L, "Jon Krakauer", "Into the Wild");
		phoneLib.add(9780446580342L, "David Baldacci", "Simple Genius");
	}

	@Test
	public void testNameLibCheckout() {
		String patron = "Jane Doe";
		assertTrue(nameLib.checkout(9780330351690L, patron, 1, 1, 2008));
		assertTrue(nameLib.checkout(9780374292799L, patron, 1, 1, 2008));
	}

	@Test
	public void testNameLibLookup() {
		String patron = "Jane Doe";
		nameLib.checkout(9780330351690L, patron, 1, 1, 2008);
		nameLib.checkout(9780374292799L, patron, 1, 1, 2008);
		ArrayList<LibraryBookGeneric<String>> booksCheckedOut = nameLib.lookup(patron);

		assertNotNull(booksCheckedOut);
		assertEquals(2, booksCheckedOut.size());
		assertTrue(booksCheckedOut.contains(new Book(9780330351690L, "Jon Krakauer", "Into the Wild")));
		assertTrue(booksCheckedOut.contains(new Book(9780374292799L, "Thomas L. Friedman", "The World is Flat")));
		assertEquals(patron, booksCheckedOut.get(0).getHolder());
		assertEquals(patron, booksCheckedOut.get(1).getHolder());
	}

	@Test
	public void testNameLibCheckin() {
		String patron = "Jane Doe";
		nameLib.checkout(9780330351690L, patron, 1, 1, 2008);
		nameLib.checkout(9780374292799L, patron, 1, 1, 2008);
		assertTrue(nameLib.checkin(patron));
	}

	@Test
	public void testPhoneLibCheckout() {
		PhoneNumber patron = new PhoneNumber("801.555.1234");
		assertTrue(phoneLib.checkout(9780330351690L, patron, 1, 1, 2008));
		assertTrue(phoneLib.checkout(9780374292799L, patron, 1, 1, 2008));
	}

	@Test
	public void testPhoneLibLookup() {
		PhoneNumber patron = new PhoneNumber("801.555.1234");
		phoneLib.checkout(9780330351690L, patron, 1, 1, 2008);
		phoneLib.checkout(9780374292799L, patron, 1, 1, 2008);
		ArrayList<LibraryBookGeneric<PhoneNumber>> booksCheckedOut = phoneLib.lookup(patron);

		assertNotNull(booksCheckedOut);
		assertEquals(2, booksCheckedOut.size());
		assertTrue(booksCheckedOut.contains(new Book(9780330351690L, "Jon Krakauer", "Into the Wild")));
		assertTrue(booksCheckedOut.contains(new Book(9780374292799L, "Thomas L. Friedman", "The World is Flat")));
		assertEquals(patron, booksCheckedOut.get(0).getHolder());
		assertEquals(patron, booksCheckedOut.get(1).getHolder());
	}

	@Test
	public void testPhoneLibCheckin() {
		PhoneNumber patron = new PhoneNumber("801.555.1234");
		phoneLib.checkout(9780330351690L, patron, 1, 1, 2008);
		phoneLib.checkout(9780374292799L, patron, 1, 1, 2008);
		assertTrue(phoneLib.checkin(patron));
	}

	@Test
	public void testGetInventoryList() {
		ArrayList<LibraryBookGeneric<String>> expectedInventoryList = new ArrayList<LibraryBookGeneric<String>>();
		expectedInventoryList.add(book2);
		expectedInventoryList.add(book1);
		expectedInventoryList.add(book3);

		ArrayList<LibraryBookGeneric<String>> books = nameLib.getOrderedByTitle();
		assertEquals(expectedInventoryList, books);
	}

	@Test
	public void testGetOverdueList() {
		ArrayList<LibraryBookGeneric<String>> expectedOverDueList = new ArrayList<LibraryBookGeneric<String>>();
		expectedOverDueList.add(book3);
		expectedOverDueList.add(book1);
		expectedOverDueList.add(book2);

		String patron = "Jane Doe";
		nameLib.checkout(9780374292799L, patron, 1, 5, 2008);
		nameLib.checkout(9780330351690L, patron, 2, 1, 2008);
		nameLib.checkout(9780446580342L, patron, 12, 1, 2007);

		ArrayList<LibraryBookGeneric<String>> overDueList = nameLib.getOverdueList(6, 1, 2008);

		assertEquals(expectedOverDueList, overDueList);
	}

	@Test
	public void testGetOrderedByTitle() {
		ArrayList<LibraryBookGeneric<String>> expectedOrderedByTitleList = new ArrayList<LibraryBookGeneric<String>>();
		expectedOrderedByTitleList.add(book2);
		expectedOrderedByTitleList.add(book3);
		expectedOrderedByTitleList.add(book1);

		ArrayList<LibraryBookGeneric<String>> books = nameLib.getOrderedByTitle();
		assertEquals(expectedOrderedByTitleList, books);
	}
}
