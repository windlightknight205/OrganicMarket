package com.project.shopapp.services.order;

import com.project.shopapp.dtos.order.OrderDTO;
import com.project.shopapp.dtos.order.RevenueDTO;
import com.project.shopapp.dtos.order.RevenueDayDTO;
import com.project.shopapp.exceptions.DataNotFoundException;
import com.project.shopapp.models.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IOrderService {
    Order createOrder(OrderDTO orderDTO) throws Exception;
    Order getOrder(Long id);
    Order updateOrder(Long id, OrderDTO orderDTO) throws DataNotFoundException;
    void deleteOrder(Long id);
    List<Order> findByUserId(Long userId);
    Page<Order> getOrdersByKeyword(String keyword, Pageable pageable);
    List<RevenueDTO> getMonthlyRevenue(int year);
    List<RevenueDayDTO> getDailyRevenueByMonth(int month, int year);
}
