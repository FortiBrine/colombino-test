package me.fortibrine.regions.api.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

@RequiredArgsConstructor
@Getter
public class RegionCrossEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();

    @NotNull private final Action action;
    @NotNull private final Location from;
    @NotNull private final Location to;
    @NotNull private final Player player;

    @Setter
    private boolean cancelled;

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public enum Action {
        JOIN,
        LEAVE
    }
}
