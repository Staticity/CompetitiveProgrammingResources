import java.util.*;

public class TrieTest
{
	public static void main(String args[])
	{
		Scanner scan = new Scanner(System.in);

		int tests = scan.nextInt();

		while (tests-- > 0)
		{
			int wordCount = scan.nextInt();
			int testWordCount = scan.nextInt();

			Trie dictionary = new Trie();

			for (int i = 0; i < wordCount; ++i)
			{
				dictionary.add(scan.next());
			}

			for (int i = 0; i < testWordCount; ++i)
			{
				if (dictionary.contains(scan.next()))
				{
					System.out.print(1);
				}
				else
				{
					System.out.print(0);
				}
			}

			System.out.println();
		}

		scan.close();
	}
}