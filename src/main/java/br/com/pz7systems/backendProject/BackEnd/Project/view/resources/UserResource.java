package br.com.pz7systems.backendProject.BackEnd.Project.view.resources;

import br.com.pz7systems.backendProject.BackEnd.Project.shared.DTO.UserDTO;
import br.com.pz7systems.backendProject.BackEnd.Project.services.UserService;
import br.com.pz7systems.backendProject.BackEnd.Project.view.model.UserRequest;
import br.com.pz7systems.backendProject.BackEnd.Project.view.model.UserResponse;
import com.sun.istack.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserResource {

    public static final String ID = "/{id}";

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UserService service;

    @GetMapping(value = ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(mapper.map(service.findById(id), UserResponse.class));
    }

    @GetMapping()
    public ResponseEntity<List<UserResponse>> findAll() {
        List<UserResponse> usersResponse = service.findAll().stream().map(x -> mapper
                .map(x, UserResponse.class)).toList();
        return ResponseEntity.ok().body(usersResponse);
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody UserRequest userRequest) {
        UserDTO userDTO = service.create(mapper.map(userRequest, UserDTO.class));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path(ID)
                .buildAndExpand(userDTO.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = ID)
    public ResponseEntity<UserResponse> update(@PathVariable Long id, @RequestBody @NotNull UserRequest userRequest){
        UserDTO userDTO = mapper.map(userRequest, UserDTO.class);
        userDTO.setId(id);
        return ResponseEntity.ok().body(mapper.map(service.update(userDTO), UserResponse.class));
    }

    @DeleteMapping(value = ID)
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
