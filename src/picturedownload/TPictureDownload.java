package picturedownload;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class TPictureDownload {
	HttpURLConnection urlcon=null;
	URL url = null;
	
	static String downloadUrl ="";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new TPictureDownload( downloadUrl);
	}

	public TPictureDownload( String urlString){
		try {
			url = new URL(urlString);
			urlcon = (HttpURLConnection) url.openConnection();
			urlcon.setRequestMethod("GET");
			urlcon.setInstanceFollowRedirects(false);
			urlcon.setRequestProperty("Accept-Language", "ja;q=0.7,en;q=0.3");
			
			urlcon.connect();
			
			Map<String, List<String>> headers = urlcon.getHeaderFields();
			Iterator it = headers.keySet().iterator();
			System.out.println("response header:");
			while ( it.hasNext()){
				String key = (String)it.next();
				System.out.println(" "+key+headers.get(key));	
			}
			
			System.out.println("response code["+urlcon.getResponseCode()+"]");
			System.out.println("response message["+urlcon.getResponseMessage()+"]");
			System.out.println("\n---body----");
			
			BufferedReader reader = null;
			reader=new BufferedReader(new InputStreamReader(urlcon.getInputStream() ));
			
			while(true){
				String line = reader.readLine();
				if ( null ==line){
					break;
				}
				System.out.println(line);
			}
			
			reader.close();
			reader=null;
			urlcon.disconnect();
			urlcon=null;
			url=null;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
