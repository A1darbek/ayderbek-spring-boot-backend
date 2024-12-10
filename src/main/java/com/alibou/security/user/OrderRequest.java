package com.alibou.security.user;

import lombok.Data;

@Data
public class OrderRequest {
    private Integer userId;
    private Integer productId;
}
