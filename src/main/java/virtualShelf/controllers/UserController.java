package virtualShelf.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import virtualShelf.dtos.CreateUser;
import virtualShelf.dtos.Login;
import virtualShelf.dtos.ResponseError;
import virtualShelf.models.User;
import virtualShelf.repositories.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity getAll(){
        var getAllUsers = userRepository.findAll();
        return ResponseEntity.ok().body(getAllUsers);
    }

    @PostMapping
    @Transactional
    public ResponseEntity createUser(@RequestBody @Valid CreateUser newUser){
        if(userRepository.existsByEmail(newUser.email())){
            return ResponseEntity.badRequest().body(new ResponseError("Account already exist. Try a different e-mail"));
        }

        var user = new User(newUser);
        userRepository.save(user);

        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid Login newLogin){

        var userLogin = userRepository.getReferenceByEmail(newLogin.email());

        if(userLogin == null){
            return ResponseEntity.badRequest().body(new ResponseError("User not found."));
        }
        if(!userLogin.getPassword().equals(newLogin.password())){
            return ResponseEntity.badRequest().body(new ResponseError("Password doesn't match. Try again."));
        }
        userRepository.save(userLogin);
        return  ResponseEntity.ok().body(userLogin);
    }
}
