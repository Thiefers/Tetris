package roc;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameJFrame extends JFrame {
	/** ����� */
	public JPanel mainJPanel;
	/** ����岼�ַ�ʽ    ��Ƭ����*/
	public CardLayout cardLayout;
	/** ����� */
	/** ��ҳ��� */
	public IndexJPanel indexJPanel;
	/** ѡ����� */
	public SelectJPanel selectJPanel;
	/** ������� */
	public HelpJPanel helpJPanel;
	/** ��ͨģʽ��� */
	public NormalJPanel normalJPanel;
	/** ǧ��ģʽ��� */
	public HardJPanel hardJPanel;

	/** GameJFrame ������ */
	public GameJFrame() {
		/** ��������弰���� */
		mainJPanel = new JPanel();
		cardLayout = new CardLayout();
		mainJPanel.setLayout(cardLayout);// ʹ�ÿ�Ƭ����
		/** ��������� */
		indexJPanel = new IndexJPanel(mainJPanel, cardLayout);
		selectJPanel = new SelectJPanel(mainJPanel, cardLayout);
		helpJPanel = new HelpJPanel(mainJPanel, cardLayout);
		normalJPanel = new NormalJPanel(mainJPanel, cardLayout);
		hardJPanel = new HardJPanel(mainJPanel, cardLayout);
		/** ���������ӵ������,ͬʱΪ���������Ӧ�Ŀ�Ƭ���ƣ���Ƭ���ƣ�������ƣ� */
		mainJPanel.add(indexJPanel, "indexJPanel");
		mainJPanel.add(selectJPanel, "selectJPanel");
		mainJPanel.add(helpJPanel, "helpJPanel");
		mainJPanel.add(normalJPanel, "normalJPanel");
		mainJPanel.add(hardJPanel, "hardJPanel");

		JPanel c = (JPanel) this.getContentPane();// ��ʼ������
		c.add(mainJPanel);// ����������ӿؼ�(mainJPanel)

		this.setTitle("MyTetris");// ���ô��ڱ���
		this.setSize(540, 590);// ���ô��ڴ�С
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// �رմ��ں������ֹ
		this.setLocationRelativeTo(null);// ���ھ���
		this.setResizable(false);// ���ɸı䴰�ڴ�С
		this.setVisible(true);// ���ڿ��ӻ�
	}

	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		new GameJFrame();
	}

}
