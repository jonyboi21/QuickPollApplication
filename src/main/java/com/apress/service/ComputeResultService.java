package com.apress.service;

import com.apress.domain.Vote;
import com.apress.dto.OptionCount;
import com.apress.dto.VoteResult;
import com.apress.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ComputeResultService {

    @Autowired
    private VoteRepository voteRepository;

    public VoteResult computeAllVotes(Long pollId){
        Iterable<Vote> allVotes = voteRepository.findByPoll(pollId);
        // Algorithm to count votes
        VoteResult voteResult = new VoteResult();
        int totalVotes = 0;
        Map<Long, OptionCount> tempMap = new HashMap<Long, OptionCount>();
        for(Vote v : allVotes) {
            totalVotes ++;
// Get the OptionCount corresponding to this Option
            OptionCount optionCount = tempMap.get(v.getOption().getId());
            if(optionCount == null) {
                optionCount = new OptionCount();
                optionCount.setOptionId(v.getOption().getId());
                tempMap.put(v.getOption().getId(), optionCount);
            }
            optionCount.setCount(optionCount.getCount()+1);
        }
        voteResult.setTotalVotes(totalVotes);
        voteResult.setResults(tempMap.values());
        return voteResult;
    }

}
