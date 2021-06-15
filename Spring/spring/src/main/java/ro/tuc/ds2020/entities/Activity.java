package ro.tuc.ds2020.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
public class Activity implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-binary")
    private UUID id;

    @Column(name = "patient_id", nullable = false)
    private String patient_id;

    @Column(name = "activity", nullable = false)
    private String activity;

    @Column(name = "startTime", nullable = false)
    private Date startTime;

    @Column(name = "endTime", nullable = false)
    private Date endTime;

    public Activity(){

    }

    public Activity(String patient_id, Date start, Date end, String activity){
        this.patient_id = patient_id;
        this.activity = activity;
        this.startTime = start;
        this.endTime = end;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public Date getStart() {
        return startTime;
    }

    public void setStart(Date start) {
        this.startTime = start;
    }

    public Date getEnd() {
        return endTime;
    }

    public void setEnd(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", activity='" + activity + '\'' +
                ", start=" + startTime +
                ", end=" + endTime +
                '}';
    }
}
