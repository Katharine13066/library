package by.intexsoft.study.service.impl;

import by.intexsoft.study.daomodel.User;
import by.intexsoft.study.mappers.UserMapper;
import by.intexsoft.study.model.UserDTO;
import by.intexsoft.study.repositories.UserDAO;
import by.intexsoft.study.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserMapper userMapper;

    public UserServiceImpl(UserDAO userDAO, UserMapper userMapper) {
        this.userDAO = userDAO;
        this.userMapper = userMapper;
    }

    @Override
    public UserDTO findById(Long id) {
        return userMapper.toDTO(userDAO.findById(id));
    }

    @Override
    public List<UserDTO> findByIds(List<Long> list) {
        return userMapper.toDTOs(userDAO.findByIds(list));
    }

    @Override
    public List<UserDTO> findAll() {
        List<User> users = userDAO.findAll();
        return userMapper.toDTOs(users);
    }

    @Override
    @Transactional
    public void deleteAll() {
        userDAO.deleteAll();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        userDAO.deleteById(id);
    }

    @Override
    @Transactional
    public UserDTO create(UserDTO userDTO) {
        return userMapper.toDTO(userDAO.createEntity(userMapper.fromDTO(userDTO)));
    }

    @Override
    @Transactional
    public UserDTO update(UserDTO userDTO) {
        return userMapper.toDTO(userDAO.updateEntity(userMapper.fromDTO(userDTO)));
    }

    @Override
    @Transactional
    public void patch(UserDTO userDTO) {
        User user = userDAO.findById(userDTO.getId());
        userMapper.updateUserFromDto(userDTO, user);
    }

}
