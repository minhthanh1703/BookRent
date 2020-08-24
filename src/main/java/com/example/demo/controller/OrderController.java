package com.example.demo.controller;


import com.example.demo.constant.Constant;
import com.example.demo.dto.ItemsOrderRequestDTO;
import com.example.demo.dto.ServiceResponseDTO;
import com.example.demo.entities.Order;
import com.example.demo.service.OrderService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Constant.API)
public class OrderController {

    private static final Logger logger = Logger.getLogger(Order.class);

    @Autowired
    OrderService orderService;

    @PostMapping(Constant.ORDER)
    public ResponseEntity addOrder(@RequestBody ItemsOrderRequestDTO requestDTO) {
        logger.info(Constant.BEGIN_CONTROLLER + "addOrder");
        ServiceResponseDTO response = new ServiceResponseDTO();
        try {
            orderService.createOrder(requestDTO);
            response.setMessage("Order added");
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error(ex);
            response.setMessage(ex.getMessage());
            response.setStatus(ServiceResponseDTO.Status.FAILED);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        } finally {
            logger.info(Constant.END_CONTROLLER + "addOrder");
        }
    }


}
