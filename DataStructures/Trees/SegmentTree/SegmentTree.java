
// UNUSED - INCOMPLETE

public class SegmentTree
{

	public interface Function
	{
		int compute(int ... n);
	}

	private class SegmentNode
	{
		boolean valid;

		SegmentNode left, right;
		int start, end;
		int value;

		public SegmentNode(int start, int end)
		{
			this.start = start;
			this.end = end;
			this.valid = false;
		}

		public SegmentNode(int start, int end, int value)
		{
			this(start, end);
			this.valid = true;
			this.value = value;
		}

		public SegmentNode(int start, int end, SegmentNode left, SegmentNode right, Function f)
		{
			this(start, end, value);
			this.left = left;
			this.right = right;
			this.value = f.compute(left.value, right.value);
		}
	}

	private int maxElement;
	private Function func;

	public SegmentTree(int maxElement, Function func)
	{
		this.maxElement = maxElement;
		this.func = func;
	}

	public SegmentNode initialize(int start, int end)
	{
		if (start == end)
		{
			return SegmentNode(start, end, 0);
		}
		else
		{
			int mid = start + (end - start) >> 1;
			return SegmentNode(start, end, initialize(start, mid - 1), initialize(mid, end), this.func);
		}
	}
}
