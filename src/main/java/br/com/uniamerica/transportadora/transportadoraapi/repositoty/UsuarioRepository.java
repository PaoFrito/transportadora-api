package br.com.uniamerica.transportadora.transportadoraapi.repositoty;

import br.com.uniamerica.transportadora.transportadoraapi.entity.Grupo;
import br.com.uniamerica.transportadora.transportadoraapi.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.math.BigDecimal;
import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

    @Query("from Usuario where id = :id")
    public List<Usuario> findById();
    @Query("SELECT usuario FROM Usuario usuario WHERE usuario.ativo = true")
    public List<Usuario> findByUsuariosAtivos();

    @Query("from Usuario where percGanho = :percGanho")
    public List<Usuario> findByPercGanho(@Param("percGanho") BigDecimal percGanho);

    @Query("from Usuario where login = :login")
    public List<Usuario> findByLogin(@Param("login") String login);

    @Query(" from Usuario where senha = :senha")
    public List<Usuario> findBySenha(@Param("senha") String senha);

    @Query("from Usuario where grupo = :grupo")
    public List<Usuario> findByGrupo(@Param("grupo") Grupo grupo);

    @Query("from Usuario where nome = :nome")
    public List<Usuario> findByNome(@Param("nome") String nome);

    @Query("from Usuario where cpf = :cpf")
    public List<Usuario> findByCpf(@Param("cpf") String cpf);

    @Query("from Usuario where telefone = :telefone")
    public List<Usuario> findByTelefone(@Param("telefone") String telefone);

    @Query("from Usuario where dataNascimento = :dataNascimento")
    public List<Usuario> findByDataNascimento(@Param("dataNascimento") Data dataNascimento);

    @Query("from Usuario where endereco = :endereco")
    public List<Usuario> findByEndereco(@Param("endereco") String endereco);

    @Query("from Usuario where observacao = :observacao")
    public List<Usuario> findByObservacao(@Param("observacao") String observacao);

}
