package net.sourceforge.ondex.core.sql3.helper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.sourceforge.ondex.core.sql3.SQL3Graph;

public class MetaDataHelper {
	
	/**
	 * Duplicate of the ones in SQL2, except accepting SQL3Graph rather than SQL2Graph
	 * 
	 * @author sckuo
	 */

	public static String fetchString (SQL3Graph s, String table, String field, String id) {
		
		String f = null;
		try {
			
			Connection conn = s.getConnection();
			PreparedStatement getStringItem = conn.prepareStatement("select "+field+" from "+table+" where (sid, id) = (?,?)");
			
			getStringItem.setLong(1, s.getSID());
			getStringItem.setString(2, id);			
			
			ResultSet rs = getStringItem.executeQuery();
			
			if(rs.next()) {
				f = rs.getString(field);
			}
			rs.close();
			getStringItem.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// TODO Auto-generated method stub
		return f;
		
	}
	
	public static Boolean fetchBoolean (SQL3Graph s, String table, String field, String id) {
		
		Boolean f = null;
		try {
			
			Connection conn = s.getConnection();
			PreparedStatement getStringItem = conn.prepareStatement("select "+field+" from "+table+" where (sid, id) = (?,?)");
			
			getStringItem.setLong(1, s.getSID());
			getStringItem.setString(2, id);
						
			ResultSet rs = getStringItem.executeQuery();
			
			if(rs.next()) {
				f = rs.getBoolean(field);
			}
			rs.close();
			getStringItem.close();
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return f;
		
	}
	
	public static void setBoolean (SQL3Graph s, String table, String field, String id, boolean value) {
		
		Connection conn = s.getConnection();
		
		try {
			PreparedStatement setStringItem = conn.prepareStatement("update "+table+" set "+field+" = ? where (sid, id) = (?,?)");
			
			setStringItem.setBoolean(1, value);
			setStringItem.setLong(2, s.getSID());
			setStringItem.setString(3, id);
			
			
			setStringItem.execute();
			setStringItem.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void setString (SQL3Graph s, String table, String field, String id, String value) {
		
		Connection conn = s.getConnection();
		
		try {
			PreparedStatement setStringItem = conn.prepareStatement("update "+table+" set "+field+" = ? where (sid, id) = (?,?)");
			
			setStringItem.setString(1, value);
			setStringItem.setLong(2, s.getSID());
			setStringItem.setString(3, id);
			
			
			setStringItem.execute();
			setStringItem.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	
	
}
