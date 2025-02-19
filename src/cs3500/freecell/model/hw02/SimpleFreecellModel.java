package cs3500.freecell.model.hw02;

import cs3500.freecell.model.FreecellModel;
import cs3500.freecell.model.PileType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * A Simple model of the Card game Freecell. This model displays everything in a text format. Our
 * FreecellModel Interface is implemented using our created Card class type.
 */
public class SimpleFreecellModel implements FreecellModel<ICard> {

  /**
   * The state of the game. See {@code GameState} for more info.
   */
  protected GameState state;
  /**
   * The list of 4 Foundation Piles for which the game of freecell can be won if they are correctly
   * filled with all 13 cards in the 52 playing-card deck. See {@code PileType} for more info.
   */
  private List<ArrayList<ICard>> foundationPile;
  /**
   * The list of Cascade Piles for which the cards in the game of freecell will be dealt. See {@code
   * PileType} for more info.
   */
  private List<ArrayList<ICard>> cascadePile;
  /**
   * The list of Open Piles for which the cards in the game of freecell can be placed at any time
   * when one of the Open Piles is empty. See {@code PileType} for more info.
   */
  private List<ArrayList<ICard>> openPile;

  /**
   * Constructs a {@code SimpleFreecellModel} Object.
   */
  public SimpleFreecellModel() {
    this.state = GameState.HASNOTSTARTED;
    this.foundationPile = new ArrayList<ArrayList<ICard>>();
    this.cascadePile = new ArrayList<ArrayList<ICard>>();
    this.openPile = new ArrayList<ArrayList<ICard>>();
  }


  /**
   * Constructs a {@code SimpleFreecellModel} object.
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
  public SimpleFreecellModel(GameState state, List<ArrayList<ICard>> foundationPile,
      List<ArrayList<ICard>> cascadePile, List<ArrayList<ICard>> openPile)
      throws IllegalArgumentException {

    if (state != GameState.HASNOTSTARTED) {
      if (foundationPile.size() != 4 || cascadePile.size() < 4 || openPile.size() < 1) {
        throw new IllegalArgumentException("Invalid construction");
      }
    }
    this.state = state;
    this.foundationPile = foundationPile;
    this.cascadePile = cascadePile;
    this.openPile = openPile;
  }

  @Override
  public List<ICard> getDeck() {
    List<ICard> newDeck = new ArrayList<ICard>();
    for (Suit s : Suit.values()) {
      for (Value v : Value.values()) {
        newDeck.add(new Card(v, s));
      }
    }
    return newDeck;
  }

  @Override
  public void startGame(List<ICard> deck, int numCascadePiles, int numOpenPiles, boolean shuffle)
      throws IllegalArgumentException {

    this.isOkayDeck(deck, numCascadePiles, numOpenPiles);

    // update state of game
    this.state = GameState.PLAYING;

    List<ICard> newDeck = new ArrayList<ICard>(deck);
    // if shuffle true, then shuffles specified deck before dealing, otherwise deals deck as is.
    if (shuffle) {
      Collections.shuffle(newDeck);
    }

    // sets all 4 foundation piles to being empty lists
    this.foundationPile = Arrays
        .asList(new ArrayList<ICard>(), new ArrayList<ICard>(), new ArrayList<ICard>(),
            new ArrayList<ICard>());

    ArrayList<ArrayList<ICard>> cascadePilesCopy = new ArrayList<ArrayList<ICard>>();
    // sets the number of Cascade Piles to the int given
    for (int j = 0; j < numCascadePiles; j++) {
      cascadePilesCopy.add(new ArrayList<ICard>());
    }

    ArrayList<ArrayList<ICard>> openPilesCopy = new ArrayList<ArrayList<ICard>>();
    // sets the number of Open Piles to the int given
    for (int k = 0; k < numOpenPiles; k++) {
      openPilesCopy.add(new ArrayList<ICard>());
    }

    // sets the number of the Open Piles to the int given
    this.openPile = openPilesCopy;

    // Finally, deals out the Cards in a round-robin fashion
    this.dealCards(numCascadePiles, newDeck, cascadePilesCopy);
  }

  /**
   * Checks if the given deck and parameters are valid for the number of Cascade Piles and Open
   * Piles.
   *
   * @param deck            the deck to be dealt - it cannot be null, have duplicates, or have more
   *                        or less than 52 cards.
   * @param numCascadePiles number of cascade piles - it cannot be less than 4
   * @param numOpenPiles    number of open piles - it cannot be less than 1
   * @throws IllegalArgumentException if the given deck is invalid. See the startGame Method for
   *                                  more info.
   */
  private void isOkayDeck(List<ICard> deck, int numCascadePiles, int numOpenPiles)
      throws IllegalArgumentException {
    // Cannot start game with deck that is null
    // Cannot start game with deck above or below 52 cards
    // Cannot start game with deck having any repeats
    // Least amount of Cascade Piles Allowed = 4
    // Least Amount of Open Piles Allowed = 1
    if (deck == null || deck.size() != 52 || hasDuplicates(deck) || numCascadePiles < 4
        || numOpenPiles < 1) {
      throw new IllegalArgumentException("Deck is Invalid");
    }
  }

