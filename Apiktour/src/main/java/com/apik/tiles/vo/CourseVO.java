package com.apik.tiles.vo;

public class CourseVO {
	// COURSE ���̺�� ����
	private int cosnum; // �ڽ� id(1~999)
	private String play; // ����?(REST) �?(PLAY)
	private String cosname; // �ڽ���(�ѱ� 20����)
	private int lv; // ������&�Ƿε� Level (1~5)
	private String intro; // ������ �Ұ���( in ������)
	
	
	public int getCosnum() {
		return cosnum;
	}
	public void setCosnum(int cosnum) {
		this.cosnum = cosnum;
	}
	public String getPlay() {
		return play;
	}
	public void setPlay(String play) {
		this.play = play;
	}
	public String getCosname() {
		return cosname;
	}
	public void setCosname(String cosname) {
		this.cosname = cosname;
	}
	public int getLv() {
		return lv;
	}
	public void setLv(int lv) {
		this.lv = lv;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	
	
	
	
}

