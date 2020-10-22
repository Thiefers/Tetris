package roc;

import java.awt.Image;

/**
 * @author 14036 格子 每一个格子都有自己所属的行、列、图片
 */
public class Cell {

	/** 格子所在行 */
	private int row;
	/** 格子所在列 */
	private int col;
	/** 格子贴图 */
	private Image image;

	/** 无参构造器 */
	public Cell() {
	}

	/** 有参构造器 */
	public Cell(int row, int col, Image image) {
		super();
		this.row = row;
		this.col = col;
		this.image = image;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	/**
	 * 格子右移
	 */
	public void moveRight() {
		col++;
	}

	/**
	 * 格子左移
	 */
	public void moveLeft() {
		col--;
	}

	/**
	 * 格子下落
	 */
	public void moveDown() {
		row++;
	}

	@Override
	public String toString() {
		return "Cell [row=" + row + ", col=" + col + ", toString()=" + super.toString() + "]";
	}

}
