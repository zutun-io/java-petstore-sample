package io.zutun.samples.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.time.LocalDateTime;
import java.util.Optional;

import static java.time.ZoneOffset.UTC;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = {
                "io.zutun.samples.domain",
                "io.zutun.samples.adapter.out.persistence"
        }
)
@EnableJpaAuditing(
        dateTimeProviderRef = "dateTimeProvider"
)
@RequiredArgsConstructor
public class JpaConfig {

    @ConditionalOnMissingBean(value = AuditorAware.class)
    @Bean(name = "auditorProvider")
    public AuditorAware<String> auditorProvider() {
        return () -> Optional.of("author@zutun.io");
    }

    @Bean(name = "dateTimeProvider")
    public DateTimeProvider dateTimeProvider() {
        return () -> Optional.of(LocalDateTime.now(UTC));
    }

}
