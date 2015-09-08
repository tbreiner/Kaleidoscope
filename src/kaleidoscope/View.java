package kaleidoscope;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

/**
 * The View "observes" and displays what is going on in the Model. The Model
 * contains and updates the position of the figure to be displayed.
 * 
 * @author David Matuszek
 * @author Theresa Breiner
 * @author Martha Trevino
 */
@SuppressWarnings("serial")
public class View extends JPanel implements Observer {

	/** This is what we will be observing. */
	ArrayList<Model> modelList = new ArrayList<Model>();

	final int TYPES = 5;
	int reflections = 8;
	boolean ball = false;
	boolean rectangle = false;
	boolean triangle = true;
	boolean roundRect = false;
	boolean diamond = false;

	/**
	 * Constructor.
	 * 
	 * @param models
	 *            The ArrayList of Models whose working is to be displayed.
	 */
	View(ArrayList<Model> models) {
		this.modelList.addAll(models);
	}

	/**
	 * Displays what is going on in the Model with reflections. Note: This
	 * method should NEVER be called directly; call repaint() instead.
	 * 
	 * @param g
	 *            The Graphics on which to paint things.
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g) {
		g.setColor(Color.CYAN);
		g.fillRect(0, 0, getWidth(), getHeight());

		for (int model = 0; model < modelList.size(); model++) {
			Model currModel = modelList.get(model);
			String currShape = currModel.getShapeType();

			if (ball && currShape.equals("ball")) {
				drawSymmShape(currModel, g);
			} else if (rectangle && currShape.equals("rectangle")) {
				drawSymmShape(currModel, g);
			} else if (roundRect && currShape.equals("roundRect")) {
				drawSymmShape(currModel, g);
			} else if (triangle && currShape.equals("triangle")) {
				drawTriangle(currModel, g);
			} else if (diamond && currShape.equals("diamond")) {
				drawDiamond(currModel, g);
			}
		}
	}

	/**
	 * Draws all the necessary reflections of one given model - for balls,
	 * rectangles, and round rectangles.
	 * 
	 * @param currModel
	 *            The model to be drawn
	 * @param g
	 *            The graphics to draw the model on
	 */
	public void drawSymmShape(Model currModel, Graphics g) {
		g.setColor(currModel.getColor());
		int x = currModel.getX();
		int y = currModel.getY();
		int size = currModel.FIGURE_SIZE;
		double angleOffset = 2 * Math.PI / reflections;

		double radius = Math.pow(x * x + y * y, 0.5);
		double angle = Math.atan((double) (x) / y);
		if (y < 0) {
			angle += Math.PI;
		}
		for (int div = 1; div <= reflections; div++) {

			x = (int) (radius * Math.sin(angle + div * angleOffset));
			y = (int) (radius * Math.cos(angle + div * angleOffset));

			if (currModel.getShapeType().equals("ball")) {
				g.fillOval(x + (getWidth() - size) / 2, y
						+ (getHeight() - size) / 2, size, size);
			}

			else if (currModel.getShapeType().equals("rectangle")) {
				g.fillRect(x + (getWidth() - size) / 2, y
						+ (getHeight() - size) / 2, size, size);
			}

			else if (currModel.getShapeType().equals("roundRect")) {
				g.fillRoundRect(x + (getWidth() - size) / 2, y
						+ (getHeight() - size) / 2, size, size, 20, 20);
			}
		}
	}

	/**
	 * Draws all the necessary reflections of the given model, which has a
	 * triangle shape.
	 * 
	 * @param model
	 *            The model to be drawn
	 * @param g
	 *            The graphics to draw the model on
	 */
	public void drawTriangle(Model model, Graphics g) {
		g.setColor(model.getColor());
		int size = model.FIGURE_SIZE;

		int x = model.getX();
		int y = model.getY();
		double angleOffset = 2 * Math.PI / reflections;

		int x1 = x + size;
		int y1 = y + size / 2;
		int x2 = x + size / 2;
		int y2 = y + size;

		double radius = Math.pow(x * x + y * y, 0.5);
		double angle = Math.atan((double) (x) / y);

		double radius1 = Math.pow(x1 * x1 + y1 * y1, 0.5);
		double radius2 = Math.pow(x2 * x2 + y2 * y2, 0.5);

		double angle1 = Math.atan((double) (x1) / y1);
		double angle2 = Math.atan((double) (x2) / y2);

		if (y < 0) {
			angle += Math.PI;
		}
		if (y1 < 0) {
			angle1 += Math.PI;
		}
		if (y2 < 0) {
			angle2 += Math.PI;
		}

		for (int div = 1; div <= reflections; div++) {

			x = (int) (radius * Math.sin(angle + div * angleOffset))
					+ getWidth() / 2;
			y = (int) (radius * Math.cos(angle + div * angleOffset))
					+ getHeight() / 2;

			x1 = (int) (radius1 * Math.sin(angle1 + div * angleOffset))
					+ getWidth() / 2;
			y1 = (int) (radius1 * Math.cos(angle1 + div * angleOffset))
					+ getHeight() / 2;

			x2 = (int) (radius2 * Math.sin(angle2 + div * angleOffset))
					+ getWidth() / 2;
			y2 = (int) (radius2 * Math.cos(angle2 + div * angleOffset))
					+ getHeight() / 2;

			g.fillPolygon(new int[] { x, x1, x2 }, new int[] { y, y1, y2 }, 3);
		}

	}

