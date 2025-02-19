import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import cs3500.freecell.model.FreecellModel;
import cs3500.freecell.model.hw02.Card;
import cs3500.freecell.model.hw02.GameState;
import cs3500.freecell.model.hw02.ICard;
import cs3500.freecell.model.hw02.SimpleFreecellModel;
import cs3500.freecell.model.hw02.Suit;
import cs3500.freecell.model.hw02.Value;
import cs3500.freecell.view.FreecellTextView;
import cs3500.freecell.view.FreecellView;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for FreecellTextView: Unit tests to ensure that FreecellTextView can be correctly
 * display the game of freecell in text correctly and otherwise behave correctly.
 */
public class FreecellTextViewTest {

  private final ICard cAce = new Card(Value.ACE, Suit.CLUBS);
  private final ICard c2 = new Card(Value.TWO, Suit.CLUBS);
  private final ICard c3 = new Card(Value.THREE, Suit.CLUBS);
  private final ICard c4 = new Card(Value.FOUR, Suit.CLUBS);
  private final ICard c5 = new Card(Value.FIVE, Suit.CLUBS);
  private final ICard c6 = new Card(Value.SIX, Suit.CLUBS);
  private final ICard c7 = new Card(Value.SEVEN, Suit.CLUBS);
  private final ICard c8 = new Card(Value.EIGHT, Suit.CLUBS);
  private final ICard c9 = new Card(Value.NINE, Suit.CLUBS);
  private final ICard c10 = new Card(Value.TEN, Suit.CLUBS);
  private final ICard cJack = new Card(Value.JACK, Suit.CLUBS);
  private final ICard cQueen = new Card(Value.QUEEN, Suit.CLUBS);
  private final ICard cKing = new Card(Value.KING, Suit.CLUBS);

  private final ICard dAce = new Card(Value.ACE, Suit.DIAMONDS);
  private final ICard d2 = new Card(Value.TWO, Suit.DIAMONDS);
  private final ICard d3 = new Card(Value.THREE, Suit.DIAMONDS);
  private final ICard d4 = new Card(Value.FOUR, Suit.DIAMONDS);
  private final ICard d5 = new Card(Value.FIVE, Suit.DIAMONDS);
  private final ICard d6 = new Card(Value.SIX, Suit.DIAMONDS);
  private final ICard d7 = new Card(Value.SEVEN, Suit.DIAMONDS);
  private final ICard d8 = new Card(Value.EIGHT, Suit.DIAMONDS);
  private final ICard d9 = new Card(Value.NINE, Suit.DIAMONDS);
  private final ICard d10 = new Card(Value.TEN, Suit.DIAMONDS);
  private final ICard dJack = new Card(Value.JACK, Suit.DIAMONDS);
  private final ICard dQueen = new Card(Value.QUEEN, Suit.DIAMONDS);
  private final ICard dKing = new Card(Value.KING, Suit.DIAMONDS);

  private final ICard hAce = new Card(Value.ACE, Suit.HEARTS);
  private final ICard h2 = new Card(Value.TWO, Suit.HEARTS);
  private final ICard h3 = new Card(Value.THREE, Suit.HEARTS);
  private final ICard h4 = new Card(Value.FOUR, Suit.HEARTS);
  private final ICard h5 = new Card(Value.FIVE, Suit.HEARTS);
  private final ICard h6 = new Card(Value.SIX, Suit.HEARTS);
  private final ICard h7 = new Card(Value.SEVEN, Suit.HEARTS);
  private final ICard h8 = new Card(Value.EIGHT, Suit.HEARTS);
  private final ICard h9 = new Card(Value.NINE, Suit.HEARTS);
  private final ICard h10 = new Card(Value.TEN, Suit.HEARTS);
  private final ICard hJack = new Card(Value.JACK, Suit.HEARTS);
  private final ICard hQueen = new Card(Value.QUEEN, Suit.HEARTS);
  private final ICard hKing = new Card(Value.KING, Suit.HEARTS);

