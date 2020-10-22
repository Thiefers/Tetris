package roc;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

public class Tool {
	public static ImageIcon getIcon(String filename) {
		Image image = Toolkit.getDefaultToolkit().getImage(filename);
		ImageIcon imageIcon = new ImageIcon(image);
		return imageIcon;
	}
}
