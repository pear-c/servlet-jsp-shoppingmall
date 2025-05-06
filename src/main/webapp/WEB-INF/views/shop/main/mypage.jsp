<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<div class="container py-5" style="max-width: 700px;">
  <h2 class="mb-4 text-center">마이페이지</h2>

  <div class="card shadow-sm">
    <div class="card-body">
      <table class="table table-borderless mb-0">
        <tr>
          <th scope="row">아이디</th>
          <td>${sessionScope.loginUser.userId}</td>
        </tr>
        <tr>
          <th scope="row">이름</th>
          <td>${sessionScope.loginUser.userName}</td>
        </tr>
        <tr>
          <th scope="row">생년월일</th>
          <td>${sessionScope.loginUser.userBirth}</td>
        </tr>
        <tr>
          <th scope="row">권한</th>
          <td>
            <c:choose>
              <c:when test="${sessionScope.loginUser.userAuth == 'ROLE_ADMIN'}">관리자</c:when>
              <c:otherwise>일반 사용자</c:otherwise>
            </c:choose>
          </td>
        </tr>
        <tr>
          <th scope="row">포인트</th>
          <td><fmt:formatNumber value="${sessionScope.loginUser.userPoint}" type="number"/> P</td>
        </tr>
      </table>
      <div class="text-end mt-3" style="max-width: 700px; margin: 0 auto;">
        <form method="post" action="/mypage/user/delete.do" onsubmit="return confirm('탈퇴하시겠습니까?');">
          <button type="submit" class="btn btn-outline-danger">회원 탈퇴</button>
        </form>
      </div>
    </div>
  </div>
</div>
