package by.intexsoft.study.service.impl;

import by.intexsoft.study.mappers.AuthorMapper;
import by.intexsoft.study.model.AuthorDto;
import by.intexsoft.study.repositories.AuthorDao;
import by.intexsoft.study.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("authorService")
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorDao authorDao;
    @Autowired
    private AuthorMapper authorMapper;

    public AuthorServiceImpl(AuthorDao authorDao, AuthorMapper authorMapper) {
        this.authorDao = authorDao;
        this.authorMapper = authorMapper;
    }

    @Override
    public AuthorDto findById(Long id) {
        return authorMapper.toDto(authorDao.findById(id));
    }

    @Override
    public List<AuthorDto> findByIds(List<Long> list) {
        return authorMapper.toDtos(authorDao.findByIds(list));
    }

    @Override
    public List<AuthorDto> findAll() {
        return authorMapper.toDtos(authorDao.findAll());
    }

    @Override
    @Transactional
    public void deleteAll() {
        authorDao.deleteAll();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        authorDao.deleteById(id);
    }

    @Override
    @Transactional
    public AuthorDto create(AuthorDto authorDto) {
      return authorMapper.toDto(authorDao.createEntity(authorMapper.fromDto(authorDto)));
    }

    @Override
    @Transactional
    public AuthorDto update(AuthorDto authorDto) {
        return authorMapper.toDto(authorDao.updateEntity(authorMapper.fromDto(authorDto)));
    }

    @Override
    @Transactional
    public void patch(AuthorDto authorDto) {
        authorMapper.updateAuthorFromDto(authorDto, authorDao.findById(authorDto.getId()));
    }

}
