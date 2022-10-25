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
        //
        //p = players;
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
            turn = players.length;


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

            if (turn % 2 == 0) {
                g2.drawString("Player_1's Turn",400,20);
            }else{
                g2.drawString("Player_2's Turn",400,20);
            }


        }

        public void mousePressed(MouseEvent e) {

            int x = e.getX();
            int y = e.getY();
            int xSpot=x/cellWidth;
            int ySpot=y/cellWidth;
            if(turn%2==0){
                if(grid[ySpot][xSpot] == Color.WHITE) {
                   // grid[ySpot][xSpot] = new Color(255, 0, 0);
                    grid[ySpot][xSpot] = players[turn%2].getToken();
                }
                else{
                    turn++;//skip this turn to redo
                }
            }else{
                if(grid[ySpot][xSpot] == Color.WHITE) {
                    //grid[ySpot][xSpot]=new Color(255,255,0);
                    grid[ySpot][xSpot] = players[turn%2].getToken();
                }
                else{
                    turn++;//skip this turn to redo
                }
            }
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
    }
}
