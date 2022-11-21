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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.model.User;
import com.backend.model.UserMember;
import com.backend.model.apply.ApplyStudyroom;
import com.backend.model.apply.ApplyStudyroomSchedule;
import com.backend.payload.request.ApplyStudyroomRequest;
import com.backend.payload.response.ApplyStudyroomResponse;
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
    public ResponseEntity<List<ApplyStudyroomResponse>> getAllApplyStudyroom() {
        try {
          List<ApplyStudyroom> applyStudyrooms = applyStudyroomRepository.findAll();
          List<ApplyStudyroomResponse> studyroomResponses = new ArrayList<ApplyStudyroomResponse>();
          if(applyStudyrooms.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
          }

          for(ApplyStudyroom _applyStudyroom : applyStudyrooms) {
            UserMember _userMember = _applyStudyroom.getUserMember();
            User _user = _userMember.getUser();
            studyroomResponses.add(new ApplyStudyroomResponse(_applyStudyroom.getId(), _user.getStudentno(), _user.getName(),
                                  _userMember.getDepartment(), _applyStudyroom.getSeat(), _applyStudyroom.getTimeslot1(),
                                  _applyStudyroom.getTimeslot2(), _applyStudyroom.getTimeslot3(), _applyStudyroom.getDate()));
          }

          return new ResponseEntity<>(studyroomResponses, HttpStatus.OK);
        } catch (Exception e) {
          return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/apply-studyroom/{id}")
    public ResponseEntity<ApplyStudyroomResponse> getApplyStudyroomById(@PathVariable("id") long id) {
      Optional<ApplyStudyroom> studyroomData = applyStudyroomRepository.findById(id);

      if(studyroomData.isPresent()) {
        ApplyStudyroom _applyStudyroom = studyroomData.get();

        UserMember _userMember = _applyStudyroom.getUserMember();
        User _user = _userMember.getUser();
        ApplyStudyroomResponse response = new ApplyStudyroomResponse(_applyStudyroom.getId(), _user.getStudentno(), _user.getName(),
                                                _userMember.getDepartment(), _applyStudyroom.getSeat(), _applyStudyroom.getTimeslot1(),
                                                _applyStudyroom.getTimeslot2(), _applyStudyroom.getTimeslot3(), _applyStudyroom.getDate());
        
        return new ResponseEntity<>(response, HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }

    @GetMapping(path="/apply-studyroom/my-studyroom")
    public ResponseEntity<List<ApplyStudyroomResponse>> getMySleepout(@RequestParam("studentNo") String studentNo) {
      try {
        Optional<User> _userData = userRepository.findByStudentno(studentNo);
        if(_userData.isPresent()) {
          User _user = _userData.get();
          Optional<UserMember> _userMemberData = userMemberRepository.findByUserId(_user.getId());
          if(_userMemberData.isPresent()) {
            UserMember _userMember = _userMemberData.get();

            List<ApplyStudyroom> studyrooms = applyStudyroomRepository.findByUserMemberId(_userMember.getId());
            
            if(studyrooms.isEmpty()) {
              return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }

            List<ApplyStudyroomResponse> studyroomResponses = new ArrayList<ApplyStudyroomResponse>();
            
            studyrooms.forEach(_applyStudyroom -> {
              studyroomResponses.add(new ApplyStudyroomResponse(_applyStudyroom.getId(), _user.getStudentno(), _user.getName(),
                                  _userMember.getDepartment(), _applyStudyroom.getSeat(), _applyStudyroom.getTimeslot1(),
                                  _applyStudyroom.getTimeslot2(), _applyStudyroom.getTimeslot3(), _applyStudyroom.getDate()));
            });

            return new ResponseEntity<List<ApplyStudyroomResponse>>(studyroomResponses, HttpStatus.OK);  
          } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
          }
        } else {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
      } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
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

    @PostMapping("/apply-studyroom")
    public ResponseEntity<ApplyStudyroomResponse> createApplyStudyroom(@RequestBody ApplyStudyroomRequest applyStudyroom) {
      try {
        Optional<User> _userData = userRepository.findByStudentno(applyStudyroom.getStudentNo());
        if(_userData.isPresent()) {
          User _user = _userData.get();
          if(userMemberManager.userMemberExists(_user)) {
            Optional <ApplyStudyroomSchedule> _studyroomScheduleData = applyStudyroomScheduleRepository.findBySeat(applyStudyroom.getSeatNo());
            System.out.println(_studyroomScheduleData);

            if(_studyroomScheduleData.isPresent()) {
                ApplyStudyroomSchedule _studyroomSchedule = _studyroomScheduleData.get();
                ApplyStudyroom _applyStudyroom = new ApplyStudyroom();
                int[] timeslots = applyStudyroom.getTimeslots();

                for(int i = 0; i < timeslots.length; i++) {
                  if(takeTimeslot(_studyroomSchedule, timeslots[i])) {
                    applyStudyroomScheduleRepository.save(_studyroomSchedule);
                    switch (i + 1) {
                      case 1:
                        _applyStudyroom.setTimeslot1(timeslots[i]);
                        break;
                      case 2:
                        _applyStudyroom.setTimeslot2(timeslots[i]);
                        break;
                      case 3:
                        _applyStudyroom.setTimeslot3(timeslots[i]);
                        break;
                      default:
                        break;
                    }
                  } else {
                    return new ResponseEntity<>(null, HttpStatus.CONFLICT);
                  }
                }
                Optional<UserMember> _userMemberData = userMemberRepository.findByUserId(_user.getId());
                UserMember _userMember = _userMemberData.get();
                _applyStudyroom.setUserMember(_userMemberData.get());
                _applyStudyroom.setSeat(applyStudyroom.getSeatNo());
                _applyStudyroom.setApplyStudyroomSchedule(_studyroomSchedule);
                applyStudyroomRepository.save(_applyStudyroom);

                ApplyStudyroomResponse response = new ApplyStudyroomResponse(_applyStudyroom.getId(), _user.getStudentno(), _user.getName(),
                                                                              _userMember.getDepartment(), _applyStudyroom.getSeat(), _applyStudyroom.getTimeslot1(),
                                                                              _applyStudyroom.getTimeslot2(), _applyStudyroom.getTimeslot3(), _applyStudyroom.getDate());

                return new ResponseEntity<>(response, HttpStatus.CREATED);
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
      } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
      }
    }

    private boolean freeTimeslot(ApplyStudyroomSchedule schedule, int timeslot) {
      switch(timeslot) {
          case 1:
              if(schedule.isTimeslot1_taken() == true) {
                  schedule.setTimeslot1_taken(false);
                  return true;
              } else {
                  return false;
              }
          case 2:
              if(schedule.isTimeslot2_taken() == true) {
                  schedule.setTimeslot2_taken(false);
                  return true;
              } else {
                  return false;
              }
          case 3:
              if(schedule.isTimeslot3_taken() == true) {
                  schedule.setTimeslot3_taken(false);
                  return true;
              } else {
                  return false;
              }
          case 4:
              if(schedule.isTimeslot4_taken() == true) {
                  schedule.setTimeslot4_taken(false);
                  return true;
              } else {
                  return false;
              }
          case 5:
              if(schedule.isTimeslot5_taken() == true) {
                  schedule.setTimeslot5_taken(false);
                  return true;
              } else {
                  return false;
              }
          case 6:
              if(schedule.isTimeslot6_taken() == true) {
                  schedule.setTimeslot6_taken(false);
                  return true;
              } else {
                  return false;
              }
          case 7:
              if(schedule.isTimeslot7_taken() == true) {
                  schedule.setTimeslot7_taken(false);
                  return true;
              } else {
                  return false;
              }
          default:
              return false;
      }
  }

    // @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/apply-studyroom/{studyroomId}")
    public ResponseEntity<HttpStatus> deleteApplyStudyroom(@PathVariable("studyroomId") long studyroomId) {
      try {
        Optional<ApplyStudyroom> _applyStudyroomData = applyStudyroomRepository.findById(studyroomId);
        if(_applyStudyroomData.isPresent()) {
          ApplyStudyroom _applyStudyroom = _applyStudyroomData.get();
          
          Optional<ApplyStudyroomSchedule> _applyStudyroomScheduleData = applyStudyroomScheduleRepository.findBySeat(_applyStudyroom.getSeat());
          if(_applyStudyroomScheduleData.isPresent()) {
            ApplyStudyroomSchedule _applyStudyroomSchedule = _applyStudyroomScheduleData.get();
            if(_applyStudyroom.getTimeslot1() != 0) {
              freeTimeslot(_applyStudyroomSchedule, _applyStudyroom.getTimeslot1());
            }

            if(_applyStudyroom.getTimeslot2() != 0) {
              freeTimeslot(_applyStudyroomSchedule, _applyStudyroom.getTimeslot2());
            }

            if(_applyStudyroom.getTimeslot3() != 0) {
              freeTimeslot(_applyStudyroomSchedule, _applyStudyroom.getTimeslot3());
            }
          } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
          }
        } else {
          return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

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
        List<ApplyStudyroomSchedule> scheduleList = applyStudyroomScheduleRepository.findAll();
        for(ApplyStudyroomSchedule schedule : scheduleList) {
          schedule.setTimeslot1_taken(false);
          schedule.setTimeslot2_taken(false);
          schedule.setTimeslot3_taken(false);
          schedule.setTimeslot4_taken(false);
          schedule.setTimeslot5_taken(false);
          schedule.setTimeslot6_taken(false);
          schedule.setTimeslot7_taken(false);
          applyStudyroomScheduleRepository.save(schedule);
        }
        applyStudyroomRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }

    }
}

