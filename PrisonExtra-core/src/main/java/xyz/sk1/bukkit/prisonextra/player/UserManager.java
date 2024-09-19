package xyz.sk1.bukkit.prisonextra.player;

import org.bukkit.entity.Player;
import xyz.sk1.bukkit.prisonextra.Core;
import xyz.sk1.bukkit.prisonextra.entity.fakeplayer.NPC;
import xyz.sk1.bukkit.prisonextra.entity.fakeplayer.factory.NPCFactory;
import xyz.sk1.bukkit.prisonextra.entity.fakeplayer.manager.FakePlayerManager;
import xyz.sk1.bukkit.prisonextra.entity.fakeplayer.manager.NPCManager;
import xyz.sk1.bukkit.prisonextra.manager.Manager;
import xyz.sk1.bukkit.prisonextra.manager.ManagerType;
import xyz.sk1.bukkit.prisonextra.entity.minion.Minion;
import xyz.sk1.bukkit.prisonextra.entity.minion.type.MinionType;
import xyz.sk1.bukkit.prisonextra.prisoner.PrisonManager;
import xyz.sk1.bukkit.prisonextra.prisoner.Prisoner;
import xyz.sk1.bukkit.prisonextra.utilities.tasks.PrisonTask;

import java.util.*;

/**
 * @author <a href="https://github.com/skylar11d">skylar</a>
 */

public class UserManager implements PrisonManager<Player, Prisoner, Minion> {

    private final Map<Prisoner, List<Minion>> prisoners;
    @SuppressWarnings("all")
    private final List<PrisonTask> prisonTasks;
    private NPCFactory npcFactory;

    public UserManager(){
        this.prisoners = new HashMap<>();
        this.prisonTasks = new ArrayList<>();
        this.npcFactory = new NPCFactory();
    }

    @Override
    public Map<Prisoner, List<Minion>> getPrisoners() {
        return this.prisoners;
    }

    @Override
    public User get(Player player) {
        return (User) getPrisoners().keySet().stream().filter(
                p -> p.getPlayer() == player).findFirst().orElse(null);
    }

    @SuppressWarnings("all")
    @Override
    public boolean checkPrisoner(Player player) {
        return getPrisoners().containsKey(player);
    }

    @Override
    public void imprison(Player player) {
        getPrisoners().put(new User() {
            @Override
            public Player getPlayer() {
                return player;
            }
        }, Collections.emptyList());
    }

    @SuppressWarnings("all")
    @Override
    public void release(Player player) {
        getPrisoners().remove(player);
    }

    @Override
    public List<Minion> getMinions(Player player) {
        return prisoners.get(player);
    }

    @Override
    public Optional<Minion> getMinion(Player player, MinionType type) {

        return Optional.of(prisoners.get(player).stream()
                .filter(m -> m.getType() == type)
                .findFirst().get());
    }

    @Override
    public void registerMinion(Minion minion, Player player) {
        prisoners.get(player).add(minion);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void load() {

        NPCManager npcManager = Core.getInstance().getFakeplayerManager();

        NPC npc = npcFactory.createNPC("Johnny Sins", "ewogICJ0aW1lc3RhbXAiIDogMTcyNjY4MDkwMjE5OCwKICAicHJvZmlsZUlkIiA6ICJiOWRjZjg1ODAyZmU0NzhjYTQ1YjVjNDFlNjZkMjQ1YSIsCiAgInByb2ZpbGVOYW1lIiA6ICJLYWk1MTIiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2Q4MDczNDg5OTgzYjAyNGEzMmY3YjhiNDkzMTkzY2ZlYzNmY2Y3OGM3NzcxZjU0OWM2NDdhNjM3YTQ0ZjFmNiIKICAgIH0sCiAgICAiQ0FQRSIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2Q5ZDgyYWIxN2ZkOTIwMjJkYmQ0YTg2Y2RlNGMzODJhNzU0MGUxMTdmYWU3YjlhMjg1MzY1ODUwNWE4MDYyNSIKICAgIH0KICB9Cn0",
                "q1+UQqB0qsSlREjR7/TzVijw7e+Xay1f5u+WMwHrDLV4oeod6/tNHRyhx+4c51jGAUWXR+0jKCMbRqIy/mcqqOeBTlRjoDDGgYH0v0laDp5MQ5DPQmToydp2m2wglBeBt4CXTSwyZkYbBek60LiFf3z3WGYYuffPDKLyK5owLhGhKvTlxa414d28SlCwjqxaFbqAZ9Z16BbTaZSP0lRQyUqLaV0YBQSUl4m74X/tHawqEqXYdyKdNKDd1Qucr2tP1vtYOO2ZiCExcQuOD1DYzQX3ucrrGRUfm24hVOenGZP0DHTBvqI7jGLjc40IP3JYuKCMVkZgY7S9CkRMdAFJzr056bV6MGSBUaTs46TR25DczJ+pIkv7hOxahVIlVQgCavr3OJVsGfRACR7ILdJctd4DBZHy21qpzPTd2DMnUD+uP8lT9MQEcJ3q3SXgtIAtJQX7umUU6UXS50rgY85DN08NjwsR8owRuCBeyz4zuTZUOfMsyaUkiTbkWuAn+O0QeTrgTjKTYUUMmQ4NOHifJ779Q5CFhlBRua7ogxl+dH0NXNDsj/YlDRRzk7dqIsmwcu/h7RTmkKTazfcslOfmYd/0DT386Qi9jNsNdQ5BbY80fli/fSrn1w9YMwnX/uQvcwZyj/INwV7qc2sGgWOkByPh63UvQYgA9I6gfJ7GYzI=",
                null, null);

        npcManager.syncNPC(npc, (User)getPrisoners().keySet());

    }

    @Override
    public ManagerType getType() {
        return ManagerType.NPC;
    }
}
