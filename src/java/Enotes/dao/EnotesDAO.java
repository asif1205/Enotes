/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Enotes.dao;

import Enotes.dbutil.DBConnection;
import Enotes.dto.EnotesAddingNotedto;
import Enotes.dto.EnotesCheckingPassworddto;
import Enotes.dto.EnotesRegistrationdto;
import Enotes.dto.EnotesShowAllNotesdto;
import Enotes.dto.EnotesUpdatingNotesdto;
import Enotes.dto.EnotesUserdto;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author acer
 */
public class EnotesDAO {

    public static int getNextId() throws SQLException {
        Connection conn = DBConnection.getConnection();
        Statement s = conn.createStatement();
        ResultSet rs = s.executeQuery("select max(id) from users");
        rs.next();
        return rs.getInt(1) + 1;
    }

    public static boolean validateUser(EnotesUserdto obj) throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("select name from users where name=? and password=?");
        ps.setString(1, obj.getName());
        ps.setString(2, obj.getPassword());
        int result = ps.executeUpdate();
        if (result > 0) {
            return true;
        }
        return false;
    }

    public static boolean registerNewUser(EnotesRegistrationdto obj) throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("insert into users values(?,?,?,?)");
        ps.setString(1, obj.getName());
        ps.setString(2, obj.getEmail());
        ps.setString(3, obj.getPassword());
        ps.setInt(4, EnotesDAO.getNextId());
        int result = ps.executeUpdate();
        if (result > 0) {
            return true;
        }
        return false;
    }

    public static int getIdFromNameAndPassword(String name, String password) throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("select id from users where name=? and password=?");
        ps.setString(1, name);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();
        int id = 0;
        while (rs.next()) {
            id = rs.getInt(1);
        }
        return id;
    }

    public static int getnextSelfIdFromDB() throws SQLException {
        Connection conn = DBConnection.getConnection();
        Statement ps = conn.createStatement();

        ResultSet rs = ps.executeQuery("select max(selfid) from notes");
        int id = 0;
        while (rs.next()) {
            id = rs.getInt(1);
        }
        return id+1;
    }
    
    public static boolean addingNewNotes(EnotesAddingNotedto obj) throws SQLException {

        int id = EnotesDAO.getIdFromNameAndPassword(obj.getName(), obj.getPassword());
        int nextSelfId=EnotesDAO.getnextSelfIdFromDB();
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        System.out.println("Date is " + sqlDate);
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("insert into notes values(?,?,?,?,?,?,?)");
        
        ps.setInt(1, id);
        ps.setString(2, obj.getName());
        ps.setString(3, obj.getContent());
        ps.setDate(4, sqlDate);
        ps.setString(5, obj.getSubject());
        ps.setString(6, obj.getTitle());
        ps.setInt(7,nextSelfId);
        int result = ps.executeUpdate();
        if (result > 0) {
            return true;
        }
        return false;
    }

    public static boolean isUniquePassword(EnotesCheckingPassworddto obj) throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("select name from users where password=?");
        ps.setString(1, obj.getPassword());
        ResultSet rs = ps.executeQuery();

        String name = "";
        if (rs.next()) {
            name = rs.getString(1);
        }

        System.out.println("Name of the password is " + name);
        System.out.println("lengthe of the name is " + name.length());
        if (name.length() == 0) {
            return true;
        }
        return false;
    }

    public static List<EnotesShowAllNotesdto> getAllNOtes(EnotesUserdto obj) throws SQLException {
        int id = EnotesDAO.getIdFromNameAndPassword(obj.getName(), obj.getPassword());
        List<EnotesShowAllNotesdto> list = new ArrayList<>();

        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("select title,subject,text,notesdate,selfid from notes where id=?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            EnotesShowAllNotesdto tempObj = new EnotesShowAllNotesdto();
            String title = rs.getString(1);
            String subject = rs.getString(2);
            String content = rs.getString(3);
            Date date = rs.getDate(4);
            int selfId=rs.getInt(5);
            tempObj.setTitle(title);
            tempObj.setSubject(subject);
            tempObj.setContent(content);
            tempObj.setD(date);
            tempObj.setselfId(selfId);
            list.add(tempObj);
        }
        return list;
    }

    public static boolean updatingNotes(int selfId,EnotesUpdatingNotesdto obj) throws SQLException {
        int id = EnotesDAO.getIdFromNameAndPassword(obj.getName(), obj.getPassword());

        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("UPDATE notes SET title=?, subject=?, text=? where selfid=?");

        ps.setString(1, obj.getNewTitle());
        ps.setString(2, obj.getNewSubject());
        ps.setString(3, obj.getNewContent());
        ps.setInt(4,selfId);

        int result = ps.executeUpdate();
        if (result > 0) {
            return true;
        }
        return false;
    }

    
    
        public static boolean deletingNotes(int selfRowId) throws SQLException {

        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("DELETE FROM notes WHERE selfid=?");
        ps.setInt(1, selfRowId);

        int rowsDeleted = ps.executeUpdate();
            System.out.println("rowsDeleted now"+rowsDeleted);
        if (rowsDeleted>0) {
            return true;
        }
        return false;
    }
        
        
    public static int getRowId(List<EnotesShowAllNotesdto> list,EnotesAddingNotedto obj)throws SQLException
    {
      String title=obj.getTitle().trim();
      String subject=obj.getSubject().trim();
      String content=obj.getContent().trim();    
      for(EnotesShowAllNotesdto temp:list){
       System.out.println("row id "+temp.getselfId());
          System.out.println("mytitle "+title);
          System.out.println("dbtitle"+temp.getTitle());
          System.out.println("mysubject "+subject);
          System.out.println("dbsubject "+temp.getSubject());
          System.out.println("mycontent "+content);
          System.out.println("dbcontent "+temp.getContent().replaceAll("\n", ""));
          if(title.equalsIgnoreCase(temp.getTitle().trim())&& subject.equalsIgnoreCase(temp.getSubject().trim()))
              return temp.getselfId();        
      }   
      return 0;
    }
}
