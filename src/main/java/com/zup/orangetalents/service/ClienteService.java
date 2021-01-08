package com.zup.orangetalents.service;

import com.zup.orangetalents.dto.ClienteDTO;
import com.zup.orangetalents.exceptions.DuplicateEmailAndCpfException;
import com.zup.orangetalents.model.Cliente;
import com.zup.orangetalents.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    public Cliente insert(ClienteDTO clienteDTO) throws DuplicateEmailAndCpfException {
        verifyIfEmailAndCpfIsAlreadyRegistered(clienteDTO.getEmail() , clienteDTO.getCpf());
        Cliente cliente = fromDTO(clienteDTO);
        return clienteRepository.save(cliente);
    }

    public Cliente fromDTO(ClienteDTO cliDTO) {
        return new Cliente(null,
                cliDTO.getNome(),
                cliDTO.getEmail(),
                cliDTO.getCpf(),
                cliDTO.getDataNasc()
        );
    }

    private void verifyIfEmailAndCpfIsAlreadyRegistered(String email , String cpf) throws DuplicateEmailAndCpfException {
        Cliente clienteEmail = clienteRepository.findByEmail(email);
        Cliente clienteCpf = clienteRepository.findByCpf(cpf);

        if (clienteEmail != null && clienteCpf != null ){
            throw new DuplicateEmailAndCpfException("O email: " + email + " e o CPF: " + cpf + " já estão cadastrados no sistema");
        }
        if (clienteEmail != null){
            throw new DuplicateEmailAndCpfException("O email: " + email + " já está cadastrado no sistema");
        }

        if (clienteCpf != null){
            throw new DuplicateEmailAndCpfException("O CPF: " + cpf + " já está cadastrado no sistema");
        }

    }
}
