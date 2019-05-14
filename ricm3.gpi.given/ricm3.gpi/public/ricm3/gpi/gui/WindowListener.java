package ricm3.gpi.gui;

/**
 * @author Pr. Olivier Gruber (olivier dot gruber at acm dot org)
 */

public interface WindowListener {

  void paint(Graphics g);
  
  void resized(int width, int height);
}
