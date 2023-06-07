package com.nemo.bookservice.service;

import com.nemo.bookservice.dto.BookRequest;
import com.nemo.bookservice.dto.BookResponse;
import com.nemo.bookservice.entity.Book;
import com.nemo.bookservice.entity.BookCategory;
import com.nemo.bookservice.enums.BookStatus;
import com.nemo.bookservice.exception.BookDoesntExistException;
import com.nemo.bookservice.repository.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BookRepository bookRepository;

//    @Override
//    public void addBooks(Book book) {
//        BookCategory category = new BookCategory();
//        category.setCategoryName(book.getBookcategory().getCategoryName());
//
//        book.setBookcategory(category);
//
//        bookRepository.save(book);
//    }

    @Override
    public void addBooks(BookRequest bookRequest) {


        Book book = modelMapper.map(bookRequest, Book.class);

        BookCategory category = new BookCategory();
        category.setCategoryName(bookRequest.getBookcategory().getCategoryName());
        book.setBookcategory(category);

        Optional<Book> existingBook = bookRepository.findBookByTitle(bookRequest.getTitle());
        if (existingBook.isPresent()) {
            Book bookToUpdate = existingBook.get();
            int currentNumberOfBooksAvailable = bookToUpdate.getNumberOfBooksAvailable();
            bookToUpdate.setNumberOfBooksAvailable(currentNumberOfBooksAvailable + 1);
            bookRepository.save(bookToUpdate);

        }
        else{
            book.setNumberOfBooksAvailable(1);
            bookRepository.save(book);
        }


    }


    @Override
    public List<BookResponse> getAllBooks() {

        List<Book> getAllBook = bookRepository.findAll();

        List<BookResponse> listofBooks = Arrays.asList(modelMapper.map(getAllBook, BookResponse[].class));

        return listofBooks;
    }

    @Override
    public void deleteBook(int bookId) throws BookDoesntExistException {
        if(!bookRepository.existsById(bookId)){
            throw new BookDoesntExistException("There is no book with that Id ");
        }
        bookRepository.deleteById(bookId);
    }

//    @Override
//    public void updateBookPrice(int bookId, double retailPrice) {
//        Optional<Book> optionalBook = bookRepository.findById(bookId);
//
//        if (!optionalBook.isPresent()) {
//            throw new BookDoesntExistException("Book not found");
//        }
//
//        Book book = optionalBook.get();
//        book.setRetailPrice(retailPrice);
//        bookRepository.save(book);
//    }

    @Override
    public Book updateBook(Book newBook, int bookId) {
        return bookRepository.findById(bookId)
                .map(book ->{
                    book.setNumberOfBooksAvailable(book.getNumberOfBooksAvailable());
                    book.setAuthors(book.getAuthors());
                    book.setBookcategory(book.getBookcategory());
                    book.setBookStatus(book.getBookStatus());
                    book.setTitle(book.getTitle());
                    book.setPublishingHouse(book.getPublishingHouse());
                    book.setRetailPrice(book.getRetailPrice());
                    return bookRepository.save(book);
                })
                .orElseGet(() ->{
                    newBook.setBookId(bookId);
                    return  bookRepository.save(newBook);
                });
    }

    @Override
    public void saveOrUpdate(Book books, int bookId) {
        bookRepository.save(books);

    }

}


//responseEmployees.forEach(employeeEntity -> {
//        for (AddressResponse addReponse : addressResponseBody) {
//            if (addReponse.getId() == employeeEntity.getId()) {
//                employeeEntity.setAddressResponse(addReponse);
//            }
//        }




