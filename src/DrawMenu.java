import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class DrawMenu {

    JFrame frame;
    int mode;       // 1 => pvp,  2 => player vs ai


    // x : number of pixels from left of the screen
    // y : number of pixels from top of the screen
    // width & height are the size of the frame
    public DrawMenu(LayoutDetails ld){
        mode = 0;
        frame = new JFrame("DrawMenu");
        frame.setBounds(ld.getX(),ld.getY(),ld.getWidth(),ld.getHeight());
        // frame close button
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(frame.getSize());
        frame.setLayout(new BoxLayout(frame.getContentPane(),BoxLayout.Y_AXIS));
        createButtons(frame);
        frame.setVisible(true);
    }


    public void createButtons(JFrame f) {
        JButton st = new JButton("Select Token");
        st.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        JButton pvp = new JButton("Player vs Player");
        pvp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mode = 1;
            }
        });
        JButton pvai = new JButton("Player vs AI");
        pvai.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mode = 2;
            }
        });
        f.add(st);
        f.add(pvp);
        f.add(pvai);


        /*
        f.add(new JButton("Select Token"));
        f.add(new JButton("Player vs Player"));
        f.add(new JButton("Player vs AI"));*/
    }

    public void hideFrame(){
        frame.setVisible(false);
    }

    // move frame to a new location
    public void refreshFrame(LayoutDetails ld){
        frame.setBounds(ld.getX(),ld.getY(),ld.getWidth(),ld.getHeight());
        frame.setVisible(true);
    }

    public int getMode(){return mode;}


    public Point getlocation(){return frame.getLocation();}
}
