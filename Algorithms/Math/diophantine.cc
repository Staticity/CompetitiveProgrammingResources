#include <iostream>
#include <cmath>

using namespace std;

int x, y, d;

int gcd(int a, int b)
{
	if (b == 0)
	{
		return a;
	}
	else
	{
		return gcd(b, a % b);
	}
}

void extendedEuclid(int a, int b)
{
	if (b == 0)
	{
		x = 1;
		y = 0;
		d = a;
		return;
	}

	extendedEuclid(b, a % b);

	int x1 = y;
	int y1 = x - (a / b) * y;

	x = x1;
	y = y1;
}

int main()
{
	int a = 25;
	int b = 18;
	int c = 839;

	extendedEuclid(a, b);

	int mult = c / gcd(a, b);

	cout << x << " " << y << " " << d << endl;

	// any multiple generates pairs
	int k = 0;

	x = (x * mult) + (b / d) * k;
	y = (y * mult) - (a / d) * k;

	// anything from ceil(min) to floor(max)
	int z = max(x / b , y / a);

	x = x + (b * z);
	y = y - (a * z);

	cout << a << " * " << x << " + " << b << " * " << y << " = " << c << endl;
}
