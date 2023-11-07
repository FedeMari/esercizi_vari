package model;

import java.sql.*;

//La indichiamo come View in quanto contiene solo un sottoinsieme delle colonne di Salesrep
public class SalesrepView {
	private String name;
    private int rep_office;
    private Date hire_date;

    public void setName(String name) {
        this.name = name;
    }
    public void setRep_office(int rep_office) {
        this.rep_office = rep_office;
    }
    public void setHire_date(Date hire_date) {
        this.hire_date = hire_date;
    }
    @Override
    public String toString() {
        return "SalesrepView [name=" + name + ", rep_office=" + rep_office + ", hire_date=" + hire_date + "]";
    }

}
