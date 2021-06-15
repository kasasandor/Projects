package UI;

import app.ClientInterface;
import app.RemoteService;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

@Service
public class AppWindow extends JFrame{

    private static final Dimension STANDARD_DIMENSION = new Dimension(400, 50);
    private RemoteService service;

    public AppWindow(RemoteService service){
        this.service = service;
        createAndShowGUI();
//        this.setTitle("Pillbox App");
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.setLayout(new GridLayout(6,1));
//        //this.clientInterface = clientInterface;
    }
    public void createAndShowGUI(){

        this.setTitle("Pillbox App");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(6,1));
        Clock clock = new Clock();
        clock.setSize(STANDARD_DIMENSION);
        this.getContentPane().add(clock.time, BorderLayout.NORTH);

        this.pack();
        this.setVisible(true);
        clock.start();
    }

    public void removeElements(){
        Component[] comps = AppWindow.this.getContentPane().getComponents();

        for(Component c : comps){
            if (c == comps[0]) continue;
            this.remove(c);
        }
    }

    public void addElements(String medications) {
        ArrayList<MedicationElement> elements = new ArrayList<>();
        String[] splitData = medications.split(" ");
        Arrays.sort(splitData);
        for (String s : splitData) {
            String[] parts = s.split("=");
            String medication = parts[1];
            String intakePeriod = parts[0];

            MedicationElement me = new MedicationElement(medication, intakePeriod, STANDARD_DIMENSION);
            me.attachListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    //System.out.println(me.getMedicationName() + " taken");
//                    try {
//                        //clientInterface.medicationTaken(me.getMedicationName());
//                    } catch (IOException ioException) {
//                        ioException.printStackTrace();
//                    }
                    System.out.println("Medication " + me.getMedicationName() + " taken!");
                    service.medicationTaken(me.getMedicationName());
                    AppWindow.this.remove(me);
                    SwingUtilities.updateComponentTreeUI(AppWindow.this);
                }
            });
            AppWindow.this.getContentPane().add(me);
        }
    }

}
