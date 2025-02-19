import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import cs3500.freecell.model.FreecellModel;
import cs3500.freecell.model.FreecellModelCreator;
import cs3500.freecell.model.PileType;
import cs3500.freecell.model.hw02.Card;
import cs3500.freecell.model.hw02.GameState;
import cs3500.freecell.model.hw02.ICard;
import cs3500.freecell.model.hw02.SimpleFreecellModel;
import cs3500.freecell.model.hw02.Suit;
import cs3500.freecell.model.hw02.Value;
import cs3500.freecell.model.hw04.MultiMoveFreecellModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests that could be abstracted from the FreecellModel Classes. These are tests that should fit
 * for both SimpleFreecellModel and MultiMoveFreecellModel implementations. Since the only
 * difference between these 2 models are the way the move function works, the tests shown here are
 * abstracted out of both test classes to make sure that they work properly for both implementations
 * for FreecellModel. Tests specific to each model are declared in their corresponding test
 * classes.
 */
public abstract class AbstractFreecellModelTest {

  protected FreecellModel<ICard> freecellModel = new SimpleFreecellModel();
  protected FreecellModel<ICard> exampleGameGivenPossibleBeginning = new SimpleFreecellModel(
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

  protected FreecellModel<ICard> exampleGameGivenNotStarted = new SimpleFreecellModel(
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

  protected FreecellModel<ICard> exampleGameGivenAlreadyStarted = new SimpleFreecellModel(
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

  protected FreecellModel<ICard> exampleGameGivenAlmostFinished = new MultiMoveFreecellModel(
      GameState.PLAYING,
      new ArrayList<ArrayList<ICard>>(
          Arrays.asList(this.cAllCards, this.dAllCards, this.hAllCards,
              this.sAllCards)),
      new ArrayList<ArrayList<ICard>>(Arrays
          .asList(this.cOnlyAceList, new ArrayList<ICard>(), new ArrayList<ICard>(),
              new ArrayList<ICard>())),
      new ArrayList<ArrayList<ICard>>(Arrays.asList(this.o1PileSampleGameGiven)));

  protected FreecellModel<ICard> exampleGameGivenFinished = new SimpleFreecellModel(
      GameState.FINISHED,
      new ArrayList<ArrayList<ICard>>(Arrays
          .asList(new ArrayList<ICard>(), new ArrayList<ICard>(), new ArrayList<ICard>(),
              new ArrayList<ICard>())),
      new ArrayList<ArrayList<ICard>>(Arrays.asList(new ArrayList<ICard>(), new ArrayList<ICard>(),
          new ArrayList<ICard>(), new ArrayList<ICard>())),
      new ArrayList<ArrayList<ICard>>(Arrays.asList(new ArrayList<ICard>(), new ArrayList<ICard>(),
          new ArrayList<ICard>(), new ArrayList<ICard>())));


  FreecellModelCreator c = new FreecellModelCreator();

  protected final ArrayList<ICard> cAllCards = new ArrayList<ICard>(Arrays
      .asList(this.cAce, this.c2, this.c3, this.c4, this.c5, this.c6, this.c7, this.c8, this.c9,
          this.c10, this.cJack, this.cQueen, this.cKing));

  protected final ArrayList<ICard> cOnlyAceList = new ArrayList<ICard>(Arrays.asList(this.cAce));

  protected final ICard cAce = new Card(Value.ACE, Suit.CLUBS);
  protected final ICard c2 = new Card(Value.TWO, Suit.CLUBS);
  protected final ICard c3 = new Card(Value.THREE, Suit.CLUBS);
  protected final ICard c4 = new Card(Value.FOUR, Suit.CLUBS);
  protected final ICard c5 = new Card(Value.FIVE, Suit.CLUBS);
  protected final ICard c6 = new Card(Value.SIX, Suit.CLUBS);
  protected final ICard c7 = new Card(Value.SEVEN, Suit.CLUBS);
  protected final ICard c8 = new Card(Value.EIGHT, Suit.CLUBS);
  protected final ICard c9 = new Card(Value.NINE, Suit.CLUBS);
  protected final ICard c10 = new Card(Value.TEN, Suit.CLUBS);
  protected final ICard cJack = new Card(Value.JACK, Suit.CLUBS);
  protected final ICard cQueen = new Card(Value.QUEEN, Suit.CLUBS);
  protected final ICard cKing = new Card(Value.KING, Suit.CLUBS);

  protected final ArrayList<ICard> dAllCards = new ArrayList<ICard>(Arrays
      .asList(this.dAce, this.d2, this.d3, this.d4,
          this.d5, this.d6, this.d7, this.d8, this.d9, this.d10, this.dJack, this.dQueen,
          this.dKing));

  protected final ICard dAce = new Card(Value.ACE, Suit.DIAMONDS);
  protected final ICard d2 = new Card(Value.TWO, Suit.DIAMONDS);
  protected final ICard d3 = new Card(Value.THREE, Suit.DIAMONDS);
  protected final ICard d4 = new Card(Value.FOUR, Suit.DIAMONDS);
  protected final ICard d5 = new Card(Value.FIVE, Suit.DIAMONDS);
  protected final ICard d6 = new Card(Value.SIX, Suit.DIAMONDS);
  protected final ICard d7 = new Card(Value.SEVEN, Suit.DIAMONDS);
  protected final ICard d8 = new Card(Value.EIGHT, Suit.DIAMONDS);
  protected final ICard d9 = new Card(Value.NINE, Suit.DIAMONDS);
  protected final ICard d10 = new Card(Value.TEN, Suit.DIAMONDS);
  protected final ICard dJack = new Card(Value.JACK, Suit.DIAMONDS);
  protected final ICard dQueen = new Card(Value.QUEEN, Suit.DIAMONDS);
  protected final ICard dKing = new Card(Value.KING, Suit.DIAMONDS);

  protected final ArrayList<ICard> hAllCards = new ArrayList<ICard>(Arrays
      .asList(this.hAce, this.h2, this.h3, this.h4, this.h5, this.h6, this.h7, this.h8,
          this.h9, this.h10, this.hJack, this.hQueen, this.hKing));

  protected final ICard hAce = new Card(Value.ACE, Suit.HEARTS);
  protected final ICard h2 = new Card(Value.TWO, Suit.HEARTS);
  protected final ICard h3 = new Card(Value.THREE, Suit.HEARTS);
  protected final ICard h4 = new Card(Value.FOUR, Suit.HEARTS);
  protected final ICard h5 = new Card(Value.FIVE, Suit.HEARTS);
  protected final ICard h6 = new Card(Value.SIX, Suit.HEARTS);
  protected final ICard h7 = new Card(Value.SEVEN, Suit.HEARTS);
  protected final ICard h8 = new Card(Value.EIGHT, Suit.HEARTS);
  protected final ICard h9 = new Card(Value.NINE, Suit.HEARTS);
  protected final ICard h10 = new Card(Value.TEN, Suit.HEARTS);
  protected final ICard hJack = new Card(Value.JACK, Suit.HEARTS);
  protected final ICard hQueen = new Card(Value.QUEEN, Suit.HEARTS);
  protected final ICard hKing = new Card(Value.KING, Suit.HEARTS);


  protected final ArrayList<ICard> sAllCards = new ArrayList<ICard>(Arrays
      .asList(this.sAce, this.s2, this.s3, this.s4, this.s5, this.s6, this.s7, this.s8, this.s9,
          this.s10, this.sJack, this.sQueen,
          this.sKing));

  protected final ICard sAce = new Card(Value.ACE, Suit.SPADES);
  protected final ICard s2 = new Card(Value.TWO, Suit.SPADES);
  protected final ICard s3 = new Card(Value.THREE, Suit.SPADES);
  protected final ICard s4 = new Card(Value.FOUR, Suit.SPADES);
  protected final ICard s5 = new Card(Value.FIVE, Suit.SPADES);
  protected final ICard s6 = new Card(Value.SIX, Suit.SPADES);
  protected final ICard s7 = new Card(Value.SEVEN, Suit.SPADES);
  protected final ICard s8 = new Card(Value.EIGHT, Suit.SPADES);
  protected final ICard s9 = new Card(Value.NINE, Suit.SPADES);
  protected final ICard s10 = new Card(Value.TEN, Suit.SPADES);
  protected final ICard sJack = new Card(Value.JACK, Suit.SPADES);
  protected final ICard sQueen = new Card(Value.QUEEN, Suit.SPADES);
  protected final ICard sKing = new Card(Value.KING, Suit.SPADES);


  protected final List<ICard> deckCopy = new ArrayList<ICard>(Arrays
      .asList(this.cAce, this.c2, this.c3, this.c4, this.c5, this.c6, this.c7, this.c8, this.c9,
          this.c10, this.cJack, this.cQueen, this.cKing, this.dAce, this.d2, this.d3, this.d4,
          this.d5, this.d6, this.d7, this.d8, this.d9, this.d10, this.dJack, this.dQueen,
          this.dKing, this.hAce, this.h2, this.h3, this.h4, this.h5, this.h6, this.h7, this.h8,
          this.h9, this.h10, this.hJack, this.hQueen, this.hKing, this.sAce, this.s2, this.s3,
          this.s4, this.s5, this.s6, this.s7, this.s8, this.s9, this.s10, this.sJack, this.sQueen,
          this.sKing));

  // These next examples are just the pile formation for the sample game version view given to
  // us in the instructions of the assignment
  protected final ArrayList<ICard> f1PileSampleGameGiven = new ArrayList<ICard>(
      Arrays.asList(this.sAce));
  protected final ArrayList<ICard> f2PileSampleGameGiven = new ArrayList<ICard>(
      Arrays.asList(this.cAce, this.c2));
  protected final ArrayList<ICard> f3PileSampleGameGiven = new ArrayList<ICard>(
      Arrays.asList(this.hAce, this.h2));
  protected final ArrayList<ICard> f4PileSampleGameGiven = new ArrayList<ICard>(
      Arrays.asList());

  protected final ArrayList<ICard> c1PileSampleGameGiven = new ArrayList<ICard>(
      Arrays.asList(this.sKing, this.d3, this.hKing, this.s8, this.h5, this.d5, this.s5));
  protected final ArrayList<ICard> c2PileSampleGameGiven = new ArrayList<ICard>(
      Arrays.asList(this.c4, this.c5, this.s4, this.d9, this.cKing));
  protected final ArrayList<ICard> c3PileSampleGameGiven = new ArrayList<ICard>(
      Arrays.asList(this.s2, this.s9, this.c8, this.d10, this.h8, this.sQueen, this.dJack));
  protected final ArrayList<ICard> c4PileSampleGameGiven = new ArrayList<ICard>(
      Arrays.asList(this.h10, this.sJack, this.dQueen, this.c6, this.c3, this.cJack));
  protected final ArrayList<ICard> c5PileSampleGameGiven = new ArrayList<ICard>(
      Arrays.asList(this.d6, this.h3, this.s10, this.hQueen, this.s6));
  protected final ArrayList<ICard> c6PileSampleGameGiven = new ArrayList<ICard>(
      Arrays.asList(this.c9, this.c7, this.h7, this.dKing, this.h4, this.s3, this.d2));
  protected final ArrayList<ICard> c7PileSampleGameGiven = new ArrayList<ICard>(
      Arrays.asList(this.cQueen, this.hJack, this.c10, this.h9));
  protected final ArrayList<ICard> c8PileSampleGameGiven = new ArrayList<ICard>(
      Arrays.asList(this.d7, this.s7, this.h6, this.dAce, this.d4));

  protected final ArrayList<ICard> o1PileSampleGameGiven = new ArrayList<ICard>();
  protected final ArrayList<ICard> o2PileSampleGameGiven = new ArrayList<ICard>();
  protected final ArrayList<ICard> o3PileSampleGameGiven = new ArrayList<ICard>();
  protected final ArrayList<ICard> o4PileSampleGameGiven = new ArrayList<ICard>(
      Arrays.asList(this.d8));


  protected final ArrayList<ICard> c2PileSampleGameGivenPossibleBeginning = new ArrayList<ICard>(
      Arrays.asList(this.c4, this.c5, this.s4, this.d9, this.cKing, this.c2, this.sAce));

  protected final ArrayList<ICard> c4PileSampleGameGivenPossibleBeginning = new ArrayList<ICard>(
      Arrays.asList(this.h10, this.sJack, this.dQueen, this.c6, this.c3, this.cJack, this.d2));

  protected final ArrayList<ICard> c5PileSampleGameGivenPossibleBeginning = new ArrayList<ICard>(
      Arrays.asList(this.d6, this.h3, this.s10, this.hQueen, this.s6, this.d8));

  protected final ArrayList<ICard> c6PileSampleGameGivenPossibleBeginning = new ArrayList<ICard>(
      Arrays.asList(this.c9, this.c7, this.h7, this.dKing, this.h4, this.s3));

  protected final ArrayList<ICard> c7PileSampleGameGivenPossibleBeginning = new ArrayList<ICard>(
      Arrays.asList(this.cQueen, this.hJack, this.c10, this.h9, this.cAce, this.h2));

  protected final ArrayList<ICard> c8PileSampleGameGivenPossibleBeginning = new ArrayList<ICard>(
      Arrays.asList(this.d7, this.s7, this.h6, this.dAce, this.d4, this.hAce));

  public final ArrayList<ICard> easyToWinDeck = new ArrayList<ICard>();

  protected final List<ICard> easyTestDeck = new MultiMoveFreecellModel().getDeck();


  @Before
  public void setupDeck() {
    for (Value v : Value.values()) {
      for (Suit s : Suit.values()) {
        this.easyToWinDeck.add(new Card(v, s));
      }
    }
    Collections.reverse(this.easyTestDeck);
    Collections.reverse(this.easyToWinDeck);
  }


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
    // I keep this setup method here empty to override it in my SimpleFreecellTest methods
    // and my MultiMoveFreecellTest methods
  }

  @Test(expected = IllegalStateException.class)
  public void testGetFoundationCardAtExceptionGameNotStarted() {
    this.exampleGameGivenNotStarted.getFoundationCardAt(0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetFoundationCardAtExceptionPileIndexTooSmall() {
    this.exampleGameGivenAlreadyStarted.getFoundationCardAt(-3, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetFoundationCardAtExceptionPileIndexTooLarge() {
    this.exampleGameGivenAlreadyStarted.getFoundationCardAt(5, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetFoundationCardAtExceptionCardIndexTooSmall() {
    this.exampleGameGivenAlreadyStarted.getFoundationCardAt(3, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetFoundationCardAtExceptionCardIndexTooLarge() {
    this.exampleGameGivenAlreadyStarted.getFoundationCardAt(0, 2);
  }


  @Test(expected = IllegalStateException.class)
  public void testGetCascadeCardAtExceptionGameNotStarted() {
    this.exampleGameGivenNotStarted.getCascadeCardAt(0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetCascadeCardAtExceptionPileIndexTooSmall() {
    this.exampleGameGivenAlreadyStarted.getCascadeCardAt(-1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetCascadeCardAtExceptionPileIndexTooLarge() {
    this.exampleGameGivenAlreadyStarted.getCascadeCardAt(8, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetCascadeCardAtExceptionCardIndexTooSmall() {
    this.exampleGameGivenAlreadyStarted.getCascadeCardAt(3, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetCascadeCardAtExceptionCardIndexTooLarge() {
    this.exampleGameGivenAlreadyStarted.getCascadeCardAt(0, 7);
  }


  @Test(expected = IllegalStateException.class)
  public void testGetOpenCardAtExceptionGameNotStarted() {
    this.exampleGameGivenNotStarted.getOpenCardAt(4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetOpenCardAtExceptionPileIndexTooSmall() {
    this.exampleGameGivenAlreadyStarted.getOpenCardAt(-1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetOpenCardAtExceptionPileIndexTooLarge() {
    this.exampleGameGivenAlreadyStarted.getOpenCardAt(5);
  }

  @Test
  public void testGetFoundationCardAtSuccess() {
    assertEquals(new Card(Value.ACE, Suit.SPADES),
        this.exampleGameGivenAlreadyStarted.getFoundationCardAt(0, 0));
    assertEquals(new Card(Value.ACE, Suit.CLUBS),
        this.exampleGameGivenAlreadyStarted.getFoundationCardAt(1, 0));
    assertEquals(new Card(Value.ACE, Suit.HEARTS),
        this.exampleGameGivenAlreadyStarted.getFoundationCardAt(2, 0));
    assertEquals(new Card(Value.TWO, Suit.HEARTS),
        this.exampleGameGivenAlreadyStarted.getFoundationCardAt(2, 1));
  }

  @Test
  public void testGetCascadeCardAtSuccess() {
    assertEquals(new Card(Value.KING, Suit.SPADES),
        this.exampleGameGivenAlreadyStarted.getCascadeCardAt(0, 0));
    assertEquals(new Card(Value.FOUR, Suit.CLUBS),
        this.exampleGameGivenAlreadyStarted.getCascadeCardAt(1, 0));
    assertEquals(new Card(Value.FIVE, Suit.SPADES),
        this.exampleGameGivenAlreadyStarted.getCascadeCardAt(0, 6));
    assertEquals(new Card(Value.FOUR, Suit.DIAMONDS),
        this.exampleGameGivenAlreadyStarted.getCascadeCardAt(7, 4));
  }

  @Test
  public void testGetOpenCardAtSuccess() {
    assertNull(this.exampleGameGivenAlreadyStarted.getOpenCardAt(0));
    assertNull(this.exampleGameGivenAlreadyStarted.getOpenCardAt(1));
    assertNull(this.exampleGameGivenAlreadyStarted.getOpenCardAt(2));
    assertEquals(new Card(Value.EIGHT, Suit.DIAMONDS),
        this.exampleGameGivenAlreadyStarted.getOpenCardAt(3));
  }

  @Test(expected = IllegalStateException.class)
  public void testGetNumCardsInFoundationPileExceptionGameNotStarted() {
    this.freecellModel.getNumCardsInFoundationPile(0);
  }

  @Test(expected = IllegalStateException.class)
  public void testGetNumCardsInCascadePileExceptionGameNotStarted() {
    this.freecellModel.getNumCardsInCascadePile(0);
  }

  @Test(expected = IllegalStateException.class)
  public void testGetNumCardsInOpenPileExceptionGameNotStarted() {
    this.freecellModel.getNumCardsInOpenPile(0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetNumCardsInFoundationPileExceptionInvalidPileIndex() {
    this.exampleGameGivenAlreadyStarted.getNumCardsInFoundationPile(-1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetNumCardsInCascadePileExceptionInvalidPileIndex() {
    this.exampleGameGivenAlreadyStarted.getNumCardsInCascadePile(9);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetNumCardsInOpenPileExceptionInvalidPileIndex() {
    this.exampleGameGivenAlreadyStarted.getNumCardsInOpenPile(5);
  }

  @Test
  public void testGetNumCardsInFoundationPile() {
    // make sure 1 Card in Foundation Pile 1
    assertEquals(1, this.exampleGameGivenAlreadyStarted.getNumCardsInFoundationPile(0));
    // 2 cards in Foundation Pile 2
    assertEquals(2, this.exampleGameGivenAlreadyStarted.getNumCardsInFoundationPile(1));
    // 2 Cards in Foundation Pile 3
    assertEquals(2, this.exampleGameGivenAlreadyStarted.getNumCardsInFoundationPile(2));
    // 0 Cards in Foundation Pile 4
    assertEquals(0, this.exampleGameGivenAlreadyStarted.getNumCardsInFoundationPile(3));
  }

  @Test
  public void testGetNumCardsInCascadePile() {
    // make sure 7 Cards in Cascade Pile 1
    assertEquals(7, this.exampleGameGivenAlreadyStarted.getNumCardsInCascadePile(0));
    // 5 cards in Cascade Pile 2
    assertEquals(5, this.exampleGameGivenAlreadyStarted.getNumCardsInCascadePile(1));
    // 7 Cards in Cascade Pile 3
    assertEquals(7, this.exampleGameGivenAlreadyStarted.getNumCardsInCascadePile(2));
    // 6 Cards in Cascade Pile 4
    assertEquals(6, this.exampleGameGivenAlreadyStarted.getNumCardsInCascadePile(3));
    // 5 Cards in Cascade Pile 5
    assertEquals(5, this.exampleGameGivenAlreadyStarted.getNumCardsInCascadePile(4));
    // 7 cards in Cascade Pile 6
    assertEquals(7, this.exampleGameGivenAlreadyStarted.getNumCardsInCascadePile(5));
    // 4 Cards in Cascade Pile 7
    assertEquals(4, this.exampleGameGivenAlreadyStarted.getNumCardsInCascadePile(6));
    // 5 Cards in Cascade Pile 5
    assertEquals(5, this.exampleGameGivenAlreadyStarted.getNumCardsInCascadePile(7));
  }

  @Test
  public void testGetNumCardsInOpenPile() {
    // make sure 0 Cards in Open Pile 1
    assertEquals(0, this.exampleGameGivenAlreadyStarted.getNumCardsInOpenPile(0));
    // 0 cards in Open Pile 2
    assertEquals(0, this.exampleGameGivenAlreadyStarted.getNumCardsInOpenPile(1));
    // 0 Cards in Open Pile 3
    assertEquals(0, this.exampleGameGivenAlreadyStarted.getNumCardsInOpenPile(2));
    // 1 Card in Open Pile 4
    assertEquals(1, this.exampleGameGivenAlreadyStarted.getNumCardsInOpenPile(3));
  }

  @Test
  public void testGetNumOfCascadePiles() {
    // also make sure returns -1 if game hasn't begun yet
    assertEquals(-1, this.exampleGameGivenNotStarted.getNumCascadePiles());
    assertEquals(-1, this.freecellModel.getNumCascadePiles());
    this.freecellModel.startGame(this.freecellModel.getDeck(), 4, 1, true);
    // make sure works when game has started
    assertEquals(4, this.freecellModel.getNumCascadePiles());
    assertEquals(8, this.exampleGameGivenAlreadyStarted.getNumCascadePiles());
  }

  @Test
  public void testGetNumOfOpenPiles() {
    // also make sure returns -1 if game hasn't begun yet
    assertEquals(-1, this.exampleGameGivenNotStarted.getNumOpenPiles());
    assertEquals(-1, this.freecellModel.getNumOpenPiles());
    this.freecellModel.startGame(this.freecellModel.getDeck(), 4, 1, true);
    // make sure works when game has started
    assertEquals(1, this.freecellModel.getNumOpenPiles());
    assertEquals(4, this.exampleGameGivenAlreadyStarted.getNumOpenPiles());
  }


  @Test
  public void testGetDeckCardsCorrect() {
    for (int i = 0; i < 52; i++) {
      assertEquals(deckCopy.get(i), this.freecellModel.getDeck().get(i));
    }
  }

  @Test
  public void testGetDeckSizeCorrect() {
    assertEquals(52, this.freecellModel.getDeck().size());
    assertEquals(52, this.exampleGameGivenAlreadyStarted.getDeck().size());
    assertEquals(52, this.exampleGameGivenPossibleBeginning.getDeck().size());
  }

  @Test
  public void testGetDeckNoDuplicates() {
    // for every card in the deck, make sure the first index of that card is equal
    // to the last index found of that card in the list (if true, then no duplicates)
    for (int i = 0; i < 52; i++) {
      assertEquals(
          this.freecellModel.getDeck().lastIndexOf(this.freecellModel.getDeck().get(i)),
          this.freecellModel
              .getDeck().indexOf(this.freecellModel.getDeck().get(i)));
    }
    List<ICard> copyOfDeck = new ArrayList<ICard>(this.freecellModel.getDeck());
    for (int i = 0; i < 52; i++) {
      ICard c = copyOfDeck.get(i);
      copyOfDeck.remove(i);
      assertFalse(copyOfDeck.contains(c));
      copyOfDeck.add(i, c);
    }
  }

  @Test
  public void testGetDeckCorrect52Cards() {
    // for every card in the deck (which I created from scratch with a unique instance
    // of each combo of a Value and a Suit), check whether the deck contains it.
    for (int i = 0; i < 52; i++) {
      // make sure contains it
      assertTrue(this.deckCopy.contains(this.exampleGameGivenAlreadyStarted.getDeck().get(i)));
      // make sure each card is exactly the same
      assertEquals(this.deckCopy.get(i), this.exampleGameGivenAlreadyStarted.getDeck().get(i));
    }

    for (ICard c : this.freecellModel.getDeck()) {
      assertTrue(this.deckCopy.contains(c));
    }
  }

  @Test
  public void testStartGameShuffleFalse() {
    // make sure no Cascade Piles even exist before starting the game (returns -1 before game start)
    assertEquals(-1, this.freecellModel.getNumCascadePiles());
    // make sure does not shuffle the deck when the boolean for shuffle is false
    // making 52 Cascade Piles so that every card is in its own list of (easier for testing)
    this.freecellModel.startGame(this.freecellModel.getDeck(), 52, 4, false);
    // make sure 8 Cascade Piles were created
    assertEquals(52, this.freecellModel.getNumCascadePiles());
    // make sure first card which was dealt is in the 1st Cascade Pile in the 1st position
    assertEquals(new Card(Value.ACE, Suit.CLUBS), this.freecellModel.getCascadeCardAt(0, 0));
    // etc -> Every card in direct order when not shuffled (dealt directly into cascade piles:
    for (int i = 1; i < 13; i++) {
      assertEquals(i + 1, this.freecellModel.getCascadeCardAt(i, 0).getValue().getNumber());
    }
    for (int suitIndex = 0; suitIndex < 3; suitIndex++) {
      for (int pileIndex = 0; pileIndex < 13; pileIndex++) {
        assertEquals(pileIndex + 1,
            this.freecellModel.getCascadeCardAt(pileIndex, 0).getValue().getNumber());
      }
    }
  }

  @Test
  public void testStartGameShuffleTrue() {
    // making 52 Cascade Piles so that every card is in its own list of (easier for testing)
    this.freecellModel.startGame(this.freecellModel.getDeck(), 52, 4, true);
    // directly not the same card as the other one (has 1/52 chance of actually being in the same
    // spot cuz of chance, but really low chance of it being in same exact spot)
    assertNotEquals(new Card(Value.ACE, Suit.CLUBS), this.freecellModel.getCascadeCardAt(0, 0));
    // this card also not in same spot <- not same dealing
    assertNotEquals(new Card(Value.TWO, Suit.CLUBS), this.freecellModel.getCascadeCardAt(1, 0));
    // making another game and going to make sure doesn't always shuffle the same way
    FreecellModel<ICard> shuffleFreecellExample = new SimpleFreecellModel();
    shuffleFreecellExample.startGame(shuffleFreecellExample.getDeck(), 52, 4, true);
    // not equal to each other in general
    assertNotEquals(shuffleFreecellExample, this.freecellModel);
    // for about 13 cards, make sure its not the same (chance of them being in same spot
    // gets pretty high if try 52 straight tests), so test might fail sometimes
    // if put i-limit as too high.
    for (int i = 0; i < 13; i++) {
      assertNotEquals(this.freecellModel.getCascadeCardAt(i, 0),
          shuffleFreecellExample.getCascadeCardAt(i, 0));
    }
  }

  @Test
  public void testStartGameSuccessCreatedRightNumberOfPiles() {
    this.freecellModel.startGame(this.deckCopy, 129, 123, true);
    assertEquals(129, this.freecellModel.getNumCascadePiles());
    assertEquals(123, this.freecellModel.getNumOpenPiles());
    this.freecellModel.startGame(this.deckCopy, 10, 1400, true);
    assertEquals(10, this.freecellModel.getNumCascadePiles());
    assertEquals(1400, this.freecellModel.getNumOpenPiles());
  }

  @Test
  public void testStartGameSuccessDealCardsRoundRobin() {
    this.freecellModel.startGame(this.freecellModel.getDeck(), 8, 1, false);
    // make sure Ace of Clubs is bottom + first card on bottom when shuffle false
    assertEquals("A♣", this.freecellModel.getCascadeCardAt(0, 0).toString());
    // make sure card above Ace of Clubs is 9 of Clubs (1 cardIndex higher in same Cascade Pile)
    assertEquals("9♣", this.freecellModel.getCascadeCardAt(0, 1).toString());
    // make sure card above that is 4 of Diamonds
    assertEquals("4♦", this.freecellModel.getCascadeCardAt(0, 2).toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testStartGameExceptionNullDeck() {
    // Deck not allowed to be null
    this.freecellModel.startGame(null, 8, 8, true);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testStartGameExceptionAbove52Cards() {
    // Cannot start game with deck above 52 cards
    List<ICard> faultyDeck;
    faultyDeck = this.freecellModel.getDeck();
    // made the faulty deck have another Card, the 8 of Clubs (so 53 Cards total),
    // and (cannot really add a card without making a duplicate because 52 cards
    // = all the possibilities for combinations between them
    // Suits and Values
    faultyDeck.add(new Card(Value.EIGHT, Suit.CLUBS));
    // now test startGame's exception for having a faulty deck which has more than 52 cards
    this.freecellModel.startGame(faultyDeck, 8, 4, false);
  }


  @Test(expected = IllegalArgumentException.class)
  public void testStartGameExceptionBelow52Cards() {
    // Cannot start game with deck below 52 cards
    List<ICard> faultyDeck;
    faultyDeck = this.freecellModel.getDeck();
    // made the faulty deck have 51 cards by removing Ace of Hearts
    faultyDeck.remove(new Card(Value.ACE, Suit.HEARTS));
    // now test startGame's exception for having a faulty deck which has more than 52 cards
    this.freecellModel.startGame(faultyDeck, 9, 10, false);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testStartGameExceptionDeckDuplicates() {
    // Cannot start game with deck having any repeats
    List<ICard> faultyDeck;
    faultyDeck = this.freecellModel.getDeck();
    // made the faulty deck have a duplicate of the Card Jack of Diamonds
    faultyDeck.add(new Card(Value.JACK, Suit.DIAMONDS));
    // removing the ace of clubs to make the deck have 52 cards again (so test with having more
    // or less than 52 cards does not make it fail)
    faultyDeck.remove(new Card(Value.ACE, Suit.CLUBS));
    // now test startGame's exception for having a faulty deck which has a duplicate card
    this.freecellModel.startGame(faultyDeck, 8, 4, true);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testStartGameExceptionBelow4CascadePiles() {
    // Least amount of Cascade Piles Allowed = 4
    // now test startGame's exception for trying to have less than 3 Cascade Piles
    this.freecellModel.startGame(this.freecellModel.getDeck(), 3, 10, false);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testStartGameExceptionBelow1OpenPile() {
    // Least Amount of Open Piles Allowed = 1
    // now test startGame's exception for trying to have less than 1 Open pile
    this.freecellModel.startGame(this.freecellModel.getDeck(), 8, 0, true);
  }


  @Test(expected = IllegalStateException.class)
  public void testMoveExceptionGameNotStarted() {
    // Game has not started yet, so should produce error that game has not started
    this.freecellModel.move(PileType.CASCADE, 0, 9, PileType.OPEN, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionNullSourcePile() {
    this.exampleGameGivenAlreadyStarted.move(null, 0, 7, PileType.OPEN, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionNullDestinationPile() {
    this.exampleGameGivenAlreadyStarted.move(PileType.CASCADE, 0, 7, null, 0);
  }


  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionWrongSuitColorMoveCascadeToCascade() {

    // moving the Jack of Diamonds in Cascade Pile 3 to Open Pile 1 (successful moving to
    // Open Piles from Cascade Piles was already tested in testMoveSuccessCascadeToOpen)
    this.exampleGameGivenAlreadyStarted.move(PileType.CASCADE, 2, 6, PileType.OPEN, 0);
    // should fail because trying to move a Jack of Clubs onto a Queen of Spades (same color)
    this.exampleGameGivenAlreadyStarted.move(PileType.CASCADE, 3, 5, PileType.CASCADE, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionNotLastCardInPileFoundation() {
    // Trying to move the Ace of Hearts in Foundation Pile 3 to Open Pile 1
    this.exampleGameGivenAlreadyStarted.move(PileType.FOUNDATION, 2, 0, PileType.OPEN, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionNotLastCardInPileCascadeAndNotValidBuild() {
    // Trying to move Ace of Diamonds to Foundation Pile 4
    this.exampleGameGivenAlreadyStarted.move(PileType.CASCADE, 7, 3, PileType.FOUNDATION, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionNotAceFoundation() {
    // testing that you cannot move a non-Ace card into an empty Foundation Pile
    // trying to move a 2 of clubs from the 2nd Foundation Pile to the 4th Foundation Pile that
    // is empty
    this.exampleGameGivenAlreadyStarted.move(PileType.FOUNDATION, 1, 1, PileType.FOUNDATION, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionNotSameSuitFoundation() {
    // testing that you cannot move one Suited Card to a Foundation Pile
    // which has a different Suit
    // trying to move a 2 of clubs from the 2nd Foundation Pile to the 1st Foundation Pile that
    // has the Ace of Spades
    this.exampleGameGivenAlreadyStarted.move(PileType.FOUNDATION, 1, 1, PileType.FOUNDATION, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionNotRightValueFoundation() {
    // moving the 2 of diamonds from Cascade Pile 6 into the first Open Pile
    // (tested moving Foundation to Open Piles in testMoveSuccessFoundationToOpen)
    this.exampleGameGivenAlreadyStarted.move(PileType.CASCADE, 5, 6, PileType.OPEN, 0);
    // trying to move the 3 of spades onto the Ace of spades
    // (should throw exception because value of ace = 1, while value of three is 3
    this.exampleGameGivenAlreadyStarted.move(PileType.FOUNDATION, 2, 1, PileType.FOUNDATION, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionNotRightColorCascade() {
    // move the 8 of diamonds (in Open Pile 4) onto the 9 of hearts (in Cascade Pile 4)
    // should throw exception because same colored suit even though values = correct
    this.exampleGameGivenAlreadyStarted.move(PileType.OPEN, 3, 0, PileType.CASCADE, 6);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionNotRightValueCascade() {
    // move the 4 of Diamonds (in Foundation Pile 8) onto the 6 of Spades (in Foundation Pile 4)
    // should throw exception because same colored suit even though values = correct
    this.exampleGameGivenAlreadyStarted.move(PileType.CASCADE, 7, 4, PileType.CASCADE, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionPileToSameExactPileButWrongValue() {
    // move 4 of Diamonds to same Pile it was in before (the 8th Cascade Pile)
    // make sure fails on Cascade Piles when you just move the first card back to the same place
    // it was before -> should not work because Ace of Diamonds = under it, so does not work
    this.exampleGameGivenAlreadyStarted.move(PileType.CASCADE, 7, 4, PileType.CASCADE, 7);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionPileToSameExactPileButWrongSuit() {
    this.exampleGameGivenPossibleBeginning.move(PileType.CASCADE, 1, 6, PileType.CASCADE, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionOnlyOneCardAllowedOpen() {
    // move the 4 of Diamonds in Cascade Pile 8 onto the 8 of Diamonds in Open Pile 4
    this.exampleGameGivenAlreadyStarted.move(PileType.CASCADE, 7, 4, PileType.OPEN, 3);
  }


  /* Tests for Successful Moves
  1. F to F
  2. F to C
  3. F to O

  1. C to F
  2. C to C
  3. C to O

  4. O to F
  5. O to C
  6. O to O
  */
  @Test
  public void testMoveSuccessFoundationToFoundation() {
    // Try moving Ace of Spades in 1st Foundation Pile to 4th Foundation Pile which is empty
    this.exampleGameGivenAlreadyStarted.move(PileType.FOUNDATION, 0, 0, PileType.FOUNDATION, 3);
    // Make sure Ace of Spades not in 1st Foundation Pile anymore
    assertEquals(0, this.exampleGameGivenAlreadyStarted.getNumCardsInFoundationPile(0));
    // Make sure Ace of Spades in 4th Foundation Pile now
    assertEquals(new Card(Value.ACE, Suit.SPADES), this.exampleGameGivenAlreadyStarted
        .getFoundationCardAt(3, 0));
  }


  @Test
  public void testMoveSuccessFoundationToCascade() {
    assertEquals(new Card(Value.ACE, Suit.SPADES), this.exampleGameGivenAlreadyStarted
        .getFoundationCardAt(0, 0));
    // will pick up the ace of spades and place it on the 2 of diamonds
    this.exampleGameGivenAlreadyStarted.move(PileType.FOUNDATION, 0, 0, PileType.CASCADE, 5);
    // make sure Ace there in Cascade Pile
    assertEquals(new Card(Value.ACE, Suit.SPADES), this.exampleGameGivenAlreadyStarted
        .getCascadeCardAt(5, 7));
    // make sure no cards in 1st Open Pile (because Ace was moved)
    assertEquals(0, this.exampleGameGivenAlreadyStarted.getNumCardsInFoundationPile(0));
  }


  @Test
  public void testMoveSuccessFoundationToOpen() {
    // Try moving Ace of Spades in 1st Foundation Pile to 2nd Open Pile which is empty
    this.exampleGameGivenAlreadyStarted.move(PileType.FOUNDATION, 0, 0, PileType.OPEN, 1);
    // Make sure Ace of Spades not in 1st Foundation Pile anymore
    assertEquals(0, this.exampleGameGivenAlreadyStarted.getNumCardsInFoundationPile(0));
    // Make sure Ace of Spades in 2nd Open Pile now
    assertEquals(new Card(Value.ACE, Suit.SPADES), this.exampleGameGivenAlreadyStarted
        .getOpenCardAt(1));
  }

  @Test
  public void testMoveSuccessCascadeToFoundation() {
    // Tested that the move 4 of diamonds to 5 of spades works (in Test below this one)
    // (see testMoveSuccessCascadeToCascade)
    this.exampleGameGivenAlreadyStarted.move(PileType.CASCADE, 7, 4, PileType.CASCADE, 0);
    // now testing whether we can actually move the Ace of Diamonds to the 4th Foundation Pile
    this.exampleGameGivenAlreadyStarted.move(PileType.CASCADE, 7, 3, PileType.FOUNDATION, 3);
    // make sure Ace now = last card in 4th Foundation Pile
    assertEquals(new Card(Value.ACE, Suit.DIAMONDS),
        this.exampleGameGivenAlreadyStarted.getFoundationCardAt(3, 0));
    // Make sure last card of Cascade Pile = not Ace
    assertNotEquals(new Card(Value.ACE, Suit.DIAMONDS), this.exampleGameGivenAlreadyStarted
        .getCascadeCardAt(7, this.exampleGameGivenAlreadyStarted.getNumCardsInCascadePile(7) - 1));
  }


  @Test
  public void testMoveSuccessCascadeToCascade() {
    // picking up 4 of diamonds (in last Cascade Pile)
    assertEquals(new Card(Value.FOUR, Suit.DIAMONDS), this.exampleGameGivenAlreadyStarted
        .getCascadeCardAt(7, 4));
    // make sure number of cards in Cascade Pile 4 is 5
    assertEquals(5, this.exampleGameGivenAlreadyStarted.getNumCardsInCascadePile(7));
    // moving it to 5 of spades (in first Cascade Pile)
    assertEquals(new Card(Value.FIVE, Suit.SPADES), this.exampleGameGivenAlreadyStarted
        .getCascadeCardAt(0, 6));
    // make sure number of cards in Cascade Pile 1 is 7
    assertEquals(7, this.exampleGameGivenAlreadyStarted.getNumCardsInCascadePile(0));
    // alright move the card because no error should be thrown
    this.exampleGameGivenAlreadyStarted.move(PileType.CASCADE, 7, 4, PileType.CASCADE, 0);
    // check that there are now 8 cards in Cascade Pile 1
    assertEquals(8, this.exampleGameGivenAlreadyStarted.getNumCardsInCascadePile(0));
    // check that there are now 4 cards in Cascade Pile 8
    assertEquals(4, this.exampleGameGivenAlreadyStarted.getNumCardsInCascadePile(7));
    // check that the card was correctly added to the Cascade Pile
    assertEquals(new Card(Value.FOUR, Suit.DIAMONDS), this.exampleGameGivenAlreadyStarted
        .getCascadeCardAt(0, 7));
    // check also that last card in the Cascade Pile where the card was before is now the Ace
    // of Diamonds
    assertEquals(new Card(Value.ACE, Suit.DIAMONDS), this.exampleGameGivenAlreadyStarted
        .getCascadeCardAt(7, 3));
    // also check that any of the other lists do not have the 4 of diamonds
    for (int i = 1; i < 8; i++) {
      int lastCardIndex = this.exampleGameGivenAlreadyStarted.getNumCardsInCascadePile(i) - 1;
      assertNotEquals(new Card(Value.FOUR, Suit.DIAMONDS),
          this.exampleGameGivenAlreadyStarted.getCascadeCardAt(i, lastCardIndex));
    }
  }


  @Test
  public void testMoveSuccessCascadeToOpen() {
    assertEquals(new Card(Value.NINE, Suit.HEARTS), this.exampleGameGivenAlreadyStarted
        .getCascadeCardAt(6, 3));
    // will pick up 9 of hearts and place it on top of the 3rd empty Open pile
    this.exampleGameGivenAlreadyStarted.move(PileType.CASCADE, 6, 3, PileType.OPEN, 2);
    // make sure 9 card there
    assertEquals(new Card(Value.NINE, Suit.HEARTS), this.exampleGameGivenAlreadyStarted
        .getOpenCardAt(2));
    // make sure last card 7th Cascade Pile is not 9
    assertNotEquals(new Card(Value.NINE, Suit.HEARTS), this.exampleGameGivenAlreadyStarted
        .getCascadeCardAt(6, this.exampleGameGivenAlreadyStarted.getNumCardsInCascadePile(6) - 1));
    // another example
    // will pick up Jack of Clubs and place it on the second Open Pile
    this.exampleGameGivenAlreadyStarted.move(PileType.CASCADE, 3, 5, PileType.OPEN, 1);
    // make sure Jack now in Open Pile
    assertEquals(new Card(Value.JACK, Suit.CLUBS),
        this.exampleGameGivenAlreadyStarted.getOpenCardAt(1));
    // make sure 3 last card now
    assertEquals(new Card(Value.THREE, Suit.CLUBS),
        this.exampleGameGivenAlreadyStarted.getCascadeCardAt(3,
            this.exampleGameGivenAlreadyStarted.getNumCardsInCascadePile(3) - 1));

  }


  @Test
  public void testMoveSuccessOpenToFoundation() {
    // Moving the Jack of Clubs to 2nd Open Pile 3 which was tested in testMoveSuccessCascadeToOpen
    // Moving the 3 of clubs to the 3rd open pile also to test that moving 3 of clubs to foundation
    // pile is okay
    this.exampleGameGivenAlreadyStarted.move(PileType.CASCADE, 3, 5, PileType.OPEN, 1);
    this.exampleGameGivenAlreadyStarted.move(PileType.CASCADE, 3, 4, PileType.OPEN, 2);
    // Now trying to move the 3 of Clubs in 2nd Open Pile to 2nd Foundation Pile which is holds the
    // Ace and 2 of Clubs (Should work b/c same suit)
    this.exampleGameGivenAlreadyStarted.move(PileType.OPEN, 2, 0, PileType.FOUNDATION, 1);
    // Make sure 3 of Clubs in 2nd Foundation Pile now as last card
    assertEquals(new Card(Value.THREE, Suit.CLUBS),
        this.exampleGameGivenAlreadyStarted.getFoundationCardAt(1,
            this.exampleGameGivenAlreadyStarted.getNumCardsInFoundationPile(1) - 1));
    // Make sure 3 of Clubs not in 3rd Open Pile anymore
    assertNull(this.exampleGameGivenAlreadyStarted.getOpenCardAt(2));
  }

  @Test
  public void testMoveSuccessOpenToCascade() {
    // tested moving cards from Cascade Piles to Open Piles in testMoveSuccessCascadeToOpen
    // so I will use that to move the 4 of diamonds to the Open Pile so I can test whether
    // moving the 4 of Diamonds to the Cascade Pile works from the Open Piles
    // Moving the 4 of Diamonds from the Cascade Pile to Open Pile 1
    this.exampleGameGivenAlreadyStarted.move(PileType.CASCADE, 7, 4, PileType.OPEN, 0);
    // Now moving the 4 of Diamonds from the 1st Open Pile to the 1st Cascade Pile
    this.exampleGameGivenAlreadyStarted.move(PileType.OPEN, 0, 0, PileType.CASCADE, 0);
    // Make sure Cascade Pile 1 has the 4 of diamonds now
    assertEquals(new Card(Value.FOUR, Suit.DIAMONDS),
        this.exampleGameGivenAlreadyStarted.getCascadeCardAt(0, 7));
    // Make sure Open Pile 1 has nothing now
    assertNull(this.exampleGameGivenAlreadyStarted.getOpenCardAt(0));
  }

  @Test
  public void testMoveSuccessOpenToOpen() {
    // Moving 8 of Diamonds from 4th Open Pile to 3rd Open Pile
    this.exampleGameGivenAlreadyStarted.move(PileType.OPEN, 3, 0, PileType.OPEN, 2);
    // Make sure 8 of Diamonds is in 3rd Open Pile now
    assertEquals(new Card(Value.EIGHT, Suit.DIAMONDS),
        this.exampleGameGivenAlreadyStarted.getOpenCardAt(2));
    // Make sure no 8 of Diamonds is in 4th Open Pile now
    assertNull(this.exampleGameGivenAlreadyStarted.getOpenCardAt(3));

    for (int i = 2; i > 0; i = i - 1) {
      // move the card to the next Open Pile to the left
      this.exampleGameGivenAlreadyStarted.move(PileType.OPEN, i, 0, PileType.OPEN, i - 1);
      // make sure 8 of Diamonds is in directly left pile
      assertEquals(new Card(Value.EIGHT, Suit.DIAMONDS),
          this.exampleGameGivenAlreadyStarted.getOpenCardAt(i - 1));
      // make sure no 8 of Diamonds = in Open Pile from which it was moved from
      assertNull(this.exampleGameGivenAlreadyStarted.getOpenCardAt(i));
    }

    // make sure can move 8 of diamonds back to 4th pile
    // Moving 8 of Diamonds from 1st Open Pile to 4th Open Pile
    this.exampleGameGivenAlreadyStarted.move(PileType.OPEN, 0, 0, PileType.OPEN, 3);
    // Make sure 8 of Diamonds is in 4th Open Pile now
    assertEquals(new Card(Value.EIGHT, Suit.DIAMONDS),
        this.exampleGameGivenAlreadyStarted.getOpenCardAt(3));
    // Make sure no 8 of Diamonds is in 1st Open Pile now
    assertNull(this.exampleGameGivenAlreadyStarted.getOpenCardAt(0));
  }

  @Test
  public void testMoveSuccessPileToSameExactPileWasAtBeforeIfFollowsRules() {
    // move the 2 of Clubs to the same Pile it was before (the 2nd Foundation Pile)
    // make sure works on foundation Piles when you just move the first card back to the same
    // place it was before
    this.exampleGameGivenAlreadyStarted.move(PileType.FOUNDATION, 1, 1, PileType.FOUNDATION, 1);
    // make sure still there
    assertEquals(new Card(Value.TWO, Suit.CLUBS),
        this.exampleGameGivenAlreadyStarted.getFoundationCardAt(1, 1));
    // move Jack of Diamonds to same Pile it was in before (the 2nd Cascade Pile)
    // make sure works on Cascade Piles when you just move the first card back to the same place
    // it was before (following the rules of the game where it has to be a different suit-ed card
    // and where the card you're moving is of a Value 1 less than the top card where you are moving
    // it to
    this.exampleGameGivenAlreadyStarted.move(PileType.CASCADE, 2, 6, PileType.CASCADE, 2);
    // make sure still there
    assertEquals(new Card(Value.JACK, Suit.DIAMONDS),
        this.exampleGameGivenAlreadyStarted.getCascadeCardAt(2, 6));
    // move the 8 of Diamonds to same Pile it was before (the 4th Open Pile)
    // make sure works on Open Piles when you just move the first card back to the same
    // place it was originally
    this.exampleGameGivenAlreadyStarted.move(PileType.OPEN, 3, 0, PileType.OPEN, 3);
    // make sure still there
    assertEquals(new Card(Value.EIGHT, Suit.DIAMONDS),
        this.exampleGameGivenAlreadyStarted.getOpenCardAt(3));
  }

  @Test
  public void testIsGameOver() {
    assertFalse(this.freecellModel.isGameOver());
    assertFalse(this.exampleGameGivenNotStarted.isGameOver());
    this.freecellModel.startGame(this.freecellModel.getDeck(), 5, 5, true);
    assertFalse(this.freecellModel.isGameOver());
    assertFalse(this.exampleGameGivenAlreadyStarted.isGameOver());
    // this game's state is in finished already (even though has nothing in its Foundation Pile
    assertTrue(this.exampleGameGivenFinished.isGameOver());
    // make sure this isn't game over yet (because move has to switch the state of the game
    assertFalse(this.exampleGameGivenAlmostFinished.isGameOver());
    // this game is actually already over (and also has 1 extra ace of clubs,
    // but the state is still in playing, so after 1 move it
    // should become game over
    this.exampleGameGivenAlmostFinished.move(PileType.CASCADE, 0, 0, PileType.OPEN, 0);
    assertTrue(this.exampleGameGivenAlmostFinished.isGameOver());

  }

}
