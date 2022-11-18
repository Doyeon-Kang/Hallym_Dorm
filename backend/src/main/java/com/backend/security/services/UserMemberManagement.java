package com.backend.security.services;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.model.ERole;
import com.backend.model.Role;
import com.backend.model.User;
import com.backend.model.UserMember;
import com.backend.model.apply.ApplyJoin;
import com.backend.model.apply.ApplyResign;
import com.backend.repository.RoleRepository;
import com.backend.repository.UserMemberRepository;
import com.backend.repository.UserRepository;
import com.backend.repository.apply.ApplyJoinRepository;
import com.backend.repository.apply.ApplyResignRepository;

@Service
public class UserMemberManagement {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMemberRepository userMemberRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    ApplyJoinRepository applyJoinRepository;

    @Autowired
    ApplyResignRepository applyResignRepository;

    @Transactional
    public boolean userMemberExists(User user) {
        Optional<UserMember> _userMemberData = userMemberRepository.findByUserId(user.getId());
        if(_userMemberData.isPresent()) {
            UserMember _userMember = _userMemberData.get();
            Optional<ApplyResign> _applyResignData = applyResignRepository.findByUserId(user.getId());
            if(_applyResignData.isPresent()) {
                ApplyResign _applyResign = _applyResignData.get();
                if((_applyResign.getRes_date().isBefore(LocalDate.now()) || _applyResign.getRes_date().isEqual(LocalDate.now()))
                    && (_applyResign.isApproved())) {
                        userMemberRepository.deleteById(_userMember.getId());
                        // detele user_member from roles

                        Set<Role> roles = user.getRoles();
                        
                        roles.removeIf(role -> role.getName() == ERole.ROLE_USER_MEMBER);
                        user.setRoles(roles);
                        userRepository.save(user);

                        return false;
                } else {
                    return true;
                }
            }
            else return true;
        }
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

                    Set<Role> roles = user.getRoles();
                    Role userMemberRole = roleRepository.findByName(ERole.ROLE_USER_MEMBER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(userMemberRole);
                    user.setRoles(roles);

                    userRepository.save(user);

                    return true;
                } else return false;
            } else return false;
        }
    }
    
}
