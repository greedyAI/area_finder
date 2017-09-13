import java.awt.Dimension;

import javax.swing.JFrame;

public class Main {
	
	public static void main(String args[]) {
		AreaFinder.largest_in_quad(100, 100, 200, 100, 200, 200, 100, 200);
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(new Canvas(100, 100, 200, 100, 200, 200, 100, 200, AreaFinder.radius, AreaFinder.centerx, AreaFinder.centery));
		frame.setVisible(true);
	}

}
