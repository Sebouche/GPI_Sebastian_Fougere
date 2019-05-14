package ricm3.gpi.gui;

/**
 * @author Pr. Olivier Gruber (olivier dot gruber at acm dot org)
 */

public interface Graphics {

  /**
   * Creates a new <code>Graphics</code> object based on this
   * <code>Graphics</code> object, but with a new translation and clip area.
   * The new <code>Graphics</code> object has its origin
   * translated to the specified point (<i>x</i>,&nbsp;<i>y</i>).
   * Its clip area is determined by the intersection of the original
   * clip area with the specified rectangle.  The arguments are all
   * interpreted in the coordinate system of the original
   * <code>Graphics</code> object. The new graphics context is
   * identical to the original, except in two respects:
   *
   * <ul>
   * <li>
   * The new graphics context is translated by (<i>x</i>,&nbsp;<i>y</i>).
   * That is to say, the point (<code>0</code>,&nbsp;<code>0</code>) in the
   * new graphics context is the same as (<i>x</i>,&nbsp;<i>y</i>) in
   * the original graphics context.
   * <li>
   * The new graphics context has an additional clipping rectangle, in
   * addition to whatever (translated) clipping rectangle it inherited
   * from the original graphics context. The origin of the new clipping
   * rectangle is at (<code>0</code>,&nbsp;<code>0</code>), and its size
   * is specified by the <code>width</code> and <code>height</code>
   * arguments.
   * </ul>
   * <p>
   * @param      x   the <i>x</i> coordinate.
   * @param      y   the <i>y</i> coordinate.
   * @param      width   the width of the clipping rectangle.
   * @param      height   the height of the clipping rectangle.
   * @return     a new graphics context.
   */
  public Graphics create(int x, int y, int width, int height);

  /**
   * Disposes of this graphics context and releases
   * any system resources that it is using.
   * A <code>Graphics</code> object cannot be used after
   * <code>dispose</code>has been called.
   * <p>
   * When a Java program runs, a large number of <code>Graphics</code>
   * objects can be created within a short time frame.
   * Although the finalization process of the garbage collector
   * also disposes of the same system resources, it is preferable
   * to manually free the associated resources by calling this
   * method rather than to rely on a finalization process which
   * may not run to completion for a long period of time.
   * <p>
   * Graphics objects which are provided as arguments to the
   * <code>paint</code> and <code>update</code> methods
   * of components are automatically released by the system when
   * those methods return. For efficiency, programmers should
   * call <code>dispose</code> when finished using
   * a <code>Graphics</code> object only if it was created
   * directly from a component or another <code>Graphics</code> object.
   */
  public abstract void dispose();

  /**
   * Gets this graphics context's current color.
   * @return    this graphics context's current color.
   */
  public abstract Color getColor();

  /**
   * Sets this graphics context's current color to the specified
   * color. All subsequent graphics operations using this graphics
   * context use this specified color.
   * @param     c   the new rendering color.
   */
  public abstract void setColor(Color c);

  /**
   * Sets the paint mode of this graphics context to overwrite the
   * destination with this graphics context's current color.
   * This sets the logical pixel operation function to the paint or
   * overwrite mode.  All subsequent rendering operations will
   * overwrite the destination with the current color.
   */
  public abstract void setPaintMode();

  /**
   * Sets the paint mode of this graphics context to alternate between
   * this graphics context's current color and the new specified color.
   * This specifies that logical pixel operations are performed in the
   * XOR mode, which alternates pixels between the current color and
   * a specified XOR color.
   * <p>
   * When drawing operations are performed, pixels which are the
   * current color are changed to the specified color, and vice versa.
   * <p>
   * Pixels that are of colors other than those two colors are changed
   * in an unpredictable but reversible manner; if the same figure is
   * drawn twice, then all pixels are restored to their original values.
   * @param     c1 the XOR alternation color
   */
  public abstract void setXORMode(Color c1);

