package by.intexsoft.study.service.impl;

import by.intexsoft.study.daomodel.BookHistory;
import by.intexsoft.study.mappers.BookHistoryMapper;
import by.intexsoft.study.model.BookHistoryDTO;
import by.intexsoft.study.repositories.impl.BookHistoryDAOImpl;
import by.intexsoft.study.service.BookHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("bookhistoryService")
public class BookHistoryServiceImpl implements BookHistoryService {

    @Autowired
    private BookHistoryDAOImpl bookHistoryDAO;

    @Autowired
    private BookHistoryMapper bookHistoryMapper;

    public BookHistoryServiceImpl(BookHistoryDAOImpl bookHistoryDAO, BookHistoryMapper bookHistoryMapper) {
        this.bookHistoryDAO = bookHistoryDAO;
        this.bookHistoryMapper = bookHistoryMapper;
    }

    @Override
    public BookHistoryDTO findById(Long id) {
        return bookHistoryMapper.toDTO(bookHistoryDAO.findById(id));
    }

    @Override
    public List<BookHistoryDTO> findByIds(List<Long> list) {
        return bookHistoryMapper.toDTOs(bookHistoryDAO.findByIds(list));
    }

    @Override
    public List<BookHistoryDTO> findAll() {
        List<BookHistory> bookHistories = bookHistoryDAO.findAll();
        return bookHistoryMapper.toDTOs(bookHistories);
    }

    @Override
    @Transactional
    public void deleteAll() {
        bookHistoryDAO.deleteAll();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        bookHistoryDAO.deleteById(id);
    }

    @Override
    @Transactional
    public BookHistoryDTO create(BookHistoryDTO bookHistoryDTO) {
        return bookHistoryMapper.toDTO(bookHistoryDAO.createEntity(bookHistoryMapper.fromDTO(bookHistoryDTO)));
    }

    @Override
    @Transactional
    public BookHistoryDTO update(BookHistoryDTO bookHistoryDTO) {
        return bookHistoryMapper.toDTO(bookHistoryDAO.updateEntity(bookHistoryMapper.fromDTO(bookHistoryDTO)));
    }

    @Override
    @Transactional
    public void patch(BookHistoryDTO bookHistoryDTO) {
        BookHistory bookHistory = bookHistoryDAO.findById(bookHistoryDTO.getId());
        bookHistoryMapper.updateFeedbackFromDto(bookHistoryDTO, bookHistory);
    }

    public BookHistoryDTO findBookHistoryByBookAndUserIds(Long book_id, Long user_id) {
        List<BookHistoryDTO> list = bookHistoryDAO.findBookHistoryByBookAndUserIds(book_id, user_id);
        BookHistoryDTO bookHistory = new BookHistoryDTO();
        for (int i = 0; i < list.size(); i++){
            if(list.get(i).getReturnDate().isEmpty()){
                bookHistory = list.get(i);
            }
        }
        return bookHistory;
    }

}
