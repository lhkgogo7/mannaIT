package manna.it.bean;

import java.util.Date;


public class EquipmentBean {
	private int eq_code; // 장비코드
	private String eq_name; //장비명
	private String manufacturer; //제조사
	private int eq_ca_code; // 장비 분류코드
	private String eq_ca_name; //분류명
	private Date eq_date;	// 장비구입일 
	private String eq_date_s;	// 장비구입일  문자타입
	private String eq_picture;//장비 사진
	
	private String rt_code; // 대여 코드 
	private String rt_m_code;//대여자 코드
	private String rt_m_name; //대여자 이름
	private int rs_code;//대여상태코드
	private String rs_name; //대여상태이름
	private Date rt_sdate; //대여시작일
	private Date rt_edate; //대여반납일
	private String rt_sdate_s; //대여시작일 문자타임
	private String rt_edate_s; //대여반납일 문자타입
	private String rt_purpose; //대여목적
	private Date rt_duedate; //대여 반납예정일
	private String rt_duedate_s; //대여 반납예정일 문자타입
	private int eq_rnum; //장비 게시물 번호 
	
	
	




	public int getEq_rnum() {
		return eq_rnum;
	}



	public void setEq_rnum(int eq_rnum) {
		this.eq_rnum = eq_rnum;
	}



	public static final String LINE_SEPARATOR = System.getProperty("line.separator");
	
	
	
	public int getEq_code() {
		return eq_code;
	}



	public void setEq_code(int eq_code) {
		this.eq_code = eq_code;
	}



	public String getEq_name() {
		return eq_name;
	}



	public void setEq_name(String eq_name) {
		this.eq_name = eq_name;
	}



	public String getManufacturer() {
		return manufacturer;
	}



	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}





	public int getEq_ca_code() {
		return eq_ca_code;
	}



	public void setEq_ca_code(int eq_ca_code) {
		this.eq_ca_code = eq_ca_code;
	}



	public EquipmentBean(){}



	public String getEq_ca_name() {
		return eq_ca_name;
	}



	public void setEq_ca_name(String eq_ca_name) {
		this.eq_ca_name = eq_ca_name;
	}



	public Date getEq_date() {
		return eq_date;
	}



	public void setEq_date(Date eq_date) {
		this.eq_date = eq_date;
	}



	public String getEq_date_s() {
		return eq_date_s;
	}



	public void setEq_date_s(String eq_date_s) {
		this.eq_date_s = eq_date_s;
	}



	public String getEq_picture() {
		return eq_picture;
	}



	public void setEq_picture(String eq_picture) {
		this.eq_picture = eq_picture;
	}



	public String getRt_code() {
		return rt_code;
	}



	public void setRt_code(String rt_code) {
		this.rt_code = rt_code;
	}



	public String getRt_m_code() {
		return rt_m_code;
	}



	public void setRt_m_code(String rt_m_code) {
		this.rt_m_code = rt_m_code;
	}



	public String getRt_m_name() {
		return rt_m_name;
	}



	public void setRt_m_name(String rt_m_name) {
		this.rt_m_name = rt_m_name;
	}



	public int getRs_code() {
		return rs_code;
	}



	public void setRs_code(int rs_code) {
		this.rs_code = rs_code;
	}



	public String getRs_name() {
		return rs_name;
	}



	public void setRs_name(String rs_name) {
		this.rs_name = rs_name;
	}



	public Date getRt_sdate() {
		return rt_sdate;
	}



	public void setRt_sdate(Date rt_sdate) {
		this.rt_sdate = rt_sdate;
	}



	public Date getRt_edate() {
		return rt_edate;
	}



	public void setRt_edate(Date rt_edate) {
		this.rt_edate = rt_edate;
	}



	public String getRt_purpose() {
		return rt_purpose;
	}



	public void setRt_purpose(String rt_purpose) {
		this.rt_purpose = rt_purpose;
	}



	public Date getRt_duedate() {
		return rt_duedate;
	}



	public void setRt_duedate(Date rt_duedate) {
		this.rt_duedate = rt_duedate;
	}



	public String getRt_sdate_s() {
		return rt_sdate_s;
	}



	public void setRt_sdate_s(String rt_sdate_s) {
		this.rt_sdate_s = rt_sdate_s;
	}



	public String getRt_edate_s() {
		return rt_edate_s;
	}



	public void setRt_edate_s(String rt_edate_s) {
		this.rt_edate_s = rt_edate_s;
	}



	public String getRt_duedate_s() {
		return rt_duedate_s;
	}



	public void setRt_duedate_s(String rt_duedate_s) {
		this.rt_duedate_s = rt_duedate_s;
	}
	
	
}
