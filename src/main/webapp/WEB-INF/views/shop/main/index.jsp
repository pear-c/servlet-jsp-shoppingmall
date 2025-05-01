<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
    <c:forEach var="product" items="${productList}">
        <div class="col">
            <div class="card shadow-sm">
                <!-- 이미지 삽입 : 이미지 없으면 기본 이미지 -->
                <c:choose>
                    <c:when test="${not empty product.imagePath}">
                        <img
                            src="${pageContext.request.contextPath}/resources/images/${product.imagePath}"
                            class="card-img-top mx-auto d-block"
                            alt="상품 이미지"
                            style="width: 100%; height: 225px; object-fit: contain; background-color: #f8f9fa;"
                            onerror="this.onerror=null; this.src='${pageContext.request.contextPath}/resources/no-image.png';"
                        />
                    </c:when>
                    <c:otherwise>
                        <img
                            src="${pageContext.request.contextPath}/resources/no-image.png"
                            class="card-img-top mx-auto d-block"
                            alt="기본 이미지"
                            style="width: 100%; height: 225px; object-fit: contain; background-color: #f8f9fa;"
                        />
                    </c:otherwise>
                </c:choose>

                <div class="card-body">
                    <p class="card-text fw-bold">${product.productName}</p>
                    <div class="d-flex justify-content-between align-items-center">
                        <div class="btn-group">
                            <a href="/product/view.do?productId=${product.productId}" class="btn btn-sm btn-outline-secondary">View</a>
                            <a href="/admin/product/edit.do?productId=${product.productId}" class="btn btn-sm btn-outline-secondary">Edit</a>
                        </div>
                        <small class="text-muted">${product.createdAt}</small>
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>
</div>
