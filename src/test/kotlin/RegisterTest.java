import com.project.evernote.EvernoteApplication;
import com.project.evernote.model.User;
import com.project.evernote.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={EvernoteApplication.class})
public class RegisterTest{

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder encoder;

    @Test
    public void registerTest() {
        //arrange
        String email = "jane.doe@mailinator.com";
        String username = "Jane Doe";
        String password = "123123";
        User user =  new User();
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(password);

        //act
        userService.save(user);
        User newUser = userService.findByEmail(email);

        //assert
        Assert.assertNotNull(newUser);
        Assert.assertEquals(username, newUser.getUsername());
        Assert.assertEquals(email, newUser.getEmail());
        Assert.assertEquals(true, encoder.matches(password,newUser.getPassword()));
    }
}