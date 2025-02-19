import cs3500.freecell.model.FreecellModel;
import cs3500.freecell.model.PileType;
import cs3500.freecell.model.hw02.ICard;
import cs3500.freecell.model.hw02.SimpleFreecellModel;
import java.util.List;

/**
 * This is a mock Model for the Controller to test if it correctly sends the move function's
 * different message to the view for it to render. It also tests get deck because a null deck
 * inputted into the playGame method should return an IllegalStateException.
 */
public class MockModel extends SimpleFreecellModel implements FreecellModel<ICard> {

  @Override
  public List<ICard> getDeck() {
    return null;
  }


  // this should just throw an exception simulating all of the exceptions in the actual model
  // which means the controller should transmit this message to the view correctly.
  @Override
  public void move(PileType source, int pileNumber, int cardIndex, PileType destination,
      int destPileNumber) throws IllegalArgumentException, IllegalStateException {
    throw new IllegalArgumentException("This mock should cause the playGame method to throw an"
        + " IllegalArgumentException and display this message.");
  }

}
