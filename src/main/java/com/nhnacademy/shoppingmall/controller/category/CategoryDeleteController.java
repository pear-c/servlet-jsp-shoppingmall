package com.nhnacademy.shoppingmall.controller.category;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.entity.category.domain.Category;
import com.nhnacademy.shoppingmall.entity.category.repository.Impl.CategoryRepositoryImpl;
import com.nhnacademy.shoppingmall.entity.category.service.CategoryService;
import com.nhnacademy.shoppingmall.entity.category.service.Impl.CategoryServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@RequestMapping(method = RequestMapping.Method.POST, value = "/admin/category/delete.do")
public class CategoryDeleteController implements BaseController {

    private final CategoryService categoryService = new CategoryServiceImpl(new CategoryRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        int categoryId = Integer.parseInt(req.getParameter("category_id"));
        try {
            categoryService.deleteCategory(categoryId);
        } catch (RuntimeException e) {
            HttpSession session = req.getSession();
            session.setAttribute("categoryDeleteError", "해당 카테고리에 속한 상품이 있습니다.");
        }

        return "redirect:/admin/management.do";
    }
}
