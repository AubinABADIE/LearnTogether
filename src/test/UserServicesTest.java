package test;

import client.Users.UserServices;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

public class UserServicesTest {

	@Test
	public final void encryptPwdTest() {
		UserServices userService = new UserServices();
		String res = "f4f263e439cf40925e6a412387a9472a6773c2580212a4fb50d224d3a817de17";
		
		assertTrue("Same passwords", userService.encryptPwd("mdp").equals(res));
		assertFalse("Different passwords", userService.encryptPwd("mdpDifferent").equals(res));
	}

}
