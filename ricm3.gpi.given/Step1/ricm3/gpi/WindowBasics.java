package ricm3.gpi;

import ricm3.gpi.gui.Color;
import ricm3.gpi.gui.Graphics;
import ricm3.gpi.gui.KeyListener;
import ricm3.gpi.gui.MouseListener;
import ricm3.gpi.gui.Window;
import ricm3.gpi.gui.WindowListener;
import ricm3.gpi.gui.layout.Root;

public class WindowBasics implements Runnable, MouseListener, KeyListener, WindowListener  {

  static WindowBasics bl;

  public static void main(String args[]) throws Exception {
    bl = new WindowBasics(args);
    Window.InitWindow(bl);
  }

  Window m_win;
  Root m_root;

  WindowBasics(String args[]) {
  }

  @Override
  public void run() {
    m_win = Window.getWindow();
    m_win.setKeyListener(this);
    m_win.setMouseListener(this);
    m_win.setWindowListener(this);
    m_win.repaint(0, 0, m_win.getWidth(), m_win.getHeight());
  }

  @Override
  public void paint(Graphics g) {
    g.setColor(Color.yellow);
    g.fillRect(0, 0, m_win.getWidth(), m_win.getHeight());
    g.setColor(Color.red);
    g.fillRect(50, 50, 100, 100);
  }

  @Override
  public void resized(int width, int height) {
    m_win.repaint(0, 0, width, height);
  }

  @Override
  public void keyPressed(char k, int code) {
    System.out.println("keyPressed: '"+k+"' ("+code+")");
  }

  @Override
  public void keyReleased(char k, int code) {
    System.out.println("keyReleased: '"+k+"' ("+code+")");
  }

  @Override
  public void mouseMoved(int x, int y) {
    System.out.println("mouseMoved: ("+x+","+y+")");
  }

  @Override
  public void mousePressed(int x, int y, int buttons) {
    System.out.println("mousePressed: ("+x+","+y+") buttons="+buttons);
  }

  @Override
  public void mouseReleased(int x, int y, int buttons) {
    System.out.println("mouseReleased: ("+x+","+y+") buttons="+buttons);
  }

  @Override
  public void mouseEntered(int x, int y) {
    System.out.println("mouseEntered: ("+x+","+y+")");
  }

  @Override
  public void mouseExited() {
    System.out.println("mouseExited");
  }

}
