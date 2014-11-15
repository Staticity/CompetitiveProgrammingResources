import java.io.*;
import java.util.*;
public class MinCostFlow {
	private static int inf = 1000000000;
	private static Node src, sink;
	private static int[] minDists;
	private static Edge[] prev;
	private static ArrayList<Edge> allEdges;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader("in.txt"));
		int T = Integer.parseInt(br.readLine().trim());
		while (T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine().trim());
			int Mbp = Integer.parseInt(st.nextToken());
			int Nsc = Integer.parseInt(st.nextToken());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			
			allEdges = new ArrayList<Edge>();
			nodeId = 0;
			src = new Node("src");
			sink = new Node("sink");
			Node Anode = new Node("Aship");
			Node Bnode = new Node("Bship");
			
			st = new StringTokenizer(br.readLine().trim());
			
			int numShipC = 0;
			// ship A
			int[] Ac = new int[Nsc];
			int[] Bc = new int[Nsc];
			for (int i = 0; i < Nsc; i++) {	
				Ac[i] = Integer.parseInt(st.nextToken());
				numShipC += Ac[i]; 
			}
			st = new StringTokenizer(br.readLine().trim());
			for (int i = 0; i < Nsc; i++) {
				Bc[i] = Integer.parseInt(st.nextToken());
			}
			Anode.addLink(sink, numShipC * A, 0);
			Bnode.addLink(sink, numShipC * B, 0);
			

			Node[] shipComponents = new Node[Nsc];
			int[] totalSC = new int[Nsc];
			for (int i = 0; i < Nsc; i++) {
				shipComponents[i] = new Node("sc"+i);
				shipComponents[i].addLink(Anode, Ac[i] * A, 0);
				shipComponents[i].addLink(Bnode, Bc[i] * B, 0);
				totalSC[i] = Ac[i] * A + Bc[i] * B;
			}
			
			Node[] baseParts = new Node[Mbp];
			for (int i = 0; i < Mbp; i++) {
				st = new StringTokenizer(br.readLine().trim());
				baseParts[i] = new Node("bp"+i);
				src.addLink(baseParts[i], inf, 0);
				for (int j = 0; j < Nsc; j++) {
					int cost = Integer.parseInt(st.nextToken());
					baseParts[i].addLink(shipComponents[j], totalSC[j], cost);
				}
			}
			
			minDists = new int[nodeId];
			prev = new Edge[nodeId];
			System.out.println(maxFlow());
		}
	}
	
	static int maxFlow() {
		
		int cost = 0;
		while (true) {
			int mincost = augPath();
			if (mincost == Integer.MAX_VALUE) break;
			cost += mincost;
		}
		return cost;
	}
	
	static int augPath() {
		Arrays.fill(minDists, Integer.MAX_VALUE);
		Arrays.fill(prev, null);
		
		// run bellman ford
		minDists[src.id] = 0;
		for (int i = 0; i < minDists.length; i++) {
			for (Edge e : allEdges) {
				// try from both ends
				if (minDists[e.a.id] < Integer.MAX_VALUE && e.getCapLeft(e.a) > 0 && minDists[e.a.id] + e.cost < minDists[e.b.id]) {
					minDists[e.b.id] = minDists[e.a.id] + e.cost;
					prev[e.b.id] = e;
				}
				if (minDists[e.b.id] < Integer.MAX_VALUE && e.getCapLeft(e.b) > 0 && minDists[e.b.id] - e.cost < minDists[e.a.id]) {
					minDists[e.a.id] = minDists[e.b.id] - e.cost;
					prev[e.a.id] = e;
				}
			}
		}
		if (prev[sink.id] == null) {
			return Integer.MAX_VALUE;
		}
		Node cur = sink;
		int capRemoved = Integer.MAX_VALUE;
		while (prev[cur.id] != null) {
			Edge e = prev[cur.id];
			Node other = (e.a.equals(cur))?e.b:e.a;
			capRemoved = Math.min(capRemoved, e.getCapLeft(other));
			cur = other;
		}
		cur = sink;
		int totalCost = 0;
		while (prev[cur.id] != null) {
			Edge e = prev[cur.id];
			totalCost += capRemoved * e.cost;
			Node other = (e.a.equals(cur))?e.b:e.a;
			e.addCap(other, capRemoved);
			cur = other;
		}
		return totalCost;
	}
	
	static int nodeId = 0;
	
	static class Node {
		String label;
		int id;
		List<Edge> adjList;
		Node(String label) {
			this.label = label;
			this.id = nodeId++;
			this.adjList = new ArrayList<Edge>();
		}
		void addLink(Node other, int cap, int cost) {
			Edge e = new Edge(this, other, cap, cost);
			this.adjList.add(e);
			other.adjList.add(e);
			allEdges.add(e);
		}
		
		public boolean equals(Object other) {
			return ((Node)other).id == id;
		}
		
		public String toString() {
			return label;
		}
	}
	
	static class Edge {
		Node a, b;
		int cost;
		int maxcap, curcap;
		Edge(Node a, Node b, int c, int cost) {
			this.a = a;
			this.b = b;
			this.maxcap = c;
			this.curcap = 0;
			this.cost = cost;
		}
		
		public Node getV(Node start) {
			assert a.equals(start) || b.equals(start);
			if (a.equals(start)) {
				return b;
			} else {
				return a;
			}
		}
		
		public int getCapLeft(Node start) {
			assert a.equals(start) || b.equals(start);
			if (a.equals(start)) {
				return maxcap - curcap;
			} else {
				return curcap;
			}
		}
		
		public void addCap(Node start, int cap) {
			assert a.equals(start) || b.equals(start);
			if (a.equals(start)) {
				curcap += cap;
			} else {
				curcap -= cap;
			}
		}
	}
}