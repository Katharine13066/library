package by.intexsoft.study.controllers;

import by.intexsoft.study.api.UsersApi;
import by.intexsoft.study.model.UserDto;
import by.intexsoft.study.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController implements UsersApi {

    private UserService userService;

    @Autowired
    public UserController(@Qualifier("userService") UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<Void> createUser(UserDto userDto) {
        userService.create(userDto);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteAllUsers() {
        userService.deleteAll();
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteUserById(Long id) {
        userService.deleteById(id);
        return new ResponseEntity<Void>( HttpStatus.OK );
    }

   @Override
    public ResponseEntity<List<UserDto>> findAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @Override
    public ResponseEntity<UserDto> findByIdUser(Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @Override
    public ResponseEntity<Void> patchUser(UserDto userDto) {
        userService.patch(userDto);
        return new ResponseEntity<Void>( HttpStatus.OK );
    }

    @Override
    public ResponseEntity<Void> updateUser(UserDto userDto) {
        userService.update(userDto);
        return new ResponseEntity<Void>( HttpStatus.OK );
    }

}
