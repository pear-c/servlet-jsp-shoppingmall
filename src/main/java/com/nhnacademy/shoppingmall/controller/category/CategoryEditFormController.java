package com.nhnacademy.shoppingmall.controller.category;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.entity.category.domain.Category;
import com.nhnacademy.shoppingmall.entity.category.repository.Impl.CategoryRepositoryImpl;
import com.nhnacademy.shoppingmall.entity.category.service.CategoryService;
import com.nhnacademy.shoppingmall.entity.category.service.Impl.CategoryServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RequestMapping(method = RequestMapping.Method.GET, value = "/admin/category/edit.do")
public class CategoryEditFormController implements BaseController {

    private final CategoryService categoryService = new CategoryServiceImpl(new CategoryRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        int categoryId = Integer.parseInt(req.getParameter("categoryId"));
        Category category = categoryService.getCategory(categoryId);
        req.setAttribute("category", category);

        return "admin/category-edit";
    }
}
