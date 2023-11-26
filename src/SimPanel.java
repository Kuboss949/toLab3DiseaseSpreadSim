import population.Population;

import javax.swing.*;
import java.awt.*;

public class SimPanel extends JPanel implements Runnable {
    final int specimenSize = 12;
    Thread simThread;
    Population population;
    public SimPanel(int screenWidth, int screenHeight){
        population = new Population(200, 10, 10, false);
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
        double interval = 1000000000./25.;
        double drawNextTime = System.nanoTime() + interval;
        while(simThread != null){
            update();
            repaint();

            try {
                double reminingTime = drawNextTime - System.nanoTime();
                reminingTime /= 1000000;
                if(reminingTime < 0){
                    reminingTime = 0;
                }
                Thread.sleep((long)reminingTime);
                drawNextTime += interval;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void update(){
        population.movePopulation();
        if(Math.random()>0.5){
            population.addSpecimen();
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        population.drawPopulation(g, specimenSize, this.getWidth(), this.getHeight());
    }

}
