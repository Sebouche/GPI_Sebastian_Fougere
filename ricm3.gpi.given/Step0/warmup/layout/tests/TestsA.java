package warmup.layout.tests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import warmup.layout.Component;
import warmup.layout.Container;
import warmup.layout.Location;
import warmup.layout.Root;

public class TestsA {

  @Test(timeout=1000)
  public void test00() throws Throwable {
    Root root = new Root(600, 480);
    assertTrue(root.width()== 600);
    assertTrue(root.height()== 480);
  }
  
  @Test(timeout=1000,expected = IllegalStateException.class)
  public void test000() throws Throwable {
    Root root = new Root(600, 480);
    root.remove();
  }

  @Test(timeout=1000)
  public void test01() throws Throwable {
    Root root = new Root(600, 480);
    Component c = new Component(root);
    Util.setBounds(c, 25, 65, 400, 300);
    assertTrue(c.parent() == root);
    assertTrue(Util.contains(root, c));
  }

  @Test(timeout=1000)
  public void test02() throws Throwable {
    Root root = new Root(600, 480);
    Component c = new Component(root);
    c.setBounds(25, 65, 400, 300);
    
    Location l = new Location(5,10);
    c.toGlobal(l);
    assertTrue(l.x() == 25+5);
    assertTrue(l.y() == 65+10);
        
    c.toLocal(l);
    assertTrue(l.x() == 5);
    assertTrue(l.y() == 10);
  }

  @Test(timeout=1000)
  public void test03() throws Throwable {
    Root root = new Root(600, 480);
    Container cont = new Container(root);
    Util.setBounds(cont, 5, 10, 400, 300);
    assertTrue(cont.parent() == root);
    assertTrue(Util.contains(root, cont));    
    Component c = new Component(cont);
    Util.setBounds(c, 12, 7, 100, 50);
    
  }

  @Test(timeout=1000)
  public void test04() throws Throwable {
    Root root = new Root(600, 480);

    Container cont = new Container(root);
    cont.setBounds(5, 10, 400, 300);
    Component c = new Component(cont);
    c.setBounds(12, 7, 100, 50);
    
    Location l = new Location(1,3);
    c.toGlobal(l);
    assertTrue(l.x() == 5+12+1);
    assertTrue(l.y() == 10+7+3);
    
    c.toLocal(l);
    assertTrue(l.x() == 1);
    assertTrue(l.y() == 3);
  }

}
