package ricm3.gpi.gui;

/**
 * @author Pr. Olivier Gruber (olivier dot gruber at acm dot org)
 */

public class Color {

  /**
   * The color white. In the default sRGB space.
   */
  public final static Color white = new Color(255, 255, 255);

  /**
   * The color white. In the default sRGB space.
   * 
   * @since 1.4
   */
  public final static Color WHITE = white;

  /**
   * The color light gray. In the default sRGB space.
   */
  public final static Color lightGray = new Color(192, 192, 192);

  /**
   * The color light gray. In the default sRGB space.
   * 
   * @since 1.4
   */
  public final static Color LIGHT_GRAY = lightGray;

  /**
   * The color gray. In the default sRGB space.
   */
  public final static Color gray = new Color(128, 128, 128);

  /**
   * The color gray. In the default sRGB space.
   * 
   * @since 1.4
   */
  public final static Color GRAY = gray;

  /**
   * The color dark gray. In the default sRGB space.
   */
  public final static Color darkGray = new Color(64, 64, 64);

  /**
   * The color dark gray. In the default sRGB space.
   * 
   * @since 1.4
   */
  public final static Color DARK_GRAY = darkGray;

  /**
   * The color black. In the default sRGB space.
   */
  public final static Color black = new Color(0, 0, 0);

  /**
   * The color black. In the default sRGB space.
   * 
   * @since 1.4
   */
  public final static Color BLACK = black;

  /**
   * The color red. In the default sRGB space.
   */
  public final static Color red = new Color(255, 0, 0);

  /**
   * The color red. In the default sRGB space.
   * 
   * @since 1.4
   */
  public final static Color RED = red;

  /**
   * The color pink. In the default sRGB space.
   */
  public final static Color pink = new Color(255, 175, 175);

  /**
   * The color pink. In the default sRGB space.
   * 
   * @since 1.4
   */
  public final static Color PINK = pink;

  /**
   * The color orange. In the default sRGB space.
   */
  public final static Color orange = new Color(255, 200, 0);

  /**
   * The color orange. In the default sRGB space.
   * 
   * @since 1.4
   */
  public final static Color ORANGE = orange;

  /**
   * The color yellow. In the default sRGB space.
   */
  public final static Color yellow = new Color(255, 255, 0);

  /**
   * The color yellow. In the default sRGB space.
   * 
   * @since 1.4
   */
  public final static Color YELLOW = yellow;

  /**
   * The color green. In the default sRGB space.
   */
  public final static Color green = new Color(0, 255, 0);

  /**
   * The color green. In the default sRGB space.
   * 
   * @since 1.4
   */
  public final static Color GREEN = green;

  /**
   * The color magenta. In the default sRGB space.
   */
  public final static Color magenta = new Color(255, 0, 255);

  /**
   * The color magenta. In the default sRGB space.
   * 
   * @since 1.4
   */
  public final static Color MAGENTA = magenta;

  /**
   * The color cyan. In the default sRGB space.
   */
  public final static Color cyan = new Color(0, 255, 255);

  /**
   * The color cyan. In the default sRGB space.
   * 
   * @since 1.4
   */
  public final static Color CYAN = cyan;

  /**
   * The color blue. In the default sRGB space.
   */
  public final static Color blue = new Color(0, 0, 255);

  /**
   * The color blue. In the default sRGB space.
   * 
   * @since 1.4
   */
  public final static Color BLUE = blue;

  /**
   * The color value.
   * 
   * @serial
   * @see #getRGB
   */
  int value;

  public Color(int r, int g, int b) {
    this(r, g, b, 255);
  }

  /**
   * Creates a color with the specified red, green, blue, and alpha values
   * in the range (0 - 255).
   *
   * @throws IllegalArgumentException
   *           if <code>r</code>, <code>g</code>, <code>b</code> or
   *           <code>a</code> are outside of the range 0 to 255, inclusive
   * @param r
   *          the red component
   * @param g
   *          the green component
   * @param b
   *          the blue component
   * @param a
   *          the alpha component (255 is for an opaque color).
   */
  public Color(int r, int g, int b, int a) {
    value = ((a & 0xFF) << 24) | ((r & 0xFF) << 16) | ((g & 0xFF) << 8) | ((b & 0xFF) << 0);
  }
  
