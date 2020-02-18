package org.pl.dropwizard.dao;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.config.RegisterFieldMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.pl.dropwizard.model.Brand;
import org.pl.dropwizard.model.Model;

import java.util.List;
import java.util.Optional;

public interface ModelDao {
    //done
    @SqlUpdate("insert into models(name_model, brand_id) values (:name, :brand_id) ")
    @GetGeneratedKeys()
    @RegisterFieldMapper(Model.class)
    Model create(@Bind("name") String name,@Bind("brand_id") Long brand_id);

    @SqlUpdate("delete from models where id = :id")
    void deleteById(Long id);

    @SqlQuery("select * from models m left join brands b on b.id_brand=m.brand_id where m.id_model=:id")
    @RegisterBeanMapper(Model.class)
    @RegisterBeanMapper(Brand.class)
    Optional<Model> findById(@Bind("id") Long id);


    //done
    @SqlQuery("select * from models m left join brands b on b.id_brand = m.brand_id where m.id_model = :id")
    @RegisterFieldMapper(Model.class)
    Optional<Model> findById2(@Bind("id") Long id);

    @SqlQuery("select id_model, name_model, brand_id, id_brand, name_brand from models m left join brands b on b.id_brand = m.brand_id")
    @RegisterFieldMapper(Model.class)
//    @RegisterFieldMapper(Brand.class)
    List<Model> findAll();
}
