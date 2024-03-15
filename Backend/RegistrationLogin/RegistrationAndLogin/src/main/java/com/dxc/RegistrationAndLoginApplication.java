package com.dxc;
 
import com.dxc.model.Role;
import com.dxc.model.User;
import com.dxc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
 
@SpringBootApplication
public class RegistrationAndLoginApplication implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;
 
    public static void main(String[] args) {
        SpringApplication.run(RegistrationAndLoginApplication.class, args);
    }
 
//	@Override
//	public void run(String... args) throws Exception {
//		// TODO Auto-generated method stub
//		
//	}
 
    @Override
    public void run(String... args) throws Exception {
        User adminAccount = userRepository.findByRole(Role.ADMIN);
        if (adminAccount == null) {
            User user = new User();
            user.setUseremail("connected@gmail.com");
            user.setUsername("ConnectED");
            user.setUserpassword("connected11");
            user.setRole(Role.ADMIN);
            userRepository.save(user);
        }
    }
}