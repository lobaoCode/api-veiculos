package br.com.tinnova.lobao.apiveiculos.repository;

import java.time.LocalDateTime;
import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tinnova.lobao.apiveiculos.model.Veiculo;


public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

    List<Veiculo> findVeiculoByVendido (boolean vendido);
    List<Veiculo> findVeiculoByMarca(String marca);
    List<Veiculo> findVeiculoByAnoBetween(int anoInicial, int anoFinal);
    List<Veiculo> findVeiculoByCreatedBetween(LocalDateTime diaInicial, LocalDateTime diaFinal);
}
