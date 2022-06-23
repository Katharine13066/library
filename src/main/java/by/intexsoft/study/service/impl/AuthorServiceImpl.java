package by.intexsoft.study.service.impl;

import by.intexsoft.study.model.AuthorDTO;
import by.intexsoft.study.repositories.AuthorDAO;
import by.intexsoft.study.daomodel.Author;
import by.intexsoft.study.mappers.AuthorMapper;
import by.intexsoft.study.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("authorService")
public class AuthorServiceImpl implements LibraryService<AuthorDTO>  {

    @Autowired
    private AuthorDAO authorDAO;

    @Autowired
    private AuthorMapper authorMapper;


    public AuthorServiceImpl(AuthorDAO authorDAO, AuthorMapper authorMapper) {
        this.authorDAO = authorDAO;
        this.authorMapper = authorMapper;
    }

    @Override
    public AuthorDTO findById(Long id) {
        return authorMapper.toDTO(authorDAO.findById(id));
    }

    @Override
    public List<AuthorDTO> findByIds(List<Long> list) {
        return authorMapper.toDTOs(authorDAO.findByIds(list));
    }

    @Override
    public List<AuthorDTO> findAll() {
        List<Author> authors = authorDAO.findAll();
        return authorMapper.toDTOs(authors);
    }

    @Override
    @Transactional
    public void deleteAll() {
        authorDAO.deleteAll();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        authorDAO.deleteById(id);
    }

    @Override
    @Transactional
    public AuthorDTO create(AuthorDTO authorDTO) {
      return authorMapper.toDTO(authorDAO.createEntity(authorMapper.fromDTO(authorDTO)));
    }

    @Override
    @Transactional
    public AuthorDTO update(AuthorDTO authorDTO) {
        return authorMapper.toDTO(authorDAO.updateEntity(authorMapper.fromDTO(authorDTO)));
    }


    @Override
    @Transactional
    public void patch(AuthorDTO authorDTO) {
        Author author = authorDAO.findById(authorDTO.getId());
        authorMapper.updateAuthorFromDto(authorDTO, author);
    }
}
