<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="container py-4">
  <h2 class="mb-4 text-center">관리자 통합 관리 페이지</h2>

  <div class="row g-4">
    <!-- 카테고리 관리 -->
    <div class="col-md-6">
      <div class="card h-100 border-primary">
        <div class="card-header bg-primary text-white">
          <h5 class="mb-0">카테고리 관리</h5>
        </div>
        <div class="card-body">
          <c:if test="${not empty sessionScope.categoryCreateError}">
            <small class="text-danger">${sessionScope.categoryCreateError}</small>
            <c:remove var="categoryCreateError" scope="session" />
          </c:if>
          <form method="post" action="/admin/category/create.do">
            <div class="form-floating mb-3">
              <input type="text" name="category_name" class="form-control" id="category_name" placeholder="카테고리 이름" required>
              <label for="category_name">카테고리 명</label>
            </div>
            <button type="submit" class="btn btn-outline-primary w-100">카테고리 등록</button>
          </form>

          <hr/>
          <h6 class="mt-3">등록된 카테고리</h6>
          <ul class="list-group list-group-flush">
            <c:forEach var="category" items="${categoryList}">
              <li class="list-group-item d-flex justify-content-between align-items-center">
                <span class="category-name">${category.categoryName}</span>

                <c:if test="${not empty sessionScope.categoryDeleteError}">
                  <small class="text-danger">${sessionScope.categoryDeleteError}</small>
                  <c:remove var="categoryDeleteError" scope="session" />
                </c:if>

                <div class="d-flex gap-2">
                  <!-- 수정 버튼 -->
                  <a href="/admin/category/edit.do?categoryId=${category.categoryId}" class="btn btn-sm btn-outline-primary">수정</a>

                  <!-- 삭제 버튼 -->
                  <form method="post" action="/admin/category/delete.do" class="mb-0">
                    <input type="hidden" name="category_id" value="${category.categoryId}" />
                    <button type="submit" class="btn btn-sm btn-outline-danger"
                            onclick="return confirm('이 카테고리를 삭제하시겠습니까?');">삭제</button>
                  </form>
                </div>
              </li>
            </c:forEach>
          </ul>
        </div>
      </div>
    </div>

    <!-- 상품 등록 및 관리 -->
    <div class="col-md-6">
      <div class="card h-100 border-success">
        <div class="card-header bg-success text-white">
          <h5 class="mb-0">상품 등록</h5>
        </div>
        <div class="card-body">
          <form method="post" action="/admin/product/create.do" enctype="multipart/form-data">
            <div class="form-floating mb-2">
              <input type="text" name="product_name" class="form-control" id="product_name" placeholder="상품 이름" required>
              <label for="product_name">상품 이름</label>
            </div>

            <div class="form-floating mb-2">
              <input type="number" name="product_price" class="form-control" id="product_price" placeholder="가격" required>
              <label for="product_price">가격</label>
            </div>

            <div class="form-floating mb-2">
              <textarea name="product_explain" class="form-control" placeholder="상품 설명" style="height: 80px;" required></textarea>
              <label for="product_explain">상품 설명</label>
            </div>

            <div class="form-floating mb-2">
              <select class="form-select" name="category_id" id="category_id" required>
                <option selected disabled>카테고리 선택</option>
                <c:forEach var="category" items="${categoryList}">
                  <option value="${category.categoryId}">${category.categoryName}</option>
                </c:forEach>
              </select>
              <label for="category_id">카테고리</label>
            </div>

            <div class="mb-3">
              <label for="product_image" class="form-label">상품 이미지</label>
              <input type="file" class="form-control" name="product_image" id="product_image" accept="image/*">
            </div>

            <button type="submit" class="btn btn-outline-success w-100">상품 등록</button>
          </form>
        </div>
      </div>
    </div>
  </div>

  <!-- 등록된 상품 목록 -->
  <div class="col-md-12">
    <div class="card border-secondary">
      <div class="card-header bg-light fw-bold">등록된 상품 목록</div>
      <div class="card-body p-0">
        <table class="table table-hover align-middle mb-0 text-center">
          <thead class="table-light">
          <tr>
            <th>이미지</th>
            <th>상품명</th>
            <th>가격</th>
            <th>카테고리</th>
            <th>관리</th>
          </tr>
          </thead>
          <tbody>
          <c:forEach var="product" items="${productList}">
            <tr>
              <td>
                <img src="${pageContext.request.contextPath}/resources/images/${product.imagePath}"
                     onerror="this.src='${pageContext.request.contextPath}/resources/no-image.png'"
                     style="width: 80px; height: 80px; object-fit: cover;"/>
              </td>
              <td>${product.productName}</td>
              <td><fmt:formatNumber value="${product.productPrice}" type="number" groupingUsed="true" /> 원</td>
              <td>${product.categoryName}</td>
              <td>
                <a href="/product/detail.do?product_id=${product.productId}" class="btn btn-sm btn-outline-secondary me-1">상세보기</a>
                <a href="/admin/product/edit.do?product_id=${product.productId}" class="btn btn-sm btn-outline-primary">수정</a>
                <form method="post" action="/admin/product/delete.do" class="d-inline">
                  <input type="hidden" name="product_id" value="${product.productId}" />
                  <button type="submit" class="btn btn-sm btn-outline-danger" onclick="return confirm('삭제하시겠습니까?')">삭제</button>
                </form>
              </td>
            </tr>
          </c:forEach>
          </tbody>
        </table>
      </div>
    </div>
  </div>

  <!-- 회원 관리 -->
  <div class="row mt-5">
    <div class="col-12">
      <div class="card border-dark">
        <div class="card-header bg-dark text-white">
          <h5 class="mb-0">회원 목록</h5>
        </div>
        <div class="card-body">
          <table class="table table-hover table-bordered align-middle text-center">
            <thead class="table-light">
            <tr>
              <th>아이디</th>
              <th>이름</th>
              <th>비밀번호</th>
              <th>생년월일</th>
              <th>포인트</th>
              <th>권한</th>
              <th>관리</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="user" items="${userList}">
              <tr>
                <td>${user.userId}</td>
                <td>${user.userName}</td>
                <td>${user.userPassword}</td>
                <td>${user.userBirth}</td>
                <td>${user.userPoint}</td>
                <td>${user.userAuth}</td>
                <td>
                  <c:if test="${user.userId ne 'admin'}">
                    <form method="post" action="/admin/user/delete.do" onsubmit="return confirm('정말 삭제하시겠습니까?');">
                      <input type="hidden" name="user_id" value="${user.userId}" />
                      <button type="submit" class="btn btn-sm btn-outline-danger">삭제</button>
                    </form>
                  </c:if>
                  <c:if test="${user.userId eq 'admin'}">
                    <span class="text-muted">관리자</span>
                  </c:if>
                </td>
              </tr>
            </c:forEach>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
</div>
