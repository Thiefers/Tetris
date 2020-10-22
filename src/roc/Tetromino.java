package roc;

import java.util.Arrays;
import java.util.Random;

/**
 * @author 14036 四格方块
 */
public class Tetromino {
	/** 4格小方块 */
	protected Cell[] cells = new Cell[4];
	/** 方块状态 */
	protected State[] states;
	protected State[] states_b;
	/** 记录旋转次数，任意值 */
	private int index = 100000;

	/**
	 * 随机生成四格方块
	 */
	public static Tetromino randomTetromino() {
		Random rnd = new Random();
		int type = rnd.nextInt(7);
		switch (type) {
		case 0:
			return new T();
		case 1:
			return new I();
		case 2:
			return new O();
		case 3:
			return new L();
		case 4:
			return new J();
		case 5:
			return new S();
		case 6:
			return new Z();
		}
		return null;
	}

	/**
	 * 下落
	 */
	public void softDrop() {
		for (int i = 0; i < cells.length; i++) {
			this.cells[i].moveDown();
		}
	}

	/**
	 * 左移
	 */
	public void moveLeft() {
		for (int i = 0; i < cells.length; i++) {
			this.cells[i].moveLeft();
		}
	}

	/**
	 * 右移
	 */
	public void moveRight() {
		for (int i = 0; i < cells.length; i++) {
			this.cells[i].moveRight();
		}
	}

	/**
	 * 右旋转
	 */
	/** 普通模式旋转 */
	public void rotateRight() {
		index++;
		// 获取将变化的形态码
		State s = states[index % states.length];
		// 获取当前轴
		Cell o = cells[0];
		// 轴与对应形态的格子位置的和作为旋转后的格子位置
		cells[1].setRow(o.getRow() + s.row1);
		cells[1].setCol(o.getCol() + s.col1);
		cells[2].setRow(o.getRow() + s.row2);
		cells[2].setCol(o.getCol() + s.col2);
		cells[3].setRow(o.getRow() + s.row3);
		cells[3].setCol(o.getCol() + s.col3);
	}

	/** 千变模式旋转 */
	public void rotateRightB() {
		index++;
		// 获取将变化的形态码
		State s = states_b[index % states_b.length];
		// 获取当前轴
		Cell o = cells[0];
		// 轴与对应形态的格子位置的和作为旋转后的格子位置
		cells[1].setRow(o.getRow() + s.row1);
		cells[1].setCol(o.getCol() + s.col1);
		cells[2].setRow(o.getRow() + s.row2);
		cells[2].setCol(o.getCol() + s.col2);
		cells[3].setRow(o.getRow() + s.row3);
		cells[3].setCol(o.getCol() + s.col3);
	}

	/**
	 * 左旋转（与右旋转类似）
	 */
	/** 普通模式旋转 */
	public void rotateLeft() {
		index--;
		State s = states[index % states.length];
		Cell o = cells[0];
		cells[1].setRow(o.getRow() + s.row1);
		cells[1].setCol(o.getCol() + s.col1);
		cells[2].setRow(o.getRow() + s.row2);
		cells[2].setCol(o.getCol() + s.col2);
		cells[3].setRow(o.getRow() + s.row3);
		cells[3].setCol(o.getCol() + s.col3);
	}

	/** 千变模式旋转 */
	public void rotateLeftB() {
		index--;
		State s = states_b[index % states_b.length];
		Cell o = cells[0];
		cells[1].setRow(o.getRow() + s.row1);
		cells[1].setCol(o.getCol() + s.col1);
		cells[2].setRow(o.getRow() + s.row2);
		cells[2].setCol(o.getCol() + s.col2);
		cells[3].setRow(o.getRow() + s.row3);
		cells[3].setCol(o.getCol() + s.col3);
	}

	/**
	 * 取方块
	 */
	public Cell[] getCells() {
		return cells;
	}

	@Override
	public String toString() {
		return "Tetromino [cells=" + Arrays.toString(cells) + ", toString()=" + super.toString() + "]";
	}

}
