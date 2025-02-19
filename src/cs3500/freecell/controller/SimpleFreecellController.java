package cs3500.freecell.controller;

import cs3500.freecell.model.FreecellModel;
import cs3500.freecell.model.PileType;
import cs3500.freecell.model.hw02.ICard;
import cs3500.freecell.view.FreecellTextView;
import cs3500.freecell.view.FreecellView;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * The controller for the SimpleFreecell game. It receives inputs from a Readable object and
 * transmits outputs to an Appendable object. The Appendable object would be provided by a view
 * (specifically {@code FreecellView}). This class delegates what the view, {@code FreecellView}
 * should do and what the model, {@code FreecellModel} should do during the game. It also handles
 * the player's inputs into the console.
 */
public class SimpleFreecellController implements FreecellController<ICard> {

  /**
   * The Model for the SimpleFreecell game. See {@code FreecellModel}.
   */
  private final FreecellModel<ICard> model;
  /**
   * The View for the SimpleFreecell game. See {@code FreecellView}.
   */
  private final FreecellView view;
  /**
   * The Readable object which is the source of characters in the console from which the controller
   * can read for which it then relays commands to the model and the view.
   */
  private final Readable in;

  /**
   * Constructs a {@code SimpleFreecellController} object.
   *
   * @param model the specific model implementation of the Freecell game. It dictates the rules and
   *              the functions which pertain to the gameplay of the FreecellModel game using the
   *              specific ICard Card representation. See {@code FreecellModel}.
   * @param rd    the Readable object which is the source of characters in the console from which
   *              the controller can read which relays commands to the model and the view.
   * @param ap    The output to the console for which character sequences can be appended.
   * @throws IllegalArgumentException only if either of its arguments are null.
   */
  public SimpleFreecellController(FreecellModel<ICard> model, Readable rd, Appendable ap)
      throws IllegalArgumentException {
    if (model == null || rd == null || ap == null) {
      throw new IllegalArgumentException("None of the Arguments should be null.");
    } else {
      this.model = model;
      this.view = new FreecellTextView(model, ap);
      this.in = rd;
    }
  }

  /**
   * Constructs a {@code SimpleFreecellController} object. This is a convenient constructor so I can
   * test that the playGame method throws an IllegalArgumentException when the model input is null.
   * Since the model cannot be null in the main constructor above, this one allows the model to be
   * null.
   *
   * @param model the specific model implementation of the Freecell game. It dictates the rules and
   *              the functions which pertain to the gameplay of the FreecellModel game using the
   *              specific ICard Card representation. Unlike the main constructor, this input is
   *              allowed to be null. See {@code FreecellModel}.
   */
  public SimpleFreecellController(FreecellModel<ICard> model) {
    this.model = model;
    this.view = new FreecellTextView(model, new StringBuilder());
    this.in = new StringReader("q");
  }

