package com.backend.controller.apply;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.model.apply.ApplyStudyroomSchedule;
import com.backend.repository.apply.ApplyStudyroomScheduleRepository;

@RestController
@RequestMapping(path="/api")
public class ApplyStudyroomScheduleController {
    @Autowired
    ApplyStudyroomScheduleRepository applyStudyroomScheduleRepository;

    @GetMapping(path="/apply-studyroom-schedule")
    public ResponseEntity<List<ApplyStudyroomSchedule>> getAllApplyStudyroomSchedule() {
        try {
          List<ApplyStudyroomSchedule> applyStudyroomSchedules = new ArrayList<ApplyStudyroomSchedule>();

          applyStudyroomScheduleRepository.findAll().forEach(applyStudyroomSchedules::add);
          if(applyStudyroomSchedules.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
          }

          return new ResponseEntity<>(applyStudyroomSchedules, HttpStatus.OK);
        } catch (Exception e) {
          return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // @GetMapping("/apply-studyroom-schedule/{id}")
    // public ResponseEntity<ApplyStudyroomSchedule> getApplyStudyroomScheduleById(@PathVariable("id") long id) {
    //   Optional<ApplyStudyroomSchedule> studyroomScheduleData = applyStudyroomScheduleRepository.findById(id);

    //   if(studyroomScheduleData.isPresent()) {
    //     ApplyStudyroomSchedule _applyStudyroomSchedule = studyroomScheduleData.get();
        
    //     return new ResponseEntity<>(applyStudyroomScheduleRepository.save(_applyStudyroomSchedule), HttpStatus.OK);
    //   } else {
    //     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    //   }
    // }
}
