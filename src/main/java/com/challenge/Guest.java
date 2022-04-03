package com.challenge;

public class Guest extends User {
	
	@Override
	public String getRole() {
		
		return "Guest";
	}
	
	@Override
	public String Register() {
		
		if(! this.checkPassword(this.password) || this.password.length() < 5) {
			
			return "Password rule not met";
			
		}
		
		if(!VolatileDB.database.containsKey(this.getUsername())) {
			
			
			
			String passwordEncrypted= "";
			
			for(int i =this.getPassword().length() ; i > 0 ; i--) {
				
				passwordEncrypted += this.getPassword().substring(i-1, i);
			}
			
			passwordEncrypted += this.getAgeString();
			passwordEncrypted = "**" + passwordEncrypted + "**";
			
			this.setPassword(passwordEncrypted);
			
			VolatileDB.insert(this.getUsername(), this);
			
			return "User Registered" ;
		}
		return "User already exist";
	}
	
	@Override
	public Boolean Login( String username ,String password ) {
		
		if(VolatileDB.database.containsKey(username)) {
			
			User user = VolatileDB.fetch(username);
			
			String passwordEncrypted = user.getPassword();
			
			passwordEncrypted = passwordEncrypted.substring(2,passwordEncrypted.length()-5);
			
			String passwordDecrypted = "";
			
			for(int i = passwordEncrypted.length() ; i > 0 ; i--) {
				
				passwordDecrypted += passwordEncrypted.substring(i-1, i);
			}
			
			if (passwordDecrypted.equals(password)) {
				
				return true;
				
			}
			
		}
		return false;
	}

}
