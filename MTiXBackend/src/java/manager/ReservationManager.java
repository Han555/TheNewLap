/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import entity.CompanyEntity;
import session.stateless.propertymanagement.ReservePropertyBeanLocal;
import session.stateless.propertymanagement.SeatingPlanManagementBeanLocal;
import entity.EquipmentEntity;
import entity.Event;
import entity.ManpowerEntity;
import entity.PropertyEntity;
import entity.SeatEntity;
import entity.SectionEntity;
import entity.SubEvent;
import entity.UserEntity;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import util.exception.ReservationConflictException;

/**
 *
 * @author catherinexiong
 */
public class ReservationManager {

    private final ReservePropertyBeanLocal rpb;

    public ReservationManager(ReservePropertyBeanLocal rpb) {
        this.rpb = rpb;
    }

    public Event addNewEvent(CompanyEntity company, String eventName, String eventDescription, String daterange, Long propertyId, String email, String type) throws ParseException {

        String[] parts = daterange.split(" - ");
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Date startDate = df.parse(parts[0].trim());
        Date endDate = df.parse(parts[1].trim());

        return rpb.addNewEvent(company, eventName, eventDescription, startDate, endDate, propertyId, email, type);

    }

    public SubEvent addNewSubEvent(CompanyEntity company, String name, String daterange, Long propertyId, Long eId, String email, String type) throws ParseException {

        String[] parts = daterange.split(" - ");
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Date startDate = df.parse(parts[0].trim());
        Date endDate = df.parse(parts[1].trim());
        System.out.println("=====Manager " + eId);
        return rpb.addNewSubEvent(company, name, startDate, endDate, propertyId, eId, email, type);

    }

    public Event addNewEventWithSub(CompanyEntity company,String name, String eventDes, Long userId) {
        return rpb.addNewEventWithSub(company,name, eventDes, userId);
    }

    public boolean checkUser(CompanyEntity company, String email) {
        System.out.println("Entered reservation manager");
        return rpb.checkUser(company, email);
    }

    public List<PropertyEntity> getReservationSearchResult(List<PropertyEntity> properties, HttpServletRequest request) throws ParseException {
        String eventcate = request.getParameter("eventcate");
        String eventScale = request.getParameter("eventscale");

        return rpb.getReservationSearchResult(properties, eventcate, eventScale);
    }

    public List<PropertyEntity> getAvailableProperties(CompanyEntity company, HttpServletRequest request) throws ParseException {
        String eventcate = request.getParameter("eventcate");
        String eventScale = request.getParameter("eventscale");
        String daterange = request.getParameter("daterange");

        return rpb.getAvailableProperties(company, eventcate, eventScale, daterange);
    }

    public List<PropertyEntity> checkRecommendation(List<PropertyEntity> properties, HttpServletRequest request) {
        String eventcate = request.getParameter("eventcate");
        return rpb.checkRecommendation(properties, eventcate);
    }

    public List<Event> getEventReservationByProperty(Long propertyId) {
        return rpb.getEventReservationByProperty(propertyId);
    }

    public List<SubEvent> getSubEventReservationByProperty(Long propertyId) {
        return rpb.getSubEventReservationByProperty(propertyId);
    }

    public List<EquipmentEntity> saveEquipmentSub(String[] evalues, String pidStr, String seidStr) {
        Long pid = Long.valueOf(pidStr);
        Long seid = Long.valueOf(seidStr);
        return rpb.saveEquipmentSub(evalues, pid, seid);
    }

    public List<ManpowerEntity> saveManpowerSub(String[] evalues, String pidStr, String seidStr) {
        Long pid = Long.valueOf(pidStr);
        Long seid = Long.valueOf(seidStr);
        return rpb.saveManpowerSub(evalues, pid, seid);
    }

    public List<EquipmentEntity> saveEquipmentEvent(String[] evalues, String pidStr, String eidStr) {
        Long pid = Long.valueOf(pidStr);
        Long eid = Long.valueOf(eidStr);
        return rpb.saveEquipmentEvent(evalues, pid, eid);
    }

    public List<ManpowerEntity> saveManpowerEvent(String[] evalues, String pidStr, String eidStr) {
        Long pid = Long.valueOf(pidStr);
        Long eid = Long.valueOf(eidStr);
        return rpb.saveManpowerEvent(evalues, pid, eid);
    }

    public Boolean deleteEventReservationById(Long eId) {

        return rpb.deleteEventReservationById(eId);
    }

    public Boolean deleteSubEventReservationById(Long eId) {

        return rpb.deleteSubEventReservationById(eId);
    }

    public Boolean editEventReservation(Long eId, String name, String des) {
        return rpb.editEventReservation(eId, name, des);
    }

    public Boolean editSubEventReservation(Long eId, String name) {
        return rpb.editSubEventReservation(eId, name);
    }

    public SubEvent getSubEventById(Long id) {
        return rpb.getSubEventById(id);
    }

    public Event getEventById(Long id) {
        return rpb.getEventById(id);
    }

    public List<SubEvent> getListOfSubEvent(Event event) {
        return rpb.getListOfSubEvent(event);
    }

    public List<UserEntity> getEventOrganizersInOneComany(CompanyEntity company) {
        return rpb.getEventOrganizersInOneComany(company);
    }
    
    public List<Event> getAllEventsWithSubEvents(CompanyEntity company){
        return rpb.getAllEventsWithSubEvents(company);
    }
    }
