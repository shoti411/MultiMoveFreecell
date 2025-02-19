package cs3500.freecell.model.hw02;

import java.util.Objects;

/**
 * The representation of a playing card in a 52-card deck. It contains a Suit type of either CLUBS,
 * DIAMONDS, HEARTS, or SPADES, and a Value of ACE, numbers TWO through TEN, and the JACK, QUEEN,
 * and KING. ACEs are considered "low" (less than 2).
 */
public final class Card implements ICard {
  // The second rule in the Design Principles interface said that the exception for using interfaces
  // over classes was if all of their fields were final or if they were a final class with
  // no interface. I added an interface because 3 of the methods we needed, needed to be public and
  // it would not be good design to have a class contain public methods for which it does not
  // have an interface for.

  /**
   * A card's value. This can be an ACE, any of the numbers between TWO (2) and TEN (10), and the
   * face cards, JACK, QUEEN, AND KING. This cannot be changed.
   */
  private final Value value;

  /**
   * This card's suit, one of the constants CLUBS, DIAMONDS, HEARTS, or SPADES. The suit cannot be
   * changed after the card is constructed.
   */
  private final Suit suit;

  /**
   * Constructs a {@code Card} object.
   *
   * @param value the value of the card
   * @param suit  the suit of the card
   * @throws IllegalArgumentException if the card is invalid (the card is invalid if it has an null
   *                                  Value or Suit).
   */
  public Card(Value value, Suit suit) throws IllegalArgumentException {
    if (value == null || suit == null) {
      throw new IllegalArgumentException("Card Value or Suit cannot be null!!!");
    }
    this.value = value;
    this.suit = suit;
  }

  /**
   * Returns the String representation of this Card with the Value of the card first in the string
   * and the Suit of the card subsequently after.
   *
   * @return The String representation of this Card
   */
  @Override
  public String toString() {
    return new StringBuilder(this.value.toString()).append(this.suit.toString()).toString();
  }


  @Override
  public Value getValue() {
    return this.value;
  }


  @Override
  public Suit getSuit() {
    return this.suit;
  }

  /**
   * Determines if the 2 Cards have the same Value and Suit. If they do, then they are said to be
   * equal.
   *
   * @param that The object in question for which you are comparing the card to.
   * @return A boolean of whether or not the 2 cards are said to be equal.
   */
  @Override
  public boolean equals(Object that) {
    if (this == that) {
      return true;
    }
    if (!(that instanceof Card)) {
      return false;
    } else {
      return that.toString().equals(this.toString());
    }
  }

  /**
   * Generates a unique Hash Code that depends on both he value of the Card and its Suit.
   *
   * @return An integer representing a unique hash code that depends on the Card's Value and Suit.
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.value, this.suit);
  }
}