  private final ICard sAce = new Card(Value.ACE, Suit.SPADES);
  private final ICard s2 = new Card(Value.TWO, Suit.SPADES);
  private final ICard s3 = new Card(Value.THREE, Suit.SPADES);
  private final ICard s4 = new Card(Value.FOUR, Suit.SPADES);
  private final ICard s5 = new Card(Value.FIVE, Suit.SPADES);
  private final ICard s6 = new Card(Value.SIX, Suit.SPADES);
  private final ICard s7 = new Card(Value.SEVEN, Suit.SPADES);
  private final ICard s8 = new Card(Value.EIGHT, Suit.SPADES);
  private final ICard s9 = new Card(Value.NINE, Suit.SPADES);
  private final ICard s10 = new Card(Value.TEN, Suit.SPADES);
  private final ICard sJack = new Card(Value.JACK, Suit.SPADES);
  private final ICard sQueen = new Card(Value.QUEEN, Suit.SPADES);
  private final ICard sKing = new Card(Value.KING, Suit.SPADES);

  private final ArrayList<ICard> cAllCards = new ArrayList<ICard>(Arrays
      .asList(this.cAce, this.c2, this.c3, this.c4, this.c5, this.c6, this.c7, this.c8, this.c9,
          this.c10, this.cJack, this.cQueen, this.cKing));

  private final ArrayList<ICard> cOnlyAceList = new ArrayList<ICard>(Arrays.asList(this.cAce));

  private final ArrayList<ICard> dAllCards = new ArrayList<ICard>(Arrays
      .asList(this.dAce, this.d2, this.d3, this.d4,
          this.d5, this.d6, this.d7, this.d8, this.d9, this.d10, this.dJack, this.dQueen,
          this.dKing));

  private final ArrayList<ICard> hAllCards = new ArrayList<ICard>(Arrays
      .asList(this.hAce, this.h2, this.h3, this.h4, this.h5, this.h6, this.h7, this.h8,
          this.h9, this.h10, this.hJack, this.hQueen, this.hKing));

  private final ArrayList<ICard> sAllCards = new ArrayList<ICard>(Arrays
      .asList(this.sAce, this.s2, this.s3, this.s4, this.s5, this.s6, this.s7, this.s8, this.s9,
          this.s10, this.sJack, this.sQueen,
          this.sKing));

  private final List<ICard> deckCopy = new ArrayList<ICard>(Arrays
      .asList(this.cAce, this.c2, this.c3, this.c4, this.c5, this.c6, this.c7, this.c8, this.c9,
          this.c10, this.cJack, this.cQueen, this.cKing, this.dAce, this.d2, this.d3, this.d4,
          this.d5, this.d6, this.d7, this.d8, this.d9, this.d10, this.dJack, this.dQueen,
          this.dKing, this.hAce, this.h2, this.h3, this.h4, this.h5, this.h6, this.h7, this.h8,
          this.h9, this.h10, this.hJack, this.hQueen, this.hKing, this.sAce, this.s2, this.s3,
          this.s4, this.s5, this.s6, this.s7, this.s8, this.s9, this.s10, this.sJack, this.sQueen,
          this.sKing));

  // These next examples are just the pile formation for the sample game version view given to
  // us in the instructions of the assignment
  private final ArrayList<ICard> f1PileSampleGameGiven = new ArrayList<ICard>(
      Arrays.asList(this.sAce));
  private final ArrayList<ICard> f2PileSampleGameGiven = new ArrayList<ICard>(
      Arrays.asList(this.cAce, this.c2));
  private final ArrayList<ICard> f3PileSampleGameGiven = new ArrayList<ICard>(
      Arrays.asList(this.hAce, this.h2));
  private final ArrayList<ICard> f4PileSampleGameGiven = new ArrayList<ICard>(
      Arrays.asList());

