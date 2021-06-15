import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MonitoredData {

	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private String activity;
	
	public MonitoredData(LocalDateTime st, LocalDateTime et, String activity) {
		this.startTime = st;
		this.endTime = et;
		this.activity = activity;
	}
	
	public MonitoredData() {
		this.activity = "";
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}
	
	@Override
	public String toString() {
		DateTimeFormatter dt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return "MonitoredData [ Start Time:" + startTime.format(dt) + ", End Time:" + endTime.format(dt) + ", Activity: " + activity + "]";
	}
}
