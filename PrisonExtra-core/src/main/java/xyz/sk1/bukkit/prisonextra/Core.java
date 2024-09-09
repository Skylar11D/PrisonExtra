package xyz.sk1.bukkit.prisonextra;

public class Core extends Plugin {

    private static volatile Core instance;

    @Override
    public void init() {

        instance = this;

    }

    @Override
    public void fini() {

    }

    public static Core getInstance(){

        if(instance != null){
            return instance;
        }

        synchronized (Core.class) {
            if(instance == null){
                instance = new Core();
            }
        }

        return instance;
    }

}
