#include <iostream>
#include <pair>

using namespace std;

pair<int, int> floyd_cycle_finding(int (*f)(int), int x0)
{
	int slow = f(x0);
	int fast = f(f(x0));

	// 1x and 2x to catch up
	while (slow != fast)
	{
		slow = f(slow);
		fast = f(f(fast));
	}

	int mu = 0;
	int fast = slow;
	int slow = x0;

	while (slow != fast)
	{
		slow = f(slow);
		fast = f(fast);
		++mu;
	}

	int lambda = 1;
	fast = f(slow);

	while (slow != fast)
	{
		fast = f(fast);
		++lambda;
	}

	return make_pair(mu, lambda);
}
