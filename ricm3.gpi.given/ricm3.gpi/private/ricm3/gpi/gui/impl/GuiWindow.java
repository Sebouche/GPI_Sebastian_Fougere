package ricm3.gpi.gui.impl;

import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import ricm3.gpi.gui.Font;
import ricm3.gpi.gui.Image;
import ricm3.gpi.gui.Window;

public class GuiWindow extends Window implements MouseListener, MouseMotionListener, KeyListener, WindowListener {

  private JFrame m_frame;
  private java.awt.Window m_window;
  private int m_width, m_height;
  private GuiCanvas m_canvas;
  private ricm3.gpi.gui.KeyListener m_kl;
  private ricm3.gpi.gui.MouseListener m_ml;
  ricm3.gpi.gui.WindowListener m_pl;
  private Runnable m_callable;
  public boolean m_doExit;
  private boolean m_inside;

  public GuiWindow(Runnable callable) {
    m_width = 1280;
    m_height = 960;
    m_callable = callable;
    m_doExit = true;
  }

  public void close() {
    if (m_doExit) {
      m_frame.setVisible(false);
      m_frame.dispose();
    } 
  }

  public int getWidth() {
    return m_width;
  }

  public int getHeight() {
    return m_height;
  }

  public void setKeyListener(ricm3.gpi.gui.KeyListener kl) {
    m_kl = kl;
  }

  public void setMouseListener(ricm3.gpi.gui.MouseListener ml) {
    m_ml = ml;
  }

  public void setWindowListener(ricm3.gpi.gui.WindowListener pl) {
    m_pl = pl;
    if (m_pl != null)
      m_pl.resized(m_canvas.getWidth(), m_canvas.getHeight());
  }

  public void repaint(int x, int y, int w, int h) {
    // m_canvas.repaint(x, y, w, h);
    m_canvas.repaint();
  }

  public Image load(InputStream is) {
    // File imageFile = new File("game.sample/sprites/winchester.png");
    BufferedImage img = null;
    try {
      img = ImageIO.read(is);
    } catch (IOException ex) {
      return null;
    }
    return new GuiImage(img);
  }

  java.awt.EventQueue eventQueue;
  
  public void createWindow() {

    eventQueue = Toolkit.getDefaultToolkit().getSystemEventQueue();

    m_frame = new WindowFrame();
    java.awt.Window[] windows = m_frame.getWindows();
    m_window = windows[0];
    m_window.addWindowListener(this);
    
    m_frame.setTitle("Sample Canvas");
    m_frame.setLayout(new BorderLayout());

    m_canvas = new GuiCanvas(this);
    m_canvas.setBackground(Color.black);
    m_canvas.setSize(m_width, m_height);
    m_frame.add(m_canvas, BorderLayout.CENTER);

    m_frame.setSize(m_width, m_height);
    m_frame.doLayout();
    m_frame.setVisible(true);

    m_canvas.addKeyListener(this);
    m_canvas.addMouseListener(this);
    m_canvas.addMouseMotionListener(this);

    // grab the focus on this JPanel, meaning keyboard events
    // are coming to our controller. Indeed, the focus controls
    // which part of the overall GUI receives the keyboard events.
    m_canvas.setFocusable(true);
    // m_canvas.requestFocus();
    m_canvas.requestFocusInWindow();
  }

  @Override
  public void keyTyped(KeyEvent e) {
  }

  @Override
  public void keyPressed(KeyEvent e) {
    if (m_kl != null)
      m_kl.keyPressed(e.getKeyChar(), e.getKeyCode());
    m_robot.eventNotify();
  }

  @Override
  public void keyReleased(KeyEvent e) {
    if (m_kl != null)
      m_kl.keyReleased(e.getKeyChar(), e.getKeyCode());
    m_robot.eventNotify();
  }

  @Override
  public void mouseDragged(MouseEvent e) {
    if (m_ml == null)
      return;
    m_ml.mouseMoved(e.getX(), e.getY());
  }

  @Override
  public void mouseMoved(MouseEvent e) {
    if (m_ml == null)
      return;
    if (!m_inside) {
      m_inside = true;
      m_ml.mouseEntered(e.getX(), e.getY());
    }
    m_ml.mouseMoved(e.getX(), e.getY());
    m_robot.eventNotify();
  }

  @Override
  public void mouseClicked(MouseEvent e) {
  }

  @Override
  public void mousePressed(MouseEvent e) {
    if (m_ml == null)
      return;
    m_ml.mousePressed(e.getX(), e.getY(), e.getButton());
    m_robot.eventNotify();
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    if (m_ml == null)
      return;
    m_ml.mouseReleased(e.getX(), e.getY(), e.getButton());
    m_robot.eventNotify();
  }

  @Override
  public void mouseEntered(MouseEvent e) {
    if (m_ml == null || m_inside)
      return;
    m_inside = true;
    m_ml.mouseEntered(e.getX(), e.getY());
  }

  @Override
  public void mouseExited(MouseEvent e) {
    if (m_ml == null || !m_inside)
      return;
    m_inside = false;
    m_ml.mouseExited();
  }

  @Override
  public Font font(String name, float size) {
    GuiFont font = null;
    java.awt.Font f;
    f = java.awt.Font.decode(name + "-PLAIN-" + size);
    if (f != null)
      font = new GuiFont(f);
    return font;
  }

  @Override
  public void windowOpened(WindowEvent e) {
    try {
      m_robot = new GuiRobot(m_canvas);
      m_callable.run();
    } catch (Throwable th) {
      th.printStackTrace(System.err);
      System.exit(-1);
    }
  }

  @Override
  public void windowClosing(WindowEvent e) {
    if (m_doExit)
      System.exit(0);
  }

  @Override
  public void windowClosed(WindowEvent e) {
    if (m_doExit)
      System.exit(0);
  }

  @Override
  public void windowIconified(WindowEvent e) {
  }

  @Override
  public void windowDeiconified(WindowEvent e) {
  }

  @Override
  public void windowActivated(WindowEvent e) {
  }

  @Override
  public void windowDeactivated(WindowEvent e) {
  }

  GuiRobot m_robot;
  public Robot getRobot() {
    return m_robot;
  }

  class WindowFrame extends JFrame {
    WindowFrame() {
      enableEvents(RunnableEvent.EVENT_ID);
    }
  }
  
  class RunnableEvent  
  extends AWTEvent {
    public static final int EVENT_ID = AWTEvent.RESERVED_ID_MAX + 1;
    Runnable runnable;
    
    RunnableEvent(Object target, Runnable runnable) {
      super(target, EVENT_ID);
      this.runnable=runnable;
    }
    void run() {
      runnable.run();
    }
  }
  
  public void post(Runnable r) {
    eventQueue.postEvent( new RunnableEvent(m_canvas, r));    
  }
}
