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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.model.User;
import com.backend.model.UserMember;
import com.backend.model.apply.ApplyConsult;
import com.backend.model.apply.ApplyConsultSchedule;
import com.backend.repository.UserMemberRepository;
import com.backend.repository.UserRepository;
import com.backend.repository.apply.ApplyConsultRepository;
import com.backend.repository.apply.ApplyConsultScheduleRepository;
import com.backend.security.services.UserMemberManagement;

@RestController
@RequestMapping(path="/api")
public class ApplyConsultController {
    @Autowired
    ApplyConsultRepository applyConsultRepository;

    @Autowired
    ApplyConsultScheduleRepository applyConsultScheduleRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMemberRepository userMemberRepository;

    @Autowired
    UserMemberManagement userMemberManager;

    // @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(path="/apply-consult")
    public ResponseEntity<List<ApplyConsult>> getAllApplyConsult() {
        try {
          List<ApplyConsult> applyConsults = new ArrayList<ApplyConsult>();

          applyConsultRepository.findAll().forEach(applyConsults::add);
          if(applyConsults.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
          }

          return new ResponseEntity<>(applyConsults, HttpStatus.OK);
        } catch (Exception e) {
          return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/apply-consult/{id}")
    public ResponseEntity<ApplyConsult> getApplyConsultById(@PathVariable("id") long id) {
      Optional<ApplyConsult> consultData = applyConsultRepository.findById(id);

      if(consultData.isPresent()) {
        ApplyConsult _applyConsult = consultData.get();
        
        return new ResponseEntity<>(applyConsultRepository.save(_applyConsult), HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }

    private boolean takeTimeslot(ApplyConsultSchedule schedule, int timeslot) {
        switch(timeslot) {
            case 1:
                if(schedule.isTimeslot1_taken() == false) {
                    schedule.setTimeslot1_taken(true);
                    return true;
                } else {
                    return false;
                }
            case 2:
                if(schedule.isTimeslot2_taken() == false) {
                    schedule.setTimeslot2_taken(true);
                    return true;
                } else {
                    return false;
                }
            case 3:
                if(schedule.isTimeslot3_taken() == false) {
                    schedule.setTimeslot3_taken(true);
                    return true;
                } else {
                    return false;
                }
            case 4:
                if(schedule.isTimeslot4_taken() == false) {
                    schedule.setTimeslot4_taken(true);
                    return true;
                } else {
                    return false;
                }
            case 5:
                if(schedule.isTimeslot5_taken() == false) {
                    schedule.setTimeslot5_taken(true);
                    return true;
                } else {
                    return false;
                }
            case 6:
                if(schedule.isTimeslot6_taken() == false) {
                    schedule.setTimeslot6_taken(true);
                    return true;
                } else {
                    return false;
                }
            case 7:
                if(schedule.isTimeslot7_taken() == false) {
                    schedule.setTimeslot7_taken(true);
                    return true;
                } else {
                    return false;
                }
            case 8:
                if(schedule.isTimeslot8_taken() == false) {
                    schedule.setTimeslot8_taken(true);
                    return true;
                } else {
                    return false;
                }
            default:
                return false;
        }
    }

    @PostMapping("/apply-consult/{studentNo}-{day}")
    public ResponseEntity<ApplyConsult> createApplyConsult(@PathVariable(name="studentNo") String studentNo, @PathVariable(name="day") String day, @RequestBody ApplyConsult applyConsult) {
      try {
        Optional<User> _userData = userRepository.findByStudentno(studentNo);
        if(_userData.isPresent()) {
          User _user = _userData.get();
          if(userMemberManager.userMemberExists(_user)) {
            Optional <ApplyConsultSchedule> _consultScheduleData = applyConsultScheduleRepository.findByDay(day);

            if(_consultScheduleData.isPresent()) {
                ApplyConsultSchedule _consultSchedule = _consultScheduleData.get();

                int timeslot = applyConsult.getTimeslot();

                if(takeTimeslot(_consultSchedule, timeslot)) {
                            applyConsultScheduleRepository.save(_consultSchedule);

                            ApplyConsult _applyConsult = new ApplyConsult(applyConsult.getTimeslot());
                        
                            Optional<UserMember> _userMemberData = userMemberRepository.findByUserId(_user.getId());
                            _applyConsult.setUserMember(_userMemberData.get());

                            _applyConsult.setApplyConsultSchedule(_consultSchedule);

                            return new ResponseEntity<>(applyConsultRepository.save(_applyConsult), HttpStatus.CREATED);
                        }
                else {
                    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
                }

            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
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
