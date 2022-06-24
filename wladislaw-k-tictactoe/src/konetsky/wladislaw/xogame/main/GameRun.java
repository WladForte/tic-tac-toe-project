package konetsky.wladislaw.xogame.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import konetsky.wladislaw.xogame.board.CollectionBoard;
import konetsky.wladislaw.xogame.boardimpl.BoardProvider;
import konetsky.wladislaw.xogame.boardview.BoardView;
import konetsky.wladislaw.xogame.entity.Cell;
import konetsky.wladislaw.xogame.entity.Mark;
import konetsky.wladislaw.xogame.exception.NonexistentCellException;
import konetsky.wladislaw.xogame.exception.UnparseableInputException;
import konetsky.wladislaw.xogame.player.ClassicPlayer;
import konetsky.wladislaw.xogame.playerimpl.AIClassicPlayer;
import konetsky.wladislaw.xogame.playerimpl.HumanClassicPlayer;
import konetsky.wladislaw.xogame.playerimpl.Mode;
import konetsky.wladislaw.xogame.playerimpl.PlayerProvider;
import konetsky.wladislaw.xogame.util.CellParamsParser;

/**
 * The class where the game runs. In this game, the core class for the cell is a class Cell.
 */
public class GameRun {
	
	public static final BufferedReader CONSOLE_READER = new BufferedReader(new InputStreamReader(System.in));
	public static final int MIN_SIZE = 3;
	public static final int MAX_SIZE = 5;

