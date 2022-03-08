package edu.happydev.service;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.happydev.entity.Users;
import edu.happydev.repository.UsersRepository;

@Service
public class UsersService {

    @Autowired
    UsersRepository usersRepository;

    public String validateEmailAndPwd(String email, String pwd) {
        Optional<Users> user = usersRepository.findByEmail(email);

        if (user.isPresent()) {
            if (!validateBcryptPassword(pwd, user.get().getPwdHash()))
                return null;

            return (user.get().getFirstName() + " " + user.get().getLastName()).trim();
        } else {
            return null;
        }
    }

    public String createNewUser(String email, String pwd, String firstName, String lastName, String bio) {
        Users user = new Users(email, firstName, lastName, bio, pwd);

        if (pwd == null)
            return "Invalid Password";

        // needs to be placed in constants file
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(pwd);
        boolean isPwdValid = matcher.matches();

        if (!isPwdValid) {
            return "Password not following the criteria";
        }

        Optional<Users> checkUser = usersRepository.findById(email);
        if (checkUser.isPresent())
            return "Email already exists!";

        user.setPwdHash(hashPasswordGenerator(pwd));
        try {
            usersRepository.save(user);
        } catch (Exception ex) {
            return "Unable to process your request";
        }
        return "User created successfully";
    }

    public String hashPasswordGenerator(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(10));
    }

    public boolean validateBcryptPassword(String pwd, String hashedPwd) {
        return BCrypt.checkpw(pwd, hashedPwd);
    }

}
