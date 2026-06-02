package br.com.Spa.controller;

import br.com.Spa.model.Servico;
import br.com.Spa.repository.ServicoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servicos")
public class ServicoController {

    @Autowired
    private ServicoRepository servicoRepository;

    @GetMapping
    public List<Servico> listarServicos () {
        return  servicoRepository.findAll();
    }

    @PostMapping("/cadastrar")
    public void cadastrarServico(@RequestBody Servico servico) {
        servicoRepository.save(servico);
    }

    @DeleteMapping("/remover/{id}")
    public void removerServico(@PathVariable Long id) {
        if(servicoRepository.existsById(id)) {
            servicoRepository.deleteById(id);
        }
    }

}
