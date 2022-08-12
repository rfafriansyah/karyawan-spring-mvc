package com.latihan.tugas6.service.impl;

import com.latihan.tugas6.model.Karyawan;
import com.latihan.tugas6.repository.KaryawanRepository;
import com.latihan.tugas6.service.KaryawanService;
import com.latihan.tugas6.utils.TemplateResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Service
public class KaryawanImpl implements KaryawanService {

    public static final Logger log = LoggerFactory.getLogger(KaryawanImpl.class);
    @Autowired
    KaryawanRepository karyawanRepository;
    @Autowired
    TemplateResponse templateResponse;

    public SimpleDateFormat dfDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    public SimpleDateFormat dfDOB = new SimpleDateFormat("yyyy-MM-dd");

    String a = dfDate.format(new java.sql.Date(new Date().getTime()));
    java.sql.Date getDate = new java.sql.Date(new Date().getTime());

    @Override
    public Map insert(Karyawan karyawan) {

        try {
            if (templateResponse.checkNull(karyawan.getNama())) {
                return templateResponse.templateError("Nama Tidak Boleh Kosong");
            }
            if (templateResponse.checkNull(karyawan.getJk())) {
                return templateResponse.templateError("Jenis Kelamin Tidak Boleh Kosong");
            }
            if (templateResponse.checkNull(karyawan.getDob())) {
                return templateResponse.templateError("Tanggal Lahir Tidak Boleh Kosong");
            }
            if (templateResponse.checkNull(karyawan.getAlamat())) {
                return templateResponse.templateError("Alamat Tidak Boleh Kosong");
            }
            if (templateResponse.checkNull(karyawan.getStatus())) {
                return templateResponse.templateError("Status Tidak Boleh Kosong");
            }

            karyawan.setCreated_date(new Date());
            karyawan.setUpdated_date(new Date());

            Karyawan karyawanObj = karyawanRepository.save(karyawan);
            log.info("{}", "Sukses Insert");
            return templateResponse.templateSukses(karyawanObj);
        } catch (Exception e) {
            log.info("{}", "Eror: " + e);
            return templateResponse.templateError(e);
        }
    }

    @Override
    public Map update(Karyawan karyawan) {

        try {
            if (templateResponse.checkNull(karyawan.getId())) {
                return templateResponse.templateError("Id Tidak Boleh Kosong");
            }

            Karyawan checkIdKaryawan = karyawanRepository.getbyID(karyawan.getId());
            if(templateResponse.checkNull(checkIdKaryawan)) {
                return templateResponse.templateError("Id Tidak Ditemukan");
            }

            checkIdKaryawan.setNama(karyawan.getNama());
            checkIdKaryawan.setJk(karyawan.getJk());
            checkIdKaryawan.setDob(karyawan.getDob());
            checkIdKaryawan.setAlamat(karyawan.getAlamat());
            checkIdKaryawan.setStatus(karyawan.getStatus());
            checkIdKaryawan.setUpdated_date(new Date());
            Karyawan karyawanObj = karyawanRepository.save(checkIdKaryawan);
            log.info("{}", "Sukses Update");
            return templateResponse.templateSukses(karyawanObj);
        } catch (Exception e) {
            log.info("{}", "Eror di method update: " + e);
            return templateResponse.templateError(e);
        }
    }

    @Override
    public Map delete(Long karyawan) {
        try {
            if (templateResponse.checkNull(karyawan)) {
                return templateResponse.templateError("Id Tidak Boleh Kosong");
            }

            Karyawan checkIdKaryawan = karyawanRepository.getbyID(karyawan);
            if(templateResponse.checkNull(checkIdKaryawan)) {
                return templateResponse.templateError("Id Tidak Ditemukan");
            }

            karyawanRepository.deleteById(karyawan);
            checkIdKaryawan.setDeleted_date(new Date());

            log.info("{}", "Sukses Deleted");
            return templateResponse.templateSukses("sukses deleted");
        }catch (Exception e) {
            log.info("{}", "Eror di method delete: " + e);
            return templateResponse.templateError(e);
        }
    }

    @Override
    public Map getAll(int size, int page) {
        try {
            Pageable show_data = PageRequest.of(page,size);
            Page<Karyawan> list = karyawanRepository.getAllData((java.awt.print.Pageable) show_data);
            return templateResponse.templateSukses(list);
        }catch (Exception e) {
            log.info("{}", "Eror di method getAll: " + e);
            return templateResponse.templateError(e);
        }
    }

    public Map getById(Karyawan karyawan) {
        try {
            if (templateResponse.checkNull(karyawan)) {
                return templateResponse.templateError("Id Tidak Boleh Kosong");
            }

            Karyawan checkIdKaryawan = karyawanRepository.getbyID(karyawan.getId());
            if(templateResponse.checkNull(checkIdKaryawan)) {
                return templateResponse.templateError("Id Tidak Ditemukan");
            }

            log.info("{}", "Sukses getById");
            return templateResponse.templateSukses(checkIdKaryawan);
        }catch (Exception e) {
            log.info("{}", "Eror di method getById: " + e);
            return templateResponse.templateError(e);
        }
    }
}