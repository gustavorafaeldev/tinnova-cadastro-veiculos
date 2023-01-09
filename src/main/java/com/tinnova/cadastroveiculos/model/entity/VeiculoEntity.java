package com.tinnova.cadastroveiculos.model.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "veiculos")
@Data
public class VeiculoEntity {

    @Id
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
