package com.sci.com.service;

import com.sci.com.components.GerenciadorCertificados;
import com.sci.com.entities.InstrutoresEntity;
import com.sci.com.repositories.InstrutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;

@Service
public class InstrutorService {


    @Autowired
    private InstrutorRepository instrutorRepository;

    @Autowired
    private GerenciadorCertificados gerenciadorCertificados;

    public List<InstrutoresEntity> toList() {
        return instrutorRepository.findAll();
    }

    public InstrutoresEntity findByIdOrThrowBadRequestException(Long id) {
        return instrutorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Instrutor não Encontrado"));
    }

    public InstrutoresEntity buscaID(Long id) {
        return findByIdOrThrowBadRequestException(id);
    }

    public InstrutoresEntity salvarInstrutor(InstrutoresEntity entity) {
        return instrutorRepository.save(entity);
    }

    public void verificarCertificadosExpirados() {
        List<InstrutoresEntity> todos = toList();
        gerenciadorCertificados.verificarCertificados(todos);
    }
}

