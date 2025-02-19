import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import cs3500.freecell.model.hw02.Card;
import cs3500.freecell.model.hw02.ICard;
import cs3500.freecell.model.hw02.Suit;
import cs3500.freecell.model.hw02.Value;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Test;

/**
 * Test class for Card: Unit tests to ensure that Cards can be displayed correctly and otherwise
 * behave correctly.
 */
public class CardTest {


  ICard cAce = new Card(Value.ACE, Suit.CLUBS);
  ICard c2 = new Card(Value.TWO, Suit.CLUBS);
  ICard c3 = new Card(Value.THREE, Suit.CLUBS);
  ICard c4 = new Card(Value.FOUR, Suit.CLUBS);
  ICard c5 = new Card(Value.FIVE, Suit.CLUBS);
  ICard c6 = new Card(Value.SIX, Suit.CLUBS);
  ICard c7 = new Card(Value.SEVEN, Suit.CLUBS);
  ICard c8 = new Card(Value.EIGHT, Suit.CLUBS);
  ICard c9 = new Card(Value.NINE, Suit.CLUBS);
  ICard c10 = new Card(Value.TEN, Suit.CLUBS);
  ICard cJack = new Card(Value.JACK, Suit.CLUBS);
  ICard cQueen = new Card(Value.QUEEN, Suit.CLUBS);
  ICard cKing = new Card(Value.KING, Suit.CLUBS);

  ICard dAce = new Card(Value.ACE, Suit.DIAMONDS);
  ICard d2 = new Card(Value.TWO, Suit.DIAMONDS);
  ICard d3 = new Card(Value.THREE, Suit.DIAMONDS);
  ICard d4 = new Card(Value.FOUR, Suit.DIAMONDS);
  ICard d5 = new Card(Value.FIVE, Suit.DIAMONDS);
  ICard d6 = new Card(Value.SIX, Suit.DIAMONDS);
  ICard d7 = new Card(Value.SEVEN, Suit.DIAMONDS);
  ICard d8 = new Card(Value.EIGHT, Suit.DIAMONDS);
  ICard d9 = new Card(Value.NINE, Suit.DIAMONDS);
  ICard d10 = new Card(Value.TEN, Suit.DIAMONDS);
  ICard dJack = new Card(Value.JACK, Suit.DIAMONDS);
  ICard dQueen = new Card(Value.QUEEN, Suit.DIAMONDS);
  ICard dKing = new Card(Value.KING, Suit.DIAMONDS);

  ICard hAce = new Card(Value.ACE, Suit.HEARTS);
  ICard h2 = new Card(Value.TWO, Suit.HEARTS);
  ICard h3 = new Card(Value.THREE, Suit.HEARTS);
  ICard h4 = new Card(Value.FOUR, Suit.HEARTS);
  ICard h5 = new Card(Value.FIVE, Suit.HEARTS);
  ICard h6 = new Card(Value.SIX, Suit.HEARTS);
  ICard h7 = new Card(Value.SEVEN, Suit.HEARTS);
  ICard h8 = new Card(Value.EIGHT, Suit.HEARTS);
  ICard h9 = new Card(Value.NINE, Suit.HEARTS);
  ICard h10 = new Card(Value.TEN, Suit.HEARTS);
  ICard hJack = new Card(Value.JACK, Suit.HEARTS);
  ICard hQueen = new Card(Value.QUEEN, Suit.HEARTS);
  ICard hKing = new Card(Value.KING, Suit.HEARTS);

  ICard sAce = new Card(Value.ACE, Suit.SPADES);
  ICard s2 = new Card(Value.TWO, Suit.SPADES);
  ICard s3 = new Card(Value.THREE, Suit.SPADES);
  ICard s4 = new Card(Value.FOUR, Suit.SPADES);
  ICard s5 = new Card(Value.FIVE, Suit.SPADES);
  ICard s6 = new Card(Value.SIX, Suit.SPADES);
  ICard s7 = new Card(Value.SEVEN, Suit.SPADES);
  ICard s8 = new Card(Value.EIGHT, Suit.SPADES);
  ICard s9 = new Card(Value.NINE, Suit.SPADES);
  ICard s10 = new Card(Value.TEN, Suit.SPADES);
  ICard sJack = new Card(Value.JACK, Suit.SPADES);
  ICard sQueen = new Card(Value.QUEEN, Suit.SPADES);
  ICard sKing = new Card(Value.KING, Suit.SPADES);

