package hw4.maze.classes;

public class Cell {
	private CellComponents left;
	private CellComponents right;
	private CellComponents up;
	private CellComponents down;
	
	/**
	 * Takes in CellComponent members for the left, right, up, and down attributes of a cell
	 * and returns a created Cell object with those attributes.
	 * @param left
	 * @param right
	 * @param up
	 * @param down
	 */
	public Cell(CellComponents left, CellComponents right, CellComponents up, CellComponents down) {
		super();
		this.left = left;
		this.right = right;
		this.up = up;
		this.down = down;
	}
	public CellComponents getLeft() {
		return left;
	}
	public void setLeft(CellComponents left) {
		if (left == null) {
			this.left = CellComponents.WALL;
		} else {
			this.left = left;
		}
	}
	public CellComponents getRight() {
		return right;
	}
	public void setRight(CellComponents right) {
		if (right == null) {
			this.right = CellComponents.WALL;
		} else {
			this.right = right;
		}
	}
	public CellComponents getUp() {
		return up;
	}
	public void setUp(CellComponents up) {
		if (up == null) {
			this.up = CellComponents.WALL;
		} else {
			this.up = up;
		}
	}
	public CellComponents getDown() {
		return down;
	}
	public void setDown(CellComponents down) {
		if (down == null) {
			this.down = CellComponents.WALL;
		} else {
			this.down = down;
		}
	}
	
	/**
	 * Converts a Cell into string format.
	 */
	@Override
	public String toString() {
		return "Cell [left=" + this.getLeft() + ", right=" + this.getRight() + ", up=" + this.getUp() + ", down=" + this.getDown() +  "]";
	}
}