  /**
   * Creates a color with the specified combined RGB value
   * consisting of the red component in bits 16-23, the green component
   * in bits 8-15, and the blue component in bits 0-7.  
   *
   * @param rgb the combined RGB components
   * @param the alpha value, 255 is opaque color
   */
  public Color(int rgb, int a) {
      value = ((a & 0xFF) << 24) | rgb;
  }
  /**
   * Returns the RGB value representing the color.
   *   - Bits 24-31 are alpha
   *   - Bits 16-23 are red
   *   - Bits 8-15 are green
   *   - Bits 0-7 are blue
   * @return the RGB value of this color
   */
  public int getRGB() {
      return value;
  }

  private static final double FACTOR = 0.7;

  /**
   * Creates a new <code>Color</code> that is a brighter version of this
   * <code>Color</code>.
   * <p>
   * This method applies an arbitrary scale factor to each of the three RGB
   * components of this <code>Color</code> to create a brighter version
   * of this <code>Color</code>.
   * The {@code alpha} value is preserved.
   * Although <code>brighter</code> and
   * <code>darker</code> are inverse operations, the results of a
   * series of invocations of these two methods might be inconsistent
   * because of rounding errors.
   * @return     a new <code>Color</code> object that is
   *                 a brighter version of this <code>Color</code>
   *                 with the same {@code alpha} value.
   */
  public Color brighter() {
      int r = getRed();
      int g = getGreen();
      int b = getBlue();
      int alpha = getAlpha();

      /* From 2D group:
       * 1. black.brighter() should return grey
       * 2. applying brighter to blue will always return blue, brighter
       * 3. non pure color (non zero rgb) will eventually return white
       */
      int i = (int)(1.0/(1.0-FACTOR));
      if ( r == 0 && g == 0 && b == 0) {
          return new Color(i, i, i, alpha);
      }
      if ( r > 0 && r < i ) r = i;
      if ( g > 0 && g < i ) g = i;
      if ( b > 0 && b < i ) b = i;

      return new Color(Math.min((int)(r/FACTOR), 255),
                       Math.min((int)(g/FACTOR), 255),
                       Math.min((int)(b/FACTOR), 255),
                       alpha);
  }

  /**
   * Creates a new <code>Color</code> that is a darker version of this
   * <code>Color</code>.
   * <p>
   * This method applies an arbitrary scale factor to each of the three RGB
   * components of this <code>Color</code> to create a darker version of
   * this <code>Color</code>.
   * The {@code alpha} value is preserved.
   * Although <code>brighter</code> and
   * <code>darker</code> are inverse operations, the results of a series
   * of invocations of these two methods might be inconsistent because
   * of rounding errors.
   * @return  a new <code>Color</code> object that is
   *                    a darker version of this <code>Color</code>
   *                    with the same {@code alpha} value.
   */
  public Color darker() {
      return new Color(Math.max((int)(getRed()  *FACTOR), 0),
                       Math.max((int)(getGreen()*FACTOR), 0),
                       Math.max((int)(getBlue() *FACTOR), 0),
                       getAlpha());
  }

  /**
   * @return the red component in the range 0-255
   */
  public int getRed() {
      return (getRGB() >> 16) & 0xFF;
  }

  /**
   * @return the green component in the range 0-255
   */
  public int getGreen() {
      return (getRGB() >> 8) & 0xFF;
  }

  /**
   * @return the blue component in the range 0-255
   */
  public int getBlue() {
      return (getRGB() >> 0) & 0xFF;
  }

  /**
   * @return the alpha component in the range 0-255
   */
  public int getAlpha() {
      return (getRGB() >> 24) & 0xff;
  }

}
