
public class GraphicalObject {
	int id, width, height;
	String type;
	Location position;
	BinarySearchTree BST;

	/**
	 * Constructor
	 * 
	 * @param id
	 *            is the identifier of this graphical object
	 * @param width
	 *            is the width of the enclosing rectangle for this graphical
	 *            object
	 * @param height
	 *            is the height of the enclosing rectangle for this graphical
	 *            object
	 * @param type
	 *            its type (fixed, user, computer, or target)
	 * @param pos
	 *            is the offset of the object
	 */
	public GraphicalObject(int id, int width, int height, String type, Location pos) {
		this.id = id;
		this.width = width;
		this.height = height;
		this.type = type;
		this.position = pos;
		this.BST = new BinarySearchTree();
	}

	/**
	 * Returns the offset of this graphical object
	 * 
	 * @return
	 */
	public Location getOffset() {
		return position;
	}

	/**
	 * Changes the offset of this graphical object to the specified value
	 * 
	 * @param position
	 */
	public void setOffset(Location position) {
		this.position = position;
	}

	/**
	 * Sets the type of this graphical object to the specified value.
	 * 
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Returns the id of this graphical object
	 * 
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * Returns the width of the enclosing rectangle for this graphical object.
	 * 
	 * @return
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Returns the height of the enclosing rectangle for this graphical object.
	 * 
	 * @return
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Returns the type of this graphical object.
	 * 
	 * @return
	 */
	public String getType() {
		return type;
	}

	/**
	 * Inserts pix into the binary search tree associated with this graphical
	 * object. Throws a DuplicatedKeyException if an error occurs when inserting
	 * the Pixel into the tree.
	 * 
	 * @param pix
	 * @throws DuplicatedKeyException
	 */
	public void addPixel(Pixel pix) throws DuplicatedKeyException {
		if (BST.get(BST.r, pix.getLocation()) != null) {
			throw new DuplicatedKeyException("Duplicated key");
		} else {
			BST.put(BST.r, pix);
		}
	}

	/**
	 * Returns true if this graphical object intersects the one specified in the
	 * parameter. It returns false otherwise.
	 * 
	 * @param gobj
	 * @return
	 */
	public boolean intersects(GraphicalObject gobj) {
		// else if the object does not cross any borders, check if it intersects the other graphical object
		// for them to overlap must have a pixel in common (use for loop from assignment pdf)
		Pixel thisPix = BST.smallest(BST.r);
		while(thisPix != null){
			// check if the location (x',y') is in the BST of the other objext
			// x' = x + xf - xf' and y' = y + yf - yf'
			int xintersect = thisPix.location.xcord + this.position.xcord - gobj.position.xcord;
			int yintersect = thisPix.location.ycord + this.position.ycord - gobj.position.ycord;
			
			Location newlocation = new Location(xintersect,yintersect);
			
			if(gobj.findPixel(newlocation) == true){
				return true;
			}
			else{
				thisPix = BST.successor(BST.r,thisPix.getLocation());
			}
		}
		return false;
	}

	// returns true if pixel is found in graphical object false if not
	private boolean findPixel(Location p) {
		// if this location is in your binary search tree
		if(BST.get(BST.r, p) == null){
			return false;
		}
		else{
			return true;
		}

	}
}
