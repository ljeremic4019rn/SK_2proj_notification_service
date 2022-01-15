package app.controller;

import app.dto.ActivationNotifDto;
import app.dto.CustomNotificationDto;
import app.dto.NotificationCreateDto;
import app.dto.NotificationDto;
import app.security.CheckSecurity;
import app.service.NotificationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    private NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<Page<NotificationDto>> findAll(@RequestHeader("Authorization") String authorization, @ApiIgnore Pageable pageable){
        return new ResponseEntity<>(notificationService.findAll(pageable), HttpStatus.OK);
    }

//    @PostMapping(value = "add")
//    public ResponseEntity<NotificationDto> add(@RequestBody @Valid NotificationCreateDto notificationCreateDto){
//        return new ResponseEntity<>(notificationService.add(notificationCreateDto), HttpStatus.CREATED);
//    }

    @GetMapping("/{id}")
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<NotificationDto> findById(@RequestHeader("Authorization") String authorization, @PathVariable("id") Long id){
        return new ResponseEntity<>(notificationService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<?> deleteById(@RequestHeader("Authorization") String authorization, @PathVariable("id") Long id){
        notificationService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/sort/email_{email}")
    @CheckSecurity(roles = {"ROLE_ADMIN","ROLE_MANAGER","ROLE_CLIENT"})
    public ResponseEntity<Page<NotificationDto>> getNotificationsByEmail(@RequestHeader("Authorization") String authorization, @PathVariable("email") String email, Pageable pageable) {
        return new ResponseEntity<>(notificationService.findByEmail(email, pageable), HttpStatus.OK);
    }

    @GetMapping("/filter/type-{type}")
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<Page<NotificationDto>> getNotificationsByType(@RequestHeader("Authorization") String authorization, @PathVariable("type") String type, Pageable pageable) {
        return new ResponseEntity<>(notificationService.findByType(type, pageable), HttpStatus.OK);
    }

    @GetMapping("/sort/email-{email}")
    @CheckSecurity(roles = {"ROLE_ADMIN","ROLE_MANAGER","ROLE_CLIENT"})
    public ResponseEntity<Page<CustomNotificationDto>> getNotificationByEmail(@RequestHeader("Authorization") String authorization, @PathVariable("email") String email, Pageable pageable) {
        return new ResponseEntity<>(notificationService.findNotificationsByName(email, pageable), HttpStatus.OK);
    }
}
