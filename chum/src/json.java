import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zsw.entity.Log;

public class json {

	public static void main(String[] args) {
//		List<Log> logs = new ArrayList<>();
//		Log log=null;
//		int i;
//		for(i=0;i<5;i++) {
//			String dname="wo"+i+1;
//			String sname="wo"+i+2;
//			String ssex="wo"+i+3;
//			String ldate="wo"+i+4;
//			String lremark="wo"+i+5;
//			String tname="wo"+i+6;
//			log=new Log(dname,sname,ssex,ldate,lremark,tname);
//			logs.add(log); 
//		}
//		//String c='{"code":0,"data":}';
//		JSONObject json= new JSONObject(); 
//		json.put("code", 0);
//		//String jsonStr = JSON.toJSONString(logs);
//		json.put("data", logs);
//		System.out.println(json);
//		System.out.println(logs.size());

		System.out.println(test());
		
	}
	
	public static String test() {
		try {
			return "a";
		} finally {
			System.out.println("close");
		}
	}

}
