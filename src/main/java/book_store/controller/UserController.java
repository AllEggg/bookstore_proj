package book_store.controller;

import book_store.dao.repository.RoleRepository;
import book_store.dao.service.UserService;
import book_store.views.UserView;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final RoleRepository roleRepository;
    private final UserView userView;

    public UserController(UserService userService, RoleRepository roleRepository, UserView userView) {
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.userView = userView;
    }

    @PostMapping
    public Boolean createUser(@RequestBody UserView view) {
        userService.create(userView.mapFromView(view, roleRepository));
        return true;
    }

}
