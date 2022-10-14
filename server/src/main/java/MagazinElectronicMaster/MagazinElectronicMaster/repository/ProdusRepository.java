package MagazinElectronicMaster.MagazinElectronicMaster.repository;

import MagazinElectronicMaster.MagazinElectronicMaster.models.Produs;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdusRepository extends MongoRepository<Produs,String> {
}
