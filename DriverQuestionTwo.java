public class DriverQuestionTwo {
	
	public static void main(String[] args) {

		AVLTree tree = new AVLTree();
		tree.insert(15);
		tree.insert(45);
		tree.insert(55);
		tree.insert(35);
		tree.insert(25);
		tree.insert(40);
		tree.insert(10);

		System.out.println("\n\nIn order traversal: ");
		tree.inOrderPrint(tree.root);

	}

}