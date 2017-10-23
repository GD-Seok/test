package com.board.domain;

import java.sql.Date;
import org.springframework.web.multipart.MultipartFile;
//���� ���ε� -> MultipartFile Ŭ���� ���� �̿� -> ���ε� ���� �̸�

// DTO
public class BoardCommand {
	
	private int seq; //�Խù� ��ȣ
	private String writer,title,content,pwd;
	private int hit; //��ȸ��
	private Date regdate;//�ۼ���¥
	private MultipartFile upload;//������ ���ε��ϱ� ���� �ʿ��� upload ��ü�� MultipartFile
	private String filename;//���ε��� ���� �̸�
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public MultipartFile getUpload() {
		return upload;
	}
	public void setUpload(MultipartFile upload) {
		this.upload = upload;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "BoardCommand[seq="+seq+",writer="+writer+",title="
				   +title+",content="+content+",pwd="+pwd+",hit="
				   +hit+",regdate="+regdate+",upload="+upload
				   +",filename="+filename+"]";
	}
	
}
