package com.tinnova.cadastroveiculos.repository;

import com.tinnova.cadastroveiculos.model.entity.VeiculoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VeiculoRepository extends MongoRepository<VeiculoEntity, String> {

    List<VeiculoEntity> findAllByMarcaAndAnoAndCor(String marca, int ano, String cor);

}
