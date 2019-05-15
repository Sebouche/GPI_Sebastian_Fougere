package ricm3.gpi;

import ricm3.gpi.gui.Color;
import ricm3.gpi.gui.MouseListener;
import ricm3.gpi.gui.Window;
import ricm3.gpi.gui.layout.Component;
import ricm3.gpi.gui.layout.Container;
import ricm3.gpi.gui.layout.Root;
import ricm3.gpi.gui.widgets.Canvas;

public class LayoutExample implements Runnable {

	static LayoutExample bl;

	public static void main(String args[]) throws Exception {
		bl = new LayoutExample(args);
		Window.InitWindow(bl);
	}

	Window m_win;
	Root m_root;

	LayoutExample(String args[]) {
	}

	@Override
	public void run() {
		int border = 4;
		int width = 400;
		int height = 400;
		m_win = Window.getWindow();
		Root root = new Root(m_win);
		Container cont = new Container(root);
		cont.setBackground(Color.black);
		cont.setBounds(50, 50, width + 100, height + 100);
		cont.setMouseListener(new ML("container"));

		Component center = new DrawCanvas(cont);
		center.setBackground(Color.red);
		center.setBounds(50, 50, width, height);
		center.setMouseListener(new Canvas.ML((Canvas) center,"center"));

		Component top = new Component(cont);
		top.setBackground(Color.green);
		top.setBounds(50, border, width, 50 - border);
		top.setMouseListener(new ML("top"));

		Component left = new Component(cont);
		left.setBackground(Color.yellow);
		left.setBounds(border, border, 50 - border, height + 100 - 2 * border);
		left.setMouseListener(new ML("left"));

		Component right = new Component(cont);
		right.setBackground(Color.orange);
		right.setBounds(width + 50, border, 50 - border, height + 100 - 2 * border);
		right.setMouseListener(new ML("right"));

		Component bottom = new Component(cont);
		bottom.setBackground(Color.magenta);
		bottom.setBounds(50, height + 50, width, 50 - border);
		bottom.setMouseListener(new ML("bottom"));
		root.repaint();
	}

	class ML implements MouseListener {
		boolean m_down;
		String m_msg;

		ML(String msg) {
			m_msg = msg;
		}

		@Override
		public void mouseReleased(int x, int y, int buttons) {
			System.out.println("mouse released on " + m_msg);
			if (m_down) {
				System.out.println(" click detected on " + m_msg);
				m_down = false;
			}
		}

		@Override
		public void mousePressed(int x, int y, int buttons) {
			m_down = true;
			System.out.println("mouse pressed on " + m_msg);
		}

		@Override
		public void mouseMoved(int x, int y) {
			System.out.println("mouse moved to " + x + "," + y+"("+m_msg+")");
		}

		@Override
		public void mouseExited() {
			System.out.println("mouse exited "+m_msg);
		}

		@Override
		public void mouseEntered(int x, int y) {
			System.out.println("mouse entered "+m_msg+" to " + x + "," + y);
		}

	}
}
