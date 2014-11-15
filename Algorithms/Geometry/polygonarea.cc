#include <iostream>
#include <cmath>

using namespace std;

struct vector
{
	double x, y;
};

double cross(const vector& a, const vector& b)
{
	return (a.x * b.y) - (a.y * b.x);
}

double area(const vector* points, int size)
{
	double area = 0.0;

	for (int i = 0; i < size - 2; ++i)
	{
		const vector& p1 = points[i];
		const vector& p2 = points[i + 1];
		const vector& p3 = points[i + 2];

		double x1 = p2.x - p1.x;
		double y1 = p2.y - p1.y;

		double x2 = p3.x - p2.x;
		double y2 = p3.y - p2.y;

		area += (x1 * y2) - (y1 * x2);
	}

	return abs(area / 2.0);
}

int main()
{

}