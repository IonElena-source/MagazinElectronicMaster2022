package MagazinElectronicMaster.MagazinElectronicMaster.controller;

import MagazinElectronicMaster.MagazinElectronicMaster.models.Produs;
import MagazinElectronicMaster.MagazinElectronicMaster.service.ProdusService;
import org.bson.json.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
// de facut cu @enablewebmvc si authorize requests
@RequestMapping("/produse")
public class ProdusControler {

    @Autowired
    ProdusService produsService;

    @PostMapping
    public ResponseEntity<Produs> createProdus(@RequestBody Produs produs){
        produsService.saveProdus(produs);
        return ResponseEntity.ok(produs);
    }

    @GetMapping
    public ResponseEntity<List<Produs>> retrieveProduse(){
        return ResponseEntity.ok(produsService.fetchAll());

    }
    @DeleteMapping(path = "/{id}")
    public void deleteProdus(@PathVariable String id){
        produsService.deleteProdus(id);

    }
    @PutMapping(path = "/{id}")
    public void actualizeazaProdus(@PathVariable String id, @RequestBody Produs produs){
        produsService.actualizeazaProdus(id,produs);

    }
    @PatchMapping(path = "/note/{id}/{nota}")
    public void adaugaNota(@PathVariable String id, @PathVariable Integer nota){
        produsService.actualizeazaNota(id,nota);

    }
}
