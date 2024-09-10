package tw.test.apis;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.SortedMap;

import org.json.JSONArray;
import org.json.JSONObject;

public class MyUtils {
	public static String loadView() throws Exception {
		String url = "D:/JavaPractice/testWeb/src/main/webapp/views/view1.html";
		String source = url;
		File viewFile = new File(source);
		int length = (int)viewFile.length();
		byte[] buffer = new byte[length];
		BufferedInputStream bin = new BufferedInputStream(new FileInputStream(viewFile));
		bin.read(buffer);
		bin.close();
		return new String(buffer);
	}
	
	public static String cal(String x,String y,String op) {
		try {
			int intX = Integer.parseInt(x);
			int intY = Integer.parseInt(y);
			StringBuffer sb = new StringBuffer();
			switch(op) {
				case "1": sb.append(intX+intY); break;
				case "2": sb.append(intX-intY); break;
				case "3": sb.append(intX*intY); break;
				case "4": sb.append(intX/intY).append("...").append(intX%intY); break;
			}
			return sb.toString();
		}catch(Exception e) {
			return "";
		}
	}
	
	public static String createScore() {
		return (int)(Math.random()*100+1)+"";
	}
	
	public static LinkedList<HashMap<String,String>> parseFood(String json) {
		LinkedList<HashMap<String,String>> items = new LinkedList<>();
		JSONArray root = new JSONArray(json);
		
		for(int i=0;i<root.length();i++) {
			JSONObject row = root.getJSONObject(i);
			HashMap<String,String> item = new HashMap<>();
			item.put("name",row.getString("Name"));
			item.put("city",row.getString("City"));
			item.put("town",row.getString("Town"));
			item.put("picture",row.getString("PicURL"));
			item.put("lat",row.getString("Latitude"));
			item.put("lng",row.getString("Longitude"));
			items.add(item);
		}
		return items;
	}
	
	public static String order2JSON(SortedMap[] rows) {
		JSONObject root = new JSONObject();
		if(rows.length>0) {
			root.put("OrderID",rows[0].getOrDefault("OrderID",""));
			root.put("CustomID",rows[0].getOrDefault("CustomerID",""));
			root.put("OrderDate",rows[0].getOrDefault("OrderDate",""));
			
			JSONArray details = new JSONArray();
			for(int i=0;i<rows.length;i++) {
				SortedMap<String,String> row = rows[i];
				JSONObject detail = new JSONObject();
				detail.put("ProductID",row.getOrDefault("ProductID",""));
				detail.put("ProductName",row.getOrDefault("ProductName",""));
				detail.put("UnitPrice",row.getOrDefault("UnitPrice",""));
				detail.put("Qty",row.getOrDefault("Quantity",""));
				details.put(detail);
			}
			root.put("details",details);
		}
		return root.toString();
	}
}
