import java.util.ArrayList;
import java.util.Scanner;

public class TicTacToe {
    static ArrayList<Player> players = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[][] field = new int[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = 0;
            }
        }

        System.out.print("Player 1, enter your name: ");
        String name = sc.nextLine();
        while(name.isBlank()){
            System.out.print("You didn't enter name. Try again: ");
            name = sc.nextLine();
        }
        Player player = getPlayer(name);

        if(player == null){
            player = new Player(name);
            players.add(player);
        }

        System.out.print("Player 2, enter your name: ");
        String name2 = sc.nextLine();
        while(name2.isBlank()){
            System.out.print("You didn't enter name. Try again: ");
            name2 = sc.nextLine();
        }
        Player player2 = getPlayer(name2);

        if(player2 == null){
            player2 = new Player(name2);
            players.add(player2);
        }

        showHintField();
        showEmptyField(field);
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

    public static void showEmptyField(int[][] field) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print("⬜ ");
            }
            System.out.println();
        }
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
}
