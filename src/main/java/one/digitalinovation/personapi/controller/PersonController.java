package one.digitalinovation.personapi.controller;

import one.digitalinovation.personapi.dto.MessageResponseDTO;
import one.digitalinovation.personapi.dto.request.PersonDTO;
import one.digitalinovation.personapi.entity.Person;
import one.digitalinovation.personapi.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/people")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public ResponseEntity<String> createPerson(@RequestBody @Valid PersonDTO personDTO) {
        Person objPerson = personService.createPerson(personDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(objPerson.getId()).toUri();
        if (objPerson == null) {
            return ResponseEntity.badRequest().body("Usuario não foi cadastrado");
        }
        return ResponseEntity.created(uri).body("Usuario de ID " + objPerson.getId() + " cadastrado com sucesso!!");
    }

    @GetMapping
    public List<PersonDTO> listAll() {
        return personService.listAll();
    }
}
