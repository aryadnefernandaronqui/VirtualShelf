package virtualShelf.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import virtualShelf.dtos.GetBooks;
import virtualShelf.dtos.ResponseError;
import virtualShelf.models.BookOnShelf;
import virtualShelf.repositories.BookOnShelfRepository;
import virtualShelf.repositories.UserRepository;


import java.util.UUID;

@RestController
@RequestMapping("/bookonshelf")
public class BookOnShelfController {
    @Autowired
    private BookOnShelfRepository bookOnShelfRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    private ResponseEntity getAll(){
        var getAllBooks = bookOnShelfRepository.findAll();
        return ResponseEntity.ok().body(getAllBooks);
    }

    @PostMapping("/{userId}")
    @Transactional
    private ResponseEntity putBookOnShelf(@PathVariable UUID userId,
                                          @Valid
                                          @RequestBody GetBooks newBookOnShelf){

//        var findBook = bookOnShelfRepository.existsByBookIdAndShelfId(newBookOnShelf.bookId(), newBookOnShelf.shelfId());
//
//        if(!findBook){
//            return ResponseEntity.badRequest().body(new ResponseError(
//                    "shelf/books", "Book or Shelf not found. Please, register the new book or a new shelf first."));
//        }

        var user = userRepository.existsById(userId);

        if(!user){
            return ResponseEntity.badRequest().body(new ResponseError("user/books", "User not found."));
        }

        var bookOnShelf = bookOnShelfRepository.save(new BookOnShelf(newBookOnShelf));
        return ResponseEntity.ok().body(bookOnShelf);

    }


}
