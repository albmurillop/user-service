package es.uah.application.user.config

import org.dozer.DozerBeanMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

/**
 * Beans configuration.
 */
@Configuration
class BeanConfiguration {

    /**
     * Initialize PasswordEncoder.
     * 
     * @return PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder()
    }

    /**
     * Initialize DozerBeanMapper.
     * 
     * @return DozerBeanMapper
     */
    @Bean
    public DozerBeanMapper mapper() {
        return new DozerBeanMapper()
    }
}
