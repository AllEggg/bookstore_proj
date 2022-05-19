package book_store.dao.service;

import book_store.dao.entity.BookStoreUser;
import book_store.dao.entity.Role;
import book_store.dao.repository.RoleRepository;
import book_store.dao.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }




    @Override
    public BookStoreUser loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.getByUsername(username);
    }

    public void create(BookStoreUser bookStoreUser) {
        BookStoreUser exist = loadUserByUsername(bookStoreUser.getUsername());
        if (exist != null) {
            throw new EntityExistsException("Пользователь с логином "
                    + bookStoreUser.getUsername() + " уже существует");
        }
        String password = passwordEncoder.encode(bookStoreUser.getPassword());
        bookStoreUser.setPassword(password);

        Set<String> roles = bookStoreUser.getRoles().stream()
                .map(Role::getRole)
                .collect(Collectors.toSet());

        Set<Role> existRoles = roleRepository.findByRoleIn(roles);

        bookStoreUser.setRoles(existRoles);
        existRoles.forEach(role -> role.setBookStoreUsers(Set.of(bookStoreUser)));
        userRepository.save(bookStoreUser);

    }




}
