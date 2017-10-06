public class Node {

	public int key;
	public int rank;
	public int depth;

	public Node left;
	public Node parent;
	public Node right;

	public Node() {
		this.key = -1;
		this.rank = -1;
		this.depth = -1;

		this.left = null;
		this.parent = null;
		this.right = null;
	}

	public Node(int inKey) {
		this.key = inKey;
		this.rank = -1;
		this.depth = -1;

		this.left = null;
		this.parent = null;
		this.right = null;		
	}

	public String toString() {
		String output = "(" + this.key;
		output += ": " + this.rank + ", ";
		output += (this.parent != null) ? this.parent.key +", " : "null, ";
		output += (this.left != null) ? this.left.key + ", " : "null, ";
		output += (this.right != null) ? this.right.key + ")" : "null)";
		return output;		
	}

}