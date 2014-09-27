import java.io.*;
import java.util.*;
 
public class disjointset {
	public static void main(String[] args) throws IOException {
		int N = 10;
		Node[] nodes = new Node[N];
		for (int i = 0; i < N; i++) {
			nodes[i] = new Node(i);
		}
		nodes[1].join(nodes[2]);
		nodes[2].join(nodes[3]);
		System.out.println(nodes[1].getRootId() + " == "  + nodes[3].getRootId());
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
	}
}  