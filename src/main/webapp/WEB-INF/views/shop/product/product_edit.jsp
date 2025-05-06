<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div class="container mt-4" style="max-width: 600px;">
  <h3 class="mb-4">상품 수정</h3>

  <form method="post" action="/admin/product/edit.do" enctype="multipart/form-data">
    <input type="hidden" name="product_id" value="${product.productId}" />

    <div class="form-floating mb-3">
      <input type="text" class="form-control" name="product_name" id="product_name" value="${product.productName}" required />
      <label for="product_name">상품 이름</label>
    </div>

    <div class="form-floating mb-3">
      <input type="number" class="form-control" name="product_price" id="product_price" value="${product.productPrice}" required />
      <label for="product_price">상품 가격</label>
    </div>

    <div class="form-floating mb-3">
      <textarea class="form-control" name="product_explain" style="height: 100px;" required>${product.explain}</textarea>
      <label for="product_explain">상품 설명</label>
    </div>

    <div class="form-floating mb-3">
      <input type="number" class="form-control" name="product_stock" id="product_stock"
             value="${product.productStock}" min="0" required />
      <label for="product_stock">재고 수량</label>
    </div>

    <div class="form-floating mb-3">
      <select class="form-select" name="category_id" id="category_id" required>
        <c:forEach var="category" items="${categoryList}">
          <option value="${category.categoryId}" ${product.categoryId == category.categoryId ? 'selected' : ''}>
              ${category.categoryName}
          </option>
        </c:forEach>
      </select>
      <label for="category_id">카테고리</label>
    </div>

    <div class="mb-3">
      <label for="product_image" class="form-label">상품 이미지 (선택)</label>
      <input type="file" class="form-control" name="product_image" id="product_image" accept="image/*" />
      <div class="mt-2">
        <img src="${pageContext.request.contextPath}/resources/images/${product.imagePath}" alt="상품 이미지" width="120"/>
      </div>
    </div>

    <button type="submit" class="btn btn-success w-100">수정 완료</button>
  </form>
</div>
