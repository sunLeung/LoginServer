package common.route;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.LoginService;

public class RouteController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			req.setCharacterEncoding("utf-8");
			resp.setHeader("content-type", "text/html;charset=UTF-8");
			String uri=req.getRequestURI();
			String[] sArray=uri.split("/");
			String methodName=sArray[sArray.length-1];
			Method method=LoginService.class.getMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
			method.invoke(LoginService.class, req,resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		String uri="c";
		String[] sArray=uri.split("/");
		System.out.println(sArray[sArray.length-1]);
	}
}