  private final ArrayList<ICard> c1PileSampleGameGiven = new ArrayList<ICard>(
      Arrays.asList(this.sKing, this.d3, this.hKing, this.s8, this.h5, this.d5, this.s5));
  private final ArrayList<ICard> c2PileSampleGameGiven = new ArrayList<ICard>(
      Arrays.asList(this.c4, this.c5, this.s4, this.d9, this.cKing));
  private final ArrayList<ICard> c3PileSampleGameGiven = new ArrayList<ICard>(
      Arrays.asList(this.s2, this.s9, this.c8, this.d10, this.h8, this.sQueen, this.dJack));
  private final ArrayList<ICard> c4PileSampleGameGiven = new ArrayList<ICard>(
      Arrays.asList(this.h10, this.sJack, this.dQueen, this.c6, this.c3, this.cJack));
  private final ArrayList<ICard> c5PileSampleGameGiven = new ArrayList<ICard>(
      Arrays.asList(this.d6, this.h3, this.s10, this.hQueen, this.s6));
  private final ArrayList<ICard> c6PileSampleGameGiven = new ArrayList<ICard>(
      Arrays.asList(this.c9, this.c7, this.h7, this.dKing, this.h4, this.s3, this.d2));
  private final ArrayList<ICard> c7PileSampleGameGiven = new ArrayList<ICard>(
      Arrays.asList(this.cQueen, this.hJack, this.c10, this.h9));
  private final ArrayList<ICard> c8PileSampleGameGiven = new ArrayList<ICard>(
      Arrays.asList(this.d7, this.s7, this.h6, this.dAce, this.d4));

  private final ArrayList<ICard> o1PileSampleGameGiven = new ArrayList<ICard>();
  private final ArrayList<ICard> o2PileSampleGameGiven = new ArrayList<ICard>();
  private final ArrayList<ICard> o3PileSampleGameGiven = new ArrayList<ICard>();
  private final ArrayList<ICard> o4PileSampleGameGiven = new ArrayList<ICard>(
      Arrays.asList(this.d8));

  SimpleFreecellModel exampleGameGivenAlreadyStarted = new SimpleFreecellModel(GameState.PLAYING,
      new ArrayList<ArrayList<ICard>>(Arrays
          .asList(this.f1PileSampleGameGiven, this.f2PileSampleGameGiven,
              this.f3PileSampleGameGiven, this.f4PileSampleGameGiven)),
      new ArrayList<ArrayList<ICard>>(Arrays
          .asList(this.c1PileSampleGameGiven, this.c2PileSampleGameGiven,
              this.c3PileSampleGameGiven, this.c4PileSampleGameGiven,
              this.c5PileSampleGameGiven, this.c6PileSampleGameGiven,
              this.c7PileSampleGameGiven, this.c8PileSampleGameGiven)),
      new ArrayList<ArrayList<ICard>>(Arrays
          .asList(this.o1PileSampleGameGiven, this.o2PileSampleGameGiven,
              this.o3PileSampleGameGiven, this.o4PileSampleGameGiven)));

  SimpleFreecellModel exampleGameGivenAlmostFinished = new SimpleFreecellModel(GameState.PLAYING,
      new ArrayList<ArrayList<ICard>>(Arrays
          .asList(this.cAllCards, this.dAllCards, this.hAllCards, this.sAllCards)),
      new ArrayList<ArrayList<ICard>>(Arrays
          .asList(this.cOnlyAceList, new ArrayList<ICard>(), new ArrayList<ICard>(),
              new ArrayList<ICard>())),
      new ArrayList<ArrayList<ICard>>(Arrays.asList(this.o1PileSampleGameGiven)));


  SimpleFreecellModel mainModel = new SimpleFreecellModel();

  FreecellTextView display = new FreecellTextView(this.mainModel);

  FreecellTextView displaySampleFreecellGame = new FreecellTextView(
      this.exampleGameGivenAlreadyStarted);

  // the game that would never happen because has more than 52 cards in total
  FreecellTextView displaySampleAlmostOverGame = new FreecellTextView(
      this.exampleGameGivenAlmostFinished);


