package com.apress.controller;

import com.apress.domain.Vote;
import com.apress.dto.VoteResult;
import com.apress.repository.VoteRepository;
import com.apress.service.ComputeResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ComputeResultController {

    @Autowired
    private ComputeResultService computeResultService;

    @RequestMapping(value="/computeResult/{pollId}", method= RequestMethod.GET)
    public ResponseEntity<?> computeResult(@PathVariable Long pollId) {
      VoteResult voteResult = computeResultService.computeAllVotes(pollId);
        return new ResponseEntity<VoteResult>(voteResult, HttpStatus.OK);
    }



}
