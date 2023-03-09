package br.com.alurafood.pagamentos1.service;

import br.com.alurafood.pagamentos1.dto.PagamentoDto;
import br.com.alurafood.pagamentos1.model.Pagamento;
import br.com.alurafood.pagamentos1.model.Status;
import br.com.alurafood.pagamentos1.repository.PagamentoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;


import javax.persistence.EntityNotFoundException;


@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    //metodo retorna ja paginado os objetos do tipo DTO, ou seja:
    //pega todos os pagamentos e devolve pra mim;
    //depois mapeia novamente para DTO (repositorio so entende pagamento e nao pagamentoDTO)
    public Page<PagamentoDto> obterTodos(Pageable paginacao) {
        return repository
                .findAll(paginacao)
                .map(p -> modelMapper.map(p, PagamentoDto.class));
    }

    //recebe um id;
    //localiza no repositorio;
    //caso nao encontrar retorna, retorna uma exeption;
    //caso encontrou, retorna de pagamento para pagamentoDTO
    public PagamentoDto obterPorId(Long id) {
        Pagamento pagamento = repository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        return modelMapper.map(pagamento, PagamentoDto.class);
    }

    //chega uma estrutura JASON - pagamentoDto;
    // leio o pagamento, transformar DTO p/ pagamento;
    // muda o Status (CRIADO);
    // salva no banco;
    // pego o pagamento, transformo para DTO;
    // devolver para classe controller.
    public PagamentoDto criarPagamento(PagamentoDto dto) {
        Pagamento pagamento = modelMapper.map(dto, Pagamento.class);
        pagamento.setStatus(Status.CRIADO);
        repository.save(pagamento);

        return modelMapper.map(pagamento, PagamentoDto.class);
    }

    //esse metodo precisamos de dois paramentros (id e PagamentoDto como dados para alteracao)
    //apos mapear para pagamento, que e a classe que o repository entende
    //setar o id, ajustar e fazer alteracao no banco
    //devolvendo novamente como pagamentoDto
    public PagamentoDto atualizarPagamento(Long id, PagamentoDto dto) {
        Pagamento pagamento = modelMapper.map(dto, Pagamento.class);
        pagamento.setId(id);
        pagamento = repository.save(pagamento);

        return modelMapper.map(pagamento, PagamentoDto.class);
    }

    public void excluirPagamento(Long id) {
        repository.deleteById(id);
    }

}
