package com.cahek.ws;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Commission list from class commission
 */
@XmlRootElement(name = "commissions")
@XmlAccessorType(XmlAccessType.FIELD)
public class CommissionList {

    /**
     * {@link List} of Commission
     */
    @XmlElement(name = "commission")
    private List<Commission> commissionList = null;

    public List<Commission> getCommissionList() {
        return commissionList;
    }

    public void setCommissionList(List<Commission> commissionList) {
        this.commissionList = commissionList;
    }
}
