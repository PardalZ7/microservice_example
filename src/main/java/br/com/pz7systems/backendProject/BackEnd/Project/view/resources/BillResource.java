package br.com.pz7systems.backendProject.BackEnd.Project.view.resources;

import br.com.pz7systems.backendProject.BackEnd.Project.shared.DTO.BillDTO;
import br.com.pz7systems.backendProject.BackEnd.Project.services.BillService;
import br.com.pz7systems.backendProject.BackEnd.Project.view.model.BillRequest;
import br.com.pz7systems.backendProject.BackEnd.Project.view.model.BillResponse;
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
@RequestMapping(value = "/bill")
public class BillResource {

    public static final String ID = "/{id}";

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private BillService service;

    @GetMapping(value = ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BillResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(mapper.map(service.findById(id), BillResponse.class));
    }

    @GetMapping()
    public ResponseEntity<List<BillResponse>> findAll() {
        List<BillResponse> response = service.findAll().stream().map(x -> mapper.map(x, BillResponse.class)).toList();
        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    public ResponseEntity<BillResponse> create(@RequestBody BillRequest billRequest) {
        BillResponse billResponse = mapper.map(service.create(mapper.map(billRequest, BillDTO.class)),
                BillResponse.class);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path(ID).buildAndExpand(billResponse.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = ID)
    public ResponseEntity<BillResponse> update(@PathVariable Long id, @RequestBody @NotNull BillRequest billRequest){
        billRequest.setId(id);
        return ResponseEntity.ok().body(mapper.map(service.update(
                mapper.map(billRequest, BillDTO.class)), BillResponse.class)) ;
    }

    @DeleteMapping(value = ID)
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
