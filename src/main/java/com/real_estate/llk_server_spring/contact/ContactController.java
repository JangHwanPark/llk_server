package com.real_estate.llk_server_spring.contact;

import com.real_estate.llk_server_spring.contact.dto.ContactDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/contact")
public class ContactController {
    private final ContactService contactService;

    @PostMapping
    public ResponseEntity<?> addContact(@RequestBody ContactDTO contactDTO) {
        return contactService.addContactProc(contactDTO);
    }

}
