package com.tinnova.cadastroveiculos.model.response;

import lombok.Data;

@Data
public class VeiculoResponse {

    private String id;
    private String veiculo;
    private String marca;
    private String cor;
    private int ano;
    private String descricao;
    private boolean vendido;
    private Long criacao;
    private Long atualizacao;
}
