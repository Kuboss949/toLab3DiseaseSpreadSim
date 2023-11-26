import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("DiseaseSpreadSim");

        SimPanel simPanel = new SimPanel(700,700);
        window.add(simPanel);
        window.pack();



        window.setLocationRelativeTo(null);
        window.setVisible(true);

        simPanel.startSimThread();
        //simPanel.run();

    }
}