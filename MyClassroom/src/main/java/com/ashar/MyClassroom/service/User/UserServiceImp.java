package com.ashar.MyClassroom.service.User;

import java.sql.SQLException;
import java.util.Map;

import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashar.MyClassroom.config.JasyptEncryptorConfig;
import com.ashar.MyClassroom.repository.User.UserRepository;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	private UserRepository UserRepo;

	@Override
	public boolean RegisterUser(Map<String, String> obj) throws SQLException {
		
		String passwordEncrpyt = obj.get("user_password");
		passwordEncrpyt = EncrptKey(passwordEncrpyt);
//		System.out.println("RegisterUser: " + passwordEncrpyt);	
		
		return UserRepo.RegisterUser(obj.get("username"), obj.get("first_name"), obj.get("last_name"),
				passwordEncrpyt, obj.get("email"), obj.get("phone_no"), obj.get("gender")
				,obj.get("age")  );
	}



	@Override
	public boolean DeleteUser(Map<String, String> obj) throws SQLException {
         return UserRepo.DeleteUser(obj.get("username") );
	}

	@Override
	public boolean ValidateUser(Map<String, String> obj) throws SQLException {
		
//		
//		System.out.println(obj.toString());
//		return false;

		String passwordEnrpyt = getThePasswordFromApi(obj.get("username"));
		String passwordDecrpyt = null;
		if(passwordEnrpyt== null || passwordEnrpyt.length()<=15) 
		{
			return UserRepo.ValidateUser(obj.get("username"), obj.get("password") );	
		}

		else {
			
			System.out.println("passwordEnrpyt: " + passwordEnrpyt);
			if(passwordEnrpyt!=null) 
				  passwordDecrpyt = DecrptKey(passwordEnrpyt);	
			
			
			System.out.println("passwordDecrpt: " + passwordDecrpyt);
			if(passwordDecrpyt!=null && passwordDecrpyt.equals( obj.get("password"))) {	
				System.out.println("equals");
				return UserRepo.ValidateUser(obj.get("username"), passwordEnrpyt );
			}
			else {
				System.out.println("not equals");
				return UserRepo.ValidateUser(obj.get("username"), obj.get("password") );
			}
		}
	}
	
	
    
	private String getThePasswordFromApi(String username) throws SQLException {
		return UserRepo.getThePasswordFromApi(username);
	}



	
	private String EncrptKey(String passwordEncrpyt) {
		if(passwordEncrpyt != null) {
			StringEncryptor encryptor = JasyptEncryptorConfig.passwordEncryptor();
			return encryptor.encrypt(passwordEncrpyt);
		}
		return null;
	}

	
	private String DecrptKey(String EncrptString) {
		if(EncrptString != null) {			
			StringEncryptor encryptor = JasyptEncryptorConfig.passwordEncryptor();
			return encryptor.decrypt(EncrptString);
		}
		return null;
	}
	
	
	
}