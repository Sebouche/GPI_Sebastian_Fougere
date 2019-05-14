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
import ricm3.gpi.gui.layout.FlowLayout;
import ricm3.gpi.gui.layout.Root;
import ricm3.gpi.gui.widgets.ActionListener;

public class FlowLayoutExample implements Runnable {

  static FlowLayoutExample sample;

  public static void main(String args[]) throws Exception {
    sample = new FlowLayoutExample(args);
    Window.InitWindow(sample);
  }

  Window m_win;
  Root m_root;

  FlowLayoutExample(String args[]) {
  }

  @Override
  public void run() {
    ActionListener al;
    Image i1, i2;
    
    m_win = Window.getWindow();
    Root root = new Root(m_win);
    
    testFlowLayout(root,FlowLayout.HORIZONTAL, 50,50);
    testFlowLayout(root,FlowLayout.VERTICAL, 300,50);
  }
  
  private void testFlowLayout(Root root, int dir, int x, int y) {
    FlowLayout bl = new FlowLayout(dir);
    Container cont = new Container(root);
    cont.setBackground(Color.darkGray);
    cont.setBounds(x, y, 200, 200);    
    cont.layoutManager(bl);
    
    Component n = new Component(cont);
    // n.setPreferredSize(new Dimension(20,20));
    n.setBackground(Color.green);
    
    Component s = new Component(cont);
    //s.setPreferredSize(new Dimension(20,20));
    s.setBackground(Color.red);

    Component w = new Component(cont);
    //w.setPreferredSize(new Dimension(20,20));
    w.setBackground(Color.blue);

    Component e = new Component(cont);
    //e.setPreferredSize(new Dimension(20,20));
    e.setBackground(Color.yellow);

    Component c = new Component(cont);
    //c.setPreferredSize(new Dimension(20,20));
    c.setBackground(Color.magenta);
    cont.layout();
  }

}