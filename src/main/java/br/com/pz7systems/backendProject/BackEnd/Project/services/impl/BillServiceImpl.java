package br.com.pz7systems.backendProject.BackEnd.Project.services.impl;

import br.com.pz7systems.backendProject.BackEnd.Project.domain.Bill;
import br.com.pz7systems.backendProject.BackEnd.Project.domain.User;
import br.com.pz7systems.backendProject.BackEnd.Project.repositories.BillRepository;
import br.com.pz7systems.backendProject.BackEnd.Project.services.BillService;
import br.com.pz7systems.backendProject.BackEnd.Project.services.exceptions.ProcessNotAllowedException;
import br.com.pz7systems.backendProject.BackEnd.Project.services.exceptions.ResourceNotFoundException;
import br.com.pz7systems.backendProject.BackEnd.Project.shared.DTO.BillDTO;
import br.com.pz7systems.backendProject.BackEnd.Project.shared.DTO.UserDTO;
import br.com.pz7systems.backendProject.BackEnd.Project.utils.Messages;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BillServiceImpl implements BillService {

    @Autowired
    private BillRepository repository;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private ModelMapper mapper;

    @Override
    public BillDTO create(BillDTO billDTO) {
        userService.findById(billDTO.getUserId());
        return mapper.map(this.repository.save(mapper.map(billDTO, Bill.class)), BillDTO.class);
    }

    @Override
    public List<BillDTO> findAllByUser(Long userId) {
        UserDTO userDTO = userService.findById(userId);
        Bill bill = new Bill();
        bill.setDeleted(false);
        bill.setUser(mapper.map(userDTO, User.class));
        return this.repository.findAll(Example.of(bill)).stream()
                .map(x -> mapper.map(x, BillDTO.class)).toList();
    }

    @Override
    public List<BillDTO> findAll() {
        Bill bill = new Bill();
        bill.setDeleted(false);
        return this.repository.findAll(Example.of(bill)).stream()
                .map(x -> mapper.map(x, BillDTO.class)).toList();
    }

    @Override
    public BillDTO findById(Long id) {
        Optional<Bill> bill = this.repository.findById(id);
        if (bill.isEmpty())
            throw new ResourceNotFoundException(Messages.BILL_NOT_FOUND);
        else
            return mapper.map(bill.get(), BillDTO.class);
    }

    @Override
    public BillDTO update(BillDTO billDTO) {
        BillDTO dbBillDTO = this.findById(billDTO.getId());

        if ((dbBillDTO.getDeleted() != null) && (dbBillDTO.getDeleted()))
            throw new ProcessNotAllowedException(Messages.NOT_UPDATE_A_DELETED_BILL);

        mapper.getConfiguration().setSkipNullEnabled(true);
        mapper.map(billDTO, dbBillDTO);
        return mapper.map(this.repository.save(mapper.map(dbBillDTO, Bill.class)), BillDTO.class);
    }

    @Override
    public Void delete(Long id) {
        BillDTO dbBillDTO = this.findById(id);
        dbBillDTO.setDeleted(true);
        this.repository.save(mapper.map(dbBillDTO, Bill.class));
        return null;
    }

}
