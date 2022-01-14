package app.controller;

import app.dto.*;
import app.security.CheckSecurity;
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
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<Page<ResetNotifDto>> findAll(@ApiIgnore Pageable pageable){
        return new ResponseEntity<>(resetNotifService.findAll(pageable), HttpStatus.OK);
    }

//    @PostMapping(value = "add")
//    public ResponseEntity<ResetNotifDto> add(@RequestBody @Valid ResetNotifCreateDto resetNotifCreateDto){
//        return new ResponseEntity<>(resetNotifService.add(resetNotifCreateDto), HttpStatus.CREATED);
//    }

    @GetMapping("/{id}")
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<ResetNotifDto> findById(@PathVariable("id") Long id){
        return new ResponseEntity<>(resetNotifService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id){
        resetNotifService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/sort/email_{email}")
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<Page<ResetNotifDto>> getNotificationsByEmail(@RequestHeader("Authorization") String authorization, @PathVariable("email") String email, Pageable pageable) {
        return new ResponseEntity<>(resetNotifService.findByEmail(email, pageable), HttpStatus.OK);
    }

    @GetMapping("/sort/between_{startDate}&{endDate}")
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<Page<ResetNotifDto>> getNotificationsBetweenDates(@RequestHeader("Authorization") String authorization, @PathVariable("startDate") String startDate, @PathVariable("endDate") String endDate, Pageable pageable) {
        return new ResponseEntity<>(resetNotifService.findBetweenDates(startDate, endDate, pageable), HttpStatus.OK);
    }

}
