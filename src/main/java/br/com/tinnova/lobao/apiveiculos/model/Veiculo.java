package br.com.tinnova.lobao.apiveiculos.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

/**
 * Veiculo
 */
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Veiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("_id")
    private Long id;

    @Column(length = 20, nullable = false)
    private String veiculo;

    @Column(length = 20, nullable = false)
    private String marca;

    @Column(length = 5, nullable = false)
    private Integer ano;

    @Column(name = "descricao", length = 200, nullable = false)
    private String descricao;

    @Column(nullable = false)
    private Boolean vendido;
    
    @Column(nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime created;

    @Column(nullable = false)
    @LastModifiedDate
    private LocalDateTime updated;
    
}