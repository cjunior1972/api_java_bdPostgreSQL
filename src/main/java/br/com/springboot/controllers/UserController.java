package br.com.springboot.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.model.User;
import br.com.springboot.repository.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{id}")
    public User user(@PathVariable("id") Long id) {
        System.out.println("O id é " + id);

        Optional<User> userFind = this.userRepository.findById(id);
        if (userFind.isPresent()) {
            return userFind.get();
        }
        return null;
    }

    // recenendo os dados da API / TELA e alimentando banco de dados, gravando o
    // registro
    // formato JASON
    @PostMapping("/")
    public User user(@RequestBody User user) {
        this.userRepository.save(user);
        System.out.println("O id e novo registro: " + user.getName());
        return user;
    }
    // fim POST ----------------------------------------------------------------

    // lista todos do banco de dados
    @GetMapping("/list")
    public List<User> list() {
        System.out.println("Lista todos do banco de dados");
        return this.userRepository.findAll();
    }

    /* lista um id específico, do banco de dados */
    @GetMapping("/list/{id}")
    public List<User> listMoreThan(@PathVariable("id") Long id) {
        System.out.println("Lista maior que id:" + id);
        return this.userRepository.findAllMoreThan(id); // aqui eu uso o sql injetado no springboot
        // return this.userRepository.findByIdGreaterThan(id); // aqui eu uso recurso do
        // próprio springboot
    }

    /* lista um nome específico, do banco de dados */
    @GetMapping("/findByName/{name}")
    public List<User> findByName(@PathVariable("name") String name) {
        System.out.println("Pesquisa por nome:" + name);
        // return this.userRepository.findAllMoreThan(id); aqui eu uso o sql injetado no
        // springboot
        return this.userRepository.findByNameIgnoreCase(name); // aqui eu uso recurso do próprio springboot
    }

    /* lista sql contém parte do nome, do banco de dados */
    @GetMapping("/findByNameL/{name}")
    public List<User> findByNameL(@PathVariable("name") String name) {
        System.out.println("Pesquisa contém o nome:" + name);
        return this.userRepository.findByNameLike(name); // aqui eu uso recurso do próprio springboot
    }

}
