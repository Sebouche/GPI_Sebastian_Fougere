package ricm3.gpi.gui.widgets;

import ricm3.gpi.gui.Font;
import ricm3.gpi.gui.Graphics;
import ricm3.gpi.gui.Image;
import ricm3.gpi.gui.Window;
import ricm3.gpi.gui.layout.Component;
import ricm3.gpi.gui.layout.Container;

/**
 * A widget that is a simple button than can be clicked.
 * To know about click events on a button, you need to register
 * an ActionListener on that button.
 * 
 * A button can have a label, that is, a name.
 * 
 * A button may also have two images, one for when the button 
 * is pressed down and the other for when the button is released.
 * 
 * 
 * @author Pr. Olivier Gruber  (olivier dot gruber at acm dot org)
 */
public class Button extends Component {

  String m_label;
  Font m_font;
  Image m_pressed;
  Image m_released;
  
  public Button(Container parent) {
    super(parent);
    throw new Error("Not Yet Implemented");
  }

  public String getLabel() {
    return m_label;
  }
  
  @Override
  public void paint(Graphics g) {
    super.paint(g);
    throw new Error("Not Yet Implemented");
  }

  public void setActionListener(ActionListener al) {
    throw new Error("Not Yet Implemented");
  }

  public void setFont(Font f) {
    throw new Error("Not Yet Implemented");
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

}
