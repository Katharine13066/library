package by.intexsoft.study.service.impl;

import by.intexsoft.study.model.BookHistory;
import by.intexsoft.study.mappers.AuthorMapper;
import by.intexsoft.study.mappers.BookHistoryMapper;
import by.intexsoft.study.mappers.BookMapper;
import by.intexsoft.study.model.AuthorDto;
import by.intexsoft.study.model.BookDto;
import by.intexsoft.study.model.BookHistoryDto;
import by.intexsoft.study.repositories.BookHistoryDao;
import by.intexsoft.study.service.BookHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("bookhistoryService")
public class BookHistoryServiceImpl implements BookHistoryService {

    @Autowired
    private BookHistoryDao bookHistoryDao;
    @Autowired
    private BookHistoryMapper bookHistoryMapper;
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private AuthorMapper authorMapper;

    public BookHistoryServiceImpl(BookHistoryDao bookHistoryDao, BookHistoryMapper bookHistoryMapper, BookMapper bookMapper, AuthorMapper authorMapper) {
        this.bookHistoryDao = bookHistoryDao;
        this.bookHistoryMapper = bookHistoryMapper;
        this.bookMapper = bookMapper;
        this.authorMapper = authorMapper;
    }

    @Override
    public BookHistoryDto findById(Long id) {
        return bookHistoryMapper.toDto(bookHistoryDao.findById(id));
    }

    @Override
    public List<BookHistoryDto> findByIds(List<Long> list) {
        return bookHistoryMapper.toDtos(bookHistoryDao.findByIds(list));
    }

    @Override
    public List<BookHistoryDto> findAll() {
        List<BookHistory> bookHistories = bookHistoryDao.findAll();
        return bookHistoryMapper.toDtos(bookHistories);
    }

    @Override
    @Transactional
    public void deleteAll() {
        bookHistoryDao.deleteAll();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        bookHistoryDao.deleteById(id);
    }

    @Override
    @Transactional
    public BookHistoryDto create(BookHistoryDto bookHistoryDto) {
        return bookHistoryMapper.toDto(bookHistoryDao.createEntity(bookHistoryMapper.fromDto(bookHistoryDto)));
    }

    @Override
    @Transactional
    public BookHistoryDto update(BookHistoryDto bookHistoryDto) {
        return bookHistoryMapper.toDto(bookHistoryDao.updateEntity(bookHistoryMapper.fromDto(bookHistoryDto)));
    }

    @Override
    @Transactional
    public void patch(BookHistoryDto bookHistoryDto) {
        bookHistoryMapper.updateFeedbackFromDto(bookHistoryDto, bookHistoryDao.findById(bookHistoryDto.getId()));
    }

    public BookHistoryDto findBookHistoryByBookAndUserIds(Long bookId, Long userId) {
        List<BookHistoryDto> list = bookHistoryMapper.toDtos(bookHistoryDao.findBookHistoryByBookAndUserIds(bookId, userId));
        BookHistoryDto bookHistory = new BookHistoryDto();
        for (int i = 0; i < list.size(); i++){
            if(list.get(i).getReturnDate().isEmpty()){
                bookHistory = list.get(i);
            }
        }
        return bookHistory;
    }

    @Override
    public List<BookHistoryDto> findBookHistoryByBookId(Long bookId) {
        return bookHistoryMapper.toDtos(bookHistoryDao.findBookHistoryByBookId(bookId));
    }

    @Override
    public List<BookDto> get10TheMostPopularBooks() {
        return bookMapper.toDtos(bookHistoryDao.get10TheMostPopularBooks());
    }

    @Override
    public List<AuthorDto> get10TheMostPopularAuthors() {
        return authorMapper.toDtos(bookHistoryDao.get10TheMostPopularAuthors());
    }

}
