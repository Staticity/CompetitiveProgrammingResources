import java.util.*;


// simple - no updating, just serves queries over an array
// to change from 
public class UpdateSegmentTree {
	int[] contents;
	int[] lazy;
	int size;
	public UpdateSegmentTree(int[] arr) {
		size = arr.length;
		contents = new int[4 * arr.length];
		lazy = new int[contents.length];
		build(0, 0, arr.length - 1, arr);
	}
	// define these (currently max segment tree)
	int DEF = Integer.MIN_VALUE;
	private int op(int a, int b) {
		return Math.max(a, b);
	}
	
	// functions
	private void build(int cur, int l, int r, int[] arr) {
		if (l == r) {
			contents[cur] = arr[l];
		} else {
			int lchild = (cur<<1) + 1;
			int rchild = (cur+1)<<1;
			int mid = (l+r)>>1;
			build(lchild, l, mid, arr);
			build(rchild, mid+1, r, arr);
			contents[cur] = op(contents[lchild], contents[rchild]);
		}
	}
	
	// inclusive [l, r]
	public int query(int l, int r) {
		return query(0, 0, size - 1, l, r);
	}
	
	private int query(int cur, int l, int r, int ql, int qr) {

		updateLazy(cur, l, r);
		
		if (ql == l && qr == r) {
			return contents[cur];
		}
		if (l > qr || r < ql) {
			return DEF;
		}
		int lchild = (cur<<1) + 1;
		int rchild = (cur+1)<<1;
		int mid = (l+r)>>1;
		int lquery = query(lchild, l, mid, Math.max(l, ql), Math.min(mid, qr));
		int rquery = query(rchild, mid + 1, r, Math.max(mid + 1, ql), Math.min(r, qr));
		
		return op(lquery, rquery);
	}

	// add val to everything in interval
	public void update(int ql, int qr, int val) {
		update(0, 0, size - 1, ql, qr, val);
	}

	private int update(int cur, int l, int r, int ql, int qr, int val) {
		if (l > qr || r < ql) {
			return contents[cur];
		}
		updateLazy(cur, l, r);

		if (ql <= l && r <= qr) {
			lazy[cur] += val;	
			updateLazy(cur, l, r);
			return contents[cur];
		}
		int lchild = (cur<<1) + 1;
		int rchild = (cur+1)<<1;
		int mid = (l+r)>>1;
		int lupdate = update(lchild, l, mid, Math.max(l, ql), Math.min(mid, qr), val);
		int rupdate = update(rchild, mid + 1, r, Math.max(mid + 1, ql), Math.min(r, qr), val);
		contents[cur] = op(lupdate, rupdate);
		return contents[cur];
	}

	private void updateLazy(int cur, int l, int r) {
		if (lazy[cur] == 0) return;
		contents[cur] += lazy[cur];
		if (l != r) {
			int lchild = (cur<<1) + 1;
			int rchild = (cur+1)<<1;
			lazy[lchild] += lazy[cur];
			lazy[rchild] += lazy[cur];
		}
		lazy[cur] = 0;
	}
	
	// no need to copy
	public static void main(String[] args) {
		int N = 1000;
		int[] vals = new int[N];
		for (int i = 0; i < N; i++) {
			vals[i] = (int)(Math.random() * 1000000);
		}
		UpdateSegmentTree seg = new UpdateSegmentTree(vals);
		for (int update = 0; update < 100; update++) {
			//do update
			int a = (int)(Math.random()*  N);
			int b = (int)(Math.random() * N);
			int l = Math.min(a, b);
			int r = Math.max(a, b);
			int val = (int)(Math.random() * 2 * N) - N;
			seg.update(l, r, val);
			for (int i = l; i <= r; i++) {
				vals[i] += val;
			}
			// verify all queries are correct
			for (int i = 0; i < N; i++) {
				for (int j = i; j < N; j++) {
					int segVal = seg.query(i, j);
					int max = Integer.MIN_VALUE;
					for (int k = i; k <= j; k++) {
						max = Math.max(max, vals[k]);
					}
					if (segVal != max) {
						System.out.println("error!!!");
						return;
					}
				}
			}
		}
	}
}
