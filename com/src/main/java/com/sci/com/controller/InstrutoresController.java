package com.sci.com.controller;

import com.sci.com.DTO.InstrutoresDto;
import com.sci.com.Entities.InstrutoresEntity;
import com.sci.com.mapper.IntrutoresMapper;
import com.sci.com.service.InstrutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/sci")
public class InstrutoresController {

    @Autowired
    InstrutorService instrutorService;

    @GetMapping
    public ResponseEntity<List<InstrutoresDto>> findAll(){
        List<InstrutoresEntity> todos = instrutorService.toList();
        List<InstrutoresDto> todosDTO = new ArrayList<>();

        for (InstrutoresEntity entity: todos){
            InstrutoresDto.add(IntrutoresMapper.InstrutoresToDto(entity));

        }
        return ResponseEntity.status(HttpStatus.OK).body(todosDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InstrutoresDto> trazPeloID(@PathVariable Long id){
        InstrutoresEntity instrutoresEntity = instrutorService.buscaID(id);
        return ResponseEntity.status(HttpStatus.OK).body(IntrutoresMapper.InstrutoresToDto(instrutoresEntity));

    }

}

