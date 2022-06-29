package by.intexsoft.study.service.impl;

import by.intexsoft.study.model.Book;
import by.intexsoft.study.model.BookHistory;
import by.intexsoft.study.mappers.BookMapper;
import by.intexsoft.study.model.BookDto;
import by.intexsoft.study.repositories.BookDao;
import by.intexsoft.study.repositories.BookHistoryDao;
import by.intexsoft.study.repositories.UserDao;
import by.intexsoft.study.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service("bookService")
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private BookHistoryDao bookHistoryDao;

    @Autowired
    private UserDao userDao;

    public BookServiceImpl(BookDao bookDao, BookMapper bookMapper, BookHistoryDao bookHistoryDao, UserDao userDao) {
        this.bookDao = bookDao;
        this.bookMapper = bookMapper;
        this.bookHistoryDao = bookHistoryDao;
        this.userDao = userDao;
    }

    public BookDto findById(Long id){
        return bookMapper.toDto(bookDao.findById(id));
    }

    public List<BookDto> findByIds(List<Long> list){
        return bookMapper.toDtos(bookDao.findByIds(list));
    }

    public List<BookDto> findAll(){
        return bookMapper.toDtos(bookDao.findAll());
    }

    @Override
    @Transactional
    public void deleteAll(){
        bookDao.deleteAll();
    }

    @Override
    @Transactional
    public void deleteById(Long id){
        bookDao.deleteById(id);
    }

    @Override
    @Transactional
    public BookDto create(BookDto bookDto) {
        return bookMapper.toDto(bookDao.createEntity(bookMapper.fromDto(bookDto)));
    }

    @Override
    @Transactional
    public BookDto update(BookDto bookDto) {
        return bookMapper.toDto(bookDao.updateEntity(bookMapper.fromDto(bookDto)));
    }

    @Override
    @Transactional
    public void patch(BookDto bookDto) {
        bookMapper.updateBookFromDto(bookDto, bookDao.findById(bookDto.getId()));
    }

    @Override
    @Transactional
    public void takeBook(Long bookId, Long userId) {
        Book book = bookDao.findById(bookId);
        book.setStatus(false);
        bookDao.updateEntity(book);
        BookHistory bookHistory = new BookHistory();
        bookHistory.setBookId(bookId);
        bookHistory.setUserId(userId);
        bookHistory.setReturnDate("");
        Date curDate = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yy");
        bookHistory.setStartDate(simpleDateFormat.format(curDate));
        bookHistory.setBook(book);
        bookHistoryDao.createEntity(bookHistory);
    }

    @Override
    @Transactional
    public void returnBook(Long bookId, Long userId) {
        Book book = bookDao.findById(bookId);
        book.setStatus(true);
        bookDao.updateEntity(book);
        List<BookHistory> bookHistoryList = bookHistoryDao.findBookHistoryByBookAndUserIds(bookId, userId);
        BookHistory bookHistory = new BookHistory();
        for(int i = 0; i < bookHistoryList.size(); i++){
            if (bookHistoryList.get(i).getReturnDate().isEmpty()){
                bookHistory = bookHistoryList.get(i);
            }
        }
        Date curDate = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yy");
        bookHistory.setReturnDate(simpleDateFormat.format(curDate));
        bookHistoryDao.updateEntity(bookHistory);
    }

}