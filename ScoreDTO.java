package c1001;

public class ScoreDTO {
	private int sno, score, win_count, draw_count, lose_count;
	private String uid;
	
    public int getSno() {
        return sno;
    }
    public void setSno(int sno) {
        this.sno = sno;
    }
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public String getUid() {
        return uid;
    }
    public void setUid(String uid) {
        this.uid = uid;
    }
    public int getWin_count() {
        return win_count;
    }
    public void setWin_count(int win_count) {
        this.win_count = win_count;
    }
    public int getDraw_count() {
        return draw_count;
    }
    public void setDraw_count(int draw_count) {
        this.draw_count = draw_count;
    }
    public int getLose_count() {
        return lose_count;
    }
    public void setLose_count(int lose_count) {
        this.lose_count = lose_count;
    }

    public String toString() {
        String record = uid + "님의 성적" + "\n"
                + "이긴 횟수 : " + win_count + "\n"
                + "비긴 횟수 : " + draw_count + "\n"
                + "진 횟수 : " + lose_count + "\n"
                + "점수 : " + score;
        return record;
    }
}
