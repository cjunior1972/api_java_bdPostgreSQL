package br.com.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.springboot.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    // aqui uso recurso do sql injetado no springboot
    @Query("SELECT u from User u where u.id >= :id order by u.id")
    public List<User> findAllMoreThan(@Param("id") Long id);

    // aqui uso recurso do próprio springboot
    public List<User> findByIdGreaterThan(Long id);

    // pesquisando pelo nome
    public List<User> findByNameIgnoreCase(String name);

    // aqui uso recurso do sql injetado no springboot
    @Query("SELECT u from User u where u.name like :name order by u.name")
    public List<User> findByNameLikeUsers(@Param("name") String name);

}
