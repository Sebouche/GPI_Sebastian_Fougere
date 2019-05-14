package ricm3.gpi;


import java.io.File;
import java.io.FileInputStream;

import ricm3.gpi.gui.Color;
import ricm3.gpi.gui.Image;
import ricm3.gpi.gui.Window;
import ricm3.gpi.gui.layout.Component;
import ricm3.gpi.gui.layout.Container;
import ricm3.gpi.gui.layout.Root;

public class DrawCanvasExample implements Runnable {

  static DrawCanvasExample gm;
  
  public static void main(String args[]) throws Exception {
    gm = new DrawCanvasExample(args);
    Window.InitWindow(gm);
  }
  
  DrawCanvasExample(String[] args) {
    
  }
  
  Window m_win;
  Root m_root;

  @Override
  public void run() {
    int border = 4;
    int width = 400;
    int height = 400;
    m_win = Window.getWindow();
    m_root = new Root(m_win);
    
    Container cont = new Container(m_root);
    cont.setBackground(Color.black);
    cont.setBounds(50, 50, width+100, height+100);    
    
    Component center = new DrawCanvas(cont);
    center.setBackground(Color.red);
    center.setBounds(50, 50, width, height);

    Component top = new Component(cont);
    top.setBackground(Color.green);
    top.setBounds(50, border, width, 50-border);

    Component left = new Component(cont);
    left.setBackground(Color.yellow);
    left.setBounds(border, border, 50-border, height+100-2*border);

    Component right = new Component(cont);
    right.setBackground(Color.orange);
    right.setBounds(width+50, border, 50-border, height+100-2*border);

    Component bottom = new Component(cont);
    bottom.setBackground(Color.magenta);
    bottom.setBounds(50, height+50, width, 50-border);

    m_root.repaint();
  }
  
}

