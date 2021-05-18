package com.example.LaboBiochimie.ServiceImplementation;

import com.example.LaboBiochimie.Entities.*;
import com.example.LaboBiochimie.Repository.AdminRepository;
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
    @Autowired
    AdminRepository adminRepository;
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
    public int tempPrelevement(Patient p){
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
    public int calculerTempsRestant(Date jour, int numBox){
        int t=0;
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
    public int NombrePatientParBox(int numBox, Date date){
        int cpt=0;
        for (RendezVous rdv:rendez_vousRepository.findAll()){
            if (rdv.getNumero_box()==numBox && convertToDateViaInstant(rdv.getDate_heure_RDV()).equals(date))
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

    @Override
    public void PersonnelPrendRDV(Personnel personnel){

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
    //Chercher les boxs qui sont disponibles par jours
    public ArrayList<Box> ChercherBoxDispoParDate(Date jour){
        ArrayList<Box> L = new ArrayList<Box>();
        int i=0;
        Box box=new Box();
        for (i=0; i<4; i++){
            if (NombrePatientParBox(i+1,jour)<40) {
                box.setNombrePatient(NombrePatientParBox(i + 1, jour));
                box.setNumeroBox(i+1);
                box.setTempRestant(calculerTempsRestant(jour,i+1));
                box.afficherBox();
                L.add(box);
            }
        }
        return L;
    }
    //Retourne le numéro de box ayant le minimum de patient
    public int numBoxMinPatient(Date jour){
        ArrayList<Box> T=ChercherBoxDispoParDate(jour);
        int pos=0;
        if (!T.isEmpty())
        {
            System.out.print("Pour la date "+jour+": ");
            int min= T.get(0).getNombrePatient();
            for (int i=1;i<T.size();i++)
            {
                Box box=T.get(i);
                if (box.getNombrePatient()<min && box.getTempRestant()>0)
                {
                    min=box.getNombrePatient();
                    pos=box.getNumeroBox();
                }
            }
        }
        System.out.print(pos);
        return pos;
    }
    ///Calculer le temps de prélèvement du dernier patient d'un box donné
    public int TempPrelDernierPatiBox(int numBox){
        Patient patient=new Patient();
        for(RendezVous rdv: rendez_vousRepository.findAll())
        {
            if (rdv.getNumero_box()==numBox)
            {
                patient=rdv.getRDVPatient();
            }
        }
        return tempPrelevement(patient);
    }
    //Récupérer la date et l'heure du dernier rdv d'un box donné
    public LocalDateTime DernierHeurRdvBox(int numBox){
        LocalDateTime heureRdv=LocalDateTime.now();
        for(RendezVous rdv: rendez_vousRepository.findAll())
        {
            if (rdv.getNumero_box()==numBox)
            {
                heureRdv=rdv.getDate_heure_RDV();
            }
        }
        return heureRdv;
    }
    //Ajouter un nouveau rendez-vous à un patient p
    public RendezVous ajoutRdv(Patient p){
        int numBox=(rendez_vousRepository.findAll().get(rendez_vousRepository.findAll().size()-1).getNumero_box()+1)%4;
        if (numBox == 0) {
            numBox = 4;
        }
        RendezVous rendezVous=new RendezVous();
        rendezVous.setNumero_box(numBox);
        rendezVous.setDate_heure_RDV(DernierHeurRdvBox(numBox).plusMinutes(TempPrelDernierPatiBox(numBox)));
        if (rendezVous.getDate_heure_RDV().getHour()>14)
        {
            rendezVous.setDate_heure_RDV(rendezVous.getDate_heure_RDV().plusDays(1).withHour(8));
            rendezVous.setNumero_box(1);
        }
        rendezVous.setRDVPatient(p);
        return rendezVous;
    }
    @Override
    public RendezVous PatientPrendRDV(Patient patient){
        RendezVous rdv=new RendezVous();
        return rdv;
    }

    @Override
    public RendezVous PatientPrendRDVById(Long id){
        RendezVous rdv = new RendezVous();
        Patient patient=patientRepository.findById(id).get();
        Date date=new Date();
        Long tempsRestant;
        int tempsPrelevement=tempPrelevement(patient);
        int i=0;
        //ArrayList<Long> tempsRestant=new ArrayList();
        rdv=ajoutRdv(patient);
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
    public RendezVous AdminPrendRDVById(Long Id){
        RendezVous rdv = new RendezVous();
        /*
        Admin admin=adminRepository.findById(Id).get();
        Date date=new Date();
        Long tempsRestant;
        int tempsPrelevement=tempPrelevement(admin);
        int i=0;
        //ArrayList<Long> tempsRestant=new ArrayList();
        rdv=ajoutRdv(admin);
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
        rdv.setRDVPatient(admin);
        rendez_vousRepository.save(rdv);
         */
        return rdv;
    }
}
