package br.com.Spa.controller;

import br.com.Spa.model.Avaliacao;
import br.com.Spa.repository.AvaliacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/avaliacao")
public class AvaliacaoController {
    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @GetMapping
    public List<Avaliacao> getAvaliacao() {
       return avaliacaoRepository.findAll();
    }

    @PostMapping("/adicionar")
    public void adicionarAvaliacao(@RequestBody Avaliacao avaliacao) {
        avaliacaoRepository.save(avaliacao);
    }

    @DeleteMapping("/remover/{id}")
    public void removerAvaliacao(@PathVariable int id) {
        if(avaliacaoRepository.existsById(id)) {
            avaliacaoRepository.deleteById(id);

        }
    }
}
