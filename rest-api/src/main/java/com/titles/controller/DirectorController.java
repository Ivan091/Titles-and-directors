package com.titles.controller;

import com.titles.model.Director;
import com.titles.model.DirectorDto;
import com.titles.service.DirectorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api")
public class DirectorController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RootController.class);

    private final DirectorService service;

    @Autowired
    public DirectorController(DirectorService service) {
        this.service = service;
    }

    @GetMapping("/directors")
    public List<DirectorDto> findAllCalculatingAverageProfit() {
        return service.findAllCalculatingProfit();
    }

    @GetMapping("/director/{id}")
    public ResponseEntity<Director> findById(@PathVariable Integer id) {
        var director = service.findById(id);
        return director
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/directors")
    public ResponseEntity<Integer> create(@RequestBody Director director) {
        var createdId = service.create(director);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping(value = "/directors")
    public ResponseEntity<Integer> update(@RequestBody Director director) {
        var updatedRowsCount = service.update(director);
        if (updatedRowsCount == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedRowsCount, HttpStatus.OK);
    }

    @DeleteMapping(value = "/directors/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

    @GetMapping(value = "/directors/count")
    public ResponseEntity<Integer> count() {
        return new ResponseEntity<>(service.count(), HttpStatus.OK);
    }
}
