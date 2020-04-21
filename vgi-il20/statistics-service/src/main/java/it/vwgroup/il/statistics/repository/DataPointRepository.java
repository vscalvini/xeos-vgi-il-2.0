package it.vwgroup.il.statistics.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.vwgroup.il.statistics.domain.timeseries.DataPoint;
import it.vwgroup.il.statistics.domain.timeseries.DataPointId;

import java.util.List;

@Repository
public interface DataPointRepository extends CrudRepository<DataPoint, DataPointId> {

	List<DataPoint> findByIdAccount(String account);

}
