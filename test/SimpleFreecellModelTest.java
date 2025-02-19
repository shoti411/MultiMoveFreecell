import static org.junit.Assert.assertEquals;

import cs3500.freecell.model.PileType;
import cs3500.freecell.model.hw02.Card;
import cs3500.freecell.model.hw02.GameState;
import cs3500.freecell.model.hw02.ICard;
import cs3500.freecell.model.hw02.SimpleFreecellModel;
import cs3500.freecell.model.hw02.Suit;
import cs3500.freecell.model.hw02.Value;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for SimpleFreecellModel: Unit tests to ensure that SimpleFreecellModel correctly deals
 * out the game Freecell (following all the rules) and otherwise behave correctly.
 */
public class SimpleFreecellModelTest extends AbstractFreecellModelTest {

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
  @Override
  @Before
  public void setup() {
    this.freecellModel = new SimpleFreecellModel();

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
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionNotLastCardInPileCascadeCardRightBeneathTop() {
    // Trying to move 3 of Spades above the 2 of Diamonds in 6th Cascade Pile
    // onto the 4 of Diamonds in the 8th Cascade Pile
    // This is a test to make sure the SimpleFreecellModel is not able to move multiple cards at
    // once even if it is a valid build
    // - The main difference between MultiMoveFreecellModel and SimpleFreecellModel
    // It should not work for SimpleFreecellModel, but this should work for MultiMoveFreecellModel
    this.exampleGameGivenAlreadyStarted.move(PileType.CASCADE, 5, 5, PileType.CASCADE, 7);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionNotLastCardInPileCascadeMoreThan1CardBeneathTop() {
    // Trying to move 4 of Hearts in the 6th Cascade Pile and all the ones before it
    // onto the 5 of Spades in the First Cascade Pile
    // Similar test to the one directly above this test - testing main difference between models
    this.exampleGameGivenAlreadyStarted.move(PileType.CASCADE, 5, 4, PileType.CASCADE, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveSuccessMove2Cards1Open0CascadePilesFree() {
    // commands to get to points in the games during the success Intermediate Spots tests.
    // C5 3 C2
    this.freecellModel.startGame(this.easyTestDeck, 14, 1, false);
    // make sure 1st Open Pile is Free
    assertEquals(0, this.freecellModel.getNumCardsInOpenPile(0));
    // make sure 8 of Diamonds is now in 4th Pile
    assertEquals(new Card(Value.EIGHT, Suit.DIAMONDS), this.freecellModel.getCascadeCardAt(3, 2));
    // make sure 7 of Clubs is in 4th pile too (on top of 8 of Diamonds)
    assertEquals(new Card(Value.SEVEN, Suit.CLUBS), this.freecellModel.getCascadeCardAt(3, 3));
    // make sure 4th pile has total of 4 cards now
    assertEquals(4, this.freecellModel.getNumCardsInCascadePile(3));
    // (N+1)∗2^K; N = # of free open piles, K = # of free open Cascade Piles
    // 1 Open Piles so far and 0 free Cascade Piles
    // Moving the first 8 of Diamonds in C5 to the 9 of Clubs in C2 (valid build with 2 Cards)
    // the max number of cards we can move should be (1+1)*2^0 = 2 * 1 = 2, so this should work
    // for MultiMoveFreecellModel, but not for SimpleFreecellModel
    this.freecellModel.move(PileType.CASCADE, 3, 2, PileType.CASCADE, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveSuccessMove2Cards0Open1CascadePilesFree() {
    this.freecellModel.startGame(this.easyTestDeck, 14, 1, false);
    // (N+1)∗2^K; N = # of free open piles, K = # of free open Cascade Piles
    // moving the Ace of Diamonds To the foundation pile
    this.freecellModel.move(PileType.CASCADE, 10, 2, PileType.FOUNDATION, 0);
    // now move the 2 of Hearts to the 3 of Clubs
    this.freecellModel.move(PileType.CASCADE, 10, 1, PileType.CASCADE, 7);
    // now move the 3 of Spades to the free open pile
    this.freecellModel.move(PileType.CASCADE, 10, 0, PileType.OPEN, 0);
    // make sure the 3 of Spades is actually in the Open Pile, so the Open Pile is not free
    assertEquals(1, this.freecellModel.getNumCardsInOpenPile(0));
    // make sure Cascade Pile 11 is empty now, so it counts as a free Cascade Pile
    assertEquals(0, this.freecellModel.getNumCardsInCascadePile(10));
    // make sure 8 of Diamonds is now in 4th Pile
    assertEquals(new Card(Value.EIGHT, Suit.DIAMONDS), this.freecellModel.getCascadeCardAt(3, 2));
    // make sure 7 of Clubs is in 4th pile too (on top of 8 of Diamonds)
    assertEquals(new Card(Value.SEVEN, Suit.CLUBS), this.freecellModel.getCascadeCardAt(3, 3));
    // make sure 4th pile has total of 4 cards now
    assertEquals(4, this.freecellModel.getNumCardsInCascadePile(3));
    // (N+1)∗2^K; N = # of free open piles, K = # of free open Cascade Piles
    // 0 Open Piles so far and 1 free Cascade Piles
    // Moving the first 8 of Diamonds in C5 to the 9 of Clubs in C2 (valid build with 2 Cards)
    // the max number of cards we can move should be (0+1)*2^1 = 1 * 2 = 2, so this should work
    // for MultiMoveFreecellModel, but not for SimpleFreecellModel
    this.freecellModel.move(PileType.CASCADE, 3, 2, PileType.CASCADE, 1);
  }

}