	/**
	 * Draws all the necessary reflections of the given model, which has a
	 * diamond shape.
	 * 
	 * @param model
	 *            The model to be drawn
	 * @param g
	 *            The graphics to draw the model on
	 */
	public void drawDiamond(Model model, Graphics g) {
		g.setColor(model.getColor());
		int size = model.FIGURE_SIZE;

		int x = model.getX();
		int y = model.getY();
		double angleOffset = 2 * Math.PI / reflections;

		int x1 = x + size;
		int y1 = y + size / 2;
		int x2 = x + size / 2;
		int y2 = y + size;
		int x3 = x + 2 * size;
		int y3 = y + 2 * size;

		double radius = Math.pow(x * x + y * y, 0.5);
		double angle = Math.atan((double) (x) / y);

		double radius1 = Math.pow(x1 * x1 + y1 * y1, 0.5);
		double radius2 = Math.pow(x2 * x2 + y2 * y2, 0.5);
		double radius3 = Math.pow(x3 * x3 + y3 * y3, 0.5);

		double angle1 = Math.atan((double) (x1) / y1);
		double angle2 = Math.atan((double) (x2) / y2);
		double angle3 = Math.atan((double) (x3) / y3);

		if (y < 0) {
			angle += Math.PI;
		}
		if (y1 < 0) {
			angle1 += Math.PI;
		}
		if (y2 < 0) {
			angle2 += Math.PI;
		}
		if (y3 < 0) {
			angle3 += Math.PI;
		}

		for (int div = 1; div <= reflections; div++) {

			x = (int) (radius * Math.sin(angle + div * angleOffset))
					+ getWidth() / 2;
			y = (int) (radius * Math.cos(angle + div * angleOffset))
					+ getHeight() / 2;

			x1 = (int) (radius1 * Math.sin(angle1 + div * angleOffset))
					+ getWidth() / 2;
			y1 = (int) (radius1 * Math.cos(angle1 + div * angleOffset))
					+ getHeight() / 2;

			x2 = (int) (radius2 * Math.sin(angle2 + div * angleOffset))
					+ getWidth() / 2;
			y2 = (int) (radius2 * Math.cos(angle2 + div * angleOffset))
					+ getHeight() / 2;

			x3 = (int) (radius3 * Math.sin(angle3 + div * angleOffset))
					+ getWidth() / 2;
			y3 = (int) (radius3 * Math.cos(angle3 + div * angleOffset))
					+ getHeight() / 2;

			g.fillPolygon(new int[] { x, x1, x3, x2 }, new int[] { y, y1, y3,
					y2 }, 4);
		}

	}

	/**
	 * Sets the number of reflections to be drawn
	 * 
	 * @param ref
	 *            Number of reflections to be drawn
	 */
	public void setReflections(int ref) {
		reflections = ref;
	}

	/**
	 * Turns ball drawing on or off
	 * 
	 * @param tf
	 *            True if balls should be drawn, false if not
	 */
	public void setBall(boolean tf) {
		ball = tf;
	}

	/**
	 * Turns rectangle drawing on or off
	 * 
	 * @param tf
	 *            True if rectangles should be drawn, false if not
	 */
	public void setRectangle(boolean tf) {
		rectangle = tf;
	}

	/**
	 * Turns triangle drawing on or off
	 * 
	 * @param tf
	 *            True if triangles should be drawn, false if not
	 */
	public void setTriangle(boolean tf) {
		triangle = tf;
	}

	/**
	 * Turns round rectangle drawing on or off
	 * 
	 * @param tf
	 *            True if round rectangles should be drawn, false if not
	 */
	public void setRoundRect(boolean tf) {
		roundRect = tf;
	}

	/**
	 * Turns diamond drawing on or off
	 * 
	 * @param tf
	 *            True if diamonds should be drawn, false if not
	 */
	public void setDiamond(boolean tf) {
		diamond = tf;
	}

	/**
	 * When an Observer notifies Observers (this View is an Observer), this is
	 * the method that gets called.
	 * 
	 * @param obs
	 *            Holds a reference to the object being observed.
	 * @param arg
	 *            If notifyObservers is given a parameter, it is received here.
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable obs, Object arg) {
		repaint();
	}
}