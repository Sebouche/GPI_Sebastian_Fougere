package warmup.tree;

public class NotFoundException extends Exception {

  public NotFoundException(String path) {
    super("Missing "+path);
  }
}
