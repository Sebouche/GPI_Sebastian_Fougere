package ricm3.gpi.gui.impl;

import ricm3.gpi.gui.Font;

public class GuiFont extends Font {

  java.awt.Font m_font;
  
  GuiFont(java.awt.Font font) {
    m_font = font;
  }
  public Font derive(int style, float size) {
    java.awt.Font f = m_font.deriveFont(style, size);
    GuiFont gf = new GuiFont(f);
    return gf;
  }
    
  @Override
  public float getSize() {
    return m_font.getSize2D();
  }
  @Override
  public int getStyle() {
    return m_font.getStyle();
  }

}
