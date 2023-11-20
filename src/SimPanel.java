import javax.swing.*;
import java.awt.*;

public class SimPanel extends JPanel implements Runnable {
    final int specimenSize = 16;
    Thread simThread;
    public SimPanel(int screenWidth, int screenHeight){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.WHITE);
        this.setDoubleBuffered(true);
    }
    public void startSimThread(){
        simThread = new Thread(this);
        simThread.start();
    }

    @Override
    public void run() {
        while(simThread != null){
            update();
            repaint();
        }
    }

    public void update(){

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.black);
        g2.fillOval(100, 100, specimenSize, specimenSize);
        g2.dispose();
    }

}
