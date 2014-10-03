import java.util.*;

// simple topo sort using array queue
public class TopoSort {
	public static void main(String[] args) throws Exception {
		int N = 6;
		ArrayList<Integer>[] adjList = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			adjList[i] = new ArrayList<Integer>();
		}
		adjList[2].add(0);
		adjList[2].add(1);
		adjList[0].add(3);
		adjList[1].add(3);
		adjList[4].add(5);
		int[] queue = new int[N];
		int queueIndex = 0;
		int[] indegrees = new int[N];
		for (int i = 0; i < N; i++) {
			for (int v : adjList[i]) {
				indegrees[v]++;
			}
		}
		for (int i = 0; i < N; i++) {
			if (indegrees[i] == 0) {
				queue[queueIndex++] = i;
			}
		}
		int index = 0;
		while (index < N && index < queueIndex) {
			int cur = queue[index++];
			System.out.println(cur);
			for (int v : adjList[cur]) {
				indegrees[v]--;
				if (indegrees[v] == 0) {
					queue[queueIndex++] = v;
				}
			}
		}
	}
}
