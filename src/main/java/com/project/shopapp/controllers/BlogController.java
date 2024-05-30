package com.project.shopapp.controllers;

import com.project.shopapp.dtos.ProductDTO;
import com.project.shopapp.models.Blog;
import com.project.shopapp.models.Product;
import com.project.shopapp.services.blog.IBlogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("${api.prefix}/blog")
@RequiredArgsConstructor
public class BlogController {
//    private final IBlogService blogService;

//    @PostMapping("")
//    public ResponseEntity<?> createBlog(
//            @Valid @RequestBody Blog blog,
//            BindingResult result
//    ) {
//        try {
//            if(result.hasErrors()) {
//                List<String> errorMessages = result.getFieldErrors()
//                        .stream()
//                        .map(FieldError::getDefaultMessage)
//                        .toList();
//                return ResponseEntity.badRequest().body(errorMessages);
//            }
//            Blog newBlog = blogService.createProduct(blog);
//            return ResponseEntity.ok(newBlog);
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }
}
