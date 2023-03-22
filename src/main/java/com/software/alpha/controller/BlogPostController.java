package com.software.alpha.controller;

import com.software.alpha.model.BlogPost;
import com.software.alpha.service.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class BlogPostController {

    @Autowired
    private BlogPostService blogPostService;

    @PostMapping("/blog/post")
    public String createPost(@ModelAttribute("post") BlogPost post,
                                             @RequestParam(value = "file") MultipartFile image) {

        return blogPostService.createBlog(post, image);
    }

    @PostMapping("/blog/post/delete/{id}")
    public String deletePost(@PathVariable long id) {
        return blogPostService.deleteBlogById(id);
    }

    @GetMapping("/blog/post/retr/{id}")
    public String retrievePost(Model model, @PathVariable long id) {
        return blogPostService.retrieveBlogById(model, id);
    }

    @GetMapping("/blog/photo/{id}")
    public ResponseEntity getPhoto(@PathVariable long id){
        return blogPostService.getPhoto(id);
    }
}
