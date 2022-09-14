package br.com.credsystem.api.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CartaoDTO {
    private Long id;

    private Long numeroCartao;

    private Integer cvv;

    private String dataVaidadeCartao;
}
