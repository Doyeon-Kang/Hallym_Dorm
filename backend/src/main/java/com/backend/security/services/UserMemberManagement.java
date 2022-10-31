package com.backend.security.services;

import java.time.LocalDate;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.model.User;
import com.backend.model.UserMember;
import com.backend.model.apply.ApplyJoin;
import com.backend.repository.UserMemberRepository;
import com.backend.repository.UserRepository;
import com.backend.repository.apply.ApplyJoinRepository;

@Service
public class UserMemberManagement {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMemberRepository userMemberRepository;

    @Autowired
    ApplyJoinRepository applyJoinRepository;

    @Transactional
    public boolean userMemberExists(User user) {
        Optional<UserMember> _userMemberData = userMemberRepository.findByUserId(user.getId());
        if(_userMemberData.isPresent()) return true;
        else {
            Optional<ApplyJoin> _applyJoinData = applyJoinRepository.findByUserId(user.getId());
            if(_applyJoinData.isPresent()) {
                ApplyJoin _applyJoin = _applyJoinData.get();
                if((_applyJoin.getDate_join().isBefore(LocalDate.now()) || _applyJoin.getDate_join().isEqual(LocalDate.now()))
                    && (_applyJoin.isApproved())) {
                    UserMember _userMember = new UserMember();
                    _userMember.setUser(user);
                    _userMember.setEnglish_name(_applyJoin.getEnglish_name());
                    _userMember.setChinese_name(_applyJoin.getChinese_name());
                    _userMember.setGrade(_applyJoin.getGrade());
                    _userMember.setGender(_applyJoin.getGender());
                    _userMember.setNationality(_applyJoin.getNationality());
                    _userMember.setDepartment(_applyJoin.getDepartment());
                    _userMember.setMajor(_applyJoin.getMajor());
                    _userMember.setStudent_status(_applyJoin.getStudent_status());
                    _userMember.setPhone(_applyJoin.getPhone());
                    _userMember.setAddress(_applyJoin.getAddress());
                    _userMember.setGuardian_name(_applyJoin.getGuardian_name());
                    _userMember.setGuardian_relation(_applyJoin.getGuardian_relation());
                    _userMember.setGuardian_phone(_applyJoin.getGuardian_phone());
                    _userMember.setLandline(_applyJoin.getLandline());
                    _userMember.setRes_fac(_applyJoin.getRes_fac());
                    _userMember.setRes_room(_applyJoin.getRes_room());

                    userMemberRepository.save(_userMember);
                    return true;
                } else return false;
            } else return false;
        }
    }
    
}
