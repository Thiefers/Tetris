package roc;

public class S extends Tetromino {
	public S() {
		/**
		 * S型四格方块
		 */
		cells[0] = new Cell(0, 4, NormalJPanel.S);
		cells[1] = new Cell(0, 5, NormalJPanel.S);
		cells[2] = new Cell(1, 3, NormalJPanel.S);
		cells[3] = new Cell(1, 4, NormalJPanel.S);
		/**
		 * S型四种形态
		 */
		states = new State[] {
			new State(0, 0, 0, 1, 1, -1, 1, 0),
			new State(0, 0, 1, 0, -1, -1, 0, -1),
		};
		states_b = new State[] {
				new State(0, 0, 0, 1, 1, -1, 1, 0),
				new State(0, 0, 1, 0, -1, -1, 0, -1),
				new State(0, 0, 0, -1, 0, 1, 0, 2),
				new State(0, 0, -1, 0, 1, 0, -1, -1),
				new State(0, 0, -1, 0, 1, 0, 2, 0),
				new State(0, 0, 0, -1, 0, 1, 1, -1),
				new State(0, 0, 0, 1, 0, -1, -1, 1),
				new State(0, 0, 0, 1, 1, 0, 1, 1),
				new State(0, 0, -1, 0, 1, 0, 0, -1),
				new State(0, 0, 1, 0, -1, 0, 1, 1),
				new State(0, 0, -1, 0, 1, 0, 1, -1),
				new State(0, 0, 0, 1, 0, -1, -1, -1),
				new State(0, 0, 1, 0, -1, 0, -1, 1),
				new State(0, 0, 0, -1, 0, 1, 1, 1),
				new State(0, 0, 0, 1, 1, -1, 1, 0),
				new State(0, 0, 0, -1, 0, 1, 1, 0),
				new State(0, 0, 1, 0, -1, -1, 0, -1),
				new State(0, 0, 0, 1, 0, -1, -1, 0),
				new State(0, 0, 1, 0, -1, 0, 0, 1),
				new State(0, 0, -1, -1, -1, 0, 0, 1),
				new State(0, 0, -1, 1, 0, 1, 1, 0)
			};	
	}
}
