package com.real_estate.llk_server_spring.contact;

import com.real_estate.llk_server_spring.contact.dto.ContactDTO;
import com.real_estate.llk_server_spring.contact.entity.Contact;
import com.real_estate.llk_server_spring.contact.entity.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactService {
    private final ContactRepository contactRepository;

    public ResponseEntity<?> addContactProc(ContactDTO contactDTO) {
        if (contactDTO.getFirstName().isEmpty() || contactDTO.getLastName().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이름을 입력해 주세요.");
        }
        if (contactDTO.getEmail().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이메일 주소를 입력해 주세요.");
        }
        if (contactDTO.getDescription().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("문의 내용을 작성해 주세요.");
        }
        if (contactDTO.getDescription().length() < 30) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("문의 사항을 최소 30자 이상 작성해 주세요.");
        }
        Contact contact = new Contact();
        contact.setFirstName(contactDTO.getFirstName());
        contact.setLastName(contactDTO.getLastName());
        contact.setEmail(contactDTO.getEmail());
        contact.setPhoneNumber(contactDTO.getPhoneNumber());
        contact.setDescription(contactDTO.getDescription());
        contactRepository.save(contact);
        return ResponseEntity.status(HttpStatus.CREATED).body(contact);
    }
}
