package com.example.warehousemanagement.repository;

import com.example.warehousemanagement.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

    @Query(value = "select id, product_id, order_id, sum(quantity) as quantity, sum(total_price) as total_price from order_detail\n" +
            "group by product_id order by quantity desc limit 10",
            nativeQuery = true)
    List<OrderDetail> getTop10BestSellingProduct();

}
