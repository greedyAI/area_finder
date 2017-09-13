import java.util.ArrayList;

public class AreaFinder {
	
	public static double radius = 0;
	public static double centerx = 0;
	public static double centery = 0;
	
	public static void circle_area() {
		//we'll use two arrays to store the coordinates of our points
		double x_coords[] = new double[1000000];
		double y_coords[] = new double[1000000];
		double area;
		
		//storing the coordinates into the arrays, notice that the Java compiler uses radian mode
		for (int i = 0; i<1000000; i++) {
			x_coords[i]=Math.cos(i*Math.PI/500000);
			y_coords[i]=Math.sin(i*Math.PI/500000);
		}
				
		double a = 0;
		double b = 0;
				
		//using a loop to compute the summation parts of the shoelace formula
		for (int j = 0; j<999999; j++) {
			a += x_coords[j]*y_coords[j+1];
			b += x_coords[j+1]*y_coords[j];
		}
				
		//computing our final answer
		area = (a + x_coords[999999]*y_coords[0] - b - x_coords[0]*y_coords[999999])/2;
				
		//prints output
		System.out.println(area);		
	}
	
	public static void largest_in_quad(double a1, double b1, double a2, double b2, double a3, double b3, double a4, double b4) {
		//compute minimum and maximum of coordinates, where a's are x coordinates and b's are y coordinates
		double min_x = Math.min(Math.min(Math.min(a1, a2), a3), a4);
		double max_x = Math.max(Math.max(Math.max(a1, a2), a3), a4);
		double min_y = Math.min(Math.min(Math.min(b1, b2), b3), b4);
		double max_y = Math.max(Math.max(Math.max(b1, b2), b3), b4);
				
		//equation of line through (a1, b1) and (a2, b2) is (b1-b2)x+(a2-a1)y=b1(a2-a1)-a1(b2-b1)
		double side_a[] = {(b1-b2), (b2-b3), (b3-b4), (b4-b1)};
		double side_b[] = {(a2-a1), (a3-a2), (a4-a3), (a1-a4)};
		double side_c[] = {b1*(a2-a1)-a1*(b2-b1), b2*(a3-a2)-a2*(b3-b2), b3*(a4-a3)-a3*(b4-b3), b4*(a1-a4)-a4*(b1-b4)};
		
		//create list-like objects to store the points on quadrilateral
		ArrayList<Double> quad_points_x = new ArrayList<Double>();
		ArrayList<Double> quad_points_y = new ArrayList<Double>();
		
		//for adding points on quadrilateral by looping through all possible y-coordinates
		for (int i = 0; i<10002; i++) {
			double y = min_y + (max_y - min_y)*i/10001; 
			//following 4 if loops checks if sides of quadrilateral passes through given y-coordinate
			if (Math.min(b1, b2) <= y && Math.max(b1, b2) >= y && b1 != b2) {
				quad_points_x.add((side_c[0]-side_b[0]*y)/side_a[0]);
				quad_points_y.add(y);
			}
			if (Math.min(b2, b3) <= y && Math.max(b2, b3) >= y && b2 != b3) {
				quad_points_x.add((side_c[1]-side_b[1]*y)/side_a[1]);
				quad_points_y.add(y);
			}
			if (Math.min(b3, b4) <= y && Math.max(b3, b4) >= y && b3 != b4) {
				quad_points_x.add((side_c[2]-side_b[2]*y)/side_a[2]);
				quad_points_y.add(y);
			}
			if (Math.min(b4, b1) <= y && Math.max(b4, b1) >= y && b4 != b1) {
				quad_points_x.add((side_c[3]-side_b[3]*y)/side_a[3]);
				quad_points_y.add(y);
			}
			//following 4 if loops checks if sides of quadrilateral are parallel to the y-axis
			if (Math.min(b1, b2) == y && Math.max(b1, b2) == y) {
				for (int j = 0; j<10002; j++) {
					double x = min_x + (max_x - min_x)*j/10001;
					quad_points_x.add(x);
					quad_points_y.add(y);
				}
			}
			if (Math.min(b2, b3) == y && Math.max(b2, b3) == y) {
				for (int j = 0; j<10002; j++) {
					double x = min_x + (max_x - min_x)*j/10001;
					quad_points_x.add(x);
					quad_points_y.add(y);
				}
			}
			if (Math.min(b3, b4) == y && Math.max(b3, b4) == y) {
				for (int j = 0; j<10002; j++) {
					double x = min_x + (max_x - min_x)*j/10001;
					quad_points_x.add(x);
					quad_points_y.add(y);
				}
			}
			if (Math.min(b4, b1) == y && Math.max(b4, b1) == y) {
				for (int j = 0; j<10002; j++) {
					double x = min_x + (max_x - min_x)*j/10001;
					quad_points_x.add(x);
					quad_points_y.add(y);
				}
			}
		}
		
		//create list-like objects to store the points inside quadrilateral
		ArrayList<Double> inside_points_x = new ArrayList<Double>();
		ArrayList<Double> inside_points_y = new ArrayList<Double>();
		
		//for adding points inside quadrilateral by looping through all possible x- and y-coordinates
		for (int i = 1; i<1001; i++) {
			for (int j = 1; j<1001; j++) {
				int k = 0;
				double x = min_x + (max_x - min_x)*i/1001;
				double y = min_y + (max_y - min_y)*j/1001;
				//following uses the ray-casting algorithm to check if the point is inside the quadrilateral
				if (Math.min(b1, b2) <= y && Math.max(b1, b2) >= y && b1 != b2 && (side_c[0]-side_b[0]*y)/side_a[0] > x) {
					k++;
				}
				if (Math.min(b2, b3) <= y && Math.max(b2, b3) >= y && b2 != b3 && (side_c[1]-side_b[1]*y)/side_a[1] > x) {
					k++;
				}
				if (Math.min(b3, b4) <= y && Math.max(b3, b4) >= y && b3 != b4 && (side_c[2]-side_b[2]*y)/side_a[2] > x) {
					k++;
				}
				if (Math.min(b4, b1) <= y && Math.max(b4, b1) >= y && b4 != b1 && (side_c[3]-side_b[3]*y)/side_a[3] > x) {
					k++;
				}
				//adds point if satisfies ray-casting algorithm 
				if (k % 2 == 1) {
					inside_points_x.add(x);
					inside_points_y.add(y);
				}
			}
		}
		
		//variables for storing minimum radius for particular center point
		double centerx_particular = 0;
		double centery_particular = 0;
		
		//loops through every internal point
		for (int i = 0; i<inside_points_x.size(); i++) {
			//initialize the minimum radius
			double dist_sqr_init = (inside_points_x.get(i) - quad_points_x.get(0)) * (inside_points_x.get(i) - quad_points_x.get(0)) + (inside_points_y.get(i) - quad_points_y.get(0)) * (inside_points_y.get(i) - quad_points_y.get(0));
			double radius_particular = dist_sqr_init;
			//for each internal point, loop through all possible points on quadrilateral
			for (int j = 0; j<quad_points_x.size(); j++) {
				double dist_sqr = (inside_points_x.get(i) - quad_points_x.get(j)) * (inside_points_x.get(i) - quad_points_x.get(j)) + (inside_points_y.get(i) - quad_points_y.get(j)) * (inside_points_y.get(i) - quad_points_y.get(j));
				//if distance between point on quadrilateral and center point is less than minimum radius, update minimum radius and center coordinates
				if (dist_sqr < radius_particular) {
					radius_particular = dist_sqr;
					centerx_particular = inside_points_x.get(i);
					centery_particular = inside_points_y.get(i);
				}
			}
			//finds maximum radius of these computed minimums and updates center coordinates
			if (radius_particular > radius * radius) {
				radius = Math.sqrt(radius_particular);
				centerx = centerx_particular;
				centery = centery_particular;
			}
		}
		
		//prints output
		System.out.println(radius);	
		System.out.println(centerx);
		System.out.println(centery);
	}

}
