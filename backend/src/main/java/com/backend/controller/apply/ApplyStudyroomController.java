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
import com.backend.model.apply.ApplyStudyroom;
import com.backend.repository.UserMemberRepository;
import com.backend.repository.UserRepository;
import com.backend.repository.apply.ApplyStudyroomRepository;
import com.backend.security.services.UserMemberManagement;

@RestController
@RequestMapping(path="/api")
public class ApplyStudyroomController {
    @Autowired
    ApplyStudyroomRepository applyStudyroomRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMemberRepository userMemberRepository;

    @Autowired
    UserMemberManagement userMemberManager;

    // @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(path="/apply-studyroom")
    public ResponseEntity<List<ApplyStudyroom>> getAllApplyStudyroom() {
        try {
          List<ApplyStudyroom> applyStudyrooms = new ArrayList<ApplyStudyroom>();

          applyStudyroomRepository.findAll().forEach(applyStudyrooms::add);
          if(applyStudyrooms.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
          }

          return new ResponseEntity<>(applyStudyrooms, HttpStatus.OK);
        } catch (Exception e) {
          return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/apply-studyroom/{id}")
    public ResponseEntity<ApplyStudyroom> getApplyStudyroomById(@PathVariable("id") long id) {
      Optional<ApplyStudyroom> studyroomData = applyStudyroomRepository.findById(id);

      if(studyroomData.isPresent()) {
        ApplyStudyroom _applyStudyroom = studyroomData.get();
        
        return new ResponseEntity<>(applyStudyroomRepository.save(_applyStudyroom), HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }

    @PostMapping("/apply-studyroom/{studentNo}-{seat}")
    public ResponseEntity<ApplyStudyroom> createApplyStudyroom(@PathVariable(name="studentNo") String studentNo, @PathVariable(name="seat") int seat, @RequestBody ApplyStudyroom applyStudyroom) {
      try {
        Optional<User> _userData = userRepository.findByStudentno(studentNo);
        if(_userData.isPresent()) {
          User _user = _userData.get();
          if(userMemberManager.userMemberExists(_user)) {
            ApplyStudyroom _applyStudyroom = new ApplyStudyroom(applyStudyroom.getTimeslot1(), applyStudyroom.getTimeslot2(), applyStudyroom.getTimeslot3());
            Optional<UserMember> _userMemberData = userMemberRepository.findByUserId(_user.getId());
            _applyStudyroom.setUserMember(_userMemberData.get());
            return new ResponseEntity<>(applyStudyroomRepository.save(_applyStudyroom), HttpStatus.CREATED);
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

    // // @PreAuthorize("hasRole('ROLE_ADMIN')")
    // @PutMapping("/apply-studyroom/{studyroomId}")
    // public ResponseEntity<ApplyStudyroom> updateApplyStudyroom(@PathVariable("studyroomId") long studyroomId, @RequestBody ApplyStudyroom applyStudyroom) {
    //   Optional<ApplyStudyroom> studyroomData = applyStudyroomRepository.findById(studyroomId);
    //   if (studyroomData.isPresent()) {
    //     ApplyStudyroom _applyStudyroom = studyroomData.get();
    //     _applyStudyroom.setDate_studyroom(applyStudyroom.getDate_studyroom());
    //     _applyStudyroom.setReason(applyStudyroom.getReason());
    //     _applyStudyroom.setApproved(applyStudyroom.isApproved());
    //     return new ResponseEntity<>(applyStudyroomRepository.save(_applyStudyroom), HttpStatus.OK);
    //   } else {
    //     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    //   }
    // }

    // @PreAuthorize("hasRole('ROLE_ADMIN')")
    // @PutMapping("/apply-studyroom/{studyroomId}/approve")
    // public ResponseEntity<ApplyStudyroom> approveApplyStudyroom(@PathVariable(name="studyroomId") Long studyroomId) {
    //   Optional<ApplyStudyroom> studyroomData = applyStudyroomRepository.findById(studyroomId);

    //   if (studyroomData.isPresent()) {
    //     ApplyStudyroom _applyStudyroom = studyroomData.get();
    //     _applyStudyroom.setApproved(true);
    //     applyStudyroomRepository.save(_applyStudyroom);
    //     return new ResponseEntity<>(_applyStudyroom, HttpStatus.OK);
    //   } else {
    //     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    //   }
    // }

    // @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/apply-studyroom/{studyroomId}")
    public ResponseEntity<HttpStatus> deleteApplyStudyroom(@PathVariable("studyroomId") long studyroomId) {
      try {
        applyStudyroomRepository.deleteById(studyroomId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    // @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/apply-studyroom")
    public ResponseEntity<HttpStatus> deleteAllApplyStudyrooms() {
      try {
        applyStudyroomRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }

    }
}

