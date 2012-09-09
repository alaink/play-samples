import org.junit.*;
import java.util.*;
import play.test.*;
import models.*;

public class BasicTest extends UnitTest {

    @Test
    public void createAndRetrieveUser() {
    	// create a new user and save it
    	new User("bob@gmail.com", "secret", "Bob").save();
    	
    	// retrieve the user with email address bob@gmail.com
    	User bob = User.find("byEmailAndPassword", "bob.gmail.com", "secret").first();
    	System.out.println("user: " + bob);
    	
    	// Test
    	assertNotNull(bob);
    	assertEquals("Bob", bob.fullName);
    }
    
    @Test
    public void tryConnectAsUser() {
    	// create a new user and save it
    	User bob = new User("bob@gmail.com", "secret", "Bob").save();
    	System.out.println("user connected: " + bob.fullName);
    	// Test
    	assertNotNull(User.connect("bob@gmail.com", "secret"));
    	assertNull(User.connect("bob@gmail.com", "badpassword"));
    	assertNull(User.connect("tom@gmail.com", "secret"));
    }

}
