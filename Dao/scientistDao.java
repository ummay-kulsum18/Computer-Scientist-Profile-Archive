package com.springapp.mvc.dao;


import com.springapp.mvc.beans.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import java.io.*;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Jui on 1/5/2017.
 */
public class scientistDao {
    @Autowired
    JdbcTemplate template;
    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }
    protected static final class sMapper implements ParameterizedRowMapper
    {
        public scientist mapRow(ResultSet rs, int rowNum)
                throws SQLException
        {
            scientist e = new scientist();
            e.setName((rs.getString(1)));
            e.setDob(rs.getString(2));
            e.setGender(rs.getString(3));
            e.setInstitution(rs.getString(4));
            e.setThesis(rs.getString(5));
            e.setNationality(rs.getString(6));
            //Blob b = rs.getBlob(7);
            //int blobLength = (int) b.length();
            //byte[] blobAsBytes = b.getBytes(1, blobLength);
            byte[] b = rs.getBytes(7);
            e.setPicture(b);
            /*File f = new File ("C:\\Users\\Jui\\Documents\\L3T2\\TestProjects\\src\\main\\webapp\\resources\\img\\image.jpg");
            if(f.exists()){
                f.delete();
            }

                try {
                    OutputStream of = new FileOutputStream(f);
                    of.write(blobAsBytes);
                    of.close();

                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }*/


            return e;
        }
    }

    protected static final class aMapper implements ParameterizedRowMapper
    {
        public awardTable mapRow(ResultSet rs, int rowNum)
                throws SQLException
        {
            awardTable a =new awardTable();
            a.setName((rs.getString(1)));
            a.setDob(rs.getString(2));
            a.setA_name(rs.getString(3));
           a.setYear(rs.getString(4));
            a.setGender(rs.getString(5));
            a.setInstitution(rs.getString(6));
            a.setThesis(rs.getString(7));
            a.setNationality(rs.getString(8));
            return a;
        }
    }

    protected static final class kMapper implements ParameterizedRowMapper
    {
        public knownFor mapRow(ResultSet rs, int rowNum)
                throws SQLException
        {
            knownFor k =new knownFor();
            k.setName((rs.getString(1)));
            k.setDob(rs.getString(2));
            k.setTopic(rs.getString(3));
            return k;
        }
    }

    protected static final class almaMapper implements ParameterizedRowMapper
    {
        public alma_mater mapRow(ResultSet rs, int rowNum)
                throws SQLException
        {
            alma_mater al =new alma_mater();
            al.setName((rs.getString(1)));
            al.setDob(rs.getString(2));
            al.setA_institution(rs.getString(3));
            al.setDegree(rs.getString(4));
            return al;
        }
    }
    protected static final class nameMapper implements ParameterizedRowMapper
    {
        public String mapRow(ResultSet rs, int rowNum)
                throws SQLException
        {
            return rs.getString("name");
        }
    }

    protected static final class picMapper implements ParameterizedRowMapper
    {
        public pictures mapRow(ResultSet rs, int rowNum)
                throws SQLException
        {
           pictures p=new pictures();
            p.setName((rs.getString("name")));
            p.setPicture(rs.getBytes("picture"));
            return p;
        }
    }

    public List<scientist> getScientist(String name){
        String sql ="select * from scientists where UPPER (name) = UPPER (?) ";
        return template.query(sql, new sMapper(), new Object[]{name} );
        //return template.queryForObject(sql, new Object[]{name},new BeanPropertyRowMapper<scientist>(scientist.class));
    }

    public List<awardTable> getAward(String name, String dob){
        String sql="select *" +
                "from awards natural join scientists where name = ? and dob = ?";
        return template.query(sql, new aMapper(), new Object[]{name, dob});
    }

    public List<knownFor> getKnownFor(String name, String dob){
        String sql="select * from knownfor where name = ? and dob = ?";
        return template.query(sql, new kMapper(), new Object[]{name, dob});
    }
    public List<alma_mater> getAlmaMater(String name, String dob){
        String sql="select * from alma_mater where name = ? and dob = ?";
        return template.query(sql, new almaMapper(), new Object[]{name, dob});
    }

    public List<String> getScientstName(String name){
        String sql="select name from scientists where UPPER (name) like UPPER (?)";
        return template.query(sql, new nameMapper(), new Object[]{name+"%"});
    }

    public List<pictures> getPictures(String name){
        String sql="select name, picture from scientists where UPPER (name) = UPPER (?)";
        return template.query(sql, new picMapper(), new Object[]{name});
    }

    public List<pictures> getAllPictures(){
        //System.out.println("hereeeeeeeeeeeeeeeeeeeeeeeee");
        String sql="select name, picture from scientists";

        return template.query(sql, new picMapper());
    }


/*****************************account*******************/


protected static final class mesMapper implements ParameterizedRowMapper
{
    public Message mapRow(ResultSet rs, int rowNum)
            throws SQLException
    {
        Message m = new Message();
        m.setId((rs.getInt(1)));
        m.setSender((rs.getString(2)));
        m.setMessage((rs.getString(3)));
        m.setDate((rs.getString(4)));
        m.setTime((rs.getString(5)));
        return m;
    }
}
    public List<Message> getMessage (){
        String sql = "select * from Messages order by date1,time desc";
        return template.query(sql, new mesMapper());
    }

}
