package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class MemberEnrollServlet
 */
@WebServlet("/enroll")
public class MemberEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberEnrollServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 회원 가입 처리용 컨트롤러
		//1. 
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		//2.
		Member member = new Member();
		member.setUserId(request.getParameter("userid"));
		member.setUserName(request.getParameter("username"));
		member.setUserPwd(request.getParameter("userpwd"));
		member.setAge(Integer.parseInt(
				request.getParameter("age").trim()));
		member.setGender(request.getParameter("gender"));
		member.setEmail(request.getParameter("email"));
		member.setPhone(request.getParameter("phone"));
		member.setEtc(request.getParameter("etc"));	
		
		String[] hobbies = request.getParameterValues("hobby");
		if(hobbies != null) {
			member.setHobby(String.join(",", hobbies));
		}
		
		//3.
		int result = new MemberService().insertMember(member);
		
		//4.
		if(result > 0) {
			response.sendRedirect("/testm/index.jsp");
		}else {
			RequestDispatcher view = 
					request.getRequestDispatcher(
					"views/member/memberError.jsp");
			request.setAttribute("message", 					 
					"회원가입 실패하였습니다.");
			view.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
