package com.swaperia.audit;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import com.swaperia.config.Constants;
import com.swaperia.security.SecurityUtils;



@Component
public class AuditorAwareImpl implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {
        return Optional.of(SecurityUtils.getCurrentUserUsername().orElse(Constants.SYSTEM_ACCOUNT));
	}

}
