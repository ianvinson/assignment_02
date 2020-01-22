package assign02;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BookTest {
	
	private Book Book1,Book2,Book3;
	private long ISBN1 = 9780374292799L;
	private String AUTHOR1 = "Thomas L. Friedman";
	private String TITLE1 = "The World is Flat";
	
	private long ISBN2 = 9780330351690L;
	private String AUTHOR2 = "Jon Krakauer";
	private String TITLE2 = "Into the Wild";
	
	private long ISBN3 = 9780446580342L;
	private String AUTHOR3 = "David Baldacci";
	private String TITLE3 = "Simple Genius";

	@BeforeEach
	void setUp() throws Exception {
		Book1 = new Book(ISBN1,AUTHOR1,TITLE1);
		Book2 = new Book(ISBN2,AUTHOR2,TITLE2);
		Book3 = new Book(ISBN3,AUTHOR3,TITLE3);
	}

	@Test
	void testBook() {
		fail("Not yet implemented");
	}

	@Test
	void testGetAuthor() {
		assertEquals(Book1.getAuthor(),AUTHOR1);
		assertEquals(Book2.getAuthor(),AUTHOR2);
		assertEquals(Book3.getAuthor(),AUTHOR3);
	}

	@Test
	void testGetIsbn() {
		assertEquals(Book1.getIsbn(),ISBN1);
		assertEquals(Book2.getIsbn(),ISBN2);
		assertEquals(Book3.getIsbn(),ISBN3);
	}

	@Test
	void testGetTitle() {
		assertEquals(Book1.getTitle(),TITLE1);
		assertEquals(Book2.getTitle(),TITLE2);
		assertEquals(Book3.getTitle(),TITLE3);
	}

	@Test
	void testEqualsObject() {
		assertTrue(Book1.equals(new Book(ISBN1,AUTHOR1,TITLE1)));
		assertFalse(Book2.equals(Book3));
	}

	@Test
	void testToString() {
		assertEquals(Book1.toString(),"9780374292799, Thomas L. Friedman, \"The World is Flat\"");
		assertEquals(Book2.toString(),"9780330351690, Jon Krakauer, \"Into the Wild\"");
		assertEquals(Book3.toString(),"9780446580342, David Baldacci, \"Simple Genius\"");
	}

}
