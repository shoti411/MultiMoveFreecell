package cs3500.freecell;

import cs3500.freecell.controller.SimpleFreecellController;
import cs3500.freecell.model.hw02.ICard;
import cs3500.freecell.model.hw04.MultiMoveFreecellModel;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;

/**
 * The SimpleFreecell game. This class does not exactly fit within the MVC model because it only
 * contains a main method which is used to run the function. This function is mainly used for
 * real-time, interactive testing and is very useful for debugging code.
 */
public class SimpleFreecell {

  /**
   * The main method which is used to run the game so that the player can play in real time.
   *
   * @param args input by the player
   */
  public static void main(String[] args) {
    List<ICard> deck = new MultiMoveFreecellModel().getDeck();
    Collections.reverse(deck);
    new SimpleFreecellController(new MultiMoveFreecellModel(), new InputStreamReader(System.in),
        System.out).playGame(deck, 14, 4, false);
  }
}
