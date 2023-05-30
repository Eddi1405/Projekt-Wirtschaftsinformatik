package thowl.wiprojekt.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thowl.wiprojekt.entity.Comment;
import thowl.wiprojekt.entity.Thread;
import thowl.wiprojekt.repository.TagRepository;
import thowl.wiprojekt.repository.ThreadRepository;

import java.util.List;


@Slf4j
@Service
public class ThreadService {

    @Autowired
    private ThreadRepository threadRepository;


    public Thread createThread(Thread thread) {
        return threadRepository.save(thread);
    }

    public Thread getThread(Long id) {
        return threadRepository.findById(id).orElse(null);
    }

    public Thread updateThread(Long id, Thread thread) {
        Thread existingThread = threadRepository.findById(id).orElse(null);
        if (existingThread != null) {
            existingThread.setHeader(thread.getHeader());
            existingThread.setContent(thread.getContent());
            // Update other properties as needed
            return threadRepository.save(existingThread);
        }
        return null;
    }

    public void deleteThread(Long id) {
        threadRepository.deleteById(id);
    }

    public List<Thread> getAllThreads() {
        return threadRepository.findAll();
    }

}
