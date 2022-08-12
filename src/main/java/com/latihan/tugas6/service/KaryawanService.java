package com.latihan.tugas6.service;

import com.latihan.tugas6.model.Karyawan;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface KaryawanService {
    public Map insert(Karyawan karyawan);
    public Map update(Karyawan karyawan);
    public Map delete(Long karyawan);
    public Map getAll(int size, int page);

    public Map getById(Karyawan karyawan);
}