package br.alura.com.literalura.repositorio;

import br.alura.com.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {

    List<Autor> findByNomeContainingIgnoreCase(String nome);

    Optional<Autor> findByNomeIgnoreCase(String nome);

    List<Autor> findAllByOrderByNomeAsc();

    @Query("SELECT a FROM Autor a WHERE (a.anoNascimento <= :ano) AND (a.anoFalecimento IS NULL OR a.anoFalecimento >= :ano)")
    List<Autor> findAutoresVivosNoAno(@Param("ano") Integer ano);

    @Query("SELECT a FROM Autor a WHERE a.anoNascimento BETWEEN :anoInicio AND :anoFim")
    List<Autor> findByAnoNascimentoBetween(@Param("anoInicio") Integer anoInicio, @Param("anoFim") Integer anoFim);

    @Query("SELECT a FROM Autor a WHERE a.anoFalecimento BETWEEN :anoInicio AND :anoFim")
    List<Autor> findByAnoFalecimentoBetween(@Param("anoInicio") Integer anoInicio, @Param("anoFim") Integer anoFim);

    List<Autor> findByAnoFalecimentoIsNull();

    List<Autor> findByAnoFalecimentoIsNotNull();

    @Query("SELECT DISTINCT a FROM Autor a JOIN Livro l JOIN l.autores la WHERE la.id = a.id")
    List<Autor> findAutoresComLivros();

    @Query("SELECT COUNT(l) FROM Livro l JOIN l.autores a WHERE a.id = :autorId")
    Long countLivrosByAutorId(@Param("autorId") Long autorId);

    @Query("SELECT a, COUNT(l) as totalLivros FROM Autor a JOIN Livro l JOIN l.autores la WHERE la.id = a.id GROUP BY a ORDER BY COUNT(l) DESC")
    List<Autor> findAutoresMaisProlificos();
}