import history.Memento;
import history.SimHistory;
import population.Population;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SimPanel extends JPanel implements Runnable {
    Thread simThread;
    SimulationController simulationController;
    SimHistory simHistory;


    public SimPanel(int screenWidth, int screenHeight){
        simulationController = new SimulationController(10, 10);
        simHistory = new SimHistory();
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.WHITE);
        this.setDoubleBuffered(true);
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                // Not used in this context
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == 'L' || e.getKeyChar() == 'l') {
                    showChooseMementoWindow();
                }
                if (e.getKeyChar() == 'S' || e.getKeyChar() == 's') {
                    simHistory.add(simulationController.saveMemento());
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // Not used in this context
            }
        });

        this.setFocusable(true);
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
        simulationController.performSimulationStep();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        simulationController.drawPopulation(g, this.getWidth(), this.getHeight());
        Font font = new Font("Arial", Font.PLAIN, 20);
        g.setFont(font);
        g.setColor(Color.BLACK);
        g.drawString("Time: " + simulationController.getSimTime()/25 + " seconds", 10, 20);
    }

    private void showChooseMementoWindow() {
        // Create and show the ChooseMementoWindow
        ChooseMementoWindow chooseMementoWindow = new ChooseMementoWindow(simHistory);
        chooseMementoWindow.setVisible(true);

        // Add ActionListener to handle the selection in ChooseMementoWindow
        chooseMementoWindow.setSelectListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = chooseMementoWindow.getSelectedIndex();
                if (selectedIndex != -1) {
                    Memento selectedMemento = simHistory.get(selectedIndex);
                    // Call loadMemento() method from SimulationController with selected memento
                    simulationController.loadMemento(selectedMemento);
                }
            }
        });
    }

}
