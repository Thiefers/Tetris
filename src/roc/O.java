package roc;

public class O extends Tetromino {
	public O() {
		/**
		 * O���ĸ񷽿�
		 */
		cells[0] = new Cell(0, 4, NormalJPanel.O);
		cells[1] = new Cell(0, 5, NormalJPanel.O);
		cells[2] = new Cell(1, 4, NormalJPanel.O);
		cells[3] = new Cell(1, 5, NormalJPanel.O);
		/**
		 * O��һ����̬
		 */
		states = new State[] {
				new State(0, 0, 0, 1, 1, 0, 1, 1),
				new State(0, 0, 0, 1, 1, 0, 1, 1),
		};
		states_b = new State[] {
				new State(0, 0, 0, 1, 1, 0, 1, 1),
				new State(0, 0, 0, 1, 1, 0, 1, 1),
				new State(0, 0, 0, 1, 0, -1, -1, 1),
				new State(0, 0, 0, -1, 0, 1, 0, 2),
				new State(0, 0, 1, 0, -1, 0, 1, 1),
				new State(0, 0, 0, -1, 0, 1, 1, -1),
				new State(0, 0, -1, 0, 1, 0, -1, -1),
				new State(0, 0, 0, -1, 0, 1, 1, 1),
				new State(0, 0, 0, 1, 1, 0, 1, 1),
				new State(0, 0, -1, 0, 1, 0, 2, 0),
				new State(0, 0, -1, 0, 1, 0, 1, -1),
				new State(0, 0, 1, 0, -1, 0, -1, 1),
				new State(0, 0, 0, 1, 1, -1, 1, 0),
				new State(0, 0, 0, 1, 0, -1, -1, 0),
				new State(0, 0, 0, 1, 0, -1, -1, -1),
				new State(0, 0, 1, 0, -1, -1, 0, -1),
				new State(0, 0, -1, 0, 1, 0, 0, -1),
				new State(0, 0, 1, 0, -1, 0, 0, 1),
				new State(0, 0, -1, -1, -1, 0, 0, 1),
				new State(0, 0, 0, -1, 0, 1, 1, 0),
				new State(0, 0, -1, 1, 0, 1, 1, 0)
		};
	}
}
