package com.ratna.Order.Repository;

import Dto.OrderResponseDto;
import com.ratna.Order.Model.OrderModel;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface OrderRepo extends PagingAndSortingRepository<OrderModel, Long> {

    List<OrderModel> findByOrderAmountAndAdvanceAmount(Long orderAmount, Long advanceAmount);
    Page<OrderModel> findAll(Pageable pageable);
    Page<OrderModel> findByAllCustomerName(String customerCode,  Pageable pageable);

    Page<OrderModel> findByAllByCustomerNameContains(String search,  Pageable pageable);


}
