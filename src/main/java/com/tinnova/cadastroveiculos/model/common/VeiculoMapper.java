package com.tinnova.cadastroveiculos.model.common;

import com.tinnova.cadastroveiculos.model.entity.VeiculoEntity;
import com.tinnova.cadastroveiculos.model.request.VeiculoRequest;
import com.tinnova.cadastroveiculos.model.response.VeiculoResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VeiculoMapper {

    private final ModelMapper mapper;

    public VeiculoResponse toVeiculoResponse(VeiculoEntity veiculoEntity) {
        return mapper.map(veiculoEntity, VeiculoResponse.class);
    }

    public VeiculoEntity toVeiculoEntity(VeiculoRequest veiculoRequest) {
        return mapper.map(veiculoRequest, VeiculoEntity.class);
    }

    public VeiculoEntity reponseToEntity(VeiculoResponse response) {
        return mapper.map(response, VeiculoEntity.class);
    }
}
