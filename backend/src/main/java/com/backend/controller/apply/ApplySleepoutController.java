package com.backend.controller.apply;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.model.apply.ApplySleepout;
import com.backend.repository.apply.ApplySleepoutRepository;

@RestController
@RequestMapping(path="/api")
public class ApplySleepoutController {
    @Autowired
    ApplySleepoutRepository applySleepoutRepository;

    @GetMapping(path="/apply-sleepout")
    public ResponseEntity<List<ApplySleepout>> getAllApplySleepout() {
        try {
          List<ApplySleepout> applySleepouts = new ArrayList<ApplySleepout>();

          applySleepoutRepository.findAll().forEach(applySleepouts::add);
          if(applySleepouts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
          }

          return new ResponseEntity<>(applySleepouts, HttpStatus.OK);
        } catch (Exception e) {
          return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/apply-sleepout/{id}")
    public ResponseEntity<ApplySleepout> getApplySleepoutById(@PathVariable("id") long id) {
      Optional<ApplySleepout> sleepoutData = applySleepoutRepository.findById(id);

      if(sleepoutData.isPresent()) {
        ApplySleepout _applySleepout = sleepoutData.get();
        
        return new ResponseEntity<>(applySleepoutRepository.save(_applySleepout), HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }

    @PostMapping("/apply-sleepout")
    public ResponseEntity<ApplySleepout> createApplySleepout(@RequestBody ApplySleepout applySleepout) {
      try {
        ApplySleepout _applySleepout = applySleepoutRepository
                    .save(new ApplySleepout(applySleepout.getDate_sleepout(), applySleepout.getReason()));
        return new ResponseEntity<>(_applySleepout, HttpStatus.CREATED);
      } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    @PutMapping("/apply-sleepout/{id}")
    public ResponseEntity<ApplySleepout> updateApplySleepout(@PathVariable("id") long id, @RequestBody ApplySleepout applySleepout) {
      Optional<ApplySleepout> sleepoutData = applySleepoutRepository.findById(id);
      if (sleepoutData.isPresent()) {
        ApplySleepout _applySleepout = sleepoutData.get();
        _applySleepout.setDate_sleepout(applySleepout.getDate_sleepout());
        _applySleepout.setReason(applySleepout.getReason());
        _applySleepout.setApproved(applySleepout.isApproved());
        return new ResponseEntity<>(applySleepoutRepository.save(_applySleepout), HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }

    @PutMapping("/apply-sleepout/{sleepoutId}/approve")
    public ResponseEntity<ApplySleepout> approveApplySleepout(@PathVariable(name="sleepoutId") Long sleepoutId) {
      Optional<ApplySleepout> sleepoutData = applySleepoutRepository.findById(sleepoutId);

      if (sleepoutData.isPresent()) {
        ApplySleepout _applySleepout = sleepoutData.get();
        _applySleepout.setApproved(true);
        applySleepoutRepository.save(_applySleepout);
        return new ResponseEntity<>(_applySleepout, HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }

    @DeleteMapping("/apply-sleepout/{id}")
    public ResponseEntity<HttpStatus> deleteApplySleepout(@PathVariable("id") long id) {
      try {
        applySleepoutRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    @DeleteMapping("/apply-sleepout")
    public ResponseEntity<HttpStatus> deleteAllApplySleepouts() {
      try {
        applySleepoutRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }

    }


}
