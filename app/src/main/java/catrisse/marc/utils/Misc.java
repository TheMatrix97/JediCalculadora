package catrisse.marc.utils;

import java.util.Comparator;

import catrisse.marc.calculadora.Puntuacion;

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
    public static class comparadorPuntuaciones implements Comparator<Puntuacion>{

        @Override
        public int compare(Puntuacion o1, Puntuacion o2) {
            Double score1 = o1.getScore();
            Double score2 = o2.getScore();
            if(score1 > score2){
                return -1;
            }else if(score1 < score2){
                return 1;
            }else return o1.getUsername().compareTo(o2.getUsername());
        }
    }

}
