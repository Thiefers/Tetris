package roc;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HelpJPanel extends JPanel implements MouseListener {
	int x = 20;
	int y = 40;
	/** 操作标签 */
	private JLabel operJLabel;
	/** 版权标签 */
	private JLabel copyJLabel;
	/** 面板与布局 */
	public JPanel mainJPanel;
	public CardLayout cardLayout;
	/** 返回按钮 */
	private JButton backButton;
	/** 返回按钮图标 */
	private ImageIcon backImageIcon;
	/** 背景图片 */
	private static Image bg;
	/** 加载图片资源 */
	static {
		try {
			bg = Tool.getIcon("graphics//bg07.jpg").getImage();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/** 绘制背景图片 */
	public void paintComponent(Graphics g) {
		g.drawImage(bg, 0, 0, 540, 590, null);
	}

	public HelpJPanel(JPanel mainJPanel, CardLayout cardLayout) {
		// TODO 自动生成的构造函数存根
		this.mainJPanel = mainJPanel;
		this.cardLayout = cardLayout;
		// 清除系统默认布局
		this.setLayout(null);

		operJLabel = new JLabel(
				"<html>特殊按键<br/>&emsp;普通模式<br/>&emsp;&emsp;向上键(↑)/Z&emsp;单种方块变形<br/>&emsp;千变模式<br/>&emsp;&emsp;向上键(↑)/Z&emsp;多种方块任意变形<br/><br/>通用键<br/>&emsp;向上键(↑)&emsp;方块右旋变形<br/>&emsp;向下键(↓)&emsp;方块加速下落<br/>&emsp;向左键(←)&emsp;方块左移<br/>&emsp;向右键(→)&emsp;方块右移<br/>&emsp;空格键(space)&emsp;方块直接到底<br/>&emsp;Z&emsp;方块左旋变形<br/>&emsp;S&emsp;开始游戏(游戏结束时可用)<br/>&emsp;P&emsp;暂停游戏<br/>&emsp;C&emsp;继续游戏(游戏暂停时可用)<br/>&emsp;R&emsp;重新开始游戏(游戏未结束时可用)<br/>&emsp;Q&emsp;直接退出程序</html>");
		operJLabel.setBounds(x, y, 260, 330);
		// operJLabel.setOpaque(true);//透明度
		// operJLabel.setForeground(Color.LIGHT_GRAY);
		operJLabel.setForeground(Color.ORANGE);

		copyJLabel = new JLabel("<html>&emsp;&emsp;@GDPU ROC<br/>&emsp;版权所有 违者必究</html>");
		copyJLabel.setBounds(x + 35, y + 150 + operJLabel.getHeight() / 2, 200, 100);
		copyJLabel.setForeground(Color.ORANGE);
		this.add(operJLabel);
		this.add(copyJLabel);

		backImageIcon = Tool.getIcon("graphics//back.png");
		backButton = new JButton(backImageIcon);
		backButton.setBounds(x + 55, y + operJLabel.getHeight() + copyJLabel.getHeight(), backImageIcon.getIconWidth(),
				backImageIcon.getIconHeight());
		backButton.setBorderPainted(false);
		backButton.setContentAreaFilled(false);
		this.add(backButton);
		backButton.addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO 自动生成的方法存根
		if (e.getSource() == backButton) {
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
