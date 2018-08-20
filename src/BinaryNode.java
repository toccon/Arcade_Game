
public class BinaryNode {
	Pixel value;
	BinaryNode left;
	BinaryNode right;
	BinaryNode parent;

	public BinaryNode() {
		this.value = null;
		this.left = null;
		this.right = null;
		this.parent = null;
	}
	public BinaryNode(Pixel value, BinaryNode left, BinaryNode right, BinaryNode parent){
		this.value = value;
		this.left = left;
		this.right = right;
		this.parent = parent;
	}
	public Pixel getData() {
		return value;
	}
	public void setData(Pixel value) {
		this.value = value;
	}
	public BinaryNode getLeft() {
		return left;
	}
	public void setLeft(BinaryNode left) {
		this.left = left;
	}
	public BinaryNode getRight() {
		return right;
	}
	public void setRight(BinaryNode right) {
		this.right = right;
	}
	public BinaryNode getParent() {
		return parent;
	}
	public void setParent(BinaryNode parent) {
		this.parent = parent;
	}
	public boolean isLeaf(){
		if(this.left == null && this.right == null && this.getData() == null) {
			return true;
		}
		return false;
	}
	

}
