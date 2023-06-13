package thowl.wiprojekt.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thowl.wiprojekt.entity.Comment;
import thowl.wiprojekt.entity.Thread;
import thowl.wiprojekt.entity.User;
import thowl.wiprojekt.errors.ThrowsInternal;
import thowl.wiprojekt.service.ThreadService;

import java.util.List;
import java.util.Map;


@Slf4j
@RestController
@ThrowsInternal
@RequestMapping("/threads")
public class ThreadController {

    @Autowired
    ThreadService TS;

    public ThreadController(ThreadService ts) {
        this.TS = ts;
    }


    @PostMapping("/create")
    public ResponseEntity<Thread> createThread(@RequestBody Thread thread) {
        Thread createdThread = TS.createThread(thread);
        return new ResponseEntity<>(createdThread, HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Thread> getThread(@PathVariable("id") Long id) {
        Thread thread = TS.getThread(id);
        return new ResponseEntity<>(thread, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Thread> updateThread(@PathVariable("id") Long id, @RequestBody Thread thread) {
        Thread updatedThread = TS.updateThread(id, thread);
        return new ResponseEntity<>(updatedThread, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteThread(@PathVariable("id") Long id) {
        TS.deleteThread(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<Thread>> getAllThreads() {
        List<Thread> threads = TS.getAllThreads();
        return new ResponseEntity<>(threads, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public Thread updateThreadFields(@PathVariable long id, @RequestBody Map<String,Object> fields){
        return TS.updateThreadsByFields(id,fields);
    }
}
