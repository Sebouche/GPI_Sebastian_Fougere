package ricm3.gpi.gui.impl;

import java.awt.AWTEvent;
import java.awt.Graphics;
import java.awt.Toolkit;

public class GuiCanvas extends java.awt.Canvas {

  GuiWindow m_window;

  GuiCanvas(GuiWindow win) {
    m_window = win;
  }

  //  public void setPaintListener(ricm3.gui.PaintListener pl) {
  //    m_pl = pl;
  //    if (m_pl!=null)
  //      m_pl.resized(getWidth(), getHeight());
  //  }

  @Override
  public final void paint(Graphics g) {
    ricm3.gpi.gui.WindowListener pl;
    pl = m_window.m_pl;
    GuiGraphics gg;
    gg = new GuiGraphics(g);
    if (pl == null)
      super.paint(g);
    else
      pl.paint(gg);
    Toolkit.getDefaultToolkit().sync();
  }

  @Override
  public void setBounds(int x, int y, int width, int height) {
    super.setBounds(x, y, width, height);
    ricm3.gpi.gui.WindowListener pl;
    pl = m_window.m_pl;
    if (pl != null)
      pl.resized(width, height);
  }

  /*
   * Over load processEvent which is inherited from class java.awt.Window
   * Our defined SimpleAWTEvent will be handled here.
   */
  protected void processEvent(AWTEvent event) {
    if (event instanceof GuiWindow.RunnableEvent) {
      GuiWindow.RunnableEvent tr = (GuiWindow.RunnableEvent)event;
      tr.run();
    } else 
      super.processEvent(event);
  }

}