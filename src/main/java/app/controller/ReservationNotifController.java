package app.controller;


import app.dto.*;
import app.service.ReservationNotifService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

@RestController
@RequestMapping("/reservationNotif")
public class ReservationNotifController {

    private ReservationNotifService reservationNotifService;

    public ReservationNotifController(ReservationNotifService reservationNotifService) {
        this.reservationNotifService = reservationNotifService;
    }

    @GetMapping
    public ResponseEntity<Page<ReservationNotifDto>> findAll(@ApiIgnore Pageable pageable){
        return new ResponseEntity<>(reservationNotifService.findAll(pageable), HttpStatus.OK);
    }

    @PostMapping(value = "register")
    public ResponseEntity<ReservationNotifDto> add(@RequestBody @Valid ReservationNotifCreateDto reservationNotifCreateDto){
        return new ResponseEntity<>(reservationNotifService.add(reservationNotifCreateDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationNotifDto> findById(@PathVariable("id") Long id){
        return new ResponseEntity<>(reservationNotifService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id){
        reservationNotifService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/sort/email_{email}")
    public ResponseEntity<Page<ReservationNotifDto>> getNotificationsByEmail(@PathVariable("email") String email, Pageable pageable) {
        return new ResponseEntity<>(reservationNotifService.findByEmail(email, pageable), HttpStatus.OK);
    }

    @GetMapping("/sort/between_{startDate}&{endDate}")
    // @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<Page<ReservationNotifDto>> getNotificationsBetweenDates(@PathVariable("startDate") String startDate, @PathVariable("endDate") String endDate, Pageable pageable) {
        return new ResponseEntity<>(reservationNotifService.findBetweenDates(startDate, endDate, pageable), HttpStatus.OK);
    }

}
