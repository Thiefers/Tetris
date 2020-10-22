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
	/** ���䷽�� */
	private Tetromino tetromino = Tetromino.randomTetromino();
	/** ��ʾ���� */
	private Tetromino nextOne = Tetromino.randomTetromino();
	/** TODO������ */
	public static final int ROWS = 20;
	/** ������ */
	public static final int COLS = 10;
	/** TODO ���Ӵ�С */
	public static final int CELL_SIZE = 26;
	/** TODO ������ɫ */
	public static final int FONT_COLOR = 0x474B5A;
	/** TODO �����С */
	public static final int FONT_SIZE = 0x20;
	/** ����ǽ����Ϸ���ڲ���������ֹͣ�����Ƕ��ǽ�У� */
	private Cell[][] wall = new Cell[ROWS][COLS];
	/** ������Ӧ������ĵ÷� */
	private static final int[] SCORE_TABLE = { 0, 1, 5, 10, 20 };
	/** ���������� */
	private int lines;
	/** �÷� */
	private int score;
	/** ��ͣ�ź��� */
	private boolean pause;
	/** ��Ϸ�����ź��� */
	private boolean gameOver = true;
	/** ��ʱ�� */
	private Timer timer;
	private long firstTime = 800;
	/** ����ͼƬ */
	private static Image background;
	/** ��Ϸ����ͼƬ */
	private static Image gameover;
	/** nextOne ������ */
	private static Image ban;
	/** ����ͼƬ */
	public static Image T;
	public static Image I;
	public static Image O;
	public static Image J;
	public static Image L;
	public static Image S;
	public static Image Z;
	/** ��Ϸ��Ϣ�߿� */
	private static Image window;
	/** ���½� */
	private static Image index;
	/** �˳���Ϸ��ť */
	private JButton exitButton;
	/** ��ʼ��Ϸ��ť */
	private JButton startButton;
	/** ����ģʽѡ��ť */
	private JButton backButton;
	/** �˳���ťͼ�� */
	private ImageIcon exitImageIcon;
	/** ��ʼ��ťͼ�� */
	private ImageIcon startImageIcon;
	/** ���ذ�ťͼ�� */
	private ImageIcon backImageIcon;
	/** ��弰�䲼�� */
	public JPanel mainJPanel;
	public CardLayout cardLayout;
	/** ���ش�С */
	private static final int SMALLSIZE = 5;
	private static final int BIGSIZE = 54;// 54-2=52
	/** �ж��Ƿ����½���� */
	private static boolean flag = true;
	/** ����ͼƬ��Դ */
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
		/** ���Ʊ���ͼƬ SMALLSIZE BIGSIZE 261 522 */
		// SMALLSIZE + BIGSIZE BIGSIZE + SMALLSIZE*2 SMALLSIZE
		g.drawImage(background, 0, 0, 540, 590, null);
		g.drawImage(index, 300, 340, null);
		/**
		 * ������Ϸ��ok
		 */
		/** ���� */
		g.drawImage(window, 10, 10, 10 + SMALLSIZE, 10 + SMALLSIZE, 0, 0, SMALLSIZE, SMALLSIZE, null);
		/** ���� */
		g.drawImage(window, 10 + SMALLSIZE, 10, 10 + SMALLSIZE + 262, 10 + SMALLSIZE, SMALLSIZE, 0, SMALLSIZE + BIGSIZE,
				SMALLSIZE, null);
		/** ���� */
		g.drawImage(window, 10 + SMALLSIZE + 262, 10, 10 + SMALLSIZE * 2 + 262, 10 + SMALLSIZE, SMALLSIZE + BIGSIZE, 0,
				BIGSIZE + SMALLSIZE * 2, SMALLSIZE, null);
		/** ���� */
		g.drawImage(window, 10, 10 + SMALLSIZE, 10 + SMALLSIZE, 10 + SMALLSIZE + 523, 0, SMALLSIZE, SMALLSIZE,
				SMALLSIZE + BIGSIZE, null);
		/** ���� */
		g.drawImage(window, 10 + SMALLSIZE, 10 + SMALLSIZE, 10 + SMALLSIZE + 262, 10 + SMALLSIZE + 523, SMALLSIZE + 1,
				SMALLSIZE + 1, BIGSIZE + SMALLSIZE - 1, SMALLSIZE + BIGSIZE - 1, null);
		/** ���� */
		g.drawImage(window, 10 + SMALLSIZE + 262, 10 + SMALLSIZE, 10 + SMALLSIZE * 2 + 262, 10 + SMALLSIZE + 523,
				SMALLSIZE + BIGSIZE, SMALLSIZE, BIGSIZE + SMALLSIZE * 2, SMALLSIZE + BIGSIZE, null);
		/** ���� */
		g.drawImage(window, 10, 10 + SMALLSIZE + 523, 10 + SMALLSIZE, 10 + SMALLSIZE * 2 + 523, 0, SMALLSIZE + BIGSIZE,
				SMALLSIZE, BIGSIZE + SMALLSIZE * 2, null);
		/** ���� */
		g.drawImage(window, 10 + SMALLSIZE, 10 + SMALLSIZE + 523, 10 + SMALLSIZE + 262, 10 + SMALLSIZE * 2 + 523,
				SMALLSIZE, SMALLSIZE + BIGSIZE, SMALLSIZE + BIGSIZE, BIGSIZE + SMALLSIZE * 2, null);
		/** ���� */
		g.drawImage(window, 10 + SMALLSIZE + 262, 10 + SMALLSIZE + 523, 10 + SMALLSIZE * 2 + 262,
				10 + SMALLSIZE * 2 + 523, SMALLSIZE + BIGSIZE, SMALLSIZE + BIGSIZE, BIGSIZE + SMALLSIZE * 2,
				BIGSIZE + SMALLSIZE * 2, null);
		/**
		 * ����NextOne��ok
		 */
		/** ���� */
		g.drawImage(window, 288, 10, 288 + SMALLSIZE, 10 + SMALLSIZE, 0, 0, SMALLSIZE, SMALLSIZE, null);
		/** ���� */
		g.drawImage(window, 288 + SMALLSIZE, 10, 288 + SMALLSIZE + 216, 10 + SMALLSIZE, SMALLSIZE, 0,
				SMALLSIZE + BIGSIZE, SMALLSIZE, null);
		/** ���� */
		g.drawImage(window, 288 + SMALLSIZE + 216, 10, 288 + SMALLSIZE * 2 + 216, 10 + SMALLSIZE, SMALLSIZE + BIGSIZE,
				0, BIGSIZE + SMALLSIZE * 2, SMALLSIZE, null);
		/** ���� */
		g.drawImage(window, 288, 10 + SMALLSIZE, 288 + SMALLSIZE, 10 + SMALLSIZE + 108, 0, SMALLSIZE, SMALLSIZE,
				SMALLSIZE + BIGSIZE, null);
		/** ���� */
		g.drawImage(window, 288 + SMALLSIZE, 10 + SMALLSIZE, 288 + SMALLSIZE + 216, 10 + SMALLSIZE + 108, SMALLSIZE + 1,
				SMALLSIZE + 1, BIGSIZE + SMALLSIZE - 1, SMALLSIZE + BIGSIZE - 1, null);
		/** ���� */
		g.drawImage(window, 288 + SMALLSIZE + 216, 10 + SMALLSIZE, 288 + SMALLSIZE * 2 + 216, 10 + SMALLSIZE + 108,
				SMALLSIZE + BIGSIZE, SMALLSIZE, BIGSIZE + SMALLSIZE * 2, SMALLSIZE + BIGSIZE, null);
		/** ���� */
		g.drawImage(window, 288, 10 + SMALLSIZE + 108, 288 + SMALLSIZE, 10 + SMALLSIZE * 2 + 108, 0,
				SMALLSIZE + BIGSIZE, SMALLSIZE, BIGSIZE + SMALLSIZE * 2, null);
		/** ���� */
		g.drawImage(window, 288 + SMALLSIZE, 10 + SMALLSIZE + 108, 288 + SMALLSIZE + 216, 10 + SMALLSIZE * 2 + 108,
				SMALLSIZE, SMALLSIZE + BIGSIZE, SMALLSIZE + BIGSIZE, BIGSIZE + SMALLSIZE * 2, null);
		/** ���� */
		g.drawImage(window, 288 + SMALLSIZE + 216, 10 + SMALLSIZE + 108, 288 + SMALLSIZE * 2 + 216,
				10 + SMALLSIZE * 2 + 108, SMALLSIZE + BIGSIZE, SMALLSIZE + BIGSIZE, BIGSIZE + SMALLSIZE * 2,
				BIGSIZE + SMALLSIZE * 2, null);
		/**
		 * ���Ʒ�����
		 */
		/** ���� */
		g.drawImage(window, 288, 135, 288 + SMALLSIZE, 135 + SMALLSIZE, 0, 0, SMALLSIZE, SMALLSIZE, null);
		/** ���� */
		g.drawImage(window, 288 + SMALLSIZE, 135, 288 + SMALLSIZE + 216, 135 + SMALLSIZE, SMALLSIZE, 0,
				SMALLSIZE + BIGSIZE, SMALLSIZE, null);
		/** ���� */
		g.drawImage(window, 288 + SMALLSIZE + 216, 135, 288 + SMALLSIZE * 2 + 216, 135 + SMALLSIZE, SMALLSIZE + BIGSIZE,
				0, BIGSIZE + SMALLSIZE * 2, SMALLSIZE, null);
		/** ���� */
		g.drawImage(window, 288, 135 + SMALLSIZE, 288 + SMALLSIZE, 135 + SMALLSIZE + 48, 0, SMALLSIZE, SMALLSIZE,
				SMALLSIZE + BIGSIZE, null);
		/** ���� */
		g.drawImage(window, 288 + SMALLSIZE, 135 + SMALLSIZE, 288 + SMALLSIZE + 216, 135 + SMALLSIZE + 48,
				SMALLSIZE + 1, SMALLSIZE + 1, BIGSIZE + SMALLSIZE - 1, SMALLSIZE + BIGSIZE - 1, null);
		/** ���� */
		g.drawImage(window, 288 + SMALLSIZE + 216, 135 + SMALLSIZE, 288 + SMALLSIZE * 2 + 216, 135 + SMALLSIZE + 48,
				SMALLSIZE + BIGSIZE, SMALLSIZE, BIGSIZE + SMALLSIZE * 2, SMALLSIZE + BIGSIZE, null);
		/** ���� */
		g.drawImage(window, 288, 135 + SMALLSIZE + 48, 288 + SMALLSIZE, 135 + SMALLSIZE * 2 + 48, 0,
				SMALLSIZE + BIGSIZE, SMALLSIZE, BIGSIZE + SMALLSIZE * 2, null);
		/** ���� */
		g.drawImage(window, 288 + SMALLSIZE, 135 + SMALLSIZE + 48, 288 + SMALLSIZE + 216, 135 + SMALLSIZE * 2 + 48,
				SMALLSIZE, SMALLSIZE + BIGSIZE, SMALLSIZE + BIGSIZE, BIGSIZE + SMALLSIZE * 2, null);
		/** ���� */
		g.drawImage(window, 288 + SMALLSIZE + 216, 135 + SMALLSIZE + 48, 288 + SMALLSIZE * 2 + 216,
				135 + SMALLSIZE * 2 + 48, SMALLSIZE + BIGSIZE, SMALLSIZE + BIGSIZE, BIGSIZE + SMALLSIZE * 2,
				BIGSIZE + SMALLSIZE * 2, null);
		/**
		 * ��������������
		 */
		/** ���� */
		g.drawImage(window, 288, 193, 288 + SMALLSIZE, 193 + SMALLSIZE, 0, 0, SMALLSIZE, SMALLSIZE, null);
		/** ���� */
		g.drawImage(window, 288 + SMALLSIZE, 193, 288 + SMALLSIZE + 216, 193 + SMALLSIZE, SMALLSIZE, 0,
				SMALLSIZE + BIGSIZE, SMALLSIZE, null);
		/** ���� */
		g.drawImage(window, 288 + SMALLSIZE + 216, 193, 288 + SMALLSIZE * 2 + 216, 193 + SMALLSIZE, SMALLSIZE + BIGSIZE,
				0, BIGSIZE + SMALLSIZE * 2, SMALLSIZE, null);
		/** ���� */
		g.drawImage(window, 288, 193 + SMALLSIZE, 288 + SMALLSIZE, 193 + SMALLSIZE + 48, 0, SMALLSIZE, SMALLSIZE,
				SMALLSIZE + BIGSIZE, null);
		/** ���� */
		g.drawImage(window, 288 + SMALLSIZE, 193 + SMALLSIZE, 288 + SMALLSIZE + 216, 193 + SMALLSIZE + 48,
				SMALLSIZE + 1, SMALLSIZE + 1, BIGSIZE + SMALLSIZE - 1, SMALLSIZE + BIGSIZE - 1, null);
		/** ���� */
		g.drawImage(window, 288 + SMALLSIZE + 216, 193 + SMALLSIZE, 288 + SMALLSIZE * 2 + 216, 193 + SMALLSIZE + 48,
				SMALLSIZE + BIGSIZE, SMALLSIZE, BIGSIZE + SMALLSIZE * 2, SMALLSIZE + BIGSIZE, null);
		/** ���� */
		g.drawImage(window, 288, 193 + SMALLSIZE + 48, 288 + SMALLSIZE, 193 + SMALLSIZE * 2 + 48, 0,
				SMALLSIZE + BIGSIZE, SMALLSIZE, BIGSIZE + SMALLSIZE * 2, null);
		/** ���� */
		g.drawImage(window, 288 + SMALLSIZE, 193 + SMALLSIZE + 48, 288 + SMALLSIZE + 216, 193 + SMALLSIZE * 2 + 48,
				SMALLSIZE, SMALLSIZE + BIGSIZE, SMALLSIZE + BIGSIZE, BIGSIZE + SMALLSIZE * 2, null);
		/** ���� */
		g.drawImage(window, 288 + SMALLSIZE + 216, 193 + SMALLSIZE + 48, 288 + SMALLSIZE * 2 + 216,
				193 + SMALLSIZE * 2 + 48, SMALLSIZE + BIGSIZE, SMALLSIZE + BIGSIZE, BIGSIZE + SMALLSIZE * 2,
				BIGSIZE + SMALLSIZE * 2, null);
		/**
		 * ������Ϸ״̬��
		 */
		/** ���� */
		g.drawImage(window, 288, 251, 288 + SMALLSIZE, 251 + SMALLSIZE, 0, 0, SMALLSIZE, SMALLSIZE, null);
		/** ���� */
		g.drawImage(window, 288 + SMALLSIZE, 251, 288 + SMALLSIZE + 216, 251 + SMALLSIZE, SMALLSIZE, 0,
				SMALLSIZE + BIGSIZE, SMALLSIZE, null);
		/** ���� */
		g.drawImage(window, 288 + SMALLSIZE + 216, 251, 288 + SMALLSIZE * 2 + 216, 251 + SMALLSIZE, SMALLSIZE + BIGSIZE,
				0, BIGSIZE + SMALLSIZE * 2, SMALLSIZE, null);
		/** ���� */
		g.drawImage(window, 288, 251 + SMALLSIZE, 288 + SMALLSIZE, 251 + SMALLSIZE + 48, 0, SMALLSIZE, SMALLSIZE,
				SMALLSIZE + BIGSIZE, null);
		/** ���� */
		g.drawImage(window, 288 + SMALLSIZE, 251 + SMALLSIZE, 288 + SMALLSIZE + 216, 251 + SMALLSIZE + 48,
				SMALLSIZE + 1, SMALLSIZE + 1, BIGSIZE + SMALLSIZE - 1, SMALLSIZE + BIGSIZE - 1, null);
		/** ���� */
		g.drawImage(window, 288 + SMALLSIZE + 216, 251 + SMALLSIZE, 288 + SMALLSIZE * 2 + 216, 251 + SMALLSIZE + 48,
				SMALLSIZE + BIGSIZE, SMALLSIZE, BIGSIZE + SMALLSIZE * 2, SMALLSIZE + BIGSIZE, null);
		/** ���� */
		g.drawImage(window, 288, 251 + SMALLSIZE + 48, 288 + SMALLSIZE, 251 + SMALLSIZE * 2 + 48, 0,
				SMALLSIZE + BIGSIZE, SMALLSIZE, BIGSIZE + SMALLSIZE * 2, null);
		/** ���� */
		g.drawImage(window, 288 + SMALLSIZE, 251 + SMALLSIZE + 48, 288 + SMALLSIZE + 216, 251 + SMALLSIZE * 2 + 48,
				SMALLSIZE, SMALLSIZE + BIGSIZE, SMALLSIZE + BIGSIZE, BIGSIZE + SMALLSIZE * 2, null);
		/** ���� */
		g.drawImage(window, 288 + SMALLSIZE + 216, 251 + SMALLSIZE + 48, 288 + SMALLSIZE * 2 + 216,
				251 + SMALLSIZE * 2 + 48, SMALLSIZE + BIGSIZE, SMALLSIZE + BIGSIZE, BIGSIZE + SMALLSIZE * 2,
				BIGSIZE + SMALLSIZE * 2, null);
		/** ƽ�ƻ�ͼ����ϵ */
		g.translate(15, 15);
		/** ������������ķ��� */
		paintTetromino(g);
		/** ������һ������ */
		paintNextOne(g);
		/** ���Ƴɼ� */
		paintScore(g);
		/** ���Ʒ���ǽ */
		paintWall(g);
	}

	/**
	 * HardJPanel ������
	 * 
	 * @param mainJPanel
	 * @param cardLayout
	 */
	public HardJPanel(JPanel mainJPanel, CardLayout cardLayout) {
		// TODO �Զ����ɵĹ��캯�����
		this.mainJPanel = mainJPanel;
		this.cardLayout = cardLayout;
		// ���ϵͳĬ�ϲ���
		this.setLayout(null);
		/** ��ʼ��ť */
		startImageIcon = Tool.getIcon("graphics//start_b.png");
		startButton = new JButton(startImageIcon);
		startButton.setBounds(300, 295, startImageIcon.getIconWidth(), startImageIcon.getIconHeight());
		startButton.setContentAreaFilled(false);
		startButton.setBorderPainted(false);
		this.add(startButton);
		startButton.addMouseListener(this);
		/** �˳���ť */
		exitImageIcon = Tool.getIcon("graphics//exit_b.png");
		exitButton = new JButton(exitImageIcon);
		exitButton.setBounds(300, 295, exitImageIcon.getIconWidth(), exitImageIcon.getIconHeight());
		exitButton.setContentAreaFilled(false);
		exitButton.setBorderPainted(false);
		exitButton.setVisible(false);
		this.add(exitButton);
		exitButton.addMouseListener(this);
		/** ���ذ�ť */
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
		// TODO �Զ����ɵķ������
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
		// TODO �Զ����ɵķ������

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO �Զ����ɵķ������

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO �Զ����ɵķ������

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO �Զ����ɵķ������

	}

	/**
	 * TODO ��ǽ
	 * 
	 * @param g
	 */
	private void paintWall(Graphics g) {
		for (int row = 0; row < wall.length; row++) {
			/** ����ÿһ�� */
			Cell[] line = wall[row];
			for (int col = 0; col < line.length; col++) {
				Cell cell = line[col];
				if (cell != null) {
					/** ��ǰλ���з���ʱ��������� */
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
	 * TODO ���ɼ���״̬
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
	 * TODO ������һ������
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
	 * TODO ���Ƶ�ǰ����
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
	 * TODO �������
	 */
	public void clearWall() {
		for (int row = 0; row < ROWS; row++) {
			Arrays.fill(wall[row], null);
		}
	}

	/**
	 * TODO ���ϱ�ֻ��һ��
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
	 * TODO �ж���Ϸ�Ƿ����
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
	 * TODO �ж��Ƿ����� ���������ٲ��Ʒ�
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
	 * TODO ���ٴ��в����ν���һ�еķ�������
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
	 * TODO �ж�һ���Ƿ�����
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
	 * ���鲻������ Ƕ��ǽ��
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
	 * ��鵱ǰ��4�񷽿��ܹ���������
	 * 
	 * @return
	 */
	private boolean tetrominoCanDrop() {
		Cell[] cells = tetromino.getCells();
		for (Cell c : cells) {
			int row = c.getRow();
			/** �䵽���� */
			if (row == ROWS - 1) {
				return false;
			}
		}
		for (Cell c : cells) {
			int row = c.getRow();
			int col = c.getCol();
			/** �·����з��飬�������� */
			if (wall[row + 1][col] != null) {
				return false;
			}
		}
		return true;
	}

	/**
	 * TODO �жϷ����Ƿ��غ�
	 * 
	 * @return
	 */
	private boolean coincide() {
		Cell[] cells = tetromino.getCells();
		for (Cell c : cells) {
			int row = c.getRow();
			int col = c.getCol();
			/** ��ǽ���и��������غ� */
			if (col < 0 || col >= COLS || row < 0 || row >= ROWS || wall[row][col] != null) {
				return true;
			}
		}
		return false;
	}

	/**
	 * TODO �жϷ����Ƿ����
	 * 
	 * @return
	 */
	private boolean outOfBound() {
		Cell[] cells = tetromino.getCells();
		for (Cell c : cells) {
			int col = c.getCol();
			/** ���� */
			if (col < 0 || col >= COLS) {
				return true;
			}
		}
		return false;
	}

	/**
	 * TODO ��Ϸ���ÿ�ʼ
	 */
	public void action() {
		startAction();
		repaint();
		/** ������ */
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
	 * TODO ������Ϸ
	 */
	public void startAction() {
		/** ������� */
		clearWall();
		/** Ϊ���������¸�ֵ */
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
	 * TODO �жϷ����Ƿ��ܼ�������
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
	 * TODO ��ͣ
	 */
	protected void pauseAction() {
		timer.cancel();
		pause = true;
		repaint();
	}

	/**
	 * TODO Ӳ������
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
	 * TODO ����ת
	 */
	protected void rotateLeftActionB() {
		tetromino.rotateLeftB();
		if (outOfBound() || coincide()) {
			tetromino.rotateRightB();
		}
	}

	/**
	 * TODO ����ת
	 */
	protected void rotateRightActionB() {
		tetromino.rotateRightB();
		if (outOfBound() || coincide()) {
			tetromino.rotateLeftB();
		}
	}

	/**
	 * TODO ���Ʋ��ж��Ƿ����/�غ�
	 */
	protected void moveLeftAction() {
		tetromino.moveLeft();
		if (outOfBound() || coincide()) {
			tetromino.moveRight();
		}
	}

	/**
	 * TODO ���Ʋ��ж��Ƿ����/�غ�
	 */
	protected void moveRightAction() {
		tetromino.moveRight();
		if (outOfBound() || coincide()) {
			tetromino.moveLeft();
		}
	}

	/**
	 * TODO ������Ϸ
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
