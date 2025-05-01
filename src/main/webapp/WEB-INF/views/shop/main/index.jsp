<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


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
                    <p class="card-text">
                        <a href="/product/detail.do?product_id=${product.productId}" class="text-decoration-none text-dark">
                                ${product.productName}
                        </a>
                    </p>
                    <span class="fw-bold text-dark">
                        <fmt:formatNumber value="${product.productPrice}" type="number" groupingUsed="true" />원
                    </span>
                </div>
            </div>
        </div>
    </c:forEach>
</div>
