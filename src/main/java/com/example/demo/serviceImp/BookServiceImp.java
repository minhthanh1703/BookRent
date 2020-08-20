package com.example.demo.serviceImp;

import com.example.demo.constant.Constant;
import com.example.demo.constant.Message;
import com.example.demo.entities.Book;
import com.example.demo.entities.Category;
import com.example.demo.entities.User;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.BookService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BookServiceImp implements BookService {
    private static final Logger logger = Logger.getLogger(BookServiceImp.class);

    @Autowired
    BookRepository bookRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Book> getAllBooks() throws Exception {
        logger.info(Constant.BEGIN_SERVICE + "getAllBooks");
        try{
            List<Book> bookList = new ArrayList<>();
            bookList = bookRepository.findAll();
            return bookList;
        }finally {
            logger.info(Constant.END_SERVICE + "getAllBooks");
        }
    }

    @Override
    public Book createBook(Book book) throws Exception {
        logger.info(Constant.BEGIN_SERVICE + "createBook");
        try{
            if(bookRepository.findByName(book.getName()) != null){
                throw new Exception(Message.BOOK_EXIST);
            }else {
                book.setName(book.getName());
                book.setPrice(book.getPrice());
                book.setAuthor(book.getAuthor());
                book.setQuantity(book.getQuantity());
                book.setDetails(book.getDetails());
                book.setCreateAt(new Timestamp(new Date().getTime()));
                book.setStatusActive(true);
                Category category = new Category();
                List<Category> categoryList = categoryRepository.findAll();
                for (int i = 0; i < categoryList.size(); i++) {
                    category = categoryRepository.getByName(categoryList.get(i).getName());
                    if(category.getId() == book.getCategoryId()){
                        book.setCategoryId(category.getId());
                    }
                }
                bookRepository.save(book);
                return book;
            }
        }finally {
            logger.info(Constant.END_SERVICE + "createBook");
        }

    }

    @Override
    public Book updateBook(Book book) throws Exception {
        logger.info(Constant.BEGIN_SERVICE + "updateBook");
        try{
            if(bookRepository.findById(book.getId()) == null) {
                throw new Exception(Message.BOOK_NOT_EXIST);
            }else {
                Book bookUpdate = bookRepository.findById(book.getId());
                bookUpdate.setName(book.getName());
                bookUpdate.setPrice(book.getPrice());
                bookUpdate.setAuthor(book.getAuthor());
                bookUpdate.setDetails(book.getDetails());
                bookUpdate.setQuantity(book.getQuantity());
                bookUpdate.setUpdateAt(new Timestamp(new Date().getTime()));
                bookUpdate.setStatusActive(book.isStatusActive());
                bookUpdate.setCategoryId(book.getCategoryId());

                bookRepository.save(bookUpdate);
                return book;
            }
        }finally {
            logger.info(Constant.END_SERVICE + "updateBook");
        }
    }
    private Book changeStatus(Book book) {
        if (book.isStatusActive()) {
            book.setStatusActive(false);
        } else {
            book.setStatusActive(true);
        }
        return book;
    }
    @Override
    public Book disableBook(int bookId) throws Exception {
        logger.info(Constant.BEGIN_SERVICE + "disableBook");
        try {
            Book book = bookRepository.findById(bookId);
            if (book == null) {
                throw new Exception(Message.BOOK_NOT_EXIST);
            } else {
                if (book.isStatusActive()) {
                    book = changeStatus(book);
                }
                bookRepository.save(book);
                return book;
            }
        } finally {
            logger.info(Constant.END_SERVICE + "disableUser");
        }
    }

    @Override
    public Book enableBook(int bookId) throws Exception {
        logger.info(Constant.BEGIN_SERVICE + "disableBook");
        try {
            Book book = bookRepository.findById(bookId);
            if (book == null) {
                throw new Exception(Message.BOOK_NOT_EXIST);
            } else {
                if (!book.isStatusActive()) {
                    book = changeStatus(book);
                }
                bookRepository.save(book);
                return book;
            }
        } finally {
            logger.info(Constant.END_SERVICE + "disableUser");
        }
    }
}
