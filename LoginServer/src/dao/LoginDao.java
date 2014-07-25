package dao;

import static common.db.DbUtils.dbUtils;

import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import bean.LoginLog;

public class LoginDao {

	private static Log log=LogFactory.getLog(LoginDao.class);
	
	public static int save(LoginLog bean){
		try {
			return dbUtils.insert(bean);
		} catch (SQLException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return -1;
	}
}