  /**
   * Sets the current clip to the rectangle specified by the given
   * coordinates.  This method sets the user clip, which is
   * independent of the clipping associated with device bounds
   * and window visibility.
   * Rendering operations have no effect outside of the clipping area.
   * @param       x the <i>x</i> coordinate of the new clip rectangle.
   * @param       y the <i>y</i> coordinate of the new clip rectangle.
   * @param       width the width of the new clip rectangle.
   * @param       height the height of the new clip rectangle.
   */
  public abstract void setClip(int x, int y, int width, int height);
  
  
  /**
   * Draws a line, using the current color, between the points
   * <code>(x1,&nbsp;y1)</code> and <code>(x2,&nbsp;y2)</code>
   * in this graphics context's coordinate system.
   * @param   x1  the first point's <i>x</i> coordinate.
   * @param   y1  the first point's <i>y</i> coordinate.
   * @param   x2  the second point's <i>x</i> coordinate.
   * @param   y2  the second point's <i>y</i> coordinate.
   */
  public abstract void drawLine(int x1, int y1, int x2, int y2);

  /**
   * Fills the specified rectangle.
   * The left and right edges of the rectangle are at
   * <code>x</code> and <code>x&nbsp;+&nbsp;width&nbsp;-&nbsp;1</code>.
   * The top and bottom edges are at
   * <code>y</code> and <code>y&nbsp;+&nbsp;height&nbsp;-&nbsp;1</code>.
   * The resulting rectangle covers an area
   * <code>width</code> pixels wide by
   * <code>height</code> pixels tall.
   * The rectangle is filled using the graphics context's current color.
   * @param         x   the <i>x</i> coordinate
   *                         of the rectangle to be filled.
   * @param         y   the <i>y</i> coordinate
   *                         of the rectangle to be filled.
   * @param         width   the width of the rectangle to be filled.
   * @param         height   the height of the rectangle to be filled.
   */
  public abstract void fillRect(int x, int y, int width, int height);

  /**
   * Draws the outline of the specified rectangle.
   * The left and right edges of the rectangle are at
   * <code>x</code> and <code>x&nbsp;+&nbsp;width</code>.
   * The top and bottom edges are at
   * <code>y</code> and <code>y&nbsp;+&nbsp;height</code>.
   * The rectangle is drawn using the graphics context's current color.
   * @param         x   the <i>x</i> coordinate
   *                         of the rectangle to be drawn.
   * @param         y   the <i>y</i> coordinate
   *                         of the rectangle to be drawn.
   * @param         width   the width of the rectangle to be drawn.
   * @param         height   the height of the rectangle to be drawn.
   */
  public abstract void drawRect(int x, int y, int width, int height);

  /**
   * Draws the outline of an oval.
   * The result is a circle or ellipse that fits within the
   * rectangle specified by the <code>x</code>, <code>y</code>,
   * <code>width</code>, and <code>height</code> arguments.
   * <p>
   * The oval covers an area that is
   * <code>width&nbsp;+&nbsp;1</code> pixels wide
   * and <code>height&nbsp;+&nbsp;1</code> pixels tall.
   * @param       x the <i>x</i> coordinate of the upper left
   *                     corner of the oval to be drawn.
   * @param       y the <i>y</i> coordinate of the upper left
   *                     corner of the oval to be drawn.
   * @param       width the width of the oval to be drawn.
   * @param       height the height of the oval to be drawn.
   */
  public abstract void drawOval(int x, int y, int width, int height);

  /**
   * Fills an oval bounded by the specified rectangle with the
   * current color.
   * @param       x the <i>x</i> coordinate of the upper left corner
   *                     of the oval to be filled.
   * @param       y the <i>y</i> coordinate of the upper left corner
   *                     of the oval to be filled.
   * @param       width the width of the oval to be filled.
   * @param       height the height of the oval to be filled.
   */
  public abstract void fillOval(int x, int y, int width, int height);

