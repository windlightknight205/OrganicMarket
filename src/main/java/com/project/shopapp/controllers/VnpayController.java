package com.project.shopapp.controllers;
import com.project.shopapp.services.vnpay.VNPayService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URLEncoder;

@RestController
@RequestMapping("${api.prefix}")
//@Validated
//Dependency Injection
@RequiredArgsConstructor
public class VnpayController {
    private final VNPayService vnPayService;
    @PostMapping("/vnpay")
    public String submitOrder(@RequestParam("orderTotal") int orderTotal,
                              @RequestParam("orderInfo") String orderInfo,
                              HttpServletRequest request){
        String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        String vnpayUrl = vnPayService.createOrder(orderTotal, orderInfo, baseUrl);
        return vnpayUrl;
    }
    @GetMapping("/vnpay-payment")
    public void GetMapping(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int paymentStatus = vnPayService.orderReturn(request);

        String orderInfo = request.getParameter("vnp_OrderInfo");
        String paymentTime = request.getParameter("vnp_PayDate");
        String transactionId = request.getParameter("vnp_TransactionNo");
        String totalPrice = request.getParameter("vnp_Amount");

        String redirectUrl = "http://localhost:4200/payment" +
                "?status=" + paymentStatus +
                "&orderId=" + URLEncoder.encode(orderInfo, "UTF-8") +
                "&totalPrice=" + URLEncoder.encode(totalPrice, "UTF-8") +
                "&paymentTime=" + URLEncoder.encode(paymentTime, "UTF-8") +
                "&transactionId=" + URLEncoder.encode(transactionId, "UTF-8");

        response.sendRedirect(redirectUrl);
    }
}
