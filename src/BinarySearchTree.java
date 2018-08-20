
public class BinarySearchTree {
	BinaryNode r;

	public BinarySearchTree() {
		r = new BinaryNode();
	}

	/**
	 * Returns the Pixel storing the given key, if the key is stored in the
	 * tree; returns null otherwise.
	 * 
	 * @param r
	 * @param key
	 * @return
	 **/
	public Pixel get(BinaryNode r, Location key) {
		// returns null if cannot find and goes to a leaf
		if (r.isLeaf()) {
			return r.getData();
		} else {
			// if the roots location is the same as the key (compareTo returns
			// 0) return the pixel in the root
			if (r.getData().location.compareTo(key) == 0) {
				return r.getData();
			}
			// if the roots location is > the key (compareTo returns 1) get the
			// left subtree
			else if (r.getData().location.compareTo(key) == 1) {
				return get(r.left, key);
			}
			// else (the key is at a location > than the root) get the right
			// subtree
			else {
				return get(r.right, key);
			}
		}
	}

	/**
	 * Inserts the given data in the tree if no data item with the same key is
	 * already there. If a node already stores the same key, the algorithm
	 * throws a DuplicatedKeyException
	 * 
	 * @param r
	 * @param data
	 * @throws DuplicatedKeyException
	 */
	public void put(BinaryNode r, Pixel data) throws DuplicatedKeyException {
		BinaryNode p = getNode(r, data.getLocation());
		if (p.isLeaf() == false) {
			throw new DuplicatedKeyException("Duplicated key");
		} else {
			p.setData(data);
			p.left = new BinaryNode();
			p.right = new BinaryNode();
			p.left.setParent(p);
			p.right.setParent(p);
		}
	}

	/**
	 * Removes the data item with the given key, if the key is stored in the
	 * tree; throws an InexistentKeyException otherwise.
	 * 
	 * @param r
	 * @param key
	 * @throws InexistentKeyException
	 */
	public void remove(BinaryNode r, Location key) throws InexistentKeyException {
		BinaryNode p = getNode(r, key);
		// if p == null, p is a leaf therefore it cannot be removed, ERROR
		if (p.isLeaf()) {
			throw new InexistentKeyException("inexistent key" + key);
		} else {
			// if at least one child c of p is a leaf
			if (p.left.isLeaf() || p.right.isLeaf()) {
				// if p is the root
				if(getRoot().equals(p)){
					// if both children are leaves
					if(p.left.isLeaf() && p.right.isLeaf()){
						p.setLeft(null);
						p.setRight(null);
						p.setData(null);
					}
					// if only left child is a leaf
					else if(p.left.isLeaf()){
						BinaryNode c = p.right;
						c = r;
						c.parent = null;
					}
					// if only right child is a leaf
					else{
						BinaryNode c = p.left;
						c = r;
						c.parent = null;
					}
				}
				// if p is not the root
				else{
					if(p.left.isLeaf() && p.right.isLeaf()){
						p.setLeft(null);
						p.setRight(null);
						p.setData(null);
					}
					// if only left child is a leaf
					else if(p.left.isLeaf()){
						p.setData(p.right.getData());
						BinaryNode tempright = p.right.getRight();
						BinaryNode templeft = p.right.getLeft();
						p.setRight(tempright);
						p.setLeft(templeft);
					}
					// if only right child is a leaf
					else{
						p.setData(p.left.getData());
						BinaryNode tempright = p.left.getRight();
						BinaryNode templeft = p.left.getLeft();
						p.setRight(tempright);
						p.setLeft(templeft);
					}
				}
			}
			// if both children are internal nodes
			else if (p.right.isLeaf() == false && p.left.isLeaf() == false) {
				BinaryNode s = smallestNode(p.right);
				p.setData(s.getData());
				s.right.parent = s.parent;
				p.setRight(s.right);
			}
		}

	}

	/**
	 * Returns the root of the binary search tree.
	 * 
	 * @return
	 */
	public BinaryNode getRoot() {
		return r;
	}

	/**
	 * Returns the Pixel with the smallest key larger than the given one (note
	 * that the tree does not need to store a node with the given key). Returns
	 * null if the given key has no successor.
	 * 
	 * @param r
	 * @param key
	 * @return
	 */
	public Pixel successor(BinaryNode r, Location key) {
		BinaryNode p = getNode(r, key);
		// if p is an internal node and p.right is an internal node
		if (p.isLeaf() == false && p.right.isLeaf() == false) {
			return smallest(p.right);
		} else {
			BinaryNode pparent = p.parent;
			while (pparent != null && pparent.right == p) {
				p = pparent;
				pparent = pparent.parent;
			}
			// parent of the root is null
			if (pparent == null) {
				return null;
			} else {
				return pparent.getData();
			}
		}
	}

	/**
	 * Returns the Pixel with the largest key smaller than the given one (note
	 * that the tree does not need to store a node with the given key). Returns
	 * null if the given key has no predecessor.
	 * 
	 * @param r
	 * @param key
	 * @return
	 */
	public Pixel predecessor(BinaryNode r, Location key) {
		BinaryNode p = getNode(r, key);
		// if p is an internal node and p.left is an internal node
		if (p.isLeaf() == false && p.left.isLeaf() == false) {
			return largest(p.left);
		} else {
			BinaryNode pparent = p.parent;
			while (pparent != null && pparent.left == p) {
				p = pparent;
				pparent = pparent.parent;
			}
			if (pparent == null) {
				return null;
			} else {
				return pparent.getData();
			}
		}
	}

	/**
	 * Returns the Pixel with the smallest key. Throws an EmptyTreeException if
	 * the tree does not contain any data
	 * 
	 * @param r
	 * @return
	 * @throws EmptyTreeException
	 */
	public Pixel smallest(BinaryNode r) throws EmptyTreeException {
		if (r.isLeaf()) {
			throw new EmptyTreeException();
		} else if (r.left.isLeaf()) {
			return r.getData();
		} else {
			return smallest(r.left);
		}
	}

	/**
	 * Returns the Pixel with the largest key. Throws an EmptyTreeException if
	 * the tree does not contain any data.
	 * 
	 * @param r
	 * @return
	 * @throws EmptyTreeException
	 */
	public Pixel largest(BinaryNode r) throws EmptyTreeException {
		if (r.isLeaf()) {
			throw new EmptyTreeException();
		} else if (r.right.isLeaf()) {
			return r.getData();
		} else {
			return largest(r.right);
		}
	}

	private BinaryNode getNode(BinaryNode r, Location key) {
		// if cannot find and goes to a leaf returns leaf where key would be
		if (r.isLeaf()) {
			return r;
		} else {
			// if the roots key is the same as the key being looked for
			// (compareTo returns
			// 0) return the pixel in the root
			if (r.getData().location.compareTo(key) == 0) {
				return r;
			}
			// if the roots key is > the key (compareTo returns 1) get the
			// left subtree
			else if (r.getData().location.compareTo(key) == 1) {
				return getNode(r.left, key);
			}
			// else (the key is > than the root) get the right
			// subtree
			else {
				return getNode(r.right, key);
			}
		}
	}

	private BinaryNode smallestNode(BinaryNode r) {
		if (r.isLeaf()) {
			throw new EmptyTreeException();
		} else if (r.left.isLeaf()) {
			return r;
		} else {
			return smallestNode(r.left);
		}
	}

}
