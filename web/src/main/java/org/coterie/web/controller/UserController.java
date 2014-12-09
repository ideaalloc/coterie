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
package org.coterie.web.controller;

import org.coterie.service.bo.UserBo;
import org.coterie.service.service.UserService;
import org.coterie.web.vo.UserVo;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Title.
 * <p/>
 * Description.
 *
 * @author Bill Lv {@literal <billcc.lv@hotmail.com>}
 * @version 1.0
 * @since 2014-12-07
 */
@Controller
@RequestMapping("/")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private Mapper mapper;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerAdd(@ModelAttribute UserVo userVo, Model model) {
        UserBo userBo = mapper.map(userVo, UserBo.class);
        userBo.setActivated(false);
        userBo.setAdmin(false);
        userBo.setEnabled(true);
        userBo = userService.addUser(userBo);
        UserVo returnedUserVo = mapper.map(userBo, UserVo.class);
        model.addAttribute("user", returnedUserVo);
        return "auth/login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register() {
        return "user/register";
    }
}
