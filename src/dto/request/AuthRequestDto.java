package dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AuthRequestDto {
    private String id;
    private String password;

    public AuthRequestDto(String id, String password) {
        this.id = id;
        this.password = password;
    }
}
