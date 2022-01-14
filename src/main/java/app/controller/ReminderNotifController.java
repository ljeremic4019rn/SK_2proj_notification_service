package app.controller;

import app.dto.*;
import app.security.CheckSecurity;
import app.service.ReminderNotifService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

@RestController
@RequestMapping("/reminderNotif")
public class ReminderNotifController {

    private ReminderNotifService reminderNotifService;

    public ReminderNotifController(ReminderNotifService reminderNotifService) {
        this.reminderNotifService = reminderNotifService;
    }

    @GetMapping
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<Page<ReminderNotifDto>> findAll(@RequestHeader("Authorization") String authorization, @ApiIgnore Pageable pageable){
        return new ResponseEntity<>(reminderNotifService.findAll(pageable), HttpStatus.OK);
    }

//    @PostMapping(value = "add")
//    public ResponseEntity<ReminderNotifDto> add(@RequestBody @Valid ReminderNotifCreateDto reminderNotifCreateDto){
//        return new ResponseEntity<>(reminderNotifService.add(reminderNotifCreateDto), HttpStatus.CREATED);
//    }

    @GetMapping("/{id}")
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<ReminderNotifDto> findById(@RequestHeader("Authorization") String authorization, @PathVariable("id") Long id){
        return new ResponseEntity<>(reminderNotifService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<?> deleteById(@RequestHeader("Authorization") String authorization, @PathVariable("id") Long id){
        reminderNotifService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/sort/email_{email}")
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<Page<ReminderNotifDto>> getNotificationsByEmail(@RequestHeader("Authorization") String authorization, @PathVariable("email") String email, Pageable pageable) {
        return new ResponseEntity<>(reminderNotifService.findByEmail(email, pageable), HttpStatus.OK);
    }

    @GetMapping("/sort/between_{startDate}&{endDate}")
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<Page<ReminderNotifDto>> getNotificationsBetweenDates(@RequestHeader("Authorization") String authorization, @PathVariable("startDate") String startDate, @PathVariable("endDate") String endDate, Pageable pageable) {
        return new ResponseEntity<>(reminderNotifService.findBetweenDates(startDate, endDate, pageable), HttpStatus.OK);
    }

}
