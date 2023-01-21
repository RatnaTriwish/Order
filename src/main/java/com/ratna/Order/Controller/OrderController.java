package com.ratna.Order.Controller;

import Dto.OrderRequestDto;
import Dto.OrderResponseDto;
import Dto.OrderUpdateDto;
import com.ratna.Order.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/orderModel")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    private OrderResponseDto createOrder(@RequestBody OrderRequestDto orderRequestDto) {
        return orderService.add(orderRequestDto);
    }

    @GetMapping("/all")
    private List<OrderResponseDto> getAllOrders(@PathParam(String search)) {
        return orderService.getAll();
    }

    @DeleteMapping("/{id}")
    private String orderDelete(@PathVariable("id") Long id) {
        orderService.deleteById(id);
        return "deleted successfully";
    }
    @PutMapping("/updateAgent")
    private OrderResponseDto updateAgent(@RequestBody OrderUpdateDto orderUpdateDto) {
        return orderService.update(orderUpdateDto);
    }
    @GetMapping("/{id}")
    private OrderResponseDto getById(@PathVariable("id") Long id){
        return orderService.orderGetById(id);
    }

}
