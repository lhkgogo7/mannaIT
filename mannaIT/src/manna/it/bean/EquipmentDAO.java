package manna.it.bean;


import java.sql.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.*;
import java.util.Vector;

import javax.servlet.http.*;

import manna.it.db.DBManager;
/*
import  manna.it.util.Utility;
import  manna.it.util.ServerInfo;
import  manna.it.bean.Request_Entity; //자료형
*/
public class EquipmentDAO {

	private DBManager mgr;
	
	private String Error_msg = ""; // 에러 메세지 저장
	private String msg = ""; // 특정 값 보기 위함

	private String page_navi = ""; // 페이지 네비게이션(html)
	private int max = 0; // 게시물 총 개수
	private int total = 0; // 총 게시물
	private int total_page = 0; // 총 페이지
	private int current_page = 0; // 현재 페이지

	private String today = "";

	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	private boolean iscon;
	// 생성자
	public EquipmentDAO() {

		try {
			this.mgr = new DBManager();
			// //커넥션풀 연결구문
			con = mgr.getConnection("oracle");
		} catch (Exception e) {
			System.out.println("DB연결실패:" + e);
			// 커넥션 풀이 죽었을 경우 자주 발생
			return;
		}
		//this.util = new Utility();
	}

	public void connect(){
		System.out.println("connect시작");
		try {
			mgr = new DBManager();
			// //커넥션풀 연결구문
			con = mgr.getConnection("oracle");
		} catch (Exception e) {
			System.out.println("DB연결실패:" + e);
			// 커넥션 풀이 죽었을 경우 자주 발생
			return;
		}
	}
	// 협조전 리스트 출력(
	public Vector<EquipmentBean> getEquipmentList(int ca_code) {

		String sql = "";
		int k = 1;
		// 결과를 저정하는 벡터 변수
		Vector<EquipmentBean> list = new Vector<EquipmentBean>();
		try {
			iscon = con.isClosed();
			if(iscon){
				connect();
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			

			sql = "SELECT EQ_CODE, EQ_NAME, EQ_MANUFACTURER, EC.EQ_CA_NAME,EQ_CA_CODE, TO_CHAR(EQ_DATE, 'yyyy-mm-dd')"
					+ " FROM EQUIPMENT EQ"
					+ " INNER JOIN EQ_CATEGORY EC"
					+ " ON EC.EQ_CA_CODE = EQ.EQ_EQCACODE"
					+ " WHERE EQ_CA_CODE="+ca_code;
			msg = sql;
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			// 해당 값을 얻는다.
			while (rs.next()) {
				k = 1;
				EquipmentBean data = new EquipmentBean();

				data.setEq_code(rs.getInt(k++));
				data.setEq_name(rs.getString(k++));
				data.setManufacturer(rs.getString(k++));
				data.setEq_ca_name(rs.getString(k++));
				data.setEq_ca_code(rs.getInt(k++));
				
				data.setEq_date_s(rs.getString(k++));
		
				list.addElement(data);
			}

			if (rs != null)
				rs.close();
			return list;

		} catch (Exception e) {
			Error_msg = "<br>sql : " + sql + "<br>Error " + e.toString();
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception e) {
				}
			if (con != null)
				try {
					con.close();
				} catch (Exception e) {
					
				}
		}

		return null;
	}
	public Vector<EquipmentBean> getEquipmentList() {

		String sql = "";
		int k = 1;
		// 결과를 저정하는 벡터 변수
		Vector<EquipmentBean> list = new Vector<EquipmentBean>();
		try {
			iscon = con.isClosed();
			if(iscon){
				connect();
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			

			sql = "SELECT EQ_CODE, EQ_NAME, EQ_MANUFACTURER, EC.EQ_CA_NAME, EQ_CA_CODE, TO_CHAR(EQ_DATE, 'yyyy-mm-dd')"
					+ " FROM EQUIPMENT EQ"
					+ " INNER JOIN EQ_CATEGORY EC"
					+ " ON EC.EQ_CA_CODE = EQ.EQ_EQCACODE";
			msg = sql;
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			// 해당 값을 얻는다.
			while (rs.next()) {
				k = 1;
				EquipmentBean data = new EquipmentBean();

				data.setEq_code(rs.getInt(k++));
				data.setEq_name(rs.getString(k++));
				data.setManufacturer(rs.getString(k++));
				data.setEq_ca_name(rs.getString(k++));
				data.setEq_ca_code(rs.getInt(k++));
				data.setEq_date_s(rs.getString(k++));
			

				list.addElement(data);
			}

			if (rs != null)
				rs.close();
			return list;

		} catch (Exception e) {
			Error_msg = "<br>sql : " + sql + "<br>Error " + e.toString();
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception e) {
				}
			if (con != null)
				try {
					con.close();
				} catch (Exception e) {
					
				}
		}

		return null;
	}
	public Date stringToDateConversion(String stringDate) throws ParseException{

	    DateFormat formatter;
	    Date date;
	    formatter = new SimpleDateFormat("yyyyMMdd");
	    date = (Date) formatter.parse(stringDate);
	    return date;
	    
	    
	}
	
	


