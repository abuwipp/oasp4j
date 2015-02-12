package io.oasp.tddsamples.general.dataaccess.base.dao;

import io.oasp.tddsamples.general.dataaccess.api.BinaryObjectEntity;
import io.oasp.tddsamples.general.dataaccess.api.dao.BinaryObjectDao;

import javax.inject.Named;

/**
 * Implementation of {@link BinaryObjectDao}.
 *
 * @author sspielma
 */
@Named
public class BinaryObjectDaoImpl extends ApplicationDaoImpl<BinaryObjectEntity> implements BinaryObjectDao {

  /**
   * {@inheritDoc}
   */
  @Override
  public Class<BinaryObjectEntity> getEntityClass() {

    return BinaryObjectEntity.class;
  }

}
