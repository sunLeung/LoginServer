package service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.Def;
import utils.RespUtils;
import utils.StringUtils;
import bean.LoginLog;
import bean.Player;
import dao.LoginDao;
import dao.RegisterDao;

public class LoginService {
	
	public static void register(HttpServletRequest req, HttpServletResponse resp){
		String name=req.getParameter("name");
		String ip=req.getRemoteAddr();
		String email=req.getParameter("email");
		String phone=req.getParameter("phone");
		String unionid=req.getParameter("unionid");
		String password=req.getParameter("password");
		Timestamp createTime=new Timestamp(System.currentTimeMillis());
		String model=req.getParameter("model");
		String mark=req.getParameter("mark");
		Timestamp lastLoginTime=createTime;
		
		if(StringUtils.isBlank(name)){
			RespUtils.commonResp(resp, RespUtils.CODE.FAIL, "player name is null.");
			return;
		}
		if(StringUtils.isBlank(email)&&StringUtils.isBlank(phone)){
			RespUtils.commonResp(resp, RespUtils.CODE.FAIL, "email or phone do not null at the same time.");
			return;
		}
		if(password.length()<6||password.length()>16){
			RespUtils.commonResp(resp, RespUtils.CODE.FAIL, "password must between 6 and 16 charset.");
			return;
		}
		
		if(RegisterDao.getPlayerByName(name)!=null){
			RespUtils.commonResp(resp, RespUtils.CODE.FAIL, "name is exist.");
			return;
		}
		if(RegisterDao.getPlayerByEmail(email)!=null){
			RespUtils.commonResp(resp, RespUtils.CODE.FAIL, "email is exist.");
			return;
		}
		if(RegisterDao.getPlayerByName(phone)!=null){
			RespUtils.commonResp(resp, RespUtils.CODE.FAIL, "phone is exist.");
			return;
		}
		
		Player p=new Player();
		p.setName(name);
		p.setIp(ip);
		p.setEmail(email);
		p.setPhone(phone);
		p.setUnionid(unionid);
		p.setPassword(password);
		p.setCreateTime(createTime);
		p.setModel(model);
		p.setMark(mark);
		p.setLastLoginTime(lastLoginTime);
		
		int id=RegisterDao.save(p);
		if(id>0){
			RespUtils.commonResp(resp, RespUtils.CODE.SUCCESS, "register success.");
		}
	}
	
	
	public static void checkName(HttpServletRequest req, HttpServletResponse resp){
		String name=req.getParameter("name");
		
		if(RegisterDao.getPlayerByName(name)!=null){
			RespUtils.commonResp(resp, RespUtils.CODE.FAIL, "this name is exist.");
			return;
		}
		RespUtils.commonResp(resp, RespUtils.CODE.SUCCESS, "this name is available");
	}

	public static void checkEmail(HttpServletRequest req, HttpServletResponse resp){
		String email=req.getParameter("email");
		
		if(RegisterDao.getPlayerByEmail(email)!=null){
			RespUtils.commonResp(resp, RespUtils.CODE.FAIL, "this email is exist.");
			return;
		}
		RespUtils.commonResp(resp, RespUtils.CODE.SUCCESS, "this email is available");
	}

	public static void checkPhone(HttpServletRequest req, HttpServletResponse resp){
		String phone=req.getParameter("phone");
		
		if(RegisterDao.getPlayerByPhone(phone)!=null){
			RespUtils.commonResp(resp, RespUtils.CODE.FAIL, "this phone is exist.");
			return;
		}
		RespUtils.commonResp(resp, RespUtils.CODE.SUCCESS, "this phone is available");
	}
	
	public static void login(HttpServletRequest req, HttpServletResponse resp){
		String ip=req.getRemoteAddr();
		//可以是email或者phone
		String loginName=req.getParameter("loginName");
		String password=req.getParameter("password");
		String model=req.getParameter("model");
		Timestamp lastLoginTime=new Timestamp(System.currentTimeMillis());
		
		LoginLog loginlog =new LoginLog();
		loginlog.setIp(ip);
		loginlog.setLastLoginTime(lastLoginTime);
		loginlog.setModel(model);
		
		Player p=RegisterDao.getPlayerByEmail(loginName);
		if(p == null){
			p=RegisterDao.getPlayerByPhone(loginName);
		}
		if(p==null){
			loginlog.setResult(1);
			LoginDao.save(loginlog);
			RespUtils.commonResp(resp, RespUtils.CODE.FAIL, "LoginName is not available");
			return;
		}
		loginlog.setLoginid(p.getLoginid());
		if(p.getRealPassword().equals(password)){
			loginlog.setResult(0);
			LoginDao.save(loginlog);
			Map<String,String> result=new HashMap<String,String>();
			result.put("loginid", p.getLoginid()+"");
			result.put("token", StringUtils.encrypt(p.getLoginid()+"#"+System.currentTimeMillis(), Def.LOGIN_TOKEN_KEY));
			RespUtils.commonResp(resp, RespUtils.CODE.SUCCESS, result);
		}else{
			loginlog.setResult(2);
			LoginDao.save(loginlog);
			RespUtils.commonResp(resp, RespUtils.CODE.FAIL, "Login fail with wrong password.");
		}
	}
}
