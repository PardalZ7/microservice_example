package br.com.pz7systems.backendProject.BackEnd.Project.repositories;

import br.com.pz7systems.backendProject.BackEnd.Project.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

}
