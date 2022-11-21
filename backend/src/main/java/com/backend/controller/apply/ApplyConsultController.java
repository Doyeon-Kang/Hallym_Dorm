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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.model.User;
import com.backend.model.UserMember;
import com.backend.model.apply.ApplyConsult;
import com.backend.payload.request.ApplyConsultRequest;
import com.backend.payload.response.ApplyConsultResponse;
import com.backend.repository.UserMemberRepository;
import com.backend.repository.UserRepository;
import com.backend.repository.apply.ApplyConsultRepository;
import com.backend.security.services.UserMemberManagement;

@RestController
@RequestMapping(path="/api")
public class ApplyConsultController {
    @Autowired
    ApplyConsultRepository applyConsultRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMemberRepository userMemberRepository;

    @Autowired
    UserMemberManagement userMemberManager;

    // @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(path="/apply-consult")
    public ResponseEntity<List<ApplyConsultResponse>> getAllApplyConsult() {
        try {
          List<ApplyConsult> applyConsults = applyConsultRepository.findAll();
          List<ApplyConsultResponse> consultResponses = new ArrayList<ApplyConsultResponse>();

          if(applyConsults.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
          } else {
            for(ApplyConsult _applyConsult : applyConsults) {
              UserMember _userMember = _applyConsult.getUserMember();
              User _user = _userMember.getUser();

              consultResponses.add(new ApplyConsultResponse(_applyConsult.getId(), _user.getStudentno(),
                                  _user.getName(), _userMember.getDepartment(), _applyConsult.getDay_of_week(),
                                  _applyConsult.isTimeslot1_okay(), _applyConsult.isTimeslot2_okay(), _applyConsult.isTimeslot3_okay(),
                                  _applyConsult.isTimeslot4_okay(), _applyConsult.isTimeslot5_okay(), _applyConsult.isTimeslot6_okay(),
                                  _applyConsult.isTimeslot7_okay(), _applyConsult.isTimeslot8_okay(), _applyConsult.getTopic(), 
                                  _applyConsult.getSubject(), _applyConsult.getDate()));
            }
          }

          return new ResponseEntity<>(consultResponses, HttpStatus.OK);
        } catch (Exception e) {
          return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/apply-consult/{id}")
    public ResponseEntity<ApplyConsultResponse> getApplyConsultById(@PathVariable("id") long id) {
      Optional<ApplyConsult> consultData = applyConsultRepository.findById(id);

      if(consultData.isPresent()) {
        ApplyConsult _applyConsult = consultData.get();
        
        UserMember _userMember = _applyConsult.getUserMember();
        User _user = _userMember.getUser();

        ApplyConsultResponse response = new ApplyConsultResponse(_applyConsult.getId(), _user.getStudentno(),
                                          _user.getName(), _userMember.getDepartment(), _applyConsult.getDay_of_week(),
                                          _applyConsult.isTimeslot1_okay(), _applyConsult.isTimeslot2_okay(), _applyConsult.isTimeslot3_okay(),
                                          _applyConsult.isTimeslot4_okay(), _applyConsult.isTimeslot5_okay(), _applyConsult.isTimeslot6_okay(),
                                          _applyConsult.isTimeslot7_okay(), _applyConsult.isTimeslot8_okay(), _applyConsult.getTopic(), 
                                          _applyConsult.getSubject(), _applyConsult.getDate());

        return new ResponseEntity<>(response, HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }

    @PostMapping("/apply-consult")
    public ResponseEntity<ApplyConsultResponse> createApplyConsult(@RequestBody ApplyConsultRequest applyConsult) {
      try {
        Optional<User> _userData = userRepository.findByStudentno(applyConsult.getStudentNo());
        if(_userData.isPresent()) {
          User _user = _userData.get();
          if(userMemberManager.userMemberExists(_user)) {
              int[] timeslots = applyConsult.getTimeslots();

              ApplyConsult _applyConsult = new ApplyConsult();
              _applyConsult.setDay_of_week(applyConsult.getDayOfWeek());

              for(int timeslot : timeslots) {
                switch(timeslot) {
                  case 1:
                    _applyConsult.setTimeslot1_okay(true);
                    break;
                  case 2:
                    _applyConsult.setTimeslot2_okay(true);
                    break;
                  case 3:
                    _applyConsult.setTimeslot3_okay(true);
                    break;
                  case 4:
                    _applyConsult.setTimeslot4_okay(true);
                    break;
                  case 5:
                    _applyConsult.setTimeslot5_okay(true);
                    break;
                  case 6:
                    _applyConsult.setTimeslot6_okay(true);
                    break;
                  case 7:
                    _applyConsult.setTimeslot7_okay(true);
                    break;
                  case 8:
                    _applyConsult.setTimeslot8_okay(true);
                    break;
                  default:
                    break;
                }
              }
              _applyConsult.setTopic(applyConsult.getTopic());
              _applyConsult.setSubject(applyConsult.getSubject());
              
              Optional<UserMember> _userMemberData = userMemberRepository.findByUserId(_user.getId());
              UserMember _userMember = _userMemberData.get();
              _applyConsult.setUserMember(_userMemberData.get());
              applyConsultRepository.save(_applyConsult);

              ApplyConsultResponse response = new ApplyConsultResponse(_applyConsult.getId(), _user.getStudentno(),
                                            _user.getName(), _userMember.getDepartment(), _applyConsult.getDay_of_week(),
                                            _applyConsult.isTimeslot1_okay(), _applyConsult.isTimeslot2_okay(), _applyConsult.isTimeslot3_okay(),
                                            _applyConsult.isTimeslot4_okay(), _applyConsult.isTimeslot5_okay(), _applyConsult.isTimeslot6_okay(),
                                            _applyConsult.isTimeslot7_okay(), _applyConsult.isTimeslot8_okay(), _applyConsult.getTopic(), 
                                            _applyConsult.getSubject(), _applyConsult.getDate());
              return new ResponseEntity<>(response, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
          } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
          }
      } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    // @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/apply-consult/{consultId}")
    public ResponseEntity<HttpStatus> deleteApplyConsult(@PathVariable("consultId") long consultId) {
      try {
        applyConsultRepository.deleteById(consultId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    // @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/apply-consult")
    public ResponseEntity<HttpStatus> deleteAllApplyConsults() {
      try {
        applyConsultRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }

    }
}
