package cs3500.freecell.view;

import cs3500.freecell.model.FreecellModelState;
import java.io.IOException;

/**
 * The view of the game Freecell. It is represented in code as text which is displayed.
 */
public class FreecellTextView implements FreecellView {

  private final FreecellModelState<?> type;
  private final Appendable dest;

  /**
   * Constructs a {@code FreecellTextView} object.
   *
   * @param type the specific model implementation. See {@code FreecellModel}.
   */
  public FreecellTextView(FreecellModelState<?> type) {
    this.type = type;
    this.dest = new StringBuilder();
  }

  /**
   * Constructs a {@code FreecellTextView} object.
   *
   * @param model       the specific model implementation. See {@code FreecellModel}.
   * @param destination the object to which character sequences and values can be appended.
   * @throws IllegalArgumentException if the destination is null.
   */
  public FreecellTextView(FreecellModelState<?> model, Appendable destination) {
    if (destination == null) {
      throw new IllegalArgumentException("Appendable cannot be null");
    }
    this.type = model;
    this.dest = destination;
  }

  @Override
  public String toString() {
    try {
      this.type.getNumCardsInFoundationPile(0);
    } catch (IllegalStateException e) {
      return "";
    }
    StringBuilder display = new StringBuilder();
    //String display = "";
    // all the Foundation Piles
    for (int i = 0; i < 4; i++) {
      display.append("F").append((i + 1)).append(':');
      for (int index = 0; index < this.type.getNumCardsInFoundationPile(i); index++) {
        display.append(" ").append(this.type.getFoundationCardAt(i, index));
        if (index == this.type.getNumCardsInFoundationPile(i) - 1) {
          display.append('\n');
        } else {
          display.append(',');
        }
      }
      if (this.type.getNumCardsInFoundationPile(i) == 0) {
        display.append('\n');
      }
    }

    // all the Open Piles
    for (int j = 0; j < this.type.getNumOpenPiles(); j++) {
      display.append("O").append((j + 1)).append(':');
      for (int jindex = 0; jindex < this.type.getNumCardsInOpenPile(j); jindex++) {
        display.append(' ').append(this.type.getOpenCardAt(j));
        if (jindex == this.type.getNumCardsInOpenPile(j) - 1) {
          display.append('\n');
        } else {
          display.append(',');
        }
      }
      if (this.type.getNumCardsInOpenPile(j) == 0) {
        display.append('\n');
      }
    }

    // all the Cascade Piles
    for (int k = 0; k < this.type.getNumCascadePiles(); k++) {
      display.append("C").append((k + 1)).append(':');
      for (int kindex = 0; kindex < this.type.getNumCardsInCascadePile(k); kindex++) {
        display.append(' ').append(this.type.getCascadeCardAt(k, kindex));
        if (kindex == this.type.getNumCardsInCascadePile(k) - 1) {
          if (k != this.type.getNumCascadePiles() - 1) {
            display.append('\n');
          }
        } else {
          display.append(',');
        }
      }
      if (this.type.getNumCardsInCascadePile(k) == 0) {
        display.append('\n');
      }
    }
    return display.toString();
  }

  @Override
  public void renderBoard() throws IOException {
    try {
      this.dest.append(this.toString());
    } catch (IOException e) {
      throw new IOException("Exception to Caller");
    }

  }

  /**
   * Render a specific message to the provided data destination.
   *
   * @param message the message to be transmitted
   * @throws IOException if transmission of the board to the provided data destination fails or if
   *                     the message is null.
   */
  @Override
  public void renderMessage(String message) throws IOException {
    // I chose to add on the exception that if the message which is passed to it is null,
    // then it throws an IOException. The professors said this exception would be left to student's
    // discretion I believe.
    if (message == null) {
      throw new IOException("Cannot Determine Message. Message cannot be null.");
    }
    try {
      this.dest.append(message);
    } catch (IOException appendableError) {
      throw new IOException("Writing to the Appendable Object Has Failed.");
    }
  }


}
