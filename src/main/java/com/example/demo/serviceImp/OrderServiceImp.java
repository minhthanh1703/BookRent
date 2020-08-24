package com.example.demo.serviceImp;

import com.example.demo.constant.Constant;
import com.example.demo.constant.Message;
import com.example.demo.dto.ItemOrderDTO;
import com.example.demo.dto.ItemsOrderRequestDTO;
import com.example.demo.entities.Book;
import com.example.demo.entities.Order;
import com.example.demo.entities.OrderDetails;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.OrderDetailRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.service.OrderService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImp implements OrderService {

    private static final Logger logger = Logger.getLogger(OrderServiceImp.class);

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    BookRepository bookRepository;

    @Override
    public List<Order> getOrderByUser(int userId) throws Exception {
        logger.info(Constant.BEGIN_SERVICE + "getOrderByUser");
        try {
            List<Order> orderListByUser = orderRepository.getOrderByUserId(userId);
            return orderListByUser;
        } finally {
            logger.info(Constant.END_SERVICE + "getOrderByUser");
        }
    }

    @Transactional
    @Override
    public void createOrder(ItemsOrderRequestDTO itemsOrderRequestDTO) throws Exception {
        logger.info(Constant.BEGIN_SERVICE + "createOrder");
        try {

            if (itemsOrderRequestDTO.getUserId() == 0) {
                throw new Exception(Message.USER_NOT_VALID);
            }
            if (itemsOrderRequestDTO.getListItem() == null || itemsOrderRequestDTO.getListItem().size() == 0) {
                throw new Exception(Message.LIST_ORDER_NOT_VALID);
            }

            List<Book> books = bookRepository.findAll();
            for(Book book : books){
                for (ItemOrderDTO dto: itemsOrderRequestDTO.getListItem()){
                    if(book.getQuantity() >= dto.getQuantity()){
                        book.setQuantity(book.getQuantity() - dto.getQuantity());
                        bookRepository.save(book);
                    }else{
                        throw new Exception(Message.BOOK_OUT_OF_STOCK);
                    }
                }
            }


            Order order = new Order();
            List<Order> orderListByUser = orderRepository.getOrderByUserIdAndStatusActiveOrderByCreateAtDesc(itemsOrderRequestDTO.getUserId(), true);
            if(orderListByUser == null || orderListByUser.size()==0){
                order.setUserId(itemsOrderRequestDTO.getUserId());
                order.setCreateAt(new Timestamp(new Date().getTime()));
                order.setStatusActive(true);
                orderRepository.save(order);
            }else{
                order = orderListByUser.get(0);
            }

            List<OrderDetails> orderDetailsList = orderDetailRepository.getAllByOrderId(order.getId());



            if(orderDetailsList.size() == 0){
                for(ItemOrderDTO itemDTO : itemsOrderRequestDTO.getListItem()){
                    OrderDetails orderDetail = new OrderDetails();
                    orderDetail.setBookId(itemDTO.getItemId());
                    orderDetail.setOrderId(order.getId());
                    orderDetail.setQuantity(itemDTO.getQuantity());
                    orderDetail.setStatusActive(true);
                    orderDetail.setCreateAt(new Timestamp(new Date().getTime()));
                    orderDetailRepository.save(orderDetail);
                }
            }else{
                for(ItemOrderDTO itemDTO : itemsOrderRequestDTO.getListItem()){
                    int count = 0;
                    for(OrderDetails orderDetails : orderDetailsList){
                        if(itemDTO.getItemId() == orderDetails.getBookId()){
                            orderDetails.setQuantity(orderDetails.getQuantity() + itemDTO.getQuantity());
                            orderDetails.setUpdateAt(new Timestamp(new Date().getTime()));
                            orderDetailRepository.save(orderDetails);
                            break;
                        }else{
                            count++;
                        }
                    }
                    if(count == orderDetailsList.size()){
                        OrderDetails orderDetail = new OrderDetails();
                        orderDetail.setBookId(itemDTO.getItemId());
                        orderDetail.setOrderId(order.getId());
                        orderDetail.setQuantity(itemDTO.getQuantity());
                        orderDetail.setStatusActive(true);
                        orderDetail.setCreateAt(new Timestamp(new Date().getTime()));
                        orderDetailRepository.save(orderDetail);
                    }
                }
            }
        } finally {
            logger.info(Constant.END_SERVICE + "createOrder");
        }
    }

    @Override
    public Order updateOrder(Order order) throws Exception {
        return null;
    }

    @Override
    public Order getOrderByUserLastest(int userId) throws Exception {
        logger.info(Constant.BEGIN_SERVICE + "getOrderByUser");

        try {
//            List<Order> listOrderByUser = orderRepository.getOrderByUserIdOrderByCreateAtDesc(userId);

        } finally {
            logger.info(Constant.END_SERVICE + "getOrderByUser");
        }
        return null;
    }
}
