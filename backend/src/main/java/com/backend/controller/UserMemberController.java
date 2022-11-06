package com.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.model.User;
import com.backend.model.UserMember;
import com.backend.model.apply.ApplySleepout;
import com.backend.repository.UserMemberRepository;
import com.backend.repository.UserRepository;
import com.backend.repository.apply.ApplySleepoutRepository;

@RestController
@RequestMapping("/api/user-member")
public class UserMemberController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMemberRepository userMemberRepository;
    
    @Autowired
    ApplySleepoutRepository applySleepoutRepository;

    @GetMapping(path="/info/{studentNo}")
    public ResponseEntity<UserMember> getGeneralInfo(@PathVariable(name="studentNo") String studentNo) {
        Optional<User> _userData = userRepository.findByStudentno(studentNo);
        if(_userData.isPresent()) {
            User _user = _userData.get();
            Optional<UserMember> _userMemberData = userMemberRepository.findByUserId(_user.getId());
            if(_userMemberData.isPresent()) {
                return new ResponseEntity<>(_userMemberData.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path="/sleepout-info/{studentNo}")
    public ResponseEntity<List<ApplySleepout>> getSleepoutList(@PathVariable(name="studentNo") String studentNo) {
        Optional<User> _userData = userRepository.findByStudentno(studentNo);
        if(_userData.isPresent()) {
            User _user = _userData.get();
            Optional<UserMember> _userMemberData = userMemberRepository.findByUserId(_user.getId());
            if(_userMemberData.isPresent()) {
                UserMember _userMember = _userMemberData.get();
                List<ApplySleepout> _applySleepoutList = applySleepoutRepository.findByUserMemberId(_userMember.getId());
                return new ResponseEntity<>(_applySleepoutList, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
