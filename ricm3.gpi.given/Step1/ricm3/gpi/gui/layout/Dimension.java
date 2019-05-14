package ricm3.gpi.gui.layout;

/**
 * @author Pr. Olivier Gruber (olivier dot gruber at acm dot org)
 */
public class Dimension {
  public int m_width, m_height;

  public Dimension() {
    this(0, 0);
  }

  public Dimension(int width, int height) {
    m_width = width;
    m_height = height;
  }

  public Dimension(Dimension d) {
    if (d != null) {
      m_width = d.m_width;
      m_height = d.m_height;
    }
  }

  public void set(int width, int height) {
    m_width = width;
    m_height = height;
  }

  public int width() {
    return m_width;
  }

  public int height() {
    return m_height;
  }

}
