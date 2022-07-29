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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

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

}
