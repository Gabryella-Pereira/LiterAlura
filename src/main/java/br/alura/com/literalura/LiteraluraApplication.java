package br.alura.com.literalura;

import br.alura.com.literalura.repositorio.AutorRepository;
import br.alura.com.literalura.repositorio.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private BookRepository livroRepository;

    public static void main(String[] args) {
        SpringApplication.run(LiteraluraApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        br.alura.com.literalura.principal.Principal principal = new br.alura.com.literalura.principal.Principal(livroRepository, autorRepository);
        principal.exibirMenu();
    }
}
