package com.latihan.tugas6.controller;

import com.latihan.tugas6.model.Karyawan;
import com.latihan.tugas6.service.KaryawanService;
import com.latihan.tugas6.utils.TemplateResponse;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/v1/karyawan/")
public class KaryawanController {
    @Autowired
    public KaryawanService karyawanService;
    @Autowired
    public TemplateResponse templateResponse;


    @PostMapping("/add")
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map>save(@RequestBody Karyawan objModel) {
        Map obj = karyawanService.insert(objModel);
        return new ResponseEntity<Map>(obj, HttpStatus.OK);
    }

    @PutMapping("/edit")
    public ResponseEntity<Map>update(@RequestBody Karyawan karyawanId) {
        Map obj = karyawanService.update(karyawanId);
        return new ResponseEntity<Map>(obj,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map>delete(@PathVariable(value = "id") Long id) {
        Map obj = karyawanService.delete(id);
        return new ResponseEntity<Map>(obj,HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<Map>list(@RequestParam() Integer page, @RequestParam Integer size) {
        Map list = karyawanService.getAll(size,page);
        return new ResponseEntity<Map>(list,new HttpHeaders(),HttpStatus.OK);
    }

    @GetMapping("{id}")
    public  ResponseEntity<Map>getById(@PathVariable(value = "id")Karyawan id) {
        Map obj = karyawanService.getById(id);
        return new ResponseEntity<Map>(obj,HttpStatus.OK);
    }
}