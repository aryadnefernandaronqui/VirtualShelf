package virtualShelf.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import virtualShelf.dtos.CreateShelf;
import virtualShelf.dtos.ResponseError;
import virtualShelf.models.Shelf;
import virtualShelf.repositories.ShelfRepository;
import virtualShelf.repositories.UserRepository;

import java.util.UUID;

@RestController
@RequestMapping("/shelves")
public class ShelfController {
    @Autowired
    private ShelfRepository shelfRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity getAll(){
        var getAllShelves = shelfRepository.findAll();
        return ResponseEntity.ok().body(getAllShelves);
    }

    @PostMapping("/{userId}")
    @Transactional
    public ResponseEntity createShelf(@PathVariable UUID userId,
                                      @RequestBody @Valid CreateShelf newShelf){
        var user = userRepository.findById(userId);

        if(user.isEmpty()){
            return ResponseEntity.badRequest().body(new ResponseError("user/books", "User not found."));
        }
        if(shelfRepository.existsByName(newShelf.name())){
            return ResponseEntity.badRequest().body(new ResponseError("shelf","This shelf name already exist. Try a different one"));
        }

        var shelf = shelfRepository.save(new Shelf(newShelf, userId));
        return ResponseEntity.ok().body(shelf);
    }


}
