package util;

import impl.ResRepositoryImpl;

public class Factory {
    public static ResRepositoryImpl resRepository = null;
    private static Factory instance = null;
    public static synchronized Factory getInstance(){
        if (instance == null){
            instance = new Factory();
        }
        return instance;
    }

    public ResRepositoryImpl getResRepository(){
        if (resRepository == null){
            resRepository = new ResRepositoryImpl();
        }
        return resRepository;
    }
}
