#include <iostream>

using namespace std;

struct line
{
	double a, b, c;
};

struct vector
{
	double x, y;
};

line getLine(const vector& p1, const vector& p2)
{
	double a = p2.y - p1.y;
	double b = p2.x - p1.x;
	double c = (p1.x * a) + (p1.y * b);

	line l = {a, b, c};
	return l;
}

vector intersect(const line& l1, const line& l2)
{
	vector v;
	double det = (l1.a * l2.b) - (l2.a * l1.b);

	if (det == 0)
	{
		// parallel
	}
	else
	{
		v.x = ((l2.b * l1.c) - (l1.b * l2.c)) / det;
		v.y = ((l1.a * l2.c) - (l2.a * l1.c)) / det;
	}

	return v;
}

int main()
{

}