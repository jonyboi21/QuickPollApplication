package com.apress.controller;

import com.apress.domain.Vote;
import com.apress.dto.VoteResult;
import com.apress.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class ComputeResultController {

    @Autowired
    private VoteRepository voteRepository;
    @RequestMapping(value="/computeResult", method= RequestMethod.GET)
    public ResponseEntity<?> computeResult(@RequestParam Long pollId) {
        VoteResult voteResult = new VoteResult();
        Optional<Vote> allVotes = voteRepository.findById(pollId);
// Algorithm to count votes
        return new ResponseEntity<VoteResult>(voteResult, HttpStatus.OK);
    }


}
