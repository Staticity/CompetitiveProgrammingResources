
public class FenwickTree
{

	private int[] array;

	public FenwickTree(int maxElement)
	{
		this.array = new int[maxElement + 1];
	}

	public int query(int a, int b)
	{
		assert a <= b;
		assert b < this.array.length;

		if (a <= 0)
		{
			int sum = 0;

			while (b > 0)
			{
				sum += this.array[b];
				b -= (b & -b);
			}

			return sum;
		}
		else
		{
			return query(0, b) - query(0, a - 1);
		}
	}

	public void update(int index, int value)
	{
		while (index < this.array.length)
		{
			this.array[index] += value;
			index += (index & -index);
		}
	}
}