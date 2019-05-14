package ricm3.gpi.gui;

/**
 * @author Pr. Olivier Gruber (olivier dot gruber at acm dot org)
 */

public abstract class Image {

  /**
   * @return the width of this image, in pixels.
   */
  public abstract int getWidth();
  /**
   * @return the height of this image, in pixels.
   */
  public abstract int getHeight();
  
  /**
   * @return the sub-image copied from the given bounds
   */
  public abstract Image getSubImage(int x, int y, int w, int h);

}
