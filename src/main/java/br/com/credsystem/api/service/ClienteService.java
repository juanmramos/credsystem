package br.com.credsystem.api.service;

import br.com.credsystem.api.Dto.CartaoDTO;
import br.com.credsystem.api.Dto.ClienteDTO;
import br.com.credsystem.api.entity.CartaoEntity;
import br.com.credsystem.api.entity.ClienteEntity;
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
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Transactional
    public Optional<ClienteDTO> criarCliente(ClienteDTO clienteDTO) {
        ClienteEntity clienteEntity = clienteRepository.save(modelMapper.map(clienteDTO, ClienteEntity.class));
        ClienteDTO result = modelMapper.map(clienteEntity, ClienteDTO.class);
        return Optional.of(result);
    }

    @Transactional
    public Optional<ClienteDTO  > atualizarCliente(Long id, ClienteDTO clienteDTO) {
        Optional<ClienteEntity> optionalClienteEntity = clienteRepository.findById(id);
        ClienteEntity clienteEntity = clienteRepository.save(modelMapper.map(clienteDTO, ClienteEntity.class));
        optionalClienteEntity.get().setIdade(clienteEntity.getIdade());
        optionalClienteEntity.get().setNome(clienteEntity.getNome());
        ClienteDTO result = modelMapper.map(optionalClienteEntity.get(), ClienteDTO.class);
        return Optional.of(result);
    }

    public Optional<List<ClienteDTO>> buscarClientes() {
        List<ClienteEntity> cartaoEntityList = clienteRepository.findAll();
        List<ClienteDTO> cartaoDTOList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(cartaoEntityList)) {
            cartaoEntityList.stream().forEach(clienteEntity -> {
                ClienteDTO clienteDTO = modelMapper.map(clienteEntity, ClienteDTO.class);
                cartaoDTOList.add(clienteDTO);
            });
        }
        return Optional.of(cartaoDTOList);
    }

    public Optional<ClienteDTO> buscarClientePorId(Long id) {
        Optional<ClienteEntity> optionalClienteEntity = clienteRepository.findById(id);
        ClienteDTO clienteDTO = modelMapper.map(optionalClienteEntity.get(), ClienteDTO.class);
        return Optional.of(clienteDTO);
    }

    public void deletarCliente(Long id) {
        Optional<ClienteEntity> optionalClienteEntity = clienteRepository.findById(id);
        clienteRepository.delete(optionalClienteEntity.get());
    }
}