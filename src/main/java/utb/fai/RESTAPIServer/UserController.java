package utb.fai.RESTAPIServer;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;


    @GetMapping("/users")
    public List<MyUser> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/getUser")
    public ResponseEntity<MyUser> getUserById(@RequestParam Long id) {
        return userRepository.findById(id)
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/createUser")
    public ResponseEntity<MyUser> createUser(@RequestBody MyUser newUser) {
        if (newUser.isUserDataValid(newUser.getName(), newUser.getPhoneNumber(), newUser.getEmail())) {
            MyUser savedUser = userRepository.save(newUser);
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }


    @PutMapping("/editUser")
    public ResponseEntity<MyUser> editUser(@RequestParam Long id, @RequestBody MyUser editedUser) {
        if (editedUser.isUserDataValid(editedUser.getName(), editedUser.getPhoneNumber(), editedUser.getEmail())) {
            userRepository.findById(id).map(existingUser -> {
                existingUser.setName(editedUser.getName());
                existingUser.setEmail(editedUser.getEmail());
                existingUser.setPhoneNumber(editedUser.getPhoneNumber());
                userRepository.save(existingUser);
                return new ResponseEntity<>(existingUser, HttpStatus.OK);
            });

    }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    @DeleteMapping("/deleteUser")
    public ResponseEntity<MyUser> deleteUser(@RequestParam Long id) {
        return userRepository.findById(id)
                .map(user -> {
                    userRepository.delete(user);
                    return new ResponseEntity<>(user, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<MyUser> deleteAll() {
        userRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }


    // TODO: Define remaining endpoints in the same way. For id parameter use annotation @RequestParam with name "id" and for MyUser structure use @RequestBody.

}
