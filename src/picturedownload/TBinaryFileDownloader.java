package picturedownload;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class TBinaryFileDownloader {
	URL url = null;
	HttpURLConnection urlcon = null;
	File outputFile = null;
	String saveFileName=null;
	/**
	 * 
	 * コンストラクタ
	 * オブジェクトの初期化処理
	 * @param _url
	 * @param savePath
	 */
	public TBinaryFileDownloader(String _url, String savePath) {
		File f = new File(savePath);
		if (!f.isDirectory()) {
			System.out.println(f.getName() + "is file.\nprogram terminated.");
		}
		outputFile = mixFolderAndFile(savePath, getFilenameFromURL(_url));
		try {
			url = new URL(_url);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			System.out.println("URL object error.");
			e.printStackTrace();
		}
	}
	
	/**
	 * 主処理
	 */
	public void done() {
		if(null==saveFileName){
			System.out.println("save filename unset.");
			System.exit(-1);
		}
		try {

			urlcon = (HttpURLConnection) url.openConnection();
			urlcon.setRequestMethod("GET");
			urlcon.setInstanceFollowRedirects(false);
			urlcon.setRequestProperty("Accept-Language", "ja;q=0.7,en;q=0.3");
			urlcon.connect();
			

			Map<String, List<String>> headers = urlcon.getHeaderFields();
			Iterator<String> it = headers.keySet().iterator();
			System.out.println("response header:");
			while (it.hasNext()) {
				String key = (String) it.next();
				System.out.println(" " + key + headers.get(key));
			}

			System.out.println("response code[" + urlcon.getResponseCode() + "]");
			System.out.println("response message[" + urlcon.getResponseMessage() + "]");
			System.out.println("\n---body----");
			
			//	here in download code
			
			
			
			//	
			
			urlcon.disconnect();
			urlcon = null;
			url = null;


		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * PATH セパレータで文字区切りを行い、パスの最後の文字列を返す。
	 * 
	 * @param s
	 * @return パスの最後の文字列
	 */
	static private String getFilenameFromURL(String s) {
		String[] lists = s.split("/");
		System.out.println(lists[lists.length]);
		return lists[lists.length - 1];
	}

	/**
	 * FileとFolderの文字列を結合し、出力ファイルの完全パス名を完成する。
	 * 
	 * @param folder
	 * @param filename
	 * @return
	 */
	private File mixFolderAndFile(String folder, String filename) {
		
		return null;
	}
}
