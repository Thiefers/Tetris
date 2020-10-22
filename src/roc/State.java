package roc;
/**
 * @author 14036
 * 存放四格方块的各种形态
 */
public class State {
		/** 方块0的行、列*/
		int row0;
		int col0;
		/** 方块1的行、列*/
		int row1;
		int col1;
		/** 方块2的行、列*/
		int row2;
		int col2;
		/** 方块3的行、列*/
		int row3;
		int col3;
		/** 构造器*/
		public State(int row0, int col0, int row1, int col1, int row2, int col2, int row3, int col3) {
			super();
			this.row0 = row0;
			this.col0 = col0;
			this.row1 = row1;
			this.col1 = col1;
			this.row2 = row2;
			this.col2 = col2;
			this.row3 = row3;
			this.col3 = col3;
	}

}
