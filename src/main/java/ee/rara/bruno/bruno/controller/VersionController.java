package ee.rara.bruno.bruno.controller;

import lombok.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class VersionController {

    @GetMapping("/version")
    public Map<String, String> getVersion() {
        Package pkg = getClass().getPackage();

        String version = pkg.getImplementationVersion();
        String buildTime = pkg.getImplementationTitle(); // not used yet

        String timestamp = "N/A";
        try {
            var manifest = new java.util.jar.JarFile(
                    new java.io.File(getClass().getProtectionDomain().getCodeSource().getLocation().toURI())
            ).getManifest();

            timestamp = manifest.getMainAttributes().getValue("Build-Timestamp");
        } catch (Exception e) {
            // fallback
        }

        return Map.of(
                "version", version != null ? version : "N/A",
                "buildTime", timestamp
        );
    }
}

