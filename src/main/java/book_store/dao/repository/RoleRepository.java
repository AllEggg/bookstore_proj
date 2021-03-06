package book_store.dao.repository;

import book_store.dao.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Set<Role> findByRoleIn(Set<String> roles);

    @Query(value = "select * from role r where r.role = :role",
            nativeQuery = true)
    Role findByName(String role);

}
