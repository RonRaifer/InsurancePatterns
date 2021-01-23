package infrastructures.JsonReader;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonReader {
	private static JsonReader instance = null;
	
	private JsonReader() {}
	
	public static JsonReader getInstance() {
        if (instance == null) {
            instance = new JsonReader();
        }
        return instance;
    }

	public List<String> readFile(String file)
	{
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(new FileReader(file));
	        JSONObject jsonObject = (JSONObject)obj;
	        String version = (String)jsonObject.get("version");
	        String student1 = (String)jsonObject.get("student_name_1");
	        String student2 = (String)jsonObject.get("student_name_2");
	        List<String> strArray = new ArrayList<String>();
	        strArray.add(version);
	        strArray.add(student1);
	        strArray.add(student2);
	        return strArray;
			} catch(Exception e) {
				e.printStackTrace();
				return null;
			}
	}
}
