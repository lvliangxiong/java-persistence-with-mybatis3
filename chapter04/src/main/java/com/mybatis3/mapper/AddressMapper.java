package com.mybatis3.mapper;

import com.mybatis3.domain.Address;
import org.apache.ibatis.annotations.Select;


public interface AddressMapper {
    @Select("SELECT addr_id AS addrId, street, city, state, zip, country FROM address WHERE addr_id = #{id}")
    Address selectAddressById(int id);
}
