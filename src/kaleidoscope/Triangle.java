package kaleidoscope;

import java.awt.Graphics;

public class Triangle {

	Graphics g;
	Model model;
	int x;
	int y;
	int size;
	double radius;
	double angle;
	double angleOffset;

	Triangle(Graphics g, Model model, int width, int height, int reflections) {
		g.setColor(model.getColor());
		size = model.FIGURE_SIZE;

		x = model.getX();
		y = model.getY();
		angleOffset = 2 * Math.PI / reflections;

		int x1 = x + size;
		int y1 = y + size / 2;
		int x2 = x + size / 2;
		int y2 = y + size;

		radius = Math.pow(x * x + y * y, 0.5);
		angle = Math.atan((double) (x) / y);

		double radius1 = Math.pow(x1 * x1 + y1 * y1, 0.5);
		double radius2 = Math.pow(x2 * x2 + y2 * y2, 0.5);

		double angle1 = Math.atan((double) (x1) / y1);
		double angle2 = Math.atan((double) (x2) / y2);
		
		if (y < 0){
			angle += Math.PI;
		}
		if (y1 < 0){
			angle1 += Math.PI;
		}
		if (y2 < 0){
			angle2 += Math.PI;
		}

		for (int div = 1; div <= reflections; div++) {

			x = (int) (radius * Math.sin(angle + div * angleOffset)) + width
					/ 2;
			y = (int) (radius * Math.cos(angle + div * angleOffset)) + height
					/ 2;

			x1 = (int) (radius1 * Math.sin(angle1 + div * angleOffset)) + width
					/ 2;
			y1 = (int) (radius1 * Math.cos(angle1 + div * angleOffset))
					+ height / 2;

			x2 = (int) (radius2 * Math.sin(angle2 + div * angleOffset)) + width
					/ 2;
			y2 = (int) (radius2 * Math.cos(angle2 + div * angleOffset))
					+ height / 2;

			g.fillPolygon(new int[] { x, x1, x2 }, new int[] { y, y1, y2 }, 3);
		}

	}

}
