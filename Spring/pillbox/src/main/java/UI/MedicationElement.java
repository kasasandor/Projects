package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MedicationElement extends JPanel {

    private JLabel medicationLabel;
    private JLabel intakePeriod;
    private JButton takeBtn;

    public MedicationElement(String medicationName, String intakePeriod, Dimension dimension){
        this.medicationLabel = new JLabel(medicationName);
        this.intakePeriod = new JLabel(intakePeriod);
        this.takeBtn = new JButton("Taken");
        configureElements(dimension);
    }

    private void configureElements(Dimension dimension){
        this.setLayout(new GridLayout(1, 3));
        this.setPreferredSize(dimension);
        this.add(medicationLabel);
        this.add(intakePeriod);
        this.add(takeBtn);
        this.medicationLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.intakePeriod.setHorizontalAlignment(SwingConstants.CENTER);
    }

    public void attachListener(ActionListener e){
        this.takeBtn.addActionListener(e);
    }

    public String getMedicationName(){
        return this.medicationLabel.getText();
    }
}
