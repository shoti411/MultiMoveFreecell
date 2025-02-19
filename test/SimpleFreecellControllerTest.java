import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import cs3500.freecell.controller.FreecellController;
import cs3500.freecell.controller.SimpleFreecellController;
import cs3500.freecell.model.FreecellModel;
import cs3500.freecell.model.hw02.Card;
import cs3500.freecell.model.hw02.GameState;
import cs3500.freecell.model.hw02.ICard;
import cs3500.freecell.model.hw02.SimpleFreecellModel;
import cs3500.freecell.model.hw02.Suit;
import cs3500.freecell.model.hw02.Value;
import cs3500.freecell.model.hw04.MultiMoveFreecellModel;
import cs3500.freecell.view.FreecellTextView;
import cs3500.freecell.view.FreecellView;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for SimpleFreecellController: Unit tests to ensure that SimpleFreecellController
 * correctly handles inputs from the player in the console, correctly delegating tasks to the View
 * and Model, and correctly handling all of the exceptions which are thrown. Makes sure the
 * controller otherwise functions correctly.
 */
public class SimpleFreecellControllerTest {

  private final ArrayList<ICard> cAllCards = new ArrayList<ICard>(Arrays
      .asList(this.cAce, this.c2, this.c3, this.c4, this.c5, this.c6, this.c7, this.c8, this.c9,
          this.c10, this.cJack, this.cQueen, this.cKing));

  private final ArrayList<ICard> cOnlyAceList = new ArrayList<ICard>(Arrays.asList(this.cAce));

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

  private final ArrayList<ICard> dAllCards = new ArrayList<ICard>(Arrays
      .asList(this.dAce, this.d2, this.d3, this.d4,
          this.d5, this.d6, this.d7, this.d8, this.d9, this.d10, this.dJack, this.dQueen,
          this.dKing));

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

  private final ArrayList<ICard> hAllCards = new ArrayList<ICard>(Arrays
      .asList(this.hAce, this.h2, this.h3, this.h4, this.h5, this.h6, this.h7, this.h8,
          this.h9, this.h10, this.hJack, this.hQueen, this.hKing));

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


  private final ArrayList<ICard> sAllCards = new ArrayList<ICard>(Arrays
      .asList(this.sAce, this.s2, this.s3, this.s4, this.s5, this.s6, this.s7, this.s8, this.s9,
          this.s10, this.sJack, this.sQueen,
          this.sKing));

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


  FreecellModel<ICard> freecell = new SimpleFreecellModel();


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


  private final ArrayList<ICard> c2PileSampleGameGivenPossibleBeginning = new ArrayList<ICard>(
      Arrays.asList(this.c4, this.c5, this.s4, this.d9, this.cKing, this.c2, this.sAce));

  private final ArrayList<ICard> c4PileSampleGameGivenPossibleBeginning = new ArrayList<ICard>(
      Arrays.asList(this.h10, this.sJack, this.dQueen, this.c6, this.c3, this.cJack, this.d2));

  private final ArrayList<ICard> c5PileSampleGameGivenPossibleBeginning = new ArrayList<ICard>(
      Arrays.asList(this.d6, this.h3, this.s10, this.hQueen, this.s6, this.d8));

  private final ArrayList<ICard> c6PileSampleGameGivenPossibleBeginning = new ArrayList<ICard>(
      Arrays.asList(this.c9, this.c7, this.h7, this.dKing, this.h4, this.s3));

  private final ArrayList<ICard> c7PileSampleGameGivenPossibleBeginning = new ArrayList<ICard>(
      Arrays.asList(this.cQueen, this.hJack, this.c10, this.h9, this.cAce, this.h2));

  private final ArrayList<ICard> c8PileSampleGameGivenPossibleBeginning = new ArrayList<ICard>(
      Arrays.asList(this.d7, this.s7, this.h6, this.dAce, this.d4, this.hAce));


  private FreecellModel<ICard> exampleGameGivenPossibleBeginning = new SimpleFreecellModel(
      GameState.PLAYING, new ArrayList<ArrayList<ICard>>(
      Arrays.asList(new ArrayList<ICard>(), new ArrayList<ICard>(), new ArrayList<ICard>(),
          new ArrayList<ICard>())), new ArrayList<ArrayList<ICard>>(Arrays
      .asList(this.c1PileSampleGameGiven, this.c2PileSampleGameGivenPossibleBeginning,
          this.c3PileSampleGameGiven, this.c4PileSampleGameGivenPossibleBeginning,
          this.c5PileSampleGameGivenPossibleBeginning, this.c6PileSampleGameGivenPossibleBeginning,
          this.c7PileSampleGameGivenPossibleBeginning,
          this.c8PileSampleGameGivenPossibleBeginning)),
      new ArrayList<ArrayList<ICard>>(Arrays
          .asList(new ArrayList<ICard>(), new ArrayList<ICard>(), new ArrayList<ICard>(),
              new ArrayList<ICard>())));

