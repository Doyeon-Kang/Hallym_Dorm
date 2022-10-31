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
import com.backend.model.apply.ApplyJoin;
import com.backend.repository.UserRepository;
import com.backend.repository.apply.ApplyJoinRepository;

@RestController
@RequestMapping(path="/api")
public class ApplyJoinController {
    @Autowired
    ApplyJoinRepository applyJoinRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping(path="/apply-join")
    public ResponseEntity<List<ApplyJoin>> getAllApplyJoin() {
        try {
          List<ApplyJoin> applyJoins = new ArrayList<ApplyJoin>();

          applyJoinRepository.findAll().forEach(applyJoins::add);
          if(applyJoins.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
          }

          return new ResponseEntity<>(applyJoins, HttpStatus.OK);
        } catch (Exception e) {
          return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/apply-join/{id}")
    public ResponseEntity<ApplyJoin> getApplyJoinById(@PathVariable("id") long id) {
      Optional<ApplyJoin> joinData = applyJoinRepository.findById(id);

      if(joinData.isPresent()) {
        ApplyJoin _applyJoin = joinData.get();
        
        return new ResponseEntity<>(applyJoinRepository.save(_applyJoin), HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }

    @PostMapping("/apply-join/{studentNo}")
    public ResponseEntity<ApplyJoin> createApplyJoin(@PathVariable(name="studentNo") String studentNo, @RequestBody ApplyJoin applyJoin) {
      try {
        Optional<User> _userData = userRepository.findByStudentno(studentNo);
        if(_userData.isPresent()) {
            User _user = _userData.get();
            ApplyJoin _applyJoin = new ApplyJoin(applyJoin.getEnglish_name(), applyJoin.getChinese_name(),
                        applyJoin.getGrade(), applyJoin.getGender(), applyJoin.getNationality(),
                        applyJoin.getDepartment(), applyJoin.getMajor(), applyJoin.getStudent_status(),
                        applyJoin.getPhone(), applyJoin.getAddress(), applyJoin.getGuardian_name(), applyJoin.getGuardian_relation(),
                        applyJoin.getGuardian_phone(), applyJoin.getLandline(), applyJoin.getHope_fac_1(),
                        applyJoin.getHope_fac_2(), applyJoin.getHope_fac_3(), applyJoin.getHope_fac_4(), applyJoin.getHope_fac_5(),
                        applyJoin.getHope_fac_6(), applyJoin.getPeriod(), applyJoin.getDate_join(), applyJoin.isSingle_yn(), 
                        applyJoin.isPri_ent(), applyJoin.isFgn_mate(), applyJoin.getAccpm_ent(), applyJoin.isPar_mes_yn());
            _applyJoin.setUser(_user);
            _applyJoin.setApproved(applyJoin.isApproved());
            applyJoinRepository.save(_applyJoin);
            return new ResponseEntity<>(_applyJoin, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
      } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    @PutMapping("/apply-join/{joinId}")
    public ResponseEntity<ApplyJoin> updateApplyJoin(@PathVariable("joinId") long joinId, @RequestBody ApplyJoin applyJoin) {
      Optional<ApplyJoin> joinData = applyJoinRepository.findById(joinId);
      if (joinData.isPresent()) {
        ApplyJoin _applyJoin = joinData.get();
        _applyJoin.setEnglish_name(applyJoin.getEnglish_name());
        _applyJoin.setChinese_name(applyJoin.getChinese_name());
        _applyJoin.setGrade(applyJoin.getGrade());
        _applyJoin.setGender(applyJoin.getGender());
        _applyJoin.setNationality(applyJoin.getNationality());
        _applyJoin.setDepartment(applyJoin.getDepartment());
        _applyJoin.setMajor(applyJoin.getMajor());
        _applyJoin.setStudent_status(applyJoin.getStudent_status());
        _applyJoin.setPhone(applyJoin.getPhone());
        _applyJoin.setAddress(applyJoin.getAddress());
        _applyJoin.setGuardian_name(applyJoin.getGuardian_name());
        _applyJoin.setGuardian_relation(applyJoin.getGuardian_relation());
        _applyJoin.setGuardian_phone(applyJoin.getGuardian_phone());
        _applyJoin.setLandline(applyJoin.getLandline());
        _applyJoin.setHope_fac_1(applyJoin.getHope_fac_1());
        _applyJoin.setHope_fac_2(applyJoin.getHope_fac_2());
        _applyJoin.setHope_fac_3(applyJoin.getHope_fac_3());
        _applyJoin.setHope_fac_4(applyJoin.getHope_fac_4());
        _applyJoin.setHope_fac_5(applyJoin.getHope_fac_5());
        _applyJoin.setHope_fac_6(applyJoin.getHope_fac_6());
        _applyJoin.setPeriod(applyJoin.getPeriod());
        _applyJoin.setSingle_yn(applyJoin.isSingle_yn());
        _applyJoin.setPri_ent(applyJoin.isPri_ent());
        _applyJoin.setFgn_mate(applyJoin.isFgn_mate());
        _applyJoin.setAccpm_ent(applyJoin.getAccpm_ent());
        _applyJoin.setPar_mes_yn(applyJoin.isPar_mes_yn());

        _applyJoin.setApproved(applyJoin.isApproved());
        return new ResponseEntity<>(applyJoinRepository.save(_applyJoin), HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }

    @PutMapping("/apply-join/{joinId}/approve")
    public ResponseEntity<ApplyJoin> approveApplyJoin(@PathVariable(name="joinId") Long joinId) {
      Optional<ApplyJoin> joinData = applyJoinRepository.findById(joinId);

      if (joinData.isPresent()) {
        ApplyJoin _applyJoin = joinData.get();
        _applyJoin.setApproved(true);
        applyJoinRepository.save(_applyJoin);
        return new ResponseEntity<>(_applyJoin, HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }

    @DeleteMapping("/apply-join/{joinId}")
    public ResponseEntity<HttpStatus> deleteApplyJoin(@PathVariable("joinId") long joinId) {
      try {
        applyJoinRepository.deleteById(joinId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    @DeleteMapping("/apply-join")
    public ResponseEntity<HttpStatus> deleteAllApplyJoins() {
      try {
        applyJoinRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }

    }


}

