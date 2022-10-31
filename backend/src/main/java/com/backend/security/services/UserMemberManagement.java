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

    // @Transactional
    // public boolean userMemberExists(User user) {
    //     Optional<UserMember> _userMemberData = userMemberRepository.findByUserId(user.getId());
    //     if(_userMemberData.isPresent()) return true;
    //     else {
    //         Optional<ApplyJoin> _applyJoinData = applyJoinRepository.findByUserId(user.getId());
    //         if(_applyJoinData.isPresent()) {
    //             ApplyJoin _applyJoin = _applyJoinData.get();
    //             if((_applyJoin.getDate_join().isBefore(LocalDate.now()) || _applyJoin.getDate_join().isEqual(LocalDate.now()))
    //                     && _applyJoin.isApproved()) {
    //                 UserMember _userMember = new UserMember();

    //             }
    //         } else return false;
    //     }
    // }
    
}
