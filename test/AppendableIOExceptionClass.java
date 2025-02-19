import java.io.IOException;

/**
 * This class is just for testing the IOExceptions thrown by both renderMessage and renderBoard in
 * the FreecellTextViewTest class. It uses append to throw an IOException error every time the
 * function append is called.
 */
public class AppendableIOExceptionClass implements Appendable {

  @Override
  public Appendable append(CharSequence csq) throws IOException {
    throw new IOException("Appendable Error");
  }

  @Override
  public Appendable append(CharSequence csq, int start, int end) throws IOException {
    throw new IOException("Appendable Error");
  }

  @Override
  public Appendable append(char c) throws IOException {
    throw new IOException("Appendable Error");
  }
}
