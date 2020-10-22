package roc;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameJFrame extends JFrame {
	/** 主面板 */
	public JPanel mainJPanel;
	/** 主面板布局方式    卡片布局*/
	public CardLayout cardLayout;
	/** 子面板 */
	/** 首页面板 */
	public IndexJPanel indexJPanel;
	/** 选择面板 */
	public SelectJPanel selectJPanel;
	/** 帮助面板 */
	public HelpJPanel helpJPanel;
	/** 普通模式面板 */
	public NormalJPanel normalJPanel;
	/** 千变模式面板 */
	public HardJPanel hardJPanel;

	/** GameJFrame 构造器 */
	public GameJFrame() {
		/** 创建主面板及布局 */
		mainJPanel = new JPanel();
		cardLayout = new CardLayout();
		mainJPanel.setLayout(cardLayout);// 使用卡片布局
		/** 创建子面板 */
		indexJPanel = new IndexJPanel(mainJPanel, cardLayout);
		selectJPanel = new SelectJPanel(mainJPanel, cardLayout);
		helpJPanel = new HelpJPanel(mainJPanel, cardLayout);
		normalJPanel = new NormalJPanel(mainJPanel, cardLayout);
		hardJPanel = new HardJPanel(mainJPanel, cardLayout);
		/** 将子面板添加到主面板,同时为面板命名对应的卡片名称（卡片名称，组件名称） */
		mainJPanel.add(indexJPanel, "indexJPanel");
		mainJPanel.add(selectJPanel, "selectJPanel");
		mainJPanel.add(helpJPanel, "helpJPanel");
		mainJPanel.add(normalJPanel, "normalJPanel");
		mainJPanel.add(hardJPanel, "hardJPanel");

		JPanel c = (JPanel) this.getContentPane();// 初始化容器
		c.add(mainJPanel);// 在容器上添加控件(mainJPanel)

		this.setTitle("MyTetris");// 设置窗口标题
		this.setSize(540, 590);// 设置窗口大小
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 关闭窗口后程序终止
		this.setLocationRelativeTo(null);// 窗口居中
		this.setResizable(false);// 不可改变窗口大小
		this.setVisible(true);// 窗口可视化
	}

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		new GameJFrame();
	}

}
