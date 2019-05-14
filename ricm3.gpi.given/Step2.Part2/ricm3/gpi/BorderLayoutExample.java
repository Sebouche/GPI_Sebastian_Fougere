package ricm3.gpi;

import java.io.File;
import java.io.FileInputStream;

import ricm3.gpi.gui.Color;
import ricm3.gpi.gui.Image;
import ricm3.gpi.gui.Window;
import ricm3.gpi.gui.layout.BorderLayout;
import ricm3.gpi.gui.layout.Component;
import ricm3.gpi.gui.layout.Container;
import ricm3.gpi.gui.layout.Dimension;
import ricm3.gpi.gui.layout.Root;

public class BorderLayoutExample implements Runnable {

  static BorderLayoutExample sample;

  public static void main(String args[]) throws Exception {
    sample = new BorderLayoutExample(args);
    Window.InitWindow(sample);
  }

  Window m_win;
  Root m_root;

  BorderLayoutExample(String args[]) {
  }

  @Override
  public void run() {
    
    m_win = Window.getWindow();
    Root root = new Root(m_win);
        
    BorderLayout bl = new BorderLayout();
    Container cont = new Container(root);
    cont.setBackground(Color.green);
    cont.setBounds(50, 300, 200, 200);    
    cont.layoutManager(bl);
    
    Component north = new Component(cont);
    north.setPreferredSize(new Dimension(20,20));
    north.setBackground(Color.green);
    bl.setup(north, BorderLayout.NORTH);
    
    Component south = new Component(cont);
    south.setPreferredSize(new Dimension(20,20));
    south.setBackground(Color.red);
    bl.setup(south, BorderLayout.SOUTH);

    Component west = new Component(cont);
    west.setPreferredSize(new Dimension(20,20));
    west.setBackground(Color.blue);
    bl.setup(west, BorderLayout.WEST);

    Component east = new Component(cont);
    east.setPreferredSize(new Dimension(20,20));
    east.setBackground(Color.yellow);
    bl.setup(east, BorderLayout.EAST);

    Component center = new Component(cont);
    center.setPreferredSize(new Dimension(20,20));
    center.setBackground(Color.darkGray);
    bl.setup(center, BorderLayout.CENTER);
    cont.layout();
    root.repaint();
  }
  
}