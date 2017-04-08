import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by chely and carlos who actually love each other :) on 4/7/17.
 */
public class TidyNumbers
{

	private static long findLatestTidy(long n)
	{
		if (n < 1)
		{
			throw new IllegalArgumentException("Not computable");
		}

		long lastTidy = n;

		// n = transformIfNeeded(n);
		while (!isTidy(n))
		{
			n--;
			n = transformNumber(n + "");
			lastTidy = n;
		}

		return lastTidy;
	}

	private final static char ZERO = '0';
	private static final char NINE = '9';

	private static long transformNumber(String n)
	{
		StringBuilder str = new StringBuilder(n);
		for (int i = n.length() - 1; i > 0; i--)
		{
			char cChar = str.charAt(i);
			// if we find a zero
			if (cChar == ZERO)
			{
				// change all digits (including this one) to the right to 9
				for (int y = i; y < n.length(); y++)
				{
					str.setCharAt(y, NINE);
				}


				// get the previous char
				char previous = str.charAt(i - 1);

				// if previous is a non-zero digit
				if (previous != ZERO)
				{
					// decrease one digit
					previous--;
					str.setCharAt(i - 1, previous);
				}
			}

		}

		return Long.parseLong(str.toString());
	}

	private static boolean isTidy(long n)
	{
		if (n < 0)
		{
			return true;
		}
		final String elements = (n + "");
		final int length = elements.length();
		for (int indx = 1; indx < length; indx++)
		{
			final int first = elements.charAt(indx);
			final int last = elements.charAt(indx - 1);
			if (first < last)
			{
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) throws IOException
	{
		runProgram();
	}

	private static void runProgram() throws IOException
	{
		// read file
		final List<String> lines = Files
				.readAllLines(Paths.get("/home/carlos-ow/Documents/personal/chely_carlos_codejam/tidynumbersjava/input_tidy_java"));
		final int cases = Integer.parseInt(lines.get(0));
		final List<String> results = new ArrayList<>(cases);
		final StringBuilder rs = new StringBuilder(50);
		for (int ccase = 1; ccase <= cases; ccase++)
		{
			final long lastTidy = findLatestTidy(Long.parseLong(lines.get(ccase)));
			rs.append("Case #")
					.append(ccase)
					.append(": ")
					.append(lastTidy);

			results.add(rs.toString());
			rs.setLength(0);
			rs.setLength(50);
		}

		// write file
		Files.write(Paths.get("output_java"), results);
	}
}
