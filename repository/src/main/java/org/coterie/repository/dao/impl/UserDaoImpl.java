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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Mapper mapper;

    @Override
    public UserPojo create(UserPojo userPojo) {
        UserPo userPo = mapper.map(userPojo, UserPo.class);
        userRepository.saveAndFlush(userPo);
        return mapper.map(userPo, UserPojo.class);
    }

    @Override
    public UserPojo update(UserPojo userPojo) {
        UserPo userPo = userRepository.findOne(userPojo.getId());
        userPo.setActivated(userPojo.isActivated());
        userPo.setAvatar(userPojo.getAvatar());
        userPo.setDescription(userPojo.getDescription());
        userPo.setEmail(userPojo.getEmail());
        userPo.setUsername(userPojo.getUsername());
        userPo.setPassword(userPojo.getPassword());
        userPo.setEnabled(userPojo.isEnabled());
        userPo.setIsAdmin(userPojo.isAdmin());
        userRepository.saveAndFlush(userPo);
        return mapper.map(userPo, UserPojo.class);
    }
}
