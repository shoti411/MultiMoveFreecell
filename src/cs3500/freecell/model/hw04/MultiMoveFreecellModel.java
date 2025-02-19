package cs3500.freecell.model.hw04;

import cs3500.freecell.model.PileType;
import cs3500.freecell.model.hw02.GameState;
import cs3500.freecell.model.hw02.ICard;
import cs3500.freecell.model.hw02.SimpleFreecellModel;
import java.util.ArrayList;
import java.util.List;

/**
 * A type of FreecellModel for the game of freecell. This model expands on the original
 * SimpleFreecellModel by allowing the player to move multiple cards at once that are in the same
 * Cascade Pile to another Cascade Pile if the move is allowed. The player can move several cards at
 * once from one cascade pile to another as specified in the move method below. I chose to extend
 * SimpleFreecellModel because I believe MultiMoveFreecellModel "is-a" version of
 * SimpleFreecellModel. I know that delegation is another way to create this method, but I still
 * believe that MultiMoveFreecellModel "is-a" version of the SimpleFreecellModel considering that
 * they have the exact same rules and functionality except for the move method which still follows
 * the same concept as the SimpleFreecellModel (as it was stated in the assignment that it was just
 * a convenience method and that these moves would be possible ), except it just means that you can
 * move multiple cards at once for the Cascade to Cascade moves in the game. Since it is not a
 * special feature and just a convenience method (which means that all the cards in that specific
 * pile would be able to be moved in a game using the SimpleFreecellModel), I believe the
 * MultiMoveFreecellModel should extend the SimpleFreecellModel class.
 */
public class MultiMoveFreecellModel extends SimpleFreecellModel {

  /**
   * Constructs a {@code MultiMoveFreecellModel} Object.
   */
  public MultiMoveFreecellModel() {
    super(GameState.HASNOTSTARTED, new ArrayList<ArrayList<ICard>>(),
        new ArrayList<ArrayList<ICard>>(), new ArrayList<ArrayList<ICard>>());
  }

  /**
   * Constructs a {@code MultiMoveFreecellModel} object.
   *
   * @param state          The GameState of the game
   * @param foundationPile The list of Foundation Piles see @link{PileType}
   * @param cascadePile    The list of Cascade Piles see @link{PileType}
   * @param openPile       The list of Open Piles see @link{PileType}
   * @throws IllegalArgumentException if the Game has not started and either the number of
   *                                  Foundation Piles is not equal to 4, the number of Cascade
   *                                  Piles is below 4, or the number of Open piles is below 1.
   *                                  (this is to ensure that we cannot create an invalid game using
   *                                  this second public constructor which should not even exist in
   *                                  our class because the assignment only told us to create 1
   *                                  constructor).
   */
  public MultiMoveFreecellModel(GameState state, List<ArrayList<ICard>> foundationPile,
      List<ArrayList<ICard>> cascadePile, List<ArrayList<ICard>> openPile)
      throws IllegalArgumentException {
    super(state, foundationPile, cascadePile, openPile);
    if (state != GameState.HASNOTSTARTED) {
      if (foundationPile.size() != 4 || cascadePile.size() < 4 || openPile.size() < 1) {
        throw new IllegalArgumentException("Invalid construction");
      }
    }
  }


