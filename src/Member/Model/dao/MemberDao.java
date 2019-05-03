package member.model.dao;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import member.model.vo.Member;

public class MemberDao {
	
	//의존성 주입 : di
	private SqlSession session = null; 
	
	private SqlSession getSession() {
		
		/*마이바티스 config 설정파일의 내용을 읽어서 db 연결하고, PreparedStatement 객체 생성과 동일한 의미를 가진 코드임 */
		/*String resource = "mybatis/mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		SqlSessionFactory factory = builder.build(inputStream);
		session = factory.openSession(false);*/
		
		try {
			session = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis/mybatis-config.xml")).openSession(false);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return session;
	}
	
	public MemberDao() {
		
	}
	
	public Member selectLogin(Member loginMember) {
		//mapper 파일 안에 있는 쿼리문 실행시키고 결과받음
		session = getSession();
		loginMember = session.selectOne("memberMapper.loginCheck", loginMember);
		session.close();
		return loginMember;
	}
	
	public Member selectMember(String userId) {
		session = getSession();
		Member member = null;
			//SqlSession session = null; //SqlSession이 Connection + PreparedStatement를 갖고 있는 애
			//mapper 파일 안에 있는 쿼리문 실행시키고 결과받음
			//첫번째 전달인자는 "[매퍼이름.]앨리먼트아이디명"
			//두번째 전달인자는 앨리먼트(쿼리문)으로 전달할 값 또는 객체 한 개
		member = session.selectOne("memberMapper.selectMember", userId);
		session.close();
		return member;
	}

	public int deleteMember(String userId) {
		session = getSession();
		int result = session.delete("memberMapper.deleteMember", userId);
		if(result > 0) {
			session.commit();
		} else {
			session.rollback();
		}
		session.close();
		return result;
	}

	public int updateMember(Member member) {
		session = getSession();
		int result = session.update("memberMapper.updateMember", member);
		if(result > 0) {
			session.commit();
		} else {
			session.rollback();
		}
		session.close();
		return result;
	}

	public int selectCheckId(String userId) {
		session = getSession();
		int idCount = session.selectOne("memberMapper.checkId", userId);
		session.close();	
		return idCount;
	}

	public int insertMember(Member member) {
		session = getSession();
		int result = session.insert("memberMapper.insertMember", member);
		if(result > 0) {
			session.commit();
		} else {
			session.rollback();
		}
		session.close();
		return result;
	}

	public ArrayList<Member> selectList() {
		session = getSession();
		List<Member> list = session.selectList("memberMapper.selectList");
		session.close();
		return (ArrayList<Member>)list;
	}
	
	
	
	
}








