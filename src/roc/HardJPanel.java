package roc;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class HardJPanel extends JPanel implements MouseListener {
	/** 下落方块 */
	private Tetromino tetromino = Tetromino.randomTetromino();
	/** 提示方块 */
	private Tetromino nextOne = Tetromino.randomTetromino();
	/** TODO总行数 */
	public static final int ROWS = 20;
	/** 总列数 */
	public static final int COLS = 10;
	/** TODO 格子大小 */
	public static final int CELL_SIZE = 26;
	/** TODO 字体颜色 */
	public static final int FONT_COLOR = 0x474B5A;
	/** TODO 字体大小 */
	public static final int FONT_SIZE = 0x20;
	/** 方块墙（游戏框内部，当方块停止下落后嵌入墙中） */
	private Cell[][] wall = new Cell[ROWS][COLS];
	/** 消除对应行数后的得分 */
	private static final int[] SCORE_TABLE = { 0, 1, 5, 10, 20 };
	/** 消除总行数 */
	private int lines;
	/** 得分 */
	private int score;
	/** 暂停信号量 */
	private boolean pause;
	/** 游戏结束信号量 */
	private boolean gameOver = true;
	/** 计时器 */
	private Timer timer;
	private long firstTime = 800;
	/** 背景图片 */
	private static Image background;
	/** 游戏结束图片 */
	private static Image gameover;
	/** nextOne 封锁线 */
	private static Image ban;
	/** 方块图片 */
	public static Image T;
	public static Image I;
	public static Image O;
	public static Image J;
	public static Image L;
	public static Image S;
	public static Image Z;
	/** 游戏信息边框 */
	private static Image window;
	/** 右下角 */
	private static Image index;
	/** 退出游戏按钮 */
	private JButton exitButton;
	/** 开始游戏按钮 */
	private JButton startButton;
	/** 返回模式选择按钮 */
	private JButton backButton;
	/** 退出按钮图标 */
	private ImageIcon exitImageIcon;
	/** 开始按钮图标 */
	private ImageIcon startImageIcon;
	/** 返回按钮图标 */
	private ImageIcon backImageIcon;
	/** 面板及其布局 */
	public JPanel mainJPanel;
	public CardLayout cardLayout;
	/** 像素大小 */
	private static final int SMALLSIZE = 5;
	private static final int BIGSIZE = 54;// 54-2=52
	/** 判断是否重新进入的 */
	private static boolean flag = true;
	/** 加载图片资源 */
	static {
		try {
			index = Tool.getIcon("graphics//18.png").getImage();
			background = Tool.getIcon("graphics//bg03.jpg").getImage();
			window = Tool.getIcon("graphics//Window.png").getImage();
			gameover = Tool.getIcon("graphics//gameover.png").getImage();
			ban = Tool.getIcon("graphics//ban.png").getImage();
			T = Tool.getIcon("graphics//T.png").getImage();
			I = Tool.getIcon("graphics//I.png").getImage();
			O = Tool.getIcon("graphics//O.png").getImage();
			J = Tool.getIcon("graphics//J.png").getImage();
			L = Tool.getIcon("graphics//L.png").getImage();
			S = Tool.getIcon("graphics//S.png").getImage();
			Z = Tool.getIcon("graphics//Z.png").getImage();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void paintComponent(Graphics g) {
		/**
		 * 5 * 5 54 * 5 5 * 5 (0,0,5,5)(5,0,59,5)(59,0,64,5) 5 * 54 54 * 54 5 * 54
		 * (0,5,5,59)(5,5,59,59)(59,5,64,59) 5 * 5 54 * 5 5 * 5
		 * (0,59,5,64)(5,59,59,64)(59,59,64,64)
		 */
		/** 绘制背景图片 SMALLSIZE BIGSIZE 261 522 */
		// SMALLSIZE + BIGSIZE BIGSIZE + SMALLSIZE*2 SMALLSIZE
		g.drawImage(background, 0, 0, 540, 590, null);
		g.drawImage(index, 300, 340, null);
		/**
		 * 绘制游戏框ok
		 */
		/** 左上 */
		g.drawImage(window, 10, 10, 10 + SMALLSIZE, 10 + SMALLSIZE, 0, 0, SMALLSIZE, SMALLSIZE, null);
		/** 中上 */
		g.drawImage(window, 10 + SMALLSIZE, 10, 10 + SMALLSIZE + 262, 10 + SMALLSIZE, SMALLSIZE, 0, SMALLSIZE + BIGSIZE,
				SMALLSIZE, null);
		/** 右上 */
		g.drawImage(window, 10 + SMALLSIZE + 262, 10, 10 + SMALLSIZE * 2 + 262, 10 + SMALLSIZE, SMALLSIZE + BIGSIZE, 0,
				BIGSIZE + SMALLSIZE * 2, SMALLSIZE, null);
		/** 左中 */
		g.drawImage(window, 10, 10 + SMALLSIZE, 10 + SMALLSIZE, 10 + SMALLSIZE + 523, 0, SMALLSIZE, SMALLSIZE,
				SMALLSIZE + BIGSIZE, null);
		/** 中中 */
		g.drawImage(window, 10 + SMALLSIZE, 10 + SMALLSIZE, 10 + SMALLSIZE + 262, 10 + SMALLSIZE + 523, SMALLSIZE + 1,
				SMALLSIZE + 1, BIGSIZE + SMALLSIZE - 1, SMALLSIZE + BIGSIZE - 1, null);
		/** 右中 */
		g.drawImage(window, 10 + SMALLSIZE + 262, 10 + SMALLSIZE, 10 + SMALLSIZE * 2 + 262, 10 + SMALLSIZE + 523,
				SMALLSIZE + BIGSIZE, SMALLSIZE, BIGSIZE + SMALLSIZE * 2, SMALLSIZE + BIGSIZE, null);
		/** 左下 */
		g.drawImage(window, 10, 10 + SMALLSIZE + 523, 10 + SMALLSIZE, 10 + SMALLSIZE * 2 + 523, 0, SMALLSIZE + BIGSIZE,
				SMALLSIZE, BIGSIZE + SMALLSIZE * 2, null);
		/** 中下 */
		g.drawImage(window, 10 + SMALLSIZE, 10 + SMALLSIZE + 523, 10 + SMALLSIZE + 262, 10 + SMALLSIZE * 2 + 523,
				SMALLSIZE, SMALLSIZE + BIGSIZE, SMALLSIZE + BIGSIZE, BIGSIZE + SMALLSIZE * 2, null);
		/** 右下 */
		g.drawImage(window, 10 + SMALLSIZE + 262, 10 + SMALLSIZE + 523, 10 + SMALLSIZE * 2 + 262,
				10 + SMALLSIZE * 2 + 523, SMALLSIZE + BIGSIZE, SMALLSIZE + BIGSIZE, BIGSIZE + SMALLSIZE * 2,
				BIGSIZE + SMALLSIZE * 2, null);
		/**
		 * 绘制NextOne框ok
		 */
		/** 左上 */
		g.drawImage(window, 288, 10, 288 + SMALLSIZE, 10 + SMALLSIZE, 0, 0, SMALLSIZE, SMALLSIZE, null);
		/** 中上 */
		g.drawImage(window, 288 + SMALLSIZE, 10, 288 + SMALLSIZE + 216, 10 + SMALLSIZE, SMALLSIZE, 0,
				SMALLSIZE + BIGSIZE, SMALLSIZE, null);
		/** 右上 */
		g.drawImage(window, 288 + SMALLSIZE + 216, 10, 288 + SMALLSIZE * 2 + 216, 10 + SMALLSIZE, SMALLSIZE + BIGSIZE,
				0, BIGSIZE + SMALLSIZE * 2, SMALLSIZE, null);
		/** 左中 */
		g.drawImage(window, 288, 10 + SMALLSIZE, 288 + SMALLSIZE, 10 + SMALLSIZE + 108, 0, SMALLSIZE, SMALLSIZE,
				SMALLSIZE + BIGSIZE, null);
		/** 中中 */
		g.drawImage(window, 288 + SMALLSIZE, 10 + SMALLSIZE, 288 + SMALLSIZE + 216, 10 + SMALLSIZE + 108, SMALLSIZE + 1,
				SMALLSIZE + 1, BIGSIZE + SMALLSIZE - 1, SMALLSIZE + BIGSIZE - 1, null);
		/** 右中 */
		g.drawImage(window, 288 + SMALLSIZE + 216, 10 + SMALLSIZE, 288 + SMALLSIZE * 2 + 216, 10 + SMALLSIZE + 108,
				SMALLSIZE + BIGSIZE, SMALLSIZE, BIGSIZE + SMALLSIZE * 2, SMALLSIZE + BIGSIZE, null);
		/** 左下 */
		g.drawImage(window, 288, 10 + SMALLSIZE + 108, 288 + SMALLSIZE, 10 + SMALLSIZE * 2 + 108, 0,
				SMALLSIZE + BIGSIZE, SMALLSIZE, BIGSIZE + SMALLSIZE * 2, null);
		/** 中下 */
		g.drawImage(window, 288 + SMALLSIZE, 10 + SMALLSIZE + 108, 288 + SMALLSIZE + 216, 10 + SMALLSIZE * 2 + 108,
				SMALLSIZE, SMALLSIZE + BIGSIZE, SMALLSIZE + BIGSIZE, BIGSIZE + SMALLSIZE * 2, null);
		/** 右下 */
		g.drawImage(window, 288 + SMALLSIZE + 216, 10 + SMALLSIZE + 108, 288 + SMALLSIZE * 2 + 216,
				10 + SMALLSIZE * 2 + 108, SMALLSIZE + BIGSIZE, SMALLSIZE + BIGSIZE, BIGSIZE + SMALLSIZE * 2,
				BIGSIZE + SMALLSIZE * 2, null);
		/**
		 * 绘制分数框
		 */
		/** 左上 */
		g.drawImage(window, 288, 135, 288 + SMALLSIZE, 135 + SMALLSIZE, 0, 0, SMALLSIZE, SMALLSIZE, null);
		/** 中上 */
		g.drawImage(window, 288 + SMALLSIZE, 135, 288 + SMALLSIZE + 216, 135 + SMALLSIZE, SMALLSIZE, 0,
				SMALLSIZE + BIGSIZE, SMALLSIZE, null);
		/** 右上 */
		g.drawImage(window, 288 + SMALLSIZE + 216, 135, 288 + SMALLSIZE * 2 + 216, 135 + SMALLSIZE, SMALLSIZE + BIGSIZE,
				0, BIGSIZE + SMALLSIZE * 2, SMALLSIZE, null);
		/** 左中 */
		g.drawImage(window, 288, 135 + SMALLSIZE, 288 + SMALLSIZE, 135 + SMALLSIZE + 48, 0, SMALLSIZE, SMALLSIZE,
				SMALLSIZE + BIGSIZE, null);
		/** 中中 */
		g.drawImage(window, 288 + SMALLSIZE, 135 + SMALLSIZE, 288 + SMALLSIZE + 216, 135 + SMALLSIZE + 48,
				SMALLSIZE + 1, SMALLSIZE + 1, BIGSIZE + SMALLSIZE - 1, SMALLSIZE + BIGSIZE - 1, null);
		/** 右中 */
		g.drawImage(window, 288 + SMALLSIZE + 216, 135 + SMALLSIZE, 288 + SMALLSIZE * 2 + 216, 135 + SMALLSIZE + 48,
				SMALLSIZE + BIGSIZE, SMALLSIZE, BIGSIZE + SMALLSIZE * 2, SMALLSIZE + BIGSIZE, null);
		/** 左下 */
		g.drawImage(window, 288, 135 + SMALLSIZE + 48, 288 + SMALLSIZE, 135 + SMALLSIZE * 2 + 48, 0,
				SMALLSIZE + BIGSIZE, SMALLSIZE, BIGSIZE + SMALLSIZE * 2, null);
		/** 中下 */
		g.drawImage(window, 288 + SMALLSIZE, 135 + SMALLSIZE + 48, 288 + SMALLSIZE + 216, 135 + SMALLSIZE * 2 + 48,
				SMALLSIZE, SMALLSIZE + BIGSIZE, SMALLSIZE + BIGSIZE, BIGSIZE + SMALLSIZE * 2, null);
		/** 右下 */
		g.drawImage(window, 288 + SMALLSIZE + 216, 135 + SMALLSIZE + 48, 288 + SMALLSIZE * 2 + 216,
				135 + SMALLSIZE * 2 + 48, SMALLSIZE + BIGSIZE, SMALLSIZE + BIGSIZE, BIGSIZE + SMALLSIZE * 2,
				BIGSIZE + SMALLSIZE * 2, null);
		/**
		 * 绘制消除行数框
		 */
		/** 左上 */
		g.drawImage(window, 288, 193, 288 + SMALLSIZE, 193 + SMALLSIZE, 0, 0, SMALLSIZE, SMALLSIZE, null);
		/** 中上 */
		g.drawImage(window, 288 + SMALLSIZE, 193, 288 + SMALLSIZE + 216, 193 + SMALLSIZE, SMALLSIZE, 0,
				SMALLSIZE + BIGSIZE, SMALLSIZE, null);
		/** 右上 */
		g.drawImage(window, 288 + SMALLSIZE + 216, 193, 288 + SMALLSIZE * 2 + 216, 193 + SMALLSIZE, SMALLSIZE + BIGSIZE,
				0, BIGSIZE + SMALLSIZE * 2, SMALLSIZE, null);
		/** 左中 */
		g.drawImage(window, 288, 193 + SMALLSIZE, 288 + SMALLSIZE, 193 + SMALLSIZE + 48, 0, SMALLSIZE, SMALLSIZE,
				SMALLSIZE + BIGSIZE, null);
		/** 中中 */
		g.drawImage(window, 288 + SMALLSIZE, 193 + SMALLSIZE, 288 + SMALLSIZE + 216, 193 + SMALLSIZE + 48,
				SMALLSIZE + 1, SMALLSIZE + 1, BIGSIZE + SMALLSIZE - 1, SMALLSIZE + BIGSIZE - 1, null);
		/** 右中 */
		g.drawImage(window, 288 + SMALLSIZE + 216, 193 + SMALLSIZE, 288 + SMALLSIZE * 2 + 216, 193 + SMALLSIZE + 48,
				SMALLSIZE + BIGSIZE, SMALLSIZE, BIGSIZE + SMALLSIZE * 2, SMALLSIZE + BIGSIZE, null);
		/** 左下 */
		g.drawImage(window, 288, 193 + SMALLSIZE + 48, 288 + SMALLSIZE, 193 + SMALLSIZE * 2 + 48, 0,
				SMALLSIZE + BIGSIZE, SMALLSIZE, BIGSIZE + SMALLSIZE * 2, null);
		/** 中下 */
		g.drawImage(window, 288 + SMALLSIZE, 193 + SMALLSIZE + 48, 288 + SMALLSIZE + 216, 193 + SMALLSIZE * 2 + 48,
				SMALLSIZE, SMALLSIZE + BIGSIZE, SMALLSIZE + BIGSIZE, BIGSIZE + SMALLSIZE * 2, null);
		/** 右下 */
		g.drawImage(window, 288 + SMALLSIZE + 216, 193 + SMALLSIZE + 48, 288 + SMALLSIZE * 2 + 216,
				193 + SMALLSIZE * 2 + 48, SMALLSIZE + BIGSIZE, SMALLSIZE + BIGSIZE, BIGSIZE + SMALLSIZE * 2,
				BIGSIZE + SMALLSIZE * 2, null);
		/**
		 * 绘制游戏状态框
		 */
		/** 左上 */
		g.drawImage(window, 288, 251, 288 + SMALLSIZE, 251 + SMALLSIZE, 0, 0, SMALLSIZE, SMALLSIZE, null);
		/** 中上 */
		g.drawImage(window, 288 + SMALLSIZE, 251, 288 + SMALLSIZE + 216, 251 + SMALLSIZE, SMALLSIZE, 0,
				SMALLSIZE + BIGSIZE, SMALLSIZE, null);
		/** 右上 */
		g.drawImage(window, 288 + SMALLSIZE + 216, 251, 288 + SMALLSIZE * 2 + 216, 251 + SMALLSIZE, SMALLSIZE + BIGSIZE,
				0, BIGSIZE + SMALLSIZE * 2, SMALLSIZE, null);
		/** 左中 */
		g.drawImage(window, 288, 251 + SMALLSIZE, 288 + SMALLSIZE, 251 + SMALLSIZE + 48, 0, SMALLSIZE, SMALLSIZE,
				SMALLSIZE + BIGSIZE, null);
		/** 中中 */
		g.drawImage(window, 288 + SMALLSIZE, 251 + SMALLSIZE, 288 + SMALLSIZE + 216, 251 + SMALLSIZE + 48,
				SMALLSIZE + 1, SMALLSIZE + 1, BIGSIZE + SMALLSIZE - 1, SMALLSIZE + BIGSIZE - 1, null);
		/** 右中 */
		g.drawImage(window, 288 + SMALLSIZE + 216, 251 + SMALLSIZE, 288 + SMALLSIZE * 2 + 216, 251 + SMALLSIZE + 48,
				SMALLSIZE + BIGSIZE, SMALLSIZE, BIGSIZE + SMALLSIZE * 2, SMALLSIZE + BIGSIZE, null);
		/** 左下 */
		g.drawImage(window, 288, 251 + SMALLSIZE + 48, 288 + SMALLSIZE, 251 + SMALLSIZE * 2 + 48, 0,
				SMALLSIZE + BIGSIZE, SMALLSIZE, BIGSIZE + SMALLSIZE * 2, null);
		/** 中下 */
		g.drawImage(window, 288 + SMALLSIZE, 251 + SMALLSIZE + 48, 288 + SMALLSIZE + 216, 251 + SMALLSIZE * 2 + 48,
				SMALLSIZE, SMALLSIZE + BIGSIZE, SMALLSIZE + BIGSIZE, BIGSIZE + SMALLSIZE * 2, null);
		/** 右下 */
		g.drawImage(window, 288 + SMALLSIZE + 216, 251 + SMALLSIZE + 48, 288 + SMALLSIZE * 2 + 216,
				251 + SMALLSIZE * 2 + 48, SMALLSIZE + BIGSIZE, SMALLSIZE + BIGSIZE, BIGSIZE + SMALLSIZE * 2,
				BIGSIZE + SMALLSIZE * 2, null);
		/** 平移绘图坐标系 */
		g.translate(15, 15);
		/** 绘制正在下落的方块 */
		paintTetromino(g);
		/** 绘制下一个方块 */
		paintNextOne(g);
		/** 绘制成绩 */
		paintScore(g);
		/** 绘制方块墙 */
		paintWall(g);
	}

	/**
	 * HardJPanel 构造器
	 * 
	 * @param mainJPanel
	 * @param cardLayout
	 */
	public HardJPanel(JPanel mainJPanel, CardLayout cardLayout) {
		// TODO 自动生成的构造函数存根
		this.mainJPanel = mainJPanel;
		this.cardLayout = cardLayout;
		// 清除系统默认布局
		this.setLayout(null);
		/** 开始按钮 */
		startImageIcon = Tool.getIcon("graphics//start_b.png");
		startButton = new JButton(startImageIcon);
		startButton.setBounds(300, 295, startImageIcon.getIconWidth(), startImageIcon.getIconHeight());
		startButton.setContentAreaFilled(false);
		startButton.setBorderPainted(false);
		this.add(startButton);
		startButton.addMouseListener(this);
		/** 退出按钮 */
		exitImageIcon = Tool.getIcon("graphics//exit_b.png");
		exitButton = new JButton(exitImageIcon);
		exitButton.setBounds(300, 295, exitImageIcon.getIconWidth(), exitImageIcon.getIconHeight());
		exitButton.setContentAreaFilled(false);
		exitButton.setBorderPainted(false);
		exitButton.setVisible(false);
		this.add(exitButton);
		exitButton.addMouseListener(this);
		/** 返回按钮 */
		backImageIcon = Tool.getIcon("graphics//back_b.png");
		backButton = new JButton(backImageIcon);
		backButton.setBounds(400, 295, backImageIcon.getIconWidth(), backImageIcon.getIconHeight());
		backButton.setContentAreaFilled(false);
		backButton.setBorderPainted(false);
		this.add(backButton);
		backButton.addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO 自动生成的方法存根
		if (e.getSource() == startButton) {
			startButton.setVisible(false);
			exitButton.setVisible(true);
			action();
		} else if (e.getSource() == backButton) {
			startButton.setVisible(true);
			exitButton.setVisible(false);
			timer.cancel();
			gameOver = true;
			clearWall();
			repaint();
			this.cardLayout.show(this.mainJPanel, "selectJPanel");
		} else if (e.getSource() == exitButton) {
			System.exit(0);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO 自动生成的方法存根

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO 自动生成的方法存根

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO 自动生成的方法存根

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO 自动生成的方法存根

	}

	/**
	 * TODO 画墙
	 * 
	 * @param g
	 */
	private void paintWall(Graphics g) {
		for (int row = 0; row < wall.length; row++) {
			/** 迭代每一行 */
			Cell[] line = wall[row];
			for (int col = 0; col < line.length; col++) {
				Cell cell = line[col];
				if (cell != null) {
					/** 当前位置有方块时，将其绘制 */
					int x = col * CELL_SIZE - 1;
					int y = row * CELL_SIZE - 1;
					g.drawImage(cell.getImage(), x, y, null);
					if (gameOver == true) {
						g.drawImage(gameover, -5, 40, null);
					}
				}
			}
		}
	}

	/**
	 * TODO 画成绩和状态
	 * 
	 * @param g
	 */
	private void paintScore(Graphics g) {
		Font f = getFont();
		Font font = new Font(f.getName(), Font.BOLD, FONT_SIZE);
		int x = 290;
		int y = 162;
		g.setColor(new Color(FONT_COLOR));
		g.setFont(font);
		String str = "SCORE:" + this.score;
		g.drawString(str, x, y);
		y += 56;
		str = "LINES:" + this.lines;
		g.drawString(str, x, y);
		y += 56;
		str = "[P]ause";
		if (pause) {
			str = "[C]ontinue";
		}
		if (gameOver) {
			str = "[S]tart";
		}
		g.drawString(str, x, y);
	}

	/**
	 * TODO 绘制下一个方块
	 * 
	 * @param g
	 */
	private void paintNextOne(Graphics g) {
		if (gameOver && wall[0][4] != null) {
			g.drawImage(ban, 273, -5, null);
			return;
		}
		Cell[] cells = nextOne.getCells();
		for (Cell c : cells) {
			int x = (c.getCol() + 10) * CELL_SIZE - 1;
			int y = (c.getRow() + 1) * CELL_SIZE - 1;
			g.drawImage(c.getImage(), x, y, null);
		}
	}

	/**
	 * TODO 绘制当前方块
	 * 
	 * @param g
	 */
	private void paintTetromino(Graphics g) {
		if (gameOver) {
			return;
		}
		Cell[] cells = tetromino.getCells();
		for (Cell c : cells) {
			int x = c.getCol() * CELL_SIZE - 1;
			int y = c.getRow() * CELL_SIZE - 1;
			g.drawImage(c.getImage(), x, y, null);
		}
	}

	/**
	 * TODO 清除格子
	 */
	public void clearWall() {
		for (int row = 0; row < ROWS; row++) {
			Arrays.fill(wall[row], null);
		}
	}

	/**
	 * TODO 最上边只下一行
	 */
	public void lastOneLandToWall() {
		Cell[] cells = nextOne.getCells();
		for (Cell c : cells) {
			int row = c.getRow();
			int col = c.getCol();
			if (row == 1 && wall[0][col] == null) {
				wall[0][col] = c;
			}
		}
	}

	/**
	 * TODO 判断游戏是否结束
	 */
	private void checkGameOver() {
		Cell[] cells = nextOne.getCells();
		for (Cell c : cells) {
			int row = c.getRow();
			int col = c.getCol();
			if (wall[row][col] != null) {
				lastOneLandToWall();
				gameOver = true;
				timer.cancel();
				repaint();
				return;
			}
		}
	}

	/**
	 * TODO 判断是否满行 满行则销毁并计分
	 */
	public void destroyLines() {
		int lines = 0;
		for (int row = 0; row < wall.length; row++) {
			if (fullCells(row)) {
				deleteRow(row);
				lines++;
			}
		}
		this.lines += lines;
		this.score += SCORE_TABLE[lines];
	}

	/**
	 * TODO 销毁此行并依次将上一行的方块下移
	 * 
	 * @param row
	 */
	private void deleteRow(int row) {
		for (int i = row; i >= 1; i--) {
			System.arraycopy(wall[i - 1], 0, wall[i], 0, COLS);
		}
		Arrays.fill(wall[0], null);
	}

	/**
	 * TODO 判断一行是否已满
	 * 
	 * @param row
	 * @return
	 */
	private boolean fullCells(int row) {
		Cell[] line = wall[row];
		for (Cell c : line) {
			if (c == null) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 方块不再下落 嵌入墙中
	 */
	private void tetrominoLandToWall() {
		Cell[] cells = tetromino.getCells();
		for (Cell c : cells) {
			int row = c.getRow();
			int col = c.getCol();
			wall[row][col] = c;
		}
	}

	/**
	 * 检查当前的4格方块能够继续下落
	 * 
	 * @return
	 */
	private boolean tetrominoCanDrop() {
		Cell[] cells = tetromino.getCells();
		for (Cell c : cells) {
			int row = c.getRow();
			/** 落到底了 */
			if (row == ROWS - 1) {
				return false;
			}
		}
		for (Cell c : cells) {
			int row = c.getRow();
			int col = c.getCol();
			/** 下方已有方块，不再下落 */
			if (wall[row + 1][col] != null) {
				return false;
			}
		}
		return true;
	}

	/**
	 * TODO 判断方块是否重合
	 * 
	 * @return
	 */
	private boolean coincide() {
		Cell[] cells = tetromino.getCells();
		for (Cell c : cells) {
			int row = c.getRow();
			int col = c.getCol();
			/** 若墙上有格子则发生重合 */
			if (col < 0 || col >= COLS || row < 0 || row >= ROWS || wall[row][col] != null) {
				return true;
			}
		}
		return false;
	}

	/**
	 * TODO 判断方块是否出界
	 * 
	 * @return
	 */
	private boolean outOfBound() {
		Cell[] cells = tetromino.getCells();
		for (Cell c : cells) {
			int col = c.getCol();
			/** 出界 */
			if (col < 0 || col >= COLS) {
				return true;
			}
		}
		return false;
	}

	/**
	 * TODO 游戏设置开始
	 */
	public void action() {
		startAction();
		repaint();
		/** 监听器 */
		KeyAdapter l = new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == KeyEvent.VK_Q) {
					System.exit(0);
				}
				if (gameOver) {
					if (key == KeyEvent.VK_S) {
						startAction();
					}
					return;
				}
				if (!gameOver) {
					if (key == KeyEvent.VK_R) {
						timer.cancel();
						startAction();
					}
				}
				if (pause) {
					if (key == KeyEvent.VK_C) {
						continueAction();
					}
					return;
				}
				switch (key) {
				case KeyEvent.VK_RIGHT:
					moveRightAction();
					break;
				case KeyEvent.VK_LEFT:
					moveLeftAction();
					break;
				case KeyEvent.VK_DOWN:
					softDropAction();
					break;
				case KeyEvent.VK_UP:
					rotateRightActionB();
					break;
				case KeyEvent.VK_Z:
					rotateLeftActionB();
					break;
				case KeyEvent.VK_SPACE:
					hardDropAction();
					break;
				case KeyEvent.VK_P:
					pauseAction();
					break;
				}
				repaint();
			}
		};
		this.requestFocus();
		if (flag) {
			this.addKeyListener(l);
			flag = false;
		}
	}

	/**
	 * TODO 启动游戏
	 */
	public void startAction() {
		/** 清空数据 */
		clearWall();
		/** 为各数据重新赋值 */
		tetromino = Tetromino.randomTetromino();
		nextOne = Tetromino.randomTetromino();
		lines = 0;
		score = 0;
		pause = false;
		gameOver = false;
		timer = new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				softDropAction();
				repaint();
			}
		}, firstTime, firstTime);
	}

	/**
	 * TODO 判断方块是否能继续下落
	 */
	public void softDropAction() {
		if (tetrominoCanDrop()) {
			if (this.lines >= 10 && this.lines < 20) {
				timer.cancel();
				timer = new Timer();
				timer.schedule(new TimerTask() {
					public void run() {
						softDropAction();
						repaint();
					}
				}, firstTime / 2, firstTime / 2);
			} else if (this.lines >= 20) {
				timer.cancel();
				timer = new Timer();
				timer.schedule(new TimerTask() {
					public void run() {
						softDropAction();
						repaint();
					}
				}, firstTime / 3, firstTime / 3);
			}
			tetromino.softDrop();
		} else {
			tetrominoLandToWall();
			destroyLines();
			checkGameOver();
			tetromino = nextOne;
			nextOne = Tetromino.randomTetromino();
		}
	}

	/**
	 * TODO 暂停
	 */
	protected void pauseAction() {
		timer.cancel();
		pause = true;
		repaint();
	}

	/**
	 * TODO 硬核下落
	 */
	protected void hardDropAction() {
		while (tetrominoCanDrop()) {
			tetromino.softDrop();
		}
		tetrominoLandToWall();
		destroyLines();
		checkGameOver();
		tetromino = nextOne;
		nextOne = Tetromino.randomTetromino();
	}

	/**
	 * TODO 左旋转
	 */
	protected void rotateLeftActionB() {
		tetromino.rotateLeftB();
		if (outOfBound() || coincide()) {
			tetromino.rotateRightB();
		}
	}

	/**
	 * TODO 右旋转
	 */
	protected void rotateRightActionB() {
		tetromino.rotateRightB();
		if (outOfBound() || coincide()) {
			tetromino.rotateLeftB();
		}
	}

	/**
	 * TODO 左移并判断是否出界/重合
	 */
	protected void moveLeftAction() {
		tetromino.moveLeft();
		if (outOfBound() || coincide()) {
			tetromino.moveRight();
		}
	}

	/**
	 * TODO 右移并判断是否出界/重合
	 */
	protected void moveRightAction() {
		tetromino.moveRight();
		if (outOfBound() || coincide()) {
			tetromino.moveLeft();
		}
	}

	/**
	 * TODO 继续游戏
	 */
	protected void continueAction() {
		timer = new Timer();
		if(this.lines >= 10 && this.lines < 20) {
			timer.schedule(new TimerTask() {
				public void run() {
					softDropAction();
					repaint();
				}
			}, firstTime / 2, firstTime / 2);
		} else if (this.lines >= 20) {
			timer.schedule(new TimerTask() {
				public void run() {
					softDropAction();
					repaint();
				}
			}, firstTime / 3, firstTime / 3);
		} else {
			timer.schedule(new TimerTask() {
				public void run() {
					softDropAction();
					repaint();
				}
			}, firstTime, firstTime);
		}
		pause = false;
		repaint();
	}

}
