///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package upload;
//
///**
// *
// * @author Luciano
// */
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//
//import java.util.regex.Pattern;
//import java.util.regex.Matcher;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class LetraHino {
//        private String letraHino;
//        private String urlSite;
//        public void buscarLetraDoHino(){          
//
//        Document doc;
//        try {
//
//            // need http protocol
//            doc = Jsoup.connect(urlSite).get();
//
//            // get page title
//            String letra = doc.body().toString();
////            Pattern pattern = Pattern.compile("<p>");
////            Pattern pattern2 = Pattern.compile("</p>");
////            Matcher matcher = pattern.matcher(letra);
////            Matcher matcher2 = pattern2.matcher(letra);
////
////            List teste = new ArrayList();
////            List testeDois = new ArrayList();
////
////            while (matcher.find() && matcher2.find()) {
////                teste.add(matcher.start());
////                teste.add(matcher2.start());
////            }
////            
////
////            for (int i = 0; i < teste.size(); i++) {
////                int valor = (int) teste.get(i);
////                if (valor > 10000) {
////                    testeDois.add(teste.indexOf(valor));
////                }
////            }
////            for (int j = 0; j < testeDois.size(); j++) {
////                int valor = (int) testeDois.get(0);
////                teste.remove(valor);
////            }
////            int primeiroValor = (int) teste.get(0);            
////            int ultimoValor = teste.size()-1;
////            ultimoValor = (int) teste.get(ultimoValor);
////            String letraFinal = letra.substring(primeiroValor, ultimoValor + 4);
////            letraHino = letraFinal.replace("<p>", " ");
////            letraHino = letraHino.replace("</p>", " ");
////            letraHino = letraHino.replace("<br>", " ");
//          
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    public String getLetraHino() {
//        return letraHino;
//    }
//
//    public String getUrlSite() {
//        return urlSite;
//    }
//
//    public void setUrlSite(String urlSite) {
//        this.urlSite = urlSite;
//    }
//    
//        
//
//}
