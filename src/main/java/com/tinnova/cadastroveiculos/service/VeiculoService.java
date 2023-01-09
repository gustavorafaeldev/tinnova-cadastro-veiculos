package com.tinnova.cadastroveiculos.service;

import com.tinnova.cadastroveiculos.model.common.VeiculoMapper;
import com.tinnova.cadastroveiculos.model.dto.VeiculoPatchDTO;
import com.tinnova.cadastroveiculos.model.entity.VeiculoEntity;
import com.tinnova.cadastroveiculos.model.request.VeiculoRequest;
import com.tinnova.cadastroveiculos.model.response.VeiculoResponse;
import com.tinnova.cadastroveiculos.repository.VeiculoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VeiculoService {

    private final VeiculoRepository repository;
    private final VeiculoMapper mapper;

    public VeiculoResponse create(VeiculoRequest request) {
        VeiculoEntity veiculoEntity = mapper.toVeiculoEntity(request);
        veiculoEntity.setCriacao(System.currentTimeMillis());
        repository.save(veiculoEntity);
        return mapper.toVeiculoResponse(veiculoEntity);
    }

    public List<VeiculoResponse> findAll() {
        List<VeiculoEntity> veiculoEntityList = repository.findAll();
        return veiculoEntityList.stream().map(mapper::toVeiculoResponse).collect(Collectors.toList());
    }

    public List<VeiculoResponse> findByParams(String marca, int ano, String cor) {
        List<VeiculoEntity> veiculoEntityList = repository.findAllByMarcaAndAnoAndCor(marca, ano, cor);
        return veiculoEntityList.stream().map(mapper::toVeiculoResponse).collect(Collectors.toList());
    }

    public VeiculoResponse findById(String id) {
        Optional<VeiculoEntity> optionalVeiculoEntity = repository.findById(id);
        return optionalVeiculoEntity.map(mapper::toVeiculoResponse).orElse(null);
    }

    public VeiculoResponse update(String id, VeiculoRequest requestBody) {
        VeiculoResponse veiculoResponse = findById(id);
        VeiculoEntity entity = mapper.reponseToEntity(veiculoResponse);
        VeiculoEntity veiculoEntityUpdate = updateData(entity, requestBody);
        VeiculoEntity veiculoEntitySaved = repository.save(veiculoEntityUpdate);
        return mapper.toVeiculoResponse(veiculoEntitySaved);
    }

    public VeiculoResponse updatePatch(String id, VeiculoPatchDTO dto) {
        VeiculoResponse veiculoResponse = findById(id);
        VeiculoEntity entity = mapper.reponseToEntity(veiculoResponse);
        entity.setVendido(dto.isVendido());
        entity.setAtualizacao(System.currentTimeMillis());
        VeiculoEntity save = repository.save(entity);
        return mapper.toVeiculoResponse(save);
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }


    public VeiculoEntity updateData(VeiculoEntity entity, VeiculoRequest requestBody) {
        entity.setVeiculo(requestBody.getVeiculo());
        entity.setMarca(requestBody.getMarca());
        entity.setCor(requestBody.getCor());
        entity.setAno(requestBody.getAno());
        entity.setDescricao(requestBody.getDescricao());
        entity.setVendido(requestBody.isVendido());
        entity.setCriacao(entity.getCriacao());
        entity.setAtualizacao(System.currentTimeMillis());
        return entity;
    }
}
