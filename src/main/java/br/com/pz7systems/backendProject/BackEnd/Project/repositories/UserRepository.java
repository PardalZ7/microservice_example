package br.com.pz7systems.backendProject.BackEnd.Project.repositories;

import br.com.pz7systems.backendProject.BackEnd.Project.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

}
