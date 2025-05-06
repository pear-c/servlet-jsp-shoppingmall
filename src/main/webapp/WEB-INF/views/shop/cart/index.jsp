<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="container py-5">
    <h2 class="mb-4">🛒 장바구니</h2>

    <c:choose>
        <c:when test="${empty cartItemProductMap}">
            <div class="alert alert-info">장바구니가 비어 있습니다.</div>
        </c:when>
        <c:otherwise>
            <table class="table table-bordered align-middle text-center">
                <thead class="table-light">
                <tr>
                    <th>이미지</th>
                    <th>상품명</th>
                    <th>가격</th>
                    <th>수량</th>
                    <th>소계</th>
                    <th>삭제</th>
                </tr>
                </thead>
                <tbody>
                <c:set var="total" value="0" />
                <c:forEach var="entry" items="${cartItemProductMap}">
                    <c:set var="item" value="${entry.key}" />
                    <c:set var="product" value="${entry.value}" />
                    <tr>
                        <td>
                            <img src="${pageContext.request.contextPath}/resources/images/${product.imagePath}"
                                 alt="상품 이미지" width="80" height="80"
                                 onerror="this.src='${pageContext.request.contextPath}/resources/no-image.png'" />
                        </td>
                        <td>${product.productName}</td>
                        <td><fmt:formatNumber value="${product.productPrice}" groupingUsed="true" />원</td>
                        <td>
                            <form method="post" action="/cart/update.do" class="d-flex justify-content-center align-items-center">
                                <input type="hidden" name="product_id" value="${product.productId}" />
                                <input type="number" name="quantity" value="${item.cartQuantity}"
                                       min="1" max="${product.productStock}" class="form-control form-control-sm me-2" style="width: 70px;" required />
                                <button type="submit" class="btn btn-sm btn-outline-secondary">변경</button>
                            </form>
                        </td>
                        <td>
                            <fmt:formatNumber value="${product.productPrice * item.cartQuantity}" groupingUsed="true" />원
                            <c:set var="total" value="${total + (product.productPrice * item.cartQuantity)}" />
                        </td>
                        <td>
                            <form method="post" action="/cart/delete.do">
                                <input type="hidden" name="product_id" value="${product.productId}" />
                                <button type="submit" class="btn btn-sm btn-outline-danger">삭제</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                <tr>
                    <td colspan="4" class="text-end fw-bold">총합</td>
                    <td colspan="2" class="fw-bold text-primary">
                        <fmt:formatNumber value="${total}" groupingUsed="true" />원
                    </td>
                </tr>
                </tbody>
            </table>

            <div class="mt-3 text-end">
                <form method="post" action="/cart/clear.do" class="d-inline">
                    <button type="submit" class="btn btn-outline-secondary">장바구니 비우기</button>
                </form>
                <a href="/order/checkout.do" class="btn btn-primary ms-2">주문하기</a>
            </div>
        </c:otherwise>
    </c:choose>
</div>
