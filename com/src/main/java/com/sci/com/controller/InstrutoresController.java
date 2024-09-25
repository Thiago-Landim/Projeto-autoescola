package com.sci.com.controller;

import com.sci.com.dto.InstrutoresDto;
import com.sci.com.entities.InstrutoresEntity;
import com.sci.com.exceptions.ApiErrors;
import com.sci.com.exceptions.BusinessException;
import com.sci.com.mapper.IntrutoresMapper;
import com.sci.com.service.InstrutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/sci")
public class InstrutoresController {

    @Autowired
    private InstrutorService instrutorService;

    @GetMapping
    public ResponseEntity<List<InstrutoresDto>> findAll() {
        List<InstrutoresEntity> todos = instrutorService.toList();
        List<InstrutoresDto> todosDTO = new ArrayList<>();
        for (InstrutoresEntity entity : todos) {
            todosDTO.add(IntrutoresMapper.InstrutoresToDto(entity));
        }
        return ResponseEntity.status(HttpStatus.OK).body(todosDTO);
    }

    @GetMapping("/buscar")
    public ResponseEntity<InstrutoresDto> buscarPorCPF(@RequestParam("cpf") String cpf) {
        InstrutoresEntity instrutoresEntity = instrutorService.buscarPorCPF(cpf);
        if (instrutoresEntity != null) {
            return ResponseEntity.status(HttpStatus.OK).body(IntrutoresMapper.InstrutoresToDto(instrutoresEntity));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<InstrutoresDto> salvarInstrutorcontroller(@RequestBody InstrutoresDto instrutoresDto) {
        InstrutoresEntity entity = instrutorService.salvarInstrutor(IntrutoresMapper.DtoToEntity(instrutoresDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(IntrutoresMapper.InstrutoresToDto(entity));
    }

    /*@PostMapping("/verificar-certificados")
    public ResponseEntity<String> verificarCertificadosExpirados1() {
        instrutorService.verificarCertificadosExpirados();
        return ResponseEntity.status(HttpStatus.OK).body("Verificação de certificados concluída.");
    */}





