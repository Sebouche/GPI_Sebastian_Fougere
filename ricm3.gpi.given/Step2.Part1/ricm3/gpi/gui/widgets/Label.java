package ricm3.gpi.gui.widgets;

import ricm3.gpi.gui.Font;
import ricm3.gpi.gui.Graphics;
import ricm3.gpi.gui.Window;
import ricm3.gpi.gui.layout.Component;
import ricm3.gpi.gui.layout.Container;

/**
 * A widget that is a simple label.
 * The label is a string. 
 * It will be displayed with a border, if any has been defined.
 * 
 * @author Pr. Olivier Gruber  (olivier dot gruber at acm dot org)
 */
public class Label extends Component {

  char[] m_chars;
  Font m_font;
  boolean m_border;

  public Label(Container parent, String text) {
    super(parent);
    m_chars = text.toCharArray();
    Window win = Window.getWindow();
    m_font = win.font(Window.MONOSPACED, 12F);
  }

  public void border(boolean border) {
    m_border = border;
  }

  public boolean border() {
    return m_border;
  }

  public void setFont(Font f) {
    m_font = f;
  }

  public Font getFont() {
    return m_font;
  }

  @Override
  public void paint(Graphics g) {
    super.paint(g);
    throw new Error("Not Yet Implemented");
  }

}
