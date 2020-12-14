package org.example.data.jpa.repository;

import org.example.data.jpa.entity.App;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by hyq on 2020/12/13 14:22.
 */
@Repository
public interface AppRepository extends JpaRepository<App, Long> {

}
