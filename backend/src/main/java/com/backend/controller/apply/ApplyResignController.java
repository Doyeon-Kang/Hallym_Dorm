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

import com.backend.model.User;
import com.backend.model.apply.ApplyResign;
import com.backend.repository.UserRepository;
import com.backend.repository.apply.ApplyResignRepository;

@RestController
@RequestMapping(path="/api")
public class ApplyResignController {
    @Autowired
    ApplyResignRepository applyResignRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping(path="/apply-resign")
    public ResponseEntity<List<ApplyResign>> getAllApplyResign() {
        try {
          List<ApplyResign> applyResigns = new ArrayList<ApplyResign>();

          applyResignRepository.findAll().forEach(applyResigns::add);
          if(applyResigns.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
          }

          return new ResponseEntity<>(applyResigns, HttpStatus.OK);
        } catch (Exception e) {
          return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/apply-resign/{id}")
    public ResponseEntity<ApplyResign> getApplyResignById(@PathVariable("id") long id) {
      Optional<ApplyResign> resignData = applyResignRepository.findById(id);

      if(resignData.isPresent()) {
        ApplyResign _applyResign = resignData.get();
        
        return new ResponseEntity<>(applyResignRepository.save(_applyResign), HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }

    @PostMapping("/apply-resign/{studentNo}")
    public ResponseEntity<ApplyResign> createApplyResign(@PathVariable(name="studentNo") String studentNo, @RequestBody ApplyResign applyResign) {
        try {
            Optional<User> _userData = userRepository.findByStudentno(studentNo);
            if(_userData.isPresent()) {
                User _user = _userData.get();
                ApplyResign _applyResign = new ApplyResign(applyResign.getRes_date(), applyResign.getRes_reason());
               
                _applyResign.setUser(_user);
                _applyResign.setApproved(applyResign.isApproved());
                applyResignRepository.save(_applyResign);
                return new ResponseEntity<>(_applyResign, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
          } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
          }
        }
    

    @PutMapping("/apply-resign/{id}")
    public ResponseEntity<ApplyResign> updateApplyResign(@PathVariable("id") long id, @RequestBody ApplyResign applyResign) {
      Optional<ApplyResign> resignData = applyResignRepository.findById(id);
      if (resignData.isPresent()) {
        ApplyResign _applyResign = resignData.get();
        _applyResign.setRes_date(applyResign.getRes_date());
        _applyResign.setRes_reason(applyResign.getRes_reason());
        _applyResign.setApproved(applyResign.isApproved());
        return new ResponseEntity<>(applyResignRepository.save(_applyResign), HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }

    @PutMapping("/apply-resign/{resignId}/approve")
    public ResponseEntity<ApplyResign> approveApplyResign(@PathVariable(name="resignId") Long resignId) {
      Optional<ApplyResign> resignData = applyResignRepository.findById(resignId);

      if (resignData.isPresent()) {
        ApplyResign _applyResign = resignData.get();
        _applyResign.setApproved(true);
        applyResignRepository.save(_applyResign);
        return new ResponseEntity<>(_applyResign, HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }

    @DeleteMapping("/apply-resign/{id}")
    public ResponseEntity<HttpStatus> deleteApplyResign(@PathVariable("id") long id) {
      try {
        applyResignRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    @DeleteMapping("/apply-resign")
    public ResponseEntity<HttpStatus> deleteAllApplyResigns() {
      try {
        applyResignRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }

    }


}
