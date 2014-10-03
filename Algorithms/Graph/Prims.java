import java.util.*;
import java.io.*;

public class Prims {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		ArrayList<Edge>[] adjList = new ArrayList[N];
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine().trim());
			int i = Integer.parseInt(st.nextToken()) - 1;
			int j = Integer.parseInt(st.nextToken()) - 1;
			int w = Integer.parseInt(st.nextToken());
			if (adjList[i] == null) {
				adjList[i] = new ArrayList<Edge>();
			}
			adjList[i].add(new Edge(w, j));
			if (adjList[j] == null) {
				adjList[j] = new ArrayList<Edge>();
			}
			adjList[j].add(new Edge(w, i));
		}
		
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
		pq.offer(new Edge(0, 0));
		long totalWeight = 0;
		boolean[] visited = new boolean[N];
		while (!pq.isEmpty()) {
			Edge cur = pq.poll();
			if (visited[cur.v]) continue;
			visited[cur.v] = true;
			totalWeight += cur.weight;
			for (Edge e : adjList[cur.v]) {
				if (!visited[e.v]) {
					pq.offer(e);
				}
			}
		}
		System.out.println(totalWeight);
	}
	
	static class Edge implements Comparable<Edge>{
		int weight, v;
		Edge(int w, int v) {
			this.weight = w;
			this.v = v;
		}
		
		public int compareTo(Edge other) {
			return this.weight - other.weight;
		}
	}
}
