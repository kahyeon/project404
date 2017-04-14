package com.ProjectPhh.www;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application home page.
 */
@Controller
public class JoinController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	//성공, 실패  테스트 계정: id:test, password:test
	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public String login(Locale locale, Model model) {
		
		return "join/login";
	}
	@RequestMapping(value = "/login_check.do", method = RequestMethod.POST)
	public ModelAndView login(HttpServletRequest request, HttpSession session){
		ModelAndView model = new ModelAndView();
		session.removeAttribute("loginInfo");//세션 삭제

		String id = request.getParameter("userId");
		String password = request.getParameter("password");
		
		String url = "jdbc:mysql://175.210.54.233/phh";        // 사용하려는 데이터베이스명을 포함한 URL 기술
		String user_id = "root";
		String pw = "root1234";   
		                     
		Connection conn;
		PreparedStatement pstmt = null;
		String loginName = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url,user_id,pw);
			//여기서 password 함수 쓰면 매칭이 안됨....?
			String sql = "SELECT name FROM member WHERE id=? AND password=?;";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				loginName = rs.getString("name");	
				System.out.println(loginName);
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(loginName!=null){
			//로그인 성공
			session.setAttribute("loginInfo", loginName);
			
		}
		model.setViewName("join/login_success_fail");
		return model;
	}
	
	
	//성공, 실패, 중복확인
	@RequestMapping(value = "/join.do", method = RequestMethod.GET)
	public String join(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		return "join/joinindex";
	}

	@RequestMapping(value = "/join_ok.do", method = RequestMethod.GET)
	public String join_ok(HttpServletRequest request, Model model) {
		logger.info("Welcome home! The client locale is {}.", request);
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String telephone = request.getParameter("telephone");
		String email = request.getParameter("email");
		
		Connection conn = null; // null로 초기화 한다.
		PreparedStatement pstmt = null;
		boardBean bean = null ;
	try{

		/*
		String url = "jdbc:mysql://175.210.54.233/phh";        // 사용하려는 데이터베이스명을 포함한 URL 기술
		String user_id = "root";
		String pw = "root1234";  */ 
		
		String url = "jdbc:mysql://127.0.0.1/cats";                    // 사용하려는 데이터베이스명을 포함한 URL 기술
		String user_id = "root";                                                    // 사용자 계정
		String pw = "cats";                                                // 사용자 계정의 패스워드
		Class.forName("com.mysql.jdbc.Driver");                       // 데이터베이스와 연동하기 위해 DriverManager에 등록한다.
		conn=DriverManager.getConnection(url,user_id,pw);              // DriverManager 객체로부터 Connection 객체를 얻어온다.
		
		
		String sql = "insert into member ( id , password , name , telephone , email ) values ( ?, password(?) , ? , ?, ?);";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		pstmt.setString(2, password);
		pstmt.setString(3, name);
		pstmt.setString(4, telephone);
		pstmt.setString(5, email);
		pstmt.executeUpdate();
		
		}catch(Exception e){                                                    // 예외가 발생하면 예외 상황을 처리한다.
					e.printStackTrace();
		}
		
		
		return "join/joinindex";
	}

}
