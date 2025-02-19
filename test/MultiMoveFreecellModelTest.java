import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import cs3500.freecell.model.PileType;
import cs3500.freecell.model.hw02.Card;
import cs3500.freecell.model.hw02.GameState;
import cs3500.freecell.model.hw02.ICard;
import cs3500.freecell.model.hw02.Suit;
import cs3500.freecell.model.hw02.Value;
import cs3500.freecell.model.hw04.MultiMoveFreecellModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for MultiMoveFreecellModel: Unit tests to ensure that MultiMoveFreecellModel correctly
 * deals out the game Freecell (following all the rules) and otherwise behave correctly.
 */
public class MultiMoveFreecellModelTest extends AbstractFreecellModelTest {

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
    this.freecellModel = new MultiMoveFreecellModel();

    // This is the example game which was given as a sample in the assignment
    // (the one in the first picture)
    this.exampleGameGivenNotStarted = new MultiMoveFreecellModel(GameState.HASNOTSTARTED,
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

    this.exampleGameGivenAlreadyStarted = new MultiMoveFreecellModel(GameState.PLAYING,
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

    this.exampleGameGivenPossibleBeginning = new MultiMoveFreecellModel(
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
    this.exampleGameGivenFinished = new MultiMoveFreecellModel(
        GameState.FINISHED,
        new ArrayList<ArrayList<ICard>>(Arrays
            .asList(new ArrayList<ICard>(), new ArrayList<ICard>(), new ArrayList<ICard>(),
                new ArrayList<ICard>())), new ArrayList<ArrayList<ICard>>(Arrays
        .asList(new ArrayList<ICard>(), new ArrayList<ICard>(), new ArrayList<ICard>(),
            new ArrayList<ICard>())),
        new ArrayList<ArrayList<ICard>>(Arrays
            .asList(new ArrayList<ICard>(), new ArrayList<ICard>(), new ArrayList<ICard>(),
                new ArrayList<ICard>())));
    this.exampleGameGivenAlmostFinished = new MultiMoveFreecellModel(
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


  @Test
  public void testMoveExceptionNotLastCardInPileCascadeCardRightBeneathTop() {
    // Trying to move 3 of Spades above the 2 of Diamonds in 6th Cascade Pile
    // onto the 4 of Diamonds in the 8th Cascade Pile
    // This is a test to make sure the MultiMoveFreecellModel is able to move multiple cards at
    // once if it is a valid build
    // - The main difference between MultiMoveFreecellModel and SimpleFreecellModel
    // It should work for MultiMoveFreecellModel, but this should not work for SimpleFreecellModel
    // make sure 2 of Diamonds is last card in 6th Cascade Pile
    assertEquals(new Card(Value.TWO, Suit.DIAMONDS), this.exampleGameGivenAlreadyStarted
        .getCascadeCardAt(5, this.exampleGameGivenAlreadyStarted.getNumCardsInCascadePile(5) - 1));
    // and 3 of Spades is card before 2 of Diamonds in 6th Cascade Pile
    assertEquals(new Card(Value.THREE, Suit.SPADES), this.exampleGameGivenAlreadyStarted
        .getCascadeCardAt(5, this.exampleGameGivenAlreadyStarted.getNumCardsInCascadePile(5) - 2));
    // also make sure 4 of Diamonds is the last card in last 8th Card Pile
    assertEquals(new Card(Value.FOUR, Suit.DIAMONDS),
        this.exampleGameGivenAlreadyStarted.getCascadeCardAt(7, 4));
    // move the 2 cards to the first pile
    this.exampleGameGivenAlreadyStarted.move(PileType.CASCADE, 5, 5, PileType.CASCADE, 7);
    // make sure 5th Card in that pile is still the 4 of Diamonds which we moved the cards onto
    // to - just make sure Destination Card did not change at all
    assertEquals(new Card(Value.FOUR, Suit.DIAMONDS),
        this.exampleGameGivenAlreadyStarted.getCascadeCardAt(7, 4));
    // make sure 6th card in that pile is now the 3 of Spades
    assertEquals(new Card(Value.THREE, Suit.SPADES),
        this.exampleGameGivenAlreadyStarted.getCascadeCardAt(7, 5));
    // make sure last card in the 8th Cascade Pile is now the 2 of Diamonds
    assertEquals(new Card(Value.TWO, Suit.DIAMONDS),
        this.exampleGameGivenAlreadyStarted.getCascadeCardAt(7, 6));
    // make sure the cards that we moved are not in the pile they were at before
    // which means 2 of Diamonds is not last card in 6th Cascade Pile now
    assertNotEquals(new Card(Value.TWO, Suit.DIAMONDS), this.exampleGameGivenAlreadyStarted
        .getCascadeCardAt(5, this.exampleGameGivenAlreadyStarted.getNumCardsInCascadePile(5) - 1));
    // and 3 of Spades is not the card before 2 of Diamonds in 6th Cascade Pile
    assertNotEquals(new Card(Value.THREE, Suit.SPADES), this.exampleGameGivenAlreadyStarted
        .getCascadeCardAt(5, this.exampleGameGivenAlreadyStarted.getNumCardsInCascadePile(5) - 2));
  }

  @Test
  public void testMoveExceptionNotLastCardInPileCascadeMoreThan1CardBeneathTop() {
    // Trying to move 4 of Hearts in the 6th Cascade Pile and all the ones before it
    // onto the 5 of Spades in the First Cascade Pile
    // Similar test to the one directly above this test - testing main difference between models
    // except moving 3 cards instead of just 2
    // make sure 5th Card in that pile is still the 4 of Diamonds which we moved the cards onto
    assertEquals(new Card(Value.FOUR, Suit.HEARTS),
        this.exampleGameGivenAlreadyStarted.getCascadeCardAt(5, 4));
    // make sure 6th card in that pile is now the 3 of Spades
    assertEquals(new Card(Value.THREE, Suit.SPADES),
        this.exampleGameGivenAlreadyStarted.getCascadeCardAt(5, 5));
    // make sure last card in the 6th Cascade Pile is now the 2 of Diamonds
    assertEquals(new Card(Value.TWO, Suit.DIAMONDS),
        this.exampleGameGivenAlreadyStarted.getCascadeCardAt(5, 6));
    // make sure last card in the 1st Cascade Pile is the 5 of Spades
    assertEquals(new Card(Value.FIVE, Suit.SPADES),
        this.exampleGameGivenAlreadyStarted.getCascadeCardAt(0, 6));
    // moving the build of size 3 to first Cascade Pile
    this.exampleGameGivenAlreadyStarted.move(PileType.CASCADE, 5, 4, PileType.CASCADE, 0);
    // make sure 6th Card in that pile is still the 5 of Spades which we moved the cards onto
    // to - just make sure Destination Card did not change at all
    assertEquals(new Card(Value.FIVE, Suit.SPADES),
        this.exampleGameGivenAlreadyStarted.getCascadeCardAt(0, 6));
    // make sure 7th card in that pile is now the 4 of Hearts
    assertEquals(new Card(Value.FOUR, Suit.HEARTS),
        this.exampleGameGivenAlreadyStarted.getCascadeCardAt(0, 7));
    // make sure the 8th card in the that Pile is now the 3 of Spades
    assertEquals(new Card(Value.THREE, Suit.SPADES),
        this.exampleGameGivenAlreadyStarted.getCascadeCardAt(0, 8));
    // make sure last card in the first Cascade Pile is now the 2 of Diamonds
    assertEquals(new Card(Value.TWO, Suit.DIAMONDS),
        this.exampleGameGivenAlreadyStarted.getCascadeCardAt(0, 9));
    // make sure the cards that we moved are not in the pile they were at before
    // which means 2 of Diamonds is not last card in 6th Cascade Pile now
    assertNotEquals(new Card(Value.TWO, Suit.DIAMONDS), this.exampleGameGivenAlreadyStarted
        .getCascadeCardAt(5, this.exampleGameGivenAlreadyStarted.getNumCardsInCascadePile(5) - 1));
    // and 3 of Spades is not the card before 2 of Diamonds in 6th Cascade Pile
    assertNotEquals(new Card(Value.THREE, Suit.SPADES), this.exampleGameGivenAlreadyStarted
        .getCascadeCardAt(5, this.exampleGameGivenAlreadyStarted.getNumCardsInCascadePile(5) - 2));
    // and 4 of Hearts is not the card before 3 of Spades in 6th Cascade Pile
    assertNotEquals(new Card(Value.FOUR, Suit.HEARTS), this.exampleGameGivenAlreadyStarted
        .getCascadeCardAt(5, this.exampleGameGivenAlreadyStarted.getNumCardsInCascadePile(5) - 3));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionCannotMoveMultipleCardsToOpenPiles() {
    // cannot move multiple cards to Open Pile even if it was open because you can only move
    // multiple cards from a Cascade Pile to another Cascade Pile
    this.exampleGameGivenAlreadyStarted.move(PileType.CASCADE, 5, 5, PileType.OPEN, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionCannotMoveMultipleCardsToFoundationPilesEmpty() {
    // cannot move multiple cards to Foundation Pile because you can only move
    // multiple cards from a Cascade Pile to another Cascade Pile - it also wouldn't
    // be a valid work anyway because of the rules for when you can put stuff in a foundation pile
    // - source card isn't even an ace
    this.exampleGameGivenAlreadyStarted.move(PileType.CASCADE, 5, 5, PileType.FOUNDATION, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionCannotMoveMultipleCardsToFoundationPilesNonEmpty() {
    // cannot move multiple cards to Open Pile even if it was open because you can only move
    // multiple cards from a Cascade Pile to another Cascade Pile
    // - though 2 of Diamonds might be the right suit, not all of cards in build are
    // - so still just wrong
    // just moving 4 of diamonds to open pile first and putting ace of diamonds in Foundation Pile
    // this has been tested before already by the Abstract Testing class
    this.exampleGameGivenAlreadyStarted.move(PileType.CASCADE, 7, 4, PileType.OPEN, 0);
    this.exampleGameGivenAlreadyStarted.move(PileType.CASCADE, 7, 3, PileType.FOUNDATION, 3);
    // now we can try moving it which will give us an exception
    this.exampleGameGivenAlreadyStarted.move(PileType.CASCADE, 5, 5, PileType.OPEN, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionNotRightSuitDestCardButValidBuild() {
    // trying to move the queen of clubs at the bottom of 7th Cascade Pile to the King of Clubs
    this.exampleGameGivenAlreadyStarted.move(PileType.CASCADE, 6, 0, PileType.CASCADE, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionWrongValueDestCardButValidBuild() {
    // trying to move jack of hearts onto jack of clubs but should fail, but correct build
    this.exampleGameGivenAlreadyStarted.move(PileType.CASCADE, 6, 1, PileType.CASCADE, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionInvalidBuildRightColorWrongValuePair() {
    // trying to move the 5 of diamonds and the 5 of spades on top of it onto the 6 of Spades
    this.exampleGameGivenAlreadyStarted.move(PileType.CASCADE, 0, 5, PileType.CASCADE, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionInvalidBuildWrongColorRightValuePair() {
    // first for this test use a little bit of different looking build
    this.freecellModel.startGame(this.deckCopy, 12, 4, false);
    // trying to move Queen of Diamonds under the Jack of Hearts under the 10 of Spades
    // in the 1st Cascade Pile onto the King of Spades in the 4th Cascade Pile
    // this move also features the build error in the middle of the build rather than
    // just being the last 2 cards
    // this should fail because red Diamond Jack was on top of red Heart Queen
    this.freecellModel.move(PileType.CASCADE, 0, 2, PileType.CASCADE, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionInvalidBuildNotEnoughIntermediateSpots0Open0CascadeFree() {
    // (N+1)∗2^K; N = # of free open piles, K = # of free open Cascade Piles
    // moving a Jack of Diamonds to Open Pile 1, 4 of Diamonds to Open Pile 2, and
    // Ace of Diamonds to Open Pile 3
    this.exampleGameGivenAlreadyStarted.move(PileType.CASCADE, 2, 6, PileType.OPEN, 0);
    this.exampleGameGivenAlreadyStarted.move(PileType.CASCADE, 7, 4, PileType.OPEN, 1);
    this.exampleGameGivenAlreadyStarted.move(PileType.CASCADE, 7, 3, PileType.OPEN, 2);
    // now all Open Piles filled
    // now try moving the 4 of Hearts (and everything above it) in the 6th Pile
    // to the 1st pile onto the 5 of Spades,
    // but this should fail because the build is of size 3 while the number of cards you can move
    // because of the number of intermediate piles is only 1
    // - so fails 3rd condition of not enough intermediate piles
    this.exampleGameGivenAlreadyStarted.move(PileType.CASCADE, 5, 4, PileType.CASCADE, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionInvalidBuildNotEnoughIntermediateSpots1Open0CascadeFree() {
    this.freecellModel.startGame(this.deckCopy, 12, 1, false);

    // (N+1)∗2^K; N = # of free open piles, K = # of free open Cascade Piles
    // moving the 10 of Hearts under the 9 of Spades in 12th Cascade Pile to
    // the 2nd Cascade Pile onto the Jack of Spades - valid move + valid build
    // (max of 2 cards able to move and this is a 2 card build) - should not fail
    this.freecellModel.move(PileType.CASCADE, 11, 2, PileType.CASCADE, 1);
    // now try moving the 10 of Clubs at the bottom of the 10th Pile to the 12th pile onto the Jack,
    // but this should fail because the build is of size 4 while the number of cards you can move
    // because of the number of intermediate piles is only 2
    // - so fails 3rd condition of not enough intermediate piles
    this.freecellModel.move(PileType.CASCADE, 9, 0, PileType.CASCADE, 11);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionInvalidBuildNotEnoughIntermediateSpots2Open0CascadeFree() {
    // (N+1)∗2^K; N = # of free open piles, K = # of free open Cascade Piles
    // moving a Jack of Diamonds to Open Pile 1, 4 of Diamonds to Open Pile 2, and
    // Ace of Diamonds to Foundation Pile 4
    this.exampleGameGivenAlreadyStarted.move(PileType.CASCADE, 2, 6, PileType.OPEN, 0);
    this.exampleGameGivenAlreadyStarted.move(PileType.CASCADE, 7, 4, PileType.OPEN, 1);
    this.exampleGameGivenAlreadyStarted.move(PileType.CASCADE, 7, 3, PileType.FOUNDATION, 3);
    // now only 2 Open Piles filled
    // now try moving the 4 of Hearts (and everything above it) in the 6th Pile
    // to the 1st pile onto the 5 of Spades, should work now because enough open piles
    this.exampleGameGivenAlreadyStarted.move(PileType.CASCADE, 5, 4, PileType.CASCADE, 0);
    // Now try moving 5 of Spades in first Cascade Pile to 6 of Hearts in 7th Cascade Pile
    // but this should fail because the build is of size 4 while the number of cards you can move
    // because of the number of intermediate piles is only 3
    // - so fails 3rd condition of not enough intermediate piles
    this.exampleGameGivenAlreadyStarted.move(PileType.CASCADE, 0, 6, PileType.CASCADE, 7);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionInvalidBuildNotEnoughIntermediateSpotsMoreThan2Open0CascadeFree() {
    this.freecellModel.startGame(this.easyTestDeck, 14, 3, false);
    // (N+1)∗2^K; N = # of free open piles, K = # of free open Cascade Piles
    // moving the 8 of Diamonds under the 7 of Clubs in 4th Cascade Pile to
    // the 2nd Cascade Pile onto the 9 of Clubs - valid move + valid build
    // + valid number of open piles
    // (max of 5 cards able to move and this is a 2 card build) - should not fail
    this.freecellModel.move(PileType.CASCADE, 3, 2, PileType.CASCADE, 1);
    // now try moving 10 of Diamonds in 2nd Pile to the 14th Pile where the Jack of Clubs is
    this.freecellModel.move(PileType.CASCADE, 1, 2, PileType.CASCADE, 13);
    // now move the Queen of Diamonds in the 14th Pile to the
    // 12th Cascade pile onto the King of Clubs
    // but this should fail because the build is of size 6 while the number of cards you can move
    // because of the max number of cards you can move is 4 because of the number of intermediate
    // piles
    // - so fails 3rd condition of not enough intermediate piles
    this.freecellModel.move(PileType.CASCADE, 13, 1, PileType.CASCADE, 11);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionInvalidBuildNotEnoughIntermediateSpots0Open1CascadeFree() {
    this.freecellModel.startGame(this.easyTestDeck, 14, 1, false);
    // (N+1)∗2^K; N = # of free open piles, K = # of free open Cascade Piles
    // moving the Ace of Diamonds To the foundation pile
    this.freecellModel.move(PileType.CASCADE, 10, 2, PileType.FOUNDATION, 0);
    // now move the 2 of Hearts to the 3 of Clubs
    this.freecellModel.move(PileType.CASCADE, 10, 1, PileType.CASCADE, 7);
    // now move the 3 of Spades to the free open pile
    this.freecellModel.move(PileType.CASCADE, 10, 0, PileType.OPEN, 0);
    // now since there are 0 free open piles and 1 free cascade pile, the max number of cards you
    // can move now should be (0 + 1) * 2^1 = 2
    // so should throw and error if we try to move a 3 Card Build
    // - such as trying to move the 4 of Diamonds in C8 onto the 5 of Clubs at C6
    // so this should fail because the build is of size 3 while the number of cards you can move
    // because of the max number of cards you can move is 2 because of the number of intermediate
    // piles
    // - so fails 3rd condition of not enough intermediate piles
    this.freecellModel.move(PileType.CASCADE, 7, 2, PileType.CASCADE, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionInvalidBuildNotEnoughIntermediateSpots0Open2CascadeFree() {
    this.freecellModel.startGame(this.easyTestDeck, 14, 2, false);
    // (N+1)∗2^K; N = # of free open piles, K = # of free open Cascade Piles
    // moving the Ace of Diamonds To the foundation pile
    this.freecellModel.move(PileType.CASCADE, 10, 2, PileType.FOUNDATION, 0);
    // now move the 2 of Hearts to the 3 of Clubs
    this.freecellModel.move(PileType.CASCADE, 10, 1, PileType.CASCADE, 7);
    // now move the 3 of Spades to the free open pile
    this.freecellModel.move(PileType.CASCADE, 10, 0, PileType.OPEN, 0);
    // now move the Ace of Clubs to the 2nd Foundation Pile
    this.freecellModel.move(PileType.CASCADE, 9, 3, PileType.FOUNDATION, 1);
    // now move the 2 of Diamonds to the 1st Foundation Pile
    this.freecellModel.move(PileType.CASCADE, 9, 2, PileType.FOUNDATION, 0);
    // now move the 4 of diamonds in the 8th Pile onto the 5 of clubs in the 6th Cascade Pile
    this.freecellModel.move(PileType.CASCADE, 7, 2, PileType.CASCADE, 5);
    // now move the 4 of Clubs to the 2nd Open Pile
    this.freecellModel.move(PileType.CASCADE, 6, 3, PileType.OPEN, 1);
    // now move the 4 of Spades onto the F of Diamonds in the 7th Cascade Pile
    this.freecellModel.move(PileType.CASCADE, 9, 0, PileType.CASCADE, 7);
    // now move the 6 of Diamonds onto the 7 of Clubs in the 4th Cascade Pile,
    // but now since there are 0 free open piles and 2 free cascade piles, the max number of cards
    // you can move now should be 4, and the size of our build is 5, we should get an error
    this.freecellModel.move(PileType.CASCADE, 5, 2, PileType.CASCADE, 3);


  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionInvalidBuildNotEnoughIntermediateSpots0OpenMoreThan2CascadeFree() {
    // commands to get to points in the games during most of the Intermediate Spots tests
    // (tests to see if you can move something which violates the max number of cards you are
    // allowed to move at the moment in time based on the intermediate spots.
    // || -> separates the commands to introduce errors
    // C11 3 F1  C11 2 C8  C11 1 O1  C10 4 F2  C10 3 F1  C8 3 C6  C7 4 O2  C10 1 C8 || C6 3 C4 ||
    // O2 1 C7  C6 3 C4   C3 4 O2  O2 1 C3  C8 1 C6  C4 3 C2   C5 4 O2 || C2 3 C14 ||
    // O2 1 C8  C1 4 C10 || C6 2 C3 ||
    this.freecellModel.startGame(this.easyTestDeck, 14, 2, false);
    // (N+1)∗2^K; N = # of free open piles, K = # of free open Cascade Piles
    // moving the Ace of Diamonds To the foundation pile
    this.freecellModel.move(PileType.CASCADE, 10, 2, PileType.FOUNDATION, 0);
    // now move the 2 of Hearts to the 3 of Clubs
    this.freecellModel.move(PileType.CASCADE, 10, 1, PileType.CASCADE, 7);
    // now move the 3 of Spades to the free open pile
    this.freecellModel.move(PileType.CASCADE, 10, 0, PileType.OPEN, 0);
    // now move the Ace of Clubs to the 2nd Foundation Pile
    this.freecellModel.move(PileType.CASCADE, 9, 3, PileType.FOUNDATION, 1);
    // now move the 2 of Diamonds to the 1st Foundation Pile
    this.freecellModel.move(PileType.CASCADE, 9, 2, PileType.FOUNDATION, 0);
    // now move the 4 of diamonds in the 8th Pile onto the 5 of clubs in the 6th Cascade Pile
    this.freecellModel.move(PileType.CASCADE, 7, 2, PileType.CASCADE, 5);
    // now move the 4 of Clubs to the 2nd Open Pile
    this.freecellModel.move(PileType.CASCADE, 6, 3, PileType.OPEN, 1);
    // now move the 4 of Spades onto the F of Diamonds in the 7th Cascade Pile
    this.freecellModel.move(PileType.CASCADE, 9, 0, PileType.CASCADE, 7);
    // O2 1 C7  C6 3 C4   C3 4 O2  O2 1 C3  C8 1 C6  C4 3 C2   C5 4 O2  C2 3 C14
    this.freecellModel.move(PileType.OPEN, 1, 0, PileType.CASCADE, 6);
    // C6 3 C4
    this.freecellModel.move(PileType.CASCADE, 5, 2, PileType.CASCADE, 3);
    // C3 4 O2
    this.freecellModel.move(PileType.CASCADE, 2, 3, PileType.OPEN, 1);
    // O2 1 C3
    this.freecellModel.move(PileType.OPEN, 1, 0, PileType.CASCADE, 2);
    // C8 1 C6
    this.freecellModel.move(PileType.CASCADE, 7, 0, PileType.CASCADE, 5);
    // C4 3 C2
    this.freecellModel.move(PileType.CASCADE, 3, 2, PileType.CASCADE, 1);
    // C5 4 O2
    this.freecellModel.move(PileType.CASCADE, 4, 3, PileType.OPEN, 1);
    // C2 3 C14
    this.freecellModel.move(PileType.CASCADE, 1, 2, PileType.CASCADE, 13);
    // this should not work
    // there are 3 free Cascade Piles and 0 free Open Piles, so the max number of cards
    // we can put down should be 8 - and our build for this move should be 9 size, so it should not
    // work here
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionInvalidBuildNotEnoughIntermediateSpots1Open1Cascade() {
    this.freecellModel.startGame(this.easyTestDeck, 14, 2, false);
    // (N+1)∗2^K; N = # of free open piles, K = # of free open Cascade Piles
    // moving the Ace of Diamonds To the foundation pile
    this.freecellModel.move(PileType.CASCADE, 10, 2, PileType.FOUNDATION, 0);
    // now move the 2 of Hearts to the 3 of Clubs
    this.freecellModel.move(PileType.CASCADE, 10, 1, PileType.CASCADE, 7);
    // now move the 3 of Spades to the free open pile
    this.freecellModel.move(PileType.CASCADE, 10, 0, PileType.OPEN, 0);
    // now move the Ace of Clubs to the 2nd Foundation Pile
    this.freecellModel.move(PileType.CASCADE, 9, 3, PileType.FOUNDATION, 1);
    // now move the 2 of Diamonds to the 1st Foundation Pile
    this.freecellModel.move(PileType.CASCADE, 9, 2, PileType.FOUNDATION, 0);
    // now move the 4 of diamonds in the 8th Pile onto the 5 of clubs in the 6th Cascade Pile
    this.freecellModel.move(PileType.CASCADE, 7, 2, PileType.CASCADE, 5);
    // now move the 4 of Clubs to the 2nd Open Pile
    this.freecellModel.move(PileType.CASCADE, 6, 3, PileType.OPEN, 1);
    // now move the 4 of Spades onto the F of Diamonds in the 7th Cascade Pile
    this.freecellModel.move(PileType.CASCADE, 9, 0, PileType.CASCADE, 7);
    // O2 1 C7  C6 3 C4   C3 4 O2  O2 1 C3  C8 1 C6  C4 3 C2   C5 4 O2  C2 3 C14
    this.freecellModel.move(PileType.OPEN, 1, 0, PileType.CASCADE, 6);
    // C6 3 C4
    this.freecellModel.move(PileType.CASCADE, 5, 2, PileType.CASCADE, 3);
    // C3 4 O2
    this.freecellModel.move(PileType.CASCADE, 2, 3, PileType.OPEN, 1);
    // O2 1 C3
    this.freecellModel.move(PileType.OPEN, 1, 0, PileType.CASCADE, 2);
    // C8 1 C6
    this.freecellModel.move(PileType.CASCADE, 7, 0, PileType.CASCADE, 5);
    // C4 3 C2
    this.freecellModel.move(PileType.CASCADE, 3, 2, PileType.CASCADE, 1);
    // C5 4 O2
    this.freecellModel.move(PileType.CASCADE, 4, 3, PileType.OPEN, 1);
    assertEquals(new Card(Value.SIX, Suit.CLUBS), this.freecellModel.getOpenCardAt(1));
    // O2 1 C8
    this.freecellModel.move(PileType.OPEN, 1, 0, PileType.CASCADE, 7);
    // C1 4 C10
    this.freecellModel.move(PileType.CASCADE, 0, 3, PileType.CASCADE, 9);
    // C6 2 C3
    this.freecellModel.move(PileType.CASCADE, 5, 1, PileType.CASCADE, 2);
    // but now since there is 1 free open pile and 1 free cascade pile, the max number of cards you
    // can move now should be (1 + 1) * 2^1 = 4, and the size of our build is 5,
    // so we should get an error
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionInvalidBuildNotEnoughIntermediateSpots1Open1CascadeTry6Cards() {
    // C11 3 F1  C11 2 C8  C11 1 O1  C10 4 F2  C10 3 F1  C8 3 C6  C7 4 O2  C10 1 C8
    this.freecellModel.startGame(this.easyTestDeck, 14, 2, false);
    // (N+1)∗2^K; N = # of free open piles, K = # of free open Cascade Piles
    // moving the Ace of Diamonds To the foundation pile
    this.freecellModel.move(PileType.CASCADE, 10, 2, PileType.FOUNDATION, 0);
    // now move the 2 of Hearts to the 3 of Clubs
    this.freecellModel.move(PileType.CASCADE, 10, 1, PileType.CASCADE, 7);
    // now move the 3 of Spades to the free open pile
    this.freecellModel.move(PileType.CASCADE, 10, 0, PileType.OPEN, 0);
    // now move the Ace of Clubs to the 2nd Foundation Pile
    this.freecellModel.move(PileType.CASCADE, 9, 3, PileType.FOUNDATION, 1);
    // now move the 2 of Diamonds to the 1st Foundation Pile
    this.freecellModel.move(PileType.CASCADE, 9, 2, PileType.FOUNDATION, 0);
    // now move the 4 of diamonds in the 8th Pile onto the 5 of clubs in the 6th Cascade Pile
    this.freecellModel.move(PileType.CASCADE, 7, 2, PileType.CASCADE, 5);
    // now move the 4 of Clubs to the 2nd Open Pile
    this.freecellModel.move(PileType.CASCADE, 6, 3, PileType.OPEN, 1);
    // now move the 4 of Spades onto the F of Diamonds in the 7th Cascade Pile
    this.freecellModel.move(PileType.CASCADE, 9, 0, PileType.CASCADE, 7);
    // O2 1 C7
    this.freecellModel.move(PileType.OPEN, 1, 0, PileType.CASCADE, 6);
    // C12 3 C10
    this.freecellModel.move(PileType.CASCADE, 11, 2, PileType.CASCADE, 9);
    // F2 1 C6
    this.freecellModel.move(PileType.FOUNDATION, 1, 0, PileType.CASCADE, 5);
    // now only have 1 free Open Pile and 1 free Cascade Pile
    // so max amount of cards allowed should be 4
    // so trying to move 6 cards should mean that an exception is produced
    // so when do C6 3 C4, should produce an exception because we are trying to move a build that
    // consists of; the 6 of Diamonds, 5 of Clubs, 4 of Diamonds, 3 of Clubs, 2 of Hearts,
    // and Ace of Clubs which has a total build size of 6.
    // it would work if we had another open pile because we are moving the 6 of Diamonds' build
    // onto a 7 of Clubs
    // C6 3 C4
    this.freecellModel.move(PileType.CASCADE, 5, 2, PileType.CASCADE, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionInvalidBuildNotEnoughIntermediateSpots1Open2Cascade() {
    this.freecellModel.startGame(this.easyTestDeck, 14, 2, false);
    // (N+1)∗2^K; N = # of free open piles, K = # of free open Cascade Piles
    // moving the Ace of Diamonds To the foundation pile
    this.freecellModel.move(PileType.CASCADE, 10, 2, PileType.FOUNDATION, 0);
    // now move the 2 of Hearts to the 3 of Clubs
    this.freecellModel.move(PileType.CASCADE, 10, 1, PileType.CASCADE, 7);
    // now move the 3 of Spades to the free open pile
    this.freecellModel.move(PileType.CASCADE, 10, 0, PileType.OPEN, 0);
    // now move the Ace of Clubs to the 2nd Foundation Pile
    this.freecellModel.move(PileType.CASCADE, 9, 3, PileType.FOUNDATION, 1);
    // now move the 2 of Diamonds to the 1st Foundation Pile
    this.freecellModel.move(PileType.CASCADE, 9, 2, PileType.FOUNDATION, 0);
    // now move the 4 of diamonds in the 8th Pile onto the 5 of clubs in the 6th Cascade Pile
    this.freecellModel.move(PileType.CASCADE, 7, 2, PileType.CASCADE, 5);
    // now move the 4 of Clubs to the 2nd Open Pile
    this.freecellModel.move(PileType.CASCADE, 6, 3, PileType.OPEN, 1);
    // now move the 4 of Spades onto the F of Diamonds in the 7th Cascade Pile
    this.freecellModel.move(PileType.CASCADE, 9, 0, PileType.CASCADE, 7);
    // O2 1 C7
    this.freecellModel.move(PileType.OPEN, 1, 0, PileType.CASCADE, 6);
    // C6 3 C4
    this.freecellModel.move(PileType.CASCADE, 5, 2, PileType.CASCADE, 3);
    // C4 3 C2
    this.freecellModel.move(PileType.CASCADE, 3, 2, PileType.CASCADE, 1);
    // C2 3 C14
    this.freecellModel.move(PileType.CASCADE, 1, 2, PileType.CASCADE, 13);
    // but now since there is 1 free open pile and 2 free cascade piles, the max number of cards you
    // can move now should be 8, and the size of our build is 9, so we should get an error
  }

  @Test
  public void testMoveSuccessMove2Cards1Open0CascadePilesFree() {
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
    // C5 3 C2
    this.freecellModel.move(PileType.CASCADE, 3, 2, PileType.CASCADE, 1);
    // make sure moving cards worked -> 8 of Diamonds is now in 2nd Pile
    assertEquals(new Card(Value.EIGHT, Suit.DIAMONDS), this.freecellModel.getCascadeCardAt(1, 4));
    // make sure 7 of Clubs is in 2nd pile now too
    assertEquals(new Card(Value.SEVEN, Suit.CLUBS), this.freecellModel.getCascadeCardAt(1, 5));
    // make sure not in 4th pile anymore too (Lost 2 cards, so now total cards in pile = only 2)
    assertEquals(2, this.freecellModel.getNumCardsInCascadePile(3));
  }

  @Test
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
    this.freecellModel.move(PileType.CASCADE, 3, 2, PileType.CASCADE, 1);
    // make sure moving cards worked -> 8 of Diamonds is now in 2nd Pile
    assertEquals(new Card(Value.EIGHT, Suit.DIAMONDS), this.freecellModel.getCascadeCardAt(1, 4));
    // make sure 7 of Clubs is in 2nd pile now too
    assertEquals(new Card(Value.SEVEN, Suit.CLUBS), this.freecellModel.getCascadeCardAt(1, 5));
    // make sure not in 4th pile anymore too (Lost 2 cards, so now total cards in pile = only 2)
    assertEquals(2, this.freecellModel.getNumCardsInCascadePile(3));
  }

  @Test
  public void testMoveSuccessMove6Cards2Open1CascadePilesFreeEvenWhenBuildSizeErrorBeforeMove() {
    // commands to get to points in the games during most of the Intermediate Spots tests
    // (tests to see if you can move something which violates the max number of cards you are
    // allowed to move at the moment in time based on the intermediate spots.
    // || -> separates the commands to introduce errors
    // C11 3 F1  C11 2 C8  C11 1 O1  C10 4 F2  C10 3 F1  C8 3 C6  C7 4 O2  C10 1 C8 || C6 3 C4 ||
    // O2 1 C7  C6 3 C4   C3 4 O2  O2 1 C3  C8 1 C6  C4 3 C2   C5 4 O2 || C2 3 C14 ||
    // O2 1 C8  C1 4 C10 || C6 2 C3 ||
    this.freecellModel.startGame(this.easyTestDeck, 14, 3, false);
    // (N+1)∗2^K; N = # of free open piles, K = # of free open Cascade Piles
    // moving the Ace of Diamonds To the foundation pile
    this.freecellModel.move(PileType.CASCADE, 10, 2, PileType.FOUNDATION, 0);
    // now move the 2 of Hearts to the 3 of Clubs
    this.freecellModel.move(PileType.CASCADE, 10, 1, PileType.CASCADE, 7);
    // now move the 3 of Spades to the free open pile
    this.freecellModel.move(PileType.CASCADE, 10, 0, PileType.OPEN, 0);
    // now move the Ace of Clubs to the 2nd Foundation Pile
    this.freecellModel.move(PileType.CASCADE, 9, 3, PileType.FOUNDATION, 1);
    // now move the 2 of Diamonds to the 1st Foundation Pile
    this.freecellModel.move(PileType.CASCADE, 9, 2, PileType.FOUNDATION, 0);
    // now move the 4 of diamonds in the 8th Pile onto the 5 of clubs in the 6th Cascade Pile
    this.freecellModel.move(PileType.CASCADE, 7, 2, PileType.CASCADE, 5);
    // now move the 4 of Clubs to the 2nd Open Pile
    this.freecellModel.move(PileType.CASCADE, 6, 3, PileType.OPEN, 1);
    // now move the 4 of Spades onto the F of Diamonds in the 7th Cascade Pile
    this.freecellModel.move(PileType.CASCADE, 9, 0, PileType.CASCADE, 7);
    // O2 1 C7  C6 3 C4   C3 4 O2  O2 1 C3  C8 1 C6  C4 3 C2   C5 4 O2  C2 3 C14
    this.freecellModel.move(PileType.OPEN, 1, 0, PileType.CASCADE, 6);
    // C6 3 C4
    this.freecellModel.move(PileType.CASCADE, 5, 2, PileType.CASCADE, 3);
    // C3 4 O2
    this.freecellModel.move(PileType.CASCADE, 2, 3, PileType.OPEN, 1);
    // O2 1 C3
    this.freecellModel.move(PileType.OPEN, 1, 0, PileType.CASCADE, 2);
    // C8 1 C6
    this.freecellModel.move(PileType.CASCADE, 7, 0, PileType.CASCADE, 5);
    // C4 3 C2
    this.freecellModel.move(PileType.CASCADE, 3, 2, PileType.CASCADE, 1);
    // C5 4 O2
    this.freecellModel.move(PileType.CASCADE, 4, 3, PileType.OPEN, 1);
    // C9 3 C7
    this.freecellModel.move(PileType.CASCADE, 8, 2, PileType.CASCADE, 6);
    // C12 3 C11
    this.freecellModel.move(PileType.CASCADE, 11, 2, PileType.CASCADE, 10);
    // O1 1 C8
    this.freecellModel.move(PileType.OPEN, 0, 0, PileType.CASCADE, 7);
    // now these are the cards that the game has in its 2nd Cascade Pile and its 14th Cascade Pile
    List<ICard> cascadePile2List = Arrays
        .asList(new Card(Value.QUEEN, Suit.SPADES), new Card(Value.JACK, Suit.HEARTS),
            new Card(Value.TEN, Suit.DIAMONDS), new Card(Value.NINE, Suit.CLUBS),
            new Card(Value.EIGHT, Suit.DIAMONDS), new Card(Value.SEVEN, Suit.CLUBS),
            new Card(Value.SIX, Suit.DIAMONDS), new Card(Value.FIVE, Suit.CLUBS),
            new Card(Value.FOUR, Suit.DIAMONDS), new Card(Value.THREE, Suit.CLUBS),
            new Card(Value.TWO, Suit.HEARTS));

    List<ICard> cascadePile14List = Arrays.asList(new Card(Value.KING, Suit.HEARTS),
        new Card(Value.QUEEN, Suit.DIAMONDS), new Card(Value.JACK, Suit.CLUBS));
    try {
      // this should not work
      // there is 1 free Cascade Pile and 1 free Open Pile, so the max number of cards
      // we can put down should be 4 - and our build for this move should be of
      // 9 size, so it should not work here, so catch the exception
      // and try to make sure all of the cards in the destination and source piles did not change
      // at all since the move before.
      this.freecellModel.move(PileType.CASCADE, 1, 2, PileType.CASCADE, 13);
    } catch (IllegalArgumentException invalidBuildSize) {
      // make sure nothing left over - still same cards in same order - even when error came up
      // (this is where we were trying to move the cards from
      for (int i = 0; i < cascadePile2List.size(); i++) {
        assertEquals(cascadePile2List.get(i), this.freecellModel.getCascadeCardAt(1, i));
      }
      // make sure nothing there in the destination
      // when we were trying to move the cards but failed
      for (int i = 0; i < cascadePile14List.size(); i++) {
        assertEquals(cascadePile14List.get(i), this.freecellModel.getCascadeCardAt(13, i));
      }
    }

    // make sure the Open Pile is free
    assertEquals(0, this.freecellModel.getNumCardsInOpenPile(2));
    // make sure Cascade Pile 10 is empty now, so it counts as a free Cascade Pile
    assertEquals(0, this.freecellModel.getNumCardsInCascadePile(9));
    // make sure the Cascade Pile 6 has 6 cards in it now
    assertEquals(6, this.freecellModel.getNumCardsInCascadePile(5));
    // make sure Cascade Pile 4 has 2 cards in it now
    assertEquals(2, this.freecellModel.getNumCardsInCascadePile(3));
    // make sure 8 of Spades is now in 6th Pile (the bottom of the Pile)
    assertEquals(new Card(Value.EIGHT, Suit.SPADES), this.freecellModel.getCascadeCardAt(5, 0));
    // make sure the 7 of Hearts is on top of the 8 of Spades
    assertEquals(new Card(Value.SEVEN, Suit.HEARTS), this.freecellModel.getCascadeCardAt(5, 1));
    // make sure 6 of Spades is on top of the 7 of Hearts
    assertEquals(new Card(Value.SIX, Suit.SPADES), this.freecellModel.getCascadeCardAt(5, 2));
    // make sure the 5 of Hearts is on top of the 6 of Spades
    assertEquals(new Card(Value.FIVE, Suit.HEARTS), this.freecellModel.getCascadeCardAt(5, 3));
    // make sure the 4 of Spades is on top of the 5 of Hearts
    assertEquals(new Card(Value.FOUR, Suit.SPADES), this.freecellModel.getCascadeCardAt(5, 4));
    // make sure the 3 of Hearts is on top of the 4 of Spades
    assertEquals(new Card(Value.THREE, Suit.HEARTS), this.freecellModel.getCascadeCardAt(5, 5));
    // C6 1 C4
    // this should work because the max number of cards you are allowed to move is 6 (2+1) * 2^1
    // while the size of your build is 6 cards
    this.freecellModel.move(PileType.CASCADE, 5, 0, PileType.CASCADE, 3);

    // make sure moving cards worked -> 8 of Spades in 4th Pile now
    assertEquals(new Card(Value.EIGHT, Suit.SPADES), this.freecellModel.getCascadeCardAt(3, 2));
    // make sure 7 of Hearts is in 4th pile now too
    assertEquals(new Card(Value.SEVEN, Suit.HEARTS), this.freecellModel.getCascadeCardAt(3, 3));
    // make sure 6 of Spades is on top of the 7 of Hearts
    assertEquals(new Card(Value.SIX, Suit.SPADES), this.freecellModel.getCascadeCardAt(3, 4));
    // make sure the 5 of Hearts is on top of the 6 of Spades
    assertEquals(new Card(Value.FIVE, Suit.HEARTS), this.freecellModel.getCascadeCardAt(3, 5));
    // make sure the 4 of Spades is on top of the 5 of Hearts
    assertEquals(new Card(Value.FOUR, Suit.SPADES), this.freecellModel.getCascadeCardAt(3, 6));
    // make sure the 3 of Hearts is on top of the 4 of Spades
    assertEquals(new Card(Value.THREE, Suit.HEARTS), this.freecellModel.getCascadeCardAt(3, 7));
    // also make sure 4th Pile now has 8 cards
    assertEquals(8, this.freecellModel.getNumCardsInCascadePile(3));
    // make sure not in 6th pile anymore too (Lost 6 cards, so none now)
    assertEquals(0, this.freecellModel.getNumCardsInCascadePile(5));
  }


  @Test
  public void testMoveSuccessNoneOfCardsChangeEvenIfInvalidMovesBefore() {
    // commands to get to points in the games during most of the Intermediate Spots tests
    // (tests to see if you can move something which violates the max number of cards you are
    // allowed to move at the moment in time based on the intermediate spots.
    // || -> separates the commands to introduce errors
    // C11 3 F1  C11 2 C8  C11 1 O1  C10 4 F2  C10 3 F1  C8 3 C6  C7 4 O2  C10 1 C8 || C6 3 C4 ||
    // O2 1 C7  C6 3 C4   C3 4 O2  O2 1 C3  C8 1 C6  C4 3 C2   C5 4 O2 || C2 3 C14 ||
    // O2 1 C8  C1 4 C10 || C6 2 C3 ||
    this.freecellModel.startGame(this.easyTestDeck, 14, 3, false);
    // (N+1)∗2^K; N = # of free open piles, K = # of free open Cascade Piles
    // now moved validly 3 times (after following move functions below this one), so check if the
    // error was working
    // moving the Ace of Diamonds To the foundation pile
    this.freecellModel.move(PileType.CASCADE, 10, 2, PileType.FOUNDATION, 0);
    // now move the 2 of Hearts to the 3 of Clubs
    this.freecellModel.move(PileType.CASCADE, 10, 1, PileType.CASCADE, 7);
    // now move the 3 of Spades to the free open pile
    this.freecellModel.move(PileType.CASCADE, 10, 0, PileType.OPEN, 0);
    // now will try to produce an invalid error
    // keep track of the list of cards in the Source List of Cards and the Destination List of Cards
    List<ICard> copyOfCardListPile1 = new ArrayList<ICard>();
    List<ICard> copyOfCardListPile2 = new ArrayList<ICard>();
    for (int i = 0; i < this.freecellModel.getNumCardsInCascadePile(0); i++) {
      copyOfCardListPile1.add(this.freecellModel.getCascadeCardAt(0, i));
    }
    for (int j = 0; j < this.freecellModel.getNumCardsInCascadePile(1); j++) {
      copyOfCardListPile2.add(this.freecellModel.getCascadeCardAt(1, j));
    }
    // producing an error the first time
    try {
      this.freecellModel.move(PileType.CASCADE, 0, 0, PileType.CASCADE, 1);
      // this will produce an exception because the build is not valid - value-wise
    } catch (IllegalArgumentException invalidMove) {
      // make sure all same cards in Cascade Pile
      for (int i = 0; i < this.freecellModel.getNumCardsInCascadePile(0); i++) {
        assertEquals(copyOfCardListPile1.get(i), this.freecellModel.getCascadeCardAt(0, i));
      }
      for (int j = 0; j < this.freecellModel.getNumCardsInCascadePile(1); j++) {
        assertEquals(copyOfCardListPile2.get(j), this.freecellModel.getCascadeCardAt(1, j));
      }
    }
    // producing an error again
    try {
      this.freecellModel.move(PileType.CASCADE, 0, 1, PileType.CASCADE, 1);
      // this will produce an exception because the build is not valid - value-wise
    } catch (IllegalArgumentException invalidMove) {
      // make sure all same cards in Cascade Pile
      for (int i = 0; i < this.freecellModel.getNumCardsInCascadePile(0); i++) {
        assertEquals(copyOfCardListPile1.get(i), this.freecellModel.getCascadeCardAt(0, i));
      }
      for (int j = 0; j < this.freecellModel.getNumCardsInCascadePile(1); j++) {
        assertEquals(copyOfCardListPile2.get(j), this.freecellModel.getCascadeCardAt(1, j));
      }
    }
    // producing an error 1 last time before making sure can still
    // move validly after errors in moves
    try {
      this.freecellModel.move(PileType.CASCADE, 0, 2, PileType.CASCADE, 1);
      // this will produce an exception because the build is not valid - value-wise
    } catch (IllegalArgumentException invalidMove) {
      // make sure all same cards in Cascade Pile
      for (int i = 0; i < this.freecellModel.getNumCardsInCascadePile(0); i++) {
        assertEquals(copyOfCardListPile1.get(i), this.freecellModel.getCascadeCardAt(0, i));
      }
      for (int j = 0; j < this.freecellModel.getNumCardsInCascadePile(1); j++) {
        assertEquals(copyOfCardListPile2.get(j), this.freecellModel.getCascadeCardAt(1, j));
      }
    }

    // now see if you can still move validly
    // (N+1)∗2^K; N = # of free open piles, K = # of free open Cascade Piles
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
    // now see if you can still move validly and it can still produce the right result
    // (N+1)∗2^K; N = # of free open piles, K = # of free open Cascade Piles
    // 0 Open Piles so far and 1 free Cascade Piles
    // Moving the first 8 of Diamonds in C5 to the 9 of Clubs in C2 (valid build with 2 Cards)
    // the max number of cards we can move should be (0+1)*2^1 = 1 * 2 = 2, so this should work
    this.freecellModel.move(PileType.CASCADE, 3, 2, PileType.CASCADE, 1);
    // make sure moving cards worked -> 8 of Diamonds is now in 2nd Pile
    assertEquals(new Card(Value.EIGHT, Suit.DIAMONDS), this.freecellModel.getCascadeCardAt(1, 4));
    // make sure 7 of Clubs is in 2nd pile now too
    assertEquals(new Card(Value.SEVEN, Suit.CLUBS), this.freecellModel.getCascadeCardAt(1, 5));
    // make sure not in 4th pile anymore too (Lost 2 cards, so now total cards in pile = only 2)
    assertEquals(2, this.freecellModel.getNumCardsInCascadePile(3));
    // make sure all same cards in Cascade Piles tried moving from and to before!
    for (int i = 0; i < this.freecellModel.getNumCardsInCascadePile(0); i++) {
      assertEquals(copyOfCardListPile1.get(i), this.freecellModel.getCascadeCardAt(0, i));
    }
    // have to make sure to add the 2 cards from the 4th Cascade Pile to original list of cards
    // in the 2nd Cascade Pile which we were checking before (during the errors) - > because
    // we moved 2 cards
    copyOfCardListPile2.add(new Card(Value.EIGHT, Suit.DIAMONDS));
    copyOfCardListPile2.add(new Card(Value.SEVEN, Suit.CLUBS));
    // now check if all same
    for (int j = 0; j < this.freecellModel.getNumCardsInCascadePile(1); j++) {
      assertEquals(copyOfCardListPile2.get(j), this.freecellModel.getCascadeCardAt(1, j));
    }
  }
}
