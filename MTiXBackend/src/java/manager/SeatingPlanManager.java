/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import entity.CompanyEntity;
import session.stateless.propertymanagement.SeatingPlanManagementBeanLocal;
import entity.PropertyEntity;
import entity.SeatEntity;
import entity.SectionEntity;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

/**
 *
 * @author catherinexiong
 */
public class SeatingPlanManager {

    private final SeatingPlanManagementBeanLocal seatingPlanManagementBeanLocal;

    public SeatingPlanManager(SeatingPlanManagementBeanLocal seatingPlanManagementBeanLocal) {
        this.seatingPlanManagementBeanLocal = seatingPlanManagementBeanLocal;
    }

    public List<PropertyEntity> getAllProperties() {
        return seatingPlanManagementBeanLocal.getAllProperties();
    }

    public List<SectionEntity> getAllSectionsInOneProperty(Long propertyId) {
        return seatingPlanManagementBeanLocal.getAllSectionsInOneProperty(propertyId);
    }

    public List<SeatEntity> getAllSeatsInOneSection(Long sectionId) {
        return seatingPlanManagementBeanLocal.getAllSeatsInOneSection(sectionId);
    }

    public Boolean linkSeatsToSection() {

        return seatingPlanManagementBeanLocal.linkSeatsToSection();
    }

    public Boolean linkSectionsToProperty() {

        return seatingPlanManagementBeanLocal.linkSectionsToProperty();
    }

//    public Boolean linkSectionToCategory() {
//
//        return seatingPlanManagementBeanLocal.linkSectionsToCategory();
//    }

    public Boolean linkCategoryToProperty() {

        return seatingPlanManagementBeanLocal.linkCategoryToProperty();
    }

    public List<SectionEntity> getSectionsByPropertyId(Long propertyId) {
        return seatingPlanManagementBeanLocal.getSectionsByPropertyId(propertyId);
    }

    public List<SeatEntity> getSeatsBySectionId(Long sectionId) {
        return seatingPlanManagementBeanLocal.getSeatsBySectionId(sectionId);
    }
    
    public PropertyEntity getPropertyById(Long propertyId) {
        return seatingPlanManagementBeanLocal.getPropertyById(propertyId);
    }
    
    public Long getPropertyByName(String name){
        return seatingPlanManagementBeanLocal.getPropertyByName(name);
    }
    
    public Long createNewProperty(CompanyEntity company,Part propertyMain,Part propertyLayout,Part data,String title,Integer capacity,Integer rental,String[] types,String recommend,String ext1,String ext2,String ext3){
        return seatingPlanManagementBeanLocal.CreateNewProperty(company,propertyMain,propertyLayout, data,title, capacity,rental, types, recommend, ext1,ext2,ext3);
    }
     public List<PropertyEntity> getAllPropertiesByCompany(CompanyEntity company) {
        return seatingPlanManagementBeanLocal.getAllPropertiesByCompany(company);
     }
    
//    public Boolean createSectionsUnderProperty(HttpServletRequest request){
//        
//        Integer categories = Integer.valueOf(request.getParameter("categories"));
//        Integer sections = Integer.valueOf(request.getParameter("sections"));
//        Long propertyId = Long.valueOf(request.getParameter("propertyId"));
//        
//            String cName,section,capacity;
//            String[] cSections;
//            ArrayList<String> cNameArr = new ArrayList<String>();
//            ArrayList<String[]> cSectionsArr = new ArrayList<String[]>();
//            ArrayList<String> sectionsArr = new ArrayList<String>();
//            ArrayList<String> capacityArr = new ArrayList<String>();
//            for(int j =1;j<=sections;j++){
//                section = request.getParameter("section"+j);
//                capacity = request.getParameter("capacity"+j);
//                sectionsArr.add(section);
//                capacityArr.add(capacity);
//            }
//            for (int i = 1; i <= categories; i++) {
//                cName = (String) request.getParameter("cname" + i);
//                cSections = request.getParameterValues("csections" + i);
//                cNameArr.add(cName);
//                cSectionsArr.add(cSections);
//               
//            }
//           
//        return seatingPlanManagementBeanLocal.createSectionsUnderProperty(propertyId,cNameArr,cSectionsArr,sectionsArr,capacityArr);
//    }

}
