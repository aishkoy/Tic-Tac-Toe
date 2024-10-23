import java.util.ArrayList;
import java.util.Scanner;

public class TicTacToe {
    static ArrayList<Player> players = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int[][] field = new int[3][3];

        Player player1 = getPlayerName(1);
        Player player2 = getPlayerName(2);

        boolean gameWon = false;
        int currentPlayer = 1;

        System.out.println("\nChoose a number from the hint field (1-9): ");
        showHintField();
        while(!gameWon) {
            System.out.printf("\nPlayer %d, enter a number: ", currentPlayer);
            int num = sc.nextInt();

            int row = (num - 1) / 3;
            int col = (num - 1) % 3;

            if(field[row][col] == 0) {
                field[row][col] = currentPlayer;
                gameWon = checkWin(field, currentPlayer);

                if(gameWon) {
                    System.out.printf("\nPlayer %d wins! %n", currentPlayer);
                    if(currentPlayer == 1) {
                        player1.incrementWins();
                        player2.incrementLosses();
                    } else{
                        player2.incrementWins();
                        player1.incrementLosses();
                    }
                    System.out.println();
                    displayLeaderboard();
                } else{
                    currentPlayer = (currentPlayer == 1) ? 2 : 1;
                }
            } else {
                System.out.printf("This spot is already taken. Try again. %n");
            }
            if (!gameWon && isFieldFull(field)) {
                System.out.println("It's a draw!");
            }
            showField(field);
        }
    }

    public static void showHintField(){
        int[][] hintField = new int[3][3];
        int counter = 1;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                hintField[i][j] = counter;
                System.out.printf("[%s]",hintField[i][j]);
                counter++;
            }
            System.out.println();
        }
    }

    public static void showField(int[][] field) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (field[i][j] == 1){
                    System.out.print("[x]");
                } else if (field[i][j] == 2){
                    System.out.print("[o]");
                } else {
                    System.out.print("[ ]");
                }
            }
            System.out.println();
        }
    }

    public static boolean checkWin(int[][] field, int currentPlayer){
        for (int i = 0; i < 3; i++) {
            if (field[i][0] == currentPlayer && field[i][1] == currentPlayer && field[i][2] == currentPlayer)
                return true;
            if (field[0][i] == currentPlayer && field[1][i] == currentPlayer && field[2][i] == currentPlayer)
                return true;
        }
        if (field[0][0] == currentPlayer && field[1][1] == currentPlayer && field[2][2] == currentPlayer)
            return true;
        if (field[0][2] == currentPlayer && field[1][1] == currentPlayer && field[2][0] == currentPlayer)
            return true;
        return false;
    }

    public static boolean isFieldFull(int[][] field) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (field[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public static Player getPlayer(String name) {
        for (Player p : players) {
            if (p.getName().equalsIgnoreCase(name)) {
                return p;
            }
        }
        return null;
    }

    static class Player {
        private String name;
        private int wins;
        private int losses;

        public Player(String name) {
            this.name = name;
            this.wins = 0;
            this.losses = 0;
        }

        public String getName() {
            return name;
        }

        public int getWins() {
            return wins;
        }

        public int getLosses() {
            return losses;
        }

        public void incrementWins() {
            wins++;
        }

        public void incrementLosses() {
            losses++;
        }
    }

    public static void sortPlayersByWins(Player[] players) {
        int n = players.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (players[j].getWins() < players[j + 1].getWins()) {
                    Player temp = players[j];
                    players[j] = players[j + 1];
                    players[j + 1] = temp;
                }
            }
        }
    }

    public static void displayLeaderboard() {
        Player[] playerArray = new Player[players.size()];
        players.toArray(playerArray);

        sortPlayersByWins(playerArray);

        for (Player p : playerArray) {
            System.out.println(p.getName() + " - Wins: " + p.getWins() + ", Losses: " + p.getLosses());
        }
    }

    public static Player getPlayerName(int playerNumber) {
        System.out.printf("Player %d, enter your name: ", playerNumber);
        String name = sc.nextLine();
        while (name.isBlank()) {
            System.out.print("You didn't enter a name. Try again: ");
            name = sc.nextLine();
        }
        Player player = getPlayer(name);
        if (player == null) {
            player = new Player(name);
            players.add(player);
        }
        return player;
    }
}
