package picturedownload;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class THTMLPageDownload {
	HttpURLConnection urlcon = null;
	URL url = null;
	ArrayList<String> lists = null;
	String saveFolder = null;

	static String downloadUrl = "";

	// public static void main(String[] args) {
	// // TODO Auto-generated method stub
	// new THTMLPageDownload(downloadUrl);
	//
	// }

	public THTMLPageDownload(String urlString) {
		try {
			url = new URL(urlString);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("url object created error.");
			System.exit(-1);
		}
		saveFolder = ".";

	}

	/**
	 * JPGのファイル名を取得する
	 * 
	 * 二重引用符で文字列を区切り、その中で、ｈｔｔｐから始まる文字列をURLとして値を返す。
	 * @param line
	 * @return URL文字列が入った配列
	 */
	private ArrayList<String> getJpgUrlFromString(String line) {
		// TODO Auto-generated method stub
		ArrayList<String> localary=new ArrayList<String>();
		String [] separateLine=line.split("\"");
		for (int i = 0; i<separateLine.length;i++){
			if( separateLine[i].indexOf("http")>=0){
				System.out.println("url ["+separateLine[i]+"]found.");
				localary.add(separateLine[i]);
			}
		}
		
		return localary;
	}

	/**
	 * 実際にダウンロードを開始する関数
	 * 
	 */
	public void done() {
		// TODO Auto-generated method stub
		lists = new ArrayList<String>();
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

			BufferedReader reader = null;
			reader = new BufferedReader(new InputStreamReader(urlcon.getInputStream()));

			while (true) {
				String line = reader.readLine();
				if (null == line) {
					break;
				}
				// ここで文字列を切り出してファイル名を取り出す
				if (line.indexOf(".jpg") > 0) {
					ArrayList<String> urls = getJpgUrlFromString(line);
					for( int j =0; j<urls.size();j++){
						lists.add(urls.get(j));
					}

				}
				System.out.println(line);
			}

			reader.close();
			reader = null;
			urlcon.disconnect();
			urlcon = null;
			url = null;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 保存場所を設定する関数 パスの区切り文字については、適宜変更する
	 * 
	 * @param string
	 *            保存場所を指定
	 */
	public void setSaveFolder(String string) {
		// TODO Auto-generated method stub
		saveFolder = string;

		File f = new File(saveFolder);
		if (!f.exists() || !f.isDirectory()) {
			System.out.println("folder:" + saveFolder + " is error path.");
			System.exit(-1);
		}
	}

}
