package ricm3.gpi.gui.layout;

import ricm3.gpi.gui.layout.Container;

/**
 * This represents a layout manager.
 * A layout manager is an object attached to a container
 * and that manages the layout of the children components
 * of that container.
 * 
 * To understand how a layout manager is attached to a container,
 * please have a look at the class Container:
 * @see void Container.layoutManager(LayoutManager lm)
 * @see LayoutManager  Container.layoutManager()
 * 
 * @author Pr. Olivier Gruber (olivier dot gruber at acm dot org)
 *
 */
public abstract class LayoutManager {

  public abstract void layout(Container target);

}
