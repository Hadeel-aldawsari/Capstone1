package com.example.capstone1.Controller;

import com.example.capstone1.ApiResponse.ApiResponse;
import com.example.capstone1.Model.User;
import com.example.capstone1.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/get-all")
    public ResponseEntity getAll() {
        return ResponseEntity.status(200).body(userService.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity add(@RequestBody @Valid User user, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        userService.add(user);
        return ResponseEntity.status(200).body(new ApiResponse("added successfully"));
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity update(@PathVariable String userId, @RequestBody @Valid User user, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        if (userService.update(userId, user))
            return ResponseEntity.status(200).body(new ApiResponse("updated successfully"));

        return ResponseEntity.status(400).body(new ApiResponse("update Unsuccessfully"));
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity delete(@PathVariable String userId) {
        if (userService.delete(userId))
            return ResponseEntity.status(200).body(new ApiResponse("deleted successfully"));

        return ResponseEntity.status(400).body(new ApiResponse("delete Unsuccessfully"));
    }

    @GetMapping("/get-user/{id}")
    public ResponseEntity getUserByID(@PathVariable String id){
        if(userService.getUserByID(id)==null)
        return ResponseEntity.status(400).body(new ApiResponse("user not found"));
        return ResponseEntity.status(200).body(userService.getUserByID(id));
    }


    @PostMapping("/add-balance/{userid}/{balance}")
    public ResponseEntity addBalance(@PathVariable String userid,@PathVariable double balance){
       if(userService.addBalance(userid,balance))
        return ResponseEntity.status(200).body(new ApiResponse("balance added"));
       return ResponseEntity.status(400).body(new ApiResponse("failer in add balance"));
    }

    @GetMapping("/get-my-order/{userid}")
    public ResponseEntity getUserOrder(@PathVariable String userid){
        if(userService.getUserOrder(userid)==null)
            return ResponseEntity.status(400).body(new ApiResponse("there is no orders"));
        return ResponseEntity.status(200).body(userService.getUserOrder(userid));
    }


 @PutMapping("/refund-order/{userid}/{orderid}")
public ResponseEntity refundOrder(@PathVariable String userid,@PathVariable String orderid){
        String result=userService.refundOrder(userid,orderid);
        if(result.contains("successfully"))
return ResponseEntity.status(200).body(new ApiResponse(result));
        return ResponseEntity.status(400).body(new ApiResponse(result));
}




}
