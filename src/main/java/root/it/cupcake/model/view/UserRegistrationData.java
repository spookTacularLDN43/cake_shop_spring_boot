package root.it.cupcake.model.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationData {
    private String name;
    private String surname;
    private String login;
    private String password;
    private String repeatedPass;
}
