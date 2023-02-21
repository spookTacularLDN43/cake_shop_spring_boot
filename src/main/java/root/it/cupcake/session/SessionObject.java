package root.it.cupcake.session;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import root.it.cupcake.model.User;

@Component
@SessionScope
@Setter
@Getter
public class SessionObject {
    private User user = null;

    public boolean isLogged(){
        return !(this.user == null);
    }
}