  /**
   * Draws the outline of a circular or elliptical arc
   * covering the specified rectangle.
   * <p>
   * The resulting arc begins at <code>startAngle</code> and extends
   * for <code>arcAngle</code> degrees, using the current color.
   * Angles are interpreted such that 0&nbsp;degrees
   * is at the 3&nbsp;o'clock position.
   * A positive value indicates a counter-clockwise rotation
   * while a negative value indicates a clockwise rotation.
   * <p>
   * The center of the arc is the center of the rectangle whose origin
   * is (<i>x</i>,&nbsp;<i>y</i>) and whose size is specified by the
   * <code>width</code> and <code>height</code> arguments.
   * <p>
   * The resulting arc covers an area
   * <code>width&nbsp;+&nbsp;1</code> pixels wide
   * by <code>height&nbsp;+&nbsp;1</code> pixels tall.
   * <p>
   * The angles are specified relative to the non-square extents of
   * the bounding rectangle such that 45 degrees always falls on the
   * line from the center of the ellipse to the upper right corner of
   * the bounding rectangle. As a result, if the bounding rectangle is
   * noticeably longer in one axis than the other, the angles to the
   * start and end of the arc segment will be skewed farther along the
   * longer axis of the bounds.
   * @param        x the <i>x</i> coordinate of the
   *                    upper-left corner of the arc to be drawn.
   * @param        y the <i>y</i>  coordinate of the
   *                    upper-left corner of the arc to be drawn.
   * @param        width the width of the arc to be drawn.
   * @param        height the height of the arc to be drawn.
   * @param        startAngle the beginning angle.
   * @param        arcAngle the angular extent of the arc,
   *                    relative to the start angle.
   */
  public abstract void drawArc(int x, int y, int width, int height,
                               int startAngle, int arcAngle);

  /**
   * Fills a circular or elliptical arc covering the specified rectangle.
   * <p>
   * The resulting arc begins at <code>startAngle</code> and extends
   * for <code>arcAngle</code> degrees.
   * Angles are interpreted such that 0&nbsp;degrees
   * is at the 3&nbsp;o'clock position.
   * A positive value indicates a counter-clockwise rotation
   * while a negative value indicates a clockwise rotation.
   * <p>
   * The center of the arc is the center of the rectangle whose origin
   * is (<i>x</i>,&nbsp;<i>y</i>) and whose size is specified by the
   * <code>width</code> and <code>height</code> arguments.
   * <p>
   * The resulting arc covers an area
   * <code>width&nbsp;+&nbsp;1</code> pixels wide
   * by <code>height&nbsp;+&nbsp;1</code> pixels tall.
   * <p>
   * The angles are specified relative to the non-square extents of
   * the bounding rectangle such that 45 degrees always falls on the
   * line from the center of the ellipse to the upper right corner of
   * the bounding rectangle. As a result, if the bounding rectangle is
   * noticeably longer in one axis than the other, the angles to the
   * start and end of the arc segment will be skewed farther along the
   * longer axis of the bounds.
   * @param        x the <i>x</i> coordinate of the
   *                    upper-left corner of the arc to be filled.
   * @param        y the <i>y</i>  coordinate of the
   *                    upper-left corner of the arc to be filled.
   * @param        width the width of the arc to be filled.
   * @param        height the height of the arc to be filled.
   * @param        startAngle the beginning angle.
   * @param        arcAngle the angular extent of the arc,
   *                    relative to the start angle.
   */
  public abstract void fillArc(int x, int y, int width, int height,
                               int startAngle, int arcAngle);

  /**
   * Draws a sequence of connected lines defined by
   * arrays of <i>x</i> and <i>y</i> coordinates.
   * Each pair of (<i>x</i>,&nbsp;<i>y</i>) coordinates defines a point.
   * The figure is not closed if the first point
   * differs from the last point.
   * @param       xPoints an array of <i>x</i> points
   * @param       yPoints an array of <i>y</i> points
   * @param       nPoints the total number of points
   */
  public abstract void drawPolyline(int xPoints[], int yPoints[],
                                    int nPoints);

