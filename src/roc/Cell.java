package roc;

import java.awt.Image;

/**
 * @author 14036 ���� ÿһ�����Ӷ����Լ��������С��С�ͼƬ
 */
public class Cell {

	/** ���������� */
	private int row;
	/** ���������� */
	private int col;
	/** ������ͼ */
	private Image image;

	/** �޲ι����� */
	public Cell() {
	}

	/** �вι����� */
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
	 * ��������
	 */
	public void moveRight() {
		col++;
	}

	/**
	 * ��������
	 */
	public void moveLeft() {
		col--;
	}

	/**
	 * ��������
	 */
	public void moveDown() {
		row++;
	}

	@Override
	public String toString() {
		return "Cell [row=" + row + ", col=" + col + ", toString()=" + super.toString() + "]";
	}

}
