package com.apress.controller;

import com.apress.domain.Vote;
import com.apress.repository.VoteRepository;
import com.apress.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Optional;

@RestController
public class VoteController {
    @Autowired
    private VoteRepository voteRepository;
    @RequestMapping(value="/polls/{pollId}/votes", method= RequestMethod.POST)
    public ResponseEntity<?> createVote(@PathVariable Long pollId, @RequestBody Vote vote) {
        vote = voteRepository.save(vote);
// Set the headers for the newly created resource
        return VoteService.createPoll(vote);
    }

    @RequestMapping(value="/polls/{pollId}/votes", method=RequestMethod.GET)
    public Optional<Vote> getAllVotes(@PathVariable Long pollId) {
        return voteRepository. findById(pollId);
    }


}