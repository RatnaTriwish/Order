package com.ratna.Order.Service;

import Dto.OrderRequestDto;
import Dto.OrderResponseDto;
import Dto.OrderUpdateDto;
import com.ratna.Order.Model.OrderModel;
import com.ratna.Order.Repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;


    private OrderModel convertedOrderModel(OrderRequestDto orderRequestDto) {

        OrderModel orderModel = new OrderModel();
        orderModel.setAdvanceAmount(orderRequestDto.getAdvanceAmount());
        orderModel.setAgentCode(orderRequestDto.getAgentCode());
        orderModel.setCustomerCode(orderRequestDto.getCustomerCode());
        orderModel.setDate(orderRequestDto.getDate());
        orderModel.setOrderDescription(orderRequestDto.getOrderDescription());
        orderModel.setOrderAmount(orderRequestDto.getOrderAmount());
        return orderModel;
    }

    private OrderResponseDto transformOrderResponse(OrderModel orderModel) {

        OrderResponseDto orderResponseDto = new OrderResponseDto();

        orderResponseDto.setAdvanceAmount(orderModel.getAdvanceAmount());
        orderResponseDto.setOrderAmount(orderModel.getOrderAmount());
        orderResponseDto.setAgentCode(orderModel.getAgentCode());
        orderResponseDto.setCustomerCode(orderModel.getCustomerCode());
        orderResponseDto.setDate(orderModel.getDate());
        orderResponseDto.setOrderDescription(orderModel.getOrderDescription());
        orderResponseDto.setId(orderModel.getId());
        return orderResponseDto;
    }

    public OrderResponseDto add(OrderRequestDto orderRequestDto) {
        //convertrequest to model
        OrderModel orderModel = convertedOrderModel(orderRequestDto);
        // save model
        OrderModel orderResponseDto = orderRepo.save(orderModel);
        //savedmodel transform to response
        OrderResponseDto orderResponseDto1 = transformOrderResponse(orderResponseDto);

        return orderResponseDto1;
    }

    public Page<OrderResponseDto> getAll(Pageable pageable, String search, String sort, String customerCode) {

        Page<OrderModel> orderModels;
        if (search.equalsIgnoreCase("none")) {
            orderModels = orderRepo.findAll(pageable);
        } else {
            orderModels = orderRepo.findByAllCustomerName(search, pageable);
            orderModels = orderRepo.findByAllByCustomerNameContains(search, pageable);

        }
        final Page<OrderResponseDto> responseDtos = orderModels.map(this::transformOrderResponse);
        return responseDtos;
    }

    public void deleteById(Long id) {
        try {
            OrderModel orderModel = orderRepo.findById(id)
                    .orElseThrow(() -> new Exception("order id not found"));
            orderRepo.delete(orderModel);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public OrderResponseDto update(OrderUpdateDto orderUpdateDto) {

        try {
            OrderModel orderModel = orderRepo.findById(orderUpdateDto.getId())
                    .orElseThrow(() -> new Exception("order id not found"));
            orderModel.setAgentCode(orderUpdateDto.getAgentCode());
            OrderModel orderResponseDto = orderRepo.save(orderModel);
            return transformOrderResponse(orderResponseDto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public OrderResponseDto orderGetById(Long id) {
        try {
            OrderModel orderModel = orderRepo.findById(id)
                    .orElseThrow(() -> new Exception("order id not found"));
            OrderModel orderModel1 = orderRepo.save(orderModel);
            return transformOrderResponse(orderModel1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /*public OrderResponseDto update(OrderRequestDto orderRequestDto){

        try{
            OrderModel orderModel = orderRepo.findById(orderRequestDto.getId())
                    .orElseThrow(() -> new Exception("order id not found"));
                    orderModel.setId(orderRequestDto.getId());
            OrderModel orderModel1 = orderRepo.save(orderModel);
            return transformOrderResponse(orderModel1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }*/
}

