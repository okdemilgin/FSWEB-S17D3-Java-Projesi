package com.workintech.zoo.demo.controller;

import com.workintech.zoo.demo.entity.Gender;
import com.workintech.zoo.demo.entity.Kangaroo;
import com.workintech.zoo.demo.exceptions.AnimalValidator;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/kangaroos")
public class KangarooController {
    private Map<Integer, Kangaroo> kangaroos;
    @PostConstruct
    public void init(){
        kangaroos = new HashMap<>();
        kangaroos.put(1, new Kangaroo(1, "Lena", 35, Gender.FEMALE, 1.6, false));
    }
    @GetMapping("/")
    public List<Kangaroo> getAll(){
        return kangaroos.values().stream().toList();
    }
    @GetMapping("/{id}")
    public Kangaroo get(@PathVariable int id){
        AnimalValidator.isIdValid(id);
        AnimalValidator.isIdNotExist(kangaroos, id);
        return kangaroos.get(id);
    }
    @PostMapping("/")
    public Kangaroo save(@RequestBody Kangaroo kangaroo){
        AnimalValidator.isIdValid(kangaroo.getId());
        AnimalValidator.isIdExist(kangaroos, kangaroo.getId());
        AnimalValidator.isAnimalValid(kangaroo);
        AnimalValidator.isKangarooValid(kangaroo);
        kangaroos.put(kangaroo.getId(), kangaroo);
        return kangaroos.get(kangaroo.getId());
    }
    @PutMapping("/{id}")
    public Kangaroo update(@PathVariable int id, @RequestBody Kangaroo kangaroo){
        AnimalValidator.isIdValid(id);
        AnimalValidator.isIdNotExist(kangaroos, id);
        AnimalValidator.isAnimalValid(kangaroo);
        AnimalValidator.isKangarooValid(kangaroo);
        kangaroo.setId(id);
        kangaroos.put(id, kangaroo);
        return kangaroo;
    }
    @DeleteMapping("/{id}")
    public Kangaroo delete(@PathVariable int id){
        AnimalValidator.isIdValid(id);
        AnimalValidator.isIdNotExist(kangaroos, id);
        Kangaroo kangaroo = kangaroos.get(id);
        kangaroos.remove(id);
        return kangaroo;
    }
}