package com.nhnacademy.shoppingmall.controller.category;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.entity.category.repository.Impl.CategoryRepositoryImpl;
import com.nhnacademy.shoppingmall.entity.category.service.CategoryService;
import com.nhnacademy.shoppingmall.entity.category.service.Impl.CategoryServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@RequestMapping(method = RequestMapping.Method.POST, value = "/admin/category/create.do")
public class CategoryCreateController implements BaseController {

    private final CategoryService categoryService = new CategoryServiceImpl(new CategoryRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String categoryName = req.getParameter("category_name");

        try {
            categoryService.saveCategoryByName(categoryName);
        } catch (RuntimeException e) {
            HttpSession session = req.getSession();
            session.setAttribute("categoryCreateError", "이미 존재하는 카테고리 입니다.");
        }
        return "redirect:/admin/management.do";
    }
}
