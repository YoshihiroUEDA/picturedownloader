package picturedownload;

import java.io.IOException;
import java.net.*;

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
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
