package com.soa.soa_api.ui;

import com.soa.soa_api.domain.db.UserRepository;
import com.soa.soa_api.domain.model.User;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserRESTController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/index")
    public String getIndex(){
        return "error.html";
    }

    @GetMapping("/all")
    public Iterable<User> getAll(){
        return userRepository.findAll();
    }

    @PostMapping("/create")
    public User create(@PathVariable("name") String name ) {
        User user = new User();
        user.setName(name);
        return userRepository.save(user);
    }

    @PostMapping("/add")
    public User add(@Valid @RequestBody User user ) throws ServiceException {
        return userRepository.save(user);
    }

    @PutMapping("/update")
    public User update(@Valid @RequestBody User user) throws ServiceException {
        if (!userRepository.findById(user.getId()).isPresent()) throw new ServiceException("User with that id is not present");
        return userRepository.save(user);
    }

    @DeleteMapping("/delete")
    public void delete(@PathVariable("id") Long id) { userRepository.deleteById(id); }

    @GetMapping("/getFavoriteStations")
    public Iterable<Long> getFavoriteStations(@PathVariable("userid") Long userid){
        User user = userRepository.getById(userid);
        if (user == null) throw new IllegalArgumentException("User with id not found");
        return user.getFavoriteStations();
    }

    @GetMapping("/addFavoriteStation")
    public void addFavoriteStation(@PathVariable("userid") String userid, @PathVariable("stationid") String stationid){
        User user = userRepository.getById(Long.valueOf(userid));
        if (user == null) throw new IllegalArgumentException("User with id not found");
        user.addFavoriteStation(Long.valueOf(stationid));
    }

    @GetMapping("/removeFavoriteStation")
    public void removeFavoriteStation(@PathVariable("userid") Long userid, @PathVariable("stationid") Long stationid){
        User user = userRepository.getById(userid);
        if (user == null) throw new IllegalArgumentException("User with id not found");
        user.removeFavoriteStation(stationid);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class, ResponseStatusException.class})
    public Map<String, String> handleValidationExceptions(Exception ex) {
        Map<String, String> errors = new HashMap<>();
        if (ex instanceof MethodArgumentNotValidException) {
            ((MethodArgumentNotValidException) ex).getBindingResult().getAllErrors().forEach((error) -> {
                String fieldName = ((FieldError) error).getField();
                String errorMessage = error.getDefaultMessage();
                errors.put(fieldName, errorMessage);
            });
        } else {
            errors.put(((ResponseStatusException) ex).getReason(), ex.getCause().getMessage());
        }
        return errors;
    }
}
