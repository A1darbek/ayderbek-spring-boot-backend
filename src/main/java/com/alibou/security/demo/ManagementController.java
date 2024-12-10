package com.alibou.security.demo;

import com.alibou.security.service.OrderService;
import com.alibou.security.service.ProductService;
import com.alibou.security.user.OrderRequest;
import com.alibou.security.user.Product;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/management")
@Tag(name = "Management")
@RequiredArgsConstructor
public class ManagementController {

    private final OrderService orderService;
    private final ProductService productService;

    @Operation(
            description = "Get endpoint for manager",
            summary = "This is a summary for management get endpoint",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "403"
                    )
            }

    )

    @PostMapping("/make")
    public ResponseEntity<String> makeOrder(@RequestBody OrderRequest orderRequest) {
        boolean success = orderService.placeOrder(orderRequest);
        if (success) {
            return new ResponseEntity<>("Order placed successfully!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to place order.", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }



    @GetMapping
    public String get() {
        return "GET:: management controller";
    }

    @PostMapping
    public String post() {
        return "POST:: management controller";
    }
    @PutMapping
    public String put() {
        return "PUT:: management controller";
    }
    @DeleteMapping
    public String delete() {
        return "DELETE:: management controller";
    }
}
