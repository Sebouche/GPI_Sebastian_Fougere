package ricm3.gpi.gui;

import java.io.InputStream;

import ricm3.gpi.gui.impl.GuiWindow;
import ricm3.gpi.gui.impl.Robot;

public abstract class Window {

  private static GuiWindow self;
  
  public static Window getWindow() {
    return self;
  }
  
  public static void InitWindow(Runnable callable) {
    self = new GuiWindow(callable);
    // do not call createWindow() from within the constructor.
    // there is a race condition between assigning "self" and
    // the window popping up and issuing the windowOpenned callback.
    // So, now that the static "self" is setup, we can safely 
    // create the window. 
    self.createWindow();
  }

  public abstract void close();

  public abstract void setKeyListener(ricm3.gpi.gui.KeyListener kl);

  public abstract void setMouseListener(ricm3.gpi.gui.MouseListener ml);

  public abstract void setWindowListener(ricm3.gpi.gui.WindowListener pl) ;

  public abstract void repaint(int x, int y, int w, int h);
  
  public abstract int getWidth();
  public abstract int getHeight();

  
  public abstract Image load(InputStream is);

  
  /**
   * A String constant for the canonical family name of the
   * logical font "Dialog". It is useful in Font construction
   * to provide compile-time verification of the name.
   * @since 1.6
   */
  public static final String DIALOG = "Dialog";

  /**
   * A String constant for the canonical family name of the
   * logical font "DialogInput". It is useful in Font construction
   * to provide compile-time verification of the name.
   * @since 1.6
   */
  public static final String DIALOG_INPUT = "DialogInput";

  /**
   * A String constant for the canonical family name of the
   * logical font "SansSerif". It is useful in Font construction
   * to provide compile-time verification of the name.
   * @since 1.6
   */
  public static final String SANS_SERIF = "SansSerif";

  /**
   * A String constant for the canonical family name of the
   * logical font "Serif". It is useful in Font construction
   * to provide compile-time verification of the name.
   * @since 1.6
   */
  public static final String SERIF = "Serif";

  /**
   * A String constant for the canonical family name of the
   * logical font "Monospaced". It is useful in Font construction
   * to provide compile-time verification of the name.
   * @since 1.6
   */
  public static final String MONOSPACED = "Monospaced";

  public abstract Font font(String name, float size);
  
}
