package com.tinnova.cadastroveiculos.service;

import com.tinnova.cadastroveiculos.model.request.VeiculoRequest;
import com.tinnova.cadastroveiculos.model.response.VeiculoResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VeiculoServiceTest {

    private final String url = "http://localhost:8080/veiculos";
    @Autowired
    private VeiculoService veiculoService;

    public VeiculoServiceTest() {
    }

    @Test
    public void testSaveVeiculo() {
        RestTemplate restTemplate = new RestTemplate();

        VeiculoRequest veiculo = new VeiculoRequest();
        veiculo.setVeiculo("Gol");
        veiculo.setMarca("Volkswagen");
        veiculo.setCor("Branco");
        veiculo.setAno(2020);
        veiculo.setDescricao("Veículo seminovo, com poucos km rodados");
        veiculo.setVendido(false);
        veiculo.setCriacao(System.currentTimeMillis());

        VeiculoResponse veiculoResponse = veiculoService.create(veiculo);

        ResponseEntity<VeiculoResponse> response = restTemplate.postForEntity(url, veiculo, VeiculoResponse.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(veiculoResponse.getId());
    }

    @Test
    public void testExclusaoVeiculo() {
        VeiculoRequest veiculo = new VeiculoRequest();
        veiculo.setVeiculo("Carro Teste");
        veiculo.setMarca("Marca Teste");
        veiculo.setCor("Cor Teste");
        veiculo.setAno(2020);
        veiculo.setDescricao("Descrição Teste");
        veiculo.setVendido(false);
        veiculo.setCriacao(System.currentTimeMillis());


        VeiculoResponse veiculoResponse = veiculoService.create(veiculo);

        veiculoService.deleteById(veiculoResponse.getId());

        VeiculoResponse veiculoExcluido = veiculoService.findById(veiculoResponse.getId());

        assertNull(veiculoExcluido);
    }
}
