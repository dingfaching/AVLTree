public class AVLTree {

	//Class variables
	public Node root;
	public int n;
	public int height;

	//Constructor
	public AVLTree() {
		this.root = null;
		this.n = 0;
		this.height = -1;
	}

	/**************************************
	* Insert method
	*
	**************************************/
	public void insert(int key) {

		System.out.println("Insert " + key + "--------");
		this.root = place(this.root, key);
		System.out.println("-----------------\n");

		//Update height
		this.height = this.root.rank;

		//Re-depth
		this.setDepth(this.root);

	}

	/**************************************
	* remove method
	*
	**************************************/
	public Node remove(int key) {

		Node nodeToRemove = this.search(this.root, key);

		if(nodeToRemove == null) {
			return null;
		}

		this.detach(nodeToRemove);

		return nodeToRemove;
	}

	/**************************************
	* Place method
	*
	**************************************/
	public Node place(Node node, int key) {
		//DEBUG------------------------------
		System.out.print("node key: ");
		if(node != null)
			System.out.println(node.key);
		else
			System.out.println("null");
		System.out.println("in key: " + key);
		//-----------------------------------

		//BST
		if(node == null) {
			System.out.println("key " + key + " inserted.");
			Node newNode = new Node(key);
			newNode.rank = 1;
			return newNode;
		}

		if(key < node.key) {
			System.out.println("< Left");
			node.left = place(node.left, key);
			if(node.left != null)
				node.left.parent = node;
		} else if(node.key < key) {
			System.out.println("> Right");
			node.right = place(node.right, key);
			if(node.right != null)
				node.right.parent = node;
		} else {
			System.out.println("Node with key " + key + " already exists.");
			return node;
		}

		//Rerank
		this.setRank(node);

		//Get balance of node
		int balance = getBalance(node);

		//Determine if balanced, and which case is evident if unbalanced
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

		if(balance > 1) {
			//Left-Left case
			Node leftChild = node.left;
			if(key < leftChild.key) {
				return this.rightRotate(node);
			} 
			//Left-Right case
			else if(leftChild.key < key) {
				node.left = this.leftRotate(node.left);
				return this.rightRotate(node);
			}
		} else if (balance < -1) {
			//Right-Right case
			Node rightChild = node.right;
			if(rightChild.key < key) {
				return this.leftRotate(node);
			}
			//Right-Left case
			else if(key < rightChild.key) {
				node.right = this.rightRotate(node.right);
				return leftRotate(node);
			}
		}

		return node;
	}

	/**************************************
	* rightRotate method
	*
	**************************************/	
	public Node rightRotate(Node node) {
		
		//Set nodes to be moved
		Node a = node;
		Node b = node.left;
		Node t3 = b.right;

		//Perform rotation
		b.parent = a.parent;
		a.parent = b;
		if(t3 != null) {t3.parent = a;}
		a.left = t3;
		b.right = a;

		//Update ranks
		a.rank = max(getRank(a.left), getRank(a.right));
		b.rank = max(getRank(b.left), getRank(b.right));

		//Return new root of the subtree
		return b;
	}

	/**************************************
	* leftRotate method
	*
	**************************************/	
	public Node leftRotate(Node node) {
		Node a = node;
		Node b = a.right;
		Node t2 = b.left;

		//Perform rotation
		b.parent = a.parent;
		a.parent = b;
		if(t2 != null) {t2.parent = a;}
		a.right = t2;
		b.left = a;

		//Update heights
		a.rank = max(getRank(a.left), getRank(a.right));
		b.rank = max(getRank(b.left), getRank(b.right));

		//return new root of subtree
		return b;		
	}

	/**************************************
	* max method
	*
	**************************************/	
	public int max(int a, int b) {
		return (a > b) ? a : b;
	}

	/**************************************
	* getRank method
	*
	**************************************/	
	public int getRank(Node node) {
		if(node == null) {
			return 0;
		}
		return node.rank;
	}

	/**************************************
	* getBalance method
	*
	**************************************/	
	public int getBalance(Node node) {
		return getRank(node.left) - getRank(node.right);
	}

	/**************************************
	* setRank method
	*
	**************************************/	
	public void setRank(Node node) {
		if(node == null)
			return;
		setRank(node.left);
		setRank(node.right);
		node.rank = 1 + max(getRank(node.left), getRank(node.right));
	}

	/**************************************
	* setDepth method
	*
	**************************************/	
	public void setDepth(Node node) {
		
		if(node == null)
			return;

		if(node.parent == null)
			node.depth = 0;
		else
			node.depth = node.parent.depth + 1;

		this.setDepth(node.left);
		this.setDepth(node.right);
	}

	/**************************************
	* search method
	*
	**************************************/
	public Node search(Node node, int key) {

		//If key doesn't exist
		if(node == null) {
			return null;
		}

		if(node.key == key) {
			return node;
		}

		if(key < node.key) {
			return search(node.left, key);
		} else if(node.key < key) {
			return search(node.right, key);
		}

		return node;

	}

	public void detach(Node node) {
		Node leftChild = node.left;
		Node rightChild = node.right;

		//Node is the root
		if(node.parent == null) {
		
		}

		if(leftChild == null) {
			rightChild.parent = node.parent;
		}
		if(rightChild == null) {
			leftChild.parent = node.parent;
		}

		node.parent = null;
		node.left = null;
		node.right = null;
	}

	/**************************************
	* inOrderPrint method
	*
	**************************************/	
	public void inOrderPrint(Node node) {
		if(node == null) {
			return;
		}
		inOrderPrint(node.left);
		System.out.println(node);
		inOrderPrint(node.right);
	}

}