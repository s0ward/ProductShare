import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;


public class Database {

	private static final String LOG = "C:\\ServerDatabase\\Log.txt";
	private static final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	private HashMap<String,String> data = new HashMap<String,String>();
	private BufferedWriter bw = null;
	private FileWriter fw = null;
	private BufferedReader br = null;
	private FileReader fr = null;
	
	Database(){
		data.put("Kristiyan" , "C:\\ServerDatabase\\Kristiyan.txt");
		data.put("Kristina" , "C:\\ServerDatabase\\Kristina.txt");
		data.put("Mette" , "C:\\ServerDatabase\\Mette.txt");
	}
	
	public void writeLog(String line) {
		
		Date date = new Date();
		

		try {
			fw = new FileWriter(LOG, true);
			bw = new BufferedWriter(fw);
			
			bw.write(line+" "+sdf.format(date));
			bw.newLine();
			bw.flush();

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}

	}
	
	public void writeBalance(String line) {
		
		String from = line.split(" ")[1];
		String to = line.split(" ")[2];
		Double amount = Double.parseDouble(line.split(" ")[3]);

		try {
			
			fr = new FileReader(data.get(from));
			br = new BufferedReader(fr);
			double fromAmount = Double.parseDouble(br.readLine());
			
			
			fw = new FileWriter(data.get(from));
			bw = new BufferedWriter(fw);
			Double resultFrom = ((long) ((fromAmount+amount)*100))/100.0;
			bw.write(resultFrom.toString());
			bw.flush();
			
			
			fr = new FileReader(data.get(to));
			br = new BufferedReader(fr);
			
			double toAmount = Double.parseDouble(br.readLine());
			
			
			fw = new FileWriter(data.get(to));
			bw = new BufferedWriter(fw);
			Double resultTo = ((long) ((toAmount-amount)*100))/100.0;
			bw.write(resultTo.toString());
			bw.flush();
			
			
			

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
	};
	
	public String readBalance(String name){
		String amount = "UNKNOWN";
		
		try {

			fr = new FileReader(data.get(name));
			br = new BufferedReader(fr);
			
			amount = br.readLine();

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
		return amount;
	}
	public String readLog(){
		String message = "";
		String line;
		
		try {

			fr = new FileReader(LOG);
			br = new BufferedReader(fr);
			
			while((line=br.readLine())!= null){
				message+=line+" ; ";
			}

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
		return message;
	}
}

