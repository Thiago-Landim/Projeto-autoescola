package com.sci.com.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "TB_Instrutores")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class InstrutoresEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInstrutor;

    @Column(name = "Nome_Instrutor")
    private String nomeInstrutor;
    @Column(name = "Certificado_Expira_EM")
    private LocalDate dataCertificado;
    @Column(name = "Endereco")
    private String endereco;
    @Column(name = "Telefone")
    private String telefone;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "Funcionario_ATIVO")
    private boolean funcionarioAtivo;





}
