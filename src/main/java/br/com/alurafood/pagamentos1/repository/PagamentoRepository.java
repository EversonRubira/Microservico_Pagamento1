package br.com.alurafood.pagamentos1.repository;

import br.com.alurafood.pagamentos1.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;



public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
}
