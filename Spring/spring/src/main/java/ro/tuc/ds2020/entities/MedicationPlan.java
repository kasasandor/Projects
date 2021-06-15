package ro.tuc.ds2020.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class MedicationPlan implements Serializable {

    private HashMap<String, Medication> medications;
    private String periodOfTreatment;

    public MedicationPlan(HashMap<String, Medication> medications, String periodOfTreatment){
        this.medications = medications;
        this.periodOfTreatment = periodOfTreatment;
    }

    public HashMap<String, Medication> getMedications(){
        return this.medications;
    }

    public void setMedications(HashMap<String, Medication> medications){ this.medications = medications; }

    public String getPeriodOfTreatment(){
        return this.periodOfTreatment;
    }

    public void setMedications(String periodOfTreatment){
        this.periodOfTreatment = periodOfTreatment;
    }

    @Override
    public String toString(){
        return medications.toString();
    }
}
