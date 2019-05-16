package ricm3.gpi.gui.widgets;

import ricm3.gpi.gui.Color;
import ricm3.gpi.gui.Font;
import ricm3.gpi.gui.Graphics;
import ricm3.gpi.gui.Image;
import ricm3.gpi.gui.MouseListener;
import ricm3.gpi.gui.Window;
import ricm3.gpi.gui.layout.Component;
import ricm3.gpi.gui.layout.Container;
import ricm3.gpi.gui.layout.Location;

/**
 * A widget that is a simple button than can be clicked. To know about click
 * events on a button, you need to register an ActionListener on that button.
 * 
 * A button can have a label, that is, a name.
 * 
 * A button may also have two images, one for when the button is pressed down
 * and the other for when the button is released.
 * 
 * 
 * @author Pr. Olivier Gruber (olivier dot gruber at acm dot org)
 */
public class Button extends Component {

	String m_label;
	Font m_font;
	Image m_pressed;
	Image m_released;
	ActionListener al;
	boolean pressed;

	public Button(Container parent) {
		super(parent);
		pressed = false;
	}

	public String getLabel() {
		return m_label;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Location l = new Location(0, 0);
		this.toGlobal(l);
		if (this.m_released == null || this.m_pressed == null) {
			if(pressed) {
			g.setColor(m_fgColor);
			g.setFont(m_font);
			}
			else {
				g.setColor(Color.black);
				g.setFont(m_font);				
			}
			g.drawString(m_label, l.x(), l.y()+(int) m_font.getSize());
			
		} else {
			if (pressed) {
				g.drawImage(m_pressed, l.x(), l.y(), m_width, m_height);
			} else {
				g.drawImage(m_released, l.x(), l.y(), m_width, m_height);
			}
		}
	}

	public void setActionListener(ActionListener al) {
		this.al = al;
		this.setMouseListener(new ML(this, "Rasp-Pi"));
	}

	public void setFont(Font f) {
		this.m_font = f;
	}

	public void setLabel(String txt) {
		m_label = txt;
		Window win = Window.getWindow();
		m_font = win.font(Window.MONOSPACED, 12F);
	}

	public void setImages(Image released, Image pressed) {
		m_pressed = pressed;
		m_released = released;
	}

	public static class ML implements MouseListener {
		boolean m_down;
		String m_msg;
		Button b;

		public ML(Button b, String msg) {
			m_msg = msg;
			this.b = b;
		}

		@Override
		public void mouseReleased(int x, int y, int buttons) {
			b.al.action(b);
			b.pressed = !b.pressed;
			b.repaint();
		}

		@Override
		public void mousePressed(int x, int y, int buttons) {
		}

		@Override
		public void mouseMoved(int x, int y) {
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
