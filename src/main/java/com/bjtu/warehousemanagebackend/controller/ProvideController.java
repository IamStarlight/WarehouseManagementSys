package com.bjtu.warehousemanagebackend.controller;


import com.bjtu.warehousemanagebackend.entity.Provide;
import com.bjtu.warehousemanagebackend.service.impl.ProvideServiceImpl;
import com.bjtu.warehousemanagebackend.utils.Result;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 供应商供货 前端控制器
 * </p>
 *
 * @author Jinxuan Chen
 * @since 2024-04-09
 */
@RestController
@RequestMapping("/provide")
public class ProvideController {

    @Autowired
    private ProvideServiceImpl provideService;

    //供货商供应商品，增一条
    @PostMapping
    public ResponseEntity<Result> addProvide(@RequestBody @Valid Provide provide){
        provideService.save(provide);
        return new ResponseEntity<>(Result.success(), HttpStatus.OK);
    }

    // Search provides by uid and gid
    @GetMapping
    public ResponseEntity<Result> getByUidAndGid(@RequestParam String uid, @RequestParam String gid) {
        return new ResponseEntity<>(Result.success(provideService.getByUidAndGid(uid, gid)), HttpStatus.OK);
    }

    //改，增，减,增删数量
    @PutMapping
    public ResponseEntity<Result> updateProvide(@RequestBody Provide provide) {
        provideService.updateProvide(provide);
        return new ResponseEntity<>(Result.success(), HttpStatus.OK);
    }

}
