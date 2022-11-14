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
import com.backend.model.apply.ApplyStudyroom;
import com.backend.model.apply.ApplyStudyroomSchedule;
import com.backend.repository.UserMemberRepository;
import com.backend.repository.UserRepository;
import com.backend.repository.apply.ApplyStudyroomRepository;
import com.backend.repository.apply.ApplyStudyroomScheduleRepository;
import com.backend.security.services.UserMemberManagement;

@RestController
@RequestMapping(path="/api")
public class ApplyStudyroomController {
    @Autowired
    ApplyStudyroomRepository applyStudyroomRepository;

    @Autowired
    ApplyStudyroomScheduleRepository applyStudyroomScheduleRepository;

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

    private boolean takeTimeslot(ApplyStudyroomSchedule schedule, int timeslot) {
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
            default:
                return false;
        }
    }

    @PostMapping("/apply-studyroom/{studentNo}-{seat}")
    public ResponseEntity<ApplyStudyroom> createApplyStudyroom(@PathVariable(name="studentNo") String studentNo, @PathVariable(name="seat") int seat, @RequestBody ApplyStudyroom applyStudyroom) {
      try {
        Optional<User> _userData = userRepository.findByStudentno(studentNo);
        if(_userData.isPresent()) {
          User _user = _userData.get();
          if(userMemberManager.userMemberExists(_user)) {
            Optional <ApplyStudyroomSchedule> _studyroomScheduleData = applyStudyroomScheduleRepository.findBySeat(seat);

            if(_studyroomScheduleData.isPresent()) {
                ApplyStudyroomSchedule _studyroomSchedule = _studyroomScheduleData.get();
                int timeslot1 = applyStudyroom.getTimeslot1();
                int timeslot2 = applyStudyroom.getTimeslot2();
                int timeslot3 = applyStudyroom.getTimeslot3();

                if(((takeTimeslot(_studyroomSchedule, timeslot1)) && (takeTimeslot(_studyroomSchedule, timeslot2)))
                        && (takeTimeslot(_studyroomSchedule, timeslot3))) {
                            applyStudyroomScheduleRepository.save(_studyroomSchedule);

                            ApplyStudyroom _applyStudyroom = new ApplyStudyroom(applyStudyroom.getTimeslot1(), applyStudyroom.getTimeslot2(), applyStudyroom.getTimeslot3());
                        
                            Optional<UserMember> _userMemberData = userMemberRepository.findByUserId(_user.getId());
                            _applyStudyroom.setUserMember(_userMemberData.get());

                            _applyStudyroom.setApplyStudyroomSchedule(_studyroomSchedule);

                            return new ResponseEntity<>(applyStudyroomRepository.save(_applyStudyroom), HttpStatus.CREATED);
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

