package br.com.mastertech.masterflix.masterflix.controller;

import br.com.mastertech.masterflix.masterflix.model.Filme;
import br.com.mastertech.masterflix.masterflix.service.FilmeService;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FilmeController {

    @Autowired
    public FilmeService service;

    @GetMapping
    public String index(){
        return "index";
    }

    @GetMapping("/cadastrar")
    public String telaCadastro(){
        return "cadastro";
    }

    @PostMapping("/cadastrar")
    public String cadastrarfilme (Filme filme) {
        service.cadastrarfilme(filme);
        return "cadastro";
    }

    @GetMapping("/buscar/")
    public String buscarFilmes(){
        return "buscarFilmes";
    }

    @GetMapping("/filmes")
    public ModelAndView listarfilmes(Filme filme, Model model){
        ModelAndView pagina = new ModelAndView("listarFilmes");
        Iterable<Model> filmes = service.listarFilmes();
        pagina.addObject("filmes", filmes);
        return pagina;
    }

    @GetMapping("/listarFilmes")
    public String ListarFilmes(@RequestParam("nome") String nome, Model model) {
        Filme filme = service.findByNome(nome);

        if (filme != null) {
            model.addAttribute("filme", filme);
            return "listarFilmes";
        }
        else {
            model.addAttribute("msg", "O filme" + nome + "Não foi encontrado");
            return "buscarFilmes";
        }

    }

    @GetMapping("/filmes/{nome}")
    public String ListarFilmesPorNome(@PathVariable("nome") String nome, Model model) {
        Model filmes = (Model) service.findByNome(nome);
        if (filmes != null) {
            model.addAttribute("filmes", filmes);
            return "listarFilmes";
        }
        else {
            model.addAttribute("msg", "O filme" + nome + "Não foi encontrado");
            return "buscarFilmes";
        }

    }

}
