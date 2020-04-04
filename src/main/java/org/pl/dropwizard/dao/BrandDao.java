package org.pl.dropwizard.dao;

import org.jdbi.v3.sqlobject.config.RegisterFieldMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.pl.dropwizard.model.Brand;

import java.util.Optional;
import java.util.Set;

public interface BrandDao {

  @SqlUpdate("insert into brands (name_brand) values (:name)")
  @GetGeneratedKeys
  @RegisterFieldMapper(Brand.class)
  Brand create(@BindBean Brand brand);

  @SqlUpdate("update brands set name_brand = :name where id_brand = :id")
  boolean update(@BindBean Brand brand, @Bind("id") Long id);

  @SqlUpdate("delete from brands where id_brand = :id")
  boolean deleteById(@Bind("id") Long id);

  @SqlQuery("select * from brands where id_brand = :id")
  @RegisterFieldMapper(Brand.class)
  Optional<Brand> findById(@Bind("id") Long id);

  @SqlQuery("select * from brands")
  @RegisterFieldMapper(Brand.class)
  Set<Brand> findAll();
}
