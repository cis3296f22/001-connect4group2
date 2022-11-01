import javax.swing.*;


public class DrawMenu {

    JFrame frame;


    // x : number of pixels from left of the screen
    // y : number of pixels from top of the screen
    // width & height are the size of the frame
    public DrawMenu(LayoutDetails ld){
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
        f.add(new JButton("Select Token"));
        f.add(new JButton("Player vs Player"));
        f.add(new JButton("Player vs AI"));
    }

    public void hideFrame(Boolean b){
        frame.setVisible(b);
    }

    // move frame to a new location
    public void refreshFrame(LayoutDetails ld){
        frame.setBounds(ld.getX(),ld.getY(),ld.getWidth(),ld.getHeight());
        frame.setVisible(true);
    }

}
