package com.mybatis3.mapper;

import com.mybatis3.domain.Address;


/**
 * @author Siva
 */
public interface AddressMapper {
    Address findAddressById(Integer id);
}
