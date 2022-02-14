package br.com.pz7systems.backendProject.BackEnd.Project.services.impl;

import br.com.pz7systems.backendProject.BackEnd.Project.services.exceptions.ResourceNotFoundException;
import br.com.pz7systems.backendProject.BackEnd.Project.shared.DTO.UserDTO;
import br.com.pz7systems.backendProject.BackEnd.Project.domain.User;
import br.com.pz7systems.backendProject.BackEnd.Project.repositories.UserRepository;
import br.com.pz7systems.backendProject.BackEnd.Project.services.UserService;
import br.com.pz7systems.backendProject.BackEnd.Project.services.exceptions.DataIntegrityViolationException;
import br.com.pz7systems.backendProject.BackEnd.Project.services.exceptions.ProcessNotAllowedException;
import br.com.pz7systems.backendProject.BackEnd.Project.utils.Messages;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public UserDTO create(UserDTO userDTO) {
        Optional<User> emailUser = this.repository.findByEmail(userDTO.getEmail());
        if (!emailUser.isEmpty())
            throw new DataIntegrityViolationException(Messages.EMAIL_ALREADY_REGISTERED);

        return mapper.map(this.repository.save(mapper.map(userDTO, User.class)), UserDTO.class);
    }

    @Override
    public List<UserDTO> findAll() {
        User user = new User();
        user.setDeleted(false);
        Example<User> example = Example.of(user);
        return this.repository.findAll(example).stream().map(x -> mapper.map(x, UserDTO.class)).toList();
    }

    @Override
    public UserDTO findById(Long id) {
        Optional<User> user = this.repository.findById(id);
        if (user.isEmpty())
            throw new ResourceNotFoundException(Messages.USER_NOT_FOUND);
        else
             return mapper.map(user.get(), UserDTO.class);
    }

    @Override
    public UserDTO update(UserDTO userDTO) {
        UserDTO dbUser = this.findById(userDTO.getId());

        if ((dbUser.getDeleted() != null) && (dbUser.getDeleted()))
            throw new ProcessNotAllowedException(Messages.NOT_UPDATE_A_DELETED_USER);

        Optional<User> emailUser = this.repository.findByEmail(userDTO.getEmail());
        if ((emailUser.isPresent()) && (!userDTO.getId().equals(emailUser.get().getId())))
            throw new DataIntegrityViolationException(Messages.EMAIL_ALREADY_REGISTERED);

        mapper.getConfiguration().setSkipNullEnabled(true);
        mapper.map(userDTO, dbUser);
        return mapper.map(this.repository.save(mapper.map(dbUser, User.class)), UserDTO.class);
    }

    @Override
    public Void delete(Long id) {
        UserDTO dbUserDTO = this.findById(id);
        dbUserDTO.setDeleted(true);
        this.repository.save(mapper.map(dbUserDTO, User.class));
        return null;
    }

}
