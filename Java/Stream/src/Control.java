import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Control extends JFrame{

	private JButton daysBtn = new JButton("Count Days");
	private JButton activityBtn = new JButton("Count Activities");
	private JButton actPerDayBtn = new JButton("Count Activities for Each Day");
	private JButton periodBtn = new JButton("Count Activity Duration");
	
	public Control(List<MonitoredData> list) {
		this.setSize(500, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		
		ConditionLister c = new ConditionLister(list);
		
		daysBtn.setBounds(100, 50, 300, 80);
		activityBtn.setBounds(100, 150, 300, 80);
		actPerDayBtn.setBounds(100, 250, 300, 80);
		periodBtn.setBounds(100, 350, 300, 80);
		
		daysBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(c.countDaysOfMonitoredData());
			}
		});
		
		activityBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<String> activities = getActivities(list);
				Map<String, Integer> map = c.countActivityAppearance();
				
				for(String s : activities) {
					System.out.println(s + ": " + map.get(s));
				}
			}
		});
		
		actPerDayBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Map<LocalDateTime, Map<String, Integer>> act = c.countActivityAppearanceForDays();
				DateTimeFormatter form = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				List<LocalDateTime> days = getDays(list);
				for(LocalDateTime l : days) {
					System.out.println(l.format(form) + ": " + act.get(l));
				}
			}
			
		});
		
		periodBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Map<String, Long> duration = c.countTotalDurationOfActivities();
				List<String> activities = getActivities(list);
				
				for(String s : activities) {
					System.out.println(s + ": " + convertDate(duration.get(s)));
				}
			}
			
		});
		
		this.add(daysBtn);
		this.add(activityBtn);
		this.add(actPerDayBtn);
		this.add(periodBtn);
		this.setVisible(true);
	}
	
	private List<String> getActivities(List<MonitoredData> list) {
		List<String> activities = new ArrayList<String>();
		
		for(MonitoredData m : list) {
			String activity = m.getActivity();
			if(activities.contains(activity)) {
				continue;
			}
			else {
				activities.add(activity);
			}
		}
		
		return activities;
	}
	
	private List<LocalDateTime> getDays(List<MonitoredData> list){
		List<LocalDateTime> result = new ArrayList<LocalDateTime>();
		LocalDateTime currentDate = list.get(0).getStartTime();
		result.add(currentDate);
		for(MonitoredData m : list) {
			if(currentDate.getDayOfYear() == m.getStartTime().getDayOfYear()) {
				continue;
			}
			else {
				currentDate = m.getStartTime();
				result.add(currentDate);
			}
		}
		
		return result;
	}
	
	private static String convertDate(long millie) {
		List<TimeUnit> units = new ArrayList<TimeUnit>();
		units.add(TimeUnit.DAYS);
		units.add(TimeUnit.HOURS);
		units.add(TimeUnit.MINUTES);
		units.add(TimeUnit.SECONDS);
		
		Map<TimeUnit, Long> map = new LinkedHashMap<TimeUnit, Long>();
		long millies = millie;
		
		for(TimeUnit unit : units) {
			long diff = unit.convert(millies, TimeUnit.MILLISECONDS);
			long diffInMillie = unit.toMillis(diff);
			millies = millies - diffInMillie;
			
			map.put(unit,  diff);
		}
		
		String result = "[ ";
		for(TimeUnit unit : units) {
			result += map.get(unit) + " ";
			result += unit.toString() + " ";
		}
		result += "]";
		return result;
	}
}
