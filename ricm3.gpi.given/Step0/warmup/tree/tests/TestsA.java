package warmup.tree.tests;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import warmup.tree.Leaf;
import warmup.tree.Node;
import warmup.tree.NotFoundException;
import warmup.tree.Tree;

/*
 * Have a look at these online resources:
 * 
 *  https://junit.org/junit4/
 *    
 * A small JUnit Cook Book:
 *    http://junit.sourceforge.net/doc/cookbook/cookbook.htm
 * 
 * The Eclipse Guide "JUnit Get Started":
 *    http://help.eclipse.org/mars/index.jsp?topic=%2Forg.eclipse.jdt.doc.user%2FgettingStarted%2Fqs-junit.htm   
 * 
 */

public class TestsA {
  
  @Before
  public void before() {
    // Executed before each test
  }

  @After
  public void after() {
    // Executed after each test
  }

  // Use this if you want a global timeout of 10 seconds.
  // @Rule
  // public Timeout globalTimeout = Timeout.seconds(10); // 10 seconds max per method tested

  // Use this if you need a timeout,
  //    @Test(timeout=1000)
  // but be careful when debugging, the timeout will trigger
  // while debugging...
  @Test
  public void test00() throws NotFoundException {
    Tree tree = new Tree();
    assertTrue("".equals(tree.name()));
    assertTrue("" == tree.name());
    assertTrue(tree.find(Tree.pathSeparatorString) == tree);
  }

  @Test(expected = IllegalArgumentException.class)
  public void test01() throws NotFoundException {
    Tree tree = new Tree();
    assertTrue("".equals(tree.name()));
    assertTrue(""==tree.name());
    tree.find("toto");
  }

  @Test(expected=NotFoundException.class)
  public void test02() throws NotFoundException {
    Tree tree = new Tree();
    assertTrue("".equals(tree.name()));
    assertTrue(""==tree.name());
    String path = "/toto";
    path = path.replace('/', Tree.pathSeparator);
    tree.find(path);
  }

  @Test
  public void test03() throws NotFoundException {
    Tree tree = new Tree();
    Object o1 = new String("o1");
    Node n1 = new Node(tree,"toto");
    n1.attach(o1);
    assertTrue(n1.parent() == tree);
    assertTrue(n1.name() == "toto");
    assertTrue(n1.attachment() == o1);
    assertTrue(tree.child("toto") == n1);
    String path = "/toto";
    path = path.replace('/', Tree.pathSeparator);
    assertTrue(tree.find(path) == n1);
  }

  @Test
  public void test04() throws NotFoundException {
    Tree tree = new Tree();
    Object o1 = new String("o1");
    Node n1 = new Node(tree,"toto");
    n1.attach(o1);
    Object o2 = new String("o2");
    Node n2 = new Node(n1,"titi");
    n2.attach(o2);
    assertTrue(n1.parent() == tree);
    assertTrue(n1.name() == "toto");
    assertTrue(n1.attachment() == o1);
    assertTrue(n2.parent() == n1);
    assertTrue(n2.name() == "titi");
    assertTrue(n2.attachment() == o2);
    assertTrue(tree.child("toto") == n1);
    assertTrue(n1.child("titi") == n2);

    String path;
    path = "/toto".replace('/', Tree.pathSeparator);
    assertTrue(tree.find(path) == n1);
    path = "/toto/titi".replace('/', Tree.pathSeparator);
    assertTrue(tree.find(path) == n2);

  }

  @Test
  public void test05() throws NotFoundException {
    Tree tree = new Tree();
    String path;
    path = "/toto/titi".replace('/', Tree.pathSeparator);
    Node n1 = tree.makePath(path,false);
    path = "/toto/".replace('/', Tree.pathSeparator);
    Node n0 = tree.find(path);
    assertTrue(n0.parent()==tree);
    path = "/toto/titi/".replace('/', Tree.pathSeparator);
    assertTrue(n1==tree.find(path));

    assertTrue(n1.parent()==n0);
    assertTrue(n1 instanceof Node);
    assertTrue(!(n1 instanceof Leaf));
    
    path = "/toto/titi/tata".replace('/', Tree.pathSeparator);
    Node n2 = tree.makePath(path,true);
    assertTrue(n2.parent()==n1);
    assertTrue(n2 instanceof Leaf);
    assertTrue(n2==n1.child("tata"));
    path = "/toto/titi/tata".replace('/', Tree.pathSeparator);
    assertTrue(n2==tree.find(path));    
  }
  
  @Test(expected=IllegalStateException.class)
  public void test06() throws NotFoundException {
    Tree tree = new Tree();
    String path;
    path = "/toto/titi".replace('/', Tree.pathSeparator);
    tree.makePath(path,false);
    // next line will throw an illegal state exception
    tree.makePath(path,true);    
  }

  @Test(expected=IllegalArgumentException.class)
  public void test07() throws NotFoundException {
    Tree tree = new Tree();
    // the name "/toto" is an illegal name,
    // it contains the path separator
    // expected to throw IllegalStateException
    new Node(tree,Tree.pathSeparatorString+"toto"); 
  }

  @Test(expected=IllegalStateException.class)
  public void test08() throws NotFoundException {
    Tree tree = new Tree();
    Node n1 = new Node(tree,"toto");
    assertTrue(n1.parent()==tree);
    Node n2 = new Node(n1,"titi");
    assertTrue(n2.parent()==n1);
    // path exists, but as a node
    // expected to throw IllegalStateException
    String path;
    path = "/toto/titi".replace('/', Tree.pathSeparator);
    tree.makePath(path,true);  
  }

  @Test(expected=IllegalStateException.class)
  public void test09() throws NotFoundException {
    Tree tree = new Tree();
    Node n1 = new Node(tree,"toto");
    assertTrue(n1.parent()==tree);
    Node n2 = new Node(n1,"titi");
    assertTrue(n2.parent()==n1);
    Node n3= new Node(n2,"tata");
    // path already exists, leading to a node
    // expected to throw IllegalStateException
    new Node(n2,"tata");
  }

  @Test(expected=IllegalStateException.class)
  public void test10() throws NotFoundException {
    Tree tree = new Tree();
    Node n1 = new Node(tree,"toto");
    assertTrue(n1.parent()==tree);
    Node n2 = new Node(n1,"titi");
    assertTrue(n2.parent()==n1);
    Node n3= new Leaf(n2,"tata");
    // path already exists, leading to a leaf
    // expected to throw IllegalStateException
    new Node(n2,"tata");
  }

  @Test(expected=IllegalStateException.class)
  public void test11() throws NotFoundException {
    Tree tree = new Tree();
    Node n1 = new Node(tree,"toto");
    assertTrue(n1.parent()==tree);
    Node n2 = new Node(n1,"titi");
    assertTrue(n2.parent()==n1);
    Node n3= new Leaf(n2,"tata");
    // cannot add to a leaf node,
    // expected to throw IllegalStateException
    new Node(n3,"notAllowed");
  }

  @Test(expected=IllegalStateException.class)
  public void test12() throws NotFoundException {
    Tree tree = new Tree();
    Node n1 = new Node(tree,"toto");
    assertTrue(n1.parent()==tree);
    Node n2 = new Node(n1,"titi");
    assertTrue(n2.parent()==n1);
    Node n3= new Leaf(n2,"tata");
    // cannot add to a leaf node
    // expected to throw IllegalStateException
    new Leaf(n3,"notAllowed");
  }

}
