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
    private String message = null;

    public boolean isLogged() {
        return !(this.user == null);
    }

    public String getMessage(){
        String result = this.message;
        this.message = null;
        return result;
    }
}
