package thowl.wiprojekt.objects;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum LearningType {

    /**
     * Role for students who learn visual
     */
    VISUAL ("visual"),
    /**
     * Role for students who learn aural
     */
    AURAL ("aural"),

    /**
     * Role for students who learn with read/write
     */
    READWRITE ("readwrite"),

    /**
     * Role for students who learn kinestehitc
     */
    KINESTHETIC ("kinesthetic");

    // The name of the learningtype as a String
    private String learningtype;

    private LearningType(String learningtype) { 
        learningtype = new String(learningtype);
    }
    @JsonValue
    public String getType() {
        return new String(learningtype);
    }
}
