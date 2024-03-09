    import java.util.Scanner;
    public class Main {
        public static void main(String[] args) {
            //Kullanıcının satır ve sütun sayısını belirlediği inputlar
            Scanner inp = new Scanner(System.in);
            System.out.println("Mayın tarlası oyununa hoş geldiniz!");
            System.out.print("Oyunu oluşturmak için lütfen satır sayısını giriniz : ");
            int rows = inp.nextInt();
            System.out.print("Oyunu oluşturmak için lütfen sütun sayısını giriniz : ");
            int cols = inp.nextInt();

            //Sınıfımızı bir nesne olarak tanımlayıp satır ve sütun bilgilerini kurucu metoda yolluyoruz.
            MineSweeper game = new MineSweeper(rows,cols);
            game.getUserInput();
        }
    }