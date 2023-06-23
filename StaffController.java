package Controller;

import Model.*;
import java.util.ArrayList;

public class StaffController {
    private ArrayList<Staff> listStaff;

    public StaffController() {
    }

    public StaffController(ArrayList<Staff> listStaff) {
        this.listStaff = listStaff;
    }

    public String getTotalTeachingHoursByNIK(String NIK) {
        int totalHours = 0;
        Dosen dosenTemp = null;

        for (Staff staff : listStaff) {
            if (staff instanceof Dosen) {
                Dosen dosen = (Dosen) staff;
                if (dosen.getNIK().equals(NIK)) {
                    dosenTemp = dosen;
                    break; // Exit the loop once the matching NIK is found
                }
            }
        }

        if (dosenTemp != null) {
            for (MatkulAjar matkulAjar : dosenTemp.getMatkulAjar()) {
                for (PresensiStaff presensi : matkulAjar.getListPresensi()) {
                    if (presensi.getStatus() == 1) {
                        totalHours += matkulAjar.getSks(); // Use matkulAjar.getSks() to get SKS value
                    }
                }
            }
            return "Total jam mengajar dosen dengan NIK " + NIK + ": " + totalHours + " jam.";
        } else {
            return "Dosen dengan NIK " + NIK + " tidak ditemukan.";
        }
    }

    public String calculateSalary(String NIK) {
        int unit = 0;
        double salary = -1;

        for (Staff staff : listStaff) {
            if (staff.getNIK().equals(NIK)) {
                if (staff instanceof Karyawan) {
                    Karyawan karyawan = (Karyawan) staff;
                    for (PresensiStaff presensi : karyawan.getListPresensi()) {
                        if (presensi.getStatus() == 1) {
                            unit++;
                        }
                    }
                    salary = unit * karyawan.getSalary() / 22;
                } else if (staff instanceof DosenTetap) {
                    DosenTetap dosenTetap = (DosenTetap) staff; // Corrected variable name
                    for (MatkulAjar matkulAjar : dosenTetap.getMatkulAjar()) {
                        for (PresensiStaff presensi : matkulAjar.getListPresensi()) {
                            if (presensi.getStatus() == 1) {
                                unit++;
                            }
                        }
                    }
                    salary = dosenTetap.getSalary() + (unit * 25000);
                } else if (staff instanceof DosenHonorer) {
                    DosenHonorer dosenHonorer = (DosenHonorer) staff; // Corrected variable name
                    for (MatkulAjar matkulAjar : dosenHonorer.getMatkulAjar()) {
                        for (PresensiStaff presensi : matkulAjar.getListPresensi()) {
                            if (presensi.getStatus() == 1) {
                                unit++;
                            }
                        }
                    }
                    salary = unit * dosenHonorer.getSalary();
                }
            }
        }

        if (salary != -1) {
            return "Gaji staff dengan NIK " + NIK + ": " + salary;
        } else {
            return "Staff dengan NIK " + NIK + " tidak ditemukan.";
        }
    }
}