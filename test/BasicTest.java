import org.junit.*;
import java.util.*;
import play.test.*;
import models.*;

public class BasicTest extends UnitTest {

	@Before
	public void setup() {
		Fixtures.deleteDatabase();
	}
	
    @Test
    public void createAndRetrieveUser() {
    	// create a new user and save it
    	new User("bob@gmail.com", "secret", "Bob").save();
    	
    	// retrieve the user with email address bob@gmail.com
    	User bob = User.find("byEmail", "bob@gmail.com").first();
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
    
    @Test
    public void createPost() {
    	// create a new user and save it
    	User bob = new User("bob@gmail.com", "secret", "Bob").save();
    	// create new post
    	new Post(bob, "My First Post", "Hello World!").save();
    	
    	// Test that the post has been created
    	assertEquals(1, Post.count());
    	
    	// Retrieve all posts created by Bob
    	List<Post> bobPosts = Post.find("byAuthor", bob).fetch();
    	System.out.println("posts: " + bobPosts.get(0).content);
    	
    	// Tests
    	assertEquals(1, bobPosts.size());
    	Post firstPost = bobPosts.get(0);
    	assertNotNull(firstPost);
    	assertEquals(bob, firstPost.author);
    	assertEquals("My First Post", firstPost.title);
    	assertEquals("Hello World!", firstPost.content);
    	assertNotNull(firstPost.postedAt);
    }

}
