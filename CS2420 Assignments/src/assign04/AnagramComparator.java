package assign04;

import java.util.Comparator;

public class AnagramComparator implements Comparator<String> {

	@Override
	public int compare(String o1, String o2) {
		if (AnagramChecker.areAnagrams(o1, o2)) {
			return -1;
		}
		else
			return 1;
	}

}
