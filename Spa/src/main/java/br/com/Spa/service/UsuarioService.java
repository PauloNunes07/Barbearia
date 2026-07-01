package br.com.Spa.service;

import br.com.Spa.dto.UsuarioDTO;
import br.com.Spa.model.Usuario;
import br.com.Spa.repository.UsuarioRepository;
import br.com.Spa.util.enuns.TipoUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario cadastrar(UsuarioDTO usuarioDTO) {

        if (usuarioRepository.findByLogin(usuarioDTO.getLogin()).isPresent()) {
            throw new RuntimeException("Login já existe");
        }

        Usuario usuario = toEntity(usuarioDTO);
        return usuarioRepository.save(usuario);
    }

    public Usuario buscarPorId(Long id) {

        return usuarioRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Usuario não encontrado"));

    }

    public void remover(Long id) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        usuarioRepository.delete(usuario);
    }

    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    public Usuario alterarSenha(Long id, String senhaAtual, String novaSenha) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!passwordEncoder.matches(senhaAtual, usuario.getSenha())) {
            throw new RuntimeException("Senha atual incorreta");
        }

        usuario.setSenha(passwordEncoder.encode(novaSenha));

       return usuarioRepository.save(usuario);
    }

    private Usuario toEntity(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setLogin(dto.getLogin());
        usuario.setSenha(passwordEncoder.encode(dto.getSenha()));
        usuario.setTipoUsuario(dto.getTipoUsuario());
        return usuario;
    }
}