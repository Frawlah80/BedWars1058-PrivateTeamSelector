package com.andrei1058.bedwars.teamselector.teamselector;

import com.andrei1058.bedwars.api.arena.IArena;
import com.andrei1058.bedwars.api.arena.team.ITeam;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public class PlayerGroup implements Comparable<PlayerGroup>{

    private final ITeam preference;
    private final IArena arena;
    private final Set<Player> members = new LinkedHashSet<>();

    public PlayerGroup(IArena arena, ITeam team) {
        this.arena = arena;
        this.preference = team;
    }

    /**
     * Add a player to current group if limit was not reached.
     */
    public void addPlayer(Player player) {
        if (members.size() == arena.getMaxInTeam()) return;
        members.add(player);
    }

    @NotNull
    public Set<Player> getMembers() {
        // if there is no preference do not bother trying to team up 1 player since is alone
        return preference == null ? members.size() > 1 ? members : Collections.emptySet() : members;
    }

    @Nullable
    public ITeam getPreference() {
        return preference;
    }


    @Override
    public int compareTo(@NotNull PlayerGroup otherGroup) {
        // Compare the groups by size
        return Integer.compare(getMembers().size(), otherGroup.getMembers().size());
    }

}
