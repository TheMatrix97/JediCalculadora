package catrisse.marc.utils;

public abstract class Misc {
    public static class UserExitsException extends Exception{
        public UserExitsException() {
            super();
        }
    }

    public static class RegisterException extends Exception{
        public RegisterException() {
            super();
        }
    }
    public static class GameFinalizado extends Exception{
        public GameFinalizado() {
            super();
        }
    }
    public static class UserNotFound extends Exception{
        public UserNotFound() {
            super();
        }
    }

}
