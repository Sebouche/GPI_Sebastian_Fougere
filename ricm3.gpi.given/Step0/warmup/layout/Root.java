package warmup.layout;

/**
 * This is a tree of layed-out of containers and components.
 * 
 * Each component represents a surface, with a width and height.
 * Each component is at a location within its parent container.
 * Each child is above its parent, in terms of the z-order. In 
 * other words, a child is considered to be in front of its parent.
 * Children may overlap within their parent.
 * Children may not fit entirely within their parent.
 * Any surface of a child that is not within its parent's surface
 * is not visible and thus not selectable.
 * 
 * @author Pr. Olivier Gruber  (olivier dot gruber at acm dot org)
 */

public class Root extends Container {

  Component m_selected;

  public Root(int w, int h) {
    super();
    m_width = w;
    m_height = h;
  }

  /**
   * Select the heir component that is on top at the given location.
   * Nota bene: parents clip their children.
   */
  @Override
  public Component select(int x, int y) {
    m_selected = super.select(x, y);
    return m_selected;
  }
  
  public Component selected() {
    return m_selected;
  }


}
