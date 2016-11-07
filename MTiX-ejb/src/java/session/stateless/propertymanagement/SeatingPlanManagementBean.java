package session.stateless.propertymanagement;

import entity.CompanyEntity;
import entity.PropertyEntity;
import entity.SeatEntity;
import entity.SectionCategoryEntity;
import entity.SectionEntity;
import entity.WebContentEntity;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.Part;

/**
 *
 * @author catherinexiong
 */
@Stateless
public class SeatingPlanManagementBean implements SeatingPlanManagementBeanLocal {

    @PersistenceContext
    private EntityManager em;

    public SeatingPlanManagementBean() {

    }

    @Override
    public void CreateProperty() {
        String name;
        Integer num;
        Integer capacity;
        try {
            PropertyEntity property = new PropertyEntity();
            property.createProperty("Merlion Concert Hall", 1, 2000);
            em.persist(property);
            em.flush();
            name = property.getPropertyName();
            capacity = property.getCapacity();
            Long propertyID = property.getId();
            System.out.println("Property Name \"" + name + "\" registered successfully as id:" + propertyID + "\" with capcity of:" + capacity);

            property.createProperty("Merlion Theater", 2, 2000);
            em.persist(property);
            em.flush();
            name = property.getPropertyName();
            capacity = property.getCapacity();
            propertyID = property.getId();
            System.out.println("Property Name \"" + name + "\" registered successfully as id:" + propertyID + "\" with capcity of:" + capacity);

            property.createProperty("Merlion Outdoor Stadium", 3, 80000);
            em.persist(property);
            em.flush();
            name = property.getPropertyName();
            capacity = property.getCapacity();
            propertyID = property.getId();
            System.out.println("Property Name \"" + name + "\" registered successfully as id:" + propertyID + "\" with capcity of:" + capacity);

            em.detach(property);

        } catch (Exception ex) {
            System.out.println("\nServer failed to add new property:\n" + ex);

        }

    }

