package com.software.alpha.service;

import com.software.alpha.model.BlogPost;
import com.software.alpha.model.CachedVideo;
import com.software.alpha.repository.CachedVideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class TemplateServiceImpl implements TemplateService {

    @Autowired
    private CacheService cacheService;

    @Autowired
    private BlogPostService blogPostService;

    private final String INDEX_TEMPLATE = "index";
    private final String ADMIN_TEMPLATE = "admin";
    private final String ADMIN_USER_NAME = System.getenv("admin_user_name");
    private final String ADMIN_USER_ROLE_LIST = System.getenv("admin_user_role_list");

    @Override
    public String getIndex(Model model) {

        model.addAttribute("userAuthenticated", userAuthenticated());

        List<CachedVideo> cachedVideos = cacheService.getCachedVideos().getBody();
        if (cachedVideos == null) return INDEX_TEMPLATE;
        model.addAttribute("cachedVideos", cachedVideos);

        List<BlogPost> blogPosts = blogPostService.getBlogs().getBody();
        Collections.reverse(blogPosts);

        if (blogPosts == null) return INDEX_TEMPLATE;
        model.addAttribute("blogs", blogPosts);

        return INDEX_TEMPLATE;
    }

    @Override
    public String getAdmin(Model model) {

        model.addAttribute("userAuthenticated", userAuthenticated());

        List<BlogPost> blogPosts = blogPostService.getBlogs().getBody();

        if (blogPosts == null) return ADMIN_TEMPLATE;
        model.addAttribute("blogs", blogPosts);

        return ADMIN_TEMPLATE;
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
