package com.playernguyen.lazyperformance.configuration;

import com.playernguyen.powreedcore.configs.PowreedConfigurationAbstract;
import org.bukkit.plugin.Plugin;

import java.io.IOException;

public class LazyPerformanceConfiguration extends
        PowreedConfigurationAbstract<LazyPerformanceConfigurationEntry> {

    public LazyPerformanceConfiguration(Plugin plugin) throws IOException {
        super(plugin,
                LazyPerformanceConfigurationEntry.values(),
                "config.yml",
                null
        );
    }

    @Override
    protected void init() {

    }
}
