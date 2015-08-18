package picturedownload;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class TBinaryFileDownloader {
	URL url = null;
	HttpURLConnection urlcon = null;
	File outputFile = null;
	
	/**
	 * 
	 * �R���X�g���N�^
	 * �I�u�W�F�N�g�̏���������
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
	 * �又��
	 */
	public void done() {
		try {

			urlcon = (HttpURLConnection) url.openConnection();
			urlcon.setRequestMethod("GET");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * PATH �Z�p���[�^�ŕ�����؂���s���A�p�X�̍Ō�̕������Ԃ��B
	 * 
	 * @param s
	 * @return �p�X�̍Ō�̕�����
	 */
	static private String getFilenameFromURL(String s) {
		String[] lists = s.split("/");
		System.out.println(lists[lists.length]);
		return lists[lists.length - 1];
	}

	/**
	 * File��Folder�̕�������������A�o�̓t�@�C���̊��S�p�X������������B
	 * 
	 * @param folder
	 * @param filename
	 * @return
	 */
	private File mixFolderAndFile(String folder, String filename) {
		
		return null;
	}
}
