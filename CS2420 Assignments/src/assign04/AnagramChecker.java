package assign04;

import java.util.Comparator;

public class AnagramChecker {
	
	
	
	public AnagramChecker() {
		
	}
	
	public static String sort (String s) {
		Character[] CharacterArray = toCharacterArray(s);
		insertionSort(CharacterArray, new CharacterComparator() );
		return convertToString(CharacterArray);
	}
	
	
	public static <T> void insertionSort(T[] array, Comparator<? super T> c) {
		
		for (int i = 1; i < array.length; i++)
        {
            int currentIndex = i;
            /*
             * Check:
             *      1. that currentIndex is at least 1
             *      2. that the item directly before the currentIndex is greater than the item at currentIndex
             *
             * If both conditions are met, swap the indexes
             */
            while (currentIndex > 0 && c.compare(array[currentIndex - 1], array[currentIndex]) > 0)
            {
                T temp = array[currentIndex];
                array[currentIndex] = array[currentIndex - 1];
                array[currentIndex - 1] = temp;
                currentIndex--;
            }
        }
		
	}
	
	
	public static boolean areAnagrams(String s1, String s2) {
		return sort(s1.toLowerCase()).contentEquals(sort(s2.toLowerCase()));
	}
	
	public static String[] getLargestAnagramGroup(String[] inputArray) {
		insertionSort(inputArray, new AnagramComparator());
		return inputArray;
	}
	
	
	public static String[] getLargestAnagramGroup(String filename) {
		return null;
		
	}
	
	private static Character[] toCharacterArray (String s) {
		Character[] newArray = new Character[s.length()];
		for (int i = 0; i < s.length(); i++)
		{
			newArray[i] = s.charAt(i);
		}
		return newArray;
		
	}
	
	private static String convertToString (Character[] inputArray)
	{
		String newString = "";
		for (int i = 0; i < inputArray.length; i++)
		{
			newString = newString + inputArray[i];
		}
		return newString;
	}
	
}
	
	

	
	



