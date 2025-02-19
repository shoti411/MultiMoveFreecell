import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import cs3500.freecell.model.FreecellModel;
import cs3500.freecell.model.FreecellModelCreator;
import cs3500.freecell.model.FreecellModelCreator.GameType;
import cs3500.freecell.model.hw02.ICard;
import cs3500.freecell.model.hw02.SimpleFreecellModel;
import cs3500.freecell.model.hw04.MultiMoveFreecellModel;
import org.junit.Test;

/**
 * The test class for FreecellModelCreator. This class tests whether the factory method, create,
 * works properly.
 */
public class FreecellModelCreatorTest {

  @Test(expected = IllegalArgumentException.class)
  public void testCreateExceptionNullGameType() {
    FreecellModelCreator.create(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCreateExceptionInvalidGameType() {
    FreecellModelCreator.create(GameType.valueOf("not a gameType"));
  }


  @Test
  public void testCreateSuccessSimpleFreecellModel() {
    FreecellModel<ICard> model = new SimpleFreecellModel();
    assertEquals(model.getDeck(), FreecellModelCreator.create(GameType.SINGLEMOVE).getDeck());

    assertEquals(-1, FreecellModelCreator.create(GameType.SINGLEMOVE).getNumOpenPiles());
    assertEquals(-1, FreecellModelCreator.create(GameType.SINGLEMOVE).getNumCascadePiles());
    assertEquals(model.getClass(), FreecellModelCreator.create(GameType.SINGLEMOVE).getClass());
    assertNotEquals(MultiMoveFreecellModel.class,
        FreecellModelCreator.create(GameType.SINGLEMOVE).getClass());
  }

  @Test
  public void testCreateSuccessMultiMoveFreecellModel() {
    FreecellModel<ICard> model = new MultiMoveFreecellModel();
    assertEquals(model.getDeck(), FreecellModelCreator.create(GameType.MULTIMOVE).getDeck());

    assertEquals(-1, FreecellModelCreator.create(GameType.MULTIMOVE).getNumOpenPiles());
    assertEquals(-1, FreecellModelCreator.create(GameType.MULTIMOVE).getNumCascadePiles());
    assertEquals(model.getClass(), FreecellModelCreator.create(GameType.MULTIMOVE).getClass());
    assertNotEquals(SimpleFreecellModel.class,
        FreecellModelCreator.create(GameType.MULTIMOVE).getClass());
  }
}