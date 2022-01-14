package app.controller;


import app.dto.*;
import app.security.CheckSecurity;
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
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<Page<ReservationNotifDto>> findAll(@RequestHeader("Authorization") String authorization, @ApiIgnore Pageable pageable){
        return new ResponseEntity<>(reservationNotifService.findAll(pageable), HttpStatus.OK);
    }

//    @PostMapping(value = "add")
//    public ResponseEntity<ReservationNotifDto> add(@RequestBody @Valid ReservationNotifCreateDto reservationNotifCreateDto){
//        return new ResponseEntity<>(reservationNotifService.add(reservationNotifCreateDto), HttpStatus.CREATED);
//    }

    @GetMapping("/{id}")
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<ReservationNotifDto> findById(@RequestHeader("Authorization") String authorization, @PathVariable("id") Long id){
        return new ResponseEntity<>(reservationNotifService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<?> deleteById(@RequestHeader("Authorization") String authorization, @PathVariable("id") Long id){
        reservationNotifService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/sort/email_{email}")
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<Page<ReservationNotifDto>> getNotificationsByEmail(@RequestHeader("Authorization") String authorization, @PathVariable("email") String email, Pageable pageable) {
        return new ResponseEntity<>(reservationNotifService.findByEmail(email, pageable), HttpStatus.OK);
    }

    @GetMapping("/sort/between_{startDate}&{endDate}")
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<Page<ReservationNotifDto>> getNotificationsBetweenDates(@RequestHeader("Authorization") String authorization, @PathVariable("startDate") String startDate, @PathVariable("endDate") String endDate, Pageable pageable) {
        return new ResponseEntity<>(reservationNotifService.findBetweenDates(startDate, endDate, pageable), HttpStatus.OK);
    }

}