  /**
   * Move a card from the given source pile to the given destination pile, if the move is valid. A
   * valid move for a build of cards (the list of cards which you are trying to move is only valid
   * when the top card follows the original rules for moving 1 card, the build follows the rules
   * specified by the function isValidBuild, and the build's size follows the rules specified by the
   * function isValidSizeBuildToMove. A multi-card move is basically several single-card moves,
   * using free open piles and empty cascade piles as "intermediate slots." Thus a multi-card move
   * may not be legal even though its first card obeys the rules dictated by moving a single card in
   * SimpleFreecellModel if there aren't enough of these intermediate slots available.
   *
   * @param source         the type of the source pile see @link{PileType}
   * @param pileNumber     the pile number of the given type, starting at 0
   * @param cardIndex      the index of the card to be moved from the source pile, starting at 0
   * @param destination    the type of the destination pile (see
   * @param destPileNumber the pile number of the given type, starting at 0
   * @throws IllegalArgumentException if the move is not possible {@link PileType} and isValidBuild
   *                                  and isValidSizeBuildToMove functions
   * @throws IllegalStateException    if a move is attempted before the game has starts
   */
  @Override
  public void move(PileType source, int pileNumber, int cardIndex, PileType destination,
      int destPileNumber) throws IllegalArgumentException, IllegalStateException {

    // get the source Card - will also produce an error if game hasn't started yet
    ICard sourceCard = this.getCardAt(source, pileNumber, cardIndex);
    // need to get all of the cards below the source card also - and create a list of their order
    // and then check if it is an okay build
    List<ICard> cardBuild = new ArrayList<ICard>();
    for (int cardPositionInPile = cardIndex;
        cardPositionInPile < this.getLastCardIndex(source, pileNumber) + 1; cardPositionInPile++) {
      // add all of the cards below the source card go into the cardBuild list of cards
      cardBuild.add(this.getCardAt(source, pileNumber, cardPositionInPile));
    }

    // if the card build does not have enough intermediate spots available, then
    // throw an IllegalArgumentException
    // (has not removed the cards yet, so do not add the cardBuild to the source Pile again
    if (!this.isValidSizeBuildToMove(cardBuild.size())) {
      throw new IllegalArgumentException(new StringBuilder("Invalid Card Build. ")
          .append("Not enough intermediate spots available").toString());
      // if the card does not fit the conditions for a valid build (e.g. alternating colored cards
      // and with each card having 1 less Value than the one before it in the list (not counting
      // the source card), then throw an IllegalArgumentException
    } else if (!this.isValidBuild(cardBuild)) {
      throw new IllegalArgumentException(
          new StringBuilder("Invalid Card Build. Must have alternating colored Cards ")
              .append("while having every Card under the source Card have a value of 1 less ")
              .append("than the card above it.").toString());
      // then know its a valid build and remove it
    } else {
      // remove the source card and all the cards below it
      // (the values are stored where we need them)
      this.getFreecellPile(source, pileNumber).removeAll(cardBuild);
    }

    // if you tried to pick up more than 1 card
    if (cardBuild.size() > 1) {
      // if size of build is more than 1 and either your source or destination or both was not
      // a type of Cascade Pile, then throw an error because that is impossible.
      if ((source != PileType.CASCADE) || (destination != PileType.CASCADE)) {
        // add all the cards back into place
        this.getFreecellPile(source, pileNumber).addAll(cardBuild);
        throw new IllegalArgumentException(
            "Moving Multiple Cards requires both Source and Destination Piles to be Cascade Piles");
      }
      // if there are more than 0 cards in the pile and that move is invalid for a Cascade Pile
      if (!this.isValidCascadeMove(sourceCard, destPileNumber)) {
        // if invalid, then throw the cards back into the place before
        this.getFreecellPile(source, pileNumber).addAll(cardBuild);
        // then throw an exception
        throw new IllegalArgumentException(
            new StringBuilder(
                "Invalid Move, Destination Card needs to have a Value of 1 more")
                .append(" than the source Card ")
                .append("and be a different colored Suit.").toString());
      } else {
        // then move should be valid so move the build
        this.getFreecellPile(destination, destPileNumber).addAll(cardBuild);
      }
    } else {
      // if we are just moving 1 card, then just send it to the move function in
      // SimpleFreecellModel, which will definitely move it correctly
      // add the 1 card back to its source pile and let SimpleFreecellModel handle it
      this.getFreecellPile(source, pileNumber).add(sourceCard);
      super.move(source, pileNumber, cardIndex, destination, destPileNumber);
    }

    // if there are fully 13 cards in each Foundation Pile, then the game is over.
    // I chose to place this here instead of the isGameOver function because changing it is more
    // efficient to check this only after every move rather than like after every second of playing
    if (this.getNumCardsInFoundationPile(0) == 13 && this.getNumCardsInFoundationPile(1) == 13
        && this.getNumCardsInFoundationPile(2) == 13
        && this.getNumCardsInFoundationPile(3) == 13) {
      this.state = GameState.FINISHED;
    }

  }

