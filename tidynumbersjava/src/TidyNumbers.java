import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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

		System.out.println("current number " + n);

		// n = transformIfNeeded(n);
		while (!isTidy(n))
		{
			n--;
			n = transformIfNeeded(n);
			System.out.println(n);
			lastTidy = n;
		}

		return lastTidy;
	}

	private static long transformIfNeeded(final long n)
	{
		String el = n + "";
		if (el.contains("0"))
		{
			final String nNumberAS = el.replaceAll("[0-9]", "9").substring(1, el.length());
			System.out.println("transformed " + n + " to " + nNumberAS);
			return Long.parseLong(nNumberAS);
		}
		return n;
	}

	private final static char ZERO = '0';
	private static final char NINE = '9';

	private static long transformNumber(String n)
	{
		StringBuilder str = new StringBuilder(n);
		for (int i = n.length() - 1; i > 0; i--)
		{
			System.out.println("checking " + str.toString());
			char cChar = str.charAt(i);
			// if we find a zero
			if (cChar == ZERO)
			{
				// change all digits (including this one) to the right to 9
				for (int y = i; y < n.length(); y++)
				{
					str.setCharAt(y, NINE);
				}
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


			str.setCharAt(i, cChar);

			System.out.println("after => " + str.toString());
		}

		return Long.parseLong(str.toString());
	}

	private static boolean isTidy(long n)
	{
		if (n < 0)
		{
			System.out.println("fucking thing!" + n);
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
		System.out.println("transformed " + transformNumber("10101"));
		// runProgram();
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
