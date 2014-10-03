import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*An undirected graph has an Eulerian cycle if and only if every vertex has even degree, and all of its vertices with nonzero degree belong to a single connected component.
An undirected graph can be decomposed into edge-disjoint cycles if and only if all of its vertices have even degree. So, a graph has an Eulerian cycle if and only if it can be decomposed into edge-disjoint cycles and its nonzero-degree vertices belong to a single connected component.
An undirected graph has an Eulerian trail if and only if at most two vertices have odd degree, and if all of its vertices with nonzero degree belong to a single connected component.
A directed graph has an Eulerian cycle if and only if every vertex has equal in degree and out degree, and all of its vertices with nonzero degree belong to a single strongly connected component. Equivalently, a directed graph has an Eulerian cycle if and only if it can be decomposed into edge-disjoint directed cycles and all of its vertices with nonzero degree belong to a single strongly connected component.
A directed graph has an Eulerian trail if and only if at most one vertex has (out-degree) − (in-degree) = 1, at most one vertex has (in-degree) − (out-degree) = 1, every other vertex has equal in-degree and out-degree, and all of its vertices with nonzero degree belong to a single connected component of the underlying undirected graph.
*/


public class Eulerian {
	// fleury's algorithm
	// O(E^2) - this assumes preconditions are met
	public static List<Integer> fleury(Set<Integer>[] adjList, boolean directed) {
		List<Integer> ret = new ArrayList<Integer>();
		int cur = 0;
		for (int i = 0; i < adjList.length; i++) {
			if (adjList[i].size() % 2 == 1) {
				cur = i;
				break;
			}
		}
		
		while (true) {
			ret.add(cur);
			Set<Integer> curAdj = adjList[cur];
			if (curAdj.size() == 0) break;
			if (curAdj.size() == 1) {
				int next = cur;
				for (int c : curAdj) {
					next = c;
				}
				curAdj.clear();
				if(!directed) {
					adjList[next].remove(cur);
				}
				cur = next;
				continue;
			}
			// iterate through and find non-bridge edges
			List<Integer> adj = new ArrayList<Integer>(curAdj);
			boolean found = false;
			for (int c : adj) {
				if (!bridge(cur, c, adjList, directed)) {
					curAdj.remove(c);
					if (!directed) {
						adjList[c].remove(cur);
					}
					cur = c;
					found = true;
					break;
				}
			}
			if (!found) {
				break;
			}
		}
		return ret;
	}
	
	private static boolean bridge(int cur, int next, Set<Integer>[] adjList, boolean directed) {
		// try removing the edge and see if you can reach less
		int count = bfsCount(next, adjList);
		adjList[cur].remove(next);
		if (!directed) {
			adjList[next].remove(cur);
		}
		int count2 = bfsCount(next, adjList);
		adjList[cur].add(next);
		if (!directed) {
			adjList[next].add(cur);
		}
		return count != count2;
	}
	
	private static int bfsCount(int start, Set<Integer>[] adjList) {
		boolean[] visited = new boolean[adjList.length];
		int[] queue = new int[adjList.length];
		int insertP = 0, curP = 0;
		queue[insertP++] = start;
		visited[start] = true;
		while (curP < adjList.length && curP < insertP) {
			int cur = queue[curP++];
			for (int v : adjList[cur]) {
				if (!visited[v]) {
					visited[v] = true;
					queue[insertP++] = v;
				}
			}
		}
		return curP;
	}
	
	// end fleury's algorithm
	
	
	// begin heirholzer's
	// TODO find cycle paths at each node that still has edges, removing as you go
	// do another path at any node still with edges
	// then traverse one, when you hit a node with more paths recursively do that path, then keep going on the current one
	
	/*public List<Integer> hierholzer(Set<Integer>[] adjList) {
		List<Integer> ret = new ArrayList<Integer>();
		int cur = 0;
		for (int i = 0; i < adjList.length; i++) {
			if (adjList[i].size() % 2 == 1) {
				cur = i;
				break;
			}
		}
	}*/
	
	public static void main(String[] args) {
		/*Set<Integer>[] adjList = new Set[6];
		for (int i = 0; i < 6; i++) {
			adjList[i] = new HashSet<Integer>();
		}
		char[] labels = new char[] {'A', 'B', 'C', 'D', 'E', 'F'};
		int[][] edges = new int[][] {{0,1}, {2, 3}, {0,3}, {0,2}, {1, 3}, {3,5}, {2,5}, {2,4}, {0,4}};
		for (int i = 0;i < edges.length; i++) {
			int a = edges[i][0];
			int b = edges[i][1];
			adjList[a].add(b);
			adjList[b].add(a);
		}
		
		List<Integer> tour = fleury(adjList, false);
		for (int i : tour) {
			System.out.print(labels[i] + " ");
		}*/
		// answer: A B D A C D F C E A 
	}
}