  @Before
  public void setup() {

    this.mainModel = new SimpleFreecellModel();
    this.display = new FreecellTextView(this.mainModel);
    this.mainModel.startGame(this.deckCopy, 8, 4, false);

    this.displaySampleAlmostOverGame = new FreecellTextView((this.exampleGameGivenAlmostFinished));
  }

  @Test
  public void testFreecellTextViewToString() {
    // make sure a regular deck works as displayed
    assertEquals(new StringBuilder()
        .append("F1:\n")
        .append("F2:\n")
        .append("F3:\n")
        .append("F4:\n")
        .append("O1:\n")
        .append("O2:\n")
        .append("O3:\n")
        .append("O4:\n")
        .append("C1: A♣, 9♣, 4♦, Q♦, 7♥, 2♠, 10♠\n")
        .append("C2: 2♣, 10♣, 5♦, K♦, 8♥, 3♠, J♠\n")
        .append("C3: 3♣, J♣, 6♦, A♥, 9♥, 4♠, Q♠\n")
        .append("C4: 4♣, Q♣, 7♦, 2♥, 10♥, 5♠, K♠\n")
        .append("C5: 5♣, K♣, 8♦, 3♥, J♥, 6♠\n")
        .append("C6: 6♣, A♦, 9♦, 4♥, Q♥, 7♠\n")
        .append("C7: 7♣, 2♦, 10♦, 5♥, K♥, 8♠\n")
        .append("C8: 8♣, 3♦, J♦, 6♥, A♠, 9♠").toString(), this.display.toString());
    // make sure a deck in different order works as displayed (also even when there are other
    // cards in the foundation piles
    assertEquals(new StringBuilder()
            .append("F1: A♠\n")
            .append("F2: A♣, 2♣\n")
            .append("F3: A♥, 2♥\n")
            .append("F4:\n")
            .append("O1:\n")
            .append("O2:\n")
            .append("O3:\n")
            .append("O4: 8♦\n")
            .append("C1: K♠, 3♦, K♥, 8♠, 5♥, 5♦, 5♠\n")
            .append("C2: 4♣, 5♣, 4♠, 9♦, K♣\n")
            .append("C3: 2♠, 9♠, 8♣, 10♦, 8♥, Q♠, J♦\n")
            .append("C4: 10♥, J♠, Q♦, 6♣, 3♣, J♣\n")
            .append("C5: 6♦, 3♥, 10♠, Q♥, 6♠\n")
            .append("C6: 9♣, 7♣, 7♥, K♦, 4♥, 3♠, 2♦\n")
            .append("C7: Q♣, J♥, 10♣, 9♥\n")
            .append("C8: 7♦, 7♠, 6♥, A♦, 4♦").toString(),
        this.displaySampleFreecellGame.toString());
  }

  @Test
  public void testToStringMakeSureWorksWithShuffledDeck() {
    // making sure does not just show the same format even though shuffled deck dealt out
    this.mainModel.startGame(this.deckCopy, 8, 4, true);
    assertNotEquals(new StringBuilder()
        .append("F1:\n")
        .append("F2:\n")
        .append("F3:\n")
        .append("F4:\n")
        .append("O1:\n")
        .append("O2:\n")
        .append("O3:\n")
        .append("O4:\n")
        .append("C1: A♣, 9♣, 4♦, Q♦, 7♥, 2♠, 10♠\n")
        .append("C2: 2♣, 10♣, 5♦, K♦, 8♥, 3♠, J♠\n")
        .append("C3: 3♣, J♣, 6♦, A♥, 9♥, 4♠, Q♠\n")
        .append("C4: 4♣, Q♣, 7♦, 2♥, 10♥, 5♠, K♠\n")
        .append("C5: 5♣, K♣, 8♦, 3♥, J♥, 6♠\n")
        .append("C6: 6♣, A♦, 9♦, 4♥, Q♥, 7♠\n")
        .append("C7: 7♣, 2♦, 10♦, 5♥, K♥, 8♠\n")
        .append("C8: 8♣, 3♦, J♦, 6♥, A♠, 9♠").toString(), this.display.toString());
  }

