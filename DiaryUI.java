package c1016;

import java.util.Scanner;

public class DiaryUI {
	Scanner sc = new Scanner(System.in);
	DiaryFileManger diaryFM = new DiaryFileManger();
	Diary diary = null;
	public void intro() {
		System.out.println("다이어리 프로그램을 실행합니다.");
	}
	public void showMenu() {
		System.out.print("1.다이어리 등록, 2.다이어리 내용 보기, 3.다이어리 목록 보기,"
				+ " 4.다이어리 수정, 5.다이어리 삭제, 0.프로그램 종료 : ");
		int menu = sc.nextInt();
		
		if(menu == 1) {
			System.out.println("다이어리 내용을 입력합니다. 문장은 /로 구분해주세요.");
			System.out.print("다이어리 내용 입력 : ");
			sc.nextLine();
			String comment = sc.nextLine();
			String[] parts = comment.split("/");
			StringBuilder result = new StringBuilder();
			for (String part : parts) {
	            result.append(part.trim()).append("\n"); // 각 부분에 \n 추가
			}
			diary = new Diary(result.toString());
			diaryFM.regist(diary);
			System.out.println("다이어리 등록이 완료되었습니다. 해당 파일명은 " + diary.getTitle() + "입니다.");
		}else if(menu == 2) {
			String[] str = diaryFM.getList();
			if(str.length > 0) {
			System.out.println("제목을 입력하면 해당되는 다이어리 내용을 보여드립니다.");
				System.out.print("다이어리 제목 입력 : ");
				sc.nextLine();
				String title = sc.nextLine();
				System.out.println("[해당 다이어리 내용]");
				System.out.println(diaryFM.get(title));
			}else System.out.println("현재 등록된 다이어리가 없습니다.");
		}else if(menu == 3) {
			String[] str = diaryFM.getList();
			if(str.length > 0) {
				System.out.println("저장되어 있는 다이어리 제목을 보여드립니다.");
				System.out.println("------------------------------------");
				System.out.println("저장된 다이어리 제목 출력");
				for(String s : str) {
					System.out.println(s);
				}
				System.out.println("------------------------------------");
			}else System.out.println("현재 등록된 다이어리가 없습니다.");
		}else if(menu == 4) {
			System.out.println("다이어리 내용을 수정합니다.");
			System.out.print("다이어리 제목 입력 : ");
			sc.nextLine();
			String title = sc.nextLine();
			if(diaryFM.get(title).length() != 0) {
				
				System.out.print("1.기존 내용 지우고 작성, 2.기존 내용에 덧붙이기, 3.메뉴이동 : ");
				int sel = sc.nextInt();
				if(sel == 1) {
					System.out.println("기존의 내용을 지우고 다이어리 내용을 재작성합니다.");
					System.out.print("다이어리 내용 입력 : ");
					sc.nextLine();
					String comment = sc.nextLine();
					String[] parts = comment.split("/");
					StringBuilder result = new StringBuilder();
					
					for (String part : parts) {
						result.append(part.trim()).append("\n"); // 각 부분에 \n 추가
					}
					
					int rlt = diaryFM.update(title, result.toString());
					if(rlt > 0) System.out.println("다이어리 내용 수정이 완료되었습니다.");
					else System.out.println("다이어리 내용 수정이 실패되었습니다.");
				}else if(sel == 2) {
					System.out.println("기존의 내용에 다이어리 내용을 덧붙입니다.");
					System.out.print("다이어리 내용 입력 : ");
					
					sc.nextLine();
					String comment = sc.nextLine();
					String[] parts = comment.split("/");
					StringBuilder result = new StringBuilder();
					
					for (String part : parts) {
						result.append(part.trim()).append("\n"); // 각 부분에 \n 추가
					}
					
					int rlt = diaryFM.append(title, result.toString());
					if(rlt > 0) System.out.println("다이어리 내용 수정이 완료되었습니다.");
					else System.out.println("다이어리 내용 수정이 실패되었습니다.");
				}
			}
		}else if(menu == 5){
			System.out.println("제목을 입력하면 해당되는 다이어리를 삭제합니다.");
			System.out.print("다이어리 제목 입력 : ");
			sc.nextLine();
			String title = sc.nextLine();
			int rlt = diaryFM.delete(title);
			if(rlt > 0) System.out.println("해당 다이어리가 삭제되었습니다.");
			else System.out.println("해당 다이어리 삭제가 실패되었습니다.");
		}
		else if(menu == 0) {
			System.out.println("다이어리 프로그램을 종료합니다.");
			sc.close();
			return;
		}
		showMenu();
	}
}
