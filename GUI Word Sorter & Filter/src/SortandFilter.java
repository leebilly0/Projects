import java.util.*;

public class SortandFilter
{
	ArrayList<String> words = new ArrayList<String>();
	String wordsforString = "";
	boolean sorted = false;

	public SortandFilter()
	{
		
	}
	
	//method for sorting
	public String sort(String a_text)
	{
		makearraylist(a_text);
		
		//sorts by length
		Collections.sort(words, new StringLengthComparator());
		
		for(int count2 = 0; count2 < words.size(); count2++)
		{
			wordsforString = wordsforString + words.get(count2) + "\n";
		}
	
		words.clear();

		return wordsforString;
		
	}
	
	//method ofr making arraylist
	public void makearraylist(String a_text)
	{
		ArrayList<String> tempwords = new ArrayList<String>();
		
		StringTokenizer token = new StringTokenizer(a_text);
		while (token.hasMoreTokens())
		{
			String tempword = token.nextToken();
			String tempword2 = tempword.replaceAll("[^\\p{L}\\p{Nd}]", "");
			words.add(tempword2);
			tempwords.add(tempword2);
		}
	}
	
	
	public String filter(String a_text, String b_limit)
	{
		//make arraylist and clear string for filtering
		makearraylist(a_text);
		wordsforString = "";
		
		char[] limitnumber = b_limit.toCharArray();
		int tempnumber = (int)(limitnumber[1]-'0');
		
		//checks to make sure filter symbols are correct before filtering
		if (checkfilter(limitnumber[0], tempnumber))
		{
			for(int count2 = 0; count2 < words.size(); count2++)
			{
				if (limitnumber[0] == '=')
				{
					if (words.get(count2).length() == tempnumber)
					{
						wordsforString = wordsforString + words.get(count2) + "\n";
					}
				}
				else if (limitnumber[0] == '>')
				{
					if (words.get(count2).length() > tempnumber)
					{
						wordsforString = wordsforString + words.get(count2) + "\n";
					}
				}
				else if (limitnumber[0] == '<')
				{
					if (words.get(count2).length() < tempnumber)
					{
						wordsforString = wordsforString + words.get(count2) + "\n";
					}
				}
			}
			return wordsforString;
		}
		else
			return "You did not enter a correct filter symbol with a number";
	}
	
	//for checking to make sure user typed int he write symbol and number
	public boolean checkfilter(char a_symbol, int b_number)
	{
		if (a_symbol == '=' || a_symbol == '>' || a_symbol == '<' )
		{
			if (b_number == 0 || b_number == 1 || b_number == 2 || b_number == 3 || b_number == 4 || b_number == 5 ||
					b_number == 6 || b_number == 7 || b_number == 8 || b_number == 9)
			{
				return true;
			}
		}
		return false;
	}
	
	public void tohtml(String a_text)
	{
		
	}	
	
}
