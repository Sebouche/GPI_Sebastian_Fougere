package warmup;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/*
 * This is a test suite, to launch all the tests for the code to develop on Monday.
 * 
 * Under Eclipse, you can right-click on this class and run/debug it as a Junit application.
 * 
 * Have a look at these online resources, the JUnit Cook Book:
 *    http://junit.sourceforge.net/doc/cookbook/cookbook.htm
 * and the JUnit Get Started Guide:
 *    http://help.eclipse.org/mars/index.jsp?topic=%2Forg.eclipse.jdt.doc.user%2FgettingStarted%2Fqs-junit.htm   
 * 
 */
@RunWith(Suite.class)
@SuiteClasses({ warmup.tree.tests.TestsA.class, warmup.layout.tests.TestsA.class, warmup.layout.tests.TestsB.class, })
public class AllTests {

}