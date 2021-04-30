package com.apress.service;

import com.apress.domain.Poll;
import com.apress.exception.ResourceNotFoundException;
import com.apress.repository.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PollService {

    @Autowired
    private PollRepository pollRepository;


    public void createPoll(Poll poll) {
        pollRepository.save(poll);

    }

    public Iterable<Poll> getAllPolls() {
        return pollRepository.findAll();

        }


    public  Optional<Poll> getPollById(Long id){
        verifyPoll(id);
        Optional<Poll> p = pollRepository.findById(id);
        return p;
    }
    public void deletePoll(Long id){
        verifyPoll(id);
        pollRepository.deleteById(id);
    }
    public void updatePoll(Long id, Poll p) {
        verifyPoll(id);
        p = pollRepository.save(p);

    }
    public Poll verifyPoll(Long pollId) throws ResourceNotFoundException {
        Optional<Poll> poll = pollRepository.findById(pollId);
        if(poll == null) {
            throw new ResourceNotFoundException("Poll with id " + pollId + " not found");
        }
        return null;
    }
}