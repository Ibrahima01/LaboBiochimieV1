package com.example.LaboBiochimie.ServiceImplementation;

import com.example.LaboBiochimie.Entities.Admin;
import com.example.LaboBiochimie.Entities.Patient;
import com.example.LaboBiochimie.Entities.Personnel;
import com.example.LaboBiochimie.Entities.RendezVous;
import com.example.LaboBiochimie.Repository.PatientRepository;
import com.example.LaboBiochimie.Repository.RendezVousRepository;
import com.example.LaboBiochimie.Service.RendezVousService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RendezVousServiceImpl implements RendezVousService {
    @Autowired
    RendezVousRepository rendez_vousRepository;
    @Autowired
    PatientRepository patientRepository;
    @Override
    public void SaveRDV(RendezVous rendez_vous){
        rendez_vousRepository.save(rendez_vous);
    }
    @Override
    public void UpdateRDV(Long Id, RendezVous rendez_vous){
        Optional<RendezVous> rendez_vous1=rendez_vousRepository.findById(Id);
        if (rendez_vous1.isEmpty())
        {
            System.out.println("Id inexistant");
        }
        else {
            RendezVous newRDV = rendez_vous1.get();
            newRDV.setDate_heure_RDV(rendez_vous.getDate_heure_RDV());
            newRDV.setNumero_box(rendez_vous.getNumero_box());
            rendez_vousRepository.save(newRDV);
        }
    }
    @Override
    public List<RendezVous> ListRDV (){
        return rendez_vousRepository.findAll();
    }
    @Override
    public void RemoveRDV(Long Id){
        rendez_vousRepository.deleteById(Id);
    }
    @Override
    public Optional<RendezVous> findRDV(Long Id){
        return rendez_vousRepository.findById(Id);
    }
    @Override
    public List<RendezVous> findByDate(LocalDateTime date){
        return rendez_vousRepository.findAll().stream().filter(x->x.getDate_heure_RDV().toLocalDate().isEqual(date.toLocalDate())).collect(Collectors.toList());
    }

    private Date convertToDateViaInstant(LocalDateTime dateToConvert) {
        return java.util.Date
                .from(dateToConvert.atZone(ZoneId.systemDefault())
                        .toInstant());
    }
    public LocalDateTime convertToLocalDateTime(Date dateToConvert) {
        return LocalDateTime.ofInstant(
                dateToConvert.toInstant(), ZoneId.systemDefault());
    }
    public LocalDateTime reglerHeure(LocalDateTime t){
       t.withHour(8);
       return t;
    }
    public LocalDateTime convertirMinuteEnHeureRDV(Date j, Long minutes){
        int H= (int) (minutes/60);
        int MN= (int) (minutes%60);
        LocalDateTime t= reglerHeure(convertToLocalDateTime(j)).plusHours(H).withMinute(MN);
        return t;
    }
    public LocalDateTime calculDateHeureRDV(Date date, Long time){
        return convertToLocalDateTime(date);
    }
    public long tempPrelevement(Patient p){
        if (p.getAge()<=2)
            return 15;
        else {
            if (p.getAge()<=12)
                return 10;
            else {
                if (p.getSexe().equals("Feminin") && p.isFemme_enceinte())
                    return 192;
                else {
                    if (p.isObese())
                        return 5;
                    else
                        return 3;
                }
            }
        }

    }
    public Long calculerTempsRestant(Date jour, int numBox){
        long t=0;
        for (RendezVous rdv:rendez_vousRepository.findAll()){
            if (jour.equals(convertToDateViaInstant(rdv.getDate_heure_RDV())) && numBox==rdv.getNumero_box()){
                for (Patient p:patientRepository.findAll()){
                    if (rdv.getRDVPatient().getId()==p.getId())
                    {
                        t=t+tempPrelevement(p);
                    }
                }
            }
        }
        t=360-t;
        return t;
    }
    public static ArrayList<Integer> TableauInfirmierAdmissible= new ArrayList<>(10);
    public static Hashtable<Date,Long> dates=new Hashtable<Date, Long>();
    public int NombrePatientParBox(int numBox, LocalDateTime date){
        int cpt=0;
        for (RendezVous rdv:rendez_vousRepository.findAll()){
            if (rdv.getNumero_box()==numBox && convertToDateViaInstant(rdv.getDate_heure_RDV()).equals(convertToDateViaInstant(date)))
                cpt++;
        }
        return cpt;
    }
    public int PositionBoxMinPatient(ArrayList <Integer> ListeBox){
        int min=0;
        for (int j:ListeBox){
            if (ListeBox.get(j)<ListeBox.get(min))
                min=j;
        }
        return min;
    }
    public void Equite(RendezVous rdv){
        //boolean equite;
        TableauInfirmierAdmissible.clear();
        TableauInfirmierAdmissible.add(NombrePatientParBox(1, rdv.getDate_heure_RDV()));
        TableauInfirmierAdmissible.add(NombrePatientParBox(2, rdv.getDate_heure_RDV()));
        TableauInfirmierAdmissible.add(NombrePatientParBox(3, rdv.getDate_heure_RDV()));
        TableauInfirmierAdmissible.add(NombrePatientParBox(4, rdv.getDate_heure_RDV()));
        int min=PositionBoxMinPatient(TableauInfirmierAdmissible);
        if (TableauInfirmierAdmissible.get(min)<40) {
            rdv.setNumero_box(min+1);
        }
        else
        {
            rdv.setDate_heure_RDV(rdv.getDate_heure_RDV().plusDays(1));
            rdv.setNumero_box(1);
            TableauInfirmierAdmissible.clear();
        }
    }
    @Override
    public RendezVous PatientPrendRDV(Patient patient){
        RendezVous rdv=new RendezVous();
        Date date=new Date();
        Long tempsRestant;
        Long tempsPrelevement=tempPrelevement(patient);
        int i=0;
        //ArrayList<Long> tempsRestant=new ArrayList();
        for (RendezVous ldt: rendez_vousRepository.findAll()){
            date=convertToDateViaInstant(ldt.getDate_heure_RDV());
            TableauInfirmierAdmissible.clear();
            TableauInfirmierAdmissible.add(NombrePatientParBox(1, ldt.getDate_heure_RDV()));
            TableauInfirmierAdmissible.add(NombrePatientParBox(2, ldt.getDate_heure_RDV()));
            TableauInfirmierAdmissible.add(NombrePatientParBox(3, ldt.getDate_heure_RDV()));
            TableauInfirmierAdmissible.add(NombrePatientParBox(4, ldt.getDate_heure_RDV()));
            int min=PositionBoxMinPatient(TableauInfirmierAdmissible);
            if (TableauInfirmierAdmissible.get(min)<40) {
                rdv.setDate_heure_RDV(ldt.getDate_heure_RDV().plusMinutes(tempsPrelevement));
                rdv.setNumero_box(min+1);
                break;
            }
            else
            {
                rdv.setDate_heure_RDV(rdv.getDate_heure_RDV().plusDays(1));
                rdv.setNumero_box(1);
                //TableauInfirmierAdmissible.clear();
            }
        }
        if (i==rendez_vousRepository.findAll().size()){
            date=convertToDateViaInstant(convertToLocalDateTime(date).plusDays(1));
            //date = date.getTime()+(1000 * 60 * 60 * 24);
        }
        //Equite(rdv);
        GregorianCalendar calendar=new GregorianCalendar();
        calendar.setTime(java.util.Date.from(rdv.getDate_heure_RDV().atZone(ZoneId.systemDefault()).toInstant()));
        int today = calendar.get(calendar.DAY_OF_WEEK);
        int indexOfToday = 0;
        switch (today) {
            case GregorianCalendar.MONDAY:
                indexOfToday = 1;
                break;
            case GregorianCalendar.TUESDAY:
                indexOfToday = 2;
                break;
            case GregorianCalendar.WEDNESDAY:
                indexOfToday = 3;
                break;
            case GregorianCalendar.THURSDAY:
                indexOfToday = 4;
                break;
            case GregorianCalendar.FRIDAY:
                indexOfToday = 5;
                break;
            case GregorianCalendar.SATURDAY:
                indexOfToday = 6;
                break;
            case GregorianCalendar.SUNDAY:
                indexOfToday = 7;
                break;
        }
        if (indexOfToday==6)
        {
            rdv.setDate_heure_RDV(rdv.getDate_heure_RDV().plusDays(2));
        }
        else if (indexOfToday==7){
            rdv.setDate_heure_RDV(rdv.getDate_heure_RDV().plusDays(1));
        }
        rdv.setRDVPatient(patient);
        rendez_vousRepository.save(rdv);
        return rdv;
    }
    @Override
    public void PersonnelPrendRDV(Personnel personnel){

    }
    @Override
    public void AdminPrendRDV(Admin admin){

    }
    @Override
    public List<LocalDateTime> findRDVByIdPatient(Long Id){
        List<RendezVous> list=rendez_vousRepository.findAll();
        List<LocalDateTime> list2=new ArrayList<LocalDateTime>();
        for (RendezVous rdv: list){
            if(rdv.getRDVPatient().getId()==Id){
                list2.add(rdv.getDate_heure_RDV());
            }
        }
        return list2;
    }
    @Override
    public RendezVous PatientPrendRDVById(Long id){
        RendezVous rdv = new RendezVous();
        /*
        if (patientRepository.findById(id).isEmpty()){
            //System.out.println("Patient inexistant");
            return rdv;
        }
        else {
            int numBox=(rendez_vousRepository.findAll().get(rendez_vousRepository.findAll().size()-1).getNumero_box()+1)%4;
            if (numBox == 0) {
                numBox = 4;
            }
            rdv.setNumero_box(numBox);
            rdv.setDate_heure_RDV(LocalDateTime.now().plusDays(5));
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(java.util.Date.from(rdv.getDate_heure_RDV().atZone(ZoneId.systemDefault()).toInstant()));
            Patient p = patientRepository.findById(id).get();//.get(0);;
            rdv.setRDVPatient(p);
        }
        rendez_vousRepository.save(rdv);
        return rdv;
         */
        Patient patient=patientRepository.findById(id).get();
        Date date=new Date();
        Long tempsRestant;
        Long tempsPrelevement=tempPrelevement(patient);
        int i=0;
        //ArrayList<Long> tempsRestant=new ArrayList();
        for (RendezVous ldt: rendez_vousRepository.findAll()){
            date=convertToDateViaInstant(ldt.getDate_heure_RDV());
            for (int i1:TableauInfirmierAdmissible) {
                tempsRestant = calculerTempsRestant(date, i1);
                if (tempsRestant - tempsPrelevement > 0) {
                    dates.put(date, tempsRestant - tempPrelevement(patient));
                    rdv.setDate_heure_RDV(convertirMinuteEnHeureRDV(date, 1440 - tempsRestant));
                    break;
                } else
                    i++;
            }
        }
        if (i==rendez_vousRepository.findAll().size()){
            date=convertToDateViaInstant(convertToLocalDateTime(date).plusDays(1));
            //date = date.getTime()+(1000 * 60 * 60 * 24);
            rdv.setDate_heure_RDV(convertirMinuteEnHeureRDV(date, 1440-tempsPrelevement));
        }
        Equite(rdv);
        GregorianCalendar calendar=new GregorianCalendar();
        calendar.setTime(java.util.Date.from(rdv.getDate_heure_RDV().atZone(ZoneId.systemDefault()).toInstant()));
        int today = calendar.get(calendar.DAY_OF_WEEK);
        int indexOfToday = 0;
        switch (today) {
            case GregorianCalendar.MONDAY:
                indexOfToday = 1;
                break;
            case GregorianCalendar.TUESDAY:
                indexOfToday = 2;
                break;
            case GregorianCalendar.WEDNESDAY:
                indexOfToday = 3;
                break;
            case GregorianCalendar.THURSDAY:
                indexOfToday = 4;
                break;
            case GregorianCalendar.FRIDAY:
                indexOfToday = 5;
                break;
            case GregorianCalendar.SATURDAY:
                indexOfToday = 6;
                break;
            case GregorianCalendar.SUNDAY:
                indexOfToday = 7;
                break;
        }
        if (indexOfToday==6)
        {
            rdv.setDate_heure_RDV(rdv.getDate_heure_RDV().plusDays(2));
        }
        else if (indexOfToday==7){
            rdv.setDate_heure_RDV(rdv.getDate_heure_RDV().plusDays(1));
        }
        rdv.setRDVPatient(patient);
        rendez_vousRepository.save(rdv);
        return rdv;
    }
}
