package kaleidoscope;

import java.awt.Graphics;

public class Ball {

	Graphics g;
	Model model;
	int x;
	int y;
	int size;
	double radius;
	double angle;
	double angleOffset;

	Ball(Graphics g, Model model, int width, int height, int reflections) {
		g.setColor(model.getColor());
		x = model.getX();
		y = model.getY();
		size = model.FIGURE_SIZE;
		angleOffset = 2 * Math.PI / reflections;

		radius = Math.pow(x * x + y * y, 0.5);
		angle = Math.atan((double) (x) / y);
		if (y < 0) {
			angle += Math.PI;
		}

		for (int div = 1; div <= reflections; div++) {

			x = (int) (radius * Math.sin(angle + div * angleOffset));
			y = (int) (radius * Math.cos(angle + div * angleOffset));

			g.fillOval(x + (width - size) / 2, y + (height - size) / 2, size,
					size);
		}

	}

}
