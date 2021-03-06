package br.com.crescer.wallet.service.service;

import br.com.crescer.wallet.entity.Situacao;
import br.com.crescer.wallet.entity.Usuario;
import br.com.crescer.wallet.service.dto.UsuarioDTO;
import br.com.crescer.wallet.service.repository.UsuarioRepository;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Hedo
 */
@Service
public class UsuarioService {

    private static final Logger LOG = Logger.getLogger(UsuarioService.class.getName());
    @Autowired
    UsuarioRepository repository;

    public Usuario findOneByDsUserName(String dsUserName) {
        return repository.findUsuarioByDsUserName(dsUserName);
    }

    public List<UsuarioDTO> findAllActiveReturningDTOs() {
        List<UsuarioDTO> list = new ArrayList<>();
        try {
            repository.findAllByDsSituacaoNot(Situacao.INATIVO).stream().map((u) -> {
                return buildDTO(u);
            }).forEach((dto) -> {
                list.add(dto);
            });
            return list;

        } catch (NullPointerException e) {
            LOG.info(e.getMessage());
            return null;
        }
    }

    public List<UsuarioDTO> findAllReturningDTOs() {
        try {
            List<UsuarioDTO> list = new ArrayList<>();
            repository.findAll().stream().map((u) -> {
                return buildDTO(u);
            }).forEach((dto) -> {
                list.add(dto);
            });
            return list;
        } catch (NullPointerException e) {
            LOG.error(e.getMessage());
            return null;
        }
    }

    public UsuarioDTO salvarUsuario(UsuarioDTO dto) {
        try {
            if(dto.getId() != null && dto.getId() > 0){
                Usuario cadastrado = repository.findOne(dto.getId());
                cadastrado.setDsEmail(dto.getEmail());
                cadastrado.setDsUserName(dto.getUsername());
                cadastrado.setNmUsuario(dto.getNome());
                cadastrado.setTpPermissao(dto.getPermissao());
                return new UsuarioDTO(repository.save(cadastrado));
            }
            Usuario user = dto.buildUsuario();
            return new UsuarioDTO(repository.save(user));
        } catch (NullPointerException e) {
            LOG.error(e.getMessage());
            return null;
        }

    }

    public boolean inativarUsuario(Long idUsuario) {
        try {
            Usuario u = repository.findOne(idUsuario);
            u.setDsSituacao(Situacao.INATIVO);
            repository.save(u);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Usuario findById(long idUsuario) {
        return repository.findOne(idUsuario);
    }

    public UsuarioDTO findByIdReturningDTO(long idUsuario) {
        return buildDTO(repository.findOne(idUsuario));
    }

    private UsuarioDTO buildDTO(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getIdUsuario());
        dto.setNome(usuario.getNmUsuario());
        dto.setEmail(usuario.getDsEmail());
        dto.setUsername(usuario.getDsUserName());
        dto.setSituacao(usuario.getDsSituacao());
        dto.setPermissao(usuario.getTpPermissao());
        return dto;
    }

    public boolean checkUsernameAvailability(String username, long id) {
        Usuario usuario = repository.findOne(id);
        if (usuario == null) {
            return repository.findUsuarioByDsUserName(username) == null;
        } else {
            return usuario.getDsUserName().equals(username) ? true : repository.findUsuarioByDsUserName(username) == null;
        }
    }
}
