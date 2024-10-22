package c1016;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DiaryFileManger implements FileManagable{
	String dir = "C:\\test\\diary";
	File source = new File(dir);
	String spath = source.getPath();
	@Override
	public void regist(Diary diary) {
		try {
			FileWriter fw = new FileWriter(spath+"/"+diary.getTitle()+".txt");
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(diary.getComment());
			bw.flush();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public String[] getList() {
		String[] fileNames = source.list();
		return fileNames;
	}
	@Override
	public String get(String fileName) {
		String comments = "";
		try {
			FileReader fr = new FileReader(spath+"/"+fileName+".txt");
			BufferedReader br = new BufferedReader(fr);
			String s = "";
			while((s = br.readLine()) != null) {
				comments += s + "\n";
			}
			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			//e.printStackTrace();
			System.out.println("해당되는 다이어리를 찾을 수 없습니다.");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return comments.replaceAll("\\n$", "");
	}
	@Override
	public int update(String title, String comments) {
		int result = 0;
		try {
			FileWriter fw = new FileWriter(spath+"/"+title+".txt", false);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(comments);
			bw.flush();
			bw.close();
			fw.close();
			result = 1;
		} catch (IOException e) {
			//e.printStackTrace(); 
			System.out.println("해당되는 다이어리를 찾을 수 없습니다.");
		}
		return result;
	}
	@Override
	public int append(String title, String comments) {
		int result = 0;
		try {
			FileWriter fw = new FileWriter(spath+"/"+title+".txt", true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(comments);
			bw.flush();
			bw.close();
			fw.close();
			result = 1;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	@Override
	public int delete(String title) {
		int result = 0;
		File file = new File(spath+"/"+title+".txt");
        
    	if( file.exists() ){
    		if(file.delete()) result = 1;
    	}else System.out.print("파일이 존재하지 않습니다.");
		return result;
	}
}
