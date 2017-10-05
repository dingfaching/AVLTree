public class Driver {

	public static void main(String[] args) {

		AVLTree tree = new AVLTree(new Node(15));

		tree.root = insert(tree.root, new Node(45));
		tree.root = insert(tree.root, new Node(55));
		tree.root = insert (tree.root, new Node(35));
		tree.root = insert (tree.root, new Node(25));
		tree.root = insert (tree.root, new Node(40));
		tree.root = insert (tree.root, new Node(10));

	}

}