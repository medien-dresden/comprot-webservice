package de.comprot

class UsernameAlreadyTakenException extends RuntimeException {

    UsernameAlreadyTakenException(String username, Throwable cause) {
        super("username '$username' already taken", cause)
    }

}
