package com.real_estate.llk_server_spring.region;

import com.real_estate.llk_server_spring.region.dto.DeleteStateDTO;
import com.real_estate.llk_server_spring.region.dto.StateDTO;
import com.real_estate.llk_server_spring.region.dto.UpdateStateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/region")
public class RegionController {
    private final RegionService regionService;

    @PostMapping("/state/add")
    public ResponseEntity<?> addState(@RequestBody StateDTO stateDTO) {
        return regionService.addStateProc(stateDTO);
    }

    @PutMapping("/state/update")
    public ResponseEntity<?> updateState(@RequestBody UpdateStateDTO updateStateDTO) {
        return regionService.updateStateProc(updateStateDTO);
    }

    @DeleteMapping("/state/delete")
    public ResponseEntity<?> deleteState(@RequestBody DeleteStateDTO deleteStateDTO) {
        return regionService.deleteStateProc(deleteStateDTO);
    }

    @GetMapping("/state/list")
    public ResponseEntity<?> getStateList() {
        return regionService.getStateListProc();
    }
}
