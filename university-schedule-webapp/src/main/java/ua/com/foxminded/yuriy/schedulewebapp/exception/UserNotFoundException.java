package ua.com.foxminded.yuriy.schedulewebapp.exception;

public class UserNotFoundException extends RuntimeException {
	public UserNotFoundException(String message) {
		super(message);
	}
}
