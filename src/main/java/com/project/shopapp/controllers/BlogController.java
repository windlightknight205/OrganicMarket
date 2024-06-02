package com.project.shopapp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
