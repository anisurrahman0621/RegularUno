# RegularUno

## About
Uno game ran in IDE. The program is run from the "Game" file. Players are initialized in main by creating a an ArrayList of players and initializing them with a constructor
that accepts a string argument containing the player name. Once the desired players are created, invoking the playGame() method will start the game. 

### Program
For every turn, the program will go through the players hand and determine all of the cards that are playable and denote them in an ArrayList of 1s and 0s corresponding to 
the "playability" of each card. The program automatically plays the first playable card in the hand so long as one exists. The hand is similar to a queue in that playable cards that were added
first get played first (dequeuing). 

The traversing of the whole hand is redundant for the current game structure since the first playable card is always played. However, this will come in handy for implementing a GUI or a game structure that requires user input to pick which card to play. 

For now, after a "wild" card is played, the user chooses the color regardless of whether or not the user played it or the CPU. In the near future, the CPU will pick the color that they have most of in their hand.
