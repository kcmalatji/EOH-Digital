package com.eoh.digitalplatoon;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;


@RestController
public class InvoiceController {
//    @Autowired
//     private JdbcTemplate jdbctemplate;

    ArrayList<Invoice> Invoices=new ArrayList<>();
   
    private Connection connect=null;
    private Statement statement = null;
    private ResultSet resultSet=null;
    private ResultSet additem=null;


    public void readDataBase() throws Exception{
        Class.forName("org.h2.Driver");
        connect=DriverManager.getConnection("jdbc:h2:~/test","sa","");
        statement= connect.createStatement();
        resultSet=statement.executeQuery("SELECT * FROM INVOICE");
        System.out.println(resultSet);
        readResultset(resultSet);


    }
    public void AlterDataBase(Invoice invoice) throws Exception{
        Class.forName("org.h2.Driver");

        connect=DriverManager.getConnection("jdbc:h2:~/test","sa","");
        statement= connect.createStatement();
        additem=statement.executeQuery("insert into Invoice(client, vatrate, invoicedate)"+" values('"+invoice.Client +"' "+','+invoice.vatRate+',' +invoice.invoiceDate+");");

       // '"+invoice.Client +"',
        //Addinvoice(additem);

    }
    public Invoice Addinvoice(ResultSet additem)throws SQLException{
        Invoice invoice=new Invoice();
        invoice.setClient(invoice.Client);


        return invoice;
    }
    public ArrayList readResultset(ResultSet resultSet)throws SQLException {
        while(resultSet.next()){

            Invoice invoice=new Invoice();
           Long id=resultSet.getLong("ID");
            String name=resultSet.getString("CLIENT");
            Long rate=resultSet.getLong("VATRATE");
            Date date=resultSet.getDate("INVOICEDATE");

            invoice.setId(id);
            invoice.setClient(name);
            invoice.setVatRate(rate);
            invoice.setInvoiceDate(date);
            Invoices.add(invoice);
        }

        return Invoices;

    }

    @RequestMapping("/invoices")
    public ArrayList<Invoice> invoice(Model model) throws Exception {
        if(Invoices.size()==0)
        {
            readDataBase();
        }
        System.out.println( Invoices.size());
        model.addAttribute("Invoices", Invoices);
        return Invoices;
    }
    @RequestMapping("/invoices/{invoiceId}")
    public Invoice singleinvoice(@PathVariable("invoiceId") int id) throws Exception {
        if(Invoices.size()==0)
        {
            readDataBase();
        }
        System.out.println( Invoices.size());
        int index=id-1;
        return Invoices.get(index);
    }

    //@PostMapping("/invoices")
    @RequestMapping(value="/invoices", method={RequestMethod.POST})
    public ResponseEntity<Invoice> update(@RequestBody Invoice invoice)throws Exception {
//        AlterDataBase(invoice);
//        System.out.println(invoice);
        if (invoice!=null){
            AlterDataBase(invoice);
        }

        return new ResponseEntity<Invoice>(invoice,HttpStatus.OK);
    }


}