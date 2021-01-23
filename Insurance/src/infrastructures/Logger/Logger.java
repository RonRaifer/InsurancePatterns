package infrastructures.Logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class Logger {
	private static Logger instance = null;
	private static FileWriter fw;
	private static BufferedWriter bw;
	private Logger() 
	{
		try {
			fw = new FileWriter("Log.txt", true);
			bw = new BufferedWriter(fw);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public static Logger GetInstance() 
	{
		if (instance == null) 
		{
			instance = new Logger();
		}
		return instance;
	}

	public void log(String action) 
	{
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
		Date date = new Date(System.currentTimeMillis());
		try 
		{
			fw = new FileWriter("Log.txt", true);
			bw = new BufferedWriter(fw);
			bw.write("\n"+"["+formatter.format(date)+"]: "+action);
			bw.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
