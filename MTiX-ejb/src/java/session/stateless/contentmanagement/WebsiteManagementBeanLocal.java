/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless.contentmanagement;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author JingYing
 */
@Local
public interface WebsiteManagementBeanLocal {

    public List<ArrayList> getWebpageList(String companyName);

    public ArrayList getEventWebpageInfo(String id);

    public List<ArrayList> getEventSessionInfo(String id);

    public List<ArrayList> getEventPromotionInfo(String id);

    public List<ArrayList> getCreditCardEvents(String company);

    public List<ArrayList> getVolumeDiscountEvents(String company);

    public List<ArrayList> getWebpageListByType(String type, String companyname);

    public String getCompanyLogo(String companyName);

    public ArrayList getCompanyInfo(String companyName);

    public List<ArrayList> getEventConcert(String type, String company);

    public List<ArrayList> getAllPropertyName(String company);

    public List<ArrayList> getPropertyEvents(String id);

    public String getPropertyName(Long id);

    public List<ArrayList> searchEngineBasedOnTypes(String keyword, String company);

    public List<ArrayList> searchEvents(String keyword, String company);
    
}
