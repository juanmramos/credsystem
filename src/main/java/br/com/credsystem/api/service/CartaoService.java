package br.com.credsystem.api.service;

import br.com.credsystem.api.Dto.CartaoDTO;
import br.com.credsystem.api.Dto.ClienteDTO;
import br.com.credsystem.api.entity.CartaoEntity;
import br.com.credsystem.api.entity.ClienteEntity;
import br.com.credsystem.api.repository.CartaoRepository;
import br.com.credsystem.api.repository.ClienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartaoService {

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public CartaoService(CartaoRepository cartaoRepository) {
        this.cartaoRepository = cartaoRepository;
    }

    @Transactional
    public Optional<CartaoDTO> criarCartao(CartaoDTO cartaoDTO) {
        CartaoEntity cartaoEntity = cartaoRepository.save(modelMapper.map(cartaoDTO, CartaoEntity.class));
        CartaoDTO result = modelMapper.map(cartaoEntity, CartaoDTO.class);
        return Optional.of(result);
    }

    @Transactional
    public Optional<CartaoDTO> atualizarCartao(Long id, CartaoDTO cartaoDTO) {
        Optional<CartaoEntity> optionalCartaoEntity = cartaoRepository.findById(id);
        CartaoEntity cartaoEntity = cartaoRepository.save(modelMapper.map(cartaoDTO, CartaoEntity.class));
        optionalCartaoEntity.get().setNumeroCartao(cartaoEntity.getNumeroCartao());
        optionalCartaoEntity.get().setDataVaidadeCartao(cartaoEntity.getDataVaidadeCartao());
        optionalCartaoEntity.get().setCvv(cartaoEntity.getCvv());
        CartaoDTO result = modelMapper.map(optionalCartaoEntity.get(), CartaoDTO.class);
        return Optional.of(result);
    }

    public Optional<List<CartaoDTO>> buscarTodosCartoes() {
        List<CartaoEntity> cartaoEntityList = cartaoRepository.findAll();
        List<CartaoDTO> cartaoDTOList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(cartaoEntityList)) {
            cartaoEntityList.stream().forEach(cartaoEntity -> {
                CartaoDTO cartaoDTO = modelMapper.map(cartaoEntity, CartaoDTO.class);
                cartaoDTOList.add(cartaoDTO);
            });
        }
        return Optional.of(cartaoDTOList);
    }

    public Optional<CartaoDTO> buscarCartaoPorId(Long id) {
        Optional<CartaoEntity> optionalCartaoEntity = cartaoRepository.findById(id);
        CartaoDTO cartaoDTO = modelMapper.map(optionalCartaoEntity.get(), CartaoDTO.class);
        return Optional.of(cartaoDTO);
    }

    public void deletarCartao(Long id) {
        Optional<CartaoEntity> optionalCartaoEntity = cartaoRepository.findById(id);
        cartaoRepository.delete(optionalCartaoEntity.get());
    }
}
