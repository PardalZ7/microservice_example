package br.com.pz7systems.backendProject.BackEnd.Project.services;

import br.com.pz7systems.backendProject.BackEnd.Project.shared.DTO.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO create(UserDTO userDTO);
    List<UserDTO> findAll();
    UserDTO findById(Long id);
    UserDTO update(UserDTO userDTO);
    Void delete(Long id);

}
