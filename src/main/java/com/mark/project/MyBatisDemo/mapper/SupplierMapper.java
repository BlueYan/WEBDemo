package com.mark.project.MyBatisDemo.mapper;

import com.mark.project.MyBatisDemo.domain.Supplier;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Mark_Yan on 2017/8/25.
 */
public interface SupplierMapper {

	void save(Supplier supplier);

	void saveMiddleData(@Param("productID")Long productID, @Param("supplierID")Long supplierID);

	Supplier select(Long id);

}
