#include <iostream>
#include <algorithm>
#include <cmath>

#define MAX 100

using namespace std;

int graph[MAX][MAX];
bool finite[MAX][MAX];

void floydwarshall()
{
	for (int k = 0; k < MAX; ++k)
	{
		for (int i = 0; i < MAX; ++i)
		{
			for (int j = 0; j < MAX; ++j)
			{
				int newCost = dist[i][k] + dist[k][j];
				if (!finite[i][j] || dist[i][j] > newCost)
				{
					graph[i][j] = newCost;
				}
			}
		}
	}
}

int main()
{
	for (int i = 0; i < MAX; ++i)
	{
		fill(graph[i], graph[i] + MAX, -1);
	}

	int edges;
	cin >> edges;

	int u, v, cost;
	for (int i = 0; i < edges; ++i)
	{
		cin >> u >> v >> cost;
		graph[u][v] = cost;
		finite[u][v] = true;
	}

	for (int i = 0; i < MAX; ++i)
	{
		graph[i][i] = 0;
		finite[u][v] = true;
	}

	floydwarshall();
}
