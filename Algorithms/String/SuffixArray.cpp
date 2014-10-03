#include <iostream>
#include <algorithm>
#include <cstring>
#include <string>

#define REP(i, n) for (int i = 0; i < (int)(n); ++i)

using namespace std;

const int MAXN = 1 << 21;
string S;
int N, gap;
int sa[MAXN], pos[MAXN], tmp[MAXN], lcp[MAXN];

bool sufCmp(int i, int j)
{
	if (pos[i] != pos[j])
		return pos[i] < pos[j];
	i += gap;
	j += gap;
	return (i < N && j < N) ? pos[i] < pos[j] : i > j;
}
void buildSA()
{
	N = S.size();
	REP(i, N) sa[i] = i, pos[i] = S[i];
	for (gap = 1;; gap *= 2)
	{
		sort(sa, sa + N, sufCmp);
		REP(i, N - 1) tmp[i + 1] = tmp[i] + sufCmp(sa[i], sa[i + 1]);
		REP(i, N) pos[sa[i]] = tmp[i];
		if (tmp[N - 1] == N - 1) break;
	}
}
void buildLCP()
{
	for (int i = 0, k = 0; i < N; ++i) if (pos[i] != N - 1)
	{
		for (int j = sa[pos[i] + 1]; S[i + k] == S[j + k];)
		++k;
		lcp[pos[i]] = k;
		if (k)--k;
	}
}

int main() {
	S = "ababccbbcc";
	buildSA();
	buildLCP();
	for (int i = 0; i < S.size(); ++i) {
		int start = sa[i];
		for (int j = start; j < S.size(); ++j)
		{
			cout << S[j];
		}
		cout << endl;
	}
	cout << endl;

	for (int i = 0; i < S.size(); ++i)
	{
		cout << lcp[i] << endl;
	}
}
