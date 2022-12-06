/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patientUtilities;

import java.rmi.NotBoundException;

import java.security.*;
import java.util.*;
import java.sql.Date;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import pojos.Doctor;
import pojos.Patient;
import pojos.Signal;
import pojos.User;

public class Menu {

    public static InputStream console = (System.in);
    public static Socket socket;
    public static InputStream inputStream;
    public static OutputStream outputStream;
    public static PrintWriter pw;
    public static BufferedReader br;
    public static Patient patient;

    public static void initiliazeStreams(String IPAddress) throws IOException {
        socket = patientUtilities.CommunicationWithServer.connectToServer(IPAddress);
        inputStream = socket.getInputStream();
        outputStream = socket.getOutputStream();
        pw = new PrintWriter(outputStream, true);
        br = new BufferedReader(new InputStreamReader(inputStream));
        pw.println(2);

    }

   
    public static String registerPatient(Integer medical_card_number, String name, String surname, java.util.Date dob, String address, String email, String diagnosis, String allergies, String gender, String macAddress) throws Exception {
        pw.println(1);
        boolean registerCorrect = false;
        Patient p = new Patient();
        p.setName(name);
        p.setSurname(surname);
        p.setMedical_card_number(medical_card_number);
        p.setDob(dob);
        p.setAddress(address);
        p.setEmail(email);
        p.setDiagnosis(diagnosis);
        p.setAllergies(allergies);
        p.setGender(gender);
        p.setMacAddress(macAddress);

        patientUtilities.CommunicationWithServer.sendPatient(pw, p);
        User user = patientUtilities.CommunicationWithServer.receiveUser(br);
        String userPassword = user.getPassword();
        String userName = user.getUsername();
        String userpass = "Username: " + userName + "\n Password: " + userPassword;

        String line = br.readLine();
        if (line.equals("Patient successfully registered")) {
            registerCorrect = true;
            return userpass;
        } else {
            registerCorrect = false;
            return null;
        }
    }
    

    public static List<String> showDoctors() throws IOException {
        List<String> doctors = new ArrayList();
        int size = Integer.parseInt(br.readLine());
        for (int i = 0; i < size; i++) {
            doctors.add(i, br.readLine() + "\n");
        }
        return doctors;
    }
    
        
    public static void sendDoctorId(int doctorid) {
        pw.println(doctorid);
    }
    
    public static boolean login(String username, String password) throws Exception {
        pw.println(2);
        boolean logInCorrect = false;
        User user = new User();
        user.setPassword(password);
        user.setUsername(username);
        patientUtilities.CommunicationWithServer.sendUser(pw, user);
        String line = br.readLine();
        if (line.equals("Wrong username or password")) {
            logInCorrect = false;
            pw.println(2);
        } else if (line.equals("patient")) {
            logInCorrect = true;
            pw.println(0);
            patient = patientUtilities.CommunicationWithServer.receivePatient(br);
         } else if (line.equals("doctor")) {
            logInCorrect = false;
            pw.println(1);
            pw.println(2);           
        }
        return logInCorrect;
    }
    
    public static void recordSignal() {
        pw.println(1);
        patientUtilities.CommunicationWithServer.recordSignal(patient, pw);
    }
    
    public static List<String> showSignalList() {
        pw.println(3);
        List<String> sList = patientUtilities.CommunicationWithServer.ShowSignals(br, pw);

        return sList;
    }
    
    public static List<Integer> showSignal(String filename) throws IOException{
        Signal s;
        pw.println(filename);
        if (filename.contains("ECG")){
            s= CommunicationWithServer.receiveECGSignal(br);
            return s.getECG_values();
        }else{
            s=CommunicationWithServer.receiveEMGSignal(br);
            return s.getEMG_values();
        }
    }

    
    public static void updateMacAddress(String macAddress) throws Exception {
        pw.println(4);
        patient.setMacAddress(macAddress);
        patientUtilities.CommunicationWithServer.sendPatient(pw, patient);
    }

    public static void backTologin() {
        pw.println(2);
    }
    
    public static void backToMenu(){
        pw.println();
    }
    
     public static void goToregister() {
        pw.println(1);
    }

    public static void exit() {
        pw.println(0);
        pw.println(0);
        patientUtilities.CommunicationWithServer.ReleaseResources(pw, br);
        patientUtilities.CommunicationWithServer.exitFromServer(inputStream, outputStream, socket);
        System.exit(0);
    }
}
