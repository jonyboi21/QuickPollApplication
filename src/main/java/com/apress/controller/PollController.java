package com.apress.controller;

import antlr.ASTNULLType;
import com.apress.domain.Poll;
import com.apress.exception.ResourceNotFoundException;
import com.apress.repository.PollRepository;
import com.apress.service.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.inject.Inject;
import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class PollController {

    @Autowired
    private PollService pollService;

    @RequestMapping(value="/polls", method=RequestMethod.GET)
    public ResponseEntity<Iterable<Poll>> getAllPolls() {
        Iterable<Poll> p = pollService.getAllPolls();
        if(p == null) {
            throw new ResourceNotFoundException("Polls not found");
        }
        return new ResponseEntity<>(p,HttpStatus.OK);
    }


    @RequestMapping(value="/polls", method=RequestMethod.POST)
    public ResponseEntity<?> createPoll(@Valid @RequestBody Poll poll) {

        pollService.createPoll(poll);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newPollUri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(poll.getId())
                .toUri();
        responseHeaders.setLocation(newPollUri);

// Set the location header for the newly created resource
        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(value="/polls/{pollId}", method=RequestMethod.GET)
    public ResponseEntity<?> getPoll(@PathVariable Long pollId) {
        Optional<Poll> p = pollService.getPollById(pollId);
        if(p == null) {
            throw new ResourceNotFoundException("Poll with id " + pollId + " not found");
        }
        return new ResponseEntity<> (p, HttpStatus.OK);
    }

    @RequestMapping(value="/polls/{pollId}",method=RequestMethod.PUT)
    public ResponseEntity<?> updatePoll(@RequestBody Poll poll, @PathVariable Long pollId) {
        pollService.updatePoll(pollId,poll);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value="/polls/{pollId}", method=RequestMethod.DELETE)
    public ResponseEntity<?> deletePoll(@PathVariable Long pollId) {
        pollService.deletePoll(pollId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
