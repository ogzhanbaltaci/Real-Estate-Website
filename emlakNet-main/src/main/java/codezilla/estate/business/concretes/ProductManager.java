package codezilla.estate.business.concretes;

import codezilla.estate.business.abstracts.ProductService;
import codezilla.estate.core.utilities.results.Result;
import codezilla.estate.core.utilities.results.SuccessResult;
import codezilla.estate.dataAccess.abstracts.ProductDao;
import codezilla.estate.entities.concretes.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;


@Service
public class ProductManager implements ProductService {

    ProductDao productDao;

    @Autowired
    public ProductManager(ProductDao productDao){
        this.productDao = productDao;
    }

    @Override
    public Result addProducts(Product product) {
        productDao.save(product);

        return new SuccessResult();
    }

    //ilan kayıt
    @Override
    public void saveProductToDB(MultipartFile file, String ilan_baslik,
                                int ilan_fiyat, String ilan_aciklama, int oda_sayisi, int bina_yasi,
                                int esya_durumu, int kat,
                                int satilik, String tam_adres, String sehir, String ilce) {

        Product p = new Product();
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if(fileName.contains("..")){
            System.out.println("not a valid file");
        }
        try {
            p.setIlan_resim(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        p.setIlan_baslik(ilan_baslik);
        p.setIlan_aciklama(ilan_aciklama);
        p.setOda_sayisi(oda_sayisi);
        p.setBina_yasi(bina_yasi);
        p.setEsya_durumu(esya_durumu);
        p.setKat(kat);
        p.setSatilik(satilik);
        p.setTam_adres(tam_adres);
        p.setSehir(sehir);
        p.setIlce(ilce);
        p.setIlan_fiyat(ilan_fiyat);

        productDao.save(p);

    }


    //Pagination için page sınıfı, nesneleri sayısına(size) göre ayrı ayrı paketleyerek tutuyor.
    @Override
    public Page<Product> findPage(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber-1, 5);//Pageable cinsinde request oluşturuyoruz: 1. sayfa 5 tane gibi.
        return productDao.findAll(pageable); //page cinsinde paket paket productları döndürüyor.
    }


    @Override
    public void updateOrt(int id, double ort) {
        productDao.updateOrt(id, ort);
    }

    @Override
    public Product getByIlan_id(int id) {
        return productDao.getByIlan_id(id);
    }

}
