package com.orange.ivdev.formation.spring.formationspringcrudmq.Controller;

import com.orange.ivdev.formation.spring.formationspringcrudmq.Entity.User;
import com.orange.ivdev.formation.spring.formationspringcrudmq.Exception.NotFoundException;
import com.orange.ivdev.formation.spring.formationspringcrudmq.Service.UserService;
import io.swagger.annotations.ApiModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@ApiModel(description="All details about users.")
public class UserController {

    private final UserService userService;

    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value="/{id}", method =RequestMethod.GET)
    public final User byId(final @PathVariable("id") Integer id) {
        return this.userService.byId(id).orElseThrow(() -> new NotFoundException("User not found"));
    }

    @RequestMapping(value="", method=RequestMethod.GET)
    public final List<User> all() {
        return this.userService.all();
    }

    @RequestMapping(value="", method=RequestMethod.POST)
    public final User byId(final @Valid @RequestBody User user) {
        return this.userService.save(user);
    }

}
