package com.sci.com.DTO;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Getter
@Setter
public class InstrutoresDto {

    private Long idInstrutor;
    private int diasRenovacaoCertificado;
    private String nomeInstrutor;
    private String endereco;
    private String telefone;
    private String email;
    private boolean ativo;


}
