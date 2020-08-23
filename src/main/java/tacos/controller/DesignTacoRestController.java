package tacos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tacos.data.TacoSpringDataRepository;
import tacos.model.Taco;

import java.awt.print.Pageable;

/**
 * @author pi
 * @date 2020/08/23 17:52:04
 **/
@RestController
@RequestMapping(path = "/design", produces = "application/json")
@CrossOrigin(origins = "*")
public class DesignTacoRestController {
    private TacoSpringDataRepository tacoRepo;

    @Autowired
    public DesignTacoRestController(TacoSpringDataRepository tacoRepo) {
        this.tacoRepo = tacoRepo;
    }

    @GetMapping("/recent")
    public Iterable<Taco> recentTacos(){
        PageRequest recentTacoPage =  PageRequest.of(0,10, Sort.by("createAt").descending());
        return tacoRepo.findAll(recentTacoPage);
    }
}
