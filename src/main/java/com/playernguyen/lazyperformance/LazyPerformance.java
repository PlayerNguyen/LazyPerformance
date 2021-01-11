package com.playernguyen.lazyperformance;

import com.playernguyen.lazyperformance.configuration.LazyPerformanceConfiguration;
import com.playernguyen.lazyperformance.configuration.LazyPerformanceConfigurationEntry;
import com.playernguyen.lazyperformance.task.LazyPerformanceAnnouncement;
import com.playernguyen.lazyperformance.task.LazyPerformanceLag;
import me.lucko.spark.bukkit.BukkitSparkPlugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Lazy Performance class
 */
public final class LazyPerformance extends JavaPlugin {
    private LazyPerformanceConfiguration configuration;
    private LazyPerformanceLag lazyPerformanceLag;
    private LazyPerformanceAnnouncement announcement;

    private final NumberFormat formatter = new DecimalFormat("#0.00");

    @Override
    public void onEnable() {
        try {
            setupConfiguration();
            setupLag();
            setupAnnouncement();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setupAnnouncement() {
        if (announcement == null) {
            this.announcement = new LazyPerformanceAnnouncement(this);
        }
        // Whether enable, enable the loop
        if (this.configuration.get(LazyPerformanceConfigurationEntry.LOG_OUT_TPS)) {
            this.announcement.runTaskTimerAsynchronously(this,
                    0,
                    (int) configuration.get(LazyPerformanceConfigurationEntry.LOG_OUT_TPS_DURATION) * 20
            );
        }
    }

    private void setupConfiguration() throws IOException {
        if (configuration == null) {
            configuration = new LazyPerformanceConfiguration(this);
        }
    }

    private void setupLag() {
        // Set up new lag object
        if (this.lazyPerformanceLag == null) {
            this.lazyPerformanceLag = new LazyPerformanceLag(this);
        }
        this.lazyPerformanceLag.runTaskTimerAsynchronously(this, 0, 0);
    }

    public double getLastTPS() {
        return (this.lazyPerformanceLag == null) ? 0 : this.lazyPerformanceLag.getLastTPS();
    }

    public String getFormattedLastTPS() {
        return formatter.format(getLastTPS());
    }

    public LazyPerformanceConfiguration getConfiguration() {
        return configuration;
    }
}
