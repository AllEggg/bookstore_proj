package book_store.controller;

import book_store.dao.entity.BookStoreUser;
import book_store.dao.entity.Role;
import book_store.dao.service.UserService;
import book_store.views.RoleView;
import book_store.views.UserView;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public Boolean createUser(@RequestBody UserView user) {
        BookStoreUser entity = new BookStoreUser();
        entity.setUsername(user.getLogin());
        entity.setPassword(user.getPassword());
        entity.setRoles(
                user.getRoles()
                        .stream()
                        .map(RoleView::getRole)
                        .map(Role::new)
                        .collect(Collectors.toSet())
        );
        userService.create(entity);
        return true;
    }

}
