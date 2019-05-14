package ricm3.gpi.gui;

/**
 * @author Pr. Olivier Gruber (olivier dot gruber at acm dot org)
 */

public interface MouseListener {

  public static final int NOBUTTON = 0;
  public static final int BUTTON1 = 1;
  public static final int BUTTON2 = 2;
  public static final int BUTTON3 = 3;

  public void mouseMoved(int x, int y);

  public void mousePressed(int x, int y, int buttons);

  public void mouseReleased(int x, int y, int buttons);

  public void mouseEntered(int x, int y);

  public void mouseExited();

}
