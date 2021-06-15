import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConditionLister {

	private List<MonitoredData> list;

	public ConditionLister(List<MonitoredData> list) {
		this.list = list;
	}
	
	public int countDaysOfMonitoredData() {
		int result = 1;
		LocalDateTime currentDate = list.get(0).getStartTime();
		
		for(MonitoredData m : list) {
			if(m.getStartTime().getDayOfYear() == currentDate.getDayOfYear()) {
				continue;
			}
			else {
				result++;
				currentDate = m.getStartTime();
			}
		}
		
		return result;
	}
	
	public Map<String, Integer> countActivityAppearance(){
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		for(MonitoredData m : list) {
			String activity = m.getActivity();
			int count = 0;
			if(map.get(activity) == null) {
				map.put(activity, 1);
			}
			else {
				count = map.get(activity);
				count++;
				map.put(activity, count);
			}
		}
		
		return map;
	}
	
	public Map<LocalDateTime, Map<String, Integer>> countActivityAppearanceForDays() {
		Map<LocalDateTime, Map<String, Integer>> result = new HashMap<LocalDateTime, Map<String, Integer>>();
		Map<String, Integer> activityCount = new HashMap<String, Integer>();
		
		LocalDateTime currentDate = list.get(0).getStartTime();
		
		for(MonitoredData m : list) {
			String activity = m.getActivity();
			int count = 0;
			if(currentDate.getDayOfYear() == m.getStartTime().getDayOfYear()) {
				if(activityCount.get(activity) == null) {
					activityCount.put(activity, 1);
				}
				else {
					count = activityCount.get(activity);
					count++;
					activityCount.put(activity, count);
				}
			}
			else {
				result.put(currentDate, activityCount);
				currentDate = m.getStartTime();
				activityCount = new HashMap<String, Integer>();
				
				activityCount.put(activity, 1);
			}
		}
		result.put(currentDate, activityCount);
		
		return result;
	}
	
	public Map<String, Long> countTotalDurationOfActivities(){
		Map<String, Long> map = new HashMap<String, Long>();
		for(MonitoredData m : list) {
			LocalDateTime st = m.getStartTime();
			LocalDateTime et = m.getEndTime();
			String activity = m.getActivity();
			
			if(map.get(activity) == null) {
				Duration dur = Duration.between(st, et);
				map.put(activity, dur.toMillis());
			}
			else {
				Duration dur = Duration.between(st, et);
				long count = map.get(activity) + dur.toMillis();
				map.put(activity, count);
			}
		}
		
		return map;
	}
}
