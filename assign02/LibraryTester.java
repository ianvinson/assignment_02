package assign02;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * This class contains tests for Library.
 * 
 * @author Erin Parker and Ian Vinson and Shengke Xu
 * @version January 22, 2020
 */
public class LibraryTester {

	private Library emptyLib, smallLib, mediumLib;

	@BeforeEach
	void setUp() throws Exception {
		emptyLib = new Library();

		smallLib = new Library();
		smallLib.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
		smallLib.add(9780330351690L, "Jon Krakauer", "Into the Wild");
		smallLib.add(9780446580342L, "David Baldacci", "Simple Genius");

		mediumLib = new Library();
		mediumLib.addAll("src/assign02/Mushroom_Publishing.txt");
	}

	@Test
	public void testEmptyLookupISBN() {
		assertNull(emptyLib.lookup(978037429279L));
	}

	@Test
	public void testEmptyLookupHolder() {
		ArrayList<LibraryBook> booksCheckedOut = emptyLib.lookup("Jane Doe");
		assertNotNull(booksCheckedOut);
		assertEquals(0, booksCheckedOut.size());
	}

	@Test
	public void testEmptyCheckout() {
		assertFalse(emptyLib.checkout(978037429279L, "Jane Doe", 1, 1, 2008));
	}

	@Test
	public void testEmptyCheckinISBN() {
		assertFalse(emptyLib.checkin(978037429279L));
	}

	@Test
	public void testEmptyCheckinHolder() {
		assertFalse(emptyLib.checkin("Jane Doe"));
	}

	@Test
	public void testSmallLibraryLookupISBN() {
		assertNull(smallLib.lookup(9780330351690L));
	}

	@Test
	public void testSmallLibraryLookupHolder() {
		smallLib.checkout(9780330351690L, "Jane Doe", 1, 1, 2008);
		ArrayList<LibraryBook> booksCheckedOut = smallLib.lookup("Jane Doe");

		assertNotNull(booksCheckedOut);
		assertEquals(1, booksCheckedOut.size());
		assertEquals(new Book(9780330351690L, "Jon Krakauer", "Into the Wild"), booksCheckedOut.get(0));
		assertEquals("Jane Doe", booksCheckedOut.get(0).getHolder());
	}

	@Test
	public void testSmallLibraryCheckout() {
		assertTrue(smallLib.checkout(9780330351690L, "Jane Doe", 1, 1, 2008));
	}

	@Test
	public void testSmallLibraryCheckinISBN() {
		smallLib.checkout(9780330351690L, "Jane Doe", 1, 1, 2008);
		assertTrue(smallLib.checkin(9780330351690L));
	}

	@Test
	public void testSmallLibraryCheckinHolder() {
		assertFalse(smallLib.checkin("Jane Doe"));
	}

	@Test
	public void testSmallLibraryLookupAnCheckedOutISBN() {
		smallLib.checkout(9780330351690L, "Jane Doe", 1, 1, 2008);

		assertEquals("Jane Doe", smallLib.lookup(9780330351690L));
	}

	@Test
	public void testMediumLibraryLookupISBN() {
		assertNull(mediumLib.lookup(9781843190349L));

		mediumLib.checkout(9781843190349L, "Jane Doe", 1, 1, 2008);

		assertEquals("Jane Doe", mediumLib.lookup(9781843190349L));
	}

	@Test
	public void testMediumLibraryCheckout() {
		assertTrue(mediumLib.checkout(9781843190677L, "Jane Doe", 1, 1, 2008));
		assertFalse(mediumLib.checkout(9781983790677L, "Jane Doe", 1, 1, 2008));
	}

	@Test
	public void testMediumLibiaryLookupHolder() {
		mediumLib.checkout(9781843190349L, "Jane Doe", 1, 1, 2008);// book 7
		mediumLib.checkout(9781843190363L, "Jane Doe", 1, 1, 2008);// book 8
		mediumLib.checkout(9781843190394L, "Bob Doe", 1, 1, 2008);// book 9
		mediumLib.checkout(9781843190400L, "Bob Doe", 1, 1, 2008);// book 10

		ArrayList<LibraryBook> booksCheckedOut = mediumLib.lookup("Jane Doe");

		assertNotNull(booksCheckedOut);
		assertEquals(2, booksCheckedOut.size());

		assertTrue(booksCheckedOut.contains(new Book(9781843190349L, "Esme Ellis", "Pathway Into Sunrise")));
		assertTrue(booksCheckedOut.contains(new Book(9781843190363L, "Emma Lorant", "Cloner")));

		assertEquals("Jane Doe", booksCheckedOut.get(0).getHolder());
		assertEquals("Jane Doe", booksCheckedOut.get(1).getHolder());
	}

	@Test
	public void testMediumLibraryCheckinHolder() {
		mediumLib.checkout(9781843190349L, "Jane Doe", 1, 1, 2008);// book 7
		mediumLib.checkout(9781843190363L, "Jane Doe", 1, 1, 2008);// book 8
		mediumLib.checkout(9781843190394L, "Bob Doe", 1, 1, 2008);// book 9

		assertTrue(mediumLib.checkin("Jane Doe"));

		assertNull(mediumLib.lookup(9781843190349L));
		assertNull(mediumLib.lookup(9781843190363L));
		assertEquals("Bob Doe", mediumLib.lookup(9781843190394L));
	}

	@Test
	public void testMediumLibraryCheckinISBN() {
		mediumLib.checkout(9781843190349L, "Jane Doe", 1, 1, 2008);// book 7
		mediumLib.checkout(9781843190363L, "Jane Doe", 1, 1, 2008);// book 8
		mediumLib.checkout(9781843190394L, "Bob Doe", 1, 1, 2008);// book 9

		assertTrue(mediumLib.checkin(9781843190349L));

		assertNull(mediumLib.lookup(9781843190349L));
		assertEquals("Jane Doe", mediumLib.lookup(9781843190363L));
		assertEquals("Bob Doe", mediumLib.lookup(9781843190394L));
	}
}