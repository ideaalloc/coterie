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

import org.coterie.service.bo.CommentBo;
import org.coterie.service.bo.TopicBo;
import org.coterie.service.bo.UserBo;
import org.coterie.service.bo.VoteBo;
import org.coterie.service.service.CommentService;
import org.coterie.service.service.TopicService;
import org.coterie.service.service.UserService;
import org.coterie.service.service.VoteService;
import org.coterie.web.aspects.NotifyClients;
import org.coterie.web.vo.CommentVo;
import org.coterie.web.vo.TopicVo;
import org.coterie.web.vo.VoteVo;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

/**
 * Topic Controller.
 * <p/>
 * Description.
 *
 * @author Bill Lv {@literal <billcc.lv@hotmail.com>}
 * @version 1.0
 * @since 2014-12-10
 */
@Controller
@RequestMapping("/")
public class TopicController {
    @Autowired
    private TopicService topicService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private VoteService voteService;

    @Autowired
    private UserService userService;

    @Autowired
    private Mapper mapper;

    @RequestMapping(value = "/topics/{id}/index", method = RequestMethod.GET)
    public String index(@PathVariable long id, Model model, Principal principal) {
        model.addAttribute("topicId", id);
        UserBo userBo;
        if (principal == null) {
            userBo = new UserBo();
        } else {
            userBo = userService.getUserByName(principal.getName());
        }
        model.addAttribute("userId", userBo.getId());
        return "topic/index";
    }

    @RequestMapping(value = "/topics/{id}", method = RequestMethod.GET)
    @ResponseBody
    public TopicVo getTopic(@PathVariable long id) {
        TopicBo topicBo = topicService.getTopic(id);
        return mapper.map(topicBo, TopicVo.class);
    }

    @RequestMapping(value = "/topics/{topicId}/comments", method = RequestMethod.GET)
    @ResponseBody
    public List<CommentVo> getComments(@PathVariable long topicId) {
        List<CommentBo> commentBos = commentService.getComments(topicId);
        List<CommentVo> commentVos = new ArrayList<>(commentBos.size());
        for (CommentBo commentBo : commentBos) {
            commentVos.add(mapper.map(commentBo, CommentVo.class));
        }
        return commentVos;
    }

    @RequestMapping(value = "/topics/{topicId}/votes", method = RequestMethod.GET)
    @ResponseBody
    public List<VoteVo> getVotes(@PathVariable long topicId) {
        List<VoteBo> voteBos = voteService.getVotes(topicId);
        List<VoteVo> voteVos = new ArrayList<>(voteBos.size());
        for (VoteBo voteBo : voteBos) {
            voteVos.add(mapper.map(voteBo, VoteVo.class));
        }
        return voteVos;
    }

    @NotifyClients
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    @ResponseBody
    public CommentVo comment(@RequestBody CommentVo commentVo) {
        CommentBo commentBo = commentService.addComment(mapper.map(commentVo, CommentBo.class));
        return mapper.map(commentBo, CommentVo.class);
    }

    @NotifyClients
    @RequestMapping(value = "/vote", method = RequestMethod.POST)
    @ResponseBody
    public VoteVo vote(@RequestBody VoteVo voteVo) {
        VoteBo voteBo = voteService.addVote(mapper.map(voteVo, VoteBo.class));
        return mapper.map(voteBo, VoteVo.class);
    }
}
