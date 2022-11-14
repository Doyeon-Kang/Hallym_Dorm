package com.backend.controller.apply;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.model.apply.ApplyConsultSchedule;
import com.backend.repository.apply.ApplyConsultScheduleRepository;

@RestController
@RequestMapping(path="/api")
public class ApplyConsultScheduleController {
    @Autowired
    ApplyConsultScheduleRepository applyConsultScheduleRepository;

    @GetMapping(path="/apply-consult-schedule")
    public ResponseEntity<List<ApplyConsultSchedule>> getAllApplyConsultSchedule() {
        try {
          List<ApplyConsultSchedule> applyConsultSchedules = new ArrayList<ApplyConsultSchedule>();

          applyConsultScheduleRepository.findAll().forEach(applyConsultSchedules::add);
          if(applyConsultSchedules.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
          }

          return new ResponseEntity<>(applyConsultSchedules, HttpStatus.OK);
        } catch (Exception e) {
          return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
