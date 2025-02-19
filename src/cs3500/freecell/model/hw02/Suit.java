package cs3500.freecell.model.hw02;


/**
 * The suit of a card. It can be 1 of 4 choices; either, CLUBS, DIAMONDS, HEARTS, or SPADES. CLUBS
 * and SPADES are black, and DIAMONDS and HEARTS are red. This cannot be changed.
 */
public enum Suit {
  CLUBS, DIAMONDS, HEARTS, SPADES;

  /**
   * Returns the color associated with this suit.
   *
   * @return the specific color associated with this suit
   */
  public String getColor() {
    if (this == CLUBS || this == SPADES) {
      return "black";
    } else {
      return "red";
    }
  }

  /**
   * Returns the suit of the card as a string.
   *
   * @return the string representation of the suit of the card
   */
  @Override
  public String toString() throws IllegalArgumentException {
    switch (this) {
      case CLUBS:
        return "♣";
      case DIAMONDS:
        return "♦";
      case HEARTS:
        return "♥";
      case SPADES:
        return "♠";
      default:
        throw new IllegalArgumentException("False Suit");
    }
  }
}

