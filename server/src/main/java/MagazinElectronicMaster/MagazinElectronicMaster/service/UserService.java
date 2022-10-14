package MagazinElectronicMaster.MagazinElectronicMaster.service;

import MagazinElectronicMaster.MagazinElectronicMaster.models.User;
import MagazinElectronicMaster.MagazinElectronicMaster.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public void createUser(User user){
        userRepository.save(user);
    }
}
