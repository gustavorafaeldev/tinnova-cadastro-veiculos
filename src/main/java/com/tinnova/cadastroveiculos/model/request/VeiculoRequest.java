package com.tinnova.cadastroveiculos.model.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VeiculoRequest {

    private String veiculo;
    private String marca;
    private String cor;
    private int ano;
    private String descricao;
    private boolean vendido;
    private Long criacao;
}
