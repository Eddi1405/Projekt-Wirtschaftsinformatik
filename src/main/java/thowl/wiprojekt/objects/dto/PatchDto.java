package thowl.wiprojekt.objects.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
/**
 * Author Florian Laufer
 */
@AllArgsConstructor
@Getter
public class PatchDto {
    String op;

    String key;

    String value;
}
