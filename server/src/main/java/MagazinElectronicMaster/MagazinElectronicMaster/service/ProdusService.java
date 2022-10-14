package MagazinElectronicMaster.MagazinElectronicMaster.service;

import MagazinElectronicMaster.MagazinElectronicMaster.models.Produs;
import MagazinElectronicMaster.MagazinElectronicMaster.models.User;
import MagazinElectronicMaster.MagazinElectronicMaster.repository.ProdusRepository;
import MagazinElectronicMaster.MagazinElectronicMaster.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProdusService {
    @Autowired
    ProdusRepository produsRepository;

    public void saveProdus(Produs produs){
        produsRepository.save(produs);
    }

    public List<Produs> fetchAll(){
        return produsRepository.findAll();
    }

    public void deleteProdus(String id) {
        produsRepository.deleteById(id);
    }

    public void actualizeazaNota(String id, Integer nota) {
        Optional<Produs> byId = produsRepository.findById(id);
        if(byId.isPresent()){
            Produs produs = byId.get();
            ArrayList<Integer> note = produs.getNote();
            if(note==null) note = new ArrayList<>();
            note.add(nota);
            produs.setNote(note);
            produsRepository.save(produs);
        }


    }

    public void actualizeazaProdus(String id, Produs produs) {
        Optional<Produs> byId = produsRepository.findById(id);
        if(byId.isPresent()){
            Produs produsOld = byId.get();
            produsRepository.deleteById(id);
            produs.setCodProdus(produsOld.getCodProdus());
            produsRepository.save(produs);
        }

    }
}
