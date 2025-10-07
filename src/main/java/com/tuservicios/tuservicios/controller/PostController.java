package com.tuservicios.tuservicios.controller;

import com.tuservicios.tuservicios.model.Post;
import com.tuservicios.tuservicios.payload.request.PostRequest;
import com.tuservicios.tuservicios.security.services.UserDetailsImpl;
import com.tuservicios.tuservicios.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts") // Base route for all publication endpoindt
@CrossOrigin(origins = "*", maxAge = 3600) // CORS configutation to allow request
public class PostController {

    @Autowired
    PostService postService;// Business layer injection

    // CREATE POST (post/api/post)

    @PostMapping
    @PreAuthorize("isAutheticated()") // Only authenticated users can create
    public ResponseEntity<Post> createPost(@Valid @RequestBody PostRequest postRequest, Authentication authentication){
        // Extracts the id of the currenttly logged in user
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Long userId = userDetails.getId();
        //Call the service  to create the post with the user ID
        Post newPost = postService.createPost(userId, postRequest);
        // Returns the created post with status code 201 CREATED
        return  new ResponseEntity<>(newPost, HttpStatus.CREATED);
    }
    //READ AND SEARCH GET
    //Get all active post Main feed: GET /api/post
    @GetMapping
    public  ResponseEntity<List<Post>> getAllPosts(){
        List<Post> posts = postService.getAllActivePosts();
        if (posts.isEmpty()){
            return  new ResponseEntity<>(HttpStatus.NO_CONTENT);// 204 if there are no post
        }
        return ResponseEntity.ok(posts); // 200 OK with the list
    }
    // Get a post by id (get /api/post/{id})
    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable long id){
        return postService.getPostById(id)
                .map(ResponseEntity::ok) // if found and active returns 200 ok
                .orElseGet(()-> new  ResponseEntity<>(HttpStatus.NOT_FOUND));// if not, return 404 not found
    }
    //Search by phone (GET /api/post/search/phone/{phone}
    @GetMapping("/search/phone/{phone}")
    public ResponseEntity<List<Post>> searchByPhone(@PathVariable String phone){
        List<Post> posts = postService.searchPostsByPhone(phone);
        if (posts.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(posts);
    }
    //Update Post (PUT /api/post/{id}
    @PutMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody PostRequest postRequest){
        Post updatePost = postService.updatePost(id, postRequest);
        return ResponseEntity.ok(updatePost);// 200 with the updated post
    }
    // DELETE POST (DELETE /api/posts/{id}-)
    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<HttpStatus> deletePost(@PathVariable long id){
        postService.deletePost(id);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }






}
