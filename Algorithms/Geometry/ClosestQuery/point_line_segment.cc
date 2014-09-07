#include <iostream>
#include <cmath>

const double epsilon = 0.00001;

using namespace std;

struct point
{
	double x, y, z;
};

void printPoint(const point& p)
{
	cout << p.x << " " << p.y << " " << p.z << endl;
}

double distSq(const point& a, const point& b)
{
	double x = a.x - b.x;
	double y = a.y - b.y;
	double z = a.z - b.z;

	return (x * x) + (y * y) + (z * z);
}

void closestPoint(const point& p, const point& a, const point& b, point& answer)
{
	double dx = b.x - a.x;
	double dy = b.y - a.y;
	double dz = b.z - a.z;

	double d1 = 1 / 3.0f;
	double d2 = d1 + d1;

	point c = {
		a.x + d1 * dx,
		a.y + d1 * dy,
		a.z + d1 * dz
	};

	point d = {
		a.x + d2 * dx,
		a.y + d2 * dy,
		a.z + d2 * dz
	};

	double cdSq = distSq(c, d);
	double pcSq = distSq(p, c);
	double pdSq = distSq(p, d);

	if (cdSq < epsilon * epsilon)
	{
		answer.x = c.x;
		answer.y = c.y;
		answer.z = c.z;
		return;
	}

	if (pcSq < pdSq)
	{
		closestPoint(p, a, d, answer);
	}
	else
	{
		closestPoint(p, c, b, answer);
	}
}

int main()
{
	point p, a, b;

	cin >> p.x >> p.y >> p.z;
	cin >> a.x >> a.y >> a.z;
	cin >> b.x >> b.y >> b.z;

	point answer;
	closestPoint(p, a, b, answer);

	printPoint(answer);
}