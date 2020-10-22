package roc;

import java.awt.CardLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class SelectJPanel extends JPanel implements MouseListener {
	/** 普通模式按钮 */
	private JButton normalButton;
	/** 千变模式按钮 */
	private JButton hardButton;
	/** 返回游戏按钮 */
	private JButton backButton;
	/** 普通模式按钮默认图标 */
	private ImageIcon normalImageIcon;
	/** 千变模式按钮默认图标 */
	private ImageIcon hardImageIcon;
	/** 返回游戏按钮默认图标 */
	private ImageIcon backImageIcon;
	/** 按钮默认大小 */
	private int x = 70;
	private int y = 50;
	/** 按钮间的垂直间距 */
	private int verticalDistance = 60;
	/** 面板及其布局 */
	public JPanel mainJPanel;
	public CardLayout cardLayout;
	/** 背景图片 */
	private static Image bg;
	/** 加载图片资源 */
	static {
		try {
			bg = Tool.getIcon("graphics//3.jpg").getImage();
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
	 * selectJPanel 构造器
	 * 
	 * @param mainJPanel
	 * @param cardLayout
	 */
	public SelectJPanel(JPanel mainJPanel, CardLayout cardLayout) {
		// TODO 自动生成的构造函数存根
		this.mainJPanel = mainJPanel;
		this.cardLayout = cardLayout;
		/** 清除系统默认布局 */
		this.setLayout(null);
		/** 普通模式按钮 */
		normalImageIcon = Tool.getIcon("graphics//normal.png");
		normalButton = new JButton(normalImageIcon);
		normalButton.setBounds(x, y, normalImageIcon.getIconWidth(), normalImageIcon.getIconHeight());
		normalButton.setBorderPainted(false);
		normalButton.setContentAreaFilled(false);
		this.add(normalButton);
		normalButton.addMouseListener(this);
		/** 千变模式按钮 */
		hardImageIcon = Tool.getIcon("graphics//hard.png");
		hardButton = new JButton(hardImageIcon);
		hardButton.setBounds(x, y + verticalDistance + normalImageIcon.getIconHeight(), hardImageIcon.getIconWidth(),
				hardImageIcon.getIconHeight());
		hardButton.setBorderPainted(false);
		hardButton.setContentAreaFilled(false);
		this.add(hardButton);
		hardButton.addMouseListener(this);
		/** 返回按钮 */
		backImageIcon = Tool.getIcon("graphics//back.png");
		backButton = new JButton(backImageIcon);
		backButton.setBounds(x + 45,
				y + verticalDistance * 2 + normalImageIcon.getIconHeight() + hardImageIcon.getIconHeight(),
				backImageIcon.getIconWidth(), backImageIcon.getIconHeight());
		backButton.setBorderPainted(false);
		backButton.setContentAreaFilled(false);
		this.add(backButton);
		backButton.addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO 自动生成的方法存根
		if (e.getSource() == normalButton) {
			this.cardLayout.show(this.mainJPanel, "normalJPanel");
		} else if (e.getSource() == hardButton) {
			this.cardLayout.show(this.mainJPanel, "hardJPanel");
		} else if (e.getSource() == backButton) {
			this.cardLayout.show(this.mainJPanel, "indexJPanel");
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
