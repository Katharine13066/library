package by.intexsoft.study.service.impl;

import by.intexsoft.study.mappers.UserMapper;
import by.intexsoft.study.model.UserDto;
import by.intexsoft.study.repositories.UserDao;
import by.intexsoft.study.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserMapper userMapper;

    public UserServiceImpl(UserDao userDao, UserMapper userMapper) {
        this.userDao = userDao;
        this.userMapper = userMapper;
    }

    @Override
    public UserDto findById(Long id) {
        return userMapper.toDto(userDao.findById(id));
    }

    @Override
    public List<UserDto> findByIds(List<Long> list) {
        return userMapper.toDtos(userDao.findByIds(list));
    }

    @Override
    public List<UserDto> findAll() {
        return userMapper.toDtos(userDao.findAll());
    }

    @Override
    @Transactional
    public void deleteAll() {
        userDao.deleteAll();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        userDao.deleteById(id);
    }

    @Override
    @Transactional
    public UserDto create(UserDto userDto) {
        return userMapper.toDto(userDao.createEntity(userMapper.fromDto(userDto)));
    }

    @Override
    @Transactional
    public UserDto update(UserDto userDto) {
        return userMapper.toDto(userDao.updateEntity(userMapper.fromDto(userDto)));
    }

    @Override
    @Transactional
    public void patch(UserDto userDto) {
        userMapper.updateUserFromDto(userDto, userDao.findById(userDto.getId()));
    }

}
