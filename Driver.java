public class Driver {

	public static void main(String[] args) {

		AVLTree tree = new AVLTree();

		tree.root = tree.insert(tree.root, 45);
		tree.updateDepth();
		tree.root = tree.insert(tree.root, 55);
		tree.updateDepth();
		tree.root = tree.insert(tree.root, 35);
		tree.updateDepth();
		tree.root = tree.insert(tree.root, 25);
		tree.updateDepth();
		tree.root = tree.insert(tree.root, 40);
		tree.updateDepth();
		tree.root = tree.insert(tree.root, 10);
		tree.updateDepth();

		System.out.println("Testing tree shit.");
		tree.preOrderPrint(tree.root);
		System.out.println("Max Depth: " + tree.maxDepth);

	}

}