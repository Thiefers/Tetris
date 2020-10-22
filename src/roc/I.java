package roc;

public class I extends Tetromino {
	public I() {
		/**
		 * I型四格方块
		 */
		cells[0] = new Cell(0, 4, NormalJPanel.I);
		cells[1] = new Cell(0, 3, NormalJPanel.I);
		cells[2] = new Cell(0, 5, NormalJPanel.I);
		cells[3] = new Cell(0, 6, NormalJPanel.I);
		/**
		 * I型两种形态
		 */
		states = new State[] { 
				new State(0, 0, 0, -1, 0, 1, 0, 2),
				new State(0, 0, -1, 0, 1, 0, 2, 0),
		};
		states_b = new State[] {
				new State(0, 0, 0, -1, 0, 1, 0, 2),
				new State(0, 0, -1, 0, 1, 0, 2, 0),
				new State(0, 0, 0, -1, 0, 1, 1, -1),
				new State(0, 0, -1, 0, 1, 0, -1, -1),
				new State(0, 0, 0, 1, 0, -1, -1, 1),
				new State(0, 0, 1, 0, -1, 0, 1, 1),
				new State(0, 0, 0, -1, 0, 1, 1, 1),
				new State(0, 0, -1, 0, 1, 0, 1, -1),
				new State(0, 0, 0, 1, 0, -1, -1, -1),
				new State(0, 0, 1, 0, -1, 0, -1, 1),
				new State(0, 0, 0, 1, 1, 0, 1, 1),
				new State(0, 0, 0, 1, 1, -1, 1, 0),
				new State(0, 0, 1, 0, -1, -1, 0, -1),
				new State(0, 0, 0, -1, 0, 1, 1, 0),
				new State(0, 0, -1, 0, 1, 0, 0, -1),
				new State(0, 0, 0, 1, 0, -1, -1, 0),
				new State(0, 0, 1, 0, -1, 0, 0, 1),
				new State(0, 0, -1, -1, -1, 0, 0, 1),
				new State(0, 0, -1, 1, 0, 1, 1, 0)
		};
	}
}
