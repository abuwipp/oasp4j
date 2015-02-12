package io.oasp.tddsamples.general.common;

import io.oasp.tddsamples.test.general.AppProperties.LoginCredentials;
import io.oasp.tddsamples.test.general.webclient.WebClientWrapper;
import io.oasp.module.configuration.common.api.ApplicationConfigurationConstants;

import java.io.IOException;

import javax.persistence.EntityManager;

import org.apache.cxf.helpers.IOUtils;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * Contains reusable test features for rest service tests. Before each test execution the database will be wiped and
 * users will be inserted. See <code>src/test/resources/initializeTests.sql</code>
 *
 * @author mbrunnli
 */
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ TransactionalTestExecutionListener.class, DependencyInjectionTestExecutionListener.class })
@ContextConfiguration({ ApplicationConfigurationConstants.BEANS_DATA_ACCESS,
"classpath:/config/app/dataaccess/beans-db-connector.xml" })
@ActiveProfiles({ "integrationTest", "db-plain" })
public abstract class AbstractRestServiceTest {

  protected WebClientWrapper waiter = new WebClientWrapper(LoginCredentials.WAITER_USERNAME,
      LoginCredentials.WAITER_PASSWORD);

  protected WebClientWrapper chief = new WebClientWrapper(LoginCredentials.CHIEF_USERNAME,
      LoginCredentials.CHIEF_PASSWORD);

  @Autowired
  public EntityManager em;

  @Autowired
  public TransactionTemplate transactionTemplate;

  /**
   * Reset DB
   */
  @Before
  public void setup() {

    this.transactionTemplate.execute(new TransactionCallback<Object>() {
      /**
       * {@inheritDoc}
       */
      @Override
      public Object doInTransaction(TransactionStatus status) {

        try {
          String initializeScript =
              IOUtils.readStringFromStream(getClass().getResourceAsStream("/initializeTests.sql"));
          AbstractRestServiceTest.this.em.createNativeQuery(initializeScript).executeUpdate();
        } catch (IOException e) {
          e.printStackTrace();
        }
        return null;
      }
    });

  }
}
