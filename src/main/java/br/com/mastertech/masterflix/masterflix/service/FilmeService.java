package br.com.mastertech.masterflix.masterflix.service;

import br.com.mastertech.masterflix.masterflix.model.Filme;
import br.com.mastertech.masterflix.masterflix.repository.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FilmeService<filmesModel> {
    @Autowired
    private FilmeRepository repository;

    public Filme cadastrarfilme(Filme filme) {
        return repository.save(filme);
    }

    public Iterable<Filme> listarFilmes() {
        Iterable<Filme> filmes = repository.findAll();
        return filmes;
    }

    public Filme findByNome(String nome) {
        Optional<Filme> filmes = repository.findByNome(nome);
        if(filmes.isPresent()){
            return filmes.get();
        }
        return null;
    }
}