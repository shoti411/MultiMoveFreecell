package cs3500.freecell.model.hw02;

/**
 * A card's value. This can be an ACE, any of the numbers between TWO (2) and TEN (10), and the face
 * cards, JACK, QUEEN, AND KING.
 */
public enum Value {
  ACE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(
      11), QUEEN(12), KING(13);

  /**
   * The number associated with the value. Since ACEs are being considered "low," the number
   * associated with them is 1, as such numbers are numbers (e.g. TWO is associated with 2), and the
   * JACK, QUEEN, and KING Values are associated with 11, 12, and 13 respectively. This cannot be
   * changed.
   */
  private final int number;

  Value(int number) {
    this.number = number;
  }

  /**
   * Returns the number associated with the Value.
   *
   * @return the integer associated with the Value
   */
  public int getNumber() {
    return this.number;
  }

  /**
   * Returns the String representation of the Value. If the Value is a face card (such as an ACE,
   * JACk, QUEEN or KING), then it returns the first letter of the name of the value (e.g. "A" for
   * ACE).
   *
   * @return the String representation of the Value
   */
  @Override
  public String toString() {
    switch (this.number) {
      case 1:
        return "A";
      case 11:
        return "J";
      case 12:
        return "Q";
      case 13:
        return "K";
      // if not face card, then return the Value's associated number
      default:
        return String.valueOf(this.number);
    }
  }
}
