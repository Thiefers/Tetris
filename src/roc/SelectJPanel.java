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
	/** ��ͨģʽ��ť */
	private JButton normalButton;
	/** ǧ��ģʽ��ť */
	private JButton hardButton;
	/** ������Ϸ��ť */
	private JButton backButton;
	/** ��ͨģʽ��ťĬ��ͼ�� */
	private ImageIcon normalImageIcon;
	/** ǧ��ģʽ��ťĬ��ͼ�� */
	private ImageIcon hardImageIcon;
	/** ������Ϸ��ťĬ��ͼ�� */
	private ImageIcon backImageIcon;
	/** ��ťĬ�ϴ�С */
	private int x = 70;
	private int y = 50;
	/** ��ť��Ĵ�ֱ��� */
	private int verticalDistance = 60;
	/** ��弰�䲼�� */
	public JPanel mainJPanel;
	public CardLayout cardLayout;
	/** ����ͼƬ */
	private static Image bg;
	/** ����ͼƬ��Դ */
	static {
		try {
			bg = Tool.getIcon("graphics//3.jpg").getImage();
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
	 * selectJPanel ������
	 * 
	 * @param mainJPanel
	 * @param cardLayout
	 */
	public SelectJPanel(JPanel mainJPanel, CardLayout cardLayout) {
		// TODO �Զ����ɵĹ��캯�����
		this.mainJPanel = mainJPanel;
		this.cardLayout = cardLayout;
		/** ���ϵͳĬ�ϲ��� */
		this.setLayout(null);
		/** ��ͨģʽ��ť */
		normalImageIcon = Tool.getIcon("graphics//normal.png");
		normalButton = new JButton(normalImageIcon);
		normalButton.setBounds(x, y, normalImageIcon.getIconWidth(), normalImageIcon.getIconHeight());
		normalButton.setBorderPainted(false);
		normalButton.setContentAreaFilled(false);
		this.add(normalButton);
		normalButton.addMouseListener(this);
		/** ǧ��ģʽ��ť */
		hardImageIcon = Tool.getIcon("graphics//hard.png");
		hardButton = new JButton(hardImageIcon);
		hardButton.setBounds(x, y + verticalDistance + normalImageIcon.getIconHeight(), hardImageIcon.getIconWidth(),
				hardImageIcon.getIconHeight());
		hardButton.setBorderPainted(false);
		hardButton.setContentAreaFilled(false);
		this.add(hardButton);
		hardButton.addMouseListener(this);
		/** ���ذ�ť */
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
		// TODO �Զ����ɵķ������
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
