package one.digitalinovation.personapi.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MessageResponseDTO {

    private Long id;
    private String message;
}
