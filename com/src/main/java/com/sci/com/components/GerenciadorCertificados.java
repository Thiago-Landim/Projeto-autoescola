package com.sci.com.components;

import java.time.LocalDate;
import java.util.List;

import com.sci.com.entities.InstrutoresEntity;
import com.sci.com.repositories.InstrutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class GerenciadorCertificados {

    @Autowired
    private InstrutorRepository repository;

    @Autowired
    private JavaMailSender javaMailSender;

    private static final int VALIDADE_CERTIFICADO_ANOS = 1;

    public void verificarCertificados(List<InstrutoresEntity> instrutores) {
        LocalDate hoje = LocalDate.now();
        for (InstrutoresEntity instrutor : instrutores) {
            LocalDate dataCertificado = instrutor.getDataCertificado();
            if (dataCertificado.plusYears(VALIDADE_CERTIFICADO_ANOS).isBefore(hoje)) {
                instrutor.setFuncionarioAtivo(false);
                enviarEmailExpiracao(instrutor);
            }
        }
        repository.saveAll(instrutores);
    }

    private void enviarEmailExpiracao(InstrutoresEntity instrutor) {
        String destinatario = instrutor.getEmail();
        String assunto = "Certificado Expirado";
        String mensagem = "Prezado " + instrutor.getNomeInstrutor() + ",\n\n" +
                "Informamos que seu certificado expirou e seu status foi atualizado para inativo.\n\n" +
                "Atenciosamente,\n" +
                "Auto Escola";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("seu.email@gmail.com"); // Usando o e-mail configurado no properties
        message.setTo(destinatario);
        message.setSubject(assunto);
        message.setText(mensagem);

        try {
            javaMailSender.send(message);
            System.out.println("E-mail enviado para " + destinatario);
        } catch (Exception e) {
            e.printStackTrace();
            // Em uma aplicação real, você deve logar a exceção ou tratá-la de acordo
        }
    }
}
