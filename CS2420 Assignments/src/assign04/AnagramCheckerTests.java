package assign04;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

public class AnagramCheckerTests {

	private AnagramChecker anagramTest;
	
	@BeforeEach
	void setUp() {
		anagramTest = new AnagramChecker();
		
		
	}
	
	@Test
	public void testSortShort() {
		assertEquals("ain", AnagramChecker.sort("ian"));
	}
	
	@Test
	public void testSortSameLetters() {
		assertEquals("costty", AnagramChecker.sort("scotty"));
	}
	
	@Test
	public void testSortLong() {
		assertEquals("abcdefghijklmnopqrstuvwxyz", AnagramChecker.sort("zmxncbvlaksjdhfgpqowieuryt"));
	}
	
	@Test
	public void testAreAnagramsSmall() {
		assertTrue(AnagramChecker.areAnagrams("bats", "stab"));
	}
	
	@Test
	public void testAreAnagramsSmallFalse() {
		assertFalse(AnagramChecker.areAnagrams("cats", "stab"));
	}
	
	@Test
	public void testAreAnagramsMediumWithCaps() {
		assertTrue(AnagramChecker.areAnagrams("Discounter", "Introduces"));
	}
	
	@Test
	public void testAnagramComparator() {
		String[] words = new String[] {"dater", "gallery", "trade", "allergy", "rated", "acre"};
		String[] sorted = new String[] {"acre", "allergy" , "gallery", "dater", "rated", "trade"};
		AnagramChecker.insertionSort(words, new AnagramComparator());
		assertTrue(Arrays.equals(words, sorted));
	}
	
	

}
