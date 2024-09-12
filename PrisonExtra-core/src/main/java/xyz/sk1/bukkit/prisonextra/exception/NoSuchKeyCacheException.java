package xyz.sk1.bukkit.prisonextra.exception;

public class NoSuchKeyCacheException extends RuntimeException {
    public NoSuchKeyCacheException(String element) {
        super("Key " + element + " doesn't exist in the local cache");
    }
}
