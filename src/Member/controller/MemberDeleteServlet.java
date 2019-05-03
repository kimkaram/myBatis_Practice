package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;

/**
 * Servlet implementation class MemberDeleteServlet
 */
@WebServlet("/mdel")
public class MemberDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 회원 탈퇴 처리용 컨트롤러
		//1. 전송온 값에 한글이 있거나, 전송방식이 post 이면
		//request 에 대해 인코딩 처리함
		
		//2. 전송온 값 꺼내서 변수 또는 객체에 기록 저장하기
		String userId = request.getParameter("userid");
		
		//3. 서비스모델로 값 전달하고, 처리 결과 받기
		int result = new MemberService().deleteMember(userId);
		
		//4. 받은 결과에 따라 뷰 선택해서 내보내기
		if(result > 0) {
			response.sendRedirect("/testm/logout");
		}else {
			RequestDispatcher view = 
				request.getRequestDispatcher("views/member/memberError.jsp");
			request.setAttribute("message", 
					userId + " 님의 탈퇴처리가 실패하였습니다.");
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
