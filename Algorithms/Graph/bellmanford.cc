#include <iostream>
#include <algorithm>
#include <vector>
#include <map>

#define MAX 1000
#define INF 10000000

vector<int> graph[MAX];

int weights[MAX];
int predecessors[MAX];

using namespace std;

void bellmanford(int vertex)
{
	for (int i = 0; i < MAX; ++i)
	{
		weights[i] = INF;
	}

	weights[vertex] = 0;

	for (int i = 0; i < MAX; ++i)
	{
		for (int j = 0; j < MAX; ++j)
		{
			for (int k = 0; k < graph[j].size(); ++k)
			{
				
			}
		}
	}
}

int main()
{
}