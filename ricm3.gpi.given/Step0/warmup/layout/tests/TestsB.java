package warmup.layout.tests;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import warmup.layout.Component;
import warmup.layout.Container;
import warmup.layout.Location;
import warmup.layout.Root;

public class TestsB {

  @Test(timeout=1000)
  public void test01() throws Throwable {
    Root root = new Root(600, 480);
    Component c = new Component(root);
    c.setBounds(25, 65, 400, 300);

    Component s;
    // selecting outside the root, 
    // should not select anything.
    s = root.select(-1, 0);
    assertTrue(s == null);

    s = root.select(601, 40);
    assertTrue(s == null);

    s = root.select(60, 482);
    assertTrue(s == null);

  }
  
  @Test(timeout=1000)
  public void test02() throws Throwable {
    Root root = new Root(600, 480);
    Component c = new Component(root);
    c.setBounds(25, 65, 400, 300);

    Component s;
    // selecting all coordinates within 
    // the component
    for (int x = 25; x < 425; x++)
      for (int y = 65; y < 365; y++) {
        s = root.select(x, y);
        assertTrue(s == c);
      }
    
    // selecting in left border, must root.
    for (int x = 0; x < 25; x++)
      for (int y = 0; y < 480; y++) {
        s = root.select(x, y);
        assertTrue(s == root);
      }

    // selecting in right border, must root.
    for (int x = 425; x < 600; x++)
      for (int y = 0; y < 480; y++) {
        s = root.select(x, y);
        assertTrue(s == root);
      }

    // selecting in top border, must root.
    for (int x = 0; x < 480; x++)
      for (int y = 0; y < 65; y++) {
        s = root.select(x, y);
        assertTrue(s == root);
      }

    // selecting in bottom border, must root.
    for (int x = 0; x < 480; x++)
      for (int y = 365; y < 480; y++) {
        s = root.select(x, y);
        assertTrue(s == root);
      }

  }

  @Test(timeout=1000)
  public void test03() throws Throwable {
    Component s;
    Root root = new Root(600, 480);
    s = root.select(7, 12);
    assertTrue(s == root);

    Container cont = new Container(root);
    cont.setBounds(5, 10, 400, 300);
    s = root.select(7, 12);
    assertTrue(s == cont);

    Component c = new Component(cont);
    c.setBounds(12, 7, 100, 50);
    s = root.select(7, 12);
    assertTrue(s == cont);

    Location l = new Location(1, 3);
    c.toGlobal(l);
    assertTrue(l.x() == 5 + 12 + 1);
    assertTrue(l.y() == 10 + 7 + 3);

    s = root.select(l.x(), l.y());
    assertTrue(s == c);

    c.toLocal(l);
    assertTrue(l.x() == 1);
    assertTrue(l.y() == 3);
  }

}
