package picturedownload;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class TBinaryFileDownloader {
	URL url =null;
	HttpURLConnection urlcon =null;
	File outputFile=null;
	
	public TBinaryFileDownloader( String _url, String savePath){
		File f = new File(savePath);
		if ( !f.isDirectory()){
			System.out.println(f.getName() + "is file.\nprogram terminated.");
		}
		outputFile= mixFolderAndFile( savePath ,getFilenameFromURL(_url));
		try {
			url = new URL(_url);
			urlcon = (HttpURLConnection) url.openConnection();
			urlcon.setRequestMethod("GET");
			
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void done(){
		
	}
	static private String getFilenameFromURL( String s){
		
		return "";
	}
	/**
	 * File‚ÆFolder‚Ì
	 * @param folder
	 * @param filename
	 * @return
	 */
	private File mixFolderAndFile( String folder, String filename){
		
		return null;
	}
}
