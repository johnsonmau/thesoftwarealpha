package com.software.alpha.service;

import com.software.alpha.model.BlogPost;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

public interface BlogPostService {

    String createBlog(BlogPost blogPost, MultipartFile image);
    String deleteBlogById(long id);
    String retrieveBlogById(Model model, long id);
    ResponseEntity getPhoto(long id);
    ResponseEntity<List<BlogPost>> getBlogs();

}
