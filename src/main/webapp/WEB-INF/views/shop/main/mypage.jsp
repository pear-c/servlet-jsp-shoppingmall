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

  <!-- 주문 내역 표시 -->
  <c:if test="${not empty orderList}">
    <h4 class="mt-5 mb-3">🧾 주문 내역</h4>
    <c:forEach var="order" items="${orderList}">
      <div class="card mb-3">
        <div class="card-header bg-light">
          주문일:
          <fmt:formatDate value="${order.orderCreatedAt}" pattern="yyyy-MM-dd HH:mm" />
          &nbsp;| 총 결제금액:
          <fmt:formatNumber value="${order.totalPrice}" groupingUsed="true" />원
        </div>
        <div class="card-body p-0">
          <table class="table table-bordered mb-0 text-center">
            <thead class="table-light">
            <tr>
              <th>상품 ID</th>
              <th>수량</th>
              <th>가격</th>
              <th>소계</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${order.orderItems}">
              <tr>
                <td>${item.orderProductId}</td>
                <td>${item.quantity}</td>
                <td><fmt:formatNumber value="${item.itemPrice}" groupingUsed="true" />원</td>
                <td><fmt:formatNumber value="${item.itemPrice * item.quantity}" groupingUsed="true" />원</td>
              </tr>
            </c:forEach>
            </tbody>
          </table>
        </div>
      </div>
    </c:forEach>
  </c:if>

  <c:if test="${empty orderList}">
    <p class="text-muted mt-4">주문 내역이 없습니다.</p>
  </c:if>
</div>
