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
	/** ������ǩ */
	private JLabel operJLabel;
	/** ��Ȩ��ǩ */
	private JLabel copyJLabel;
	/** ����벼�� */
	public JPanel mainJPanel;
	public CardLayout cardLayout;
	/** ���ذ�ť */
	private JButton backButton;
	/** ���ذ�ťͼ�� */
	private ImageIcon backImageIcon;
	/** ����ͼƬ */
	private static Image bg;
	/** ����ͼƬ��Դ */
	static {
		try {
			bg = Tool.getIcon("graphics//bg07.jpg").getImage();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/** ���Ʊ���ͼƬ */
	public void paintComponent(Graphics g) {
		g.drawImage(bg, 0, 0, 540, 590, null);
	}

	public HelpJPanel(JPanel mainJPanel, CardLayout cardLayout) {
		// TODO �Զ����ɵĹ��캯�����
		this.mainJPanel = mainJPanel;
		this.cardLayout = cardLayout;
		// ���ϵͳĬ�ϲ���
		this.setLayout(null);

		operJLabel = new JLabel(
				"<html>���ⰴ��<br/>&emsp;��ͨģʽ<br/>&emsp;&emsp;���ϼ�(��)/Z&emsp;���ַ������<br/>&emsp;ǧ��ģʽ<br/>&emsp;&emsp;���ϼ�(��)/Z&emsp;���ַ����������<br/><br/>ͨ�ü�<br/>&emsp;���ϼ�(��)&emsp;������������<br/>&emsp;���¼�(��)&emsp;�����������<br/>&emsp;�����(��)&emsp;��������<br/>&emsp;���Ҽ�(��)&emsp;��������<br/>&emsp;�ո��(space)&emsp;����ֱ�ӵ���<br/>&emsp;Z&emsp;������������<br/>&emsp;S&emsp;��ʼ��Ϸ(��Ϸ����ʱ����)<br/>&emsp;P&emsp;��ͣ��Ϸ<br/>&emsp;C&emsp;������Ϸ(��Ϸ��ͣʱ����)<br/>&emsp;R&emsp;���¿�ʼ��Ϸ(��Ϸδ����ʱ����)<br/>&emsp;Q&emsp;ֱ���˳�����</html>");
		operJLabel.setBounds(x, y, 260, 330);
		// operJLabel.setOpaque(true);//͸����
		// operJLabel.setForeground(Color.LIGHT_GRAY);
		operJLabel.setForeground(Color.ORANGE);

		copyJLabel = new JLabel("<html>&emsp;&emsp;@GDPU ROC<br/>&emsp;��Ȩ���� Υ�߱ؾ�</html>");
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
		// TODO �Զ����ɵķ������
		if (e.getSource() == backButton) {
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