  @Test
  public void testToStringMakeSureWorksWithEvenWithFaultyModel() {
    // this model would never happen in a game because there are functions to prevent it from
    // happening, so this test is just to prove that the View function works separately
    // from the Model's functions.
    assertEquals(new StringBuilder()
        .append("F1: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n")
        .append("F2: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n")
        .append("F3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n")
        .append("F4: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n")
        .append("O1:\n")
        .append("C1: A♣\n")
        .append("C2:\n")
        .append("C3:\n")
        .append("C4:\n").toString(), this.displaySampleAlmostOverGame.toString());
  }

  @Test
  public void testToStringReturnsEmptyStringWhenCallToStartGameBelow4CascadeThrowsException() {
    FreecellModel<ICard> model = new SimpleFreecellModel();
    FreecellView view = new FreecellTextView(model);
    try {
      // should throw exception, so should not go through with model starting the game.
      model.startGame(this.deckCopy, 3, 4, true);
    } catch (IllegalArgumentException invalidInputs) {
      assertEquals("", view.toString());
    }
  }

  @Test
  public void testToStringReturnsEmptyStringWhenCallToStartGameBelow1OpenThrowsException() {
    FreecellModel<ICard> model = new SimpleFreecellModel();
    FreecellView view = new FreecellTextView(model);
    try {
      // should throw exception, so should not go through with model starting the game.
      model.startGame(this.deckCopy, 8, 1, true);
    } catch (IllegalArgumentException invalidInputs) {
      assertEquals("", view.toString());
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorViewNullAppendable() {
    FreecellModel<ICard> model = this.exampleGameGivenAlreadyStarted;
    new FreecellTextView(model, null);
  }

  @Test(expected = IOException.class)
  public void testRenderBoardThrowsIOExceptionCouldNotAppend() throws IOException {
    FreecellModel<ICard> model = this.exampleGameGivenAlreadyStarted;
    Appendable out = new AppendableIOExceptionClass();
    FreecellTextView faultyView = new FreecellTextView(model, out);
    faultyView.renderBoard();
  }

  @Test(expected = IOException.class)
  public void testRenderMessageIOExceptionCouldNotAppend() throws IOException {
    FreecellModel<ICard> model = this.exampleGameGivenAlreadyStarted;
    Appendable out = new AppendableIOExceptionClass();
    FreecellTextView faultyView = new FreecellTextView(model, out);
    faultyView.renderMessage("big oof");
  }

  @Test(expected = IOException.class)
  public void testRenderMessageExceptionNullMessage() throws IOException {
    FreecellModel<ICard> model = this.exampleGameGivenAlreadyStarted;
    Appendable out = new AppendableIOExceptionClass();
    FreecellTextView faultyView = new FreecellTextView(model, out);
    faultyView.renderMessage(null);
  }

  @Test
  public void testRenderBoardSuccess() throws IOException {
    FreecellModel<ICard> model = this.exampleGameGivenAlreadyStarted;
    Appendable out = new StringBuilder();
    FreecellTextView view = new FreecellTextView(model, out);
    view.renderBoard();
    assertEquals(new StringBuilder()
        .append("F1: A♠\n")
        .append("F2: A♣, 2♣\n")
        .append("F3: A♥, 2♥\n")
        .append("F4:\n")
        .append("O1:\n")
        .append("O2:\n")
        .append("O3:\n")
        .append("O4: 8♦\n")
        .append("C1: K♠, 3♦, K♥, 8♠, 5♥, 5♦, 5♠\n")
        .append("C2: 4♣, 5♣, 4♠, 9♦, K♣\n")
        .append("C3: 2♠, 9♠, 8♣, 10♦, 8♥, Q♠, J♦\n")
        .append("C4: 10♥, J♠, Q♦, 6♣, 3♣, J♣\n")
        .append("C5: 6♦, 3♥, 10♠, Q♥, 6♠\n")
        .append("C6: 9♣, 7♣, 7♥, K♦, 4♥, 3♠, 2♦\n")
        .append("C7: Q♣, J♥, 10♣, 9♥\n")
        .append("C8: 7♦, 7♠, 6♥, A♦, 4♦").toString(), out.toString());

    // now appending a second board again to prove it works when appending on an existing
    // character sequence and to prove that it does not add a new line at the end of existing
    // boards
    view.renderBoard();
    assertEquals(new StringBuilder()
        .append("F1: A♠\n")
        .append("F2: A♣, 2♣\n")
        .append("F3: A♥, 2♥\n")
        .append("F4:\n")
        .append("O1:\n")
        .append("O2:\n")
        .append("O3:\n")
        .append("O4: 8♦\n")
        .append("C1: K♠, 3♦, K♥, 8♠, 5♥, 5♦, 5♠\n")
        .append("C2: 4♣, 5♣, 4♠, 9♦, K♣\n")
        .append("C3: 2♠, 9♠, 8♣, 10♦, 8♥, Q♠, J♦\n")
        .append("C4: 10♥, J♠, Q♦, 6♣, 3♣, J♣\n")
        .append("C5: 6♦, 3♥, 10♠, Q♥, 6♠\n")
        .append("C6: 9♣, 7♣, 7♥, K♦, 4♥, 3♠, 2♦\n")
        .append("C7: Q♣, J♥, 10♣, 9♥\n")
        .append("C8: 7♦, 7♠, 6♥, A♦, 4♦")
        .append("F1: A♠\n")
        .append("F2: A♣, 2♣\n")
        .append("F3: A♥, 2♥\n")
        .append("F4:\n")
        .append("O1:\n")
        .append("O2:\n")
        .append("O3:\n")
        .append("O4: 8♦\n")
        .append("C1: K♠, 3♦, K♥, 8♠, 5♥, 5♦, 5♠\n")
        .append("C2: 4♣, 5♣, 4♠, 9♦, K♣\n")
        .append("C3: 2♠, 9♠, 8♣, 10♦, 8♥, Q♠, J♦\n")
        .append("C4: 10♥, J♠, Q♦, 6♣, 3♣, J♣\n")
        .append("C5: 6♦, 3♥, 10♠, Q♥, 6♠\n")
        .append("C6: 9♣, 7♣, 7♥, K♦, 4♥, 3♠, 2♦\n")
        .append("C7: Q♣, J♥, 10♣, 9♥\n")
        .append("C8: 7♦, 7♠, 6♥, A♦, 4♦").toString(), out.toString());
  }

  @Test
  public void testRenderMessageSuccess() throws IOException {
    FreecellModel<ICard> model = this.exampleGameGivenAlreadyStarted;
    Appendable out = new StringBuilder();
    FreecellTextView view = new FreecellTextView(model, out);
    // adding stuff to ouput
    view.renderMessage("bro what");
    assertEquals("bro what", out.toString());
    // hello
    view.renderMessage("\nare you doing?");
    assertEquals("bro what\nare you doing?", out.toString());
  }

  @Test
  public void testRenderMessageAndRenderBoardInSuccession() throws IOException {
    FreecellModel<ICard> model = this.exampleGameGivenAlreadyStarted;
    Appendable out = new StringBuilder();
    FreecellTextView view = new FreecellTextView(model, out);
    view.renderMessage("First Board:\n");
    view.renderBoard();
    view.renderMessage("Second Board:\n");
    view.renderMessage("Just kidding its another message\n");
    view.renderMessage("that was kinda mean, so here's the second, third, and fourth board:\n");
    view.renderBoard();
    view.renderBoard();
    view.renderBoard();
    view.renderMessage("this will be a long assert...");
    assertEquals(new StringBuilder()
        .append("First Board:\n")
        .append("F1: A♠\n")
        .append("F2: A♣, 2♣\n")
        .append("F3: A♥, 2♥\n")
        .append("F4:\n")
        .append("O1:\n")
        .append("O2:\n")
        .append("O3:\n")
        .append("O4: 8♦\n")
        .append("C1: K♠, 3♦, K♥, 8♠, 5♥, 5♦, 5♠\n")
        .append("C2: 4♣, 5♣, 4♠, 9♦, K♣\n")
        .append("C3: 2♠, 9♠, 8♣, 10♦, 8♥, Q♠, J♦\n")
        .append("C4: 10♥, J♠, Q♦, 6♣, 3♣, J♣\n")
        .append("C5: 6♦, 3♥, 10♠, Q♥, 6♠\n")
        .append("C6: 9♣, 7♣, 7♥, K♦, 4♥, 3♠, 2♦\n")
        .append("C7: Q♣, J♥, 10♣, 9♥\n")
        .append("C8: 7♦, 7♠, 6♥, A♦, 4♦")
        .append("Second Board:\n")
        .append("Just kidding its another message\n")
        .append("that was kinda mean, so here's the second, third, and fourth board:\n")
        .append("F1: A♠\n")
        .append("F2: A♣, 2♣\n")
        .append("F3: A♥, 2♥\n")
        .append("F4:\n")
        .append("O1:\n")
        .append("O2:\n")
        .append("O3:\n")
        .append("O4: 8♦\n")
        .append("C1: K♠, 3♦, K♥, 8♠, 5♥, 5♦, 5♠\n")
        .append("C2: 4♣, 5♣, 4♠, 9♦, K♣\n")
        .append("C3: 2♠, 9♠, 8♣, 10♦, 8♥, Q♠, J♦\n")
        .append("C4: 10♥, J♠, Q♦, 6♣, 3♣, J♣\n")
        .append("C5: 6♦, 3♥, 10♠, Q♥, 6♠\n")
        .append("C6: 9♣, 7♣, 7♥, K♦, 4♥, 3♠, 2♦\n")
        .append("C7: Q♣, J♥, 10♣, 9♥\n")
        .append("C8: 7♦, 7♠, 6♥, A♦, 4♦")
        .append("F1: A♠\n")
        .append("F2: A♣, 2♣\n")
        .append("F3: A♥, 2♥\n")
        .append("F4:\n")
        .append("O1:\n")
        .append("O2:\n")
        .append("O3:\n")
        .append("O4: 8♦\n")
        .append("C1: K♠, 3♦, K♥, 8♠, 5♥, 5♦, 5♠\n")
        .append("C2: 4♣, 5♣, 4♠, 9♦, K♣\n")
        .append("C3: 2♠, 9♠, 8♣, 10♦, 8♥, Q♠, J♦\n")
        .append("C4: 10♥, J♠, Q♦, 6♣, 3♣, J♣\n")
        .append("C5: 6♦, 3♥, 10♠, Q♥, 6♠\n")
        .append("C6: 9♣, 7♣, 7♥, K♦, 4♥, 3♠, 2♦\n")
        .append("C7: Q♣, J♥, 10♣, 9♥\n")
        .append("C8: 7♦, 7♠, 6♥, A♦, 4♦")
        .append("F1: A♠\n")
        .append("F2: A♣, 2♣\n")
        .append("F3: A♥, 2♥\n")
        .append("F4:\n")
        .append("O1:\n")
        .append("O2:\n")
        .append("O3:\n")
        .append("O4: 8♦\n")
        .append("C1: K♠, 3♦, K♥, 8♠, 5♥, 5♦, 5♠\n")
        .append("C2: 4♣, 5♣, 4♠, 9♦, K♣\n")
        .append("C3: 2♠, 9♠, 8♣, 10♦, 8♥, Q♠, J♦\n")
        .append("C4: 10♥, J♠, Q♦, 6♣, 3♣, J♣\n")
        .append("C5: 6♦, 3♥, 10♠, Q♥, 6♠\n")
        .append("C6: 9♣, 7♣, 7♥, K♦, 4♥, 3♠, 2♦\n")
        .append("C7: Q♣, J♥, 10♣, 9♥\n")
        .append("C8: 7♦, 7♠, 6♥, A♦, 4♦")
        .append("this will be a long assert...").toString(), out.toString());
  }
}
