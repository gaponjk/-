package org.example;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class CompanyList {

    private List<Company> companies;

    @XmlElementWrapper(name = "companies")
    @XmlElement(name = "company")
    public List<Company> getCompanies() {
        return companies;
    }

    public CompanyList(List<Company> companies) {
        this.companies = companies;
    }
    public CompanyList(){}
}
