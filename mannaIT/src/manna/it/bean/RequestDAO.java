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
public class RequestDAO {

	private DBManager mgr;
	
	private String Error_msg = ""; // 에러 메세지 저장
	private String msg = ""; // 특정 값 보기 위함

	private String page_navi = ""; // 페이지 네비게이션(html)
	private int max = 0; // 게시물 총 개수
	private int total = 0; // 총 게시물
	private int total_page = 0; // 총 페이지
	private int current_page = 0; // 현재 페이지

	private String today = "";
	
	private boolean iscon;

	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;

	// 생성자
	public RequestDAO() {

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

	// 요청 리스트 출력

	public Vector<RequestBean> getRequestList() {
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

		int k;
		int max_num = 0; // 총수량
		// 결과를 저정하는 벡터 변수
		Vector<RequestBean> list = new Vector<RequestBean>();
					
		try {
	
				sql = "SELECT REQ_CODE, RC.CA_NAME, M.M_NAME, R.REQ_SUBJECT,R.REQ_CONTENT ,TO_CHAR(R.REQ_DATE, 'yyyy-mm-dd'), R.REQ_REPORT, RR.RES_NAME "
						+ "FROM REQUEST R "
						+ "INNER JOIN REQ_CATEGORY RC "
						+ "ON RC.CA_CODE = R.REQ_CACODE "
						+ "INNER JOIN MEMBER M "
						+ "ON M.M_CODE = R.REQ_MCODE "
						+ "INNER JOIN REQ_RESULT RR "
						+ "ON RR.RES_CODE = R.REQ_RESCODE "
					
						+ "ORDER BY R.REQ_CODE DESC";
				
			msg = sql;
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			// 해당 값을 얻는다.
			
			while (rs.next()) {
				k = 1;
				RequestBean data = new RequestBean();

				data.setReq_code(rs.getString(k++));
				data.setCa_name(rs.getString(k++));
				data.setM_name(rs.getString(k++));
				data.setReq_subject(rs.getString(k++));
				data.setReq_content(rs.getString(k++));
				//Date d = rs.getDate(k++);
				data.setReq_date_s(rs.getString(k++));
				data.setReq_report(rs.getString(k++));
				data.setRes_name(rs.getString(k++));
				data.setCount(max_num);
				list.addElement(data);
			}

			if (rs != null)
				rs.close();
			return list;

		} catch (Exception e) {
			Error_msg = "<br>sql : " + sql + "<br>Error " + e.toString();
			System.out.println("list e:"+ e);
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
	
	public Vector<RequestBean> getRequestList(int res, String req) {
		try {
			iscon = con.isClosed();
			if(iscon){
				connect();
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("getRequestList(int res, String req) res:"+res+"req:"+req);
		String sql = "";

		int k;
		int max_num = 0; // 총수량
		// 결과를 저정하는 벡터 변수
		Vector<RequestBean> list = new Vector<RequestBean>();
					
		try {
	
				sql = "SELECT REQ_CODE, RC.CA_NAME, M.M_NAME, R.REQ_SUBJECT,R.REQ_CONTENT , TO_CHAR(R.REQ_DATE, 'yyyy-mm-dd'), R.REQ_REPORT, RR.RES_NAME "
						+ " FROM REQUEST R "
						+ " INNER JOIN REQ_CATEGORY RC "
						+ " ON RC.CA_CODE = R.REQ_CACODE "
						+ " INNER JOIN MEMBER M "
						+ " ON M.M_CODE = R.REQ_MCODE "
						+ " INNER JOIN REQ_RESULT RR "
						+ " ON RR.RES_CODE = R.REQ_RESCODE "
						+ " where R.REQ_RESCODE ="+res+" and R.REQ_CACODE ="+req
						+ " ORDER BY R.REQ_CODE DESC";
				
			msg = sql;
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			// 해당 값을 얻는다.
			
			while (rs.next()) {
				k = 1;
				RequestBean data = new RequestBean();

				data.setReq_code(rs.getString(k++));
				data.setCa_name(rs.getString(k++));
				data.setM_name(rs.getString(k++));
				data.setReq_subject(rs.getString(k++));
				data.setReq_content(rs.getString(k++));
				//Date d = rs.getDate(k++);
				data.setReq_date_s(rs.getString(k++));
				data.setReq_report(rs.getString(k++));
				data.setRes_name(rs.getString(k++));
				data.setCount(max_num);
				list.addElement(data);
			}

			if (rs != null)
				rs.close();
			return list;

		} catch (Exception e) {
			Error_msg = "<br>sql : " + sql + "<br>Error " + e.toString();
			System.out.println("list e:"+ e);
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
	
	public Vector<RequestBean> getRequestList(String req) {
		try {
			iscon = con.isClosed();
			if(iscon){
				connect();
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("getRequestList(String req) req:"+req);
		String sql = "";

		int k;
		int max_num = 0; // 총수량
		// 결과를 저정하는 벡터 변수
		Vector<RequestBean> list = new Vector<RequestBean>();
					
		try {
	
				sql = "SELECT REQ_CODE, RC.CA_NAME, M.M_NAME, R.REQ_SUBJECT,R.REQ_CONTENT , TO_CHAR(R.REQ_DATE, 'yyyy-mm-dd'), R.REQ_REPORT, RR.RES_NAME "
						+ " FROM REQUEST R "
						+ " INNER JOIN REQ_CATEGORY RC "
						+ " ON RC.CA_CODE = R.REQ_CACODE "
						+ " INNER JOIN MEMBER M "
						+ " ON M.M_CODE = R.REQ_MCODE "
						+ " INNER JOIN REQ_RESULT RR "
						+ " ON RR.RES_CODE = R.REQ_RESCODE "
						+ " where R.REQ_CACODE ="+req+""
						+ " ORDER BY R.REQ_CODE DESC";
				
			msg = sql;
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			// 해당 값을 얻는다.
			
			while (rs.next()) {
				k = 1;
				RequestBean data = new RequestBean();

				data.setReq_code(rs.getString(k++));
				data.setCa_name(rs.getString(k++));
				data.setM_name(rs.getString(k++));
				data.setReq_subject(rs.getString(k++));
				data.setReq_content(rs.getString(k++));
				//Date d = rs.getDate(k++);
				data.setReq_date_s(rs.getString(k++));
				data.setReq_report(rs.getString(k++));
				data.setRes_name(rs.getString(k++));
				data.setCount(max_num);
				list.addElement(data);
			}

			if (rs != null)
				rs.close();
			return list;

		} catch (Exception e) {
			Error_msg = "<br>sql : " + sql + "<br>Error " + e.toString();
			System.out.println("list e:"+ e);
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
	


	//Request Category 목록 불러오는 코드
	public Vector<RequestBean> getRequestCategoryList() {
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

		// 결과를 저정하는 벡터 변수
		Vector<RequestBean> list = new Vector<RequestBean>();

		try {
			

			sql = "SELECT CA_CODE,CA_NAME FROM REQ_CATEGORY";
			msg = sql;
			pstmt = con.prepareStatement(sql);
			System.out.println(sql);
			rs = pstmt.executeQuery();
			// 해당 값을 얻는다.
			while (rs.next()) {
				k = 1;
				RequestBean data = new RequestBean();
 
				data.setCa_code(rs.getInt(k++));
				data.setCa_name(rs.getString(k++));
			
				list.addElement(data);
			}

			if (rs != null)
				rs.close();
			return list;

		} catch (Exception e) {
			Error_msg = "<br>sql : " + sql + "<br>Error " + e.toString();
			System.out.println("getRequestCategoryList() e : "+e);
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
	
	
	//REQ_RESULT 목록 불러오는 코드
	public Vector<RequestBean> getRequestResultList() {
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

		// 결과를 저정하는 벡터 변수
		Vector<RequestBean> list = new Vector<RequestBean>();

		try {
			

			sql = "SELECT RES_CODE,RES_NAME FROM REQ_RESULT";
			msg = sql;
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				k = 1;
				RequestBean data = new RequestBean();
 
				data.setRes_code(rs.getInt(k++));
				data.setRes_name(rs.getString(k++));
				list.addElement(data);
			}

			if (rs != null)
				rs.close();
			return list;

		} catch (Exception e) {
			Error_msg = "<br>sql : " + sql + "<br>Error " + e.toString();
			System.out.println("e getRequestResultList() : "+e);
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
	// 요청 입력 proc 쿼리
	public boolean insertRequest(RequestBean rb) {
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
				System.out.println("insertRequest(RequestBean rb) 진입");
				sql = "INSERT INTO REQUEST (REQ_CODE, REQ_CACODE, REQ_MCODE, REQ_SUBJECT, REQ_CONTENT, REQ_DATE, REQ_REPORT, REQ_RESCODE) "
						+ " VALUES (to_char(sysdate,'yyyy')||LPAD(REQCODE_SEQ.NEXTVAL,5,0), ?, ?, ?, ?,SYSDATE,?, '401')";
				
				pstmt = con.prepareStatement(sql);
				
				pstmt.setInt(1, rb.getCa_code());
				pstmt.setString(2, rb.getM_code());
				pstmt.setString(3, rb.getReq_subject());
				pstmt.setString(4, rb.getReq_content());			
				pstmt.setString(5,rb.getReq_report());		

				res = pstmt.executeUpdate();
				
				if(res == 0){ return false;}

				msg = sql;
				
				return true;
			} catch (Exception e) {
				System.out.println("e : "+e);
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
	//요청자 이름으로 코드 찾아줌
	public String findMember(String m_name) {
		if(pstmt==null||con==null){
			connect();
		}
			String m_code = "";
			String sql = "";
			
			try {
				System.out.println("진입");
				sql = "SELECT * FROM MEMBER WHERE M_NAME = ?";
				System.out.println("sql1"+sql);
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, m_name);
				rs = pstmt.executeQuery();
				rs.next();
				m_code = rs.getString(1);
				
				return m_code;
			} catch (Exception e) {
				System.out.println("e : "+e);
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
				return null;
	}
	//요청자 이름 리스트 
	public Vector<RequestBean> getMemberList() {
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

		// 결과를 저정하는 벡터 변수
		Vector<RequestBean> list = new Vector<RequestBean>();

		try {
			

			sql = "SELECT M_CODE, M_NAME FROM MEMBER";
			msg = sql;
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				k = 1;
				RequestBean data = new RequestBean();
 
				data.setM_code(rs.getString(k++));
				data.setM_name(rs.getString(k++));
				list.addElement(data);
			}

			if (rs != null)
				rs.close();
			return list;

		} catch (Exception e) {
			Error_msg = "<br>sql : " + sql + "<br>Error " + e.toString();
			System.out.println("e getRequestResultList() : "+e);
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
	// 요청 삭제
	public boolean requestDelete(int code) {
			String sql = "";
			int res = 0;

			try {
				System.out.println("requestDelete(int code) 진입");
				sql = "DELETE FROM REQUEST WHERE REQ_CODE = ?";
				System.out.println("sql1"+sql);
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, code);
				res = pstmt.executeUpdate();
				System.out.println("res"+res);
				if (res != 0) return true;
			} catch (Exception e) {
				System.out.println("e : "+e);
			} finally {
				if (pstmt != null)
					try {
						pstmt.close();
					} catch (Exception e) {
						System.out.println("e : "+e);
					}
				if (con != null)
					try {
						con.close();
					} catch (Exception e) {
						System.out.println("e : "+e);
					}
			}
			// TODO Auto-generated method stub
						return false;
		}

	//요청  view
	public RequestBean getRequestView(int code) {
		if(pstmt==null||con==null){
			connect();
		}

		String sql = "";

		try {

			sql = "SELECT REQ_CODE,RC.CA_CODE, RC.CA_NAME, M.M_NAME, R.REQ_SUBJECT,R.REQ_CONTENT , R.REQ_DATE, R.REQ_REPORT, RR.RES_NAME "
					+ "FROM REQUEST R "
					+ "INNER JOIN REQ_CATEGORY RC "
					+ "ON RC.CA_CODE = R.REQ_CACODE "
					+ "INNER JOIN MEMBER M "
					+ "ON M.M_CODE = R.REQ_MCODE "
					+ "INNER JOIN REQ_RESULT RR "
					+ "ON RR.RES_CODE = R.REQ_RESCODE "
					+ "WHERE REQ_CODE= ?";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, code);
			rs = pstmt.executeQuery();

			int k = 1;
			if (rs.next()) {
				RequestBean data = new RequestBean();
				data.setReq_code(rs.getString(k++));
				data.setCa_code(rs.getInt(k++));
				data.setCa_name(rs.getString(k++));
				data.setM_name(rs.getString(k++));
				data.setReq_subject(rs.getString(k++));
				data.setReq_content(rs.getString(k++));
				Date d = rs.getDate(k++);
				data.setReq_date(d);
				data.setReq_report(rs.getString(k++));
				data.setRes_name(rs.getString(k++));
				System.out.println(d);
				
				return data;
			}
			if (rs != null)
				rs.close();
			msg = sql;
		} catch (Exception e) {
			Error_msg = "<br>sql : " + sql + "<br>Error " + e.toString();
			System.out.println("e : "+e);
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

	//요청 수정
	public boolean modifyRequest(RequestBean rb) {
		String sql = "";
		int res = 0;

		try {
			System.out.println("진입");
			sql = " UPDATE REQUEST "
					+ " SET  REQ_CACODE=?, "
					+ " REQ_SUBJECT=?, "
					+ " REQ_CONTENT=?,"
					+ " REQ_REPORT=?"
					+ " WHERE REQ_CODE=?";
			
			pstmt = con.prepareStatement(sql);	
			pstmt.setInt(1, rb.getCa_code());
			pstmt.setString(2, rb.getReq_subject());
			pstmt.setString(3, rb.getReq_content());
			pstmt.setString(4, rb.getReq_report());
			pstmt.setString(5, rb.getReq_code());

			res = pstmt.executeUpdate();
			if (res != 0) {
					return true;
			}
			
			msg = sql;
			return false;
		} catch (Exception e) {
			Error_msg = "<br>sql : " + sql + "<br>Error " + e.toString();
			System.out.println("e : "+e);
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
	//Result변경
	public boolean updateResult(RequestBean rb) {
		String sql = "";
		int res = 0;

		try {
			System.out.println("updateResult 진입");
			sql = "UPDATE REQUEST SET REQ_RESCODE=? WHERE REQ_CODE=?";
			
			pstmt = con.prepareStatement(sql);	
			pstmt.setInt(1, rb.getRes_code());
			pstmt.setString(2, rb.getReq_code());
			
			System.out.println("rb.getRes_code()"+rb.getRes_code());
			System.out.println("rb.getReq_code()"+rb.getReq_code());
			
			res = pstmt.executeUpdate();
			if (res != 0) {
					return true;
			}
			
			msg = sql;
			return false;
		} catch (Exception e) {
			Error_msg = "<br>sql : " + sql + "<br>Error " + e.toString();
			System.out.println("e : "+e);
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
	
	//date 형식 
	public Date stringToDateConversion(String stringDate) throws ParseException{

	    DateFormat formatter;
	    Date date;
	    formatter = new SimpleDateFormat("yyyyMMdd");
	    date = (Date) formatter.parse(stringDate);
	    return date;
	    
	    
	}
	

	
	
}// end_class	