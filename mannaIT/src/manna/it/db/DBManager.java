package manna.it.db;

import java.sql.*;

import javax.sql.*;
import javax.naming.*;

public class DBManager{
  public DBManager(){}
    public Connection getConnection(String div)throws Exception{
      Connection conn=null;
      try{
        Context ctx=new InitialContext();
        Context envCtx=(Context)ctx.lookup("java:comp/env");
        if(envCtx!=null){
          DataSource ds=(DataSource)envCtx.lookup("jdbc/testDB");
          System.out.println(ds);
          if(ds!=null){
            conn=ds.getConnection();
            System.out.println("DBManager1");
          }
        }
      }catch(Exception e){}
      
      return conn;
    }
}