  /**
   * Draws a closed polygon defined by
   * arrays of <i>x</i> and <i>y</i> coordinates.
   * Each pair of (<i>x</i>,&nbsp;<i>y</i>) coordinates defines a point.
   * <p>
   * This method draws the polygon defined by <code>nPoint</code> line
   * segments, where the first <code>nPoint&nbsp;-&nbsp;1</code>
   * line segments are line segments from
   * <code>(xPoints[i&nbsp;-&nbsp;1],&nbsp;yPoints[i&nbsp;-&nbsp;1])</code>
   * to <code>(xPoints[i],&nbsp;yPoints[i])</code>, for
   * 1&nbsp;&le;&nbsp;<i>i</i>&nbsp;&le;&nbsp;<code>nPoints</code>.
   * The figure is automatically closed by drawing a line connecting
   * the final point to the first point, if those points are different.
   * @param        xPoints   a an array of <code>x</code> coordinates.
   * @param        yPoints   a an array of <code>y</code> coordinates.
   * @param        nPoints   a the total number of points.
   */
  public abstract void drawPolygon(int xPoints[], int yPoints[],
                                   int nPoints);

  /**
   * Fills a closed polygon defined by
   * arrays of <i>x</i> and <i>y</i> coordinates.
   * <p>
   * This method draws the polygon defined by <code>nPoint</code> line
   * segments, where the first <code>nPoint&nbsp;-&nbsp;1</code>
   * line segments are line segments from
   * <code>(xPoints[i&nbsp;-&nbsp;1],&nbsp;yPoints[i&nbsp;-&nbsp;1])</code>
   * to <code>(xPoints[i],&nbsp;yPoints[i])</code>, for
   * 1&nbsp;&le;&nbsp;<i>i</i>&nbsp;&le;&nbsp;<code>nPoints</code>.
   * The figure is automatically closed by drawing a line connecting
   * the final point to the first point, if those points are different.
   * <p>
   * The area inside the polygon is defined using an
   * even-odd fill rule, also known as the alternating rule.
   * @param        xPoints   a an array of <code>x</code> coordinates.
   * @param        yPoints   a an array of <code>y</code> coordinates.
   * @param        nPoints   a the total number of points.
   */
  public abstract void fillPolygon(int xPoints[], int yPoints[],
                                   int nPoints);

  /**
   * Draws the text given by the specified string, using this
   * graphics context's current font and color. The baseline of the
   * leftmost character is at position (<i>x</i>,&nbsp;<i>y</i>) in this
   * graphics context's coordinate system.
   * @param       str      the string to be drawn.
   * @param       x        the <i>x</i> coordinate.
   * @param       y        the <i>y</i> coordinate.
   * @throws NullPointerException if <code>str</code> is <code>null</code>.
   */
  public abstract void drawString(String str, int x, int y);
  public abstract void drawString(char[] chars, int offset, int length, int x, int y);

  public abstract Font getFont();
  public abstract void setFont(Font f);
  
  /**
   * @return the current font height in pixels.
   */
  public abstract int getFontHeight();

  /**
   * @return the string width in pixels when drawn.
   */
  public abstract int getStringWidth(String s);
  public abstract int getStringWidth(char chars[], int offset, int length);
  public abstract int getCharWidth(char c);

  /**
   * The image is drawn with its top-left corner at
   * (<i>x</i>,&nbsp;<i>y</i>) in this graphics context's coordinate
   * space. Transparent pixels in the image do not affect whatever
   * pixels are already there.
   * @param    img the specified image to be drawn. This method does
   *               nothing if <code>img</code> is null.
   * @param    x   the <i>x</i> coordinate.
   * @param    y   the <i>y</i> coordinate.
   * @param    width   
   * @param    height
   */
  public abstract void drawImage(Image img, int x, int y, int width, int height);

}
