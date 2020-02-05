package assign04;

public class TestShit {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String s = "Ian";
		System.out.print(convertToString(toCharacterArray(s)));

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
