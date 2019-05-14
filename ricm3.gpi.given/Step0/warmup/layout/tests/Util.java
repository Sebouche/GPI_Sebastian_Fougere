package warmup.layout.tests;

import static org.junit.Assert.assertTrue;

import warmup.layout.Component;
import warmup.layout.Container;

public class Util {
  static boolean contains(Container parent, Component child) {
    for (int i = 0; i < parent.childrenCount(); i++) {
      Component c = parent.childrenAt(i);
      if (c == child)
        return true;
    }
    return false;
  }

  static void setBounds(Component c, int x, int y, int w, int h) {
    c.setBounds(x, y, w, h);
    assertTrue(c.x() == x);
    assertTrue(c.y() == y);
    assertTrue(c.width() == w);
    assertTrue(c.height() == h);
  }

}
