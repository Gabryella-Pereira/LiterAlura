package br.alura.com.literalura.repositorio;

import br.alura.com.literalura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Livro, Long> {

    Optional<Livro> findByTituloContainingIgnoreCase(String titulo);

    List<Livro> findByIdiomaIgnoreCase(String idioma);

    List<Livro> findTop10ByOrderByNumeroDownloadsDesc();

    @Query("SELECT l FROM Livro l JOIN l.autores a WHERE a.nome ILIKE %:nomeAutor%")
    List<Livro> findByAutoresNomeContainingIgnoreCase(@Param("nomeAutor") String nomeAutor);

    List<Livro> findAllByOrderByTituloAsc();

    @Query("SELECT COUNT(l) FROM Livro l WHERE l.idioma = :idioma")
    Long countByIdioma(@Param("idioma") String idioma);
}