  /**
   * Deals out the cards from the deck to every Cascade Pile in a round-robin fashion. Do note that
   * the it deals from the bottom up, so the first Card in the first cascade pile is the first Card
   * in the deck, while the first Card in the second Cascade Pile is the second Card in the deck,
   * and etc.
   *
   * @param numCascadePiles the number of Cascade Piles to deal to
   * @param copyOfDeck      A copy of the deck to deal to each Cascade Pile
   */
  private void dealCards(int numCascadePiles, List<ICard> copyOfDeck,
      List<ArrayList<ICard>> cascadePilesCopy) {
    // for how many cards there are in the deck, deal them out
    int deckCardIndex = 0;
    // while the deck still has stuff in it
    while (deckCardIndex < 52) {
      // get the remainder of the index/the number of Cascade Piles and
      // add the specific Card at the deck's index to the specific Cascade Pile
      // e.g. pos = 0 >>> cascadePilesCopy.get(0).add(copyOfDeck.get(0)
      // pos = 1 >>> cascadePilesCopy.get(1).add(copyOfDeck.get(1)
      cascadePilesCopy.get(deckCardIndex % numCascadePiles).add(copyOfDeck.get(deckCardIndex));
      // add 1 to the cascadePilePosition
      deckCardIndex = deckCardIndex + 1;
    }
    // set the Cascade Piles to the dealt out version
    this.cascadePile = cascadePilesCopy;
  }

  /**
   * Determines whether there are duplicate cards in the list of cards (returns true if there is at
   * least 1 duplicate card in the list and false if there are none).
   *
   * @param list A list of objects
   * @return A boolean of whether or not there are duplicate objects in the list
   */
  private static boolean hasDuplicates(List<ICard> list) {
    // create a copy of the list
    List<ICard> copyOfList = new ArrayList<ICard>(list);
    // after storing and taking that item out of the copy of the list, see if there
    // are any other items in the list that
    for (ICard c : list) {
      copyOfList.remove(c);
      if (copyOfList.contains(c)) {
        return true;
      }
    }
    return false;
  }

