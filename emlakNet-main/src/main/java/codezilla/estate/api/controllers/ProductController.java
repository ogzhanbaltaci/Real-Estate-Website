package codezilla.estate.api.controllers;

import codezilla.estate.business.abstracts.ProductService;

import codezilla.estate.dataAccess.abstracts.ProductDao;
import codezilla.estate.entities.concretes.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Controller
public class ProductController {

    ProductService productService;

    @Autowired
    ProductDao productDao;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/ilanver")
    public String viewIlanPage(){
        return "ilanver";
    }

    //İlan kayıt kodu
    @PostMapping("/process_ilan")
    public String saveProduct(@RequestParam("file") MultipartFile file, @RequestParam("pbaslik") String ilan_baslik,
                              @RequestParam("pfiyat") int ilan_fiyat, @RequestParam("paciklama") String ilan_aciklama,
                              @RequestParam("podasayisi") int oda_sayisi,
                              @RequestParam("pyas") int bina_yasi,
                              @RequestParam("pesya") int esya_durumu,
                              @RequestParam("pkat") int kat,
                              @RequestParam("psatilik") int satilik,
                              @RequestParam("padres") String tam_adres,
                              @RequestParam("psehir") String sehir,
                              @RequestParam("pilce") String ilce) {

        productService.saveProductToDB(file, ilan_baslik,ilan_fiyat,ilan_aciklama,oda_sayisi,
                bina_yasi,esya_durumu,kat,satilik,tam_adres,sehir,ilce);

        return "result";
    }

    //getOnePage methoduna ilk parametreyi(ilk currentPage = 1) göndermek için kullanıyoruz.
    @GetMapping("/")
    public String getAllPages(Model model){
        return getOnePage(model, 1);
    }


    //Pagination kodu
    @GetMapping("/{pageNumber}")
    public String getOnePage(Model model, @PathVariable("pageNumber") int currentPage){
        Page<Product> page = productService.findPage(currentPage); //Page içinde tüm nesneleri tutuyor anca parametreye göre o sayfada istenen kadarını getiriyor(5tane). currentPage=2 oldugunda da bir sonraki 5liyi getirip tutuyor.
        int totalPages = page.getTotalPages(); //page'in içinde productlar size'a göre sayfalara bölünüyor. 10 ürün varsa 2 sayfa.(size=5)
        long totalItems = page.getTotalElements(); //page'in içinde tüm productlarımız var bu methodla kaç tane oldugunu buluyoruz.
        List<Product> products = page.getContent(); //page'in içinden getContent ile pageden product'a çevirerek nesneleri alıyoruz. Ancak currentPage parametresine göre alıyoruz. 1. sayfa 5 adet,2. sayfa 5 adet.

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("products", products);

        return "Anasayfa";
    }

}

//    @GetMapping("/ilanver")
//    public String ilanForm(Model model){
//        List<Product> products = productDao.findAll();
//        model.addAttribute("product", products);
//
//        return "ilanver";
//    }

//    @PostMapping("/process_ilan")
//    public String ilanForm(Product product){
//        productService.addProducts(product);
//
//        return "result";
//    }
