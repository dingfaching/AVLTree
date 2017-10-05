public class Node {
	
	public int key;
	public int depth;
	public int rank;
	public Node parent;
	public Node left;
	public Node right;

	public Node(int inKey) {
		key = inKey;
		depth = 0;
		rank = 0;
		parent = null;
		left = null;
		right = null;
	}

	public String toString() {
		String output = "(" + this.key;
		output += ": " + this.depth + ", ";
		output += (this.parent != null) ? this.parent.key +", " : "null, ";
		output += (this.left != null) ? this.left.key + ", " : "null, ";
		output += (this.right != null) ? this.right.key + ")" : "null)";
		return output;
	}

}