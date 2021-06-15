import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class FileReader {

	private static final String FILE_NAME = "activities.txt";
	
	public List<MonitoredData> getList(){
		
		List<MonitoredData> list = new ArrayList<MonitoredData>();
		Converter conv = (a) -> {
			StringTokenizer defaultTokenizer = new StringTokenizer(a);
			DateTimeFormatter dt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); 
			LocalDateTime st, et;
			MonitoredData m = new MonitoredData();
			try {
				st = LocalDateTime.parse(defaultTokenizer.nextToken("\t"), dt);
				et = LocalDateTime.parse(defaultTokenizer.nextToken("\t"), dt);
				m.setActivity(defaultTokenizer.nextToken("\t"));
				m.setEndTime(et);
				m.setStartTime(st);
			}catch(Exception e) {
				e.printStackTrace();
			}
			return m;
		};
		
		try(Stream<String> stream = Files.lines(Paths.get(FILE_NAME))){
			stream.forEach(e->list.add(conv.convert(e)));
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	interface Converter{
		MonitoredData convert(String data);
	}
}
