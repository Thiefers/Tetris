package roc;

public class Z extends Tetromino {
	public Z() {
		/**
		 * Z型四格方块
		 */
		cells[0] = new Cell(1, 4, NormalJPanel.Z);
		cells[1] = new Cell(0, 3, NormalJPanel.Z);
		cells[2] = new Cell(0, 4, NormalJPanel.Z);
		cells[3] = new Cell(1, 5, NormalJPanel.Z);
		/**
		 * Z型两种形态
		 */
		states = new State[] {
			new State(0, 0, -1, -1, -1, 0, 0, 1),
			new State(0, 0, -1, 1, 0, 1, 1, 0),
		};
		states_b = new State[] {
				new State(0, 0, -1, -1, -1, 0, 0, 1),
				new State(0, 0, -1, 1, 0, 1, 1, 0),
				new State(0, 0, 0, -1, 0, 1, 0, 2),
				new State(0, 0, -1, 0, 1, 0, -1, -1),
				new State(0, 0, 0, -1, 0, 1, 1, -1),
				new State(0, 0, -1, 0, 1, 0, 1, -1),
				new State(0, 0, 0, 1, 0, -1, -1, 1),
				new State(0, 0, -1, 0, 1, 0, 2, 0),
				new State(0, 0, 1, 0, -1, 0, 1, 1),
				new State(0, 0, 0, 1, 1, 0, 1, 1),
				new State(0, 0, 0, 1, 1, -1, 1, 0),
				new State(0, 0, 1, 0, -1, 0, 0, 1),
				new State(0, 0, 0, -1, 0, 1, 1, 1),
				new State(0, 0, 1, 0, -1, -1, 0, -1),
				new State(0, 0, 1, 0, -1, 0, -1, 1),
				new State(0, 0, 0, 1, 0, -1, -1, -1),
				new State(0, 0, 0, -1, 0, 1, 1, 0),
				new State(0, 0, -1, 0, 1, 0, 0, -1),
				new State(0, 0, 0, 1, 0, -1, -1, 0),
				new State(0, 0, -1, 1, 0, 1, 1, 0),
				new State(0, 0, -1, -1, -1, 0, 0, 1)
			};
	}
}
