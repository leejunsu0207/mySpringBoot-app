package com.base.mySpring.service;

import com.base.mySpring.dto.UserDto;
import com.base.mySpring.entity.Account;
import com.base.mySpring.entity.User;
import com.base.mySpring.exception.ResourceNotFoundException;
import com.base.mySpring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

//    public UserService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

//    public UserDto insertUser(UserDto userDto){
//        User user = new User();
//        BeanUtils.copyProperties(userDto, user);
//        User savedUser = userRepository.save(user);
//
//        UserDto resultUserDto = new UserDto();
//        BeanUtils.copyProperties(savedUser, resultUserDto);
//        return resultUserDto;
//    }
        public Long insertUser(UserDto userDto){

            User user = new User();
            BeanUtils.copyProperties(userDto, user);
            User savedUser = userRepository.save(user);
            return savedUser.getId();

        }
        @Transactional(readOnly = true)
        public UserDto selectUserByEmail(@PathVariable String email){

            Optional<User> optionalUser = userRepository.findByEmail(email);
            User existUser = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User", "email", email));
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(existUser, userDto);
            return userDto;

        }

        @Transactional(readOnly = true)
        public List<UserDto> selectUsers(){

            List<User> userRepositoryAll = userRepository.findAll();
            List<UserDto> userDtoList = userRepositoryAll.stream()  // Stream<User>
                    .map(user -> modelMapper.map(user, UserDto.class)) // Stream<UserDto>
                    .collect(Collectors.toList());  // List<UserDto>
            return userDtoList;

        }

        public UserDto updateUser(Long id, UserDto userDto) {

            User user = userRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
            // setter 메서드를 호출 하면 update가 된다.
            // @Transactional 선언 되있어야만 setter로만 update가능
            user.setName(userDto.getName());
            user.setEmail(userDto.getEmail());

            UserDto existUserDto = modelMapper.map(user, UserDto.class);
            return existUserDto;

        }

        public ResponseEntity<?> deleteUser(Long id){

            Optional<User> optionalUser = userRepository.findById(id);

            if(!optionalUser.isPresent()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(id + " User Not Found");
            }

            User existUser = optionalUser.get();
            userRepository.delete(existUser);
            return ResponseEntity.status(HttpStatus.OK).body(id + "Delete Complete");
        }



}
