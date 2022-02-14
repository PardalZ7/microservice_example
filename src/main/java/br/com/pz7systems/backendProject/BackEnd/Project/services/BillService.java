package br.com.pz7systems.backendProject.BackEnd.Project.services;

import br.com.pz7systems.backendProject.BackEnd.Project.shared.DTO.BillDTO;

import java.util.List;

public interface BillService {

    BillDTO create(BillDTO paymentDTO);
    List<BillDTO> findAll();
    List<BillDTO> findAllByUser(Long userId);
    BillDTO findById(Long id);
    BillDTO update(BillDTO paymentDTO);
    Void delete(Long id);

}
