import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class DrawGrid {
    private JFrame frame;
    private Player[] p;

    public DrawGrid(Player[] players) {
        frame = new JFrame("DrawGrid");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(frame.getSize());
        frame.add(new MultiDraw(frame.getSize(),players));
        frame.pack();
        frame.setVisible(true);
    }



    public class MultiDraw extends JPanel  implements MouseListener {
        int startX = 10;
        int startY = 10;
        int cellWidth = 40;
        int turn;
        int rows = 6;
        int cols = 7;
        Player[] players;

        Color[][] grid = new Color[rows][cols];

        public MultiDraw(Dimension dimension, Player[] PL) {

            players = PL;
            turn = 0;


            setSize(dimension);
            setPreferredSize(dimension);
            addMouseListener(this);
            //1. initialize array here
            int x = 0;
            for (int row = 0; row < grid.length; row++) {
                for (int col = 0; col < grid[0].length; col++) {
                    grid[row][col]= Color.WHITE;
                }
            }
        }

        @Override
        public void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D)g;
            Dimension d = getSize();
            g2.setColor(new Color(0, 0, 0));
            g2.fillRect(0,0,d.width,d.height);
            startX = 0;
            startY = 0;

            //2) draw grid here
            for (int row = 0; row < grid.length; row++) {
                for (int col = 0; col < grid[0].length; col++) {
                    g2.setColor(grid[row][col]);
                    g2.fillOval(startX,startY,cellWidth,cellWidth);
                    startX=startX+cellWidth;
                }
                startX=0;
                startY+=cellWidth;

            }

            g2.setColor(new Color(255, 255, 255));
            String s = "Turns: " + (turn-2);
            g2.drawString(s,cellWidth * (1 + cols), 20);

            int numPlayers = players.length;


            g2.drawString("Player_" + (turn%numPlayers + 1) + "'s Turn",cellWidth * (1 + cols), 30);
            g2.setColor(players[turn%numPlayers].getToken());
            g2.fillOval(cellWidth * (1 + cols),40,cellWidth,cellWidth);


        }

        public void mousePressed(MouseEvent e) {

            int x = e.getX();
            int y = e.getY();
            int xSpot=x/cellWidth;
            int ySpot= 0;
            int numPlayers = players.length;

            turn = generatePlayerMove(ySpot, xSpot, turn, numPlayers);

            System.out.println(x + " " + xSpot + " " + y + " "+ ySpot);
            turn++;
            repaint();
        }

        public void mouseReleased(MouseEvent e) {

        }

        public void mouseEntered(MouseEvent e) {

        }

        public void mouseExited(MouseEvent e) {

        }

        public void mouseClicked(MouseEvent e) {

        }

        public int generatePlayerMove(int ySpot, int xSpot, int turn, int numPlayers) {

            ySpot = 0;
            while ((grid[ySpot][xSpot] == Color.WHITE) && (ySpot < rows - 1)) {
                ySpot++;
            }
            if (grid[ySpot][xSpot] == Color.WHITE) {
                grid[ySpot][xSpot] = players[turn % numPlayers].getToken();
            } else if (ySpot - 1 >= 0 && grid[ySpot - 1][xSpot] == Color.WHITE) {
                grid[ySpot - 1][xSpot] = players[turn % numPlayers].getToken();
            } else {
                turn--;
            }

            return turn;

        }

    }
}
