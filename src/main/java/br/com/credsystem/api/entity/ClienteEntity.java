package br.com.credsystem.api.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cliente", schema = "credsystem")
public class ClienteEntity implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String nome;

    private Integer idade;
}
