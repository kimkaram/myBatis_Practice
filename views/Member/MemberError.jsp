<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!-- 선언 태그(Declaration Tag)
jsp 파일 안에 멤버변수와 메소드를 추가할 때 사용 -->
<%!
	private int num = 77;

	public void testMethod(){
		System.out.println("num : " + num);
	}
%>  
<%
	//scriptlet tag(스크립트릿 태그)
	//실제 자바 소스 코드 작성하는 영역 지정
	//스크리브릿 태그 안에서 작성한 모든 코드는
	//자바로 변환된 클래스 안의 _jspService() 메소드 안에
	//기록됨
	//testMethod();
%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원서비스 에러</title>
</head>
<body>
<h2>회원 서비스</h2>
<h3>
<c:catch var="e">
	<c:set var="num" value="null" />
</c:catch>
<c:if test="${e != null }">
	예외 발생 : ${e.message } <br>
</c:if>
<%-- 
${ message }</h3> --%>
<c:set var="value" value="${param.message }"/>
 에러메세지 : ${value } <br>
<a href="<c:url value='/index.jsp' />">시작페이지로 이동</a>
</body>
</html>









