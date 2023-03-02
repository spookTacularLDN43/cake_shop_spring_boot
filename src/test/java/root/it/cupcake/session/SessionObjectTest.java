package root.it.cupcake.session;

import org.junit.Assert;
import org.junit.Test;
import root.it.cupcake.model.User;

public class SessionObjectTest {

    @Test
    public void isLoggedUserTest() {
        SessionObject sessionObject = new SessionObject();
        User user = new User(1, "abc", "def", "ghi", "jkl");
        boolean notLoggedResult = sessionObject.isLogged();
        sessionObject.setUser(user);
        boolean loggedResult = sessionObject.isLogged();
        Assert.assertFalse(notLoggedResult);
        Assert.assertTrue(loggedResult);
    }

    @Test
    public void getMessageTest(){
        SessionObject sessionObject = new SessionObject();
        String message = "abc";
        String expectedResult = "abc";
        Assert.assertNull(sessionObject.getMessage());
        sessionObject.setMessage(message);
        Assert.assertEquals(expectedResult, sessionObject.getMessage());
        Assert.assertNull(sessionObject.getMessage());
    }
}
