package com.backend.controller;

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

import com.backend.model.Point;
import com.backend.model.User;
import com.backend.model.UserMember;
import com.backend.payload.request.PointRequest;
import com.backend.payload.response.PointResponse;
import com.backend.repository.PointRepository;
import com.backend.repository.UserMemberRepository;
import com.backend.repository.UserRepository;
import com.backend.security.services.UserMemberManagement;

@RestController
@RequestMapping(path="/api/point")
public class PointController {
    @Autowired
    UserMemberRepository userMemberRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PointRepository pointRepository;

    @Autowired
    UserMemberManagement userMemberManager;

    @GetMapping
    public ResponseEntity<List<PointResponse>> getListPoint() {
        try {    
            List<Point> points = pointRepository.findAll();

            if(points.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            List<PointResponse> responses = new ArrayList<PointResponse>();

            for(Point point : points) {
                UserMember _userMember = point.getUserMember();
                User _user = _userMember.getUser();
                responses.add(new PointResponse(point.getId(), _user.getStudentno(), _user.getName(), 
                _userMember.getDepartment(), point.getReason(), point.getDate_receive(), point.getDate(), point.getPlusPoint(), point.getMinusPoint()));
            }

            return new ResponseEntity<List<PointResponse>>(responses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path="/{id}")
    public ResponseEntity<PointResponse> getPointById(@PathVariable(name="id") Long id) {
        try {
            Optional<Point> pointData = pointRepository.findById(id);

            if(pointData.isPresent()) {
                Point point = pointData.get();
                UserMember _userMember = point.getUserMember();
                User _user = _userMember.getUser();
                PointResponse response = new PointResponse(point.getId(), _user.getStudentno(), _user.getName(), 
                _userMember.getDepartment(), point.getReason(), point.getDate_receive(), point.getDate(), point.getPlusPoint(), point.getMinusPoint());

                return new ResponseEntity<PointResponse>(response, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path="/my-point")
    public ResponseEntity<List<PointResponse>> getMyPoints(@RequestParam("studentNo") String studentNo) {
        try {
            Optional<User> _userData = userRepository.findByStudentno(studentNo);
            if(_userData.isPresent()) {
                User _user = _userData.get();
                Optional<UserMember> _userMemberData = userMemberRepository.findByUserId(_user.getId());
                if(_userMemberData.isPresent()) {
                    UserMember _userMember = _userMemberData.get();

                    List<Point> myPoints = pointRepository.findByUserMemberId(_userMember.getId());

                    if(myPoints.isEmpty()) {
                        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
                    }

                    List<PointResponse> responses = new ArrayList<PointResponse>();

                    myPoints.forEach(point -> {
                        responses.add(new PointResponse(point.getId(), _user.getStudentno(), _user.getName(), 
                        _userMember.getDepartment(), point.getReason(), point.getDate_receive(), point.getDate(), 
                        point.getPlusPoint(), point.getMinusPoint()));
                    });

                    return new ResponseEntity<List<PointResponse>>(responses, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path="")
    public ResponseEntity<PointResponse> addPoint(@RequestBody PointRequest pointRequest) {
        try {
            Optional<User> _userData = userRepository.findByStudentno(pointRequest.getStudentNo());
            System.out.println("student number = " + pointRequest.getReason());
            if(_userData.isPresent()) {
                User _user = _userData.get();
                if(userMemberManager.userMemberExists(_user)) {
                    Point _point = new Point(pointRequest.getReason(), pointRequest.getDate_receive(), pointRequest.getPlusPoint(), pointRequest.getMinusPoint());
                    Optional<UserMember> _userMemberData = userMemberRepository.findByUserId(_user.getId());
                    UserMember _userMember = _userMemberData.get();
                    _point.setUserMember(_userMember);
                    pointRepository.save(_point);

                    _userMember.setPlusPoint(_userMember.getPlusPoint() + _point.getPlusPoint());
                    _userMember.setMinusPoint(_userMember.getMinusPoint() - _point.getMinusPoint());
                    userMemberRepository.save(_userMember);

                    PointResponse response = new PointResponse(_point.getId(), _user.getStudentno(), _user.getName(), _userMember.getDepartment(), 
                    _point.getReason(), _point.getDate_receive(), _point.getDate(), _point.getPlusPoint(), _point.getMinusPoint());
                    return new ResponseEntity<PointResponse>(response, HttpStatus.CREATED);
                } else {
                    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity<HttpStatus> deletePointById(@PathVariable(name="id") Long id) {
        try {
            Optional<Point> pointData = pointRepository.findById(id);

            if(pointData.isPresent()) {
                Point point = pointData.get();
                UserMember _userMember = point.getUserMember();

                _userMember.setPlusPoint(_userMember.getPlusPoint() - point.getPlusPoint());
                _userMember.setMinusPoint(_userMember.getMinusPoint() + point.getMinusPoint());
                userMemberRepository.save(_userMember);

                pointRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteAllPoints() {
        try {
            List<Point> points = pointRepository.findAll();
            if(points.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                for(Point point : points) {
                    UserMember _userMember = point.getUserMember();

                    _userMember.setPlusPoint(_userMember.getPlusPoint() - point.getPlusPoint());
                    _userMember.setMinusPoint(_userMember.getMinusPoint() + point.getMinusPoint());
                    userMemberRepository.save(_userMember);
                }
                pointRepository.deleteAll();
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
