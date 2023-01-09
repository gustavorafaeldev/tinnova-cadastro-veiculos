package com.tinnova.cadastroveiculos.controller;

import com.tinnova.cadastroveiculos.model.dto.VeiculoPatchDTO;
import com.tinnova.cadastroveiculos.model.request.VeiculoRequest;
import com.tinnova.cadastroveiculos.model.response.VeiculoResponse;
import com.tinnova.cadastroveiculos.service.VeiculoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("veiculos")
@RequiredArgsConstructor
@CrossOrigin("*")
public class VeiculoController {

    private final VeiculoService service;

    @PostMapping
    public ResponseEntity<VeiculoResponse> create(@RequestBody VeiculoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @GetMapping
    public ResponseEntity<List<VeiculoResponse>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("findByParams")
    public ResponseEntity<List<VeiculoResponse>> findByParams(@RequestParam("marca") String marca,
                                                              @RequestParam("ano") int ano,
                                                              @RequestParam("cor") String cor) {

        return ResponseEntity.ok().body(service.findByParams(marca, ano, cor));
    }

    @GetMapping("{id}")
    public ResponseEntity<VeiculoResponse> findById(@PathVariable String id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<VeiculoResponse> update(@PathVariable String id, @RequestBody VeiculoRequest request) {
        return ResponseEntity.ok().body(service.update(id, request));
    }

    @PatchMapping("{id}")
    public ResponseEntity<VeiculoResponse> updatePatch(@PathVariable String id, @RequestBody VeiculoPatchDTO request) {
        return ResponseEntity.ok().body(service.updatePatch(id, request));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        service.deleteById(id);
        return ResponseEntity.status(202).build();
    }

}
