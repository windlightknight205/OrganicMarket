package com.project.shopapp.repositories;

import com.project.shopapp.dtos.order.RevenueDTO;
import com.project.shopapp.dtos.order.RevenueDayDTO;
import com.project.shopapp.models.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    //Tìm các đơn hàng của 1 user nào đó
    List<Order> findByUserId(Long userId);
    @Query("SELECT o FROM Order o WHERE o.active = true AND (:keyword IS NULL OR :keyword = '' OR " +
            "o.fullName LIKE %:keyword% " +
            "OR o.address LIKE %:keyword% " +
            "OR o.note LIKE %:keyword% " +
            "OR o.email LIKE %:keyword%)")
    Page<Order> findByKeyword(@Param("keyword") String keyword, Pageable pageable);

    @Query("SELECT new com.project.shopapp.dtos.order.RevenueDTO(MONTH(o.orderDate), SUM(o.totalMoney)) " +
            "FROM Order o WHERE o.status = 'delivered' AND YEAR(o.orderDate) = :year GROUP BY MONTH(o.orderDate) ORDER BY MONTH(o.orderDate)")
    List<RevenueDTO> findMonthlyRevenue(@Param("year") int year);

    @Query("SELECT new com.project.shopapp.dtos.order.RevenueDayDTO(DAY(o.orderDate), SUM(o.totalMoney)) " +
            "FROM Order o WHERE o.status = 'delivered' AND MONTH(o.orderDate) = :month " +
            "AND YEAR(o.orderDate) = :year GROUP BY DAY(o.orderDate) ORDER BY DAY(o.orderDate)")
    List<RevenueDayDTO> findDailyRevenueByMonth(@Param("month") int month, @Param("year") int year);
}
