#include <iostream>
#include <string>
#include <cmath>

#define MAX 10000

using namespace std;

int dist[MAX + 5][MAX + 5];

int editDistance(string a, string b)
{
	dist[0][0] = 0;

	for (int i = 1; i <= a.size(); ++i)
	{
		dist[i][0] = i;
	}

	for (int i = 1; i <= b.size(); ++i)
	{
		dist[0][i] = i;
	}

	for (int i = 1; i <= a.size(); ++i)
	{
		for (int j = 1; j <= b.size(); ++j)
		{
			int x = dist[i - 1][j] + 1;
			int y = dist[i][j - 1] + 1;

			// a[i - 1] and b[j - 1] because we're 1-indexing
			int z = dist[i - 1][j - 1] + ((a[i - 1] == b[j - 1]) ? 0 : 1);

			dist[i][j] = min(x, min(y, z));
			cout << dist[i][j] << " ";
		}
		cout << endl;
	}

	return dist[a.size()][b.size()];
}

int main()
{
	cout << editDistance(a, b) << endl;
}
