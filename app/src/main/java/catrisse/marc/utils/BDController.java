package catrisse.marc.utils;

import android.content.Context;

import catrisse.marc.calculadora.User;
import io.realm.Realm;
import io.realm.RealmResults;

public class BDController {
    Realm realm;
    private static BDController INSTANCE = null;

    //SINGLETON pattern
    private BDController(Context c) {
        Realm.init(c);
        this.realm = Realm.getDefaultInstance();
    }

    public static BDController getInstance(Context c) {
        if(INSTANCE == null){
            INSTANCE = new BDController(c);
        }
        return INSTANCE;
    }

    public boolean registrarUserNuevo(String userName, String s, String nom, String surname) throws Misc.UserExitsException, Misc.RegisterException {
        RealmResults<User> res = realm.where(User.class).equalTo("username",userName).findAll();
        if(res.size() == 0){
            final User user = new User(userName,nom,surname,s);
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.copyToRealm(user);
                }
            });
            //check que se ha guardado correctamente (se puede hacer mejor??)
            res = realm.where(User.class).equalTo("username",userName)
                    .and()
                    .equalTo("pass",s).findAll();
            if(res.size() == 0){
                throw new Misc.RegisterException();
            }
            return res.size() == 1;

        }else {
            throw new Misc.UserExitsException();
        }
    }

    public User estaRegistrado(User aux) {
        String nom = aux.getUsername();
        String passw = aux.getPass();
        RealmResults<User> result = realm.where(User.class).equalTo("username",nom) //buscamos user con mismo nombre y pass
                .and()
                .equalTo("pass",passw).findAll();
        if(result.size() == 1) return realm.copyFromRealm(result.get(0));
        else return null;
    }

    public User load_user(String username) throws Misc.UserNotFound {
        User user;
        RealmResults<User> result = realm.where(User.class).equalTo("username",username) //buscamos user con mismo nombre y pass
                .findAll();
        if(result.size() == 1) return realm.copyFromRealm(result.get(0));
        else throw new Misc.UserNotFound(); //throw exception...
    }
}
