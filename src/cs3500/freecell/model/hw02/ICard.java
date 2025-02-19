package cs3500.freecell.model.hw02;

/**
 * The Interface for a Card of a classic, single 52 Playing-Card Deck. Each card has a Value and a
 * Suit. The Suit types can only be CLUBS, DIAMONDS, HEARTS, and SPADES. The Values of the cards can
 * be an ACE, numbers TWO through TEN, and the JACK, the QUEEN, * and the KING. ACEs are considered
 * "low" (less than 2). See {@code Suit} and {@code Value} for more info. This interface contains
 * the methods that all kinds of Card classes would contain and allows for more implementations of
 * cards.
 */
public interface ICard {

  /**
   * Returns the String representation of this Card with the Value of the card first in the string
   * and the Suit of the card subsequently after.
   *
   * @return The String representation of this Card
   */
  String toString();

  /**
   * Returns the @Value of the Card.
   *
   * @return the @Value of the card
   */
  Value getValue();

  /**
   * Returns the Suit of the card (either CLUBS, DIAMONDS, HEARTS, or SPADES).
   *
   * @return the Suit of the card
   */
  Suit getSuit();
}


