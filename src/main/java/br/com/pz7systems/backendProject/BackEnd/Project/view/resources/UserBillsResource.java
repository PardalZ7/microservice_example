package br.com.pz7systems.backendProject.BackEnd.Project.view.resources;

import br.com.pz7systems.backendProject.BackEnd.Project.services.BillService;
import br.com.pz7systems.backendProject.BackEnd.Project.view.model.BillResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/user/bills")
public class UserBillsResource {

    public static final String ID = "/{userId}";

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private BillService service;

    @GetMapping(value = ID)
    public ResponseEntity<List<BillResponse>> findAll(@PathVariable Long userId) {
        List<BillResponse> response = service.findAllByUser(userId).stream().map(x -> mapper
                .map(x, BillResponse.class)).toList();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping()
    public ResponseEntity<String> simpleResponse() {
        return ResponseEntity.ok().body("String response");
    }

}
