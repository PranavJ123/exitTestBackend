package com.nagarro.reviewSystem.controller;

import com.nagarro.reviewSystem.model.*;
import com.nagarro.reviewSystem.services.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class AppController {
    @Autowired
    private UserService userService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ReviewRequestService reviewRequestService;
    @Autowired
    private ReviewService reviewService;

    @GetMapping("/test")
    public String Test(){
        return "testing";
    }

    @PostMapping("/user")
    public User getUserById(@RequestBody @NotNull UserRequest request) {
        return userService.getUserById(request.getUid());
    }

    @PostMapping("/auth")
    public AuthResult auth(@RequestBody User user){
        return userService.auth(user);
    }
    @PostMapping("/addUser")
    public AuthResult addUser(@RequestBody @NotNull User user) {
        String password = user.getPassword();
        User saveUser = null;
        AuthResult response = new AuthResult();
        saveUser = userService.addUser(user);
        response.setUser(saveUser);
        response.setMessage(new Message("User Created Successfully", 200));
        return response;
    }

    @PostMapping("/adminAuth")
    public AdminAuthResult auth(@RequestBody Admin admin){
        return adminService.auth(admin);
    }

    @PostMapping("/product")
    public Message addProduct(@RequestBody ProductRequest product) {
        return productService.addProduct(product);
    }

    @GetMapping("/product")
    public List<ProductRequest> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("/getProductById")
    public ProductRequest getProductById(@RequestParam String id) {
        ProductRequest result = null;
        try {
            result = productService.getProductById(Integer.parseInt(id));
        }
        catch (Exception e) {}
        return result;
    }



    @GetMapping("/products")
    public List<ProductRequest> getProductsByCriteria(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String code,
            @RequestParam(required = false) String brand) {

        List<ProductRequest> productData = productService.getProductsByCriteria(name, code, brand);

        return productData;
    }

    @PostMapping("/reviewRequest/request")
    public Message requestReview(@RequestBody ReviewRequest request){
        return reviewRequestService.request(request);
    }

    @PostMapping("/reviewRequest/approve")
    public Message approveRequestReview(@RequestBody ReviewRequest request){
        return reviewRequestService.approve(request);
    }

    @PostMapping("/reviewRequest/reject")
    public Message rejectRequestReview(@RequestBody ReviewRequest request){
        return reviewRequestService.reject(request);
    }

    @PostMapping("/reviewRequest/statusById")
    public ReviewRequestStatus getReviewRequestStatusById(@RequestBody @NotNull ReviewRequest request){
        return reviewRequestService.getStatusById(request.getId());
    }

    @PostMapping("/reviewRequest/status")
    public List<ReviewRequest> getReviewRequestStatus(@RequestBody @NotNull ReviewRequest request){
        return reviewRequestService.status(request.getUid());
    }
    
    @GetMapping("/reviewRequest")
    public List<ReviewRequest> getReviewRequests(){
        return reviewRequestService.getAllRequests();
    }
    
    @GetMapping("/review")
    public List<Review> getReviews(){
        return reviewService.getAllReviews();
    }

    @PostMapping("/review/request")
    public Message requestReview(@RequestBody ReviewUpdateRequest review){
        return reviewService.request(review);
    }

    @PostMapping("/review/approve")
    public Message approveReview(@RequestBody Review review){
        return reviewService.approve(review);
    }

    @PostMapping("/review/reject")
    public Message rejectReview(@RequestBody Review review){
        return reviewService.reject(review);
    }

    @PostMapping("/review/statusById")
    public ReviewStatus getReviewRequestStatusById(@RequestBody @NotNull Review review){
        return reviewService.getStatusById(review.getId());
    }

    @PostMapping("/review/getById")
    public Review getReviewById(@RequestBody @NotNull Review review) {
        return reviewService.getReviewById(review.getId());
    }

    @PostMapping("/review/status")
    public List<Review> getReviewRequestStatus(@RequestBody @NotNull UserRequest request){
        return reviewService.status(request.getUid());
    }

    @GetMapping("/review/getByProduct")
    public List<Review> getByProductId(@RequestParam long productId) {
        return reviewService.getReviewsByProductId(productId);
    }

    @GetMapping("/stats")
    public Stats getStats(){
        Stats stats = new Stats(userService.totalUsers(), productService.totalProducts(),
                reviewService.totalReviews());
        return stats;
    }

}
