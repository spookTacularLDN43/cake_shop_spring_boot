package root.it.cupcake.model.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChangePassData {
    private String pass;
    private String newPass;
    private String repeatedNewPass;
}
