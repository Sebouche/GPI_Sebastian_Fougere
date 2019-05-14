package warmup.tree;

import java.util.Iterator;

/**
 * This is a tree of named node.
 * 
 * @author Pr. Olivier Gruber (olivier dot gruber at acm dot org)
 */
public class Tree extends Node {

	public static final char pathSeparator = '/';
	public static final String pathSeparatorString = "/";

	public Tree() {
		super("");
	}

	/**
	 * Finds a node corresponding to a path in the tree. If the path does not
	 * exists, throws NotFoundException
	 * 
	 * @param path of the searched node
	 * @return the node named by the given path
	 * @throws NotFoundException if the path does not exist
	 */
	public Node find(String path) throws NotFoundException {
		if (!path.contains(pathSeparatorString)) {
			throw new IllegalArgumentException();
		}
		String[] split = path.split(pathSeparatorString);
		Node tmp = this;
		boolean found;
		if (this.path().equals(path)) {
			return this;
		} else {
			for (int i = 1; i < split.length; i++) {
				found = false;
				Iterator<Node> tree_iter = tmp.children();
				while (tree_iter.hasNext()) {
					Node child = tree_iter.next();
					if (child.m_name.equals(split[i])) {
						tmp = child;
						found = true;
						break;
					}
				}
				if (found == false) {
					throw new NotFoundException(path);
				}
			}
		}
		return tmp;
	}

	/**
	 * Make a path in the tree, leading either to a leaf or to a node.
	 * 
	 * @throws IllegalStateException if the path should be to a leaf but it already
	 *                               exists to a node, or if the path should be to a
	 *                               node but it already exists to a leaf.
	 */
	public Node makePath(String path, boolean isLeaf) {
		try {
			Node found = this.find(path);
			if ((found instanceof Leaf && isLeaf == false) || (found instanceof Node && isLeaf == true)) {
				throw new IllegalStateException();
			} else {
				return found;
			}
		} catch (NotFoundException e) {
		}
		String[] split = path.split(pathSeparatorString);
		Node tmp = this;
		boolean found;
		for (int i = 1; i < split.length; i++) {
			found = false;
			Iterator<Node> tree_iter = tmp.children();
			while (tree_iter.hasNext()) {
				Node child = tree_iter.next();
				if (child.m_name.equals(split[i])) {
					tmp = child;
					found = true;
					break;
				}
			}
			if (found == false) {
				if (i != split.length-1) {
					tmp = new Node(tmp, split[i]);
				} else {
					if (isLeaf) {
						tmp = new Leaf(tmp, split[i]);
					} else {
						tmp = new Node(tmp, split[i]);
					}
				}
			}
		}
		return tmp;
	}

	public String toString() {
		TreePrinter p = new TreePrinter(this);
		return p.toString();
	}

}
