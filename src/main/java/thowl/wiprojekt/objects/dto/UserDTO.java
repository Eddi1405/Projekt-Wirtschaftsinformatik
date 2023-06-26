package thowl.wiprojekt.objects.dto;

import thowl.wiprojekt.objects.LearningType;
import thowl.wiprojekt.objects.Role;

public record UserDTO (
        long id,
        String username,
        String email,

        Role role,

        LearningType learningType,

        boolean darkmode,

        boolean deEn,

        boolean contrast,

        int frontsize,

        boolean colorBlindness,

        boolean eyeTracking,

        boolean singLanguage


){

}
