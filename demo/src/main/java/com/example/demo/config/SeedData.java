package com.example.demo.config;

import com.example.demo.Model.Authority;
import com.example.demo.Repositories.AuthorityRepository;
import com.example.demo.Model.Account;
import com.example.demo.Model.Post;
import com.example.demo.Service.AccountService;
import com.example.demo.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class SeedData implements CommandLineRunner {

    @Autowired
    private PostService postService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public void run (String... args) throws Exception{
        List<Post> post = postService.getAll();
        // Check if there are any posts in the database

        if (post.size() == 0){
            // Create Authority objects for ROLE_USER and ROLE_ADMIN
            Authority user = new Authority();
            user.setName("ROLE_USER");
            authorityRepository.save(user);

            Authority admin = new Authority();
            admin.setName("ROLE_ADMIN");
            authorityRepository.save(admin);

            // Create Account objects with associated authorities
            Account account1 = new Account();
            Account account2 = new Account();

            // Configuring details for account1
            account1.setFirstName("user");
            account1.setLastName("user");
            account1.setEmail("user.user@domain.com");
            account1.setPassword("password");
            Set<com.example.demo.Model.Authority> authorities1 = new HashSet<>();
            authorityRepository.findById("ROLE_USER").ifPresent(authorities1::add);
            account1.setAuthorities(authorities1);

            account2.setFirstName("admin_first");
            account2.setLastName("admin_last");
            account2.setEmail("admin.admin@domain.com");
            account2.setPassword("password");

            Set<com.example.demo.Model.Authority> authorities2 = new HashSet<>();
            authorityRepository.findById("ROLE_ADMIN").ifPresent(authorities2::add);
            authorityRepository.findById("ROLE_USER").ifPresent(authorities2::add);
            account2.setAuthorities(authorities2);

            // Save the account details
            accountService.save(account1);
            accountService.save(account2);


            // Create Post objects associated with respective accounts
            Post post1 = new Post();
            post1.setTitle("What is a Healthy Lifestyle?");
            post1.setBody("A healthy lifestyle is generally characterized as a “balanced life” in which one makes “wise choices”");
            post1.setAccount(account1);

            Post post2 = new Post();
            post2.setTitle("Title of Post 2");
            post2.setBody("This is the body of post 2");
            post2.setAccount(account2);

            postService.save(post1);
            postService.save(post2);
        }
    }
}