	public static void main(String[] args) {
		
		// SETUPS OF THE GAME
		// Instantiating board provider
		BoardProvider boardProvider = BoardProvider.getInstance();

		System.out.println("Greetings! Welcome to the tic-tac-toe console game");
		System.out.println("For a start, here is a list of all available boards");
		System.out.println(boardProvider.presentAllAvailableBoards() + "\n");
		System.out.println("Please, type a type of a board from above and its size, the size should be not less than " + MIN_SIZE + " and not greater than " + MAX_SIZE);
		System.out.println("The input should be provided in the following manner: \"the name of the board, its size (integer number)\"");
		
		// Instantiating variable for user input and variable for the board
		String userInput;
		CollectionBoard gameBoard;
		int gameBoardSize;
		userInput = "";
		gameBoard = null;
		gameBoardSize = 0;
		
		// getting a board from the user input
		while (gameBoard == null) {
			try {
				userInput = CONSOLE_READER.readLine();
				try {
					gameBoard = (CollectionBoard) boardProvider.getCustomizedBoard(userInput);
					gameBoardSize = gameBoard.getSize();
					if (gameBoardSize < MIN_SIZE || gameBoardSize > MAX_SIZE) {
						System.out.println("Please, provide the correct input, the size should be not less than " + MIN_SIZE + " and not greater than " + MAX_SIZE);
						gameBoard = null;
					}
				} catch (UnparseableInputException uie) {
					System.out.println(uie.getMessage());
				}
			} catch (IOException e) {
				gameBoard = (CollectionBoard) boardProvider.getCustomizedBoard("ClassicListBoard, 3");
				System.out.println("An exception occured while reading the input, the created board is a default board, its size is " + gameBoard.getSize());
			}
		}
		System.out.println("Here are the rules of the selected board:\n" + gameBoard.getDescription() + "\n");
		
		// Instantiating player provider
		PlayerProvider playerProvider = PlayerProvider.getInstance(gameBoard);
		ClassicPlayer[] protoPlayers = new ClassicPlayer[gameBoard.getMaxPlayersCount()];
		List<Mark> availableMarks = new ArrayList<Mark>(Mark.geytAllActiveMarks());
		
		System.out.println("Now is the time to select players, the maximum number of players on this board is " + protoPlayers.length);
		System.out.println("All available marks for the players are " + availableMarks);
		System.out.println("Select, how much human players you would like to have for the board (it should be not greater than " + (protoPlayers.length - 1) + ")?");
		
		// determining the number of human players
		int humanPlayerCount;
		humanPlayerCount = 0;
		while (humanPlayerCount == 0) {
			try {
				userInput = CONSOLE_READER.readLine();
				try {
					humanPlayerCount = Integer.parseInt(userInput);
					if (humanPlayerCount < 1 || humanPlayerCount > (protoPlayers.length - 1)) {
						humanPlayerCount = 0;
						System.out.println("Please, provide the correct input - the positive number that is not greater than " + (protoPlayers.length - 1));
					}
				} catch (NumberFormatException nfe) {
					System.out.println("Please, provide the correct input - the positive number that is not greater than " + (protoPlayers.length - 1));
				}
			} catch (IOException e) {
				humanPlayerCount = 1;
				System.out.println("An exception occured while reading the input, the number of human players is " + humanPlayerCount);
			}
		}
		
		// Assigning marks to human players.
		System.out.println("Please, provide, marks for the human players");
		for (int i = 0; i < humanPlayerCount; i++) {
			System.out.println("The remaining marks: " + availableMarks);
			System.out.println("Human player N" + (i + 1));
			Mark playerMark = null;
			while (playerMark == null) {
				try {
					userInput = CONSOLE_READER.readLine();
					try {
						playerMark = Mark.valueOf(userInput);
						if (availableMarks.contains(playerMark)) {
							protoPlayers[i] = playerProvider.provideHumanPlayer(playerMark);
						} else {
							playerMark = null;
							System.out.println("Please provide the mark that is not assigned to other player");
						}
					} catch (IllegalArgumentException iae) {
						System.out.println("Please, provide the correct mark");
					}
				} catch (IOException e) {
					playerMark = availableMarks.get(0);
					System.out.println("An exception occured while reading the input, the mark of the human player N" + (i + 1) + "is " + playerMark);
				}
			}
			availableMarks.remove(playerMark);
		}
		
		// determining the number of ai players
		int aiPlayerCount;
		aiPlayerCount = 0;
		System.out.println("\nNow is time to select the number of AI players, this number must not exceed " + (protoPlayers.length - humanPlayerCount));
		while (aiPlayerCount == 0) {
			try {
				userInput = CONSOLE_READER.readLine();
				try {
					aiPlayerCount = Integer.parseInt(userInput);
					if (aiPlayerCount < 1 || aiPlayerCount > (protoPlayers.length - humanPlayerCount)) {
						System.out.println("Please, provide the correct input - the number that is not greater than " + (protoPlayers.length - humanPlayerCount));
						aiPlayerCount = 0;
					}
				} catch (NumberFormatException nfe) {
					System.out.println("Please, provide the correct input - the number that is not greater than " + (protoPlayers.length - humanPlayerCount));
				}
			} catch (IOException e) {
				aiPlayerCount = 1;
				System.out.println("An exception occured while reading the input, the number of human players is " + aiPlayerCount);
			}
		}
		
		// Assigning mode to ai players
		Mode aiMode;
		aiMode = null;
		System.out.println("Please, provide, mode for the ai players");
		System.out.println("The available modes are as follows: " + Arrays.toString(Mode.values()));
		while (aiMode == null) {
			try {
				userInput = CONSOLE_READER.readLine();
				try {
					aiMode = Mode.valueOf(userInput.toUpperCase());
				} catch (IllegalArgumentException iae) {
					System.out.println("The input is incorrect, please provide the correct name of the mode");
				}
			} catch (IOException e) {
				aiMode = Mode.MEDIUM;
				System.out.println("An exception occured while reading the input, the mode of the ai players is " + aiMode);
			}
		}
		
		
		// Assigning marks to ai players
		System.out.println("Please, provide, marks for the ai players");
		for (int i = 0; i < aiPlayerCount; i++) {
			System.out.println("The remaining marks: " + availableMarks);
			System.out.println("AI player N" + (i + 1));
			Mark playerMark = null;
			while (playerMark == null) {
				try {
					userInput = CONSOLE_READER.readLine();
					try {
						playerMark = Mark.valueOf(userInput);
						if (availableMarks.contains(playerMark)) {
							protoPlayers[humanPlayerCount + i] = playerProvider.provideAIPlayer(playerMark, aiMode);
						} else {
							playerMark = null;
							System.out.println("Please, provide the mark that is not assigned to other player");
						}	
					} catch (IllegalArgumentException iae) {
						System.out.println("Please, provide the correct mark");
					}
				} catch (IOException e) {
					playerMark = availableMarks.get(0);
					System.out.println("An exception occured while reading the input, the mark of the ai player N" + (i + 1) + "is " + playerMark);
				}
			}
			availableMarks.remove(playerMark);
		}
		
		System.out.println();
		
		// Presenting the setups of the game
		ClassicPlayer[] players = null;
		players = Arrays.stream(protoPlayers).filter((player) -> player != null).toArray(ClassicPlayer[]::new);
		System.out.println("The setups of the game are as follows:");
		System.out.println("Board: " + gameBoard.getClass().getSimpleName() + ", size - " + gameBoardSize + "x" + gameBoardSize);
		System.out.println(presentClassicPlayers(players));
		
		// Instantiating board view
		BoardView view = BoardView.getInstance();
		System.out.println("\nThe game begins!");
		makeGameMode(gameBoard, players, view);
		
		closeReader();
		System.out.println("\nThe game has finished");

	}
	
