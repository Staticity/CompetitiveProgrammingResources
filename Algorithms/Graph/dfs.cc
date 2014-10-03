#include <iostream>

#define MAX_NODES 1000

vector<int> graph[MAX_NODES + 5];
int info[MAX_NODES + 5];

// 1: 2, 3
// 2: 1, 3
// 3: 1, 2, 4
// 4: 1, 3, 5, 6
// 5: 4, 6
// 6: 4, 5

int edges = {
	{2, 2, 3},
	{1, 3},
	{1, 4},
	{2, 5, 6},
	{1, 6},
	{0}
}

int main()
{
	for (int i = 1; i <= 6; ++i)
	{
		for (int j = 0; j < edges[])
		graph[i].push_back()
	}
}