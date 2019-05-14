package ricm3.gpi;

import java.io.File;
import java.io.FileInputStream;

import ricm3.gpi.gui.Color;
import ricm3.gpi.gui.Font;
import ricm3.gpi.gui.Image;
import ricm3.gpi.gui.Window;
import ricm3.gpi.gui.layout.Component;
import ricm3.gpi.gui.layout.Root;
import ricm3.gpi.gui.widgets.ActionListener;
import ricm3.gpi.gui.widgets.Button;

public class ButtonExample implements Runnable {

  static ButtonExample sample;

  public static void main(String args[]) throws Exception {
    sample = new ButtonExample(args);
    Window.InitWindow(sample);
  }

  Window m_win;
  Root m_root;

  ButtonExample(String args[]) {
  }

  private Image loadImage(Window win, String path) {
    try {
      return win.load(new FileInputStream(new File(path)));
    } catch (Exception ex) {
      return null;
    }
  }

  @Override
  public void run() {
    ActionListener al;
    Image i1, i2;
    
    m_win = Window.getWindow();
    Root root = new Root(m_win);
    Component center = new Component(root);
    center.setBackground(Color.green);
    center.setBounds(50, 50, 200, 200);

    i1 = loadImage(m_win, "images/raspberry-pi-color.png");
    i2 = loadImage(m_win, "images/raspberry-pi.png");
    Button b;
    b = new Button(root);
    b.setImages(i1, i2);
    b.setBounds(0, 0, 50, 50);
    al = new ButtonListener(b,"Rasp-Pi");
    b.setActionListener(al);

    Font font = m_win.font(Window.MONOSPACED, 12F);
    font = font.derive(Font.ITALIC | Font.BOLD, 24F);

    final String label = "Rasp-Pi";
    b = new Button(root);
    b.setLabel(label);
    b.setFont(font);
    b.setBounds(50, 0, 200, 50);
    al = new ButtonListener(b,"Rasp-Pi Label");
    b.setActionListener(al);
    root.repaint();
  }

  class ButtonListener implements ActionListener {
    Button m_button;
    String m_name;

    ButtonListener(Button b, String name) {
      m_button = b;
      m_name = name;
    }

    @Override
    public void action(Component c) {
      if (c instanceof Button) {
        Button b = (Button) c;
        if (b == m_button)
          System.out.println("Button " + m_name + " clicked");
        else
          System.out.println("Unknown button clicked");
      } else
        System.out.println("Unknown component clicked");
    }
  };
}