package com.latihan.tugas6.repository;

import com.latihan.tugas6.model.Karyawan;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;

@Repository
public interface KaryawanRepository extends PagingAndSortingRepository<Karyawan, Long> {

    // JPQ
    @Query("select c from Karyawan c")// nama class
    public Page<Karyawan> getAllData(Pageable pageable);
    // JPQ
    @Query("select c from Karyawan c WHERE c.id = :karyawanId")
    public Karyawan getbyID(@Param("karyawanId") Long idKaryawan);
    // jPQL
    public Karyawan findOneById(Long id);

    // native query
    @Query(value = "select c from Karyawan c WHERE c.id = :karyawanId", nativeQuery = true)
    public Object getbyIDNativeQuery(@Param("karyawanId") Long idKaryawan);

    default Karyawan save(Karyawan karyawan) {
        return null;
    }
}