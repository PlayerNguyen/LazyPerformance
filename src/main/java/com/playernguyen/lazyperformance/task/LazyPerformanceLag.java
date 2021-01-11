package com.playernguyen.lazyperformance.task;

import co.aikar.timings.Timings;
import com.playernguyen.lazyperformance.LazyPerformance;
import com.playernguyen.lazyperformance.configuration.LazyPerformanceConfigurationEntry;
import me.lucko.spark.bukkit.BukkitSparkPlugin;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class LazyPerformanceLag extends BukkitRunnable {

    private volatile double lastTPS = -1;
    private long lastTime = System.currentTimeMillis();
    private int i = 0;

    private final LazyPerformance lazyPerformance;

    public LazyPerformanceLag(LazyPerformance lazyPerformance) {
        this.lazyPerformance = lazyPerformance;
    }

    @Override
    public void run() {
        // Already for one second
        if (++i >= 20) {
//            System.out.println("lastTime = " + lastTime);
//            System.out.println("System.currentTimeMillis() = " + System.currentTimeMillis());
//            System.out.println("(System.currentTimeMillis() - lastTime) = " + (System.currentTimeMillis() - lastTime));
            // Get end point
            long currentTPms = System.currentTimeMillis() - lastTime;
            //System.out.println("currentTPms = " + currentTPms);
            double delayedTickPerSec = (double) currentTPms / 1000D;
            //System.out.println("delayedTickPerSec = " + delayedTickPerSec);

            lastTPS = 20.0D - delayedTickPerSec;
            //System.out.println("lastTPS = " + lastTPS);
            // Reset the start point
            lastTime = System.currentTimeMillis();
            // Reset the counter
            i = 0;
            // System.out.println(lastTPS);
        }
        // update();
    }

    private void update() {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (lastTPS != -1 &&
                        lastTPS <= (double) lazyPerformance.getConfiguration().get(LazyPerformanceConfigurationEntry.MINIMUM_TPS_TO_REPORT)) {
                    lazyPerformance.getLogger().warning(String.format("The tps is dropping down to %s", lastTPS));
                    // Generate report
                    Timings.generateReport(Bukkit.getConsoleSender());
                    BukkitSparkPlugin.getPlugin(BukkitSparkPlugin.class).createTickReporter();
                }
            }
        }.runTaskAsynchronously(lazyPerformance);
    }

    public double getLastTPS() {
        return lastTPS;
    }
}
