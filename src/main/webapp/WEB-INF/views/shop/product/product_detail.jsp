<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<div class="container py-5">
    <div class="row">
        <!-- 상품 이미지 영역 -->
        <div class="col-md-5">
            <c:choose>
                <c:when test="${not empty product.imagePath}">
                    <img src="${pageContext.request.contextPath}/resources/images/${product.imagePath}"
                         alt="상품 이미지"
                         class="img-fluid rounded border"
                         onerror="this.onerror=null; this.src='${pageContext.request.contextPath}/resources/no-image.png';" />
                </c:when>
                <c:otherwise>
                    <img src="${pageContext.request.contextPath}/resources/no-image.png"
                         alt="기본 이미지"
                         class="img-fluid rounded border" />
                </c:otherwise>
            </c:choose>
        </div>

        <!-- 상품 정보 영역 -->
        <div class="col-md-7">
            <h2 class="fw-bold mb-3">${product.productName}</h2>
            <p class="mb-4">
                ${product.explain}
            </p>
            <h5 class="fw-bold text-dark"><fmt:formatNumber value="${product.productPrice}" type="number" groupingUsed="true" />원</h5>

            <p class="mt-2">
                <span class="badge bg-${product.productStock > 0 ? 'success' : 'danger'}">
                    재고:
                    <c:choose>
                        <c:when test="${product.productStock > 0}">
                            ${product.productStock}개 남음
                        </c:when>
                        <c:otherwise>
                            품절
                        </c:otherwise>
                    </c:choose>
                </span>
            </p>

            <br>

            <p class="text-muted">
                등록일: <fmt:formatDate value="${createdDate}" pattern="yyyy-MM-dd HH:mm" />
            </p>

            <!-- 장바구니 담기 -->
            <c:if test="${sessionScope.loginUser.userId ne 'admin'}">
                <c:if test="${param.duplicated eq 'true'}">
                    <p class="text-danger small mb-1">※ 이미 장바구니에 담긴 상품입니다.</p>
                </c:if>

                <form method="post" action="/cart/add.do" class="mt-3">
                    <input type="hidden" name="product_id" value="${product.productId}" />

                    <div class="input-group mb-3" style="max-width: 200px;">
                        <input type="number" name="quantity" class="form-control" value="1" min="1" max="${product.productStock}" required />
                        <button type="submit" class="btn btn-outline-primary">장바구니 담기</button>
                    </div>
                </form>
            </c:if>

            <a href="/index.do" class="btn btn-secondary mt-3">← 메인으로 돌아가기</a>
        </div>
    </div>
</div>
