/*
 * The MIT License (MIT)
 * 
 * Copyright (c) 2014 Bill Lv (Lv, Chao).
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package org.coterie.repository.dao.impl;

import org.coterie.repository.dao.UserDao;
import org.coterie.repository.po.UserPo;
import org.coterie.repository.pojo.UserPojo;
import org.coterie.repository.repository.UserRepository;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Title.
 * <p/>
 * Description.
 *
 * @author Bill Lv {@literal <billcc.lv@hotmail.com>}
 * @version 1.0
 * @since 2014-12-07
 */
@Repository
public class UserDaoImpl implements UserDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Mapper mapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserPojo create(UserPojo userPojo) {
        UserPo userPo = mapper.map(userPojo, UserPo.class);
        userPo.setPassword(passwordEncoder.encode(userPo.getPassword()));
        userRepository.saveAndFlush(userPo);
        UserPojo returnedUserPojo = mapper.map(userPo, UserPojo.class);
        returnedUserPojo.setPassword("");
        return returnedUserPojo;
    }

    @Override
    public UserPojo update(UserPojo userPojo) {
        UserPo userPo = userRepository.findOne(userPojo.getId());
        userPo.setActivated(userPojo.isActivated());
        userPo.setAvatar(userPojo.getAvatar());
        userPo.setDescription(userPojo.getDescription());
        userPo.setEmail(userPojo.getEmail());
        userPo.setPhone(userPojo.getPhone());
        userPo.setUsername(userPojo.getUsername());
        userPo.setPassword(passwordEncoder.encode(userPojo.getPassword()));
        userPo.setEnabled(userPojo.isEnabled());
        userPo.setAdmin(userPojo.isAdmin());
        userPo.setBirthday(userPojo.getBirthday());
        userPo.setFirstName(userPojo.getFirstName());
        userPo.setLastName(userPojo.getLastName());
        userPo.setGender(userPojo.getGender());
        userRepository.saveAndFlush(userPo);
        UserPojo returnedUserPojo = mapper.map(userPo, UserPojo.class);
        returnedUserPojo.setPassword("");
        return returnedUserPojo;
    }

    @Override
    public UserPojo getUserByName(String username) {
        List<UserPo> userPos = userRepository.findByUsername(username);
        if (userPos.size() == 0) {
            String errorMessage = String.format("There is no user name %s in db", username);
            LOGGER.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
        UserPojo userPojo = mapper.map(userPos.get(0), UserPojo.class);
        userPojo.setPassword("");
        return userPojo;
    }

}
