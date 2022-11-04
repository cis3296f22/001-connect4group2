import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class DrawGrid {
    private JFrame frame;
    private static boolean didNotMove;
    private boolean moveSuccessful = true;
    private boolean ai = false;
    private JPanel board;
    private Dimension boardSize;

    public DrawGrid(Player[] players, LayoutDetails ld, boolean hasAi) {
        RoundButton rButton = new RoundButton(new ImageIcon("images\\replay.png"));
        ai = hasAi;

        frame = new JFrame("CONNECT 4");
        frame.setBounds(ld.getX(),ld.getY(),ld.getWidth(), ld.getHeight());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(frame.getSize());
        frame.setLayout(new BoxLayout(frame.getContentPane(),BoxLayout.Y_AXIS));

        // make some room for button
        boardSize = new Dimension(ld.getWidth(),ld.getHeight()-150);
        board = new MultiDraw(boardSize,players);

        frame.add(board);


        // empty space
        JPanel container = new JPanel();
        container.setSize(ld.width,100);
        container.add(Box.createRigidArea(new Dimension(0,100)));
        container.add(rButton);
        frame.add(container);
        // button action: reset the game
        rButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // remove the board
                frame.getContentPane().remove(board);
                // rebuild a board
                board = new MultiDraw(boardSize,players);
                frame.add(board,players);
                // refresh the frame
                frame.repaint();
            }
        });





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

            //mini interface
            int numPlayers = players.length;

            g2.setColor(new Color(255, 255, 255));
            String s = "Turns: " + (turn-2);
            g2.drawString(s,cellWidth * (1 + cols), 20);
            g2.setColor(players[turn%numPlayers].getToken());
            g2.fillOval(cellWidth * (1 + cols),40,cellWidth,cellWidth);

            //Error message
            if(moveSuccessful == false)
            {
                g2.setColor(Color.RED);
                g2.drawString("Move was not successful please redo the move", cellWidth * (1 + cols), 50+cellWidth);
            }

            //no AI
            g2.setColor(new Color(255, 255, 255));
            if(!ai)
            {
                g2.drawString("Player_" + (turn%numPlayers + 1) + "'s Turn",cellWidth * (1 + cols), 30);
            }
            //there is an AI
            else
            {
                //player
                if(turn%numPlayers != numPlayers-1)
                {
                    g2.drawString("Player_" + (turn%numPlayers + 1) + "'s Turn",cellWidth * (1 + cols), 30);
                }
                else
                {
                    g2.drawString("AI_" + (turn%numPlayers) + "'s Turn",cellWidth * (1 + cols), 30);
                }

            }

        }

        public void mousePressed(MouseEvent e) {

            int x = e.getX();
            int y = e.getY();
            int xSpot=x/cellWidth;
            int ySpot= 0;
            int numPlayers = players.length;

            int temp = turn;

            try
            {
                turn = generatePlayerMove(ySpot, xSpot, turn, numPlayers);
                moveSuccessful = true;
                //move fails
                if(temp != turn)
                {
                    moveSuccessful = false;
                }
            }
            catch (ArrayIndexOutOfBoundsException ae)
            {
                //move fails
                moveSuccessful = false;
                turn--;
            }

            System.out.println(x + " " + xSpot + " " + y + " "+ ySpot);
            turn++;
            repaint();
        }

        public void mouseReleased(MouseEvent e) {

            if(ai)
            {
                if(moveSuccessful)
                {
                    int x = e.getX();
                    int y = e.getY();
                    int xSpot=x/cellWidth;
                    int ySpot= 0;
                    int numPlayers = players.length;
                    int temp = turn;

                    try {
                        TimeUnit.MILLISECONDS.sleep(500);
                    } catch (InterruptedException ie) {
                        throw new RuntimeException(ie);
                    }


                    turn = generateAIMove(turn, numPlayers);
                    //move failed, then redo
                    while(temp != turn)
                    {
                        turn = generateAIMove(temp, numPlayers);
                    }


                    turn++;
                    repaint();
                }
            }

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

        public int generateAIMove(int turn, int numPlayers) {

            int ySpot = 0;
            Random rand = new Random();
            int xSpot = rand.nextInt(cols);
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
