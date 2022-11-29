import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class DrawGrid {
    private JFrame frame;
    private boolean moveSuccessful = true;
    private boolean ai;
    private JPanel board;
    private Dimension boardSize;
    private JLabel win_label;
    private Player[] players;
    private boolean gameOver = false;
    private boolean gameEnd = false;
    private boolean isMute;

    private JPanel container;
    public DrawGrid(Player[] players, LayoutDetails ld, boolean hasAi, DrawMenu menu, AI algorithm) {
        this.players=players;
        RoundButton rButton = new RoundButton(new ImageIcon(getClass().getResource("/res/images/replay.png")),null);
        ai = hasAi;
        isMute = menu.getIsMute();

        frame = new JFrame("CONNECT 4");
        frame.setBounds(ld.getX(),ld.getY(),ld.getWidth(), ld.getHeight());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(frame.getSize());
        frame.setLayout(new BoxLayout(frame.getContentPane(),BoxLayout.Y_AXIS));

        // make some room for button
        boardSize = new Dimension(ld.getWidth(),ld.getHeight()-150);
        board = new MultiDraw(boardSize,players,algorithm);

        frame.add(board);

        Window w=new Window(null)
        {
            @Override
            public void paint(Graphics g)
            {
                final Font font = getFont().deriveFont(48f);
                g.setFont(font);
                g.setColor(Color.RED);
                final String message = "Hello";
                FontMetrics metrics = g.getFontMetrics();
                if(gameOver)
                {
                    //draw line
                }
            }
            @Override
            public void update(Graphics g)
            {
                paint(g);
            }
        };
        w.setAlwaysOnTop(true);
        w.setBounds(w.getGraphicsConfiguration().getBounds());
        w.setBackground(new Color(0, true));
        w.setVisible(true);


        // empty space for buttons
        this.container = new JPanel();
        container.setSize(ld.width,100);
        container.add(Box.createRigidArea(new Dimension(0,110)));
        // button area layout
        container.setLayout(new FlowLayout(FlowLayout.LEFT,60,0));
        // menu button
        JLabel menuLabel = new JLabel(new ImageIcon(getClass().getResource("/res/images/menu.png")));
        // exit button
        JLabel exitLabel = new JLabel(new ImageIcon(getClass().getResource("/res/images/exit.png")));

        win_label = new JLabel("WINNER: ");
        win_label.setVisible(false);

        container.add(menuLabel);
        container.add(rButton);
        container.add(exitLabel);
        container.add(win_label);


        // button action: reset the game
        rButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clickSound("/res/sounds/mixkit-unlock-game-notification-253.wav", menu.getIsMute());
                win_label.setVisible(false);
                // remove the board
                frame.getContentPane().remove(board);
                // rebuild a board
                board = new MultiDraw(boardSize,players,algorithm);
                gameOver = false;
                frame.add(board,players);
                frame.getContentPane().remove(container);
                frame.add(container);
                // refresh the frame
                frame.repaint();
            }
        });
        // menu label action: hide current frame & display front page
        menuLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //play sound effect
                clickSound("/res/sounds/mixkit-unlock-game-notification-253.wav", menu.getIsMute());
                // hide the board
                frame.setVisible(false);
                // update frame location
                ld.setX(frame.getLocation().x);
                ld.setY(frame.getLocation().y);

                menu.refreshFrame(ld);

            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}

        });
        // exit label action: close current frame & close front page
        exitLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //play sound effect
                clickSound("/res/sounds/mixkit-retro-arcade-casino-notification-211.wav", menu.getIsMute());
                menu.closeMenu();
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });

        frame.add(container);
        frame.pack();
        frame.setVisible(true);
    }

    private boolean getMute(boolean isMute)
    {
        return isMute;
    }

    public class MultiDraw extends JPanel  implements MouseListener {
        private int startX = 10;
        private int startY = 10;
        private int cellWidth = 40;
        private int turn;
        private int rows = 6;
        private int cols = 7;
        private Player[] players;
        private Boolean disablePanelMouseEvent;
        private Color[][] grid = new Color[rows][cols];
        private AI ai_al;
        public MultiDraw(Dimension dimension, Player[] PL, AI ai_algorithm) {

            players = PL;
            turn = players.length;
            ai_al = ai_algorithm;

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

            // winner screen
            disablePanelMouseEvent = false;

        }

        @Override
        public void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D)g;
            Dimension d = getSize();
            //adjust background shading 0-255
            int shade = 230;
            g2.setColor(new Color(shade, shade, shade));
            g2.fillRect(0,0,d.width,d.height);
            startX = 30;
            int tempX = startX;
            startY = 10;
            int gridAdjust = 5;
            int arcSize = 10;

            //draw board layout
            g2.setColor(Color.BLUE);
            g2.fillRoundRect(startX-gridAdjust, startY-gridAdjust, grid[0].length*cellWidth + (2*gridAdjust),
                    grid.length*cellWidth + (2*gridAdjust), arcSize, arcSize);

            //2) draw grid here
            for (int row = 0; row < grid.length; row++) {
                for (int col = 0; col < grid[0].length; col++) {
                    g2.setColor(grid[row][col]);
                    g2.fillOval(startX,startY,cellWidth,cellWidth);
                    startX=startX+cellWidth;
                }
                startX=tempX;
                startY+=cellWidth;

            }

            //mini interface
            int numPlayers = players.length;

            g2.setColor(Color.BLACK);
            String s = "Turns: " + (turn-2);
            g2.drawString(s,cellWidth * (1 + cols) + startX, 20);
            g2.setColor(players[turn%numPlayers].getToken());
            g2.fillOval(cellWidth * (1 + cols) + startX,40,cellWidth,cellWidth);

            //Error message
            if(moveSuccessful == false)
            {
                g2.setColor(Color.RED);
                g2.drawString("Move was not successful please redo", cellWidth * (1 + cols) + startX, 50+cellWidth);
            }

            //no AI
            g2.setColor(Color.BLACK);
            if(!ai)
            {
                g2.drawString("Player_" + (turn%numPlayers + 1) + "'s Turn",cellWidth * (1 + cols) + startX, 30);
            }
            //there is an AI
            else
            {
                //player
                if(turn%numPlayers != numPlayers-1)
                {
                    g2.drawString("Player_" + (turn%numPlayers + 1) + "'s Turn",cellWidth * (1 + cols) + startX, 30);
                }
                else
                {
                    g2.drawString("AI_" + (turn%numPlayers) + "'s Turn",cellWidth * (1 + cols) + startX, 30);
                }
            }

            //draw game end screen
            if(gameEnd)
            {
                int boxWidth = 200;
                int boxHeight = 100;
                int boxArc = 10;
                int shading = 255;
                int shading2 = 80;
                int adj = 15;
                int adj2 = 10;
                int inc = 9;

                int sx = cellWidth * (1 + cols) + startX;
                int sy = d.height/2;
                g2.setColor(new Color(shading2, shading2, shading));
                g2.fillRoundRect(sx-adj, sy-adj, boxWidth-(adj-adj2), boxHeight-(adj-adj2), boxArc, boxArc);
                g2.setColor(Color.BLACK);
                g2.fillRoundRect(sx-adj2, sy-adj2, boxWidth-adj, boxHeight-adj, boxArc, boxArc);

                g2.setColor(players[(turn-1)%numPlayers].getToken());
                g2.fillOval(sx + boxWidth-cellWidth*2-30, sy-10, cellWidth, cellWidth);
                g2.setColor(Color.WHITE);
                g2.drawString("Result Screen", sx, sy+3);

                Color win = checkIfWon();
                if(ai)
                {
                    if(players[1].getToken() == win)
                    {
                        inc+=10;
                        g2.drawString("AI Wins!", sx, sy+inc);
                    }
                    else
                    {
                        inc+=10;
                        g2.drawString("Player Wins!", sx, sy+inc);
                    }
                }
                else
                {
                    inc+=10;
                    for(int i = 0; i < players.length; i++)
                    {
                        if(players[i].getToken() == win)
                        {
                            g2.drawString("Player " + (i+1) + " Wins!", sx, sy+inc);
                            break;
                        }
                    }
                }

                inc+=20;
                g2.drawString("Press Menu to go to start.", sx, sy+inc);
                inc+=10;
                g2.drawString("Press Restart Icon to replay.", sx, sy+inc);
                inc+=10;
                g2.drawString("Press Exit to leave game.", sx, sy+inc);

                gameEnd = false;

            }

        }

        public void mousePressed(MouseEvent e) {
            // disable mouse event when winner screen pops out
            if(!disablePanelMouseEvent){
                int x = e.getX();
                int y = e.getY();
                int xSpot=(x-startX)/cellWidth;
                int ySpot = 0;
                int numPlayers = players.length;

                clickSound("/res/sounds/mixkit-unlock-game-notification-253.wav", getMute(isMute));

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

//            System.out.println(x + " " + xSpot + " " + y + " "+ ySpot);
                turn++;
                repaint();
            }


        }

        public void mouseReleased(MouseEvent e) {
            // disable mouse event when winner screen pops out
            if(!disablePanelMouseEvent){
                if(ai)
                {
                    if(moveSuccessful)
                    {
                        //win check
                        Color winner = checkIfWon();
                        if(winner != Color.WHITE)
                        {
                            System.out.println("WINNER: "+winner);
                            win_label.setVisible(true);
                            for(int i = 0; i < players.length; i++)
                            {
                                if(players[i].getToken() == winner)
                                {
                                    gameEnd = true;
                                    win_label.setText("Player "+(i+1)+" Wins!");
                                    break;
                                }
                            }
                            disablePanelMouseEvent = true;

                        }else{
                            int numPlayers = players.length;
                            int temp = turn;

                            try {
                                TimeUnit.MILLISECONDS.sleep(500);
                            } catch (InterruptedException ie) {
                                throw new RuntimeException(ie);
                            }

                            // public int generateAIMove(int turn, int numPlayers, int cols, int rows, Player[] players, Color[][] grid) {
                            ai_al.setColor(players[turn%numPlayers].getToken());
                            turn = ai_al.generateAIMove(turn, numPlayers,cols,rows,players,grid);
                            //move failed, then redo
                            while(temp != turn)
                            {
                                turn = ai_al.generateAIMove(temp, numPlayers,cols,rows,players,grid);
                            }

                            winner = checkIfWon();
                            if(winner != Color.WHITE) {
                                System.out.println("WINNER: " + winner);
                                win_label.setVisible(true);
                                for(int i = 0; i < players.length; i++)
                                {
                                    if(players[i].getToken() == winner)
                                    {
                                        win_label.setText("AI "+(1)+" Wins!");
                                        gameEnd = true;
                                        repaint();
                                        break;
                                    }
                                }
                                disablePanelMouseEvent = true;
                            }


                            turn++;
                        }
                        repaint();


                    }
                }else{//Player vs. Player
                    // win check
                    Color winner = checkIfWon();
                    if(winner != Color.WHITE)
                    {
                        System.out.println("WINNER: "+winner);
                        win_label.setVisible(true);
                        for(int i = 0; i < players.length; i++)
                        {
                            if(players[i].getToken() == winner)
                            {
                                win_label.setText("Player "+(i+1)+" Wins!");
                                gameEnd = true;
                                repaint();
                                break;
                            }
                        }
                        disablePanelMouseEvent = true;
                    }
                }
            }


        }

        public void mouseEntered(MouseEvent e) {

        }

        public void mouseExited(MouseEvent e) {

        }

        public void mouseClicked(MouseEvent e) {

        }

        private int generatePlayerMove(int ySpot, int xSpot, int turn, int numPlayers) {

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

        //https://codereview.stackexchange.com/questions/127091/java-connect-four-four-in-a-row-detection-algorithms
        private Color checkIfWon()
        {
            int HEIGHT = rows;
            int WIDTH = cols;
            Color EMPTY_SLOT = Color.WHITE;
            for (int r = 0; r < HEIGHT; r++) { // iterate rows, bottom to top
                for (int c = 0; c < WIDTH; c++) { // iterate columns, left to right
                    Color player = grid[r][c];
                    if (player == EMPTY_SLOT)
                        continue; // don't check empty slots

                    if (c + 3 < WIDTH &&
                            player == grid[r][c+1] && // look right
                            player == grid[r][c+2] &&
                            player == grid[r][c+3])
                        return player;
                    if (r + 3 < HEIGHT) {
                        if (player == grid[r+1][c] && // look up
                                player == grid[r+2][c] &&
                                player == grid[r+3][c])
                            return player;
                        if (c + 3 < WIDTH &&
                                player == grid[r+1][c+1] && // look up & right
                                player == grid[r+2][c+2] &&
                                player == grid[r+3][c+3])
                            return player;
                        if (c - 3 >= 0 &&
                                player == grid[r+1][c-1] && // look up & left
                                player == grid[r+2][c-2] &&
                                player == grid[r+3][c-3])
                            return player;
                    }
                }
            }
            return EMPTY_SLOT;
        }

    }
    private void clickSound(String path, boolean mute){
//mute SE
        if(!mute)
            return;

        try{
            SoundEffect se = new SoundEffect();
            se.playBackGround(path);
            Thread t1 = new Thread(se);
            t1.start();
        }catch (Exception ae)
        {
            System.out.println(ae.getMessage());
        }
    }
}
