package io.oasp.tddsamples.general.logic.impl;

import io.oasp.tddsamples.general.common.api.UserProfile;
import io.oasp.tddsamples.general.common.api.Usermanagement;
import io.oasp.tddsamples.general.common.api.datatype.Role;
import io.oasp.tddsamples.general.common.api.to.UserDetailsClientTo;
import io.oasp.tddsamples.general.common.base.AbstractBeanMapperSupport;

import javax.inject.Named;

import org.springframework.stereotype.Component;

/**
 * Implementation of {@link Usermanagement}.
 */
@Named
@Component
public class UsermanagementDummyImpl extends AbstractBeanMapperSupport implements Usermanagement {

  /**
   * {@inheritDoc}
   */
  @Override
  public UserProfile findUserProfileByLogin(String login) {
    // this is only a dummy - please replace with a real implementation
    UserDetailsClientTo profile = new UserDetailsClientTo();
    profile.setName(login);
    profile.setFirstName("Peter");
    profile.setLastName(login);
    profile.setRole(Role.CHIEF);
    return profile;
  }

}
