package app.controller;

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
    public ResponseEntity<Page<NotificationDto>> findAll(@ApiIgnore Pageable pageable){
        return new ResponseEntity<>(notificationService.findAll(pageable), HttpStatus.OK);
    }

    @PostMapping(value = "register")
    public ResponseEntity<NotificationDto> add(@RequestBody @Valid NotificationCreateDto notificationCreateDto){
        return new ResponseEntity<>(notificationService.add(notificationCreateDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificationDto> findById(@PathVariable("id") Long id){
        return new ResponseEntity<>(notificationService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id){
        notificationService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
