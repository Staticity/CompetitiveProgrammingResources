import java.util.*;
import java.io.*;

public class Kruskal {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		int N = Integer.parseInt(st.nextToken());
		Node[] nodes = new Node[N];
		for (int i = 0; i < N; i++) {
			nodes[i] = new Node(i);
		}
		int M = Integer.parseInt(st.nextToken());
		Edge[] edges = new Edge[M];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine().trim());
			int u = Integer.parseInt(st.nextToken()) - 1;
			int v = Integer.parseInt(st.nextToken()) - 1;
			int w = Integer.parseInt(st.nextToken());
			edges[i] = new Edge(w, u, v);
		}
		
		Arrays.sort(edges);
		
		long totalWeight = 0;
		
		for (int i = 0; i < M; i++) {
			Edge e = edges[i];
			if (nodes[e.u].getRootId() != nodes[e.v].getRootId()) {
				totalWeight += e.weight;
				nodes[e.u].join(nodes[e.v]);
			}
		}
		
		System.out.println(totalWeight);
	}
	
	static class Edge implements Comparable<Edge>{
		int weight, u, v;
		Edge(int w, int u, int v) {
			this.weight = w;
			this.u = u;
			this.v = v;
		}
		
		public int compareTo(Edge other) {
			return this.weight - other.weight;
		}
	}
	
	// disjoint set node
	static class Node {
		int id;
		int height;
		Node parent;
		Node(int id) {
			this.id = id;
			this.height = 1;
			this.parent = null;
		}
		void join(Node other) {
			if (other.height < this.height) {
				// make this the parent
				this.addChild(other);
			} else if (this.height < other.height) {
				other.addChild(this);
			} else if (Math.random() < 0.5) { // to make sure both sides of tree even
				this.addChild(other);
			} else {
				other.addChild(this);
			}
		}
		
		void addChild(Node n) {
			n.parent = this;
			this.height = Math.max(this.height, n.height + 1);
		}
		Node getRoot() {
			if (parent == null) {
				return this;
			}
			Node root = parent.getRoot();
			// squash tree - helps runtime
			this.parent = root;
			return root;
		}
		int getRootId() {
			return this.getRoot().id;
		}
		// TODO remove
		public String toString() {
			return ""+getRootId();
		}
	}
}
