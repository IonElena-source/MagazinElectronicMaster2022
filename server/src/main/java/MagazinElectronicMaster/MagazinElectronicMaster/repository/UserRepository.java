package MagazinElectronicMaster.MagazinElectronicMaster.repository;

import MagazinElectronicMaster.MagazinElectronicMaster.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User,String> {
}
