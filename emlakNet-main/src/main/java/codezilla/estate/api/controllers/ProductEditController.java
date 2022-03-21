package codezilla.estate.api.controllers;

import codezilla.estate.business.abstracts.ProductService;
import codezilla.estate.core.utilities.results.Result;
import codezilla.estate.entities.concretes.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;


@Controller

public class ProductEditController {

    @Autowired
    private ProductService productService;

    @GetMapping("/ilanduzenle/{ilan_id}")
    public String displayEditPage(@PathVariable(name = "ilan_id") int id, Model model) {
       Product product = productService.getByIlan_id(id);
       model.addAttribute("editp", product);

        return "ilanduzenle";
    }

    @PostMapping("/process_edit/{ilan_id}")
    public String updateProduct(@PathVariable (name = "ilan_id") int id, @RequestParam("efile") MultipartFile file, @Param("ebaslik") String ilan_baslik,
                               @Param("efiyat") int ilan_fiyat, @Param("eaciklama") String ilan_aciklama,
                               @Param("eodasayisi") int oda_sayisi,
                               @Param("eyas") int bina_yasi,
                               @Param("eesya") int esya_durumu,
                               @Param("ekat") int kat,
                               @Param("esatilik") int satilik,
                               @Param("eadres") String tam_adres,
                               @Param("esehir") String sehir,
                               @Param("eilce") String ilce) {

        Product editable = productService.getByIlan_id(id);

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if(fileName.contains("..")){
            System.out.println("not a valid file");
        }
        try {
            editable.setIlan_resim(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        editable.setIlce(ilce);
        editable.setSehir(sehir);
        editable.setIlan_baslik(ilan_baslik);
        editable.setIlan_aciklama(ilan_aciklama);
        editable.setOda_sayisi(oda_sayisi);
        editable.setBina_yasi(bina_yasi);
        editable.setEsya_durumu(esya_durumu);
        editable.setKat(kat);
        editable.setSatilik(satilik);
        editable.setTam_adres(tam_adres);
        editable.setIlan_fiyat(ilan_fiyat);

        productService.addProducts(editable);

        return "result";}



}
