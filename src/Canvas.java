import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;

import javax.swing.JPanel;

public class Canvas extends JPanel {
	
	private double xPoints[] = new double[4];
	private double yPoints[] = new double[4];
	private double radius;
	private double centerx;
	private double centery;

	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		GeneralPath path = new GeneralPath(GeneralPath.WIND_EVEN_ODD, 4);
		path.moveTo(xPoints[0], yPoints[0]);
		path.lineTo(xPoints[1], yPoints[1]);
		path.lineTo(xPoints[2], yPoints[2]);
		path.lineTo(xPoints[3], yPoints[3]);
		path.closePath();
		g2.draw(path);
		g2.draw(new Ellipse2D.Double(centerx - radius, centery - radius, radius*2, radius*2));
	}
	
	public Canvas(double a1, double b1, double a2, double b2, double a3, double b3, double a4, double b4, double radius, double centerx, double centery) {
		xPoints[0] = a1;
		xPoints[1] = a2;
		xPoints[2] = a3;
		xPoints[3] = a4;
		yPoints[0] = b1;
		yPoints[1] = b2;
		yPoints[2] = b3;
		yPoints[3] = b4;
		this.radius = radius;
		this.centerx = centerx;
		this.centery = centery;
		this.setPreferredSize(new Dimension(500, 500));
	}

}
