package br.com.tinnova.lobao.apiveiculos.controller;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.tinnova.lobao.apiveiculos.model.Veiculo;
import br.com.tinnova.lobao.apiveiculos.repository.VeiculoRepository;
import lombok.AllArgsConstructor;

/**
 * VeiculoController
 */
@RestController
@RequestMapping("/api/veiculos")
@AllArgsConstructor
public class VeiculoController {

    private final VeiculoRepository veiculoRepository;

    @GetMapping
    public List<Veiculo> findAll() {
        return veiculoRepository.findAll();
    }

    @GetMapping(path = "/find")
    public List<Veiculo> findVeiculoByVendido(
        @RequestParam(required = false, name = "marca") String marca,
        @RequestParam(required = false, name = "anoInicial") Integer anoInicial,
        @RequestParam(required = false, name = "anoFinal") Integer anoFinal,
        @RequestParam(required = false, name = "dia") Integer dia,
        @RequestParam(required = false, name = "isVendido") Boolean isVendido) {
        
        if (marca != null) {
            return veiculoRepository.findVeiculoByMarca(marca);
        } else if (isVendido != null) {
            return veiculoRepository.findVeiculoByVendido(isVendido);
        } else if (anoInicial != null && anoFinal != null) {
            return veiculoRepository.findVeiculoByAnoBetween(anoInicial, anoFinal);
        } else if (dia != null) {
            return veiculoRepository.findVeiculoByCreatedBetween(LocalDateTime.now().minusDays(dia), LocalDateTime.now());
        }
        return findAll(); 
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<Veiculo> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(veiculoRepository.findById(id).get());
    }

    @PostMapping
    public Veiculo save(@RequestBody Veiculo veiculo) {
        return veiculoRepository.save(veiculo);
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<Veiculo> update(@PathVariable("id") long id, @RequestBody Veiculo veiculo) {
        return veiculoRepository.findById(id)
            .map(v -> {
                v.setVeiculo(veiculo.getVeiculo());
                v.setAno(veiculo.getAno());
                v.setDescricao(veiculo.getDescricao());
                v.setMarca(veiculo.getMarca());
                v.setVendido(veiculo.getVendido());
                Veiculo update = veiculoRepository.save(v);
                return ResponseEntity.ok().body(update);
            }).orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Veiculo> update(@PathVariable("id") long id, @RequestBody boolean vendido) {
        return veiculoRepository.findById(id)
            .map(v -> {
                v.setVendido(vendido);
                Veiculo update = veiculoRepository.save(v);
                return ResponseEntity.ok().body(update);
            }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = {"/{id}"})
    public void delete(@PathVariable Long id) {
        veiculoRepository.deleteById(id);
    }
}