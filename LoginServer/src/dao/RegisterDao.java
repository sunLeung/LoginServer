package dao;

import static common.db.DbUtils.dbUtils;

import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import bean.Player;

public class RegisterDao {

	private static Log log=LogFactory.getLog(RegisterDao.class);
	
	public static int save(Player bean){
		try {
			return dbUtils.insert(bean);
		} catch (SQLException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return -1;
	}
	
	public static int update(Player bean){
		try {
			return dbUtils.update(Player.class, "where loginid=?", bean.getId());
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return -1;
	}
	
	public static Player getPlayerByLoginid(int loginid){
		Player bean=null;
		try {
			bean=dbUtils.read(Player.class, "where loginid=?", loginid);
		} catch (SQLException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return bean;
	}
	
	public static Player getPlayerByName(String name){
		Player bean=null;
		try {
			bean=dbUtils.read(Player.class, "where name=?", name);
		} catch (SQLException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return bean;
	}
	
	public static Player getPlayerByPhone(String phone){
		Player bean=null;
		try {
			bean=dbUtils.read(Player.class, "where phone=?", phone);
		} catch (SQLException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return bean;
	}
	
	public static Player getPlayerByEmail(String email){
		Player bean=null;
		try {
			bean=dbUtils.read(Player.class, "where email=?", email);
		} catch (SQLException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return bean;
	}

}
