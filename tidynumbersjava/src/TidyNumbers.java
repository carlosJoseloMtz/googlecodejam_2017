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
		while (!isTidy(n))
		{
			n--;
			lastTidy = n;
		}

		return lastTidy;
	}

	private static boolean isTidy(long n)
	{
		final String elements = (n + "");

		final int length = elements.length();
		for (int indx = 1; indx < length; indx++)
		{
			final int first = elements.charAt(indx);
			final int last = elements.charAt(indx -1);
			if (first < last)
			{
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) throws IOException
	{
		// read file
		final List<String> lines = Files.readAllLines(Paths.get("/home/carlos-ow/Documents/personal/chely_carlos_codejam/tidynumbersjava/input_tidy_java"));
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
