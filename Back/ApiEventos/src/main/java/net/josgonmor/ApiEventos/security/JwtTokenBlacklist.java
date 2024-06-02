package net.josgonmor.ApiEventos.security;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

@Component
public class JwtTokenBlacklist {

    private ConcurrentMap<String, Long> blacklist = new ConcurrentHashMap<>();
    private final long BLACKLIST_EXPIRATION_MINUTES = 6000000;
    private final long CLEANUP_INTERVAL_MINUTES = 600000000;

    public void addToBlacklist(String token) {
        blacklist.put(token, System.currentTimeMillis());
    }

    public boolean isTokenBlacklisted(String token) {
        cleanupExpiredTokens();
        return blacklist.containsKey(token);
    }

//    @Scheduled(fixedRate = CLEANUP_INTERVAL_MINUTES * 60 * 1000)
    private void cleanupExpiredTokens() {
        long expirationTime = TimeUnit.MINUTES.toMillis(BLACKLIST_EXPIRATION_MINUTES);
        long currentTime = System.currentTimeMillis();
        blacklist.entrySet().removeIf(entry -> (currentTime - entry.getValue()) > expirationTime);
    }
}

