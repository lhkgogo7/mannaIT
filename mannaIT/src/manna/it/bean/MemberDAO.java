package manna.it.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import manna.it.db.DBManager;

public class MemberDAO {
	

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
	public MemberDAO() {

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
	
	
	private void reconnect(){
		try {
			iscon = con.isClosed();
			if(iscon){
				connect();
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	public Vector<MemberBean> getMemberList() {
		System.out.println("getmemeberlist()");
		reconnect();
		String sql = "";

		int k;
		int max_num = 0; // 총수량
		// 결과를 저정하는 벡터 변수
		Vector<MemberBean> list = new Vector<MemberBean>();
					
					
		try {
	
				sql = "SELECT M_CODE, M_NAME, D.DEP1_NAME,D.DEP1_CODE, M_EXTENSION, M_PHONE, M_MOBILE, M_MAIL, P.POS_NAME,P.POS_CODE, M_ID, M_PWD"
						+ " FROM MEMBER M"
						+ " INNER JOIN DEPARTMENT1 D"
						+ " ON M.M_DEPCODE= D.DEP1_CODE"
						+ " INNER JOIN POSITION P"
						+ " ON M.M_POSCODE = P.POS_CODE"
						+ " ORDER BY M_NAME ASC";
				
			msg = sql;
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			// 해당 값을 얻는다.
			
			while (rs.next()) {
				k = 1;
				MemberBean data = new MemberBean();

				data.setM_code(rs.getString(k++));
				data.setM_name(rs.getString(k++));
				data.setM_depname(rs.getString(k++));
				data.setM_depcode(rs.getInt(k++));
				data.setM_extension(rs.getString(k++));
				data.setM_phone(rs.getString(k++));
				data.setM_mobile(rs.getString(k++));
				data.setM_mail(rs.getString(k++));
				data.setM_posname(rs.getString(k++));
				data.setM_poscode(rs.getInt(k++));
				data.setM_id(rs.getString(k++));
				data.setM_pwd(rs.getString(k++));

				list.addElement(data);
				System.out.println("list"+list);
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

	public Vector<MemberBean> getMemberList(int code) {
		
		reconnect();
		String sql = "";

		int k;
		int max_num = 0; // 총수량
		// 결과를 저정하는 벡터 변수
		Vector<MemberBean> list = new Vector<MemberBean>();
					
		try {
	
				sql = "SELECT M_CODE, M_NAME, D.DEP1_NAME, M_EXTENSION, M_PHONE, M_MOBILE, M_MAIL, P.POS_NAME, M_ID, M_PWD "
						+ " FROM MEMBER M "
						+ " INNER JOIN DEPARTMENT1 D "
						+ " ON M.M_DEPCODE= D.DEP1_CODE "
						+ " INNER JOIN POSITION P "
						+ " ON M.M_POSCODE = P.POS_CODE "
						+ " WHERE D.DEP1_CODE = "+ code 
						+ " OR P.POS_CODE =" +code
						+ " ORDER BY M_NAME ASC";

				
			msg = sql;
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			// 해당 값을 얻는다.
			
			while (rs.next()) {
				k = 1;
				MemberBean data = new MemberBean();

				data.setM_code(rs.getString(k++));
				data.setM_name(rs.getString(k++));
				data.setM_depname(rs.getString(k++));
				data.setM_extension(rs.getString(k++));
				data.setM_phone(rs.getString(k++));
				data.setM_mobile(rs.getString(k++));
				data.setM_mail(rs.getString(k++));
				data.setM_posname(rs.getString(k++));
				data.setM_id(rs.getString(k++));
				data.setM_pwd(rs.getString(k++));

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
	public Vector<MemberBean> getMemberList(int dep_code,int pos_code) {
		
		reconnect();
		String sql = "";

		int k;
		int max_num = 0; // 총수량
		// 결과를 저정하는 벡터 변수
		Vector<MemberBean> list = new Vector<MemberBean>();
					
		try {
	
				sql = "SELECT M_CODE, M_NAME, D.DEP1_NAME, M_EXTENSION, M_PHONE, M_MOBILE, M_MAIL, P.POS_NAME, M_ID, M_PWD "
						+ " FROM MEMBER M "
						+ " INNER JOIN DEPARTMENT1 D "
						+ " ON M.M_DEPCODE= D.DEP1_CODE "
						+ " INNER JOIN POSITION P "
						+ " ON M.M_POSCODE = P.POS_CODE "
						+ " WHERE D.DEP1_CODE = "+ dep_code 
						+ " AND P.POS_CODE =" +pos_code
						+ " ORDER BY M_NAME ASC";
				
			msg = sql;
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			// 해당 값을 얻는다.
			
			while (rs.next()) {
				k = 1;
				MemberBean data = new MemberBean();

				data.setM_code(rs.getString(k++));
				data.setM_name(rs.getString(k++));
				data.setM_depname(rs.getString(k++));
				data.setM_extension(rs.getString(k++));
				data.setM_phone(rs.getString(k++));
				data.setM_mobile(rs.getString(k++));
				data.setM_mail(rs.getString(k++));
				data.setM_posname(rs.getString(k++));
				data.setM_id(rs.getString(k++));
				data.setM_pwd(rs.getString(k++));

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
	public Vector<MemberBean> getdepartmentList() {
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
		Vector<MemberBean> list = new Vector<MemberBean>();

		try {
			

			sql = "SELECT DEP1_CODE, DEP1_NAME FROM DEPARTMENT1 ORDER BY DEP1_NAME ASC";
			msg = sql;
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				k = 1;
				MemberBean data = new MemberBean();
 
				data.setM_depcode(rs.getInt(k++));
				data.setM_depname(rs.getString(k++));
				list.addElement(data);
			}

			if (rs != null)
				rs.close();
			return list;

		} catch (Exception e) {
			Error_msg = "<br>sql : " + sql + "<br>Error " + e.toString();
			System.out.println("e getdepartmentResultList() : "+e);
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

	public Vector<MemberBean> getPositionList() {
		
		reconnect();
		String sql = "";

		int k = 1;

		// 결과를 저정하는 벡터 변수
		Vector<MemberBean> list = new Vector<MemberBean>();

		try {
			

			sql = "SELECT POS_CODE, POS_NAME FROM POSITION";
			msg = sql;
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				k = 1;
				MemberBean data = new MemberBean();
 
				data.setM_poscode(rs.getInt(k++));
				data.setM_posname(rs.getString(k++));
				list.addElement(data);
			}

			if (rs != null)
				rs.close();
			return list;

		} catch (Exception e) {
			Error_msg = "<br>sql : " + sql + "<br>Error " + e.toString();
			System.out.println("e getPositionList() : "+e);
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
	
	public boolean insertMember(MemberBean mb) {
		try {
			iscon = con.isClosed();
			if(iscon){
				connect();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		String sql = "";
		int res = 0;

		try {
			System.out.println("insertMember(MemberBean rb) 진입");
			sql = "INSERT INTO MEMBER(M_CODE, M_NAME, M_DEPCODE, M_EXTENSION, M_PHONE, M_MOBILE, M_MAIL,M_POSCODE, M_ID, M_PWD) "
					+ " VALUES('M'||LPAD(MCODE_SEQ.NEXTVAL,4,0),?,?,?,?,?,?,?,?,?)";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, mb.getM_name());
			pstmt.setInt(2, mb.getM_depcode());
			pstmt.setString(3, mb.getM_extension());
			pstmt.setString(4, mb.getM_phone());			
			pstmt.setString(5, mb.getM_mobile());			
			pstmt.setString(6, mb.getM_mail());			
			pstmt.setInt(7, mb.getM_poscode());			
			pstmt.setString(8, mb.getM_id());			
			pstmt.setString(9, mb.getM_pwd());		

			res = pstmt.executeUpdate();
			
			if(res == 0){ return false;}

			msg = sql;
			
			return true;
		} catch (Exception e) {
			System.out.println("insertMember e : "+e);
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

	public boolean memberDelete(String m_code) {
		String sql = "";
		int res = 0;

		try {
			System.out.println("memberDelete(String m_code) 진입"+m_code);
			sql = "DELETE FROM MEMBER WHERE M_CODE =?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, m_code);
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
	
	
	//요청 수정
		public boolean modifyMember(MemberBean mb) {
			String sql = "";
			int res = 0;

			try {
				System.out.println("진입");
				sql = " UPDATE MEMBER"
						+ " SET M_NAME=?, M_DEPCODE=?, M_EXTENSION=?, M_PHONE=?,"
						+ " M_MOBILE=?,M_MAIL=?,M_POSCODE=?,M_ID=?,M_PWD=?"
						+ " WHERE M_CODE=?";
				
				pstmt = con.prepareStatement(sql);	
				
				
				
				pstmt.setString(1, mb.getM_name());
				pstmt.setInt(2, mb.getM_depcode());
				pstmt.setString(3, mb.getM_extension());
				pstmt.setString(4, mb.getM_phone());
				pstmt.setString(5, mb.getM_mobile());
				pstmt.setString(6, mb.getM_mail());
				pstmt.setInt(7, mb.getM_poscode());
				pstmt.setString(8, mb.getM_id());
				pstmt.setString(9, mb.getM_pwd());
				pstmt.setString(10, mb.getM_code());

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
	
	
	
}
