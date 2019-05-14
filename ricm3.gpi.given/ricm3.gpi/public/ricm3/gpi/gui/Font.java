package ricm3.gpi.gui;

/**
 * @author Pr. Olivier Gruber (olivier dot gruber at acm dot org)
 */

public abstract class Font {
  
  /**
   * The plain style constant.
   */
  public static final int PLAIN       = 0;

  /**
   * The bold style constant.  This can be combined with the other style
   * constants (except PLAIN) for mixed styles.
   */
  public static final int BOLD        = 1;

  /**
   * The italicized style constant.  This can be combined with the other
   * style constants (except PLAIN) for mixed styles.
   */
  public static final int ITALIC      = 2;
  
  /**
   * Derive a new font from this font, with the given style and size.
   * @param style
   * @param size
   * @return
   */
  public abstract Font derive(int style, float size);

  /**
   * @return the size of this font.
   */
  public abstract float getSize();
  
  /**
   * @return the style of this font.
   */
  public abstract int getStyle();


}
