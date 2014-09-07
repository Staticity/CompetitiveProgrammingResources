import java.util.Scanner;

public class FenwickTreeTest
{
	public static void main(String args[])
	{
		Scanner scan = new Scanner(System.in);

		int tests = scan.nextInt();

		for (int i = 1; i <= tests; ++i)
		{
			int n = scan.nextInt(), q = scan.nextInt();
			int[] values = new int[n];

			int maxValue = 0;
			for (int j = 0; j < n; ++j)
			{
				values[j] = scan.nextInt();
				maxValue = Math.max(maxValue, values[j]);
			}

			FenwickTree tree = new FenwickTree(maxValue);

			for (int j = 0; j < n; ++j)
			{
				tree.update(values[j], 1);
			}

			int a, b;
			for (int j = 0; j < q; ++j)
			{
				a = scan.nextInt();
				b = scan.nextInt();

				System.out.print(tree.query(a, b) + " ");
			}

			System.out.println();
		}

		scan.close();
	}
}