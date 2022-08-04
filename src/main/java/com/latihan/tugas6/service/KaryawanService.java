package com.latihan.tugas6.service;

import com.latihan.tugas6.model.Karyawan;

import java.util.List;

public interface KaryawanService {
    public Karyawan save(Karyawan obj);
    public Karyawan update(Karyawan obj);
    public List<Karyawan> deleted(Long id);
    public List<Karyawan> dataKaryawan(int row,int page);
    public Karyawan findById(long id);
}
