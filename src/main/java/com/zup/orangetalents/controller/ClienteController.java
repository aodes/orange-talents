package com.zup.orangetalents.controller;

import com.zup.orangetalents.dto.ClienteDTO;
import com.zup.orangetalents.exceptions.DuplicateEmailAndCpfException;
import com.zup.orangetalents.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@RequestBody @Valid ClienteDTO clienteDTO) throws DuplicateEmailAndCpfException {
        clienteService.insert(clienteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}



