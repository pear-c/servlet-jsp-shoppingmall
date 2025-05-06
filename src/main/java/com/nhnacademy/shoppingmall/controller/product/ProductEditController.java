package com.nhnacademy.shoppingmall.controller.product;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.entity.product.domain.Product;
import com.nhnacademy.shoppingmall.entity.product.repository.impl.ProductRepositoryImpl;
import com.nhnacademy.shoppingmall.entity.product.service.ProductService;
import com.nhnacademy.shoppingmall.entity.product.service.impl.ProductServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.UUID;

@RequestMapping(method = RequestMapping.Method.POST, value = "/admin/product/edit.do")
public class ProductEditController implements BaseController {

    private final ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        int productId = Integer.parseInt(req.getParameter("product_id"));
        String productName = req.getParameter("product_name");
        int productPrice = Integer.parseInt(req.getParameter("product_price"));
        String explain = req.getParameter("product_explain");
        int categoryId = Integer.parseInt(req.getParameter("category_id"));
        int productStock = Integer.parseInt(req.getParameter("product_stock"));

        try {
            Part imagePart = req.getPart("product_image");
            String imagePath = null;
            if (imagePart != null && imagePart.getSize() > 0) {
                imagePath = saveImageToWebapp(imagePart, req);
            } else {
                imagePath = productService.getProduct(productId).getImagePath(); // 기존 이미지 유지
            }

            Product product = new Product(productId, categoryId, productName, productPrice, LocalDateTime.now(), imagePath, explain, productStock);
            productService.updateProduct(product);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return "redirect:/admin/management.do";
    }

    private String saveImageToWebapp(Part imagePart, HttpServletRequest req) throws IOException{
        String fileName = Path.of(imagePart.getSubmittedFileName()).getFileName().toString();
        if(fileName == null || fileName.isBlank()) {
            return null;
        }

        String ext = fileName.substring(fileName.lastIndexOf('.'));
        String newFileName = UUID.randomUUID() + ext;

        String realPath = req.getServletContext().getRealPath("/resources/images");
        File uploadDir = new File(realPath);
        if(!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        Path uploadPath = Path.of(uploadDir.getAbsolutePath(), newFileName);
        Files.copy(imagePart.getInputStream(), uploadPath, StandardCopyOption.REPLACE_EXISTING);

        return newFileName;
    }
}
