package xyz.sk1.bukkit.prisonextra.exception;

public class KeyExistsCacheException extends RuntimeException {

    public KeyExistsCacheException(String element){
        super("The key " + element + " Already exists in the local cache");
    }

}