  @Override
  public void playGame(List<ICard> deck, int numCascades, int numOpens, boolean shuffle)
      throws IllegalStateException, IllegalArgumentException {

    if (deck == null) {
      throw new IllegalArgumentException("Deck cannot be null!");
    }
    if (this.model == null) {
      throw new IllegalArgumentException("Model cannot be null!");
    }
    // Transmit game state exactly as the view produces it plus a new line (\n) character
    // If the game parameters are invalid and the model throws an exception as a result,
    // the method should transmit a message "Could not start game." and return.
    try {
      this.model.startGame(deck, numCascades, numOpens, shuffle);
    } catch (IllegalArgumentException invalidStartGameParams) {
      // If the game parameters are invalid (e.g., invalid number of cascade/open piles)
      // and the model throws an exception as a result, the method should transmit
      // a message "Could not start game." and return
      this.renderMsgStateException("Could not start game.");
      // end the function
      return;
    }
    // render the board
    this.renderBoardStateException();

    // Initiate the scanner and scan the player's input
    Scanner scan = new Scanner(this.in);

    // while the game is not over
    while (!(this.model.isGameOver())) {
      String inputSource = "";
      String inputCard = "";
      String inputDest = "";
      boolean isFirstInputOkay = false;
      boolean isSecondInputOkay = false;
      boolean isThirdInputOkay = false;

      try {
        // I did not abstract this into a helper function because if I did, I would need
        // to initialize the variables to something which could be one of the inputs which the
        // player would then try using. Like -1 should still be a valid input even though it is a
        // preposterous card index number which the player should know not to put and the move
        // function will throw an exception anyway.
        while (!isFirstInputOkay) {
          inputSource = scan.next();
          // if is 'q' or 'Q', then quit
          // the 'q' has to be a standalone to quit
          if (inputSource.equals("q") || inputSource.equals("Q")) {
            this.renderMsgStateException("Game quit prematurely.");
            // end the game if quit prematurely
            return;
          }
          // is the whole Source input okay - if it is, then continue
          isFirstInputOkay = this.isWholePileInputOkay(inputSource, true);
        }

        while (!isSecondInputOkay) {
          // set the Source Card Index to the following and check for errors
          inputCard = scan.next();
          // if is 'q' or 'Q', then quit
          // the 'q' has to be a standalone to quit
          if (inputCard.equals("q") || inputCard.equals("Q")) {
            this.renderMsgStateException("Game quit prematurely.");
            // end the game if quit prematurely
            return;
          }
          try {
            Integer.parseInt(inputCard);
            isSecondInputOkay = true;
          } catch (NumberFormatException invalidCardIndex) {
            this.renderMsgStateException(
                "Invalid Input for Card Index in Pile. Please Reenter Card Index.\n");
            isSecondInputOkay = false;
          }
        }

        while (!isThirdInputOkay) {
          inputDest = scan.next();
          // if is 'q' or 'Q', then quit
          // the 'q' has to be a standalone to quit
          if (inputDest.equals("q") || inputDest.equals("Q")) {
            this.renderMsgStateException("Game quit prematurely.");
            // end the game if quit prematurely
            return;
          }
          // is the whole Destination input okay - if it is, then continue
          isThirdInputOkay = this.isWholePileInputOkay(inputDest, false);
        }
      } catch (NoSuchElementException noMoreInputs) {
        throw new IllegalStateException("Readable Failure - Nothing else to read!!!");
      }

      // try moving the model then
      this.moveOrMessage(inputSource, inputCard, inputDest);
      // if the game has been won, the method should transmit the final game state, and the
      // message "Game over." on a new line and return.
      if (this.model.isGameOver()) {
        this.renderMsgStateException("Game over.");
        return;
      }
      // this is the end of the while loop
    }

  }

  /**
   * Determines if the whole Pile input is valid; if it is, then return true. If it is invalid,
   * return false and send the error message to the view to display.
   *
   * @param inputPile the string which contains the specific pile (type and index) (e.g. C1)
   * @param isSource  if the pile input is the source pile or if it is the input for the destination
   *                  pile
   * @return true if the input is valid
   */
  private boolean isWholePileInputOkay(String inputPile, boolean isSource) {
    try {
      this.inputPile(inputPile, isSource);
      this.inputPileIndex(inputPile);
      return true;
    } catch (IllegalStateException invalidPileInput) {
      this.renderMsgStateException(invalidPileInput.getMessage());
      this.renderMsgStateException("\n");
      return false;
    }
  }


  /**
   * Sends the move commands to the model if it can, but if the model encounters an error, then
   * takes the error and displays it on screen so the user can input commands again. Also renders
   * the board again if the move goes through.
   *
   * @param inputSource the string which contains the source pile (type and index) (e.g. C1)
   * @param inputCard   the string of the index of the card which you want to move from the source
   *                    pile
   * @param inputDest   the string which contains the destination pile (type and index) (e.g. O1);
   * @throws IllegalStateException if one of the input strings are invalid
   */
  private void moveOrMessage(String inputSource, String inputCard, String inputDest)
      throws IllegalStateException {
    try {
      this.model
          .move(this.inputPile(inputSource, true), this.inputPileIndex(inputSource),
              (Integer.parseInt(inputCard) - 1), this.inputPile(inputDest, false),
              this.inputPileIndex(inputDest));
      // then try rendering the board right after (this won't get called if move fails).
      this.renderBoardStateException();
      // if move failed, then the error cannot be an IllegalStateException because the game
      // was definitely started by the startGame method
    } catch (IllegalArgumentException moveException) {
      this.renderMsgStateException(moveException.getMessage());
      this.renderMsgStateException("\n");
      // if renderBoard failed, then throw a new IllegalStateException because the appendable
      // failed.
    }
  }


