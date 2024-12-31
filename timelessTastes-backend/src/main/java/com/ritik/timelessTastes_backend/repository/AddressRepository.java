package com.ritik.timelessTastes_backend.repository;

import com.ritik.timelessTastes_backend.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
