package ml.hadiya.conferencedemo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcType;
import org.hibernate.annotations.Type;

import java.util.List;

@Entity(name = "speakers")
public class Speaker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long speaker_id;
    private String first_name;
    private String last_name;
    private String title;
    private String company;
    private String speaker_bio;
    @Lob
    private byte[] speaker_photo;

    // Mapped to field "speakers" defined in Session class
    /*
    * We are using @JsonIgnore annotation by jackson to avoid cyclic infinite recursion.
    * By that i mean, the sessions will list the speakers, each speaker will again list all sessions,
    * each session in that will again list the speakers and so on..
    *
    * We will use this annotation here in speakers as session side many to many must be dominant and recursion
    * must stop here.
    * */
    @ManyToMany(mappedBy = "speakers")
    @JsonIgnore
    private List<Session> sessions;

    public Speaker() {
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public Long getSpeaker_id() {
        return speaker_id;
    }

    public void setSpeaker_id(Long speaker_id) {
        this.speaker_id = speaker_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getSpeaker_bio() {
        return speaker_bio;
    }

    public void setSpeaker_bio(String speaker_bio) {
        this.speaker_bio = speaker_bio;
    }
}
