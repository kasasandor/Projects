package ro.tuc.ds2020.remoting;

import org.json.JSONArray;
import org.json.JSONObject;
import ro.tuc.ds2020.entities.Medication;

import java.util.Date;

public class RemoteServiceImpl implements RemoteService{

    @Override
    public String sendMedicationPlan() {
        JSONArray ja = new JSONArray();
        JSONObject obj = new JSONObject();
        obj.put("intake", "15:00-16:00");
        obj.put("medication", new Medication("Paracetamol", "10mg"));
        ja.put(obj);
        JSONObject obj1 = new JSONObject();
        obj1.put("intake", "16:00-17:00");
        obj1.put("medication", new Medication("Propranolol", "10mg"));
        ja.put(obj1);
        JSONObject obj2 = new JSONObject();
        obj2.put("intake", "18:00-19:00");
        obj2.put("medication", new Medication("Xarelto", "15mg"));
        ja.put(obj2);

        JSONObject mainObj = new JSONObject();
        mainObj.put("plan", ja);

        System.out.println("Sending medication plan!");
        return mainObj.toString();
    }
    @Override
    public void medicationTaken(String medication) {
        System.out.println("Medication " + medication + " taken!");
    }
}
