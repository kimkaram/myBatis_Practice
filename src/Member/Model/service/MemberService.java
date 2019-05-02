package member.model.service;

import java.util.ArrayList;

import member.model.dao.MemberDao;
import member.model.vo.Member;

public class MemberService {
	//의존성 주입 : DI
	private MemberDao mdao = new MemberDao();
	
	public MemberService() {}
	
	//로그인 요청 처리용
	public Member loginCheck(Member member) {
		return	mdao.selectLogin(member);
	}

	public Member selectMember(String userId) {
		return	mdao.selectMember(userId);
	}

	public int deleteMember(String userId) {
		return mdao.deleteMember(userId);
	}

	public int updateMember(Member member) {
		return mdao.updateMember(member);
	}

	public int selectCheckId(String userId) {
		return mdao.selectCheckId(userId);
	}

	public int insertMember(Member member) {
		return mdao.insertMember(member);
	}

	public ArrayList<Member> selectList() {
		return mdao.selectList();
	}
}









