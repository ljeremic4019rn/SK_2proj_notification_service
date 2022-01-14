package app.controller;

import app.dto.*;
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
    public ResponseEntity<Page<ReminderNotifDto>> findAll(@ApiIgnore Pageable pageable){
        return new ResponseEntity<>(reminderNotifService.findAll(pageable), HttpStatus.OK);
    }

    @PostMapping(value = "register")
    public ResponseEntity<ReminderNotifDto> add(@RequestBody @Valid ReminderNotifCreateDto reminderNotifCreateDto){
        return new ResponseEntity<>(reminderNotifService.add(reminderNotifCreateDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReminderNotifDto> findById(@PathVariable("id") Long id){
        return new ResponseEntity<>(reminderNotifService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id){
        reminderNotifService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/sort/email_{email}")
    public ResponseEntity<Page<ReminderNotifDto>> getNotificationsByEmail(@PathVariable("email") String email, Pageable pageable) {
        return new ResponseEntity<>(reminderNotifService.findByEmail(email, pageable), HttpStatus.OK);
    }

    @GetMapping("/sort/between_{startDate}&{endDate}")
    // @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<Page<ReminderNotifDto>> getNotificationsBetweenDates(@PathVariable("startDate") String startDate, @PathVariable("endDate") String endDate, Pageable pageable) {
        return new ResponseEntity<>(reminderNotifService.findBetweenDates(startDate, endDate, pageable), HttpStatus.OK);
    }

}
