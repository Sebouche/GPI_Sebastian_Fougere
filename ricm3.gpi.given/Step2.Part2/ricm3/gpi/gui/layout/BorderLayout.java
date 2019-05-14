package ricm3.gpi.gui.layout;

import ricm3.gpi.gui.layout.Component;
import ricm3.gpi.gui.layout.Container;
import ricm3.gpi.gui.layout.Dimension;

public class BorderLayout extends LayoutManager {

  public static final int CENTER = 0;
  public static final int NORTH = 1;
  public static final int SOUTH = 2;
  public static final int WEST = 3;
  public static final int EAST = 4;

  Component m_north;
  Component m_south;
  Component m_west;
  Component m_east;
  Component m_center;
  int m_vgap, m_hgap;

  public int getHorizontalGap() {
    return m_hgap;
  }
  public void setHorizontalGap(int hgap) {
    m_hgap = hgap;
  }
  public int getVerticalGap() {
    return m_vgap;
  }
  public void setVerticalGap(int vgap) {
    m_vgap = vgap;
  }
  
  public void setup(Component c, int where) {
    switch(where) {
    case CENTER:
      m_center = c;
      break;
    case NORTH:
      m_north = c;
      break;
    case SOUTH:
      m_south = c;
      break;
    case EAST:
      m_east = c;
      break;
    case WEST:
      m_west = c;
      break;
    }
  }

  @Override
  public void layout(Container target) {
    int top = 0;
    int bottom = target.m_height;
    int left = 0;
    int right = target.m_width;

    Component c = null;
    c = m_north;
    if (c != null) {
      c.setDimension(right - left, c.m_height);
      Dimension d = c.getPreferredSize();
      c.setBounds(left, top, right - left, d.m_height);
      top += d.m_height + m_vgap;
    }
    c = m_south;
    if (c != null) {
      c.setDimension(right - left, c.m_height);
      Dimension d = c.getPreferredSize();
      c.setBounds(left, bottom - d.m_height, right - left, d.m_height);
      bottom -= d.m_height + m_vgap;
    }
    c = m_east;
    if (c != null) {
      c.setDimension(c.m_width, bottom - top);
      Dimension d = c.getPreferredSize();
      c.setBounds(right - d.m_width, top, d.m_width, bottom - top);
      right -= d.m_width + m_hgap;
    }
    c = m_west;
    if (c != null) {
      c.setDimension(c.m_width, bottom - top);
      Dimension d = c.getPreferredSize();
      c.setBounds(left, top, d.m_width, bottom - top);
      left += d.m_width + m_hgap;
    }
    c = m_center;
    if (c != null)
      c.setBounds(left, top, right - left, bottom - top);
  }

}
