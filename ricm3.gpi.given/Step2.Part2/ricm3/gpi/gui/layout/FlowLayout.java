package ricm3.gpi.gui.layout;

import ricm3.gpi.gui.layout.Component;
import ricm3.gpi.gui.layout.Container;
import ricm3.gpi.gui.layout.Dimension;

public class FlowLayout extends LayoutManager {

  public static final int VERTICAL = 0;
  public static final int HORIZONTAL = 1;

  int m_direction;
  
  public FlowLayout(int dir) {
    m_direction = dir;
  }

  @Override
  public void layout(Container target) {
    if (m_direction == VERTICAL)
      layoutVertically(target);
    else
      layoutHorizontally(target);
  }

  private void layoutVertically(Container target) {
    int x, y;
    int h = target.m_height/ target.childrenCount();
    int w = target.m_width;
    x = y = 0;
    int childrenCount = target.childrenCount();
    for (int i = 0; i < childrenCount; i++) {
      Component c = target.childrenAt(i);
      Dimension d = c.getPreferredSize();
      if (d.m_height!=0 && d.m_height < h) {
        c.setBounds(x, y, w, d.m_height);
        y += d.m_height;
        h = (target.m_height-y) / (childrenCount-i);
      } else {
        c.setBounds(x, y, w, h);
        y += h;
      }
    }
  }

  private void layoutHorizontally(Container target) {
    int childrenCount = target.childrenCount();
    int x, y;
    int h = target.m_height;
    int w = target.m_width / childrenCount;
    x = y = 0;
    for (int i = 0; i < childrenCount; i++) {
      Component c = target.childrenAt(i);
      Dimension d = c.getPreferredSize();
      if (d.m_width!=0 && d.m_width < w) {
        c.setBounds(x, y, d.m_width, h);
        x += d.m_width;
        w = (target.m_width-x) / (childrenCount-i);
      } else {
        c.setBounds(x, y, w, h);
        x += w;
      }
    }
  }

}
