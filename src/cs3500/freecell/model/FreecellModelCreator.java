package cs3500.freecell.model;

import cs3500.freecell.model.hw02.ICard;
import cs3500.freecell.model.hw02.SimpleFreecellModel;
import cs3500.freecell.model.hw04.MultiMoveFreecellModel;

/**
 * A class which creates a model of Freecell for which you can play using. Since there are only 2
 * types of FreecellModels to play using, the only 2 models which you can create are
 * SimpleFreecellModel and MultiMoveFreecellModel. You can create them using the factory method
 * create for which you put in a GameType and it gives out a type of FreecellModel.
 */
public class FreecellModelCreator {

  /**
   * The type of game of freecell for which to play. SINGLEMOVE represents a FreecellModel of
   * SimpleFreecellModel which means you are only allowed to move 1 card at a time, while MULTIMOVE
   * represents a FreecellModel of MultiMoveFreecellModel which means you are allowed to move
   * multiple cards at once, but only if they could be moved 1 at a time when playing a game with
   * SimpleFreecellModel rules (this change is only convenience to the player.
   */
  public enum GameType { SINGLEMOVE, MULTIMOVE }

  /**
   * Creates an {@code FreecellModel} object. This can be either a SimpleFreecellModel object or a
   * MultiMoveFreecellModel object.
   *
   * @param type the type of model to use
   * @return a FreecellModel object
   * @throws IllegalArgumentException if the GameType entered is null or not one of the 2 possible
   *                                  GameTypes, SINGLEMOVE and MULTIMOVE.
   */
  public static FreecellModel<ICard> create(GameType type) throws IllegalArgumentException {
    if (type == null) {
      throw new IllegalArgumentException("Game Type cannot be null.");
    }
    switch (type) {
      case SINGLEMOVE:
        return new SimpleFreecellModel();
      case MULTIMOVE:
        return new MultiMoveFreecellModel();
      default:
        throw new IllegalArgumentException("Invalid GameType Entered.");
    }
  }
}

