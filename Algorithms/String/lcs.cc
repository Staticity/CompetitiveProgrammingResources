#include <iostream>
#include <string>
#include <cmath>

#define MAX 10000

using namespace std;

int dist[MAX + 5][MAX + 5];

int longestCommonSubsequence(string a, string b)
{
	dist[0][0] = 0;

	// Empty strings have a lcs of 0
	for (int i = 1; i <= a.size(); ++i)
	{
		dist[i][0] = 0;
	}

	for (int i = 1; i <= b.size(); ++i)
	{
		dist[0][i] = 0;
	}

	for (int i = 1; i <= a.size(); ++i)
	{
		for (int j = 1; j <= b.size(); ++j)
		{
			// a[i - 1] and b[j - 1] because 1-indexing
			if (a[i - 1] == b[j - 1])
			{
				dist[i][j] = 1 + dist[i - 1][j - 1];
			}
			else
			{
				int x = dist[i - 1][j];
				int y = dist[i][j - 1];

				dist[i][j] = max(x, y);
			}
		}
	}

	return dist[a.size()][b.size()];
}

int main()
{
	string a = "chocojoshlate";
	string b = "josh";

	cout << longestCommonSubsequence(a, b) << endl;
}
