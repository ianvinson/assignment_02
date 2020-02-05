package assign04;

import java.util.Comparator;

/**
 * Uses the Characters comparable as a Comparator
 * @author Ian
 *
 */
public class CharacterComparator implements Comparator<Character>{

	@Override
	public int compare(Character o1, Character o2) {
		return o1.compareTo(o2);
	}

}
