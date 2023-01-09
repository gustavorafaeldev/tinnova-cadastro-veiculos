package com.tinnova.cadastroveiculos.model.dto;

import lombok.Data;

@Data
public class VeiculoPatchDTO {

    private boolean vendido;
    private Long criacao;
    private Long atualizacao;
}
