import java.io.*;
import java.util.*;
public class classscheduling {
	
	private static HashMap<String, Node> studentNodeMap;
	private static Node src, sink;
	private static boolean[] visited;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader("in.txt"));
		int T = Integer.parseInt(br.readLine().trim());
		while (T-- > 0) {
			studentNodeMap = new HashMap<String, Node>();
			nodeId = 0;
			int N = Integer.parseInt(br.readLine().trim());
			src = new Node("src");
			sink = new Node("sink");
			while (N-- > 0) {
				String line = br.readLine().trim();
				StringTokenizer st = new StringTokenizer(line);
				int classCap = Integer.parseInt(st.nextToken());
				Node classNode = new Node(line);
				classNode.addLink(sink, classCap);
				while (st.hasMoreTokens()) {
					String studentName = st.nextToken();
					Node studentNode = studentNodeMap.get(studentName);
					if (studentNode == null) {
						studentNode = addStudentNode(studentName);
						src.addLink(studentNode, 1);
					}
					studentNode.addLink(classNode, 1);
				}
			}
			System.out.println(maxFlow());
		}
	}
	
	static int maxFlow() {
		
		int flow = 0;
		while (true) {
			visited = new boolean[nodeId];
			int newflow = augPath(src, Integer.MAX_VALUE);
			if (newflow == 0) break;
			flow += newflow;
		}
		return flow;
	}
	
	static int augPath(Node cur, int cap) {
		visited[cur.id] = true;
		if (cur.equals(sink)) {
			return cap;
		}
		for (Edge e: cur.adjList) {
			Node next = e.getV(cur);
			int capLeft = e.getCapLeft(cur);
			if (!visited[next.id] && capLeft > 0) {
				int c = augPath(next, Math.min(cap, capLeft));
				if (c > 0) {
					e.addCap(cur, c);
					return c;
				}
			}
		}
		return 0;
	}
	
	static Node addStudentNode(String label) {
		Node n = new Node(label);
		studentNodeMap.put(label, n);
		return n;
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
		void addLink(Node other, int cap) {
			Edge e = new Edge(this, other, cap);
			this.adjList.add(e);
			other.adjList.add(e);
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
		int maxcap, curcap;
		Edge(Node a, Node b, int c) {
			this.a = a;
			this.b = b;
			this.maxcap = c;
			this.curcap = 0;
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
