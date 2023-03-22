package com.software.alpha.service;

import com.software.alpha.model.BlogPost;
import com.software.alpha.model.GenericResponse;
import com.software.alpha.repository.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
public class BlogPostServiceImpl implements BlogPostService {

    @Autowired
    private BlogPostRepository blogPostRepository;

    private final String ADMIN_TEMPLATE_REDIRECT = "redirect:/admin#upload";
    private final String BLOG_POST_TEMPLATE = "post";
    private final String ADMIN_USER_NAME = System.getenv("admin_user_name");
    private final String ADMIN_USER_ROLE_LIST = System.getenv("admin_user_role_list");

    @Override
    public String deleteBlogById(long id){
        blogPostRepository.deleteById(id);
        return ADMIN_TEMPLATE_REDIRECT;
    }

    @Override
    public String retrieveBlogById(Model model, long id) {

        model.addAttribute("userAuthenticated", userAuthenticated());

        if (blogPostRepository.findById(id).isPresent() == false)
            return BLOG_POST_TEMPLATE;


        BlogPost blogPost = blogPostRepository.findById(id).get();
        model.addAttribute("blogPost", blogPost);

        return BLOG_POST_TEMPLATE;
    }

    @Override
    public String createBlog(BlogPost post, MultipartFile image) {
        String fileName = StringUtils.cleanPath(image.getOriginalFilename());
        post.setPhotoName(fileName);
        try{
            post.setPhotoData(image.getBytes());
        } catch (IOException io){
            io.printStackTrace();
        }

        post.setCreated(new Date());
        post.setLastUpdated(new Date());

        blogPostRepository.save(post);

        return ADMIN_TEMPLATE_REDIRECT;
    }

    @Override
    public ResponseEntity getPhoto(long id) {

        BlogPost blogPost = blogPostRepository.findById(id).get();

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(blogPost.getPhotoData());

    }

    @Override
    public ResponseEntity<List<BlogPost>> getBlogs() {

        return ResponseEntity.status(HttpStatus.OK).
                body(blogPostRepository.findAll());
    }

    private boolean userAuthenticated() {
        Authentication user = SecurityContextHolder.getContext().getAuthentication();

        String userName = user.getName();
        Collection<? extends GrantedAuthority> roles = user.getAuthorities();
        boolean authenticated = false;

        if (userName.equals(ADMIN_USER_NAME) && roles.toArray()[0].toString().equals(ADMIN_USER_ROLE_LIST))
            authenticated = true;

        return authenticated;
    }
}
