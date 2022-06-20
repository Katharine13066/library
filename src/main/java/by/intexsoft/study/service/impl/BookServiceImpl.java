package by.intexsoft.study.service.impl;

import by.intexsoft.study.repositories.BookDAO;
import by.intexsoft.study.daomodel.Book;
import by.intexsoft.study.dto.BookDTO;
import by.intexsoft.study.mappers.BookMapper;
import by.intexsoft.study.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BookServiceImpl implements LibraryService<BookDTO> {

    @Autowired
    private BookDAO bookDAO;
    @Autowired
    private BookMapper bookMapper;

    public BookServiceImpl(BookDAO bookDAO, BookMapper bookMapper) {
        this.bookDAO = bookDAO;
        this.bookMapper = bookMapper;
    }

    public BookDTO findById(Long id){
        return bookMapper.toDTO(bookDAO.findById(id));
    }

    public List<BookDTO> findByIds(List<Long> list){
        return bookMapper.toDTOs(bookDAO.findByIds(list));
    }

    public List<BookDTO> findAll(){
        List<Book> bookList = bookDAO.findAll();
        return bookMapper.toDTOs(bookList);
    }

    @Transactional
    public void deleteAll(){
        bookDAO.deleteAll();
    }

    @Transactional
    public void deleteById(Long id){
        bookDAO.deleteById(id);
    }

    @Override
    @Transactional
    public BookDTO create(BookDTO bookDTO) {
        return bookMapper.toDTO(bookDAO.createEntity(bookMapper.fromDTO(bookDTO)));
    }

    @Override
    @Transactional
    public BookDTO update(BookDTO bookDTO) {
        return bookMapper.toDTO(bookDAO.updateEntity(bookMapper.fromDTO(bookDTO)));
    }

    @Override
    public void patch(BookDTO bookDTO) {
        Book book = bookDAO.findById(bookDTO.getId());
        bookMapper.updateBookrFromDto(bookDTO, book);
    }


}