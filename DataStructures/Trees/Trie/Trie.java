import java.util.Map;
import java.util.TreeMap;

public class Trie
{

	char letter;
	boolean isContained;

	Map<Character, Trie> children;

	public Trie()
	{
		this.letter = 0;
	}

	private Trie(char letter)
	{
		this.letter = letter;
	}

	public void add(String sequence)
	{
		if (!sequence.isEmpty())
		{
			if (this.children == null)
			{
				this.children = new TreeMap<Character, Trie>();
			}

			char nextLetter = sequence.charAt(0);
			Trie child = this.children.get(nextLetter);

			if (child == null)
			{
				child = new Trie(nextLetter);
				this.children.put(nextLetter, child);
			}

			child.add(sequence.substring(1));
		}
		else
		{
			this.isContained = true;
		}
	}

	public boolean contains(String sequence)
	{
		if (!sequence.isEmpty())
		{
			if (this.children == null)
			{
				return false;
			}

			char nextLetter = sequence.charAt(0);
			Trie child = this.children.get(nextLetter);

			if (child == null)
			{
				return false;
			}
			else
			{
				return child.contains(sequence.substring(1));
			}
		}
		else
		{
			return this.isContained;
		}
	}

}