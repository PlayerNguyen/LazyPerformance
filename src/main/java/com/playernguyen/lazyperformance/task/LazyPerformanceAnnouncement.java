package com.playernguyen.lazyperformance.task;

import com.playernguyen.lazyperformance.LazyPerformance;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * The announcement class
 */
public class LazyPerformanceAnnouncement extends BukkitRunnable {
    private final LazyPerformance lazyPerformance;

    public LazyPerformanceAnnouncement(LazyPerformance lazyPerformance) {
        this.lazyPerformance = lazyPerformance;
    }

    @Override
    public void run() {
        lazyPerformance.getLogger().info(String.format(" Last tps: %s", this.lazyPerformance.getFormattedLastTPS()));
    }
}
