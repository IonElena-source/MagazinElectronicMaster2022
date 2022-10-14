package MagazinElectronicMaster.MagazinElectronicMaster.controller;

import MagazinElectronicMaster.MagazinElectronicMaster.models.User;
import MagazinElectronicMaster.MagazinElectronicMaster.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserControler {

    @Autowired
    private UserService personService;

    @PostMapping
    public ResponseEntity<User> save(@RequestBody User person) {
         personService.createUser(person);
         return ResponseEntity.ok(person);
    }
}
