package com.backend.controller.apply;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.model.User;
import com.backend.model.UserMember;
import com.backend.model.apply.ApplySleepout;
import com.backend.repository.UserMemberRepository;
import com.backend.repository.UserRepository;
import com.backend.repository.apply.ApplySleepoutRepository;
import com.backend.security.services.UserMemberManagement;

@RestController
@RequestMapping(path="/api")
public class ApplySleepoutController {
    @Autowired
    ApplySleepoutRepository applySleepoutRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMemberRepository userMemberRepository;

    @Autowired
    UserMemberManagement userMemberManager;

    // @PreAuthorize("hasRole('ROLE_ADMIN')")
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

    // @PreAuthorize("hasRole('ROLE_ADMIN')")
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

    @PostMapping("/apply-sleepout/{studentNo}")
    public ResponseEntity<ApplySleepout> createApplySleepout(@PathVariable(name="studentNo") String studentNo, @RequestBody ApplySleepout applySleepout) {
      try {
        Optional<User> _userData = userRepository.findByStudentno(studentNo);
        if(_userData.isPresent()) {
          User _user = _userData.get();
          if(userMemberManager.userMemberExists(_user)) {
            ApplySleepout _applySleepout = new ApplySleepout(applySleepout.getDate_sleepout(), applySleepout.getReason());
            Optional<UserMember> _userMemberData = userMemberRepository.findByUserId(_user.getId());
            _applySleepout.setUserMember(_userMemberData.get());
            return new ResponseEntity<>(applySleepoutRepository.save(_applySleepout), HttpStatus.CREATED);
          } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
          }
        } else {
          return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
      } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    // @PreAuthorize("hasRole('ROLE_ADMIN')")
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

    // @PreAuthorize("hasRole('ROLE_ADMIN')")
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

    // @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/apply-sleepout/{id}")
    public ResponseEntity<HttpStatus> deleteApplySleepout(@PathVariable("id") long id) {
      try {
        applySleepoutRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    // @PreAuthorize("hasRole('ROLE_ADMIN')")
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
