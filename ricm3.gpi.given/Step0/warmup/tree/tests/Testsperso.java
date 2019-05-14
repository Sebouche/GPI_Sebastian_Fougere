package warmup.tree.tests;

import static org.junit.Assert.assertTrue;

import warmup.tree.Leaf;
import warmup.tree.Node;
import warmup.tree.NotFoundException;
import warmup.tree.Tree;






public class Testsperso {

	public static void main(String[] args) {
		try {
				test00();
		}
		catch(Exception e) {
			System.out.printf("Test 00 failed");
			return;
		}
		try {
			test01();
	}
	catch(Exception e) {
		System.out.printf("Test 01 failed");
		return;
	}
		try {
			test02();
	}
	catch(Exception e) {
		System.out.printf("Test 02 failed");
		return;
	}
		System.out.printf("Tests passed");
	}
	
	public static void test00() throws NotFoundException {
		Tree tree = new Tree();
		assertTrue("".equals(tree.name()));
		Node node1 = new Node(tree, "test");
		assertTrue(("/test").equals(node1.path()));
		assertTrue(tree == node1.parent());
		assertTrue(tree.child("test") == node1);
	}

	public static void test01() throws NotFoundException {
		Tree tree = new Tree();
		Leaf leaf1 = new Leaf(tree, "test");
		try {
			leaf1.child("test");
			assertTrue(1 == 0);
		} catch (IllegalStateException e) {
		}
	}

	public static void test02() throws NotFoundException {
		Tree tree = new Tree();
		tree.makePath("/test1/", true);
		assertTrue(tree.child("test1") != null);
		assertTrue(tree.child("test1") instanceof Leaf);
		tree.makePath("/test2/", false);
		assertTrue(tree.child("test2") != null);
		assertTrue(!(tree.child("test2") instanceof Leaf));
		try {
			tree.find("/test1/");
		} catch (NotFoundException e) {
			assertTrue(0 == 1);
		}
		try {
			tree.find("/test3");
			assertTrue(1 == 0);
		} catch (NotFoundException e) {
		}
	}
}