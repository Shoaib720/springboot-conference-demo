package ml.hadiya.conferencedemo.controllers;

import ml.hadiya.conferencedemo.models.Session;
import ml.hadiya.conferencedemo.models.Speaker;
import ml.hadiya.conferencedemo.repositories.SpeakerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/speakers")
public class SpeakerController {
    @Autowired
    private SpeakerRepository speakerRepository;
    @GetMapping
    public ResponseEntity<List<Speaker>> listSpeakers(){
        return ResponseEntity.ok(speakerRepository.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Speaker> getSpeakerById(@PathVariable Long id){
        Optional<Speaker> optionalSpeaker = speakerRepository.findById(id);
        if(optionalSpeaker.isPresent()){
            return ResponseEntity.ok(optionalSpeaker.get());
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public void createSpeaker(@RequestBody Speaker speaker){
        speakerRepository.saveAndFlush(speaker);
    }

    @DeleteMapping("{id}")
    public void deleteSpeakerById(@PathVariable Long id) {
        speakerRepository.deleteById(id);
    }

    @PutMapping("{id}")
    public ResponseEntity<Speaker> updateSpeakerById(@PathVariable Long id, @RequestBody Speaker speaker) {
        Optional<Speaker> optionalSpeaker = speakerRepository.findById(id);
        if(optionalSpeaker.isEmpty()) {return ResponseEntity.notFound().build();}
        Speaker existingSpeaker = optionalSpeaker.get();
        BeanUtils.copyProperties(speaker, existingSpeaker, "speaker_id");
        return ResponseEntity.ok(speakerRepository.saveAndFlush(existingSpeaker));
    }

}
