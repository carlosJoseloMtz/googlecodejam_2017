import java.util.Arrays;


/**
 * Created by Chely and Carlos who actually love each other <3 :) on 4/7/17.
 */
public class TheHouseOfPancakes
{

	private static void calculatePancake(String line)
	{
		final String[] current = line.split(" ");
		char[] hotcakes = current[0].toCharArray();
		// for mexican folks (and us :V) flipper is the espátula :)
		final int flipper = Integer.parseInt(current[1]);



		hotcakes = flipHotcakes(hotcakes, X, flipper);
	}


	private static char[] flipHotcakes(char[] line, int start, int flipper)
	{
		int end = start + flipper;
		if (end >= line.length)
		{
			// do nothing..
			return line;
		}

		for (int indx = 0; indx < line.length; indx++)
		{
			char cur = line[indx];
			if (indx >= start && indx <= end)
			{
				cur = cur == '+' ? '-' : '+';
			}
			line[indx] = cur;
		}

		return line;
	}

	public static void main(String[] args)
	{
		System.out.println("flipped " + flipHotcakes("++--+-", 1, 3));
	}
}
