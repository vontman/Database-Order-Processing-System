package controller;

import java.time.Year;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    public static final int MIN_PASSWORD_LEN = 3, MIN_PHONE_LEN = 4;

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile(
            "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
            Pattern.CASE_INSENSITIVE);

    @SuppressWarnings("serial")
    public static class ValidationException extends RuntimeException {
        public ValidationException(String s) {
            super(s);
        }
    }

    public static boolean isUserNameValid(String userName)
            throws ValidationException {
        try {
            validateUserName(userName);
            return true;
        } catch (ValidationException e) {
            return false;
        }
    }

    public static boolean isPasswordValid(String password)
            throws ValidationException {
        try {
            validatePassword(password);
            return true;
        } catch (ValidationException e) {
            return false;
        }
    }

    public static void validateUserName(String userName)
            throws ValidationException {
        if (userName == null || userName.isEmpty())
            throw new ValidationException("Username cannot be empty!");
        for (int i = 0; i < userName.length(); i++) {
            if (!Character.isAlphabetic(userName.charAt(i))
                    && !Character.isDigit(userName.charAt(i)))
                throw new ValidationException(
                        "UserName must contain alphabetical and numerical characters only!");
        }
    }

    public static void validatePassword(String password)
            throws ValidationException {
        if (password == null || password.length() < MIN_PASSWORD_LEN)
            throw new ValidationException("Password must be at least "
                    + MIN_PASSWORD_LEN + " characters!");
        for (int i = 0; i < password.length(); i++) {
            if (Character.isWhitespace(password.charAt(i)))
                throw new ValidationException(
                        "Password cannot contain spaces!");
        }
    }

    public static void validateEmail(String email) throws ValidationException {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        if (!matcher.find())
            throw new ValidationException("Must provide a valide email!");
    }

    public static void validateYear(String year) throws ValidationException {
        try {
            if (year != null && !year.isEmpty())
                validateYear(Integer.parseInt(year));
        } catch (NumberFormatException e) {
            throw new ValidationException("Year must be numeric!");
        }
    }

    public static void validateYear(int year) throws ValidationException {
        if (year < 1 || year > Year.now().getValue())
            throw new ValidationException("Not a valid year!");
    }

    public static void validatePhone(String phone) {
        if (phone != null && !phone.isEmpty()) {
            for (int i = 0; i < phone.length(); i++) {
                if (!Character.isDigit(phone.charAt(i)))
                    throw new ValidationException("Phone must be numeric!");
            }
            if (phone.length() < MIN_PHONE_LEN)
                throw new ValidationException("Phone must be at least "
                        + MIN_PHONE_LEN + " numbers!");
        }
    }
    
    public static void validateName(String name) {
        if (name != null && !name.isEmpty()) {
            for (int i = 0; i < name.length(); i++) {
                if (!Character.isAlphabetic(name.charAt(i)))
                    throw new ValidationException("Name must be alphabetic");
            }
        }
    }
}
