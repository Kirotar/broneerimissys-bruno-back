package ee.rara.bruno.bruno.config;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.lang.management.ManagementFactory;

@Component
public class UptimeInfoContributor implements InfoContributor {

    @Override
    public void contribute(Info.Builder builder) {
        long uptime = ManagementFactory.getRuntimeMXBean().getUptime();
        builder.withDetail("uptime", uptime + " ms");
    }
}