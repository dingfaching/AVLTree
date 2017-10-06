public class DriverQuestionThree {
	
	public static void main(String[] args) {

		AVLTree tree = new AVLTree();

		tree.insert(50);
		tree.insert(40);
		tree.insert(78);
		tree.insert(20);
		tree.insert(62);
		tree.insert(88);
		tree.insert(55);
		tree.insert(70);

		tree.inOrderPrint(tree.root);

		System.out.println("\n\nTesting removal:\n");

		tree.remove(40);
		tree.remove(21);
		tree.remove(70);

	}

}