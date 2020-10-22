package roc;

import java.awt.CardLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class IndexJPanel extends JPanel implements MouseListener {
	/** 开始游戏按钮 */
	private JButton startButton;
	/** 新手指导按钮 */
	private JButton helpButton;
	/** 退出按钮 */
	private JButton exitButton;
	/** 开始游戏按钮图片 */
	private ImageIcon startImageIcon;
	/** 新手指导按钮图片 */
	private ImageIcon helpImageIcon;
	/** 退出按钮图片 */
	private ImageIcon exitImageIcon;
	/** 按钮默认大小 */
	private int x = 290;
	private int y = 40;
	/** 按钮垂直间距 */
	private int verticalDistance = 60;
	/** 面板与布局 */
	public JPanel mainJPanel;
	public CardLayout cardLayout;
	/** 背景图片 */
	private static Image bg;
	/** 加载图片资源 */
	static {
		try {
			bg = Tool.getIcon("graphics//1.jpg").getImage();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/** 绘制背景图片 */
	public void paintComponent(Graphics g) {
		g.drawImage(bg, 0, 0, 540, 590, null);
	}

	/**
	 * IndexJPanel 构造器
	 * 
	 * @param mainJPanel
	 * @param cardLayout
	 */
	public IndexJPanel(JPanel mainJPanel, CardLayout cardLayout) {
		// TODO 自动生成的构造函数存根
		this.mainJPanel = mainJPanel;
		this.cardLayout = cardLayout;
		/** 采用卡片布局方式, 参数为空则清除系统默认布局 */
		this.setLayout(null);
		/** 初始图片按钮的设置 */
		/** 开始按钮 */
		startImageIcon = Tool.getIcon("graphics//start.png");
		startButton = new JButton(startImageIcon);
		startButton.setBounds(x, y, startImageIcon.getIconWidth(), startImageIcon.getIconHeight());
		startButton.setBorderPainted(false);
		startButton.setContentAreaFilled(false);
		this.add(startButton);
		startButton.addMouseListener(this);
		/** 帮助按钮 */
		helpImageIcon = Tool.getIcon("graphics//help.png");
		helpButton = new JButton(helpImageIcon);
		helpButton.setBounds(x, y + verticalDistance + helpImageIcon.getIconHeight(), helpImageIcon.getIconWidth(),
				helpImageIcon.getIconHeight());
		helpButton.setBorderPainted(false);
		helpButton.setContentAreaFilled(false);
		this.add(helpButton);
		helpButton.addMouseListener(this);
		/** 退出按钮 */
		exitImageIcon = Tool.getIcon("graphics//exit.png");
		exitButton = new JButton(exitImageIcon);
		exitButton.setBounds(x, y + verticalDistance + verticalDistance + 2 * exitImageIcon.getIconHeight(),
				exitImageIcon.getIconWidth(), exitImageIcon.getIconHeight());
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		this.add(exitButton);
		exitButton.addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO 自动生成的方法存根
		if (e.getSource() == startButton) {
			// 跳转到模式选择界面
			this.cardLayout.show(this.mainJPanel, "selectJPanel");
		} else if (e.getSource() == helpButton) {
			// 跳转到按键指导界面（帮助界面）
			this.cardLayout.show(this.mainJPanel, "helpJPanel");
		} else if (e.getSource() == exitButton) {
			// 退出程序
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

}