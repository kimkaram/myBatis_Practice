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
 * Servlet implementation class MyInfoServlet
 */
@WebServlet("/myinfo")
public class MyInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 내 정보 보기 처리용 컨트롤러
		//1. 전송 온 값에 한글이 있다면, 인코딩처리함
		request.setCharacterEncoding("utf-8");
		//내보낼 값에 한글이 있다면, 인코딩처리함
		response.setContentType("text/html; charset=utf-8");
		
		//2. 전송 온 값 꺼내서 변수 또는 객체에 저장하기
		String userId = request.getParameter("userid");
		
		//3. 서비스 모델로 값 전달하고, 처리된 결과 받기
		Member member = 
			new MemberService().selectMember(userId);
		
		//4. 받은 결과를 가지고 성공/실패에 따라 뷰 파일 내보내기
		RequestDispatcher view = null;
		if(member != null) {  //조회가 성공했을 때
			view = request.getRequestDispatcher(
					"views/member/memberDetailView.jsp");
			request.setAttribute("member", member);
			view.forward(request, response);
			
		}else {  //조회가 실패했을 때
			view = request.getRequestDispatcher(
					"views/member/memberError.jsp");
			request.setAttribute("message", 
					userId + " 아이디에 대한 조회가 실패하였습니다.");
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
