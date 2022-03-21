package codezilla.estate.api.controllers;

import codezilla.estate.business.abstracts.ProductService;
import codezilla.estate.business.abstracts.UserService;
import codezilla.estate.business.concretes.PuanManager;
import codezilla.estate.business.concretes.SSSManager;
import codezilla.estate.dataAccess.abstracts.UserDao;
import codezilla.estate.entities.concretes.Product;
import codezilla.estate.entities.concretes.Puan;
import codezilla.estate.entities.concretes.SSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.DecimalFormat;
import java.util.List;


@Controller
@RequestMapping("")
public class MainController {

    @Autowired
    ProductService productService;

    private UserService userService;


    @Autowired
    private UserDao userDao;


    @Autowired
    private PuanManager puanManager;

    @Autowired
    public MainController(UserService userService ) {
        super();
        this.userService = userService;


    }

    @GetMapping("/düzenle")
    public String duzenlePage(){

        return "düzenle";
    }




    //Puan ortalaması hesaplama methodu
    public double calculateScoreAverage(int id, List<Puan> puanlar){

        double toplam=0.0;
        double ort=0.00;

        //puani olmayan ilanlar icin
        if(puanlar.size() < 1 || puanlar == null)
            return ort;

        //toplam puan
        for (Puan puan1 : puanlar) {
            toplam += puan1.getPuan();
        }

        //puan ortalaması
        ort = toplam / puanlar.size();

        ort = (double)Math.round(ort * 100)/100; //virgülden sonraki basamak sayısı max=2

        //productService.updateOrt(id, ort);

        return ort;
    }


    //İlan puanı ortalaması hesaplatmak için
    @GetMapping("/ilan/{ilan_id}")
    public String ilanPage(@PathVariable(name = "ilan_id") int id, Model model, Model model2){

        //Model kullanmıyoruz çünkü yeni puan oluşturamıyorduk.
        //Puan puan = new Puan(); //htmlde ilan_idsini çekebilmek için tek parametreli constructor ile nesneyi oluşturuyoruz.
        //model.addAttribute("puan", new Puan());//oluşturduğumuz nesnenin modelini tanımlıyoruz.

        List<Puan> puanlar = puanManager.getByIlan_id(id); //ortalama hesaplatmak için tüm puanları alıyoruz.

        //Verilen Puanların ortalamasını hesaplamak için ikinci parametreye methodu yazıldı.
        productService.updateOrt(id, calculateScoreAverage(id, puanlar));// ortalamayı ilan tablosunda ilanın değerlendirme_ort sütununa ekliyoruz.

        //Oluşturdugumuz modelden ayrıca htmlde action alarak ilan/d/{ilan_id} url'ini oluşturuyoruz.
        Product product = productService.getByIlan_id(id); //yukarıda updateledikten sonra ilanı htmlde display etmek için productu getiriyoruz.
        model2.addAttribute("ilan1", product);  //productumuzun modelini tanımlıyoruz.
        //Ayrıca bu modeli ilan sayfasında bu id'ye göre olusturdugumuz ilanın(modelin) bilgilerini display etmek için kullanıyoruz
        return "ilan";
    }

    //Ilan puanı almak için
    @PostMapping ("/ilan/d/{ilan_id}") //html üzerinden, gittiğimiz url'in ilan_id sini de bir önceki asıl ilan sayfamızdaki urldeki id ile aynı yaptım.
    public String ilanPage2(@RequestParam int puan, @PathVariable(name = "ilan_id") int id){ //Daha sonra o id'yi urlden çektim
        // puan.setIlanid(id);  //idyi girdiğimiz puan değerine set ettim.
        // puanManager.add(puan); //puan kayıt.

        puanManager.savePuanToDB(puan, id);

        return "result";
    }


    @GetMapping("/ilanduzenle")
    public String ilanduzenlePage(){

        return "ilanduzenle";
    }

    /*@GetMapping("/ilanver")
    public String ilanverPage(){

        return "ilanver";
    }*/
    /*@GetMapping( value = "/kayitol.html")
    public String showSignUpForm(Model model ) {
        model.addAttribute("user", new User());

        return "kayitol";
    }/*
    @PostMapping("/kaydet")
    public String showSignUpForm(@ModelAttribute("user")  User user){
        System.out.println(new SuccessResult("basarili") + user.getEmail());
        return "redirect:Anasayfa";

    }*/

    @GetMapping("/mesaj")
    public String mesajPage(){

        return "mesaj";
    }

    @GetMapping("/passchange")
    public String passchangePage(){

        return "passchange";
    }

    @GetMapping("/profil")
    public String profilPage(){

        return "profil";
    }

    @GetMapping("/sifremi_unuttum")
    public String sifremi_unuttum(){

        return "sifremi_unuttum";
    }

    @Autowired
    private SSSManager sssManager;

    @RequestMapping("/SSS")
    public String viewHomePage(Model model) {
        List<SSS> listProducts = sssManager.listAll();
        model.addAttribute("listProducts", listProducts);

        return "SSS";
    }
    @GetMapping("/yorum")
    public String yorum(){

        return "yorum";
    }

    @GetMapping("/gmail")
    public String gmailPage(){

        return "gmail";
    }

}
