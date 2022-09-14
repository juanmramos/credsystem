package br.com.credsystem.api.controller;

import br.com.credsystem.api.Dto.CartaoDTO;
import br.com.credsystem.api.Dto.ClienteDTO;
import br.com.credsystem.api.entity.ClienteEntity;
import br.com.credsystem.api.service.ClienteService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    public static final Logger LOG = LoggerFactory.getLogger(ClienteController.class);
    @Autowired
    private ClienteService clienteService;

    @ApiOperation(value = "Realiza a buscas de todos os clientes.")
    @ApiResponses({ @ApiResponse(code = 200, message = "Ok", response = ClienteDTO.class),
            @ApiResponse(code = 404, message = "Clientes não encontrado", response = String.class),
            @ApiResponse(code = 422, message = "Erro de validação nos campos", response = String.class),
            @ApiResponse(code = 500, message = "Erro interno do servidor", response = String.class), })
    @GetMapping("clientes")
    public ResponseEntity<?> getClientes() {
        return new ResponseEntity<>(clienteService.buscarClientes(), HttpStatus.OK);
    }

    @ApiOperation(value = "Realiza a busca dos cliente por id.")
    @ApiResponses({ @ApiResponse(code = 200, message = "Ok", response = ClienteDTO.class),
            @ApiResponse(code = 404, message = "Cliente não encontrado", response = String.class),
            @ApiResponse(code = 422, message = "Erro de validação nos campos", response = String.class),
            @ApiResponse(code = 500, message = "Erro interno do servidor", response = String.class), })
    @GetMapping("/{id}")
    public ResponseEntity<?> getClienteId(@PathVariable Long id) {
        return new ResponseEntity<>(clienteService.buscarClientePorId(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Realiza o cadastro de um novo cliente.")
    @ApiResponses({ @ApiResponse(code = 201, message = "Ok", response = ClienteEntity.class),
            @ApiResponse(code = 404, message = "Cliente não encontrado", response = String.class),
            @ApiResponse(code = 422, message = "Erro de validação nos campos", response = String.class),
            @ApiResponse(code = 500, message = "Erro interno do servidor", response = String.class), })
    @PostMapping(path = "/criar-cliente", produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> criarCliente(@Valid @RequestBody ClienteDTO clienteDTO) {
        return new ResponseEntity<>(clienteService.criarCliente(clienteDTO), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Realiza o atualizacao do cartao.")
    @ApiResponses({ @ApiResponse(code = 201, message = "Ok", response = ClienteDTO.class),
            @ApiResponse(code = 404, message = "Cartao não encontrado", response = String.class),
            @ApiResponse(code = 422, message = "Erro de validação nos campos", response = String.class),
            @ApiResponse(code = 500, message = "Erro interno do servidor", response = String.class), })
    @PutMapping(path = "/atualizar-cliente/{id}", produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> atualizarCliente(@RequestParam Integer id, @RequestBody ClienteDTO clienteDTO) {
        Long idLong = Long.valueOf(id);
        return new ResponseEntity<>(clienteService.atualizarCliente(idLong,clienteDTO), HttpStatus.OK);
    }

    @ApiOperation(value = "Deletar o cliente.")
    @ApiResponses({ @ApiResponse(code = 200, message = "Ok", response = String.class),
            @ApiResponse(code = 404, message = "Imóvel não encontrado", response = String.class),
            @ApiResponse(code = 422, message = "Erro de validação nos campos", response = String.class),
            @ApiResponse(code = 500, message = "Erro interno do servidor", response = String.class), })
    @DeleteMapping(path = "/deletar-cliente/{id}")
    public ResponseEntity<?> deletarCliente(@PathVariable Long id) {
        clienteService.deletarCliente(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
