import java.util.Scanner;

public class LocalPlayer extends Player{
    private Scanner sc;
    private String name = "";
    private int col;
    public LocalPlayer()
    {
        sc = new Scanner(System.in);
    }
    public LocalPlayer(String name)
    {
        this.name = name;
        sc = new Scanner(System.in);
    }
    @Override
    public int GetMove() {
        System.out.print("Player "+this.name+", Please choose a move: ");
        return sc.nextInt();
    }
}
