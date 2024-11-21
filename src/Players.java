import java.util.Scanner;

public class Players {
    public static Player PlayerOne;
    public static Player PlayerTwo;
    public static Player PlayerThree;
    public static Player PlayerFour;
    public static Player PlayerFive;

    //I know I can use a loop here. It is 2 am and I do not feel like it
    public Players() {
//        Scanner input = new Scanner(System.in);
//        System.out.print("Enter Player 1 name: ");
//        PlayerOne = new Player(input.next());
//
//        input = new Scanner(System.in);
//        System.out.print("Enter Player 2 name: ");
//        PlayerTwo = new Player(input.next());
//
//        input = new Scanner(System.in);
//        System.out.print("Enter Player 3 name: ");
//        PlayerThree = new Player(input.next());
//
//        input = new Scanner(System.in);
//        System.out.print("Enter Player 4 name: ");
//        PlayerFour = new Player(input.next());
//
//        input = new Scanner(System.in);
//        System.out.print("Enter Player 5 name: ");
//        PlayerFive = new Player(input.next());
        PlayerOne = new Player("");
        PlayerTwo = new Player("");
        PlayerThree = new Player("");
        PlayerFour = new Player("");
        PlayerFive = new Player("");


    }
    class Player {
        String name;
        int points = 0;
        public Player(String name) {
            this.name = name;
        }
        public int getPoints() {
            return points;
        }
    }
}
