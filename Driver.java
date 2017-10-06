import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {

		AVLTree tree = new AVLTree();

		tree.root = tree.insert(tree.root, 15);
		tree.updateDepth();
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

		System.out.println("\n");
		scale(tree.root);

	}

	public static void scale(Node node) {
		Scanner scnr = new Scanner(System.in);
		String inVal;
		System.out.println("            w\n            /\\\n            ||\na <== left  up  right ==> s\n");
		System.out.println(node);
		while(true) {
			inVal = scnr.nextLine();
			if("0".equals(inVal)) {
				System.out.println("Exiting.");
				break;
			} else if("a".equals(inVal)) {
				if(node.left != null) {
					node = node.left;
					System.out.println(node);
				} else {
					System.out.println("null");
				}
			} else if("w".equals(inVal)) {
				if(node.parent != null) {
					node = node.parent;
					System.out.println(node);
				} else {
					System.out.println("null");
				}
			} else if("d".equals(inVal)) {
				if(node.right != null) {
					node = node.right;
					System.out.println(node);
				} else {
					System.out.println("null");
				}				
			} else {
				System.out.println("Enter a valid key.");
			}
		}
	}	

}