  /**
   * Determines if the move is valid or not. Since this function is only called after a valid build
   * has been proven, it only needs to check whether the move to the next Cascade Pile is valid or
   * not. Returns true if valid.
   *
   * @param sourceCard    the card which you are trying to move to the destination pile
   * @param destPileIndex the pile number of the given type, starting at 0
   * @return true only if moving the source card to the specific Cascade Pile is valid.
   */
  private boolean isValidCascadeMove(ICard sourceCard, int destPileIndex) {
    // if the Cascade Pile is empty, then return true
    if (this.getNumCardsInCascadePile(destPileIndex) == 0) {
      return true;
    } else {
      // if not empty, you can see the last card in the pile
      ICard destLastCard = this
          .getCascadeCardAt(destPileIndex, this.getLastCardIndex(PileType.CASCADE, destPileIndex));
      // then you just need to check if the Value of the destination last card is 1 more than the
      // source card's value, and that the 2 Cards have opposing colors.
      return (destLastCard.getValue().getNumber() == (sourceCard.getValue().getNumber() + 1)
          && !(destLastCard.getSuit().getColor().equals(sourceCard.getSuit().getColor())));
    }
  }

  /**
   * Determines if the given card build (a list of cards) is valid. If the build is valid, then
   * returns true, but if the build is invalid, then throws an IllegalArgumentException specifying
   * what was wrong. A build is invalid when the cards are arranged in alternating colors and
   * consecutive, descending values in the cascade pile that they are moving from.
   *
   * @param cardBuild the list of cards from a Cascade Pile that you are trying to move
   * @return true if the build is valid
   * @throws IllegalArgumentException if the build is invalid
   */
  private boolean isValidBuild(List<ICard> cardBuild) throws IllegalArgumentException {

    // for every card in the build except for the last card in the build (the - 1 in the condition)
    {
      for (int cardIndex = 0; cardIndex < cardBuild.size() - 1; cardIndex++) {
        // get the top card
        ICard topCard = cardBuild.get(cardIndex);
        // get the card below it
        ICard bottomCard = cardBuild.get(cardIndex + 1);
        // if the top card's Value is not 1 more than the bottom card's Value,
        // then it is not a valid build
        if (topCard.getValue().getNumber() != (bottomCard.getValue().getNumber() + 1)
            // or if the top card's suit is the same as the bottom card's suit,
            // it is not a valid build
            || (topCard.getSuit().getColor().equals(bottomCard.getSuit().getColor()))) {
          return false;
        }
      }
    }
    // if passed the for loop, then should be valid build (haven't checked size of build using
    // equation yet though)
    return true;
  }

  /**
   * Determines if there are enough intermediate slots available to move the number of cards given.
   * The maximum number of cards that can be moved from 1 Cascade Pile to another when there are N
   * free open piles and K empty Cascade Piles is (N + 1) * 2^K.
   *
   * @param sizeOfBuild the number of cards in the build of cards which the user wants to move.
   * @return true if the size of the build is less than the maximum number of cards able to move
   */
  private boolean isValidSizeBuildToMove(int sizeOfBuild) {
    int numEmptyOpenPiles = 0;
    int numEmptyCascadePiles = 0;
    // get all the free empty Open Piles
    for (int openPileIndex = 0; openPileIndex < this.getNumOpenPiles(); openPileIndex++) {
      if (this.getNumCardsInOpenPile(openPileIndex) == 0) {
        numEmptyOpenPiles = numEmptyOpenPiles + 1;
      }
    }
    // get all the free empty Cascade Piles
    for (int cascadePileIndex = 0; cascadePileIndex < this.getNumCascadePiles();
        cascadePileIndex++) {
      if (this.getNumCardsInCascadePile(cascadePileIndex) == 0) {
        numEmptyCascadePiles = numEmptyCascadePiles + 1;
      }
    }
    // then return if its the condition for how many cards you can move at one time
    // if size of build is less than or equal to that number,
    // then is okay sized build and allow the move
    return (sizeOfBuild <= ((numEmptyOpenPiles + 1) * Math.pow(2, numEmptyCascadePiles)));
  }

}
