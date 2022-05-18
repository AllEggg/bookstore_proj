package book_store.views;

import book_store.dao.entity.BookStoreUser;
import book_store.dao.entity.Role;
import book_store.dao.repository.RoleRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class UserView {

    private String login;
    private String password;
    private Set<RoleView> roles;

    public List<UserView> mapToView(List<BookStoreUser> users) {
        List<UserView> views = new ArrayList<>();
        for (BookStoreUser user : users) {
            UserView view = new UserView();
            Set<RoleView> roles = user.getRoles().stream()
                    .map(role -> new RoleView(role.getRole()))
                    .collect(Collectors.toSet());
            view.setLogin(user.getUsername());
            view.setPassword(user.getPassword());
            view.setRoles(roles);
            views.add(view);
        }
        return views;
    }

    public UserView mapToView(BookStoreUser user) {
        UserView view = new UserView();
        Set<RoleView> roles = user.getRoles().stream()
                .map(role -> new RoleView(role.getRole()))
                .collect(Collectors.toSet());
        view.setLogin(user.getUsername());
        view.setPassword(user.getPassword());
        view.setRoles(roles);
        return view;
    }

    public BookStoreUser mapFromView(UserView view, RoleRepository roleRepository) {
        BookStoreUser user = new BookStoreUser();
        Set<Role> roles = view.getRoles().stream()
                .map(role -> roleRepository.findByName(role.getRole()))
                .collect(Collectors.toSet());
        user.setUsername(view.getLogin());
        user.setPassword(view.getPassword());
        user.setRoles(roles);
        return user;
    }

}
