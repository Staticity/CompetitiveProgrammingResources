#include <iostream>

struct point
{
	float x, y;
};

// test convexity by checking all adjacent 3 points

// should use ints as lattice points instead
int turn(point p, point q, point r)
{
	float result = (r.x - q.x) * (p.y - q.y) - (r.y - q.y) * (p.x - q.x);

	if (result > 0.0f)
	{
		return -1; // right turn
	}
	else if (result < 0.0f)
	{
		return 1; // left turn
	}

	return 0; // collinear
}

