import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by Jui on 18/5/2017.
 */

public class jasoup {

    public static void main(String[] args) {
        try {
            // fetch the document over HTTP
            String name=null,dob=null,gender="male",institution=null,thesis=null,nationality=null,picture,topic=null,a_name=null,year,a_institution=null,degree;
            Document doc = Jsoup.connect("https://en.wikipedia.org/wiki/List_of_computer_scientists").get();

            // get the page title
            String title = doc.title();
            System.out.println("title: " + title);

            // get all links in page
            Elements links = doc.select("div#mw-content-text");
           // System.out.println(links);
            Elements ul =links.select("ul"); //doc.select("div#mw-content-text > ul");
            Elements ul1= new Elements();

            for(int i= 2; i< ul.size()-3;i++){
                ul1.add(ul.get(i));
            }
            Elements li = ul1.select("li");
           // System.out.println(li);
            Elements ahref = new Elements();
            for(Element l :li){
              // System.out.println(l.select("a[href]").get(0));
                ahref.add(l.select("a[href]").get(0));
            }
           //Elements ahref = li.select("a[href]");
            //System.out.println("***************"+li.size()+"********"+ahref.size());
          for (Element link : ahref) {
                //System.out.println(link.attr("href"));
              try{
                Document doc1 = Jsoup.connect("https://en.wikipedia.org"+link.attr("href")).get();
                Elements div = doc1.select("div#mw-content-text");
                //System.out.println(div.size()+"***************");

                  Element table = div.select("table").get(0);
                  // System.out.println(table.text()+"*********************");
                  //System.out.println(div.size());
                  Elements rows = table.select("tr");
                  int num=0;
                  for(Element r : rows){
                      if(num==0){
                          System.out.println(r.select("th").text());
                          name = r.select("th").text();
                          //continue;
                      }
                      if(num==1){
                          System.out.println("Chobi:"+r.select("img[src$=.jpg]"));
                          if(r.select("th").text().equals("Born")){
                              dob= r.select("td").text();
                              System.out.println("Born:"+dob);
                          }
                      }
                      else {
                          Elements cols = r.select("td");
                          Elements headers = r.select("th");
                        //System.out.println(cols.get(1).text());

                          if(r.select("th").text().equals("Born")){
                            dob=cols.text();
                              System.out.println("Born:"+dob);
                          }
                          else if(r.select("th").text().equals("Nationality")){
                              nationality=cols.text();
                              System.out.println("Nationality:"+nationality);
                          }
                          else if(r.select("th").text().equals("Institutions")){
                              institution=cols.text();
                              System.out.println("Institutions:"+institution);
                          }
                          else if(r.select("th").text().equals("Alma mater")){
                              a_institution=cols.text();
                              System.out.println("Alma:"+a_institution);
                          }
                          else if(r.select("th").text().equals("Thesis"))
                          {
                              thesis=cols.text();
                              System.out.println("Thesis:"+thesis);
                          }
                          else if(r.select("th").text().equals("Known for"))
                          {
                              topic=cols.text();
                              System.out.println("Known for:"+topic);
                          }

                          else if(r.select("th").text().equals( "Notable awards"))
                          {
                              a_name=cols.text();
                              System.out.println("Notable awards:"+a_name);
                          }
                             //System.out.println("Chobi:"+cols.get(1).select("ahref"));

                      }
                      num++;
                  }


                  if(name==null|| dob==null || institution==null || nationality==null|| topic==null|| a_name==null ||thesis==null||a_institution==null )
                  {

                  }
               // }
              }catch (Exception e){
                    continue;
              }


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
