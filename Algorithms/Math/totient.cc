#include <iostream>
#include <vector>

#define MAX 100000

using namespace std;

int divs[MAX + 5];

void store()
{
	for (int i = 2; i <= MAX; i += 2)
	{
		divs[i] = 2;
	}

	for (int i = 3; i * i <= MAX; i += 2)
	{
		if (divs[i] == 0)
		{
			for (int j = i; j <= MAX; j += i)
			{
				divs[j] = i;
			}
		}
	}
}

// gets unique prime factors
void getPrimeFactors(int n, vector<int>& primes)
{
	int index = -1;
	while (n > 1)
	{
		int prime = divs[n];

		// primes.push_back(prime);

		if (primes.empty() || primes[index] != prime)
		{
			primes.push_back(prime);
			++index;
		}

		n /= prime;
	}
}

// Finds number of coprime values x, x <= n
int totient(int n)
{
	vector<int> primes;
	getPrimeFactors(n, primes);

	int coprimes = n;

	for (int i = 0; i < primes.size(); ++i)
	{
		cout << primes[i] << endl;
		coprimes -= coprimes / primes[i];
	}

	return coprimes;
}

int main()
{
	store();
	cout << totient(36) << endl;
}