  public ArrayList<ICard> deck = new ArrayList<ICard>(Arrays
      .asList(this.cAce, this.c2, this.c3, this.c4, this.c5, this.c6, this.c7, this.c8, this.c9,
          this.c10, this.cJack, this.cQueen, this.cKing, this.dAce, this.d2, this.d3, this.d4,
          this.d5, this.d6, this.d7, this.d8, this.d9, this.d10, this.dJack, this.dQueen,
          this.dKing, this.hAce, this.h2, this.h3, this.h4, this.h5, this.h6, this.h7, this.h8,
          this.h9, this.h10, this.hJack, this.hQueen, this.hKing, this.sAce, this.s2, this.s3,
          this.s4, this.s5, this.s6, this.s7, this.s8, this.s9, this.s10, this.sJack, this.sQueen,
          this.sKing));

  @Test(expected = IllegalArgumentException.class)
  public void testCardConstructorExceptionNullValue() {
    new Card(null, Suit.DIAMONDS);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCardConstructorExceptionNullSuit() {
    new Card(Value.EIGHT, null);
  }

  @Test
  public void testToString() {
    // test to make sure printing of Suit works
    assertEquals("A♣", this.cAce.toString());
    assertEquals("A♦", this.dAce.toString());
    assertEquals("A♥", this.hAce.toString());
    assertEquals("A♠", this.sAce.toString());

    // test to make sure printing of Value also works
    assertEquals("2♣", this.c2.toString());
    assertEquals("J♦", this.dJack.toString());
    assertEquals("Q♥", this.hQueen.toString());
    assertEquals("K♠", this.sKing.toString());

    // just make sure everything works when printing
    for (ICard c : this.deck) {
      assertEquals(c.getValue().toString() + c.getSuit().toString(), c.toString());
    }
  }

  @Test
  public void testGetValue() {
    assertEquals(Value.ACE, this.cAce.getValue());
    assertEquals(Value.TWO, this.s2.getValue());
    assertEquals(Value.THREE, this.d3.getValue());
    assertEquals(Value.FOUR, this.h4.getValue());
    assertEquals(Value.FIVE, this.c5.getValue());
    assertEquals(Value.SIX, this.s6.getValue());
    assertEquals(Value.SEVEN, this.d7.getValue());
    assertEquals(Value.EIGHT, this.h8.getValue());
    assertEquals(Value.NINE, this.c9.getValue());
    assertEquals(Value.TEN, this.s10.getValue());
    assertEquals(Value.JACK, this.dJack.getValue());
    assertEquals(Value.QUEEN, this.hQueen.getValue());
    assertEquals(Value.KING, this.cKing.getValue());
  }

  @Test
  public void testGetSuit() {
    for (int i = 0; i < 13; i++) {
      assertEquals(Suit.CLUBS, this.deck.get(i).getSuit());
    }
    for (int j = 13; j < 26; j++) {
      assertEquals(Suit.DIAMONDS, this.deck.get(j).getSuit());
    }

    for (int k = 26; k < 39; k++) {
      assertEquals(Suit.HEARTS, this.deck.get(k).getSuit());
    }

    for (int l = 39; l < 52; l++) {
      assertEquals(Suit.SPADES, this.deck.get(l).getSuit());
    }
  }

  @Test
  public void testEquals() {

    ICard aceAlias = this.cAce;

    assertTrue(this.cAce.equals(aceAlias));

    assertTrue(this.cAce.equals(new Card(Value.ACE, Suit.CLUBS)));
    assertTrue(this.c2.equals(new Card(Value.TWO, Suit.CLUBS)));
    assertTrue(this.d3.equals(new Card(Value.THREE, Suit.DIAMONDS)));
    assertTrue(this.d4.equals(new Card(Value.FOUR, Suit.DIAMONDS)));
    assertTrue(this.h5.equals(new Card(Value.FIVE, Suit.HEARTS)));
    assertTrue(this.h6.equals(new Card(Value.SIX, Suit.HEARTS)));
    assertTrue(this.s7.equals(new Card(Value.SEVEN, Suit.SPADES)));
    assertTrue(this.s8.equals(new Card(Value.EIGHT, Suit.SPADES)));

    assertFalse(this.cAce.equals(this.c2));
    assertFalse(this.dAce.equals(this.sAce));
  }

  @Test
  public void testHashCode() {

    ICard h9Alias = this.h9;
    assertEquals(this.h9.hashCode(), h9Alias.hashCode());

    assertEquals(this.cQueen.hashCode(), new Card(Value.QUEEN, Suit.CLUBS).hashCode());
    assertEquals(this.dAce.hashCode(), new Card(Value.ACE, Suit.DIAMONDS).hashCode());
    assertEquals(this.hJack.hashCode(), new Card(Value.JACK, Suit.HEARTS).hashCode());
    assertEquals(this.sKing.hashCode(), new Card(Value.KING, Suit.SPADES).hashCode());

    assertNotEquals(this.sKing.hashCode(), this.sQueen.hashCode());
    assertNotEquals(this.hQueen.hashCode(), this.dQueen.hashCode());
  }
}