  private FreecellModel<ICard> exampleGameGivenNotStarted = new SimpleFreecellModel(
      GameState.HASNOTSTARTED,
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

  private FreecellModel<ICard> exampleGameGivenAlreadyStarted = new SimpleFreecellModel(
      GameState.PLAYING,
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
  
  private FreecellModel<ICard> exampleGameGivenAlmostFinished = new SimpleFreecellModel(
      GameState.PLAYING,
      new ArrayList<ArrayList<ICard>>(
          Arrays.asList(this.cAllCards, this.dAllCards, this.hAllCards,
              this.sAllCards)),
      new ArrayList<ArrayList<ICard>>(Arrays
          .asList(this.cOnlyAceList, new ArrayList<ICard>(), new ArrayList<ICard>(),
              new ArrayList<ICard>())),
      new ArrayList<ArrayList<ICard>>(Arrays.asList(this.o1PileSampleGameGiven)));

  private FreecellModel<ICard> exampleGameGivenFinished = new SimpleFreecellModel(
      GameState.FINISHED,
      new ArrayList<ArrayList<ICard>>(Arrays
          .asList(new ArrayList<ICard>(), new ArrayList<ICard>(), new ArrayList<ICard>(),
              new ArrayList<ICard>())),
      new ArrayList<ArrayList<ICard>>(Arrays.asList(new ArrayList<ICard>(), new ArrayList<ICard>(),
          new ArrayList<ICard>(), new ArrayList<ICard>())),
      new ArrayList<ArrayList<ICard>>(Arrays.asList(new ArrayList<ICard>(), new ArrayList<ICard>(),
          new ArrayList<ICard>(), new ArrayList<ICard>())));

  List<ICard> oppositeOfEasyToWinDeck = new ArrayList<ICard>();
  List<ICard> easyToWinDeck = new ArrayList<ICard>();

  Readable rd = new StringReader("");
  Appendable ap = new StringBuilder();
  FreecellController<ICard> control = new SimpleFreecellController(this.freecell, rd, ap);

  /*
   *      F1: A♠
   *      F2: A♣, 2♣
   *      F3: A♥, 2♥
   *      F4:
   *      O1:
   *      O2:
   *      O3:
   *      O4: 8♦
   *      C1: K♠, 3♦, K♥, 8♠, 5♥, 5♦, 5♠
   *      C2: 4♣, 5♣, 4♠, 9♦, K♣
   *      C3: 2♠, 9♠, 8♣, 10♦, 8♥, Q♠, J♦
   *      C4: 10♥, J♠, Q♦, 6♣, 3♣, J♣
   *      C5: 6♦, 3♥, 10♠, Q♥, 6♠
   *      C6: 9♣, 7♣, 7♥, K♦, 4♥, 3♠, 2♦
   *      C7: Q♣, J♥, 10♣, 9♥
   *      C8: 7♦, 7♠, 6♥, A♦, 4♦
   */
  @Before
  public void setup() {
    // This is the example game which was given as a sample in the assignment
    // (the one in the first picture)
    this.exampleGameGivenNotStarted = new SimpleFreecellModel(GameState.HASNOTSTARTED,
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

    this.exampleGameGivenAlreadyStarted = new SimpleFreecellModel(GameState.PLAYING,
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

    this.exampleGameGivenPossibleBeginning = new SimpleFreecellModel(
        GameState.PLAYING, new ArrayList<ArrayList<ICard>>(
        Arrays.asList(new ArrayList<ICard>(), new ArrayList<ICard>(), new ArrayList<ICard>(),
            new ArrayList<ICard>())), new ArrayList<ArrayList<ICard>>(Arrays
        .asList(this.c1PileSampleGameGiven, this.c2PileSampleGameGivenPossibleBeginning,
            this.c3PileSampleGameGiven, this.c4PileSampleGameGivenPossibleBeginning,
            this.c5PileSampleGameGivenPossibleBeginning,
            this.c6PileSampleGameGivenPossibleBeginning,
            this.c7PileSampleGameGivenPossibleBeginning,
            this.c8PileSampleGameGivenPossibleBeginning)),
        new ArrayList<ArrayList<ICard>>(Arrays
            .asList(new ArrayList<ICard>(), new ArrayList<ICard>(), new ArrayList<ICard>(),
                new ArrayList<ICard>())));
    this.exampleGameGivenFinished = new SimpleFreecellModel(
        GameState.FINISHED,
        new ArrayList<ArrayList<ICard>>(Arrays
            .asList(new ArrayList<ICard>(), new ArrayList<ICard>(), new ArrayList<ICard>(),
                new ArrayList<ICard>())), new ArrayList<ArrayList<ICard>>(Arrays
        .asList(new ArrayList<ICard>(), new ArrayList<ICard>(), new ArrayList<ICard>(),
            new ArrayList<ICard>())),
        new ArrayList<ArrayList<ICard>>(Arrays
            .asList(new ArrayList<ICard>(), new ArrayList<ICard>(), new ArrayList<ICard>(),
                new ArrayList<ICard>())));
    this.exampleGameGivenAlmostFinished = new SimpleFreecellModel(
        GameState.PLAYING,
        new ArrayList<ArrayList<ICard>>(
            Arrays.asList(this.cAllCards, this.dAllCards, this.hAllCards,
                this.sAllCards)),
        new ArrayList<ArrayList<ICard>>(Arrays
            .asList(this.cOnlyAceList, new ArrayList<ICard>(), new ArrayList<ICard>(),
                new ArrayList<ICard>())),
        new ArrayList<ArrayList<ICard>>(Arrays
            .asList(this.o1PileSampleGameGiven, new ArrayList<ICard>(), new ArrayList<ICard>(),
                new ArrayList<ICard>())));

    for (Value v : Value.values()) {
      for (Suit s : Suit.values()) {
        this.oppositeOfEasyToWinDeck.add(new Card(v, s));
      }
    }

    for (Value v : Value.values()) {
      for (Suit s : Suit.values()) {
        this.easyToWinDeck.add(new Card(v, s));
      }
    }
    Collections.reverse(this.easyToWinDeck);

    this.oppositeOfEasyToWinDeck.remove(this.cAce);
    this.oppositeOfEasyToWinDeck.remove(this.dAce);
    this.oppositeOfEasyToWinDeck.remove(this.hAce);
    this.oppositeOfEasyToWinDeck.remove(this.sAce);
    this.oppositeOfEasyToWinDeck.add(this.cAce);
    this.oppositeOfEasyToWinDeck.add(this.dAce);
    this.oppositeOfEasyToWinDeck.add(this.hAce);
    this.oppositeOfEasyToWinDeck.add(this.sAce);

    this.rd = new StringReader("");
    this.ap = new StringBuilder();
    this.control = new SimpleFreecellController(this.freecell, rd, ap);
  }


  @Test(expected = IllegalArgumentException.class)
  public void testControllerConstructorExceptionNullModel() {
    // should throw an exception if the model is null
    Appendable out = new StringBuilder();
    Readable in = new StringReader("C1 7 O1 q");
    SimpleFreecellController controller = new SimpleFreecellController(null, in, out);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testControllerConstructorExceptionNullReadable() {
    FreecellModel<ICard> model = this.exampleGameGivenAlreadyStarted;
    Appendable out = new StringBuilder();
    SimpleFreecellController controller = new SimpleFreecellController(model, null, out);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testControllerConstructorExceptionNullAppendable() {
    FreecellModel<ICard> model = this.exampleGameGivenNotStarted;
    Readable in = new StringReader("C1\n7\nO1\nq");
    SimpleFreecellController controller = new SimpleFreecellController(model, in, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPlayGameExceptionDeckNull() {
    FreecellModel<ICard> model = this.exampleGameGivenPossibleBeginning;
    Appendable out = new StringBuilder();
    Readable in = new StringReader("q");
    SimpleFreecellController controller = new SimpleFreecellController(model, in, out);
    controller.playGame(null, 8, 4, false);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPlayGameExceptionNullModel() {
    FreecellController<ICard> nullModelController = new SimpleFreecellController(null);
    nullModelController.playGame(this.easyToWinDeck, 8, 4, false);
  }

  @Test(expected = IllegalStateException.class)
  public void testPlayGameExceptionStateNoQuitSoFunctionNeverEndsAndRunsOutOfInputsToRead() {
    FreecellModel<ICard> model = this.freecell;
    Readable in = new StringReader("null");
    Appendable out = new StringBuilder(
        "There should be not enough to read in this controller's readable");
    SimpleFreecellController controller = new SimpleFreecellController(model, in, out);
    controller.playGame(this.oppositeOfEasyToWinDeck, 8, 4, false);
  }

  @Test(expected = IllegalStateException.class)
  public void testPlayGameExceptionReadableFailureNothingToRead() {
    FreecellModel<ICard> model = this.exampleGameGivenNotStarted;
    Appendable out = new StringBuilder();
    // this is all whitespace so it shouldn't read anything here
    Readable in = new StringReader("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    SimpleFreecellController controller = new SimpleFreecellController(model, in, out);
    controller.playGame(this.deckCopy, 8, 4, false);
  }

  @Test(expected = IllegalStateException.class)
  public void testPlayGameExceptionNoQuitOrGameEndSoStringSoCannotRead() {
    FreecellModel<ICard> model = this.exampleGameGivenFinished;
    Appendable out = new StringBuilder();
    Readable in = new StringReader("C1 1 O1");
    SimpleFreecellController controller = new SimpleFreecellController(model, in, out);
    controller.playGame(this.deckCopy, 8, 4, false);
  }


  @Test(expected = IllegalStateException.class)
  public void testPlayGameExceptionAppendableIOExceptionFailure() {
    FreecellModel<ICard> model = this.exampleGameGivenPossibleBeginning;
    Appendable out = new AppendableIOExceptionClass();
    Readable in = new StringReader("q");
    SimpleFreecellController controller = new SimpleFreecellController(model, in, out);
    controller.playGame(this.deckCopy, 8, 4, false);
  }


  @Test
  public void testPlayGameExceptionInvalidDeckNotNull() {
    FreecellModel<ICard> model = this.exampleGameGivenNotStarted;
    Appendable out = new StringBuilder();
    Readable in = new StringReader("C1 1 O1");
    SimpleFreecellController controller = new SimpleFreecellController(model, in, out);
    // this is not enough cards to start the game of freecell
    controller.playGame(this.c1PileSampleGameGiven, 8, 4, false);
    assertEquals("Could not start game.", out.toString());
  }

  @Test
  public void testPlayGameInvalidCascadePiles() {
    FreecellModel<ICard> model = this.exampleGameGivenAlmostFinished;
    Appendable out = new StringBuilder();
    Readable in = new StringReader("Trying 3 Cascade Piles");
    SimpleFreecellController controller = new SimpleFreecellController(model, in, out);
    controller.playGame(model.getDeck(), 3, 4, false);
    assertEquals("Could not start game.", out.toString());
  }

  @Test
  public void testPlayGameInvalidOpenPiles() {
    FreecellModel<ICard> model = this.exampleGameGivenFinished;
    Appendable out = new StringBuilder();
    Readable in = new StringReader("Trying 0 Open Piles");
    SimpleFreecellController controller = new SimpleFreecellController(model, in, out);
    controller.playGame(model.getDeck(), 8, 0, false);
    assertEquals("Could not start game.", out.toString());
  }

  @Test
  public void testPlayGameQuitSmallq() {
    FreecellModel<ICard> model = new SimpleFreecellModel();
    Appendable out = new StringBuilder();
    Readable in = new StringReader("q");
    model.startGame(model.getDeck(), 8, 4, false);
    SimpleFreecellController controller = new SimpleFreecellController(model, in, out);
    controller.playGame(model.getDeck(), 8, 4, false);
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
        .append("C8: 8♣, 3♦, J♦, 6♥, A♠, 9♠\n")
        .append("Game quit prematurely.").toString(), out.toString());
  }

  @Test
  public void testPlayGameQuitBigQ() {
    FreecellModel<ICard> model = new SimpleFreecellModel();
    Appendable out = new StringBuilder();
    Readable in = new StringReader("Q");
    model.startGame(model.getDeck(), 8, 4, false);
    SimpleFreecellController controller = new SimpleFreecellController(model, in, out);
    controller.playGame(model.getDeck(), 8, 4, false);
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
        .append("C8: 8♣, 3♦, J♦, 6♥, A♠, 9♠\n")
        .append("Game quit prematurely.").toString(), out.toString());
  }

  @Test
  public void testPlayGameSuccessNotShuffledDeckDisplayCorrect() {
    String playerInputMove = "C1 13 F1 q";
    FreecellModel<ICard> model = this.exampleGameGivenFinished;
    Appendable out = new StringBuilder();
    Readable in = new StringReader(playerInputMove);
    SimpleFreecellController controller = new SimpleFreecellController(model, in, out);
    controller.playGame(this.oppositeOfEasyToWinDeck, 4, 10, false);
    assertEquals(new StringBuilder()
        .append("F1:\n")
        .append("F2:\n")
        .append("F3:\n")
        .append("F4:\n")
        .append("O1:\n")
        .append("O2:\n")
        .append("O3:\n")
        .append("O4:\n")
        .append("O5:\n")
        .append("O6:\n")
        .append("O7:\n")
        .append("O8:\n")
        .append("O9:\n")
        .append("O10:\n")
        .append("C1: 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣, A♣\n")
        .append("C2: 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦, A♦\n")
        .append("C3: 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥, A♥\n")
        .append("C4: 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠, A♠\n")
        .append("F1: A♣\n")
        .append("F2:\n")
        .append("F3:\n")
        .append("F4:\n")
        .append("O1:\n")
        .append("O2:\n")
        .append("O3:\n")
        .append("O4:\n")
        .append("O5:\n")
        .append("O6:\n")
        .append("O7:\n")
        .append("O8:\n")
        .append("O9:\n")
        .append("O10:\n")
        .append("C1: 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n")
        .append("C2: 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦, A♦\n")
        .append("C3: 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥, A♥\n")
        .append("C4: 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠, A♠\n")
        .append("Game quit prematurely.").toString(), out.toString());
  }

  @Test
  public void testPlayGameSuccessShuffledDeck() {
    String playerInputMove = "q";
    FreecellModel<ICard> model = this.exampleGameGivenFinished;
    Appendable out = new StringBuilder();
    Readable in = new StringReader(playerInputMove);
    SimpleFreecellController controller = new SimpleFreecellController(model, in, out);
    controller.playGame(this.oppositeOfEasyToWinDeck, 4, 10, true);
    // if shuffled correctly, then the cards should not be in the same place
    assertNotEquals(new StringBuilder("     F1:\n")
        .append("     F2:\n")
        .append("     F3:\n")
        .append("     F4:\n")
        .append("     O1:\n")
        .append("     O2:\n")
        .append("     O3:\n")
        .append("     O4:\n")
        .append("     O5:\n")
        .append("     O6:\n")
        .append("     O7:\n")
        .append("     O8:\n")
        .append("     O9:\n")
        .append("     O10:\n")
        .append("     C1: 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣, A♣\n")
        .append("     C2: 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦, A♦\n")
        .append("     C3: 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥, A♥\n")
        .append("     C4: 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠, A♠\n")
        .append("Game quit prematurely.").toString(), out.toString());
    // trying with same deck again to make sure cards actually randomly shuffled every time.
    Readable in2 = new StringReader("q");
    Appendable out2 = new StringBuilder();
    FreecellController<ICard> controller2 = new SimpleFreecellController(model, in2, out2);
    controller2.playGame(this.oppositeOfEasyToWinDeck, 4, 10, true);
    assertNotEquals(new StringBuilder("     F1:\n")
        .append("     F2:\n")
        .append("     F3:\n")
        .append("     F4:\n")
        .append("     O1:\n")
        .append("     O2:\n")
        .append("     O3:\n")
        .append("     O4:\n")
        .append("     O5:\n")
        .append("     O6:\n")
        .append("     O7:\n")
        .append("     O8:\n")
        .append("     O9:\n")
        .append("     O10:\n")
        .append("     C1: 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣, A♣\n")
        .append("     C2: 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦, A♦\n")
        .append("     C3: 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥, A♥\n")
        .append("     C4: 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠, A♠\n")
        .append("Game quit prematurely.").toString(), out2.toString());
    // make sure the outputs after shuffling the deck are not the same thing
    assertNotEquals(out.toString(), out2.toString());

  }

  @Test
  public void testPlayGameSuccessMoveCascadeToOpenPile() {
    String playerInputMove = "C1 7 O1 q";
    FreecellModel<ICard> model = this.exampleGameGivenFinished;
    Appendable out = new StringBuilder();
    Readable in = new StringReader(playerInputMove);
    model.startGame(model.getDeck(), 8, 4, false);
    SimpleFreecellController controller = new SimpleFreecellController(model, in, out);
    controller.playGame(model.getDeck(), 8, 4, false);
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
        .append("C8: 8♣, 3♦, J♦, 6♥, A♠, 9♠\n")
        .append("F1:\n")
        .append("F2:\n")
        .append("F3:\n")
        .append("F4:\n")
        .append("O1: 10♠\n")
        .append("O2:\n")
        .append("O3:\n")
        .append("O4:\n")
        .append("C1: A♣, 9♣, 4♦, Q♦, 7♥, 2♠\n")
        .append("C2: 2♣, 10♣, 5♦, K♦, 8♥, 3♠, J♠\n")
        .append("C3: 3♣, J♣, 6♦, A♥, 9♥, 4♠, Q♠\n")
        .append("C4: 4♣, Q♣, 7♦, 2♥, 10♥, 5♠, K♠\n")
        .append("C5: 5♣, K♣, 8♦, 3♥, J♥, 6♠\n")
        .append("C6: 6♣, A♦, 9♦, 4♥, Q♥, 7♠\n")
        .append("C7: 7♣, 2♦, 10♦, 5♥, K♥, 8♠\n")
        .append("C8: 8♣, 3♦, J♦, 6♥, A♠, 9♠\n")
        .append("Game quit prematurely.").toString(), out.toString());
  }

  @Test
  public void testPlayGameKeepsAskingForSourceInputAfterMultipleFailedAttempts() {
    Readable in = new StringReader(" A1 B2 Z3 c f o C F O Cop Fe Or 1 2 3 1C 2F 3O C1 Q ");
    Appendable out = new StringBuilder();
    this.control = new SimpleFreecellController(this.freecell, in, out);
    this.control.playGame(this.oppositeOfEasyToWinDeck, 4, 1, false);
    assertEquals(new StringBuilder()
        .append("F1:\n")
        .append("F2:\n")
        .append("F3:\n")
        .append("F4:\n")
        .append("O1:\n")
        .append("C1: 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣, A♣\n")
        .append("C2: 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦, A♦\n")
        .append("C3: 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥, A♥\n")
        .append("C4: 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠, A♠\n")
        .append("Invalid Source Pile Type Input, Please Input Again.\n")
        .append("Invalid Source Pile Type Input, Please Input Again.\n")
        .append("Invalid Source Pile Type Input, Please Input Again.\n")
        .append(
            "Invalid Source Pile, To Identify Pile must have the 1st character be the Pile Type ")
        .append("and the 2nd character be the Pile Index. Try Again.\n")
        .append(
            "Invalid Source Pile, To Identify Pile must have the 1st character be the Pile Type ")
        .append("and the 2nd character be the Pile Index. Try Again.\n")
        .append(
            "Invalid Source Pile, To Identify Pile must have the 1st character be the Pile Type ")
        .append("and the 2nd character be the Pile Index. Try Again.\n")
        .append(
            "Invalid Source Pile, To Identify Pile must have the 1st character be the Pile Type ")
        .append("and the 2nd character be the Pile Index. Try Again.\n")
        .append(
            "Invalid Source Pile, To Identify Pile must have the 1st character be the Pile Type ")
        .append("and the 2nd character be the Pile Index. Try Again.\n")
        .append(
            "Invalid Source Pile, To Identify Pile must have the 1st character be the Pile Type ")
        .append("and the 2nd character be the Pile Index. Try Again.\n")
        .append("Could not find Valid Pile Index in Input. Try Inputting the Whole Pile Again.\n")
        .append("Could not find Valid Pile Index in Input. Try Inputting the Whole Pile Again.\n")
        .append("Could not find Valid Pile Index in Input. Try Inputting the Whole Pile Again.\n")
        .append(
            "Invalid Source Pile, To Identify Pile must have the 1st character be the Pile Type ")
        .append("and the 2nd character be the Pile Index. Try Again.\n")
        .append(
            "Invalid Source Pile, To Identify Pile must have the 1st character be the Pile Type ")
        .append("and the 2nd character be the Pile Index. Try Again.\n")
        .append(
            "Invalid Source Pile, To Identify Pile must have the 1st character be the Pile Type ")
        .append("and the 2nd character be the Pile Index. Try Again.\n")
        .append("Invalid Source Pile Type Input, Please Input Again.\n")
        .append("Invalid Source Pile Type Input, Please Input Again.\n")
        .append("Invalid Source Pile Type Input, Please Input Again.\n")
        .append("Game quit prematurely.").toString(), out.toString());
  }

  @Test
  public void testPlayGameInvalidMoveInvalidSourcePileIndex() {
    Readable in = new StringReader("C5 13 O1 C0 13 O1 q");
    Appendable out = new StringBuilder();
    this.control = new SimpleFreecellController(this.freecell, in, out);
    this.control.playGame(this.easyToWinDeck, 4, 1, false);
    assertEquals(new StringBuilder()
        .append("F1:\n")
        .append("F2:\n")
        .append("F3:\n")
        .append("F4:\n")
        .append("O1:\n")
        .append("C1: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠\n")
        .append("C2: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n")
        .append("C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n")
        .append("C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n")
        .append("Invalid Pile Index\n")
        .append("Invalid Pile Index\n")
        .append("Game quit prematurely.").toString(), out.toString());
  }

  @Test
  public void testPlayGameInvalidMoveInvalidDestPileIndex() {
    Readable in = new StringReader("C4 13 O5 C4 13 O0 q");
    Appendable out = new StringBuilder();
    this.control = new SimpleFreecellController(this.freecell, in, out);
    this.control.playGame(this.easyToWinDeck, 4, 1, false);
    assertEquals(new StringBuilder()
        .append("F1:\n")
        .append("F2:\n")
        .append("F3:\n")
        .append("F4:\n")
        .append("O1:\n")
        .append("C1: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠\n")
        .append("C2: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n")
        .append("C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n")
        .append("C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n")
        .append("Invalid Pile Index\n")
        .append("Invalid Pile Index\n")
        .append("Game quit prematurely.").toString(), out.toString());
  }


  @Test
  public void testPlayGameInvalidMoveNotLastCardInPile() {
    Readable in = new StringReader("C1 12 O1 q");
    Appendable out = new StringBuilder();
    this.control = new SimpleFreecellController(this.freecell, in, out);
    this.control.playGame(this.easyToWinDeck, 4, 1, false);
    assertEquals(new StringBuilder()
        .append("F1:\n")
        .append("F2:\n")
        .append("F3:\n")
        .append("F4:\n")
        .append("O1:\n")
        .append("C1: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠\n")
        .append("C2: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n")
        .append("C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n")
        .append("C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n")
        .append("Invalid Move, Not Last Card in Cascade Pile\n")
        .append("Game quit prematurely.").toString(), out.toString());
  }

  @Test
  public void testPlayGameInvalidMoveInvalidCacadeCardIndexTooHigh() {
    Readable in = new StringReader("C1 14 O1 q");
    Appendable out = new StringBuilder();
    this.control = new SimpleFreecellController(this.freecell, in, out);
    this.control.playGame(this.easyToWinDeck, 4, 1, false);
    assertEquals(new StringBuilder()
        .append("F1:\n")
        .append("F2:\n")
        .append("F3:\n")
        .append("F4:\n")
        .append("O1:\n")
        .append("C1: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠\n")
        .append("C2: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n")
        .append("C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n")
        .append("C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n")
        .append("Invalid Cascade Card Index\n")
        .append("Game quit prematurely.").toString(), out.toString());
  }

  @Test
  public void testPlayGameInvalidMoveInvalidCascadeCardIndexTooLow() {
    Readable in = new StringReader("C1 0 O1 q");
    Appendable out = new StringBuilder();
    this.control = new SimpleFreecellController(this.freecell, in, out);
    this.control.playGame(this.easyToWinDeck, 4, 1, false);
    assertEquals(new StringBuilder()
        .append("F1:\n")
        .append("F2:\n")
        .append("F3:\n")
        .append("F4:\n")
        .append("O1:\n")
        .append("C1: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠\n")
        .append("C2: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n")
        .append("C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n")
        .append("C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n")
        .append("Invalid Cascade Card Index\n")
        .append("Game quit prematurely.").toString(), out.toString());
  }


  @Test
  public void testPlayGameKeepsAskingForSourceCardInputAfterMultipleFailedAttempts() {
    Readable in = new StringReader("C1 A b r i p0 u9 a10201 7 q");
    Appendable out = new StringBuilder();
    this.control = new SimpleFreecellController(this.freecell, in, out);
    this.control.playGame(this.oppositeOfEasyToWinDeck, 4, 1, false);
    assertEquals(new StringBuilder()
        .append("F1:\n")
        .append("F2:\n")
        .append("F3:\n")
        .append("F4:\n")
        .append("O1:\n")
        .append("C1: 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣, A♣\n")
        .append("C2: 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦, A♦\n")
        .append("C3: 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥, A♥\n")
        .append("C4: 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠, A♠\n")
        .append("Invalid Input for Card Index in Pile. Please Reenter Card Index.\n")
        .append("Invalid Input for Card Index in Pile. Please Reenter Card Index.\n")
        .append("Invalid Input for Card Index in Pile. Please Reenter Card Index.\n")
        .append("Invalid Input for Card Index in Pile. Please Reenter Card Index.\n")
        .append("Invalid Input for Card Index in Pile. Please Reenter Card Index.\n")
        .append("Invalid Input for Card Index in Pile. Please Reenter Card Index.\n")
        .append("Invalid Input for Card Index in Pile. Please Reenter Card Index.\n")
        .append("Game quit prematurely.").toString(), out.toString());
  }

  @Test
  public void testPlayGameKeepsAskingForDestCardInputAfterMultipleFailedAttempts() {
    Readable in = new StringReader("C1 7 A1 B2 Z3 c f o C F O Cop Fe Or 1 2 3 1C 2F 3O C1 Q ");
    Appendable out = new StringBuilder();
    this.control = new SimpleFreecellController(this.freecell, in, out);
    this.control.playGame(this.oppositeOfEasyToWinDeck, 4, 1, false);
    assertEquals(new StringBuilder()
        .append("F1:\n")
        .append("F2:\n")
        .append("F3:\n")
        .append("F4:\n")
        .append("O1:\n")
        .append("C1: 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣, A♣\n")
        .append("C2: 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦, A♦\n")
        .append("C3: 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥, A♥\n")
        .append("C4: 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠, A♠\n")
        .append("Invalid Destination Pile Type Input, Please Input Again.\n")
        .append("Invalid Destination Pile Type Input, Please Input Again.\n")
        .append("Invalid Destination Pile Type Input, Please Input Again.\n")
        .append(
            "Invalid Destination Pile, To Identify Pile must have the 1st character be the Pile ")
        .append("Type and the 2nd character be the Pile Index. Try Again.\n")
        .append(
            "Invalid Destination Pile, To Identify Pile must have the 1st character be the Pile ")
        .append("Type and the 2nd character be the Pile Index. Try Again.\n")
        .append(
            "Invalid Destination Pile, To Identify Pile must have the 1st character be the Pile ")
        .append("Type and the 2nd character be the Pile Index. Try Again.\n")
        .append(
            "Invalid Destination Pile, To Identify Pile must have the 1st character be the Pile ")
        .append("Type and the 2nd character be the Pile Index. Try Again.\n")
        .append(
            "Invalid Destination Pile, To Identify Pile must have the 1st character be the Pile ")
        .append("Type and the 2nd character be the Pile Index. Try Again.\n")
        .append(
            "Invalid Destination Pile, To Identify Pile must have the 1st character be the Pile ")
        .append("Type and the 2nd character be the Pile Index. Try Again.\n")
        .append("Could not find Valid Pile Index in Input. Try Inputting the Whole Pile Again.\n")
        .append("Could not find Valid Pile Index in Input. Try Inputting the Whole Pile Again.\n")
        .append("Could not find Valid Pile Index in Input. Try Inputting the Whole Pile Again.\n")
        .append(
            "Invalid Destination Pile, To Identify Pile must have the 1st character be the Pile ")
        .append("Type and the 2nd character be the Pile Index. Try Again.\n")
        .append(
            "Invalid Destination Pile, To Identify Pile must have the 1st character be the Pile ")
        .append("Type and the 2nd character be the Pile Index. Try Again.\n")
        .append(
            "Invalid Destination Pile, To Identify Pile must have the 1st character be the Pile ")
        .append("Type and the 2nd character be the Pile Index. Try Again.\n")
        .append("Invalid Destination Pile Type Input, Please Input Again.\n")
        .append("Invalid Destination Pile Type Input, Please Input Again.\n")
        .append("Invalid Destination Pile Type Input, Please Input Again.\n")
        .append("Invalid Move, Not Last Card in Cascade Pile\n")
        .append("Game quit prematurely.").toString(), out.toString());
  }

  @Test
  public void testPlayGameKeepsAskingForInputsAfterMultipleFailedAttemptsAllTogether() {
    Readable in = new StringReader(
        new StringBuilder(
            " A1 B2 Z3 c f o C F O Cop Fe Or 1 2 3 1C 2F 3O C1 A b r i p0 u9 a10201 13")
            .append(" A1 B2 Z3 c f o C F O Cop Fe Or 1 2 3 1C 2F 3O O1 q").toString());
    Appendable out = new StringBuilder();
    this.control = new SimpleFreecellController(this.freecell, in, out);
    this.control.playGame(this.oppositeOfEasyToWinDeck, 4, 1, false);
    assertEquals(new StringBuilder()
        .append("F1:\n")
        .append("F2:\n")
        .append("F3:\n")
        .append("F4:\n")
        .append("O1:\n")
        .append("C1: 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣, A♣\n")
        .append("C2: 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦, A♦\n")
        .append("C3: 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥, A♥\n")
        .append("C4: 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠, A♠\n")
        .append("Invalid Source Pile Type Input, Please Input Again.\n")
        .append("Invalid Source Pile Type Input, Please Input Again.\n")
        .append("Invalid Source Pile Type Input, Please Input Again.\n")
        .append(
            "Invalid Source Pile, To Identify Pile must have the 1st character be the Pile Type ")
        .append("and the 2nd character be the Pile Index. Try Again.\n")
        .append(
            "Invalid Source Pile, To Identify Pile must have the 1st character be the Pile Type ")
        .append("and the 2nd character be the Pile Index. Try Again.\n")
        .append(
            "Invalid Source Pile, To Identify Pile must have the 1st character be the Pile Type ")
        .append("and the 2nd character be the Pile Index. Try Again.\n")
        .append(
            "Invalid Source Pile, To Identify Pile must have the 1st character be the Pile Type ")
        .append("and the 2nd character be the Pile Index. Try Again.\n")
        .append(
            "Invalid Source Pile, To Identify Pile must have the 1st character be the Pile Type ")
        .append("and the 2nd character be the Pile Index. Try Again.\n")
        .append(
            "Invalid Source Pile, To Identify Pile must have the 1st character be the Pile Type ")
        .append("and the 2nd character be the Pile Index. Try Again.\n")
        .append("Could not find Valid Pile Index in Input. Try Inputting the Whole Pile Again.\n")
        .append("Could not find Valid Pile Index in Input. Try Inputting the Whole Pile Again.\n")
        .append("Could not find Valid Pile Index in Input. Try Inputting the Whole Pile Again.\n")
        .append(
            "Invalid Source Pile, To Identify Pile must have the 1st character be the Pile Type ")
        .append("and the 2nd character be the Pile Index. Try Again.\n")
        .append(
            "Invalid Source Pile, To Identify Pile must have the 1st character be the Pile Type ")
        .append("and the 2nd character be the Pile Index. Try Again.\n")
        .append(
            "Invalid Source Pile, To Identify Pile must have the 1st character be the Pile Type ")
        .append("and the 2nd character be the Pile Index. Try Again.\n")
        .append("Invalid Source Pile Type Input, Please Input Again.\n")
        .append("Invalid Source Pile Type Input, Please Input Again.\n")
        .append("Invalid Source Pile Type Input, Please Input Again.\n")
        .append("Invalid Input for Card Index in Pile. Please Reenter Card Index.\n")
        .append("Invalid Input for Card Index in Pile. Please Reenter Card Index.\n")
        .append("Invalid Input for Card Index in Pile. Please Reenter Card Index.\n")
        .append("Invalid Input for Card Index in Pile. Please Reenter Card Index.\n")
        .append("Invalid Input for Card Index in Pile. Please Reenter Card Index.\n")
        .append("Invalid Input for Card Index in Pile. Please Reenter Card Index.\n")
        .append("Invalid Input for Card Index in Pile. Please Reenter Card Index.\n")
        .append("Invalid Destination Pile Type Input, Please Input Again.\n")
        .append("Invalid Destination Pile Type Input, Please Input Again.\n")
        .append("Invalid Destination Pile Type Input, Please Input Again.\n")
        .append(
            "Invalid Destination Pile, To Identify Pile must have the 1st character be the Pile ")
        .append("Type and the 2nd character be the Pile Index. Try Again.\n")
        .append(
            "Invalid Destination Pile, To Identify Pile must have the 1st character be the Pile ")
        .append("Type and the 2nd character be the Pile Index. Try Again.\n")
        .append(
            "Invalid Destination Pile, To Identify Pile must have the 1st character be the Pile ")
        .append("Type and the 2nd character be the Pile Index. Try Again.\n")
        .append(
            "Invalid Destination Pile, To Identify Pile must have the 1st character be the Pile ")
        .append("Type and the 2nd character be the Pile Index. Try Again.\n")
        .append(
            "Invalid Destination Pile, To Identify Pile must have the 1st character be the Pile ")
        .append("Type and the 2nd character be the Pile Index. Try Again.\n")
        .append(
            "Invalid Destination Pile, To Identify Pile must have the 1st character be the Pile ")
        .append("Type and the 2nd character be the Pile Index. Try Again.\n")
        .append("Could not find Valid Pile Index in Input. Try Inputting the Whole Pile Again.\n")
        .append("Could not find Valid Pile Index in Input. Try Inputting the Whole Pile Again.\n")
        .append("Could not find Valid Pile Index in Input. Try Inputting the Whole Pile Again.\n")
        .append(
            "Invalid Destination Pile, To Identify Pile must have the 1st character be the Pile ")
        .append("Type and the 2nd character be the Pile Index. Try Again.\n")
        .append(
            "Invalid Destination Pile, To Identify Pile must have the 1st character be the Pile ")
        .append("Type and the 2nd character be the Pile Index. Try Again.\n")
        .append(
            "Invalid Destination Pile, To Identify Pile must have the 1st character be the Pile ")
        .append("Type and the 2nd character be the Pile Index. Try Again.\n")
        .append("Invalid Destination Pile Type Input, Please Input Again.\n")
        .append("Invalid Destination Pile Type Input, Please Input Again.\n")
        .append("Invalid Destination Pile Type Input, Please Input Again.\n")
        .append("F1:\n")
        .append("F2:\n")
        .append("F3:\n")
        .append("F4:\n")
        .append("O1: A♣\n")
        .append("C1: 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n")
        .append("C2: 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦, A♦\n")
        .append("C3: 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥, A♥\n")
        .append("C4: 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠, A♠\n")
        .append("Game quit prematurely.").toString(), out.toString());
  }

  @Test
  public void testPlayGameGameOver() {

    String commandsToWin = new StringBuilder()
        .append(" C1 13 F1 ")
        .append(" C1 12 F1 ")
        .append(" C1 11 F1 ")
        .append(" C1 10 F1 ")
        .append(" C1 9 F1 ")
        .append(" C1 8 F1 ")
        .append(" C1 7 F1 ")
        .append(" C1 6 F1 ")
        .append(" C1 5 F1 ")
        .append(" C1 4 F1 ")
        .append(" C1 3 F1 ")
        .append(" C1 2 F1 ")
        .append(" C1 1 F1 ")
        .append(" C2 13 F2 ")
        .append(" C2 12 F2 ")
        .append(" C2 11 F2 ")
        .append(" C2 10 F2 ")
        .append(" C2 9 F2 ")
        .append(" C2 8 F2 ")
        .append(" C2 7 F2 ")
        .append(" C2 6 F2 ")
        .append(" C2 5 F2 ")
        .append(" C2 4 F2 ")
        .append(" C2 3 F2 ")
        .append(" C2 2 F2 ")
        .append(" C2 1 F2 ")
        .append(" C3 13 F3 ")
        .append(" C3 12 F3 ")
        .append(" C3 11 F3 ")
        .append(" C3 10 F3 ")
        .append(" C3 9 F3 ")
        .append(" C3 8 F3 ")
        .append(" C3 7 F3 ")
        .append(" C3 6 F3 ")
        .append(" C3 5 F3 ")
        .append(" C3 4 F3 ")
        .append(" C3 3 F3 ")
        .append(" C3 2 F3 ")
        .append(" C3 1 F3 ")
        .append(" C4 13 F4 ")
        .append(" C4 12 F4 ")
        .append(" C4 11 F4 ")
        .append(" C4 10 F4 ")
        .append(" C4 9 F4 ")
        .append(" C4 8 F4 ")
        .append(" C4 7 F4 ")
        .append(" C4 6 F4 ")
        .append(" C4 5 F4 ")
        .append(" C4 4 F4 ")
        .append(" C4 3 F4 ")
        .append(" C4 2 F4 ")
        .append(" C4 1 F4 ").toString();

    Readable in = new StringReader(commandsToWin);
    Appendable out = new StringBuilder();
    this.control = new SimpleFreecellController(this.freecell, in, out);
    this.control.playGame(this.easyToWinDeck, 4, 1, false);
    assertEquals(""
        + "F1:\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "C1: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠\n"
        + "C2: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "F1: A♠\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "C1: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠\n"
        + "C2: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "F1: A♠, 2♠\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "C1: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠\n"
        + "C2: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "F1: A♠, 2♠, 3♠\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "C1: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠\n"
        + "C2: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "C1: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠\n"
        + "C2: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "C1: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠\n"
        + "C2: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "C1: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠\n"
        + "C2: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "C1: K♠, Q♠, J♠, 10♠, 9♠, 8♠\n"
        + "C2: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "C1: K♠, Q♠, J♠, 10♠, 9♠\n"
        + "C2: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "C1: K♠, Q♠, J♠, 10♠\n"
        + "C2: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "C1: K♠, Q♠, J♠\n"
        + "C2: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "C1: K♠, Q♠\n"
        + "C2: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "C1: K♠\n"
        + "C2: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "C1:\n"
        + "C2: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "C1:\n"
        + "C2: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "C1:\n"
        + "C2: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "C1:\n"
        + "C2: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "C1:\n"
        + "C2: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "C1:\n"
        + "C2: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "C1:\n"
        + "C2: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "C1:\n"
        + "C2: K♥, Q♥, J♥, 10♥, 9♥, 8♥\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "C1:\n"
        + "C2: K♥, Q♥, J♥, 10♥, 9♥\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "C1:\n"
        + "C2: K♥, Q♥, J♥, 10♥\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "C1:\n"
        + "C2: K♥, Q♥, J♥\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "C1:\n"
        + "C2: K♥, Q♥\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "C1:\n"
        + "C2: K♥\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "C1:\n"
        + "C2:\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
        + "F3: A♦\n"
        + "F4:\n"
        + "O1:\n"
        + "C1:\n"
        + "C2:\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
        + "F3: A♦, 2♦\n"
        + "F4:\n"
        + "O1:\n"
        + "C1:\n"
        + "C2:\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
        + "F3: A♦, 2♦, 3♦\n"
        + "F4:\n"
        + "O1:\n"
        + "C1:\n"
        + "C2:\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
        + "F3: A♦, 2♦, 3♦, 4♦\n"
        + "F4:\n"
        + "O1:\n"
        + "C1:\n"
        + "C2:\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
        + "F3: A♦, 2♦, 3♦, 4♦, 5♦\n"
        + "F4:\n"
        + "O1:\n"
        + "C1:\n"
        + "C2:\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
        + "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦\n"
        + "F4:\n"
        + "O1:\n"
        + "C1:\n"
        + "C2:\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
        + "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦\n"
        + "F4:\n"
        + "O1:\n"
        + "C1:\n"
        + "C2:\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
        + "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦\n"
        + "F4:\n"
        + "O1:\n"
        + "C1:\n"
        + "C2:\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
        + "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦\n"
        + "F4:\n"
        + "O1:\n"
        + "C1:\n"
        + "C2:\n"
        + "C3: K♦, Q♦, J♦, 10♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
        + "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦\n"
        + "F4:\n"
        + "O1:\n"
        + "C1:\n"
        + "C2:\n"
        + "C3: K♦, Q♦, J♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
        + "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦\n"
        + "F4:\n"
        + "O1:\n"
        + "C1:\n"
        + "C2:\n"
        + "C3: K♦, Q♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
        + "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦\n"
        + "F4:\n"
        + "O1:\n"
        + "C1:\n"
        + "C2:\n"
        + "C3: K♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
        + "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
        + "F4:\n"
        + "O1:\n"
        + "C1:\n"
        + "C2:\n"
        + "C3:\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
        + "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
        + "F4: A♣\n"
        + "O1:\n"
        + "C1:\n"
        + "C2:\n"
        + "C3:\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
        + "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
        + "F4: A♣, 2♣\n"
        + "O1:\n"
        + "C1:\n"
        + "C2:\n"
        + "C3:\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
        + "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
        + "F4: A♣, 2♣, 3♣\n"
        + "O1:\n"
        + "C1:\n"
        + "C2:\n"
        + "C3:\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
        + "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
        + "F4: A♣, 2♣, 3♣, 4♣\n"
        + "O1:\n"
        + "C1:\n"
        + "C2:\n"
        + "C3:\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
        + "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
        + "F4: A♣, 2♣, 3♣, 4♣, 5♣\n"
        + "O1:\n"
        + "C1:\n"
        + "C2:\n"
        + "C3:\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
        + "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
        + "F4: A♣, 2♣, 3♣, 4♣, 5♣, 6♣\n"
        + "O1:\n"
        + "C1:\n"
        + "C2:\n"
        + "C3:\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
        + "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
        + "F4: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣\n"
        + "O1:\n"
        + "C1:\n"
        + "C2:\n"
        + "C3:\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
        + "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
        + "F4: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣\n"
        + "O1:\n"
        + "C1:\n"
        + "C2:\n"
        + "C3:\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
        + "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
        + "F4: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣\n"
        + "O1:\n"
        + "C1:\n"
        + "C2:\n"
        + "C3:\n"
        + "C4: K♣, Q♣, J♣, 10♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
        + "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
        + "F4: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣\n"
        + "O1:\n"
        + "C1:\n"
        + "C2:\n"
        + "C3:\n"
        + "C4: K♣, Q♣, J♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
        + "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
        + "F4: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣\n"
        + "O1:\n"
        + "C1:\n"
        + "C2:\n"
        + "C3:\n"
        + "C4: K♣, Q♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
        + "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
        + "F4: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣\n"
        + "O1:\n"
        + "C1:\n"
        + "C2:\n"
        + "C3:\n"
        + "C4: K♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
        + "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
        + "F4: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n"
        + "O1:\n"
        + "C1:\n"
        + "C2:\n"
        + "C3:\n"
        + "C4:\n"
        + "\n"
        + "Game over.", out.toString());
  }

  // using my mock model
  @Test(expected = IllegalArgumentException.class)
  public void testPlayGameWithMockModelErrorGetDeck() {
    FreecellModel<ICard> model = new MockModel();
    Readable in = new StringReader("c q");
    Appendable out = new StringBuilder();
    this.control = new SimpleFreecellController(model, in, out);
    // should not work because the mock model's getDeck function returns a null deck
    this.control.playGame(model.getDeck(), 4, 1, false);
  }

  @Test
  public void testPlayGameWithMockModelMoveErrorMessage() {
    FreecellModel<ICard> model = new MockModel();
    Readable in = new StringReader("C1 13 O1 q");
    Appendable out = new StringBuilder();
    this.control = new SimpleFreecellController(model, in, out);
    // start game should pass because its just taking that from the Regular SimpleFreecellModel
    // which we tested in the tests above.
    this.control.playGame(this.easyToWinDeck, 4, 1, false);
    assertEquals(new StringBuilder()
        .append("F1:\n")
        .append("F2:\n")
        .append("F3:\n")
        .append("F4:\n")
        .append("O1:\n")
        .append("C1: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠\n")
        .append("C2: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n")
        .append("C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n")
        .append("C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n")
        .append("This mock should cause the playGame method to throw an ")
        .append("IllegalArgumentException and display this message.\n")
        .append("Game quit prematurely.").toString(), out.toString());
  }

  @Test
  public void testPlayGameViewShowsEmptyStringPlusCouldNotBeStartedWhenStartGameCalledAndFailed()
      throws IOException {
    FreecellModel<ICard> model = new SimpleFreecellModel();
    Readable in = new StringReader("C1 13 O1 q");
    Appendable out = new StringBuilder();
    this.control = new SimpleFreecellController(model, in, out);
    FreecellView view = new FreecellTextView(model, out);
    try {
      model.startGame(this.easyToWinDeck, 8, -1, false);
    } catch (IllegalArgumentException invalidDeck) {
      view.renderBoard();
      assertEquals("", out.toString());
      this.control.playGame(this.easyToWinDeck, 8, -1, false);
      assertEquals("Could not start game.", out.toString());
    }
  }

  @Test
  public void testPlayGameGameOverWorksEvenWhenHadInvalidInputs() {

    String commandsToWin = new StringBuilder()
        .append(" C1 13 F1 ").append(" fa i l ed input")
        .append(" C1 12 F1 ")
        .append(" C1 wrongCardIndex 11 F1 ")
        .append(" C1 10 F1 ")
        .append(" C1 9 lastErrorBeforeContinueOntoWinGame F1 ")
        .append(" C1 8 F1 ")
        .append(" C1 7 F1 ")
        .append(" C1 6 F1 ")
        .append(" C1 5 F1 ")
        .append(" C1 4 F1 ")
        .append(" C1 3 F1 ")
        .append(" C1 2 F1 ")
        .append(" C1 1 F1 ")
        .append(" C2 13 F2 ")
        .append(" C2 12 F2 ")
        .append(" C2 11 F2 ")
        .append(" C2 10 F2 ")
        .append(" C2 9 F2 ")
        .append(" C2 8 F2 ")
        .append(" C2 7 F2 ")
        .append(" C2 6 F2 ")
        .append(" C2 5 F2 ")
        .append(" C2 4 F2 ")
        .append(" C2 3 F2 ")
        .append(" C2 2 F2 ")
        .append(" C2 1 F2 ")
        .append(" C3 13 F3 ")
        .append(" C3 12 F3 ")
        .append(" C3 11 F3 ")
        .append(" C3 10 F3 ")
        .append(" C3 9 F3 ")
        .append(" C3 8 F3 ")
        .append(" C3 7 F3 ")
        .append(" C3 6 F3 ")
        .append(" C3 5 F3 ")
        .append(" C3 4 F3 ")
        .append(" C3 3 F3 ")
        .append(" C3 2 F3 ")
        .append(" C3 1 F3 ")
        .append(" C4 13 F4 ")
        .append(" C4 12 F4 ")
        .append(" C4 11 F4 ")
        .append(" C4 10 F4 ")
        .append(" C4 9 F4 ")
        .append(" C4 8 F4 ")
        .append(" C4 7 F4 ")
        .append(" C4 6 F4 ")
        .append(" C4 5 F4 ")
        .append(" C4 4 F4 ")
        .append(" C4 3 F4 ")
        .append(" C4 2 F4 ")
        .append(" C4 1 F4 ").toString();

    Readable in = new StringReader(commandsToWin);
    Appendable out = new StringBuilder();
    this.control = new SimpleFreecellController(this.freecell, in, out);
    this.control.playGame(this.easyToWinDeck, 4, 1, false);
    assertEquals(""
        + "F1:\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "C1: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠\n"
        + "C2: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "F1: A♠\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "C1: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠\n"
        + "C2: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "Invalid Source Pile Type Input, Please Input Again.\n"
        + "Invalid Source Pile, To Identify Pile must have the 1st character be the Pile Type and "
        + "the 2nd character be the Pile Index. Try Again.\n"
        + "Invalid Source Pile, To Identify Pile must have the 1st character be the Pile Type and "
        + "the 2nd character be the Pile Index. Try Again.\n"
        + "Invalid Source Pile Type Input, Please Input Again.\n"
        + "Invalid Source Pile Type Input, Please Input Again.\n"
        + "F1: A♠, 2♠\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "C1: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠\n"
        + "C2: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "Invalid Input for Card Index in Pile. Please Reenter Card Index.\n"
        + "F1: A♠, 2♠, 3♠\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "C1: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠\n"
        + "C2: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "C1: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠\n"
        + "C2: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "Invalid Destination Pile Type Input, Please Input Again.\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "C1: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠\n"
        + "C2: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "C1: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠\n"
        + "C2: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "C1: K♠, Q♠, J♠, 10♠, 9♠, 8♠\n"
        + "C2: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "C1: K♠, Q♠, J♠, 10♠, 9♠\n"
        + "C2: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "C1: K♠, Q♠, J♠, 10♠\n"
        + "C2: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "C1: K♠, Q♠, J♠\n"
        + "C2: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "C1: K♠, Q♠\n"
        + "C2: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "C1: K♠\n"
        + "C2: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "C1:\n"
        + "C2: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "C1:\n"
        + "C2: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "C1:\n"
        + "C2: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "C1:\n"
        + "C2: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "C1:\n"
        + "C2: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "C1:\n"
        + "C2: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "C1:\n"
        + "C2: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "C1:\n"
        + "C2: K♥, Q♥, J♥, 10♥, 9♥, 8♥\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "C1:\n"
        + "C2: K♥, Q♥, J♥, 10♥, 9♥\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "C1:\n"
        + "C2: K♥, Q♥, J♥, 10♥\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "C1:\n"
        + "C2: K♥, Q♥, J♥\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "C1:\n"
        + "C2: K♥, Q♥\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "C1:\n"
        + "C2: K♥\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "C1:\n"
        + "C2:\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
        + "F3: A♦\n"
        + "F4:\n"
        + "O1:\n"
        + "C1:\n"
        + "C2:\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
        + "F3: A♦, 2♦\n"
        + "F4:\n"
        + "O1:\n"
        + "C1:\n"
        + "C2:\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
        + "F3: A♦, 2♦, 3♦\n"
        + "F4:\n"
        + "O1:\n"
        + "C1:\n"
        + "C2:\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
        + "F3: A♦, 2♦, 3♦, 4♦\n"
        + "F4:\n"
        + "O1:\n"
        + "C1:\n"
        + "C2:\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
        + "F3: A♦, 2♦, 3♦, 4♦, 5♦\n"
        + "F4:\n"
        + "O1:\n"
        + "C1:\n"
        + "C2:\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
        + "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦\n"
        + "F4:\n"
        + "O1:\n"
        + "C1:\n"
        + "C2:\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
        + "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦\n"
        + "F4:\n"
        + "O1:\n"
        + "C1:\n"
        + "C2:\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
        + "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦\n"
        + "F4:\n"
        + "O1:\n"
        + "C1:\n"
        + "C2:\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
        + "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦\n"
        + "F4:\n"
        + "O1:\n"
        + "C1:\n"
        + "C2:\n"
        + "C3: K♦, Q♦, J♦, 10♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
        + "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦\n"
        + "F4:\n"
        + "O1:\n"
        + "C1:\n"
        + "C2:\n"
        + "C3: K♦, Q♦, J♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
        + "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦\n"
        + "F4:\n"
        + "O1:\n"
        + "C1:\n"
        + "C2:\n"
        + "C3: K♦, Q♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
        + "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦\n"
        + "F4:\n"
        + "O1:\n"
        + "C1:\n"
        + "C2:\n"
        + "C3: K♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
        + "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
        + "F4:\n"
        + "O1:\n"
        + "C1:\n"
        + "C2:\n"
        + "C3:\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
        + "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
        + "F4: A♣\n"
        + "O1:\n"
        + "C1:\n"
        + "C2:\n"
        + "C3:\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
        + "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
        + "F4: A♣, 2♣\n"
        + "O1:\n"
        + "C1:\n"
        + "C2:\n"
        + "C3:\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
        + "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
        + "F4: A♣, 2♣, 3♣\n"
        + "O1:\n"
        + "C1:\n"
        + "C2:\n"
        + "C3:\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
        + "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
        + "F4: A♣, 2♣, 3♣, 4♣\n"
        + "O1:\n"
        + "C1:\n"
        + "C2:\n"
        + "C3:\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
        + "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
        + "F4: A♣, 2♣, 3♣, 4♣, 5♣\n"
        + "O1:\n"
        + "C1:\n"
        + "C2:\n"
        + "C3:\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
        + "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
        + "F4: A♣, 2♣, 3♣, 4♣, 5♣, 6♣\n"
        + "O1:\n"
        + "C1:\n"
        + "C2:\n"
        + "C3:\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
        + "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
        + "F4: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣\n"
        + "O1:\n"
        + "C1:\n"
        + "C2:\n"
        + "C3:\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
        + "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
        + "F4: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣\n"
        + "O1:\n"
        + "C1:\n"
        + "C2:\n"
        + "C3:\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
        + "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
        + "F4: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣\n"
        + "O1:\n"
        + "C1:\n"
        + "C2:\n"
        + "C3:\n"
        + "C4: K♣, Q♣, J♣, 10♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
        + "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
        + "F4: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣\n"
        + "O1:\n"
        + "C1:\n"
        + "C2:\n"
        + "C3:\n"
        + "C4: K♣, Q♣, J♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
        + "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
        + "F4: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣\n"
        + "O1:\n"
        + "C1:\n"
        + "C2:\n"
        + "C3:\n"
        + "C4: K♣, Q♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
        + "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
        + "F4: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣\n"
        + "O1:\n"
        + "C1:\n"
        + "C2:\n"
        + "C3:\n"
        + "C4: K♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
        + "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
        + "F4: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n"
        + "O1:\n"
        + "C1:\n"
        + "C2:\n"
        + "C3:\n"
        + "C4:\n"
        + "\n"
        + "Game over.", out.toString());
  }

  // Tests for using MultiMoveFreecellModel instead of SimpleFreecellModel

  @Test
  public void testMultiMoveFreecellModelPlayGameExceptionInvalidBuildInvalidSuit() {
    FreecellModel<ICard> model = new MultiMoveFreecellModel();
    Readable in = new StringReader("C1 3 C4 q");
    Appendable out = new StringBuilder();
    this.control = new SimpleFreecellController(model, in, out);
    this.control.playGame(this.deckCopy, 12, 4, false);
    assertEquals(new StringBuilder()
        .append("F1:\n")
        .append("F2:\n")
        .append("F3:\n")
        .append("F4:\n")
        .append("O1:\n")
        .append("O2:\n")
        .append("O3:\n")
        .append("O4:\n")
        .append("C1: A♣, K♣, Q♦, J♥, 10♠\n")
        .append("C2: 2♣, A♦, K♦, Q♥, J♠\n")
        .append("C3: 3♣, 2♦, A♥, K♥, Q♠\n")
        .append("C4: 4♣, 3♦, 2♥, A♠, K♠\n")
        .append("C5: 5♣, 4♦, 3♥, 2♠\n")
        .append("C6: 6♣, 5♦, 4♥, 3♠\n")
        .append("C7: 7♣, 6♦, 5♥, 4♠\n")
        .append("C8: 8♣, 7♦, 6♥, 5♠\n")
        .append("C9: 9♣, 8♦, 7♥, 6♠\n")
        .append("C10: 10♣, 9♦, 8♥, 7♠\n")
        .append("C11: J♣, 10♦, 9♥, 8♠\n")
        .append("C12: Q♣, J♦, 10♥, 9♠\n")
        .append(
            "Invalid Card Build. Must have alternating colored Cards while having every")
        .append(" Card under the source Card have a value of 1 less than the card above it.\n")
        .append("Game quit prematurely.").toString(), out.toString());
  }

  @Test
  public void testMultiMoveFreecellModelPlayGameExceptionInvalidBuildInvalidValues() {
    FreecellModel<ICard> model = new MultiMoveFreecellModel();
    Readable in = new StringReader("C1 1 C5 q");
    Appendable out = new StringBuilder();
    this.control = new SimpleFreecellController(model, in, out);
    this.control.playGame(this.deckCopy, 12, 4, false);
    assertEquals(new StringBuilder()
        .append("F1:\n")
        .append("F2:\n")
        .append("F3:\n")
        .append("F4:\n")
        .append("O1:\n")
        .append("O2:\n")
        .append("O3:\n")
        .append("O4:\n")
        .append("C1: A♣, K♣, Q♦, J♥, 10♠\n")
        .append("C2: 2♣, A♦, K♦, Q♥, J♠\n")
        .append("C3: 3♣, 2♦, A♥, K♥, Q♠\n")
        .append("C4: 4♣, 3♦, 2♥, A♠, K♠\n")
        .append("C5: 5♣, 4♦, 3♥, 2♠\n")
        .append("C6: 6♣, 5♦, 4♥, 3♠\n")
        .append("C7: 7♣, 6♦, 5♥, 4♠\n")
        .append("C8: 8♣, 7♦, 6♥, 5♠\n")
        .append("C9: 9♣, 8♦, 7♥, 6♠\n")
        .append("C10: 10♣, 9♦, 8♥, 7♠\n")
        .append("C11: J♣, 10♦, 9♥, 8♠\n")
        .append("C12: Q♣, J♦, 10♥, 9♠\n")
        .append(
            "Invalid Card Build. Must have alternating colored Cards while having every")
        .append(" Card under the source Card have a value of 1 less than the card above it.\n")
        .append("Game quit prematurely.").toString(), out.toString());
  }

  @Test
  public void testMultiMoveFreecellModelPlayGameExceptionInvalidBuildInvalidDestinationCard() {
    FreecellModel<ICard> model = new MultiMoveFreecellModel();
    Readable in = new StringReader("C7 1 C2 q");
    Appendable out = new StringBuilder();
    this.control = new SimpleFreecellController(model, in, out);
    this.control.playGame(this.deckCopy, 12, 4, false);
    assertEquals(new StringBuilder()
        .append("F1:\n")
        .append("F2:\n")
        .append("F3:\n")
        .append("F4:\n")
        .append("O1:\n")
        .append("O2:\n")
        .append("O3:\n")
        .append("O4:\n")
        .append("C1: A♣, K♣, Q♦, J♥, 10♠\n")
        .append("C2: 2♣, A♦, K♦, Q♥, J♠\n")
        .append("C3: 3♣, 2♦, A♥, K♥, Q♠\n")
        .append("C4: 4♣, 3♦, 2♥, A♠, K♠\n")
        .append("C5: 5♣, 4♦, 3♥, 2♠\n")
        .append("C6: 6♣, 5♦, 4♥, 3♠\n")
        .append("C7: 7♣, 6♦, 5♥, 4♠\n")
        .append("C8: 8♣, 7♦, 6♥, 5♠\n")
        .append("C9: 9♣, 8♦, 7♥, 6♠\n")
        .append("C10: 10♣, 9♦, 8♥, 7♠\n")
        .append("C11: J♣, 10♦, 9♥, 8♠\n")
        .append("C12: Q♣, J♦, 10♥, 9♠\n")
        .append(
            "Invalid Card Build. Must have alternating colored Cards while having every")
        .append(" Card under the source Card have a value of 1 less than the card above it.\n")
        .append("Game quit prematurely.").toString(), out.toString());
  }


  @Test
  public void testMultiMoveFreecellModelPlayGameGameOverWithErrorsInBuildAndOthers() {
    FreecellModel<ICard> model = new MultiMoveFreecellModel();
    Readable in = new StringReader("C11 3 F1 q");
    Appendable out = new StringBuilder();
    this.control = new SimpleFreecellController(model, in, out);
    this.control.playGame(this.deckCopy, 12, 4, false);
    assertEquals(new StringBuilder()
        .append("F1:\n")
        .append("F2:\n")
        .append("F3:\n")
        .append("F4:\n")
        .append("O1:\n")
        .append("O2:\n")
        .append("O3:\n")
        .append("O4:\n")
        .append("C1: A♣, K♣, Q♦, J♥, 10♠\n")
        .append("C2: 2♣, A♦, K♦, Q♥, J♠\n")
        .append("C3: 3♣, 2♦, A♥, K♥, Q♠\n")
        .append("C4: 4♣, 3♦, 2♥, A♠, K♠\n")
        .append("C5: 5♣, 4♦, 3♥, 2♠\n")
        .append("C6: 6♣, 5♦, 4♥, 3♠\n")
        .append("C7: 7♣, 6♦, 5♥, 4♠\n")
        .append("C8: 8♣, 7♦, 6♥, 5♠\n")
        .append("C9: 9♣, 8♦, 7♥, 6♠\n")
        .append("C10: 10♣, 9♦, 8♥, 7♠\n")
        .append("C11: J♣, 10♦, 9♥, 8♠\n")
        .append("C12: Q♣, J♦, 10♥, 9♠\n")
        .append(
            "Moving Multiple Cards requires both Source and Destination Piles")
        .append(" to be Cascade Piles\n")
        .append("Game quit prematurely.").toString(), out.toString());
  }

  @Test
  public void testMultiMoveModelPlayGameGameOverWorksEvenWhenHadInvalidBuildsAndInputs() {

    String commandsToWin = new StringBuilder()
        .append(" C1 13 F1 ").append(" fa i l ed input")
        .append(" C1 12 F1 ")
        .append(" C1 wrongCardIndex 11 F1 ")
        .append(" C1 10 F1 ")
        .append(" C1 9 lastErrorBeforeContinueOntoWinGame F1 ")
        .append(" C1 8 F1 ")
        .append(" C1 7 F1 ")
        .append(" C1 6 F1 ")
        .append(" C1 5 F1 ").append(" C1 1 F2 ") // invalid build error shown there
        .append(" C1 4 F1 ")
        .append(" C1 3 F1 ")
        .append(" C1 2 F1 ")
        .append(" C1 1 F1 ")
        // first put the ace of clubs on the 2 of Hearts
        // then try putting that build on something that doesnt work
        // then put the Ace of Clubs in the Foundation Pile
        .append(" C2 13 F2 ").append(" C4 13 C2 ").append(" C2 12 C3 ").append(" C2 13 F4 ")
        .append(" C2 12 F2 ")
        .append(" C2 11 F2 ")
        .append(" C2 10 F2 ")
        .append(" C2 9 F2 ")
        .append(" C2 8 F2 ")
        .append(" C2 7 F2 ")
        .append(" C2 6 F2 ")
        .append(" C2 5 F2 ")
        .append(" C2 4 F2 ")
        .append(" C2 3 F2 ")
        .append(" C2 2 F2 ")
        .append(" C2 1 F2 ")
        .append(" C3 13 F3 ")
        .append(" C3 12 F3 ")
        .append(" C3 11 F3 ")
        .append(" C3 10 F3 ")
        .append(" C3 9 F3 ")
        .append(" C3 8 F3 ")
        .append(" C3 7 F3 ")
        .append(" C3 6 F3 ")
        .append(" C3 5 F3 ")
        .append(" C3 4 F3 ")
        .append(" C3 3 F3 ")
        .append(" C3 2 F3 ")
        .append(" C3 1 F3 ")
        .append(" C4 13 F4 ")
        .append(" C4 12 F4 ")
        .append(" C4 11 F4 ")
        .append(" C4 10 F4 ")
        .append(" C4 9 F4 ")
        .append(" C4 8 F4 ")
        .append(" C4 7 F4 ")
        .append(" C4 6 F4 ")
        .append(" C4 5 F4 ")
        .append(" C4 4 F4 ")
        .append(" C4 3 F4 ")
        .append(" C4 2 F4 ")
        .append(" C4 1 F4 q").toString();

    Readable in = new StringReader(commandsToWin);
    Appendable out = new StringBuilder();
    this.control = new SimpleFreecellController(new MultiMoveFreecellModel(), in, out);
    this.control.playGame(this.easyToWinDeck, 4, 1, false);
    assertEquals(""
        + "F1:\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "C1: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠\n"
        + "C2: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "F1: A♠\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "C1: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠\n"
        + "C2: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "Invalid Source Pile Type Input, Please Input Again.\n"
        + "Invalid Source Pile, To Identify Pile must have the 1st character be the Pile Type "
        + "and the 2nd character be the Pile Index. Try Again.\n"
        + "Invalid Source Pile, To Identify Pile must have the 1st character be the Pile Type and "
        + "the 2nd character be the Pile Index. Try Again.\n"
        + "Invalid Source Pile Type Input, Please Input Again.\n"
        + "Invalid Source Pile Type Input, Please Input Again.\n"
        + "F1: A♠, 2♠\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "C1: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠\n"
        + "C2: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "Invalid Input for Card Index in Pile. Please Reenter Card Index.\n"
        + "F1: A♠, 2♠, 3♠\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "C1: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠\n"
        + "C2: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "C1: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠\n"
        + "C2: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "Invalid Destination Pile Type Input, Please Input Again.\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "C1: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠\n"
        + "C2: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "C1: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠\n"
        + "C2: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "C1: K♠, Q♠, J♠, 10♠, 9♠, 8♠\n"
        + "C2: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "C1: K♠, Q♠, J♠, 10♠, 9♠\n"
        + "C2: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "C1: K♠, Q♠, J♠, 10♠\n"
        + "C2: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "Invalid Card Build. Not enough intermediate spots available\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "C1: K♠, Q♠, J♠\n"
        + "C2: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "C1: K♠, Q♠\n"
        + "C2: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "C1: K♠\n"
        + "C2: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "C1:\n"
        + "C2: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "C1:\n"
        + "C2: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "C1:\n"
        + "C2: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♣\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣\n"
        + "Invalid Move, Destination Card needs to have a Value of 1 more "
        + "than the source Card and be a different colored Suit.\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥\n"
        + "F3:\n"
        + "F4: A♣\n"
        + "O1:\n"
        + "C1:\n"
        + "C2: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥\n"
        + "F3:\n"
        + "F4: A♣\n"
        + "O1:\n"
        + "C1:\n"
        + "C2: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥\n"
        + "F3:\n"
        + "F4: A♣\n"
        + "O1:\n"
        + "C1:\n"
        + "C2: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥\n"
        + "F3:\n"
        + "F4: A♣\n"
        + "O1:\n"
        + "C1:\n"
        + "C2: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥\n"
        + "F3:\n"
        + "F4: A♣\n"
        + "O1:\n"
        + "C1:\n"
        + "C2: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥\n"
        + "F3:\n"
        + "F4: A♣\n"
        + "O1:\n"
        + "C1:\n"
        + "C2: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥\n"
        + "F3:\n"
        + "F4: A♣\n"
        + "O1:\n"
        + "C1:\n"
        + "C2: K♥, Q♥, J♥, 10♥, 9♥, 8♥\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥\n"
        + "F3:\n"
        + "F4: A♣\n"
        + "O1:\n"
        + "C1:\n"
        + "C2: K♥, Q♥, J♥, 10♥, 9♥\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥\n"
        + "F3:\n"
        + "F4: A♣\n"
        + "O1:\n"
        + "C1:\n"
        + "C2: K♥, Q♥, J♥, 10♥\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥\n"
        + "F3:\n"
        + "F4: A♣\n"
        + "O1:\n"
        + "C1:\n"
        + "C2: K♥, Q♥, J♥\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥\n"
        + "F3:\n"
        + "F4: A♣\n"
        + "O1:\n"
        + "C1:\n"
        + "C2: K♥, Q♥\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥\n"
        + "F3:\n"
        + "F4: A♣\n"
        + "O1:\n"
        + "C1:\n"
        + "C2: K♥\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
        + "F3:\n"
        + "F4: A♣\n"
        + "O1:\n"
        + "C1:\n"
        + "C2:\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
        + "F3: A♦\n"
        + "F4: A♣\n"
        + "O1:\n"
        + "C1:\n"
        + "C2:\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
        + "F3: A♦, 2♦\n"
        + "F4: A♣\n"
        + "O1:\n"
        + "C1:\n"
        + "C2:\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
        + "F3: A♦, 2♦, 3♦\n"
        + "F4: A♣\n"
        + "O1:\n"
        + "C1:\n"
        + "C2:\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
        + "F3: A♦, 2♦, 3♦, 4♦\n"
        + "F4: A♣\n"
        + "O1:\n"
        + "C1:\n"
        + "C2:\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
        + "F3: A♦, 2♦, 3♦, 4♦, 5♦\n"
        + "F4: A♣\n"
        + "O1:\n"
        + "C1:\n"
        + "C2:\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
        + "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦\n"
        + "F4: A♣\n"
        + "O1:\n"
        + "C1:\n"
        + "C2:\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
        + "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦\n"
        + "F4: A♣\n"
        + "O1:\n"
        + "C1:\n"
        + "C2:\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦, 8♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
        + "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦\n"
        + "F4: A♣\n"
        + "O1:\n"
        + "C1:\n"
        + "C2:\n"
        + "C3: K♦, Q♦, J♦, 10♦, 9♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
        + "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦\n"
        + "F4: A♣\n"
        + "O1:\n"
        + "C1:\n"
        + "C2:\n"
        + "C3: K♦, Q♦, J♦, 10♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
        + "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦\n"
        + "F4: A♣\n"
        + "O1:\n"
        + "C1:\n"
        + "C2:\n"
        + "C3: K♦, Q♦, J♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
        + "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦\n"
        + "F4: A♣\n"
        + "O1:\n"
        + "C1:\n"
        + "C2:\n"
        + "C3: K♦, Q♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
        + "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦\n"
        + "F4: A♣\n"
        + "O1:\n"
        + "C1:\n"
        + "C2:\n"
        + "C3: K♦\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
        + "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
        + "F4: A♣\n"
        + "O1:\n"
        + "C1:\n"
        + "C2:\n"
        + "C3:\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣\n"
        + "Invalid Cascade Card Index\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
        + "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
        + "F4: A♣, 2♣\n"
        + "O1:\n"
        + "C1:\n"
        + "C2:\n"
        + "C3:\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
        + "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
        + "F4: A♣, 2♣, 3♣\n"
        + "O1:\n"
        + "C1:\n"
        + "C2:\n"
        + "C3:\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
        + "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
        + "F4: A♣, 2♣, 3♣, 4♣\n"
        + "O1:\n"
        + "C1:\n"
        + "C2:\n"
        + "C3:\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
        + "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
        + "F4: A♣, 2♣, 3♣, 4♣, 5♣\n"
        + "O1:\n"
        + "C1:\n"
        + "C2:\n"
        + "C3:\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
        + "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
        + "F4: A♣, 2♣, 3♣, 4♣, 5♣, 6♣\n"
        + "O1:\n"
        + "C1:\n"
        + "C2:\n"
        + "C3:\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
        + "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
        + "F4: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣\n"
        + "O1:\n"
        + "C1:\n"
        + "C2:\n"
        + "C3:\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣, 8♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
        + "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
        + "F4: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣\n"
        + "O1:\n"
        + "C1:\n"
        + "C2:\n"
        + "C3:\n"
        + "C4: K♣, Q♣, J♣, 10♣, 9♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
        + "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
        + "F4: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣\n"
        + "O1:\n"
        + "C1:\n"
        + "C2:\n"
        + "C3:\n"
        + "C4: K♣, Q♣, J♣, 10♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
        + "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
        + "F4: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣\n"
        + "O1:\n"
        + "C1:\n"
        + "C2:\n"
        + "C3:\n"
        + "C4: K♣, Q♣, J♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
        + "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
        + "F4: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣\n"
        + "O1:\n"
        + "C1:\n"
        + "C2:\n"
        + "C3:\n"
        + "C4: K♣, Q♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
        + "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
        + "F4: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣\n"
        + "O1:\n"
        + "C1:\n"
        + "C2:\n"
        + "C3:\n"
        + "C4: K♣\n"
        + "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
        + "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
        + "F4: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n"
        + "O1:\n"
        + "C1:\n"
        + "C2:\n"
        + "C3:\n"
        + "C4:\n"
        + "\n"
        + "Game over.", out.toString());
  }

}