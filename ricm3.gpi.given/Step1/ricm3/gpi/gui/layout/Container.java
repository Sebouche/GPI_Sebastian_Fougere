package ricm3.gpi.gui.layout;

import ricm3.gpi.gui.Graphics;

import java.util.*;

/**
 * This is a container within a tree of containers and components. A container
 * is a component that has children components. Children components are painted
 * on top of their parent container.
 * 
 * @author Pr. Olivier Gruber (olivier dot gruber at acm dot org)
 */
public class Container extends Component {

	List<Component> m_children;

	Container() {
		m_children = new java.util.ArrayList<Component>();
	}

	public Container(Container parent) {
		super(parent);
		m_children = new java.util.ArrayList<Component>();
	}

	/**
	 * @return the number of components that are children to this container
	 */
	public int childrenCount() {
		return this.m_children.size();
	}

	/**
	 * @return the component indexed by the given index.
	 */
	public Component childrenAt(int i) {
		if (i < 0) {
			return null;
		}
		Iterator<Component> iter = this.m_children.iterator();
		Component c;
		while (iter.hasNext()) {
			c = iter.next();
			if (i == 0) {
				return c;
			}
			i--;
		}
		return null;
	}

	/**
	 * Select the component, on top, at the given location. The location is given in
	 * local coordinates. Reminder: children are above their parent.
	 * 
	 * @param x
	 * @param y
	 * @return this selected component
	 */
	public Component select(int x, int y) {
		Iterator<Component> iter = this.m_children.iterator();
		Component c;
		while (iter.hasNext()) {
			c = iter.next();
			if (c.inside(x, y)) {
				return c.select(x - c.m_x, y - c.m_y);
			}
		}
		if ((x >= 0) && (y >= 0) && (x < this.m_width) && (y < this.m_height)) {
			return this;
		}
		return null;
	}

	/**
	 * Painting a container is a two-step process in order to paint children
	 * components above. - First, the container paints itself. - Second, the
	 * container paints its children
	 */
	public void paint(Graphics g) {
		super.paint(g);
		Iterator<Component> iter = this.m_children.iterator();
		while(iter.hasNext()) {
			iter.next().paint(g);
		}
	}

}
