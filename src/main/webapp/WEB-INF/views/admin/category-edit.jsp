<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div style="max-width: 600px; margin: auto;">
  <h3 class="mb-4">카테고리 수정</h3>

  <form method="post" action="/admin/category/update.do">
    <input type="hidden" name="category_id" value="${category.categoryId}" />

    <div class="form-floating mb-3">
      <input type="text" name="category_name" class="form-control" id="category_name" value="${category.categoryName}" required />
      <label for="category_name">카테고리 이름</label>
    </div>

    <div class="d-flex justify-content-between">
      <a href="/admin/management.do" class="btn btn-outline-secondary">취소</a>
      <button type="submit" class="btn btn-primary">수정 완료</button>
    </div>
  </form>
</div>
