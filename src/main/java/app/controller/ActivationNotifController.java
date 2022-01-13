package app.controller;

import app.dto.ActivationNotifCreateDto;
import app.dto.ActivationNotifDto;
import app.service.ActivationNotifService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

@RestController
@RequestMapping("/activationnotif")
public class ActivationNotifController {

    private ActivationNotifService activationNotifService;

    public ActivationNotifController(ActivationNotifService activationNotifService) {
        this.activationNotifService = activationNotifService;
    }

    @GetMapping
    public ResponseEntity<Page<ActivationNotifDto>> findAll(@ApiIgnore Pageable pageable){
        return new ResponseEntity<>(activationNotifService.findAll(pageable), HttpStatus.OK);
    }

    @PostMapping(value = "register")
    public ResponseEntity<ActivationNotifDto> add(@RequestBody @Valid ActivationNotifCreateDto activationNotifCreateDto){
        return new ResponseEntity<>(activationNotifService.add(activationNotifCreateDto), HttpStatus.CREATED);
//        activationNotifService.add(activationNotifCreateDto);
//        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActivationNotifDto> findById(@PathVariable("id") Long id){
        return new ResponseEntity<>(activationNotifService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id){
        activationNotifService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