	public static void closeReader() {
		try {
			CONSOLE_READER.close();
		} catch (IOException e) {
			System.out.println("An exception occured while closing the reader");
		}
	}
	
	public static String presentClassicPlayers(ClassicPlayer... players) {
		StringBuilder builder = new StringBuilder();
		Arrays.sort(players); // sort player according to their turn!
		
		for (int i = 0; i < players.length; i++) {
			builder.append("Player N").append((i + 1)).append(", mark - ").append(players[i].getPlayerMark());
			if (players[i] instanceof AIClassicPlayer) {
				builder.append(", AI, mode - ");
				AIClassicPlayer instance = (AIClassicPlayer) players[i];
				builder.append(instance.getPlayerMode()).append('\n');
			}
			if (players[i] instanceof HumanClassicPlayer) {
				builder.append(", human").append('\n');
			}		
		}
		
		return builder.deleteCharAt(builder.length() - 1).toString();
	}
	
	public static void makeGameMode(CollectionBoard gameBoard, ClassicPlayer[] players, BoardView view) {
		System.out.println("The current condition of the board");
		System.out.println(view.boardToString(gameBoard));
		
		String winningString = "";
		
		while (!gameBoard.hasWinningCondition()) {
			
			for (int j = 0; j < players.length; j++) {
				Cell targetCell = null;
				String userInput = "";
				boolean isSelectable = false;
				
				if (!players[j].isAIPlayer()) {
					
					System.out.println("Player N" + (j + 1) + " turn");
					while (!isSelectable) {
						try {
							System.out.println("Please, provide cell coordinates (ex. - 1, 2)");
							userInput = CONSOLE_READER.readLine();
							try {
								targetCell = players[j].selectCell(gameBoard, userInput);
								if (targetCell.getMark() == Mark.EMPTY) {
									players[j].putMark(gameBoard, targetCell);
									isSelectable = true;
								} else {
									System.out.println("This cell cannot be selected");
								}
							}
							catch (UnparseableInputException uie) {
								System.out.println("Please, provide the input in the correct manner");
							} 
							catch (NonexistentCellException nce) {
								System.out.println("Please, provide the correct coordinates for the cell");
							}
						} catch (IOException e) {
							// TODO Auto-generated catch block
							// break game
							e.printStackTrace();
						}
					}
					System.out.println(view.boardToString(gameBoard));
					
				} else {
					System.out.println("Player N" + (j + 1) + " turn");
					targetCell = players[j].selectCell(gameBoard, userInput);
					players[j].putMark(gameBoard, targetCell);
					System.out.println(view.boardToString(gameBoard));
				}
				
				if (gameBoard.hasWinningCondition()) {
					winningString = "Player N" + (j + 1) + " has won!";
					System.out.println(winningString);
					break;
				}
			}
			
		}
		
	}
	
	

}
