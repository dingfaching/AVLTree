public class AVLTree {

	public Node root;
	public int n;
	public int maxDepth;

	public AVLTree() {
		this.root = null;
		this.n = 0;
		this.maxDepth = -1;
	}

	public AVLTree(Node inRoot) {
		this.root = inRoot;
		this.n = 1;
		this.maxDepth = 0;
	}

	/*  Left-left case : rightRotate() @ a
				 <a>            b 
			     / \           / \
		  	    b   t4        c   a
			   / \		=>   / \  | \
		      c  t3         t1 t2 t3 t4
		     / \
	  	    t1 t2  
	
		Right-right case: leftRotate() @ a
		   <a> 					b
		   / \				   / \
		  t1  b               /   \ 
		     / \     =>      a     c
		    t2  c 			/ \   / \
		       / \		   t1 t2 t3 t4
		      t3  t4

		Left-Right case: leftRotate() @ b  =>  rightRotate() @ a
		    a              <a> 				  c
		   / \			   / \				 / \
		 <b> t4			  c  t4				/   \
		 / \      =>     / \        =>     b     a
		t1  c           b  t3			  / \   / \
		   / \         / \				 t1 t2 t3 t4
		  t2  t3      t1  t2

		Right-Left case: rightRotate() @ b  =>  leftRotate() @ a
		    a 			  <a> 				  c
		   / \			  / \				 / \
		  t1 <b>		 t1  c 				/   \
		     / \   =>       / \    =>      a     b
		    c  t4		   t2  b 		  / \   / \
		   / \			      / \        t1 t2 t3 t4
		  t2 t3				 t3 t4

	*/
	public Node insert(Node node, int key) {

		//BST Insertion (recursive)
		if(node == null) {
			return(new Node(key));
		}

		if(key < node.key) {
			node.left = insert(node.left, key);
			node.left.parent = node;
		} else if (node.key < key) {
			node.right = insert(node.right, key);
			node.right.parent = node;
		} else {
			System.out.println(AVLTreeMessage.EXISTS);
			return node;
		}

		//Update current node balance information
		int balance = getBalance(node);

		//If the node is balanced correctly, simply return the node unchanged
		if(-1 <= balance && balance <= 1) {
			return node;
		}

		//Left-Left case
		if(balance > 1 && key < node.left.key) {
			return rightRotate(node);
		}
		//Right-Right case
		else if(balance < -1 && node.right.key < key) {
			return leftRotate(node);
		}
		//Left-Right case
		else if(balance > 1 && node.left.key < key) {
			node.left = leftRotate(node.left);
			return rightRotate(node);
		}
		//Right-Left case
		else if(balance < -1 && key < node.right.key) {
			node.right = rightRotate(node.right);
			return leftRotate(node);
		}
		return node;
	}

	public int getBalance(Node node) {
		int b = (node.left == null) ? 0 : node.left.rank;
		int c = (node.right == null) ? 0 : node.right.rank;
		return b - c;
	}

	public int max(int a, int b) {
		return (a > b) ? a : b;
	}

	public int getRank(Node node) {
		if(node == null) {
			return 0;
		}
		return node.rank;
	}

	public Node rightRotate(Node node) {
		Node a = node;
		Node b = node.left;
		Node t3 = b.right;

		//Perform rotation
		b.parent = a.parent;
		a.parent = b;
		t3.parent = a;
		a.left = t3;
		b.right = a;

		//Update heights
		a.rank = max(getRank(a.left), getRank(a.right));
		b.rank = max(getRank(b.left), getRank(b.right));

		//Return the new root of the subtree
		return b;
	}

	public Node leftRotate(Node node) {
		Node a = node;
		Node b = a.right;
		Node t2 = b.left;

		//Perform rotation
		b.parent = a.parent;
		a.parent = b;
		t2.parent = a;
		a.right = t2;
		b.left = a;

		//Update heights
		a.rank = max(getRank(a.left), getRank(a.right));
		b.rank = max(getRank(b.left), getRank(b.right));

		//return new root of subtree
		return b;
	}

	public int updateDepth() {
		root.depth = 0;
		setDepth(root.left);
		setDepth(root.right);
	}

	public void setDepth(Node node) {
		if(node = null) {
			return;
		}
		node.depth = node.parent.depth + 1;
		setDepth(node.left);
		setDepth(node.right);
	}

	public String inOrderTraversal(Node node) {
		
	}

	public String toString() {

	}


	
}