  /**
   * Sends the command to render the board to the view so it can render it, and deals with the
   * possibility of an IOException by replacing it with a IllegalStateException because an
   * IOException would only happen if the given Appendable or Readable function failed. (for the
   * playGame method).
   *
   * @throws IllegalStateException if an IOException occurs.
   */
  private void renderBoardStateException() throws IllegalStateException {
    try {
      this.view.renderBoard();
      this.view.renderMessage("\n");
    } catch (IOException appendableError) {
      throw new IllegalStateException(
          "Writing to the Appendable Object failed...");
    }
  }

  /**
   * Sends the given message to the view so it can render it, and deals with possibility of an
   * IOException by replacing it with a IllegalStateException because an IOException would only
   * happen if the given Appendable or Readable function failed.
   *
   * @param message the string to be displayed by the view
   * @throws IllegalStateException if an IOException occurs.
   */
  private void renderMsgStateException(String message) throws IllegalStateException {
    try {
      this.view.renderMessage(message);
    } catch (IOException appendableError) {
      throw new IllegalStateException(appendableError.getMessage());
    }
  }

  /**
   * Returns the PileType associated with the input which is a string. Also throws an exception with
   * a string dependent on whether the inputted Pile was a Source or a Destination. (Helper for the
   * {@code playGame} method).
   *
   * @param input    a string which is the input into the console by the player
   * @param isSource a boolean of whether the PileType is the Source or the Destination Pile
   * @return the PileType associated with the input String. See {@code PileType}.
   * @throws IllegalStateException if the input String's length is less than 2 characters or if the
   *                               String's first character does not correspond to one of the
   *                               PileTypes listed. Also produces an error if an IOException is
   *                               thrown from rendering of the message. See {@code PileType} for
   *                               more info.
   */
  private PileType inputPile(String input, boolean isSource) throws IllegalStateException {
    String sourceOrDest;
    if (isSource) {
      sourceOrDest = "Source";
    } else {
      sourceOrDest = "Destination";
    }
    PileType pileType = null;
    // if they inputted a string length of less than 2 characters, then you know it is wrong
    // because every source pile has a letter and a number which is at least 2 characters
    // in total. (we are doing things in strings however so the number of characters, 2,
    // would just be a string of length 2).
    if (input.length() < 2) {
      throw new IllegalStateException(new StringBuilder("Invalid ").append(sourceOrDest)
          .append(" Pile, To Identify Pile must have the 1st character be the Pile Type and")
          .append(" the 2nd character be the Pile Index. Try Again.").toString());
    } else {
      // get the pile type
      char inputPile = input.charAt(0);
      switch (inputPile) {
        case 'F':
          pileType = PileType.FOUNDATION;
          break;
        case 'C':
          pileType = PileType.CASCADE;
          break;
        case 'O':
          pileType = PileType.OPEN;
          break;
        default:
          // if didn't fit any of the Piles, then ask for it again and keep the pileType as null,
          // so the loop can repeat again.
          throw new IllegalStateException(
              new StringBuilder("Invalid ").append(sourceOrDest)
                  .append(" Pile Type Input, Please Input Again.").toString());
      }
    }
    // if didn't fit any of the Pile Types, then it is still null
    return pileType;
  }

  /**
   * Returns the PileIndex integer associated with PileType.
   *
   * @param input the input String which consists of at least 2 characters. We know this to be true
   *              without testing it because we always run the {@code inputPile()} function before
   *              running this function.
   * @return the pile number of the given type, starting at 0.
   * @throws IllegalStateException if the input string cannot correctly parse into a valid PileType
   *                               and a valid number.
   */
  private int inputPileIndex(String input) throws IllegalStateException {
    try {
      // if the length is greater than 1, then we can try to parse the string for an int
      return Integer.parseInt(input.substring(1)) - 1;
    }
    // if doesn't find number able to parse in string (error able to come because of parseInt
    // function.
    catch (NumberFormatException numberError) {
      throw new IllegalStateException(
          "Could not find Valid Pile Index in Input. Try Inputting the Whole Pile Again.");
    }
  }
}
