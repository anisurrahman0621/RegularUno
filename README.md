# RegularUno

## About
Uno game ran in IDE where user controlled players can play against the CPU or against other players. The program is run from the "Main Game File" file. Players are initialized in the main method by creating a an ArrayList of players and initializing them with a constructor that accepts a string argument containing the player name and a boolean value that denotes whether or not a player is user controlled. Once the desired players are created, invoking the playGame() method will start the game. 

### Program

The game starts with the creation of the deck. The deck is populated according to the official Uno rules. After the deck is created, it is shuffled. A "played card pile" is used to keep count of every card that is played. If the deck is empty, the played card pile is shuffled and becomes the new deck. This process repeats as necessary until the game finishes.

For every turn, the program will go through the player's hand and determine all of the cards that are playable and denote them in an ArrayList of 1s and 0s corresponding to the "playability" of each card. The program automatically plays the first playable card in the hand so long as one exists. Otherwise, the player draws from the deck.

If a user controlled player plays a "wild" card, the program asks the user to choose the next color. Checks are made to ensure that invalid inputs are rejected. If a CPU controlled player plays a "wild" card, the program automatically picks the best possible color at the current time for the CPU by counting how many cards the CPU player has of each color.

### Future Plans

The traversing of the whole hand is redundant for the current game structure since the first playable card is always played. However, this will come in handy for implementing a GUI or a game structure that requires user input to pick which card to play.




