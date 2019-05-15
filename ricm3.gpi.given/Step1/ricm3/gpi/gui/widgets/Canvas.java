package ricm3.gpi.gui.widgets;

import ricm3.gpi.gui.Graphics;
import ricm3.gpi.gui.KeyListener;
import ricm3.gpi.gui.MouseListener;
import ricm3.gpi.gui.layout.Component;
import ricm3.gpi.gui.layout.Container;
import ricm3.gpi.gui.layout.Location;

/**
 * A canvas to draw on.
 * 
 * The intended usage is to subclass this canvas, usually listening to mouse and
 * keyboard events
 * 
 * The default painting for a canvas is to fill the canvas with the background
 * color. Your subclass may override this behavior.
 * 
 * @author Pr. Olivier Gruber (olivier dot gruber at acm dot org)
 */

public class Canvas extends Component {

	boolean draw_mode;
	boolean c_pressed;
	Location location1;
	Location location2;
	Location location3;
	
	public Canvas(Container parent) {
		super(parent);
		draw_mode = false;
		c_pressed = true;
		this.location2 = new Location(0, 0);
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(m_bgColor);
		Location location = new Location();
		toGlobal(location);
		g.fillRect(location.x(), location.y(), m_width, m_height);
		if (!c_pressed) {
			System.out.printf("test\n");
			g.setColor(m_fgColor);
			g.drawLine(location1.x(), location1.y(), location2.x(), location2.y());
		}
	}

	public static class KL implements KeyListener {
		boolean m_down;
		Canvas c;
		String m_msg;

		public KL(Canvas c, String msg) {
			this.c = c;
			this.m_msg = msg;
		}

		@Override
		public void keyPressed(char k, int code) {
			System.out.println("keyPressed: '" + k + "' (" + code + ")");
		}

		@Override
		public void keyReleased(char k, int code) {
			if (code == KeyListener.VK_SPACE) {
				c.draw_mode = !c.draw_mode;
				if (c.draw_mode) {
					c.c_pressed=false;
					c.location1 = c.location3;
				}
				else {
				c.repaint();
				}
			}
			if (code == KeyListener.VK_C) {
				c.c_pressed = true;
				c.repaint();
			}
		}

	}

	public static class ML implements MouseListener {
		boolean m_down;
		Canvas c;
		String m_msg;

		public ML(Canvas c, String msg) {
			this.c = c;
			this.m_msg = msg;
		}

		@Override
		public void mouseReleased(int x, int y, int buttons) {
			System.out.println("mouse released on " + m_msg);
			c.location2 = new Location(x, y);
			c.draw_mode = false;
			c.repaint();
		}

		@Override
		public void mousePressed(int x, int y, int buttons) {
			System.out.println("mouse pressed on " + m_msg);
			c.c_pressed = false;
			c.draw_mode = true;
			c.location1 = new Location(x, y);
		}

		@Override
		public void mouseMoved(int x, int y) {
			System.out.println("mouse moved to " + x + "," + y + "(" + m_msg + ")");
			if (c.draw_mode) {
				c.location2 = new Location(x, y);
				c.repaint();
			}
			c.location3= new Location(x,y);
		}

		@Override
		public void mouseExited() {
			System.out.println("mouse exited " + m_msg);
		}

		@Override
		public void mouseEntered(int x, int y) {
			System.out.println("mouse entered " + m_msg + " to " + x + "," + y);
		}
	}
}