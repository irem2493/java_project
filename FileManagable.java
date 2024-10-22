package c1016;

public interface FileManagable {
	//파일 등록
	void regist(Diary diary);
	
	//파일 조회
	String get(String fileName);
	
	//파일 목록
	String[] getList();
	
	//파일 수정
	int update(String title, String comments);
	
	//기존의 파일에 내용 덧붙임
	int append(String title, String comments);
	
	//파일 삭제
	int delete(String title);
}
