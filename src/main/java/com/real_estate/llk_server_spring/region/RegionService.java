package com.real_estate.llk_server_spring.region;

import com.real_estate.llk_server_spring.exception.LlkServerException;
import com.real_estate.llk_server_spring.exception.LlkServerExceptionErrorCode;
import com.real_estate.llk_server_spring.region.dto.DeleteStateDTO;
import com.real_estate.llk_server_spring.region.dto.StateDTO;
import com.real_estate.llk_server_spring.region.dto.UpdateStateDTO;
import com.real_estate.llk_server_spring.region.entity.CityRepository;
import com.real_estate.llk_server_spring.region.entity.State;
import com.real_estate.llk_server_spring.region.entity.StateRepository;
import com.real_estate.llk_server_spring.util.LlkUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RegionService {
    private final CityRepository cityRepository;
    private final StateRepository stateRepository;
    private final LlkUtil llkUtil;

    public ResponseEntity<?> addStateProc(StateDTO stateDTO) throws LlkServerException {
        llkUtil.usingStringDataValidationCheck(stateDTO.getStateName());
        llkUtil.usingStringDataValidationCheck(stateDTO.getStateAbbreviation());
        if(stateRepository.existsByStateName(stateDTO.getStateName())) {
            throw new LlkServerException(HttpStatus.INTERNAL_SERVER_ERROR, LlkServerExceptionErrorCode.REQUEST_VALUE_DATABASE_DUPLICATE);
        }
        if (stateRepository.existsByStateAbbreviation(stateDTO.getStateAbbreviation())) {
            throw new LlkServerException(HttpStatus.INTERNAL_SERVER_ERROR, LlkServerExceptionErrorCode.REQUEST_VALUE_DATABASE_DUPLICATE);
        }
        State state = State.builder()
                .stateName(stateDTO.getStateName())
                .stateAbbreviation(stateDTO.getStateAbbreviation())
                .build();
        stateRepository.save(state);
        return ResponseEntity.status(HttpStatus.CREATED).body("State added successfully");
    }


    public ResponseEntity<?> updateStateProc(UpdateStateDTO updateStateDTO) throws LlkServerException{
        llkUtil.usingStringDataValidationCheck(updateStateDTO.getStateName());
        llkUtil.usingStringDataValidationCheck(updateStateDTO.getStateAbbreviation());
        llkUtil.usingStringDataValidationCheck(String.valueOf(updateStateDTO.getStateId()));
        if(!stateRepository.existsById(updateStateDTO.getStateId())) {
            throw new LlkServerException(HttpStatus.BAD_REQUEST, LlkServerExceptionErrorCode.NOT_FOUND_STATE);
        }
        stateRepository.updateStateNameAndStateAbbreviationById(updateStateDTO.getStateName(),updateStateDTO.getStateAbbreviation(),updateStateDTO.getStateId());
        return ResponseEntity.status(HttpStatus.OK).body("State updated successfully");
    }

    public ResponseEntity<?> deleteStateProc(DeleteStateDTO deleteStateDTO) throws LlkServerException{
        llkUtil.usingStringDataValidationCheck(String.valueOf(deleteStateDTO.getStateId()));
        if(!stateRepository.existsById(deleteStateDTO.getStateId())) {
            throw new LlkServerException(HttpStatus.BAD_REQUEST, LlkServerExceptionErrorCode.NOT_FOUND_STATE);
        }
        stateRepository.deleteById(deleteStateDTO.getStateId());
        return ResponseEntity.status(HttpStatus.OK).body("State deleted successfully");
    }

    public ResponseEntity<?> getStateListProc() {
        List<State> state = stateRepository.findAll();
        return ResponseEntity.ok(state);
    }
}
