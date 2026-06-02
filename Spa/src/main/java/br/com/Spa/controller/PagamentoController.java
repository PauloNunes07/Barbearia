package br.com.Spa.controller;

import br.com.Spa.model.Pagamento;
import br.com.Spa.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @GetMapping
    public List<Pagamento> listarPagamentos() {
        return pagamentoRepository.findAll();
    }

    @PostMapping("/adicionar")
    public void adicionarPagamento(@RequestBody Pagamento pagamento) {
        pagamentoRepository.save(pagamento);
    }

    @DeleteMapping("/remover/{id}")
    public void removerPagamento(@PathVariable int id) {
        if(pagamentoRepository.existsById(id)) {
            pagamentoRepository.deleteById(id);
        }
    }
}
