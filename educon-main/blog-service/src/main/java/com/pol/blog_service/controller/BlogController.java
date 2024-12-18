package com.pol.blog_service.controller;

import com.pol.blog_service.dto.blog.BlogPageResponseDTO;
import com.pol.blog_service.dto.blog.BlogRequestDTO;
import com.pol.blog_service.dto.blog.BlogResponseDTO;
import com.pol.blog_service.entity.Blog;
import com.pol.blog_service.service.blog.BlogService;
import com.pol.blog_service.service.blog.BlogServiceImpl;
import com.pol.blog_service.utils.AppConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/blogs")
public class BlogController {

    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping("/hello")
    public String hello(){
        return "Hello Guest users";
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlogResponseDTO> getBlogById(@PathVariable UUID id){
        return ResponseEntity.ok(blogService.getBlogById(id));
    }

    @GetMapping
    public ResponseEntity<BlogPageResponseDTO> getAllBlogs(
            @RequestParam(defaultValue = AppConstants.PAGE,required = false) int page,
            @RequestParam(defaultValue = AppConstants.SIZE,required = false) int size,
            @RequestParam(defaultValue = AppConstants.SORT_BY_BLOG_PUBLISHED_AT,required = false) String sortBy,
            @RequestParam(defaultValue = AppConstants.ORDER,required = false) String order
    ){
        return ResponseEntity.ok(blogService.getAllBlogs(page,size,sortBy,order));
    }

    @GetMapping("/search")
    public ResponseEntity<BlogPageResponseDTO> searchBlogsByKeyword(
            @RequestParam("keyword") String keyword,
            @RequestParam(defaultValue = AppConstants.PAGE,required = false) int page,
            @RequestParam(defaultValue = AppConstants.SIZE,required = false) int size,
            @RequestParam(defaultValue = AppConstants.SORT_BY_BLOG_PUBLISHED_AT,required = false) String sortBy,
            @RequestParam(defaultValue = AppConstants.ORDER,required = false) String order
    ){
        return ResponseEntity.ok(blogService.searchBlogsByKeyword(keyword,page,size,sortBy,order));
    }
}
