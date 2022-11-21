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
import com.backend.payload.request.ApplyResignRequest;
import com.backend.payload.response.ApplyResignResponse;
import com.backend.repository.UserMemberRepository;
import com.backend.repository.UserRepository;
import com.backend.repository.apply.ApplyResignRepository;

@RestController
@RequestMapping(path="/api")
public class ApplyResignController {
    @Autowired
    ApplyResignRepository applyResignRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMemberRepository userMemberRepository;

    // @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(path="/apply-resign")
    public ResponseEntity<List<ApplyResignResponse>> getAllApplyResign() {
        try {
          List<ApplyResign> applyResigns = applyResignRepository.findAll();
          if(applyResigns.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
          }
          List<ApplyResignResponse> responses = new ArrayList<ApplyResignResponse>();

          for(ApplyResign _applyResign : applyResigns) {
            User _user = _applyResign.getUser();
            // Optional<UserMember> _userMemberData = userMemberRepository.findByUserId(_user.getId());
            responses.add(new ApplyResignResponse(_applyResign.getId(), _user.getStudentno(), _user.getName(),
                                                     _applyResign.getRes_date(), _applyResign.getRes_reason(),
                                                    _applyResign.getDate(), _applyResign.isApproved()));
          }
          return new ResponseEntity<>(responses, HttpStatus.OK);
        } catch (Exception e) {
          return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/apply-resign/{id}")
    public ResponseEntity<ApplyResignResponse> getApplyResignById(@PathVariable("id") long id) {
      Optional<ApplyResign> resignData = applyResignRepository.findById(id);

      if(resignData.isPresent()) {
        ApplyResign _applyResign = resignData.get();

        ApplyResignResponse response;
        User _user = _applyResign.getUser();
        // Optional<UserMember> _userMemberData = userMemberRepository.findByUserId(_user.getId());
        // if(_userMemberData.isPresent()) {
          // UserMember _userMember = _userMemberData.get();
          response = new ApplyResignResponse(_applyResign.getId(), _user.getStudentno(), _user.getName(),
                                                _applyResign.getRes_date(), _applyResign.getRes_reason(),
                                                _applyResign.getDate(), _applyResign.isApproved());
          return new ResponseEntity<>(response, HttpStatus.OK);
        // } else {
        //   return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        // }
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }

    @PostMapping("/apply-resign")
    public ResponseEntity<ApplyResignResponse> createApplyResign(@RequestBody ApplyResignRequest applyResign) {
        try {
            Optional<User> _userData = userRepository.findByStudentno(applyResign.getStudentNo());
            if(_userData.isPresent()) {
                User _user = _userData.get();
                ApplyResign _applyResign = new ApplyResign(applyResign.getRes_date(), applyResign.getRes_reason());
               
                _applyResign.setUser(_user);
                // _applyResign.setApproved(applyResign.isApproved());
                applyResignRepository.save(_applyResign);

                ApplyResignResponse response;
                // Optional<UserMember> _userMemberData = userMemberRepository.findByUserId(_user.getId());
                // if(_userMemberData.isPresent()) {
                  // UserMember _userMember = _userMemberData.get();
                  response = new ApplyResignResponse(_applyResign.getId(), _user.getStudentno(), _user.getName(),
                                                        _applyResign.getRes_date(), _applyResign.getRes_reason(),
                                                        _applyResign.getDate(), _applyResign.isApproved());
                  return new ResponseEntity<>(response, HttpStatus.OK);
                // } else {
                //   return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                // }
            } else {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
          } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
          }
        }
    

    // @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/apply-resign/{resignId}")
    public ResponseEntity<ApplyResignResponse> updateApplyResign(@PathVariable("resignId") long resignId, @RequestBody ApplyResignRequest applyResign) {
      Optional<ApplyResign> resignData = applyResignRepository.findById(resignId);
      if (resignData.isPresent()) {
        ApplyResign _applyResign = resignData.get();
        _applyResign.setRes_date(applyResign.getRes_date());
        _applyResign.setRes_reason(applyResign.getRes_reason());
        _applyResign.setApproved(applyResign.isApproved());

        ApplyResignResponse response;
        User _user = _applyResign.getUser();
        // Optional<UserMember> _userMemberData = userMemberRepository.findByUserId(_user.getId());
        // if(_userMemberData.isPresent()) {
          // UserMember _userMember = _userMemberData.get();
          response = new ApplyResignResponse(_applyResign.getId(), _user.getStudentno(), _user.getName(),
                                                _applyResign.getRes_date(), _applyResign.getRes_reason(),
                                                _applyResign.getDate(), _applyResign.isApproved());
          return new ResponseEntity<>(response, HttpStatus.OK);
        // } else {
        //   return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        // }
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }

    // @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/apply-resign/{resignId}/approve")
    public ResponseEntity<ApplyResignResponse> approveApplyResign(@PathVariable(name="resignId") Long resignId) {
      Optional<ApplyResign> resignData = applyResignRepository.findById(resignId);

      if (resignData.isPresent()) {
        ApplyResign _applyResign = resignData.get();
        _applyResign.setApproved(true);
        applyResignRepository.save(_applyResign);

        ApplyResignResponse response;
        User _user = _applyResign.getUser();
        // Optional<UserMember> _userMemberData = userMemberRepository.findByUserId(_user.getId());
        // if(_userMemberData.isPresent()) {
          // UserMember _userMember = _userMemberData.get();
          response = new ApplyResignResponse(_applyResign.getId(), _user.getStudentno(), _user.getName(),
                                                _applyResign.getRes_date(), _applyResign.getRes_reason(),
                                                _applyResign.getDate(), _applyResign.isApproved());
          return new ResponseEntity<>(response, HttpStatus.OK);
        // } else {
        //   return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        // }
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }

    // @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/apply-resign/{resignId}")
    public ResponseEntity<HttpStatus> deleteApplyResign(@PathVariable("resignId") long resignId) {
      try {
        applyResignRepository.deleteById(resignId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    // @PreAuthorize("hasRole('ROLE_ADMIN')")
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
