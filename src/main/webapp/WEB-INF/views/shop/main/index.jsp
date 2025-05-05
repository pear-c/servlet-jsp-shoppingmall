<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="container-fluid">
    <div class="row">
        <!-- 카테고리 사이드바 -->
        <div class="col-md-2 mb-4">
            <div class="card">
                <div class="card-header bg-light fw-bold">
                    카테고리
                </div>
                <ul class="list-group list-group-flush">
                    <li class="list-group-item">
                        <a href="/index.do" class="text-decoration-none text-dark">전체 보기</a>
                    </li>
                    <c:forEach var="category" items="${categoryList}">
                        <li class="list-group-item">
                            <a href="/index.do?category_id=${category.categoryId}" class="text-decoration-none">
                                    ${category.categoryName}
                            </a>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>

        <!-- 상품 목록 -->
        <div class="col-md-10">
            <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
                <c:forEach var="product" items="${productList}">
                    <div class="col">
                        <div class="card shadow-sm">
                            <!-- 이미지 삽입 : 이미지 없으면 기본 이미지 -->
                            <a href="/product/detail.do?product_id=${product.productId}">
                                <img
                                        src="${pageContext.request.contextPath}/resources/images/${product.imagePath}"
                                        class="card-img-top mx-auto d-block"
                                        alt="상품 이미지"
                                        style="width: 100%; height: 225px; object-fit: contain; background-color: #f8f9fa;"
                                        onerror="this.onerror=null; this.src='${pageContext.request.contextPath}/resources/no-image.png';"
                                />
                            </a>

                            <div class="card-body">
                                <p class="card-text d-flex align-items-center justify-content-between">
                                    <a href="/product/detail.do?product_id=${product.productId}" class="text-decoration-none text-dark">
                                            ${product.productName}
                                    </a>
                                    <!-- 카테고리 표시 -->
                                    <c:if test="${not empty product.categoryName}">
                                        <span class="badge bg-secondary">${product.categoryName}</span>
                                    </c:if>
                                </p>
                                <span class="fw-bold text-dark">
                                    <fmt:formatNumber value="${product.productPrice}" type="number" groupingUsed="true" />원
                                </span>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>

            <!-- 페이지 네비게이션 -->
            <div class="mt-4 d-flex justify-content-center">
                <nav>
                    <ul class="pagination">
                        <c:set var="totalCount" value="${productPage.totalCount}" />
                        <c:set var="pageSize" value="9" />
                        <c:set var="currentPage" value="${param.page != null ? param.page : 1}" />
                        <c:set var="totalPages" value="${(totalCount + pageSize - 1) / pageSize}" />

                        <c:forEach var="i" begin="1" end="${totalPages}">
                            <li class="page-item ${i == currentPage ? 'active' : ''}">
                                <a class="page-link"
                                   href="/index.do?page=${i}<c:if test='${not empty selectedCategoryId}'>&category_id=${selectedCategoryId}</c:if>">
                                        ${i}
                                </a>
                            </li>
                        </c:forEach>
                    </ul>
                </nav>
            </div>
        </div>
    </div>
</div>