  // same comment as from interface FreecellModel, but do note, this function can only move
  // 1 Card at a time (that means only the last Card)
  // also added the function of changing the game state to FINISHED whether all 52 cards
  // are in the foundation piles because it would be more efficient to check this only after every
  // move instead of after every second
  @Override
  public void move(PileType source, int pileNumber, int cardIndex, PileType destination,
      int destPileNumber) throws IllegalArgumentException, IllegalStateException {
    this.checkError(destination, destPileNumber);
    ICard sourceCard;
    sourceCard = this.getCardAt(source, pileNumber, cardIndex);
    ICard destLastCard;

    // Take the sourceCard from the corresponding pile
    // WILL NOT WORK if Card is not last Card in pile!!! (will throw exception)
    if (cardIndex != this.getLastCardIndex(source, pileNumber)) {
      throw new IllegalArgumentException(
          new StringBuilder("Invalid Move, Not Last Card in ").append(this.pileTypeToString(source))
              .append(" Pile").toString());
    } else {
      this.getFreecellPile(source, pileNumber).remove(sourceCard);
    }

    // Move the source Card to the destination
    // if the last Card in the pile has a value which is 1 greater than the sourceCard and
    // is of an opposite color, then the move is allowed

    switch (destination) {
      case FOUNDATION:
        // if the foundation pile is empty, then the sourceCard's value needs to be an Ace
        if (this.getNumCardsInFoundationPile(destPileNumber) == 0) {
          // if empty foundation pile and sourceCard is an ace, then allow it
          if (sourceCard.getValue().equals(Value.ACE)) {
            this.getFreecellPile(destination, destPileNumber).add(sourceCard);
          } else {
            this.getFreecellPile(source, pileNumber).add(sourceCard);
            throw new IllegalArgumentException(
                "Invalid Move, Ace Needed for Empty Foundation Pile");
          }
        } else {
          destLastCard = this.getCardAt(PileType.FOUNDATION, destPileNumber,
              this.getNumCardsInFoundationPile(destPileNumber) - 1);
          // if sourceCard is 1 less than destLastCard and they are the same suit, then allow
          // the move to the Foundation Pile
          if ((destLastCard.getValue().getNumber() == (sourceCard.getValue().getNumber() - 1))
              && (sourceCard.getSuit().equals(destLastCard.getSuit()))) {
            this.foundationPile.get(destPileNumber).add(sourceCard);
          } else {
            this.getFreecellPile(source, pileNumber).add(sourceCard);
            throw new IllegalArgumentException(
                new StringBuilder("Invalid Move, ").append(sourceCard)
                    .append(" cannot be placed on ").append(destLastCard)
                    .append("in Foundation piles, Cards must have the same Suit as the Pile, and ")
                    .append(
                        "the Destination Card must have a Value of 1 less than the Source Card.")
                    .toString());
          }
        }
        break;
      // if the destLastCard in the Cascade Pile is non-existent or is the same colored
      // suit than the sourceCard or NOT 1 more than the sourceCard's value, then throw exception
      case CASCADE:
        // if there more than 0 cards in the pile
        if (this.getNumCardsInCascadePile(destPileNumber) > 0) {
          // set the destLastCard as the last Card in the pile
          destLastCard = this.getCardAt(PileType.CASCADE, destPileNumber,
              this.getLastCardIndex(PileType.CASCADE, destPileNumber));
          // then if that last Card's Value is 1 more than the source Card's Value &&
          // the Card's Suit is not the same as the source Card's Suit
          if (destLastCard.getValue().getNumber() == (sourceCard.getValue().getNumber() + 1)
              && !(destLastCard.getSuit().getColor().equals(sourceCard.getSuit().getColor()))) {
            // then add the Card to the Cascade Pile
            this.getFreecellPile(destination, destPileNumber).add(sourceCard);
          } else {
            this.getFreecellPile(source, pileNumber).add(sourceCard);
            throw new IllegalArgumentException(
                new StringBuilder("Invalid Move, ").append(sourceCard)
                    .append(" cannot be placed on ").append(destLastCard).append(
                    ", Destination Card needs to have a Value of 1 more than the source Card ")
                    .append("and be a different colored Suit.").toString());
          }
        } else {
          // then it should have 0 cards in the list and the move should be allowed
          this.getFreecellPile(destination, destPileNumber).add(sourceCard);
        }
        break;
      case OPEN:
        // if the number of cards in the Open Pile isn't 0, then there is an error
        if (this.getNumCardsInOpenPile(destPileNumber) > 0) {
          // and add the source card back where it was before
          this.getFreecellPile(source, pileNumber).add(sourceCard);
          throw new IllegalArgumentException("Invalid Move, Open Piles can only contain 1 Card");
        } else {
          // just add the Card to the originally empty open pile
          this.openPile.get(destPileNumber).add(sourceCard);
        }
        break;
      default:
        // if none of these run, something definitely went wrong
        // so add the card back to the pile and throw an exception
        this.getFreecellPile(source, pileNumber).add(sourceCard);
        throw new IllegalArgumentException("Invalid Pile Type");
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
   * Return the Pile within the Pile Type with the given Pile Index. See {@code PileType}.
   *
   * @param pileType  the type of the source pile. See {@code PileType}.
   * @param pileIndex the pile number of the given type, starting at 0.
   * @return the specific pile within the game with the given pile index
   * @throws IllegalArgumentException if the given Pile Type is  different than the ones listed in
   *                                  {@code PileType}.
   */
  protected List<ICard> getFreecellPile(PileType pileType, int pileIndex)
      throws IllegalArgumentException {
    switch (pileType) {
      case FOUNDATION:
        return this.foundationPile.get(pileIndex);
      case CASCADE:
        return this.cascadePile.get(pileIndex);
      case OPEN:
        return this.openPile.get(pileIndex);
      default:
        throw new IllegalArgumentException("Invalid Pile Type");
    }
  }

  /**
   * Returns the name of the associated with the given Pile Type (in String format).
   *
   * @param pileType the type of the source pile. See {@code PileType}.
   * @return a string of the name of the given Pile Type.
   * @throws IllegalArgumentException if the given Pile Type is  different than the ones listed in
   *                                  {@code PileType}.
   */
  private String pileTypeToString(PileType pileType) throws IllegalArgumentException {
    switch (pileType) {
      case FOUNDATION:
        return "Foundation";
      case CASCADE:
        return "Cascade";
      case OPEN:
        return "Open";
      default:
        throw new IllegalArgumentException("Invalid Pile Type");
    }
  }

  /**
   * Returns the position of the last card in the specific pile.
   *
   * @param pileType  the type of the source pile. See {@code PileType}.
   * @param pileIndex the pile number of the given type, starting at 0
   * @return the index of the last card in the pile.
   * @throws IllegalArgumentException if the given Pile Type is null or different than the ones *
   *                                  listed in {@code PileType}.
   */
  protected int getLastCardIndex(PileType pileType, int pileIndex) throws IllegalArgumentException {
    switch (pileType) {
      case FOUNDATION:
        return this.getNumCardsInFoundationPile(pileIndex) - 1;
      case CASCADE:
        return this.getNumCardsInCascadePile(pileIndex) - 1;
      case OPEN:
        return this.getNumCardsInOpenPile(pileIndex) - 1;
      default:
        throw new IllegalArgumentException("Invalid Pile Type");
    }
  }

  /**
   * Returns the Card at the specified pileType and cardIndex.
   *
   * @param pileType   the type of the source pile see @link{PileType}.
   * @param pileNumber the pile number of the given type, starting at 0.
   * @param cardIndex  the index of the Card to be moved from the source pile, starting at 0
   * @return the Card specified indexes.
   * @throws IllegalArgumentException if the pileIndex or cardIndex is invalid.
   * @throws IllegalStateException    if the game has not started.
   */
  protected ICard getCardAt(PileType pileType, int pileNumber, int cardIndex)
      throws IllegalArgumentException, IllegalStateException {
    // can't abstract both checkError and getCardAt together because checkError needs to be
    // a void function (to work with the numCards functions) while getCardAt needs to have a
    // return type of type Card
    this.checkError(pileType, pileNumber);
    switch (pileType) {
      case FOUNDATION:
        if (cardIndex < 0 || cardIndex > this.foundationPile.get(pileNumber).size() - 1) {
          throw new IllegalArgumentException("Invalid Foundation Card Index");
        } else {
          return this.foundationPile.get(pileNumber).get(cardIndex);
        }
      case CASCADE:

        if (cardIndex < 0 || cardIndex > this.cascadePile.get(pileNumber).size() - 1) {
          throw new IllegalArgumentException("Invalid Cascade Card Index");
        } else {
          return this.cascadePile.get(pileNumber).get(cardIndex);
        }
      case OPEN:
        // if empty, return null
        if (this.openPile.get(pileNumber).size() == 0) {
          return null;
          // else return the Card there
        } else {
          return this.openPile.get(pileNumber).get(0);
        }
      default:
        throw new IllegalArgumentException();
    }
  }

  // we are not counting the time when there are no moves available because Piazza
  // said you don't have to
  @Override
  public boolean isGameOver() {
    return this.state.equals(GameState.FINISHED);
  }

  @Override
  public int getNumCardsInFoundationPile(int index)
      throws IllegalArgumentException, IllegalStateException {
    this.checkError(PileType.FOUNDATION, index);
    return this.foundationPile.get(index).size();
  }

  @Override
  public int getNumCascadePiles() {
    if (this.state.equals(GameState.HASNOTSTARTED)) {
      return -1;
    } else {
      return this.cascadePile.size();
    }
  }

  @Override
  public int getNumCardsInCascadePile(int index)
      throws IllegalArgumentException, IllegalStateException {
    this.checkError(PileType.CASCADE, index);
    return this.cascadePile.get(index).size();
  }

  @Override
  public int getNumCardsInOpenPile(int index)
      throws IllegalArgumentException, IllegalStateException {
    this.checkError(PileType.OPEN, index);
    return this.openPile.get(index).size();
  }

  @Override
  public int getNumOpenPiles() {
    if (this.state.equals(GameState.HASNOTSTARTED)) {
      return -1;
    } else {
      return this.openPile.size();
    }
  }

  @Override
  public ICard getFoundationCardAt(int pileIndex, int cardIndex)
      throws IllegalArgumentException, IllegalStateException {
    return this.getCardAt(PileType.FOUNDATION, pileIndex, cardIndex);
  }

  @Override
  public ICard getCascadeCardAt(int pileIndex, int cardIndex)
      throws IllegalArgumentException, IllegalStateException {
    return this.getCardAt(PileType.CASCADE, pileIndex, cardIndex);
  }

  @Override
  public ICard getOpenCardAt(int pileIndex) throws IllegalArgumentException, IllegalStateException {
    return this.getCardAt(PileType.OPEN, pileIndex, 0);
  }


  /**
   * Throws an error if the given pileIndex is invalid or if the game has not started yet.
   *
   * @param pileType  type for one of the three types of piles in Freecell, see {@code PileType}
   * @param pileIndex the index of the specified pileType, starting at 0
   * @throws IllegalArgumentException if the pileIndex is invalid (this changes according to which
   *                                  pileType is inputted) see {@code PileType}
   * @throws IllegalStateException    if the game has not started
   */
  private void checkError(PileType pileType, int pileIndex)
      throws IllegalArgumentException, IllegalStateException {
    if (this.state.equals(GameState.HASNOTSTARTED)) {
      throw new IllegalStateException("Game Has Not Started");
    } else {
      int upperBoundPilesIndex;
      if (pileType == null) {
        throw new IllegalArgumentException("Pile Type cannot be null");
      }
      switch (pileType) {
        case FOUNDATION:
          // highest index you can do is 3 (4 foundation piles always)
          upperBoundPilesIndex = 3;
          break;
        case CASCADE:
          // highest index you can do is the number of Cascade Piles - 1
          upperBoundPilesIndex = this.cascadePile.size() - 1;
          break;
        case OPEN:
          // highest index you can do is the number of Open Piles - 1
          upperBoundPilesIndex = this.openPile.size() - 1;
          break;
        default:
          throw new IllegalArgumentException(
              "Pile Type must be either a Foundation Pile, a Cascade Pile, or an Open Pile.");
      }
      if (pileIndex < 0 || pileIndex > upperBoundPilesIndex) {
        throw new IllegalArgumentException("Invalid Pile Index");
      }
    }
  }
}
