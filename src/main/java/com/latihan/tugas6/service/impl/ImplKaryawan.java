package com.latihan.tugas6.service.impl;

import com.latihan.tugas6.model.Karyawan;
import com.latihan.tugas6.service.KaryawanService;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ImplKaryawan implements KaryawanService {
    static List<Karyawan> listKaryawan = new ArrayList<>();
    static int idIncrement = 0;
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Date date;

    @Override
    public Karyawan save(Karyawan obj) {
        date = new Date();
        obj.setId(idIncrement++);
        listKaryawan.add(obj);
        return obj;
    }

    @Override
    public Karyawan update(Karyawan obj) {
        for(Karyawan data: listKaryawan) {
            if(obj.getId() == data.getId()) {
                date = new Date();
                Karyawan update = new Karyawan();
                update.setId(obj.getId());
                update.setName(obj.getName());
                update.setJk(obj.getJk());
                update.setDob(obj.getDob());
                update.setAlamat(obj.getAlamat());
                update.setStatus(obj.getStatus());
                listKaryawan.remove(data);
                listKaryawan.add(update);
                return update;
            }
        }
        return null;
    }

    @Override
    public List<Karyawan> deleted(Long id) {
        for (Karyawan data: listKaryawan) {
            if (id == data.getId()) {
                Karyawan update = new Karyawan();
                update.setId(data.getId());
                update.setName(data.getName());
                update.setJk(data.getJk());
                update.setDob(data.getDob());
                update.setAlamat(data.getAlamat());
                update.setStatus(data.getStatus());
                listKaryawan.remove(data);
                return listKaryawan;
            }
        }
        return null;
    }

    @Override
    public List<Karyawan> dataKaryawan(int row, int page) {
        return listKaryawan;
    }

    @Override
    public Karyawan findById(long id) {
        for(Karyawan data: listKaryawan) {
            if (id == data.getId()) {
                return data;
            }
        }
        return null;
    }
}
