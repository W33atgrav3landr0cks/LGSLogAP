import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import javax.net.ssl.HttpsURLConnection;
import com.opencsv.CSVWriter;

public class Main extends TimerTask{

	static String userName;
	static String path;
	
	public static void main(String[] args) {
		Scanner myObj = new Scanner(System.in);
	    
	    System.out.println("Name des APs welchen du loggen willst"); 
	    userName = myObj.nextLine(); 
	    System.out.println("Name des Pfades zur CSV"); 
	    path = myObj.nextLine();
	    myObj.close();
	       
		Timer timer = new Timer();
		timer.schedule(new Main(), 0, 20000);

		

	}
	 public static void alles()throws IOException {
		 CSVWriter writer = new CSVWriter(new FileWriter(path,true));
		 URL link = new URL("https://bigbrother.lgsit.de/info/quackquack.php");
			HttpsURLConnection con = (HttpsURLConnection) link.openConnection();
			BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

			CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withDelimiter(';'));
			for (CSVRecord csvRecord : csvParser) {
				if (csvRecord.get(0).equals(userName)) {
					String[] dome = new String[]{new SimpleDateFormat("MM.dd.HH.mm.ss").format(new Date()), csvRecord.get(1).replace("\"","")};
					writer.writeNext(dome);
					writer.flush();
					for(String jaro:dome) {System.out.println(jaro);}
				}

			}
			csvParser.close();
			System.out.println();
	 }
	@Override
	public void run(){
		// TODO Auto-generated method stub
		try {
			alles();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}