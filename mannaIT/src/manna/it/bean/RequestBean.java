package manna.it.bean;

import java.util.Date;


public class RequestBean {
	private int ca_code;  //요청분휴코드
	private String ca_name; //요청분류명
	private int res_code; //처리결과코드
	private String res_name; //처리결과명
	private String req_code; //요청코드 
	private String req_subject; //요청제목
	private String req_content; //요청 내용
	private Date req_date; //요청 날자
	private String req_report; // 요청 처리내용
	private String m_name; //요청자명
	private String m_code; //요청자코드 
	private int count; //총갯수
	private String search; //검색어
	private String req_date_s;
	
	
	public static final String LINE_SEPARATOR = System.getProperty("line.separator");
	


	public int getCa_code() {
		return ca_code;
	}



	public void setCa_code(int ca_code) {
		this.ca_code = ca_code;
	}






	public String getCa_name() {
		return ca_name;
	}






	public void setCa_name(String ca_name) {
		this.ca_name = ca_name;
	}






	public int getRes_code() {
		return res_code;
	}






	public void setRes_code(int res_code) {
		this.res_code = res_code;
	}






	public String getRes_name() {
		return res_name;
	}






	public void setRes_name(String res_name) {
		this.res_name = res_name;
	}






	public String getReq_code() {
		return req_code;
	}






	public void setReq_code(String req_code) {
		this.req_code = req_code;
	}






	public String getReq_subject() {
		return req_subject;
	}






	public void setReq_subject(String req_subject) {
		this.req_subject = req_subject;
	}






	public String getReq_content() {
		return req_content;
	}






	public void setReq_content(String req_content) {
		this.req_content = req_content;
	}






	public Date getReq_date() {
		return req_date;
	}






	public void setReq_date(Date req_date) {
		this.req_date = req_date;
	}






	public String getReq_report() {
		return req_report;
	}






	public void setReq_report(String req_report) {
		this.req_report = req_report;
	}






	public String getM_name() {
		return m_name;
	}






	public void setM_name(String m_name) {
		this.m_name = m_name;
	}






	public String getM_code() {
		return m_code;
	}






	public void setM_code(String m_code) {
		this.m_code = m_code;
	}






	public RequestBean(){}



	public int getCount() {
		return count;
	}



	public void setCount(int count) {
		this.count = count;
	}



	public String getSearch() {
		return search;
	}



	public void setSearch(String search) {
		this.search = search;
	}



	public String getReq_date_s() {
		return req_date_s;
	}



	public void setReq_date_s(String req_date_s) {
		this.req_date_s = req_date_s;
	}
	
	
}
