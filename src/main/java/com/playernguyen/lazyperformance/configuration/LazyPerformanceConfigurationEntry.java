package com.playernguyen.lazyperformance.configuration;

import com.playernguyen.powreedcore.configs.PowreedConfigurationEntryInterface;

public enum  LazyPerformanceConfigurationEntry implements PowreedConfigurationEntryInterface {

    MINIMUM_TPS_TO_REPORT("MinimumTPSToReport", 12.0D),
    LOG_OUT_TPS("LogOutTpsEnable", true),
    LOG_OUT_TPS_DURATION("LogOutTpsDuration", 60L)
    ;

    private final String path;
    private final Object definition;

    LazyPerformanceConfigurationEntry(String path, Object definition) {
        this.path = path;
        this.definition = definition;
    }


    @Override
    public String path() {
        return path;
    }

    @Override
    public Object initial() {
        return definition;
    }
}
