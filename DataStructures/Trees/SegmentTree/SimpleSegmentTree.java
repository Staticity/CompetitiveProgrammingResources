
// simple - no updating, just serves queries over an array
// to change from 
public class SimpleSegmentTree {
	int[] contents;
	int size;
	public SimpleSegmentTree(int[] arr) {
		size = arr.length;
		contents = new int[4 * arr.length];
		build(0, 0, arr.length - 1, arr);
	}
	// define these (currently max)
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
	
	// no need to copy
	public static void main(String[] args) {
		int N = 1000;
		int[] vals = new int[N];
		for (int i = 0; i < N; i++) {
			vals[i] = (int)(Math.random() * 1000000);
		}
		SimpleSegmentTree seg = new SimpleSegmentTree(vals);
		for (int i = 0; i < N; i++) {
			for (int j = i; j < N; j++) {
				int segVal = seg.query(i, j);
				int max = Integer.MIN_VALUE;
				for (int k = i; k <= j; k++) {
					max = Math.max(max, vals[k]);
				}
				if (segVal != max) {
					System.out.printf("Error for (%d, %d): seg: %d, actual: %d\n", i, j, segVal, max);
				}
			}
		}
	}
}
