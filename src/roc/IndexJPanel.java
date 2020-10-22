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
	/** ��ʼ��Ϸ��ť */
	private JButton startButton;
	/** ����ָ����ť */
	private JButton helpButton;
	/** �˳���ť */
	private JButton exitButton;
	/** ��ʼ��Ϸ��ťͼƬ */
	private ImageIcon startImageIcon;
	/** ����ָ����ťͼƬ */
	private ImageIcon helpImageIcon;
	/** �˳���ťͼƬ */
	private ImageIcon exitImageIcon;
	/** ��ťĬ�ϴ�С */
	private int x = 290;
	private int y = 40;
	/** ��ť��ֱ��� */
	private int verticalDistance = 60;
	/** ����벼�� */
	public JPanel mainJPanel;
	public CardLayout cardLayout;
	/** ����ͼƬ */
	private static Image bg;
	/** ����ͼƬ��Դ */
	static {
		try {
			bg = Tool.getIcon("graphics//1.jpg").getImage();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/** ���Ʊ���ͼƬ */
	public void paintComponent(Graphics g) {
		g.drawImage(bg, 0, 0, 540, 590, null);
	}

	/**
	 * IndexJPanel ������
	 * 
	 * @param mainJPanel
	 * @param cardLayout
	 */
	public IndexJPanel(JPanel mainJPanel, CardLayout cardLayout) {
		// TODO �Զ����ɵĹ��캯�����
		this.mainJPanel = mainJPanel;
		this.cardLayout = cardLayout;
		/** ���ÿ�Ƭ���ַ�ʽ, ����Ϊ�������ϵͳĬ�ϲ��� */
		this.setLayout(null);
		/** ��ʼͼƬ��ť������ */
		/** ��ʼ��ť */
		startImageIcon = Tool.getIcon("graphics//start.png");
		startButton = new JButton(startImageIcon);
		startButton.setBounds(x, y, startImageIcon.getIconWidth(), startImageIcon.getIconHeight());
		startButton.setBorderPainted(false);
		startButton.setContentAreaFilled(false);
		this.add(startButton);
		startButton.addMouseListener(this);
		/** ������ť */
		helpImageIcon = Tool.getIcon("graphics//help.png");
		helpButton = new JButton(helpImageIcon);
		helpButton.setBounds(x, y + verticalDistance + helpImageIcon.getIconHeight(), helpImageIcon.getIconWidth(),
				helpImageIcon.getIconHeight());
		helpButton.setBorderPainted(false);
		helpButton.setContentAreaFilled(false);
		this.add(helpButton);
		helpButton.addMouseListener(this);
		/** �˳���ť */
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
		// TODO �Զ����ɵķ������
		if (e.getSource() == startButton) {
			// ��ת��ģʽѡ�����
			this.cardLayout.show(this.mainJPanel, "selectJPanel");
		} else if (e.getSource() == helpButton) {
			// ��ת������ָ�����棨�������棩
			this.cardLayout.show(this.mainJPanel, "helpJPanel");
		} else if (e.getSource() == exitButton) {
			// �˳�����
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

}