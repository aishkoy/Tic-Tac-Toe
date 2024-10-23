import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[][] field = new int[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = 0;
            }
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


}
