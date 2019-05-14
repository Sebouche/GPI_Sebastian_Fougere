package warmup.layout;

import java.util.List;
import java.util.Iterator;


/**
 * This is a container within a tree of containers and components.
 * A container is a component that has children components.
 * The children are managed as an ordered set.
 * Children components are painted on top of their parent container.
 * 
 * @author Pr. Olivier Gruber (olivier dot gruber at acm dot org)
 */

public class Container extends Component {

	List<Component> m_children;	
	
  Container() {
	  super();
	  m_children=new java.util.ArrayList<Component>();
  }

  public Container(Container parent) {
	  super(parent);
	  m_children=new java.util.ArrayList<Component>();
  }

  /**
   * @return the number of components that are 
   *         children to this container
   */
  public int childrenCount() {
	  int i=0;
	  Iterator<Component> iter=this.m_children.iterator();
	  while(iter.hasNext()) {
		  iter.next();
		  i++;
	  }
	  return i;
  }

  /**
   * @return the component indexed by the given
   *         index.
   */
  public Component childrenAt(int i) {
	  int j=0;
	  Iterator<Component> iter=this.m_children.iterator();
	  while(iter.hasNext()) {
		  Component tmp=iter.next();
		  if(j==i) {
			  return tmp;
		  }
		  j++;
	  }
	  return null;
	  }

  /**
   * Select the component, on top, at the given location.
   * The location is given in local coordinates.
   * Reminder: children are above their parent.
   * @param x
   * @param y
   * @return this selected component 
   */
  public Component select(int x, int y) {
	  if(this.inside(x,y)) {
	  Iterator<Component> iter=this.m_children.iterator();
	  while(iter.hasNext()) {
		  Component tmp=iter.next();
		  if(tmp.inside(x, y)) {
			  return tmp.select(x, y);
		  }
	  }
	  return this;
	  }
	  return null; 
  }

}
