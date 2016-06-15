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
	
	//장비 리스트 카테고리검색후 갯수 
	public int getEquipmentTotal(int eq_ca_code){
		String sql  ="SELECT COUNT(*) FROM EQUIPMENT EQ "
				+ " INNER JOIN EQ_CATEGORY EC"
				+ " ON EC.EQ_CA_CODE = EQ.EQ_EQCACODE WHERE EQ_CA_CODE="+eq_ca_code;
		try{
			pstmt=con.prepareStatement(sql);
	        rs=pstmt.executeQuery();
	        rs.next();
			return rs.getInt(1);
		} catch (Exception e) {
			Error_msg = "<br>sql : " + sql + "<br>Error " + e.toString();
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception e) {
					 System.out.println("ListCount 에러:"+e);
				}
			if (con != null)
				try {
					con.close();
				} catch (Exception e) {
					 System.out.println("ListCount con 에러:"+e);
				}
		}
		return 0;
	}
	
	//장비 리스트 총 갯수 
	public int getEquipmentTotal(){
		String sql  ="SELECT COUNT(*) FROM EQUIPMENT";
		try{
			pstmt=con.prepareStatement(sql);
	        rs=pstmt.executeQuery();
	        rs.next();
			return rs.getInt(1);
		} catch (Exception e) {
			Error_msg = "<br>sql : " + sql + "<br>Error " + e.toString();
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception e) {
					 System.out.println("ListCount 에러:"+e);
				}
			if (con != null)
				try {
					con.close();
				} catch (Exception e) {
					 System.out.println("ListCount con 에러:"+e);
				}
		}
		return 0;
	}
	
	
	
	
	
	//장비 리스트 출력 카테고리 검색 결과 출력 
	public Vector<EquipmentBean> getEquipmentList(int ca_code, int cur_page, int limit) {
		System.out.println("dao:  getEquipmentList(int ca_code, int page,int limit)"+ca_code+"::"+cur_page+":::"+limit);
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
				int start_row = (cur_page-1)*limit+1;
				int end_row = cur_page*limit;

	/*	      sql = "SELECT * FROM (SELECT ROWNUM RNUM, EQ_CODE, EQ_NAME, EQ_MANUFACTURER, EC.EQ_CA_NAME,EQ_CA_CODE, TO_CHAR(EQ_DATE, 'yyyy-mm-dd')"
					+ " FROM EQUIPMENT EQ"
					+ " INNER JOIN EQ_CATEGORY EC"
					+ " ON EC.EQ_CA_CODE = EQ.EQ_EQCACODE"
					+ " WHERE EQ_CA_CODE="+ca_code +" ORDER BY EQ_CODE ASC)"
					+ " WHERE RNUM >="+start_row+" AND RNUM<="+end_row+" ORDER BY EQ_CODE DESC";*/
		      
		      sql = "SELECT * FROM (SELECT ROWNUM RNUM,EQ_CODE, EQ_NAME, EQ_MANUFACTURER, EQ_CA_NAME,EQ_CA_CODE,E_DATE , EQ_PICTURE, EQ_USER "
		      		+ " FROM(SELECT EQ_CODE, EQ_NAME, EQ_MANUFACTURER, EC.EQ_CA_NAME EQ_CA_NAME,EQ_CA_CODE, TO_CHAR(EQ_DATE, 'YYYY-MM-DD') E_DATE , EQ_PICTURE, EQ_USER "
		    		+ " FROM EQUIPMENT EQ"
		    	    + " INNER JOIN EQ_CATEGORY EC"
		    		+ " ON EC.EQ_CA_CODE = EQ.EQ_EQCACODE"
		    		+ " WHERE EQ_CA_CODE="+ca_code +"ORDER BY EQ_CODE DESC))"
		    		+ " WHERE RNUM >="+start_row+" AND RNUM<="+end_row +" ORDER BY RNUM ASC";
			msg = sql;
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			// 해당 값을 얻는다.
			while (rs.next()) {
				k = 1;
				EquipmentBean data = new EquipmentBean();
				
				data.setEq_rnum(rs.getInt(k++));
				data.setEq_code(rs.getInt(k++));
				data.setEq_name(rs.getString(k++));
				data.setManufacturer(rs.getString(k++));
				data.setEq_ca_name(rs.getString(k++));
				data.setEq_ca_code(rs.getInt(k++));
				data.setEq_date_s(rs.getString(k++));
				data.setEq_user(rs.getString(k++));
		
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
	
	//장비 전체리스트 출력 
	public Vector<EquipmentBean> getEquipmentList(int cur_page,int limit){
		System.out.println("dao : getEquipmentList(int cur_page,int limit)");
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
			
			int start_row = (cur_page-1)*limit+1;
		      //page 가
		        //  1일 경우 : 1번   (1-1)*10 +1
		        //  2일 경우 : 11번(2-1)*10 +1
		        //  3일 경우 : 21번
		      
		      //int end_row=start_row +limit-1;
		      int end_row = cur_page*limit;
		      System.out.println("getEquipmentList(int cur_page,int limit) endrow:"+end_row+"start_row:"+start_row);

/*			sql = "SELECT * FROM (SELECT ROWNUM RNUM, EQ_CODE, EQ_NAME, EQ_MANUFACTURER, EC.EQ_CA_NAME, EQ_CA_CODE, TO_CHAR(EQ_DATE, 'YYYY-MM-DD'), EQ_PICTURE"
					+ " FROM EQUIPMENT EQ"
					+ " INNER JOIN EQ_CATEGORY EC"
					+ " ON EC.EQ_CA_CODE = EQ.EQ_EQCACODE ORDER BY EQ_CODE )"
					+ " WHERE RNUM >="+start_row+" AND RNUM<="+end_row+" ORDER BY EQ_CODE DESC";*/
		      sql =" SELECT * FROM (SELECT ROWNUM RNUM,EQ_CODE, EQ_NAME, EQ_MANUFACTURER, EQ_CA_NAME,EQ_CA_CODE,E_DATE , EQ_PICTURE, EQ_USER "
		      		+ "FROM(SELECT EQ_CODE, EQ_NAME, EQ_MANUFACTURER, EC.EQ_CA_NAME EQ_CA_NAME,EQ_CA_CODE, TO_CHAR(EQ_DATE, 'YYYY-MM-DD') E_DATE , EQ_PICTURE, EQ_USER" 
		      		+ " FROM EQUIPMENT EQ"
		      		+ " INNER JOIN EQ_CATEGORY EC"
		      		+ " ON EC.EQ_CA_CODE = EQ.EQ_EQCACODE"
		      		+ " ORDER BY EQ_CODE DESC))"
		      		+ " WHERE RNUM >="+start_row+" AND RNUM<="+end_row +"ORDER BY RNUM ASC";
			msg = sql;
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			// 해당 값을 얻는다.
			int rownum=0;
			while (rs.next()) {
				k = 1;
				EquipmentBean data = new EquipmentBean();
				
				data.setEq_rnum(rs.getInt(k++));
				data.setEq_code(rs.getInt(k++));
				data.setEq_name(rs.getString(k++));
				data.setManufacturer(rs.getString(k++));
				data.setEq_ca_name(rs.getString(k++));
				data.setEq_ca_code(rs.getInt(k++));
				data.setEq_date_s(rs.getString(k++));
				data.setEq_picture(rs.getString(k++));
				data.setEq_user(rs.getString(k++));

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
			

			sql = "SELECT EQ_CA_NAME, EQ_CA_CODE FROM EQ_CATEGORY  ORDER BY EQ_CA_CODE ASC";
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
					sql = "INSERT INTO EQUIPMENT (EQ_CODE, EQ_NAME, EQ_MANUFACTURER, EQ_EQCACODE, EQ_DATE, EQ_PICTURE)"
							+ " VALUES (EQCODE_NEXT_SEQ, ?, ?, ?, ?,?)";
					
					System.out.println("sql1"+sql);
					
					
					pstmt = con.prepareStatement(sql);
					System.out.println("sql1");
					pstmt.setString(1, eb.getEq_name());
					System.out.println("name"+eb.getEq_name());
					pstmt.setString(2, eb.getManufacturer());
					System.out.println("man"+eb.getManufacturer());
					pstmt.setInt(3, eb.getEq_ca_code());
					System.out.println("ca_code"+eb.getEq_ca_code());
					
					pstmt.setString(4,eb.getEq_date_s());
					pstmt.setString(5, eb.getEq_picture());
					System.out.println("date"+eb.getEq_date_s());
					System.out.println(" eb.getEq_picture()"+ eb.getEq_picture());
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

	
	

	// 장비 입력 proc 쿼리
	public boolean updateEquipmentReturn(EquipmentBean eb) {
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
				System.out.println("updateEquipmentReturn 진입");
				
					sql = "UPDATE RENTAL SET RENT_RSCODE=802 , RENT_EDATE = TO_DATE(?, 'YYYY-MM-DD')"
							+ " WHERE RENT_EQCODE =? AND RENT_RSCODE=?";
				
				System.out.println("sql1"+sql);
				
				
				pstmt = con.prepareStatement(sql);
				System.out.println("sql1");
				
				
				
				pstmt.setString(1,eb.getRt_edate_s());
				pstmt.setInt(2, eb.getEq_code());
				pstmt.setInt(3, eb.getRs_code());
				
				System.out.println("date"+eb.getRt_edate_s());
				System.out.println("eq_code"+eb.getEq_code());
				System.out.println("eb.getRs_code()"+eb.getRs_code());
				
				
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

			sql = "SELECT EQ_CODE, EQ_NAME, EQ_MANUFACTURER, EC.EQ_CA_NAME, EQ_DATE, EC.EQ_CA_CODE, EQ.EQ_PICTURE "
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
				data.setEq_picture(rs.getString(k++));
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

	//장비 view에서 수정
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
					+ " EQ_DATE = ?,"
					+ " EQ_PICTURE =?"
					+ " WHERE EQ_CODE=?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, eb.getEq_name());
			System.out.println(" eb.getEq_name()::"+ eb.getEq_name());
			pstmt.setString(2, eb.getManufacturer());
			System.out.println("  eb.getManufacturer()::"+  eb.getManufacturer());
			pstmt.setInt(3, eb.getEq_ca_code());
			System.out.println(" eb.getEq_ca_code()"+ eb.getEq_ca_code());
			java.util.Date uDate= eb.getEq_date();
			java.sql.Date sDate = new java.sql.Date(uDate.getTime()); //utilDate->sqlDate
			pstmt.setDate(4, sDate);
			System.out.println(" sDate::"+ sDate);
			pstmt.setString(5, eb.getEq_picture());
			System.out.println(" eb.getEq_picture()"+ eb.getEq_picture());
			pstmt.setInt(6, eb.getEq_code());
			System.out.println("Eq_code():::"+ eb.getEq_code());
			
			res = pstmt.executeUpdate();
			System.out.println("res::"+res);
			if (res != 0) {
					return true;
			}
			
			msg = sql;
			return false;
		} catch (Exception e) {
			Error_msg = "<br>sql : " + sql + "<br>Error " + e.toString();
			System.out.println("e::"+e);
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

	
	// 수정버튼눌러서 ajax통해 바로 수정

	public boolean modifyFrontEquipment(EquipmentBean eb) {
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
					+ " EQ_DATE = ?,"
					+ " WHERE EQ_CODE=?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, eb.getEq_name());
			System.out.println(" eb.getEq_name()::"+ eb.getEq_name());
			pstmt.setString(2, eb.getManufacturer());
			System.out.println("  eb.getManufacturer()::"+  eb.getManufacturer());
			pstmt.setInt(3, eb.getEq_ca_code());
			System.out.println(" eb.getEq_ca_code()"+ eb.getEq_ca_code());
			java.util.Date uDate= eb.getEq_date();
			java.sql.Date sDate = new java.sql.Date(uDate.getTime()); //utilDate->sqlDate
			pstmt.setDate(4, sDate);
			System.out.println(" sDate::"+ sDate);
			pstmt.setInt(5, eb.getEq_code());
			System.out.println("Eq_code():::"+ eb.getEq_code());
			
			res = pstmt.executeUpdate();
			System.out.println("res::"+res);
			if (res != 0) {
					return true;
			}
			
			msg = sql;
			return false;
		} catch (Exception e) {
			Error_msg = "<br>sql : " + sql + "<br>Error " + e.toString();
			System.out.println("e::"+e);
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

	//equipment list 에서 사용자 수정
	public boolean updateEquipmentUser(int eq_code, String m_code) {
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
				System.out.println("updateEquipmentUser 진입");
				
					sql = "update equipment set eq_user = ? where eq_code = ?";
				
				System.out.println("sql1"+sql);
				
				
				pstmt = con.prepareStatement(sql);
				
				
				
				pstmt.setString(1,m_code);
				pstmt.setInt(2, eq_code);
				
				
				res = pstmt.executeUpdate();
				
				System.out.println("updateEquipmentUser res : "+res);
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

	//장비 사용자 가져오기 
		public String getUserName(String m_code){
				String sql  ="SELECT M_NAME FROM MEMBER WHERE M_CODE = ?";
				try{
					pstmt=con.prepareStatement(sql);
					System.out.println(m_code+"user name:::::: mname");
					pstmt.setString(1, m_code);
					
			        rs=pstmt.executeQuery();
			        
			        rs.next();
					return rs.getString(1);
				} catch (Exception e) {
					Error_msg = "<br>sql : " + sql + "<br>Error " + e.toString();
				} finally {
					if (pstmt != null)
						try {
							pstmt.close();
						} catch (Exception e) {
							 System.out.println("ListCount 에러:"+e);
						}
					if (con != null)
						try {
							con.close();
						} catch (Exception e) {
							 System.out.println("ListCount con 에러:"+e);
						}
				}
				return null;
			}
			
	///////////////////////////////////////rental ///////////////
	public Vector<EquipmentBean> getRentalList() {

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
			

			sql = "SELECT RENT_CODE, EQ_NAME, M_NAME,  TO_CHAR(RENT_SDATE, 'yyyy-mm-dd'), TO_CHAR(RENT_EDATE, 'yyyy-mm-dd')"
					+ " FROM RENTAL R"
					+ " INNER JOIN EQUIPMENT E"
					+ " ON R.RENT_EQCODE=E.EQ_CODE"
					+ " INNER JOIN MEMBER M "
					+ " ON M.M_CODE = R.RENT_MCODE";
			msg = sql;
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			// 해당 값을 얻는다.
			while (rs.next()) {
				k = 1;
				EquipmentBean data = new EquipmentBean();

				data.setRt_code(rs.getString(k++));
				data.setEq_name(rs.getString(k++));
				data.setRt_m_name(rs.getString(k++));
				//data.setRs_name(rs.getString(k++));
				data.setRt_sdate_s(rs.getString(k++));
				data.setRt_edate_s(rs.getString(k++));
				//data.setRt_purpose(rs.getString(k++));
				//data.setRt_duedate_s(rs.getString(k++));
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

	public Vector<EquipmentBean> getRentalList(int ca_code) {

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
			

			sql = "SELECT RENT_CODE, EQ_NAME, M_NAME,  TO_CHAR(RENT_SDATE, 'yyyy-mm-dd'), TO_CHAR(RENT_EDATE, 'yyyy-mm-dd')"
					+ " FROM RENTAL R"
					+ " INNER JOIN EQUIPMENT E"
					+ " ON R.RENT_EQCODE=E.EQ_CODE"
					+ " INNER JOIN MEMBER M "
					+ " ON M.M_CODE = R.RENT_MCODE"
					+ " WHERE E.EQ_EQCACODE=? ";
			msg = sql;
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, ca_code);
			rs = pstmt.executeQuery();
			// 해당 값을 얻는다.
			while (rs.next()) {
				k = 1;
				EquipmentBean data = new EquipmentBean();

				data.setRt_code(rs.getString(k++));
				data.setEq_name(rs.getString(k++));
				data.setRt_m_name(rs.getString(k++));
				//data.setRs_name(rs.getString(k++));
				data.setRt_sdate_s(rs.getString(k++));
				data.setRt_edate_s(rs.getString(k++));
				//data.setRt_purpose(rs.getString(k++));
				//data.setRt_duedate_s(rs.getString(k++));
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
		public boolean insertEquipmentRental(EquipmentBean eb) {
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
						sql = "INSERT INTO RENTAL (RENT_CODE, RENT_EQCODE, RENT_MCODE, RENT_SDATE) "
								+ " VALUES (RENT_GET_NEXT_SEQ, ?, ?, TO_DATE(?, 'YYYY-MM-DD'))";
					
					System.out.println("sql1"+sql);
					
					
					pstmt = con.prepareStatement(sql);
					System.out.println("sql1");
					
					pstmt.setInt(1, eb.getEq_code());
					System.out.println("eq_code"+eb.getEq_code());
					
					pstmt.setString(2, eb.getRt_m_code());
					System.out.println("rt_m_code"+eb.getRt_m_code());

					pstmt.setString(3,eb.getRt_sdate_s());
					System.out.println("getRt_sdate"+eb.getRt_sdate_s());



				
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
	
	public boolean equipmentRentalDelete(String code) {
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
				sql = "DELETE FROM RENTAL WHERE RENT_CODE = ?";
				System.out.println("RentalDelete sql1"+sql);
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, code);
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


}// end_class	