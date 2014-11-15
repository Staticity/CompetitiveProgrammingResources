#include <iostream>

#define MAX 1000

using namespace std;

int knap[MAX][MAX];

void knapsack(int* weights, int* costs, int n, int capacity)
{
	if (weights[0] <= capacity)
	{
		knap[weights[0]] = costs[0];
	}

	for (int i = 1; i <= n; ++i)
	{
		for (int j = 0; j <= capacity; ++j)
		{
			knap[i][j] = knap[i - 1][j];

			int k = j - weights[i - 1];

			if (k >= 0)
			{
				knap[i][j] = max(knap[i][j], costs[i - 1] + knap[i - 1][k]);
			}
		}
	}
}