    public Long CreateNewProperty(CompanyEntity company, Part propertyMain, Part propertyLayout, Part data, String title, Integer capacity, Integer rental, String[] types, String recommend, String ext1, String ext2, String ext3) {
        try {

            String type = types[0];
            for (int i = 1; i < types.length; i++) {
                type = type.concat("," + types[i]);

            }
            String fileName1 = "";
            String fileName2 = "";
            String fileName3 = "";
            Integer no1 = 0;
            Integer no2 = 0;

            fileName1 = company.getCompanyName() + "_" + title + "_main";
            fileName2 = company.getCompanyName() + "_" + title + "_layout";
            fileName3 = company.getCompanyName() + "_" + title + "_data";
            List<PropertyEntity> ps = getAllPropertiesByCompany(company);
            if (ps == null) {
                no1 = 0;

            } else {
                for (PropertyEntity p : ps) {
                    no2 = p.getPropertyNo();
                    if (no1 < no2) {
                        no1 = no2;
                    }
                }

            }
            PropertyEntity property = new PropertyEntity();
            property.setPropertyNo(no1 + 1);
            property.setCapacity(capacity);
            property.setPropertyName(title);
            property.setRecommend(recommend);
            property.setCompany(company);
            property.setRental(Double.valueOf(rental));

            property.setTypes(type);
            property.setMainFileName(fileName1 + "." + ext1);
            property.setLayoutFileName(fileName2 + "." + ext2);
            em.persist(property);
            em.flush();
            //Store the file into system
            OutputStream out1 = null;
            InputStream filecontent1 = null;

            out1 = new FileOutputStream(new File("C:/Users/Yong Jing Ying/Desktop/contentManagement" + File.separator
                    + fileName1 + "." + ext1));
            filecontent1 = propertyMain.getInputStream();

            int read1 = 0;
            final byte[] bytes1 = new byte[1024];

            while ((read1 = filecontent1.read(bytes1)) != -1) {
                out1.write(bytes1, 0, read1);
            }

            out1.close();
            filecontent1.close();
            OutputStream out2 = null;
            InputStream filecontent2 = null;

            out2 = new FileOutputStream(new File("C:/Users/Yong Jing Ying/Desktop/contentManagement" + File.separator
                    + fileName2 + "." + ext2));
            filecontent2 = propertyLayout.getInputStream();

            int read2 = 0;
            final byte[] bytes2 = new byte[1024];

            while ((read2 = filecontent2.read(bytes2)) != -1) {
                out2.write(bytes2, 0, read2);
            }

            out2.close();
            filecontent2.close();
            OutputStream out3 = null;
            InputStream filecontent3 = null;

            out3 = new FileOutputStream(new File("C:/Users/Yong Jing Ying/Desktop/contentManagement" + File.separator
                    + fileName3 + "." + ext3));
            filecontent3 = data.getInputStream();

            int read3 = 0;
            final byte[] bytes3 = new byte[1024];

            while ((read3 = filecontent3.read(bytes3)) != -1) {
                out3.write(bytes3, 0, read3);
            }

            out3.close();
            filecontent3.close();
            return property.getId();

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

//    public Boolean createSectionsUnderProperty(Long propertyId, ArrayList<String> cNameArr, ArrayList<String[]> cSectionsArr, ArrayList<String> sectionsArr, ArrayList<String> capacity) {
//
//        PropertyEntity property = em.find(PropertyEntity.class, propertyId);
//        List<SectionCategoryEntity> sectionCategories = new ArrayList<SectionCategoryEntity>();
//        for (int i = 1; i <= cNameArr.size(); i++) {
//            SectionCategoryEntity category = new SectionCategoryEntity();
//                category.setCategoryName(cNameArr.get(i));
//                category.setProperty(property);
//                category.setCategoryNum(i);
//                em.persist(category);
//                em.flush();
//                sectionCategories.add(category);
//        }
//        for (int j =1;j<=sectionsArr.size();j++){
//            SectionEntity section = new SectionEntity();
//            section.setNumberInProperty(j);
//            section.setCoords(sectionsArr.get(j));
//            section.setSeatMap(seatMap);
//            for(int m=1;m<=cSectionsArr.size();m++){
//                String[] cSection = cSectionsArr.get(m);
//                for(int n=1;n<=cSection.length;n++){
//                    
//                    if(j==Integer.valueOf(cSection[n])){
//                        section.setCategory(sectionCategories.get(m));
//                        
//                    }
//                }
//            }
//           section.setCapacity(Integer.valueOf(capacity.get(j)));
//    }
//
//}
    /**
     * *************************************************************
     * Get Property, Section, Seat By Id
     * *************************************************************
     */
    @Override
    public PropertyEntity
            getPropertyById(Long propertyId) {
        PropertyEntity property = em.find(PropertyEntity.class, propertyId);
        return property;
    }

    @Override
    public SectionEntity
            getSectionById(Long sectionId) {
        SectionEntity section = em.find(SectionEntity.class, sectionId);
        return section;
    }

    @Override
    public SectionCategoryEntity getCategoryById(Long categoryId) {
        SectionCategoryEntity category = em.find(SectionCategoryEntity.class, categoryId);
        return category;
    }

    /**
     * ***********************************************************
     * Get All Property, Section, Seat (List)
     * ***********************************************************
     */
    @Override
    public List<PropertyEntity> getAllProperties() {

        Query query = em.createQuery("SELECT p FROM PropertyEntity p");
        return query.getResultList();
    }

    @Override
    public List<PropertyEntity> getAllPropertiesByCompany(CompanyEntity company) {

        Query query = em.createQuery("SELECT p FROM PropertyEntity p WHERE p.company=:inCompany");
        query.setParameter("inCompany", company);
        return query.getResultList();
    }

    @Override
    public List<SectionCategoryEntity> getAllCategories() {

        Query query = em.createQuery("SELECT sc FROM SectionCategoryEntity sc");
        return query.getResultList();
    }
    /*
     @Override
     public List<SectionEntity> getAllSections(Long propertyId) {
     Property property = getPropertyById(propertyId);
     Query query = em.createQuery("SELECT se FROM SectionEntity se WHERE se.property = :inProperty");
     query.setParameter("inProperty", property);
     return query.getResultList();
     }
     */

    @Override
    public List<SeatEntity> getAllSeatsInOneSection(Long sectionId) {
        SectionEntity section = getSectionById(sectionId);
        Query query = em.createQuery("SELECT s FROM SeatEntity s WHERE s.section = :inSection");
        query.setParameter("inSection", section);
        return query.getResultList();
    }

    @Override
    public List<SectionEntity> getAllSectionsInOneProperty(Long propertyId) {
        PropertyEntity property = getPropertyById(propertyId);
        Query query = em.createQuery("SELECT se FROM SectionEntity se WHERE se.property = :inProperty ORDER BY se.numberInProperty");
        query.setParameter("inProperty", property);
        return query.getResultList();
    }

    @Override
    public List<SectionCategoryEntity> getAllCategoryInOneProperty(Long propertyId) {

        PropertyEntity property = getPropertyById(propertyId);
        Query query = em.createQuery("SELECT sc FROM SectionCategoryEntity sc WHERE sc.property = :inProperty");

        query.setParameter("inProperty", property);
        return query.getResultList();
    }

    @Override
    public List<SectionEntity> getAllSectionsInOneCategory(Long categoryId) {
        SectionCategoryEntity category = getCategoryById(categoryId);
        Query query = em.createQuery("SELECT se FROM SectionEntity se WHERE se.category = :inCategory");
        query.setParameter("inCategory", category);
        return query.getResultList();
    }

    /**
     * *************************************************************
     * Link Property, Section, Seat
     *
     * ************************************************************* @return
     */
    @Override
    public Boolean linkSeatsToSection() {
        try {
            for (int i = 0; i <= 26; i++) {
                List<SeatEntity> seats = getAllSeatsInOneSection(Long.valueOf(i + 1));

                SectionEntity section = getSectionById(Long.valueOf(i + 1));
                section.setSeats(seats);
                em.merge(section);

            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<SeatEntity> getSeatsBySectionId(Long sectionId) {
        SectionEntity section = getSectionById(sectionId);
        return section.getSeats();

    }

    @Override
    public Boolean linkSectionsToProperty() {
        try {

            List<SectionEntity> sections = getAllSectionsInOneProperty(Long.valueOf(1));

            PropertyEntity property = getPropertyById(Long.valueOf(1));
            property.setSections(sections);
            em.merge(property);

            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public Boolean linkCategoryToProperty() {
        try {

            List<SectionCategoryEntity> category = getAllCategoryInOneProperty(Long.valueOf(1));

            PropertyEntity property = getPropertyById(Long.valueOf(1));
            property.setCategory(category);
            em.merge(property);

            return true;
        } catch (Exception e) {
            return false;
        }

    }

//    @Override
//    public Boolean linkSectionsToCategory() {
//        try {
//            List<SectionCategory> category = new ArrayList();
//            category = getAllCategories();
//            
//            for(SectionCategoryEntity sc:category){
//                List<SectionEntity> sections = getAllSectionsInOneCategory(sc.getId());
//                sc.setSection(sections);
//                em.merge(sc);
//                
//            }
//
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//       
//    }
//    
    @Override
    public List<SectionEntity> getSectionsByPropertyId(Long propertyId) {
        PropertyEntity property = getPropertyById(propertyId);
        return property.getSections();

    }

    @Override
    public Long getPropertyByName(String name) {
        Query query = em.createQuery("SELECT p FROM PropertyEntity p WHERE p.propertyName = :inName");
        query.setParameter("inName", name);
        if (query.getSingleResult() != null) {
            PropertyEntity property = (PropertyEntity) query.getSingleResult();
            return property.getId();
        } else {
            return null;
        }
    }
    
    @Override
    public SectionEntity getSectionEntityById(Long id){
        SectionEntity section = em.find(SectionEntity.class, id);
        return section;
    }

    //public List<ArrayList> getSectionStatusBySession(Long sessionId);
    /**
     * *************************************************************
     *
     * *************************************************************
     */
}
