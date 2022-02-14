package br.com.pz7systems.backendProject.BackEnd.Project.repositories;

import br.com.pz7systems.backendProject.BackEnd.Project.domain.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {

}
