package com.example.demo.controller;

import com.example.demo.constant.Constant;
import com.example.demo.dto.ServiceResponseDTO;
import com.example.demo.entities.Book;
import com.example.demo.service.BookService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Constant.API)
public class BookController {
    private static final Logger logger = Logger.getLogger(BookController.class);

    @Autowired
    BookService bookService;

    @GetMapping(Constant.BOOK)
    public ResponseEntity findAllBook(){
        logger.info(Constant.BEGIN_CONTROLLER + "findAllBook");
        ServiceResponseDTO response = new ServiceResponseDTO();
        try{
            List<Book> listBook = bookService.getAllBooks();
            response.setData(listBook);
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e);
            response.setMessage(e.getMessage());
            response.setStatus(ServiceResponseDTO.Status.FAILED);
            return new ResponseEntity(response,HttpStatus.BAD_REQUEST);
        }finally {
            logger.info(Constant.END_CONTROLLER + "findAllBook");
        }
    }

    @GetMapping(Constant.BOOK +"/page")
    public ResponseEntity findAllBook(@RequestParam int page, @RequestParam int size){
        logger.info(Constant.BEGIN_CONTROLLER + "findAllBook");
        ServiceResponseDTO response = new ServiceResponseDTO();
        try{
            Page<Book> listBook = bookService.getAllBooks(page, size);
            response.setData(listBook);
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e);
            response.setMessage(e.getMessage());
            response.setStatus(ServiceResponseDTO.Status.FAILED);
            return new ResponseEntity(response,HttpStatus.BAD_REQUEST);
        }finally {
            logger.info(Constant.END_CONTROLLER + "findAllBook");
        }
    }


    @PostMapping(Constant.BOOK)
    public ResponseEntity createBook(@RequestBody Book book){
        logger.info(Constant.BEGIN_CONTROLLER + "createBook");
        ServiceResponseDTO response = new ServiceResponseDTO();
        try{
            bookService.createBook(book);
            response.setMessage("Created");
            return new ResponseEntity(response, HttpStatus.OK);
        }catch (Exception e){
            logger.error(e);
            response.setMessage(e.getMessage());
            response.setStatus(ServiceResponseDTO.Status.FAILED);
            return new ResponseEntity(response,HttpStatus.BAD_REQUEST);
        }finally {
            logger.info(Constant.END_CONTROLLER + "createBook");
        }
    }

    @PutMapping(Constant.BOOK)
    public ResponseEntity updateBook(@RequestBody Book book){
        logger.info(Constant.BEGIN_CONTROLLER + "updateBook");
        ServiceResponseDTO response = new ServiceResponseDTO();
        try{
            bookService.updateBook(book);
            response.setMessage("Updated");
            return new ResponseEntity(response, HttpStatus.OK);
        }catch (Exception e){
            logger.error(e);
            response.setMessage(e.getMessage());
            response.setStatus(ServiceResponseDTO.Status.FAILED);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }finally {
            logger.info(Constant.END_CONTROLLER + "updateBook");
        }
    }

    @PutMapping(Constant.BOOK + Constant.DISABLE + "/{id}")
    public ResponseEntity disableBook(@PathVariable int id){
        logger.info(Constant.BEGIN_CONTROLLER + "disableBook");
        ServiceResponseDTO response = new ServiceResponseDTO();
        try{
            bookService.disableBook(id);
            response.setMessage("Disabled");
            return new ResponseEntity(response, HttpStatus.OK);
        }catch (Exception e){
            logger.error(e);
            response.setMessage(e.getMessage());
            response.setStatus(ServiceResponseDTO.Status.FAILED);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }finally {
            logger.info(Constant.END_CONTROLLER + "disableBook");
        }
    }

    @PutMapping(Constant.BOOK + Constant.ENABLE + "/{id}")
    public ResponseEntity enableBook(@PathVariable int id){
        logger.info(Constant.BEGIN_CONTROLLER + "enableBook");
        ServiceResponseDTO response = new ServiceResponseDTO();
        try{
            bookService.enableBook(id);
            response.setMessage("Enabled");
            return new ResponseEntity(response, HttpStatus.OK);
        }catch (Exception e){
            logger.error(e);
            response.setMessage(e.getMessage());
            response.setStatus(ServiceResponseDTO.Status.FAILED);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }finally {
            logger.info(Constant.END_CONTROLLER + "enableBook");
        }
    }
}
