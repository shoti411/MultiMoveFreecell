package cs3500.freecell.model.hw02;

/**
 * The 3 states of the game of Freecell. These can be HASNOTSTARTED (representing when the game has
 * not been started yet), PLAYING (representing the time when the user can/is playing the game), and
 * FINISHED (representing when the game is complete - when all 13 cards of each Suit are in their
 * collective Suit piles in order).
 */
public enum GameState { HASNOTSTARTED, PLAYING, FINISHED }