	//Equipment Category 목록 불러오는 코드
	public Vector<EquipmentBean> getEquipmentCategoryList() {
		try {
			iscon = con.isClosed();
			if(iscon){
				connect();
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String sql = "";
		int k = 1;
		int max_num = 0; // 총수량
		// 결과를 저정하는 벡터 변수
		Vector<EquipmentBean> list = new Vector<EquipmentBean>();

		try {
			

			sql = "SELECT EQ_CA_NAME, EQ_CA_CODE FROM EQ_CATEGORY";
			msg = sql;
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			// 해당 값을 얻는다.
			while (rs.next()) {
				k = 1;
				EquipmentBean data = new EquipmentBean();
 
				data.setEq_ca_name(rs.getString(k++));
				data.setEq_ca_code(rs.getInt(k++));
			
				list.addElement(data);
			}

			if (rs != null)
				rs.close();
			return list;

		} catch (Exception e) {
			Error_msg = "<br>sql : " + sql + "<br>Error " + e.toString();
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception e) {
				}
			if (con != null)
				try {
					con.close();
				} catch (Exception e) {
				}
		}

		return null;
	}
	// 장비 입력 proc 쿼리
	public boolean insertEquipment(EquipmentBean eb) {
		try {
			iscon = con.isClosed();
			if(iscon){
				connect();
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			String sql = "";
			int res = 0;

			try {
				System.out.println("진입");
				sql = "INSERT INTO EQUIPMENT (EQ_CODE, EQ_NAME, EQ_MANUFACTURER, EQ_EQCACODE, EQ_DATE)"
						+ " VALUES (TO_CHAR(SYSDATE, 'YYYY')||lpad(EQCODE_SEQ.NEXTVAL,4,0), ?, ?, ?, ?)";
				
				System.out.println("sql1"+sql);
				
				
				pstmt = con.prepareStatement(sql);
				System.out.println("sql1");
				pstmt.setString(1, eb.getEq_name());
				System.out.println("name"+eb.getEq_name());
				pstmt.setString(2, eb.getManufacturer());
				System.out.println("man"+eb.getManufacturer());
				pstmt.setInt(3, eb.getEq_ca_code());
				System.out.println("ca_code"+eb.getEq_ca_code());
				java.util.Date uDate = eb.getEq_date();
				java.sql.Date sDate  = new java.sql.Date(uDate.getTime());
				pstmt.setDate(4,sDate);
				
				System.out.println("date"+sDate);
				
				System.out.println("sql1"+sql);

				res = pstmt.executeUpdate();
				
				System.out.println("res : "+res);
				if(res == 0){ return false;}

				msg = sql;
				
				return true;
			} catch (Exception e) {
				Error_msg = "<br>sql : " + sql + "<br>Error " + e.toString();
			} finally {
				if (pstmt != null)
					try {
						pstmt.close();
					} catch (Exception e) {
					}
				if (con != null)
					try {
						con.close();
					} catch (Exception e) {
					}
			}
			return false;
	}

	public boolean isEquiment(int num) {
			
			return false;
	}

		
			
	// 장비 삭제
	public boolean equipmentDelete(int code) {
		try {
			iscon = con.isClosed();
			if(iscon){
				connect();
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			String sql = "";
			int res = 0;

			try {
				System.out.println("진입");
				sql = "DELETE FROM EQUIPMENT WHERE EQ_CODE = ?";
				System.out.println("sql1"+sql);
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, code);
				res = pstmt.executeUpdate();
				System.out.println("res"+res);
				if (res != 0) return true;
			} catch (Exception e) {
				Error_msg = "<br>sql : " + sql + "<br>Error " + e.toString();
			} finally {
				if (pstmt != null)
					try {
						pstmt.close();
					} catch (Exception e) {
					}
				if (con != null)
					try {
						con.close();
					} catch (Exception e) {
					}
			}
			// TODO Auto-generated method stub
						return false;
		}

	//장비  view
	public EquipmentBean getEquipmentView(int code) {
		try {
			iscon = con.isClosed();
			if(iscon){
				connect();
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String sql = "";

		try {

			sql = "SELECT EQ_CODE, EQ_NAME, EQ_MANUFACTURER, EC.EQ_CA_NAME, EQ_DATE, EC.EQ_CA_CODE "
					+ " FROM EQUIPMENT EQ "
					+ " INNER JOIN EQ_CATEGORY EC ON EC.EQ_CA_CODE = EQ.EQ_EQCACODE"
					+ " WHERE EQ_CODE= ?";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, code);
			rs = pstmt.executeQuery();

			int k = 1;
			if (rs.next()) {
				EquipmentBean data = new EquipmentBean();
				data.setEq_code(rs.getInt(k++));
				data.setEq_name(rs.getString(k++));
				data.setManufacturer(rs.getString(k++));
				data.setEq_ca_name(rs.getString(k++));
				Date d = rs.getDate(k++);
				data.setEq_ca_code(rs.getInt(k++));
				data.setEq_date(d);
				System.out.println(d);
				
				return data;
			}
			if (rs != null)
				rs.close();
			msg = sql;
		} catch (Exception e) {
			Error_msg = "<br>sql : " + sql + "<br>Error " + e.toString();
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception e) {
				}
			if (con != null)
				try {
					con.close();
				} catch (Exception e) {
				}
		}
		return null;
	}

	//장비 수정
	public boolean modifyEquipment(EquipmentBean eb) {
		try {
			iscon = con.isClosed();
			if(iscon){
				connect();
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String sql = "";
		int res = 0;

		try {
			System.out.println("진입");
			sql = " UPDATE EQUIPMENT"
					+ " set EQ_NAME = ? ,"
					+ " EQ_MANUFACTURER = ?,"
					+ " EQ_EQCACODE = ?,"
					+ " EQ_DATE = ?"
					+ " WHERE EQ_CODE=?";
			
			pstmt = con.prepareStatement(sql);	
			pstmt.setString(1, eb.getEq_name());
			pstmt.setString(2, eb.getManufacturer());
			pstmt.setInt(3, eb.getEq_ca_code());
			java.util.Date uDate= eb.getEq_date();
			java.sql.Date sDate = new java.sql.Date(uDate.getTime()); //utilDate->sqlDate
			pstmt.setDate(4, sDate);
			pstmt.setInt(5, eb.getEq_code());

			res = pstmt.executeUpdate();
			if (res != 0) {
					return true;
			}
			
			msg = sql;
			return false;
		} catch (Exception e) {
			Error_msg = "<br>sql : " + sql + "<br>Error " + e.toString();
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception e) {
				}
			if (con != null)
				try {
					con.close();
				} catch (Exception e) {
				}
		}
		return false;
	}

}// end_class	