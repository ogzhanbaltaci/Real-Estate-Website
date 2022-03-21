package codezilla.estate.business.abstracts;

import codezilla.estate.core.utilities.results.Result;
import codezilla.estate.entities.concretes.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;


public interface ProductService {

    Result addProducts(Product product);

    public void saveProductToDB(MultipartFile file, String ilan_baslik,
                                int ilan_fiyat, String ilan_aciklama, int oda_sayisi, int bina_yasi,
                                int esya_durumu, int kat,
                                int satilik, String tam_adres, String sehir, String ilce);


    public Page<Product> findPage(int pageNumber);

    public void updateOrt(int id, double ort);

    Product getByIlan_id(int id);

}
