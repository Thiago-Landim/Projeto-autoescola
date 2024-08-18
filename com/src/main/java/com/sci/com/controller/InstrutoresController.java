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
    InstrutorService instrutorService;

    @GetMapping
    public ResponseEntity<List<InstrutoresDto>> findAll(){
        List<InstrutoresEntity> todos = instrutorService.toList();
        List<InstrutoresDto> todosDTO = new ArrayList<>();

        for (InstrutoresEntity entity: todos){

            todosDTO.add(IntrutoresMapper.InstrutoresToDto(entity));
        }
        return ResponseEntity.status(HttpStatus.OK).body(todosDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InstrutoresDto> trazPeloID(@PathVariable Long id){
        InstrutoresEntity instrutoresEntity = instrutorService.buscaID(id);
        return ResponseEntity.status(HttpStatus.OK).body(IntrutoresMapper.InstrutoresToDto(instrutoresEntity));

    }

    @PostMapping("/save")
    public ResponseEntity<InstrutoresDto> salvarInstrutorcontroller(@RequestBody InstrutoresDto instrutoresDto){
        InstrutoresEntity entity = instrutorService.salvarInstrutor(IntrutoresMapper.DtoToEntity(instrutoresDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(IntrutoresMapper.InstrutoresToDto(entity));
    }

    @PostMapping("/verificar-certificados")
    public ResponseEntity<String> verificarCertificadosExpirados() {
        instrutorService.verificarCertificadosExpirados();
        return ResponseEntity.status(HttpStatus.OK).body("Verificação de certificados concluída.");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleValidationExceptions(MethodArgumentNotValidException exception){
        BindingResult bindingResult = exception.getBindingResult();


        return new ApiErrors(bindingResult);


    }

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleBusinessException(BusinessException exception){
        return new ApiErrors(exception);


    }
}



