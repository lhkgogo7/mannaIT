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
	
	
}
