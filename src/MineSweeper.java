import java.util.Scanner;
import java.util.Random;

public class MineSweeper {
    char[][] board;
    boolean[][] revealed;
    int numRows;
    int numCols;
    int numMines;
    int numUncovered;
    Scanner input;

    //Kurucu metodumuz Oyunu oluşturmak ve mayınların sayısını belirlemek için kullanıyoruz.
    public MineSweeper(int numRows, int numCols) {
        this.numRows = numRows;
        this.numCols = numCols;
        this.numMines = numRows * numCols / 4;
        this.numUncovered = 0;
        this.input = new Scanner(System.in);
        this.board = new char[numRows][numCols];
        this.revealed = new boolean[numRows][numCols]; //Hücrelerin açılıp açıladığını kontrol eder.
        initializeBoard();
        placeMines(); // Mayınların tahtaya rastgele yerleştirilmesini sağlayan metot.
        printBoard();
    }
    //Kullanıcıdan veri alarak oyunun devamlılığını sağlamak için sonsuz döngüye sokuyoruz.
    public void getUserInput() {
        for (;;) {
            System.out.print("Satır sayısını giriniz : ");
            int row = input.nextInt();
            System.out.print("Sütun sayısını giriniz : ");
            int col = input.nextInt();
            System.out.println("===========================");
            uncoverCell(row, col);
            printBoard();
        }
    }
    // Döngü kullanarak satır ve sütunlara bakılarak "-" işareti atanır.Açılmamış hücreler false durumunda kalır.
    private void initializeBoard() {
        for (int i = 0; i < numRows; i++) {
            for (int k = 0; k < numCols; k++) {
                board[i][k] = '-';
                revealed[i][k] = false;
            }
        }
    }
    //Bu metod, mayınları rastgele yerleştirmek için kullanılır. ('*') koyar.
    private void placeMines() {
        Random rand = new Random();
        int minesPlaced = 0;
        while (minesPlaced < numMines) {
            int row = rand.nextInt(numRows);
            int col = rand.nextInt(numCols);
            if (board[row][col] != '*') {
                board[row][col] = '*';
                minesPlaced++;
            }
        }
    }
    //Oyunu konsola yazdırma işlemi
    public void printBoard() {
        for (int i = 0; i < numRows; i++) {
            for (int k = 0; k < numCols; k++) {
                if (!revealed[i][k]) {
                    System.out.print("-");
                } else {
                    System.out.print(board[i][k] + "");
                }
            }
            System.out.println();
        }
    }
    //Oyuncunun yaptığı hamleleri kontrol eder
    public boolean isValidMove(int row, int col) {
        return row >= 0 && row < numRows && col >= 0 && col < numCols && !revealed[row][col];
    }
    //Mayın kontrolü yapılmasını sağlıyor.
    public boolean isMine(int row, int col) {

        return board[row][col] == '*';
    }
    // Bu metot oyuncunun belirtilen bir hücreyi seçmesini sağlar ardından oyunun mevcut durumunu kontrol eder.
    public void uncoverCell(int row, int col) {
        if (!isValidMove(row, col)) {
            System.out.println("Geçersiz hamle. Lütfen başka bir konum seçin.");
            return;
        }

        revealed[row][col] = true;
        numUncovered++;

        if (isMine(row, col)) {
            System.out.println("Oyunu kaybettiniz. Mayına bastınız!");
            printBoard();
            System.exit(0);
        } else {
            int count = countAdjacentMines(row, col);
            board[row][col] = (char) (count + '0');

            if (numUncovered == numRows * numCols - numMines) {
                System.out.println("Tebrikler! Tüm mayınları buldunuz ve oyunu kazandınız!");
                printBoard();
                System.exit(0);
            }
        }
    }
    //Bu metot belirtilen bir hücrenin etrafındaki komşu hücrelerdeki mayın sayısını sayar.
    private int countAdjacentMines(int row, int col) {
        int count = 0;
        for (int i = row - 1; i <= row + 1; i++) {
            for (int k = col - 1; k <= col + 1; k++) {
                if (i >= 0 && i < numRows && k >= 0 && k < numCols && board[i][k] == '*') {
                    count++;
                }
            }
        }
        return count;
    }

}