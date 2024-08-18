package com.sci.com.service;

import com.sci.com.components.GerenciadorCertificados;
import com.sci.com.entities.InstrutoresEntity;
import com.sci.com.repositories.InstrutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class InstrutorService {


    @Autowired
    private InstrutorRepository instrutorRepository;

    @Autowired
    private GerenciadorCertificados gerenciadorCertificados; // Injeção de dependência
    public List<InstrutoresEntity> toList(){
        return instrutorRepository.findAll();
    }

    public InstrutoresEntity findByIdOrThrowBadRequestException(Long id){
        return instrutorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Instrutor não Encontrado"));
    }

    public InstrutoresEntity buscaID(Long id){
        return findByIdOrThrowBadRequestException(id);
    }

    public InstrutoresEntity salvarInstrutor(InstrutoresEntity entity){
        return instrutorRepository.save(entity);
    }

    public void verificarCertificadosExpirados() {
        List<InstrutoresEntity> todos = toList();

        // Lógica para verificar certificados expirados e enviar e-mail
        LocalDate hoje = LocalDate.now();
        for (InstrutoresEntity instrutor : todos) {
            if (instrutor.getDataCertificado() != null) {
                LocalDate dataExpiracao = instrutor.getDataCertificado().plusDays(365);
                long diasRestantes = ChronoUnit.DAYS.between(hoje, dataExpiracao);

                if (diasRestantes <= 30) { // Enviar e-mail se expirar em 30 dias ou menos
                    enviarEmailRenovacao(instrutor);
                }
            }
        }
    }

    private void enviarEmailRenovacao(InstrutoresEntity instrutor) {
        // Lógica para enviar e-mail
        String assunto = "Renovação de Certificado";
        String corpo = String.format("Olá %s, seu certificado está prestes a expirar em %s. Por favor, renove o certificado.",
                instrutor.getNomeInstrutor(), instrutor.getDataCertificado().plusDays(365));

        // Simulação de envio de e-mail
        System.out.println("Enviando e-mail para: " + instrutor.getEmail());
        System.out.println("Assunto: " + assunto);
        System.out.println("Corpo: " + corpo);
    }
}