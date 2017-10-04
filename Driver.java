public class Driver {

	public static void main(String[] args) {

		Node newNode = new Node(5);
		Node newNode2 = new Node(4);

		newNode.left = newNode2;
		newNode2.parent = newNode;

		System.out.println(newNode);
		System.out.println(newNode2);

	}

}