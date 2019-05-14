package warmup.tree;

import java.io.PrintStream;
import java.util.Iterator;

public class TreePrinter {

  Tree m_tree;

  TreePrinter(Tree tree) {
    m_tree = tree;
  }

  private String append(int tabs, String s, Node node) {
    s += "\n";
    for (int i = 0; i < tabs; i++)
      s += " ";
    Iterator<Node> iter = node.children();
    s += "[" + node.name() + ":" + node.attachment();
    if (iter.hasNext()) {
      while (iter.hasNext()) {
        Node child = iter.next();
        s = append(tabs + 2, s, child);
      }
    } else
      s += "\n";
    for (int i = 0; i < tabs; i++)
      s += " ";
    s += "]\n";
    return s;
  }

  public String toString() {
    String s;
    s = m_tree.name();
    if (s == null)
      s = "";
    else
      s += ":";
    Iterator<Node> iter = m_tree.children();
    while (iter.hasNext()) {
      Node child = iter.next();
      s = append(2, s, child);
    }
    return s;
  }

  private void print(PrintStream ps, int tabs, String s, Node node) {
    ps.print("\n");
    for (int i = 0; i < tabs; i++)
      ps.print(" ");
    Iterator<Node> iter = node.children();
    ps.print("[" + node.name() + ":" + node.attachment());
    if (iter.hasNext()) {
      while (iter.hasNext()) {
        Node child = iter.next();
        print(ps, tabs + 2, s, child);
      }
    } else
      ps.println();
    for (int i = 0; i < tabs; i++)
      ps.print(" ");
    ps.print("]\n");
  }

  public void print(PrintStream ps) {
    String s;
    s = m_tree.name();
    if (s != null)
      ps.print(s + ":");
    Iterator<Node> iter = m_tree.children();
    while (iter.hasNext()) {
      Node child = iter.next();
      print(ps, 2, s, child);
    }
  }

}
