package com.sci.com.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "TB_Instrutores")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InstrutoresEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInstrutor;

    @Column(name = "Nome_Instrutor")
    private String nomeInstrutor;
    @Column(name = "Certificado_Expira_EM")
    private int diasRenovacaoCertificado;
    @Column(name = "Endereco")
    private String endereco;
    @Column(name = "Telefone")
    private String telefone;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "Funcionario_ATIVO")
    private boolean ativo;





}
