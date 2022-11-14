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
import com.backend.model.UserMember;
import com.backend.model.apply.ApplySleepout;
import com.backend.payload.request.ApplySleepoutRequest;
import com.backend.payload.response.ApplySleepoutResponse;
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
    public ResponseEntity<List<ApplySleepoutResponse>> getAllApplySleepout() {
        try {
          List<ApplySleepout> applySleepouts = applySleepoutRepository.findAll();
          List<ApplySleepoutResponse> sleepoutResponses = new ArrayList<ApplySleepoutResponse>();


          for(ApplySleepout _applySleepout : applySleepouts) {
            // find user and user_member connected to each apply_sleepout
            UserMember _userMember = _applySleepout.getUserMember();
            User _user = _userMember.getUser();
            sleepoutResponses.add(new ApplySleepoutResponse(_applySleepout.getId(),
                                  _user.getStudentno(), _user.getName(), _userMember.getDepartment(),
                                  _applySleepout.getDate_sleepout(), _applySleepout.getReason(), _applySleepout.getDate(),
                                  _applySleepout.isApproved()));
          }

          if(applySleepouts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
          }

          return new ResponseEntity<>(sleepoutResponses, HttpStatus.OK);
        } catch (Exception e) {
          return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/apply-sleepout/{id}")
    public ResponseEntity<ApplySleepoutResponse> getApplySleepoutById(@PathVariable("id") long id) {
      Optional<ApplySleepout> sleepoutData = applySleepoutRepository.findById(id);

      if(sleepoutData.isPresent()) {
        ApplySleepout _applySleepout = sleepoutData.get();

        UserMember _userMember = _applySleepout.getUserMember();
        User _user = _userMember.getUser();
        ApplySleepoutResponse response = new ApplySleepoutResponse(_applySleepout.getId(),
                                  _user.getStudentno(), _user.getName(), _userMember.getDepartment(),
                                  _applySleepout.getDate_sleepout(), _applySleepout.getReason(), _applySleepout.getDate(),
                                  _applySleepout.isApproved());
        
        return new ResponseEntity<>(response, HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }

    @PostMapping("/apply-sleepout")
    public ResponseEntity<ApplySleepoutResponse> createApplySleepout(@RequestBody ApplySleepoutRequest applySleepout) {
      try {
        Optional<User> _userData = userRepository.findByStudentno(applySleepout.getStudentNo());
        if(_userData.isPresent()) {
          User _user = _userData.get();
          if(userMemberManager.userMemberExists(_user)) {
            ApplySleepout _applySleepout = new ApplySleepout(applySleepout.getDate_sleepout(), applySleepout.getReason());
            Optional<UserMember> _userMemberData = userMemberRepository.findByUserId(_user.getId());
            UserMember _userMember = _userMemberData.get();
            _applySleepout.setUserMember(_userMemberData.get());
            applySleepoutRepository.save(_applySleepout);

            ApplySleepoutResponse response = new ApplySleepoutResponse(_applySleepout.getId(),
                                              _user.getStudentno(), _user.getName(), _userMember.getDepartment(),
                                              _applySleepout.getDate_sleepout(), _applySleepout.getReason(), _applySleepout.getDate(),
                                              _applySleepout.isApproved());
            return new ResponseEntity<>(response, HttpStatus.CREATED);
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
    @PutMapping("/apply-sleepout/{sleepoutId}")
    public ResponseEntity<ApplySleepoutResponse> updateApplySleepout(@PathVariable("sleepoutId") long sleepoutId, @RequestBody ApplySleepout applySleepout) {
      Optional<ApplySleepout> sleepoutData = applySleepoutRepository.findById(sleepoutId);
      if (sleepoutData.isPresent()) {
        ApplySleepout _applySleepout = sleepoutData.get();
        _applySleepout.setDate_sleepout(applySleepout.getDate_sleepout());
        _applySleepout.setReason(applySleepout.getReason());
        _applySleepout.setApproved(applySleepout.isApproved());
        applySleepoutRepository.save(_applySleepout);

        // find user and user_member connected to apply_sleepout
        UserMember _userMember = _applySleepout.getUserMember();
        User _user = _userMember.getUser();

        ApplySleepoutResponse response = new ApplySleepoutResponse(_applySleepout.getId(),
                                              _user.getStudentno(), _user.getName(), _userMember.getDepartment(),
                                              _applySleepout.getDate_sleepout(), _applySleepout.getReason(), _applySleepout.getDate(),
                                              _applySleepout.isApproved());
        return new ResponseEntity<>(response, HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }

    // @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/apply-sleepout/{sleepoutId}/approve")
    public ResponseEntity<ApplySleepoutResponse> approveApplySleepout(@PathVariable(name="sleepoutId") Long sleepoutId) {
      Optional<ApplySleepout> sleepoutData = applySleepoutRepository.findById(sleepoutId);

      if (sleepoutData.isPresent()) {
        ApplySleepout _applySleepout = sleepoutData.get();
        _applySleepout.setApproved(true);
        applySleepoutRepository.save(_applySleepout);

        // find user and user_member connected to apply_sleepout
        UserMember _userMember = _applySleepout.getUserMember();
        User _user = _userMember.getUser();

        ApplySleepoutResponse response = new ApplySleepoutResponse(_applySleepout.getId(),
                                              _user.getStudentno(), _user.getName(), _userMember.getDepartment(),
                                              _applySleepout.getDate_sleepout(), _applySleepout.getReason(), _applySleepout.getDate(),
                                              _applySleepout.isApproved());
        return new ResponseEntity<>(response, HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }

    // @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/apply-sleepout/{sleepoutId}")
    public ResponseEntity<HttpStatus> deleteApplySleepout(@PathVariable("sleepoutId") long sleepoutId) {
      try {
        applySleepoutRepository.deleteById(sleepoutId);
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
