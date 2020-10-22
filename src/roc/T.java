package roc;

public class T extends Tetromino {
	public T() {
		/**
		 * T型四格方块
		 */
		cells[0] = new Cell(0, 4, NormalJPanel.T);
		cells[1] = new Cell(0, 3, NormalJPanel.T);
		cells[2] = new Cell(0, 5, NormalJPanel.T);
		cells[3] = new Cell(1, 4, NormalJPanel.T);
		/**
		 * T型四种形态
		 */
		states = new State[] {
				new State(0, 0, 0, -1, 0, 1, 1, 0),
				new State(0, 0, -1, 0, 1, 0, 0, -1),
				new State(0, 0, 0, 1, 0, -1, -1, 0),
				new State(0, 0, 1, 0, -1, 0, 0, 1),
		};
		states_b = new State[] {
				new State(0, 0, 0, -1, 0, 1, 1, 0),
				new State(0, 0, -1, 0, 1, 0, 0, -1),
				new State(0, 0, 0, 1, 0, -1, -1, 0),
				new State(0, 0, 1, 0, -1, 0, 0, 1),
				new State(0, 0, 0, -1, 0, 1, 0, 2),
				new State(0, 0, -1, 0, 1, 0, 2, 0),
				new State(0, 0, 0, -1, 0, 1, 1, -1),
				new State(0, 0, -1, 0, 1, 0, -1, -1),
				new State(0, 0, 1, 0, -1, 0, 1, 1),
				new State(0, 0, 1, 0, -1, 0, -1, 1),
				new State(0, 0, 0, -1, 0, 1, 1, 1),
				new State(0, 0, -1, 0, 1, 0, 1, -1),
				new State(0, 0, 0, 1, 0, -1, -1, -1),
				new State(0, 0, 0, 1, 0, -1, -1, 1),
				new State(0, 0, -1, 0, 1, 0, 0, -1),
				new State(0, 0, 0, 1, 1, -1, 1, 0),
				new State(0, 0, 1, 0, -1, -1, 0, -1),
				new State(0, 0, 0, 1, 1, 0, 1, 1),
				new State(0, 0, -1, -1, -1, 0, 0, 1),
				new State(0, 0, 0, -1, 0, 1, 1, 0),
				new State(0, 0, 0, 1, 0, -1, -1, 0),
				new State(0, 0, 1, 0, -1, 0, 0, 1),
				new State(0, 0, -1, 1, 0, 1, 1, 0)
		};
	}
}
