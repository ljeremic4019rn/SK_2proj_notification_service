package app.controller;

import app.dto.ReminderNotifCreateDto;
import app.dto.ReminderNotifDto;
import app.dto.ResetNotifCreateDto;
import app.dto.ResetNotifDto;
import app.service.ResetNotifService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

@RestController
@RequestMapping("/resetNotif")
public class ResetNotifController {

    private ResetNotifService resetNotifService;

    public ResetNotifController(ResetNotifService resetNotifService) {
        this.resetNotifService = resetNotifService;
    }

    @GetMapping
    public ResponseEntity<Page<ResetNotifDto>> findAll(@ApiIgnore Pageable pageable){
        return new ResponseEntity<>(resetNotifService.findAll(pageable), HttpStatus.OK);
    }

    @PostMapping(value = "Register")
    public ResponseEntity<ResetNotifDto> add(@RequestBody @Valid ResetNotifCreateDto resetNotifCreateDto){
        return new ResponseEntity<>(resetNotifService.add(resetNotifCreateDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResetNotifDto> findById(@PathVariable("id") Long id){
        return new ResponseEntity<>(resetNotifService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id){
        resetNotifService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
