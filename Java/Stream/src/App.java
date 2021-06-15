import java.util.List;

public class App {

	public static void main(String[] args) {
		FileReader f = new FileReader();
		
		List<MonitoredData> list = f.getList();
		
		Control cont = new Control(list);
	}
	
	
}
