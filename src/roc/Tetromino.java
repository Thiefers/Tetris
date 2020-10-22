package roc;

import java.util.Arrays;
import java.util.Random;

/**
 * @author 14036 �ĸ񷽿�
 */
public class Tetromino {
	/** 4��С���� */
	protected Cell[] cells = new Cell[4];
	/** ����״̬ */
	protected State[] states;
	protected State[] states_b;
	/** ��¼��ת����������ֵ */
	private int index = 100000;

	/**
	 * ��������ĸ񷽿�
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
	 * ����
	 */
	public void softDrop() {
		for (int i = 0; i < cells.length; i++) {
			this.cells[i].moveDown();
		}
	}

	/**
	 * ����
	 */
	public void moveLeft() {
		for (int i = 0; i < cells.length; i++) {
			this.cells[i].moveLeft();
		}
	}

	/**
	 * ����
	 */
	public void moveRight() {
		for (int i = 0; i < cells.length; i++) {
			this.cells[i].moveRight();
		}
	}

	/**
	 * ����ת
	 */
	/** ��ͨģʽ��ת */
	public void rotateRight() {
		index++;
		// ��ȡ���仯����̬��
		State s = states[index % states.length];
		// ��ȡ��ǰ��
		Cell o = cells[0];
		// �����Ӧ��̬�ĸ���λ�õĺ���Ϊ��ת��ĸ���λ��
		cells[1].setRow(o.getRow() + s.row1);
		cells[1].setCol(o.getCol() + s.col1);
		cells[2].setRow(o.getRow() + s.row2);
		cells[2].setCol(o.getCol() + s.col2);
		cells[3].setRow(o.getRow() + s.row3);
		cells[3].setCol(o.getCol() + s.col3);
	}

	/** ǧ��ģʽ��ת */
	public void rotateRightB() {
		index++;
		// ��ȡ���仯����̬��
		State s = states_b[index % states_b.length];
		// ��ȡ��ǰ��
		Cell o = cells[0];
		// �����Ӧ��̬�ĸ���λ�õĺ���Ϊ��ת��ĸ���λ��
		cells[1].setRow(o.getRow() + s.row1);
		cells[1].setCol(o.getCol() + s.col1);
		cells[2].setRow(o.getRow() + s.row2);
		cells[2].setCol(o.getCol() + s.col2);
		cells[3].setRow(o.getRow() + s.row3);
		cells[3].setCol(o.getCol() + s.col3);
	}

	/**
	 * ����ת��������ת���ƣ�
	 */
	/** ��ͨģʽ��ת */
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

	/** ǧ��ģʽ��ת */
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
	 * ȡ����
	 */
	public Cell[] getCells() {
		return cells;
	}

	@Override
	public String toString() {
		return "Tetromino [cells=" + Arrays.toString(cells) + ", toString()=" + super.toString() + "]";
	}

}
