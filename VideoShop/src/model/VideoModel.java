package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.vo.Video;
public class VideoModel {
	
	Connection con;
	
	public VideoModel() throws Exception{
		
		
			con = DBCon.getInstance();
	}
	
	public void insertVideo(Video dao, int count) throws Exception{

		String sql = "INSERT INTO Video_Info  VALUES(seq_video_id.nextval,?,?,?,?,?,1)";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1,dao.getGenre());
		ps.setString(2,dao.getVideoName());
		ps.setString(3,dao.getDirector());
		ps.setString(4,dao.getActor());
		ps.setString(5,dao.getExp());
	
		for(int i=0;i<count;i++) {
			ps.executeUpdate();
		}
				
	}

	public ArrayList searchVideo(int idx, String word) throws Exception {
			ArrayList list  = new ArrayList();
			String [] colName = {"VIDEO_ID","TITLE","GENRE","DIRECTOR","ACTOR"}; 
			String sql = "select VIDEO_ID,GENRE,TITLE,DIRECTOR,ACTOR from Video_Info where "+ colName[idx]+" Like '%"+word+"%'";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				ArrayList temp = new ArrayList();
				temp.add(rs.getString("VIDEO_ID"));
				temp.add(rs.getString("TITLE"));
				temp.add(rs.getString("GENRE"));
				temp.add(rs.getString("DIRECTOR"));
				temp.add(rs.getString("ACTOR"));
				list.add(temp);
			}
			
		return list;
	}

	public void modifyVideo(int id,Video v) throws SQLException {
		String sql = "UPDATE Video_Info SET GENRE = ?, TITLE = ?, DIRECTOR = ? , ACTOR = ?, EX = ? WHERE Video_id = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, v.getGenre());
		ps.setString(2, v.getVideoName());
		ps.setString(3, v.getDirector());
		ps.setString(4, v.getActor());
		ps.setString(5, v.getExp());
		ps.setInt(6, id);
		ps.executeUpdate();
		
	}

	public void deleteVideo(int id) throws SQLException {
		String sql = "DELETE FROM Video_Info WHERE VIDEO_ID = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, id);
	
		ps.executeUpdate();
		
	}

	public Video searchByPk(int vNum) throws SQLException {
		String sql = "SELECT * FROM Video_Info WHERE Video_id = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, vNum);
		ResultSet rs = ps.executeQuery();
		Video v = new Video();
		rs.next();
		v.setActor(rs.getString("ACTOR"));
		v.setDirector(rs.getString("DIRECTOR"));
		v.setExp(rs.getString("EX"));
		v.setGenre(rs.getString("GENRE"));
		v.setVideoName(rs.getString("TITLE"));
		v.setVideoNo(Integer.parseInt(rs.getString("VIDEO_ID")));
		return v;
	}
	

}
