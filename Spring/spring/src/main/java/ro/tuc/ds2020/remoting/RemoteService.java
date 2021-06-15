package ro.tuc.ds2020.remoting;

import org.json.JSONObject;

public interface RemoteService {
    public String sendMedicationPlan();

    public void medicationTaken(String medication);
}
