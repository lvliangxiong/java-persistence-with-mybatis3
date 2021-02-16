package com.mybatis3.mapper;

import com.mybatis3.domain.Address;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;


public interface AddressMapper {
    @Select("SELECT addr_id AS addrId, street, city, state, zip, country FROM address WHERE addr_id = #{id}")
    Address selectAddressById(int id);

    @Insert("INSERT INTO address (street, city, state, zip, country) " +
            "VALUES (#{street}, #{city}, #{state}, #{zip}, #{country})")
    void insertAddress(Address address);
}
